package ctbu.qx.mapper;

import ctbu.qx.pojo.Student;

public interface StudentMapper {
    Student selectStudent(int studentId);
    void insertStudent(Student student);
    void deleteStudent(int studentId);
    void updateStudent(Student student);
}
