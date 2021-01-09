package ctbu.qx.mapper;

import ctbu.qx.pojo.Course;
import ctbu.qx.pojo.Selection;
import ctbu.qx.pojo.Student;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

public class SelectionMapperImpl extends SqlSessionDaoSupport implements SelectionMapper {
    public Selection selectSelection(int courseId, int studentId) {
        SqlSession sqlSession = getSqlSession();

        SelectionMapper selectionMapper = sqlSession.getMapper(SelectionMapper.class);
        Selection selection = selectionMapper.selectSelection(courseId, studentId);

        if (selection != null)
            return selection;
        else
            return new Selection(-1, -1, new Date());
    }

    @Override
    public List<Selection> selectSelectionByStudentId(int studentId) {
        SqlSession sqlSession = getSqlSession();

        SelectionMapper mapper = sqlSession.getMapper(SelectionMapper.class);
        return mapper.selectSelectionByStudentId(studentId);
    }

    @Override
    public List<Selection> selectSelectionByCourseId(int courseId) {
        SqlSession sqlSession = getSqlSession();

        SelectionMapper mapper = sqlSession.getMapper(SelectionMapper.class);
        return mapper.selectSelectionByCourseId(courseId);
    }

    public void insertSelection(Selection selection) {
        SqlSession sqlSession = getSqlSession();

        SelectionMapper mapper = sqlSession.getMapper(SelectionMapper.class);
        insertTrigger(selection);
        mapper.insertSelection(selection);
    }

    public void deleteSelection(int courseId, int studentId) {
        SqlSession sqlSession = getSqlSession();

        SelectionMapper mapper = sqlSession.getMapper(SelectionMapper.class);
        deleteTrigger(courseId, studentId);
        mapper.deleteSelection(courseId, studentId);
    }

    @Override
    public void deleteSelectionByStudentId(int studentId) {
        SqlSession sqlSession = getSqlSession();

        SelectionMapper mapper = sqlSession.getMapper(SelectionMapper.class);
        deleteByStudentIdTrigger(studentId);
        mapper.deleteSelectionByStudentId(studentId);
    }

    @Override
    public void deleteSelectionByCourseId(int courseId) {
        SqlSession sqlSession = getSqlSession();

        SelectionMapper mapper = sqlSession.getMapper(SelectionMapper.class);
        deleteByCourseIdTrigger(courseId);
        mapper.deleteSelectionByCourseId(courseId);
    }


    @Override
    public void insertTrigger(Selection selection) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
        StudentMapperImpl studentMapper = (StudentMapperImpl) context.getBean("studentMapper");
        CourseMapperImpl courseMapper = (CourseMapperImpl) context.getBean("courseMapper");

        Course course = courseMapper.selectCourse(selection.getCourseId());
        course.setCourseCurrStudentNumber(course.getCourseCurrStudentNumber()+1);
        courseMapper.updateTrigger(course);
        Student student = studentMapper.selectStudent(selection.getStudentId());
        student.setStudentCurrCourseScore(student.getStudentCurrCourseScore() + course.getCourseScore());
        studentMapper.updateStudent(student);
    }

    @Override
    public void deleteTrigger(int courseId, int studentId) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
        StudentMapperImpl studentMapper = (StudentMapperImpl) context.getBean("studentMapper");
        CourseMapperImpl courseMapper = (CourseMapperImpl) context.getBean("courseMapper");

        Course course = courseMapper.selectCourse(courseId);
        course.setCourseCurrStudentNumber(course.getCourseCurrStudentNumber()-1);
        courseMapper.updateTrigger(course);
        Student student = studentMapper.selectStudent(studentId);
        student.setStudentCurrCourseScore(student.getStudentCurrCourseScore() - course.getCourseScore());
        studentMapper.updateStudent(student);
    }

    @Override
    public void deleteByStudentIdTrigger(int studentId) {
        SqlSession sqlSession = getSqlSession();

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
        StudentMapperImpl studentMapper = (StudentMapperImpl) context.getBean("studentMapper");
        CourseMapperImpl courseMapper = (CourseMapperImpl) context.getBean("courseMapper");

        SelectionMapper selectionMapper = sqlSession.getMapper(SelectionMapper.class);

        Student student = studentMapper.selectStudent(studentId);
        if (student.getStudentId() != -1)
            student.setStudentCurrCourseScore(0);

        List<Selection> selectionList = selectionMapper.selectSelectionByStudentId(studentId);

        for (Selection selection: selectionList) {
            Course course = courseMapper.selectCourse(selection.getCourseId());
            course.setCourseCurrStudentNumber(course.getCourseCurrStudentNumber()-1);
            courseMapper.updateTrigger(course);
        }
    }

    @Override
    public void deleteByCourseIdTrigger(int courseId) {
        SqlSession sqlSession = getSqlSession();

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
        StudentMapperImpl studentMapper = (StudentMapperImpl) context.getBean("studentMapper");
        CourseMapperImpl courseMapper = (CourseMapperImpl) context.getBean("courseMapper");
        SelectionMapper selectionMapper = sqlSession.getMapper(SelectionMapper.class);

        Course course = courseMapper.selectCourse(courseId);
        List<Selection> selectionList = selectionMapper.selectSelectionByCourseId(courseId);

        course.setCourseCurrStudentNumber(course.getCourseCurrStudentNumber()-selectionList.size());
        courseMapper.updateTrigger(course);
        for (Selection selection: selectionList) {
            Student student = studentMapper.selectStudent(selection.getStudentId());
            student.setStudentCurrCourseScore(student.getStudentCurrCourseScore() - course.getCourseScore());
            studentMapper.updateStudent(student);
        }
    }
}
