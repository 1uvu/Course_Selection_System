package ctbu.qx.view;

import ctbu.qx.ctrl.StudentCtrlImpl;
import ctbu.qx.mapper.StudentMapperImpl;
import ctbu.qx.pojo.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentEditView extends View{
    private final static JFrame frame = new JFrame("StudentEditView");

    private JTextField studentIdTextField;
    private JTextField studentNameTextField;
    private JTextField studentAgeTextField;
    private JTextField minScoreTextField;
    private JTextField maxScoreTextField;
    private JTextField pubCourseNumberTextField;
    private JTextField reqCourseNumberTextField;
    private JTextField selCourseNumberTextField;
    private JButton okButton;
    private JButton cancelButton;
    private JPanel studentEditPanel;

    private Student student;

    private final AdminClientView view;

    private final ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
    private final StudentMapperImpl studentMapper = (StudentMapperImpl) context.getBean("studentMapper");

    private final StudentCtrlImpl studentCtrl = new StudentCtrlImpl(studentMapper);

    public StudentEditView(AdminClientView view) {
        this.view = view;
        initialize();
    }


    private void initialize() {
        if (view.isStudentUpdate()) {
            parseStudent();
        }

        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                genStudent();
                view.initializeDataArea();
                view.initializeInfoArea();
                exit();
            }
        });

        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                exit();
            }
        });
    }


    @Override
    public void launch() {
        frame.setContentPane(new StudentEditView(view).studentEditPanel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int x = (int)(toolkit.getScreenSize().getWidth()-frame.getWidth())/2;
        int y = (int)(toolkit.getScreenSize().getHeight()-frame.getHeight())/2;
        frame.setLocation(x, y);

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void exit(){
        frame.setVisible(false);
        frame.dispose();
    }

    private void genStudent() {
        Student student = new Student(
                Integer.parseInt(studentIdTextField.getText()),
                studentNameTextField.getText(),
                Integer.parseInt(studentAgeTextField.getText()),
                Integer.parseInt(minScoreTextField.getText()),
                Integer.parseInt(maxScoreTextField.getText()),
                Integer.parseInt(pubCourseNumberTextField.getText()),
                Integer.parseInt(reqCourseNumberTextField.getText()),
                Integer.parseInt(selCourseNumberTextField.getText())
        );

        if (this.student != null) student.setStudentCurrCourseScore(this.student.getStudentCurrCourseScore());

        System.out.println(student);
        if (view.isStudentUpdate())
            studentCtrl.updateStudent(student);
        else
            studentCtrl.addStudent(student);

    }

    public void parseStudent() {
        Student student = studentMapper.selectStudent(view.getStudentId());
        studentIdTextField.setText(String.valueOf(student.getStudentId()));
        studentIdTextField.setEditable(false);
        studentNameTextField.setText(String.valueOf(student.getStudentName()));
        studentAgeTextField.setText(String.valueOf(student.getStudentAge()));
        minScoreTextField.setText(String.valueOf(student.getStudentMinCourseScore()));
        maxScoreTextField.setText(String.valueOf(student.getStudentMaxCourseScore()));
        pubCourseNumberTextField.setText(String.valueOf(student.getStudentNeedPubCourse()));
        reqCourseNumberTextField.setText(String.valueOf(student.getStudentNeedReqCourse()));
        selCourseNumberTextField.setText(String.valueOf(student.getStudentNeedSelCourse()));

        this.student = student;
    }

}
