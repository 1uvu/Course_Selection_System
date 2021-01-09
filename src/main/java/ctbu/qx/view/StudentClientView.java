package ctbu.qx.view;

import ctbu.qx.ctrl.CourseCtrlImpl;
import ctbu.qx.ctrl.SelectionCtrlImpl;
import ctbu.qx.ctrl.StudentCtrlImpl;
import ctbu.qx.mapper.CourseMapperImpl;
import ctbu.qx.mapper.SelectionMapperImpl;
import ctbu.qx.mapper.StudentMapperImpl;
import ctbu.qx.pojo.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentClientView extends View{
    private final static JFrame frame = new JFrame("StudentClientView");

    private JButton selectButton;
    private JButton quitButton;
    private JTextArea studentInfoTextArea;
    private JTextArea adviceTextArea;
    private JPanel studentClientPanel;
    private JTextArea allCourseTextArea;
    private JTextArea selectedCourseTextArea;
    private JTextField selectedTextField;
    private JTextField quitTextField;

    private final LoginView view;


    private final ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
    private final CourseMapperImpl courseMapper = (CourseMapperImpl) context.getBean("courseMapper");
    private final StudentMapperImpl studentMapper = (StudentMapperImpl) context.getBean("studentMapper");
    private final SelectionMapperImpl selectionMapper = (SelectionMapperImpl) context.getBean("selectionMapper");

    private final CourseCtrlImpl courseCtrl = new CourseCtrlImpl(courseMapper);
    private final StudentCtrlImpl studentCtrl = new StudentCtrlImpl(studentMapper);
    private final SelectionCtrlImpl selectionCtrl = new SelectionCtrlImpl(selectionMapper);
    private Student student;

    public StudentClientView(LoginView view) {
        this.view = view;
        initialize();
    }

    private void initialize(){
        this.student = studentMapper.selectStudent(view.getStudentId());

        initializeAllCourseDataArea();
        initializeSelectedCourseDataArea();
        initializeBottomDataArea();

        selectButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!selectedTextField.getText().equals("")) {
                    if (
                    selectionCtrl.selectCourse(
                            Integer.parseInt(selectedTextField.getText()),
                            student.getStudentId()
                    )
                    ) {
                        selectedTextField.setText("");
                        initializeSelectedCourseDataArea();
                        initializeBottomDataArea();
                    }
                    else {
                        adviceTextArea.append("\n警告：选课失败！当前课程已选或名额已满，请选择其它课程。");
                    }

                }
            }
        });

        quitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!quitTextField.getText().equals("")) {
                    if (
                    selectionCtrl.deleteCourse(
                            Integer.parseInt(quitTextField.getText()),
                            student.getStudentId()
                    )
                    ) {
                        quitTextField.setText("");
                        initializeSelectedCourseDataArea();
                        initializeBottomDataArea();
                    }else {
                        adviceTextArea.append("\n警告：退课失败！当前课程未选。");
                    }

                }
            }
        });
    }

    @Override
    public void launch() {
        frame.setContentPane(new StudentClientView(view).studentClientPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int x = (int)(toolkit.getScreenSize().getWidth()-frame.getWidth())/2;
        int y = (int)(toolkit.getScreenSize().getHeight()-frame.getHeight())/2;
        frame.setLocation(x-600, y-200);

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void exit() {

    }

    public void initializeAllCourseDataArea() {
        allCourseTextArea.setText("");
        allCourseTextArea.append(courseCtrl.showAllCourse());
    }

    public void initializeSelectedCourseDataArea() {
        student = studentMapper.selectStudent(student.getStudentId());
        selectedCourseTextArea.setText("");
        selectedCourseTextArea.append(selectionCtrl.showStudentInfo(student.getStudentId(), courseMapper));
    }

    public void initializeBottomDataArea() {
        student = studentMapper.selectStudent(student.getStudentId());
        studentInfoTextArea.setText("");
        adviceTextArea.setText("");
        studentInfoTextArea.append(student.toString());
        adviceTextArea.append(studentCtrl.makeAdvice(student));
    }
}
