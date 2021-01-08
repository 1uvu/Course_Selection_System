package ctbu.qx.ctrl;

import ctbu.qx.mapper.StudentMapperImpl;
import ctbu.qx.pojo.Course;
import ctbu.qx.pojo.Student;

import java.util.List;

public class StudentCtrlImpl implements StudentCtrl {
    private final StudentMapperImpl studentMapper;

    public StudentCtrlImpl(StudentMapperImpl studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public boolean addStudent(Student student) {
        Student _student = studentMapper.selectStudent(student.getStudentId());
        if (_student.getStudentId() == -1) return false;
        else studentMapper.insertStudent(student);
        return true;
    }

    @Override
    public boolean updateStudent(Student student) {
        Student _student = studentMapper.selectStudent(student.getStudentId());
        if (_student.getStudentId() == -1) return false;
        else studentMapper.updateStudent(student);
        return true;
    }

    @Override
    public boolean deleteStudent(int studentId) {
        Student _student = studentMapper.selectStudent(studentId);
        if (_student.getStudentId() == -1) return false;
        else studentMapper.deleteStudent(studentId);
        return true;
    }

    @Override
    public String showOneStudent(int studentId) {
        Student student = studentMapper.selectStudent(studentId);
        StringBuilder rtn = new StringBuilder();

        if (student.getStudentId() != -1) {
            rtn.append("基本信息如下：\n").append(student.toString()).append("\n").append("该学生当前选课情况如下：\n");
        }
        return rtn.toString();
    }

    @Override
    public String showAllStudent() {
        List<Student> studentList = studentMapper.selectAllStudent();

        StringBuilder rtn = new StringBuilder();
        for (Student student: studentList) {
            rtn.append(student.toString()).append("\n");
        }

        return rtn.toString();
    }

    @Override
    public boolean isSelectionValid(Student newStudent) {
        return newStudent.getStudentCurrCourseScore() < newStudent.getStudentMaxCourseScore();
    }

    @Override
    public String makeAdvice(Student student) {

        StringBuilder rtn = new StringBuilder();
        // adv.1 学分评价
        if (student.getStudentCurrCourseScore() < student.getStudentMinCourseScore())
            rtn.append("1. 学分建议：学分不足，还缺 ")
                    .append(String.valueOf(student.getStudentMinCourseScore() - student.getStudentCurrCourseScore()))
                    .append(" 分")
                    .append("\n");
        else rtn.append("1. 学分建议：学分充足").append("\n");
        // todo adv.2 已选课程分布评价

        return rtn.toString();
    }

    public StudentMapperImpl getStudentMapper() {
        return studentMapper;
    }
}
