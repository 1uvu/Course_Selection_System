package ctbu.qx.mapper;

import ctbu.qx.pojo.Student;

import java.util.List;

public interface StudentMapper {
    Student selectStudent(int studentId);
    List<Student> selectAllStudent();
    void insertStudent(Student student);
    void deleteStudent(int studentId);
    void updateStudent(Student student);

    void deleteTrigger(int studentId);
}
