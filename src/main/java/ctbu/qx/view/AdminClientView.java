package ctbu.qx.view;

import ctbu.qx.ctrl.CourseCtrlImpl;
import ctbu.qx.ctrl.SelectionCtrl;
import ctbu.qx.ctrl.SelectionCtrlImpl;
import ctbu.qx.ctrl.StudentCtrlImpl;
import ctbu.qx.mapper.CourseMapperImpl;
import ctbu.qx.mapper.SelectionMapperImpl;
import ctbu.qx.mapper.StudentMapperImpl;
import ctbu.qx.pojo.Course;
import ctbu.qx.pojo.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.util.List;

public class AdminClientView extends View{
    private final static JFrame frame = new JFrame("AdminClientView");
    private JPanel adminClientPanel;
    private JButton addStudentButton;
    private JButton updateStudentButton;
    private JButton deleteStudentButton;
    private JTextArea studentInfoTextArea;
    private JButton addCourseButton;
    private JButton updateCourseButton;
    private JButton deleteCourseButton;
    private JTextArea courseInfoTextArea;
    private JTextArea courseDataTextArea;
    private JTextArea studentDataTextArea;
    private JTextField courseIdTextField;
    private JTextField studentIdTextField;
    private JButton showCourseButton;
    private JButton showStudentButton;

    private CourseEditView courseView;
    private StudentEditView studentView;
    private boolean isCourseUpdate = false;
    private boolean isStudentUpdate = false;

    private final ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
    private final CourseMapperImpl courseMapper = (CourseMapperImpl) context.getBean("courseMapper");
    private final StudentMapperImpl studentMapper = (StudentMapperImpl) context.getBean("studentMapper");
    private final SelectionMapperImpl selectionMapper = (SelectionMapperImpl) context.getBean("selectionMapper");

    private final CourseCtrlImpl courseCtrl = new CourseCtrlImpl(courseMapper);
    private final StudentCtrlImpl studentCtrl = new StudentCtrlImpl(studentMapper);
    private final SelectionCtrlImpl selectionCtrl = new SelectionCtrlImpl(selectionMapper);

    public AdminClientView() {
        initialize();
    }

    private void initialize() {
        initializeDataArea();

        addCourseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isCourseUpdate = false;
                super.mouseClicked(e);
                 CourseEditView courseEditView = new CourseEditView(getThis());
                 courseView = courseEditView;
                 courseEditView.launch();
            }
        });

        updateCourseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!courseIdTextField.getText().equals("")) {
                    isCourseUpdate = true;
                    CourseEditView courseEditView = new CourseEditView(getThis());
                    courseView = courseEditView;
                    courseEditView.launch();
                }
            }
        });

        deleteCourseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isCourseUpdate = false;
                super.mouseClicked(e);
                if (!courseIdTextField.getText().equals("")) {
                    courseCtrl.deleteCourse(Integer.parseInt(courseIdTextField.getText()));
                    initializeDataArea();
                }
            }
        });

        showCourseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isCourseUpdate = false;
                courseInfoTextArea.setText("");
                super.mouseClicked(e);
                if (!courseIdTextField.getText().equals("")) {
                    courseInfoTextArea.append(courseCtrl.showOneCourse(Integer.parseInt(courseIdTextField.getText())));
                    courseInfoTextArea.append(selectionCtrl.showCourseInfo(
                            Integer.parseInt(courseIdTextField.getText()),
                            studentMapper
                    ));
                }
            }
        });

        addStudentButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isStudentUpdate = false;
                super.mouseClicked(e);
                StudentEditView studentEditView = new StudentEditView(getThis());
                studentView = studentEditView;
                studentEditView.launch();
            }
        });


        updateStudentButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!studentIdTextField.getText().equals("")) {
                    isStudentUpdate = true;
                    StudentEditView studentEditView = new StudentEditView(getThis());
                    studentView = studentEditView;
                    studentEditView.launch();
                }
            }
        });

        deleteStudentButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isStudentUpdate = false;
                super.mouseClicked(e);
                if (!studentIdTextField.getText().equals("")) {
                    studentCtrl.deleteStudent(Integer.parseInt(studentIdTextField.getText()));
                    initializeDataArea();
                }
            }
        });

        showStudentButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isStudentUpdate = false;
                studentInfoTextArea.setText("");
                super.mouseClicked(e);
                if (!studentIdTextField.getText().equals("")) {
                    studentInfoTextArea.append(studentCtrl.showOneStudent(Integer.parseInt(studentIdTextField.getText())));
                    studentInfoTextArea.append(selectionCtrl.showStudentInfo(
                            Integer.parseInt(studentIdTextField.getText()),
                            courseMapper
                    ));
                }
            }
        });
    }

    @Override
    public void launch() {
        frame.setContentPane(new AdminClientView().adminClientPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int x = (int)(toolkit.getScreenSize().getWidth()-frame.getWidth())/2;
        int y = (int)(toolkit.getScreenSize().getHeight()-frame.getHeight())/2;
        frame.setLocation(x, y);

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void exit(){

    }

    private AdminClientView getThis() {
        return this;
    }

    public void initializeDataArea() {
        courseDataTextArea.setText("");
        studentDataTextArea.setText("");

        courseDataTextArea.setText(courseCtrl.showAllCourse());
        studentDataTextArea.setText(studentCtrl.showAllStudent());
    }

    public int getCourseId() {
        return Integer.parseInt(courseIdTextField.getText());
    }

    public int getStudentId() {
        return Integer.parseInt(studentIdTextField.getText());
    }

    public boolean isCourseUpdate() {
        return isCourseUpdate;
    }

    public boolean isStudentUpdate() {
        return isStudentUpdate;
    }
}
