package ctbu.qx.mapper;

import ctbu.qx.pojo.Course;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class CourseMapperImpl extends SqlSessionDaoSupport implements CourseMapper {

    public Course selectCourse(int courseId) {
        SqlSession sqlSession = getSqlSession();

        CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
        return mapper.selectCourse(courseId);
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
