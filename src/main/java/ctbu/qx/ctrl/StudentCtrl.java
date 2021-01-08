package ctbu.qx.ctrl;

import ctbu.qx.pojo.Course;
import ctbu.qx.pojo.Student;

public interface StudentCtrl {
    boolean addStudent(Student student);
    boolean updateStudent(Student student);
    boolean deleteStudent(int studentId);
    String showOneStudent(int studentId);
    String showAllStudent();  // 查看所有学生
    boolean isSelectionValid(Student newStudent);  // 选前判断是否合规
    String makeAdvice(Student student);  // 给出选课建议
}
