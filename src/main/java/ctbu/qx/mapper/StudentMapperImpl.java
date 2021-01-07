package ctbu.qx.mapper;

import ctbu.qx.pojo.Student;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class StudentMapperImpl extends SqlSessionDaoSupport implements StudentMapper {

    public Student selectStudent(int studentId) {
        SqlSession sqlSession = getSqlSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        return mapper.selectStudent(studentId);
    }

    public void insertStudent(Student student) {
        SqlSession sqlSession = getSqlSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        mapper.insertStudent(student);
    }

    public void deleteStudent(int studentId) {
        SqlSession sqlSession = getSqlSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        mapper.deleteStudent(studentId);
    }

    public void updateStudent(Student student) {
        SqlSession sqlSession = getSqlSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        mapper.updateStudent(student);
    }
}
