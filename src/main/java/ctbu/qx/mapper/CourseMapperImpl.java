package ctbu.qx.mapper;

import ctbu.qx.pojo.Course;
import ctbu.qx.pojo.Selection;
import ctbu.qx.pojo.Student;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class CourseMapperImpl extends SqlSessionDaoSupport implements CourseMapper {

    public Course selectCourse(int courseId) {
        SqlSession sqlSession = getSqlSession();

        CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
        Course course = mapper.selectCourse(courseId);
        if (course != null)
            return course;
        else
            return new Course(-1, "", "", 0, 0, 0, 0, 0, 0, "");
    }

    public List<Course> selectAllCourse() {
        SqlSession sqlSession = getSqlSession();

        CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
        return mapper.selectAllCourse();
    }

    @Override
    public List<Course> selectAllCourseByKind(String courseKind) {
        SqlSession sqlSession = getSqlSession();

        CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
        return mapper.selectAllCourseByKind(courseKind);
    }



    public void insertCourse(Course course) {
        SqlSession sqlSession = getSqlSession();

        CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
        mapper.insertCourse(course);
    }

    public void deleteCourse(int courseId) {
        SqlSession sqlSession = getSqlSession();

        CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
        deleteTrigger(courseId);
        mapper.deleteCourse(courseId);
    }

    public void updateCourse(Course course) {
        SqlSession sqlSession = getSqlSession();

        CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
        updateTrigger(course);
        mapper.updateCourse(course);
    }

    @Override
    public void deleteTrigger(int courseId) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
        SelectionMapperImpl selectionMapper = (SelectionMapperImpl) context.getBean("selectionMapper");
        selectionMapper.deleteSelectionByCourseId(courseId);
    }

    @Override
    public void updateTrigger(Course course) {
        SqlSession sqlSession = getSqlSession();

        CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
        StudentMapperImpl studentMapper = (StudentMapperImpl) context.getBean("studentMapper");
        SelectionMapperImpl selectionMapper = (SelectionMapperImpl) context.getBean("selectionMapper");

        Course oldCourse = courseMapper.selectCourse(course.getCourseId());
        List<Selection> selectionList = selectionMapper.selectSelectionByCourseId(course.getCourseId());
        for (Selection selection: selectionList) {
            Student student = studentMapper.selectStudent(selection.getStudentId());
            student.setStudentCurrCourseScore(student.getStudentCurrCourseScore() - oldCourse.getCourseScore() + course.getCourseScore());
            studentMapper.updateStudent(student);
        }
    }
}
