package ctbu.qx.ctrl;

import ctbu.qx.mapper.CourseMapperImpl;
import ctbu.qx.mapper.SelectionMapperImpl;
import ctbu.qx.mapper.StudentMapperImpl;
import ctbu.qx.pojo.Course;
import ctbu.qx.pojo.Selection;
import ctbu.qx.pojo.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class StudentCtrlImpl implements StudentCtrl {
    private final StudentMapperImpl studentMapper;

    public StudentCtrlImpl(StudentMapperImpl studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public boolean addStudent(Student student) {
        Student _student = studentMapper.selectStudent(student.getStudentId());
        if (_student.getStudentId() != -1) return false;
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
            rtn.append("1. 学分建议：当前学分").append(student.getStudentCurrCourseScore())
                    .append("\n学分不足，还缺 ")
                    .append(student.getStudentMinCourseScore() - student.getStudentCurrCourseScore())
                    .append(" 分")
                    .append("\n");
        else rtn.append("1. 学分建议：当前学分").append(student.getStudentCurrCourseScore())
                .append("\n学分充足").append("\n");

        if (student.getStudentCurrCourseScore() > student.getStudentMaxCourseScore())
            rtn.append("但学分已超 ")
                    .append(student.getStudentCurrCourseScore() - student.getStudentMaxCourseScore())
                    .append(" 分。");

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
        CourseMapperImpl courseMapper = (CourseMapperImpl) context.getBean("courseMapper");
        SelectionMapperImpl selectionMapper = (SelectionMapperImpl) context.getBean("selectionMapper");

        List<Selection> selectionList = selectionMapper.selectSelectionByStudentId(student.getStudentId());

        int pubNum = 0;
        int reqNum = 0;
        int selNum = 0;
        for (Selection selection: selectionList) {
            Course course = courseMapper.selectCourse(selection.getCourseId());
            switch (course.getCourseKind()) {
                case "公共课":
                    pubNum++;
                    break;
                case "必修课":
                    reqNum++;
                    break;
                case "选修课":
                    selNum++;
                    break;
            }
        }

        rtn.append("2. 选课建议：\n目前已选 ").append(pubNum)
                .append(" 门公共课，").append(reqNum)
                .append(" 门必修课,").append(selNum)
                .append(" 门选修课。\n");

        if (pubNum < student.getStudentNeedPubCourse())
            rtn.append("还差 ").append(student.getStudentNeedPubCourse() - pubNum).append(" 门公共课。");
        else rtn.append("公共课已选择足够。");
        if (reqNum < student.getStudentNeedReqCourse())
            rtn.append("还差 ").append(student.getStudentNeedReqCourse() - reqNum).append(" 门必修课。");
        else rtn.append("必修课已选择足够。");
        if (selNum < student.getStudentNeedSelCourse())
            rtn.append("还差 ").append(student.getStudentNeedSelCourse() - selNum).append(" 门选修课。");
        else rtn.append("选修课已选择足够。");

        return rtn.toString();
    }

    public StudentMapperImpl getStudentMapper() {
        return studentMapper;
    }
}
