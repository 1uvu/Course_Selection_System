package ctbu.qx.mapper;

import ctbu.qx.mapper.CourseMapper;
import ctbu.qx.pojo.Course;
import ctbu.qx.pojo.Student;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class CourseMapperImpl extends SqlSessionDaoSupport implements CourseMapper {

    public Course selectCourse(int courseId) {
        SqlSession sqlSession = getSqlSession();

        CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
        try {
            return mapper.selectCourse(courseId);
        } catch (Exception e) {
            return new Course(-1, "", "", 0, 0, 0, 0, 0, 0, "");
        }
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
        mapper.deleteCourse(courseId);
    }

    public void updateCourse(Course course) {
        SqlSession sqlSession = getSqlSession();

        CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
        mapper.updateCourse(course);
    }
}
