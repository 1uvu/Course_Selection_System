package ctbu.qx.view;

import ctbu.qx.mapper.StudentMapperImpl;
import ctbu.qx.pojo.Student;

import javax.swing.*;
import java.awt.*;

public class StudentClientView extends View{
    private final static JFrame frame = new JFrame("StudentClientView");

    private JTable allCourseTable;
    private JButton selectButton;
    private JTable selectedCourseTable;
    private JButton quitButton;
    private JTextArea studentInfoTextArea;
    private JTextArea adviceTextArea;
    private JPanel studentClientPanel;

    private final StudentMapperImpl mapper = new StudentMapperImpl();
    private final Student student;

    public StudentClientView(int studentId) {
        student = mapper.selectStudent(studentId);
        initialize();
    }

    private void initialize(){

    }

    @Override
    public void launch() {
        frame.setContentPane(new StudentClientView(student.getStudentId()).studentClientPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int x = (int)(toolkit.getScreenSize().getWidth()-frame.getWidth())/2;
        int y = (int)(toolkit.getScreenSize().getHeight()-frame.getHeight())/2;
        frame.setLocation(x, y);

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void exit() {

    }

    public Student getStudent() {
        return student;
    }

    public StudentMapperImpl getMapper() {
        return mapper;
    }

}
