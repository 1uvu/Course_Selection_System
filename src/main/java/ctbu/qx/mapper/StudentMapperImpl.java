package ctbu.qx.mapper;

import ctbu.qx.pojo.Student;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class StudentMapperImpl extends SqlSessionDaoSupport implements StudentMapper {

    public Student selectStudent(int studentId) {
        SqlSession sqlSession = getSqlSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectStudent(studentId);
        if (student != null)
            return student;
        else
            return new Student(-1, "", 0, 0, 0, 0, 0, 0, 0);
    }

    public List<Student> selectAllStudent() {
        SqlSession sqlSession = getSqlSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        return mapper.selectAllStudent();
    }

    public void insertStudent(Student student) {
        SqlSession sqlSession = getSqlSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        mapper.insertStudent(student);
    }

    public void deleteStudent(int studentId) {
        SqlSession sqlSession = getSqlSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        deleteTrigger(studentId);
        mapper.deleteStudent(studentId);
    }

    public void updateStudent(Student student) {
        SqlSession sqlSession = getSqlSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        mapper.updateStudent(student);
    }

    @Override
    public void deleteTrigger(int studentId) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
        SelectionMapperImpl selectionMapper = (SelectionMapperImpl) context.getBean("selectionMapper");

        selectionMapper.deleteSelectionByStudentId(studentId);
    }
}
