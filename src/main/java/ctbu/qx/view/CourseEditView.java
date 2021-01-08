package ctbu.qx.view;

import ctbu.qx.ctrl.CourseCtrlImpl;
import ctbu.qx.mapper.CourseMapperImpl;
import ctbu.qx.pojo.Course;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CourseEditView extends View {
    private final static JFrame frame = new JFrame("CourseEditView");

    private JTextField courseIdTextField;
    private JTextField courseNameTextField;
    private JTextField courseKindTextField;
    private JTextField courseHourTextField;
    private JTextField classHourTextField;
    private JTextField testHourTextField;
    private JTextField maxStudentNumberTextField;
    private JTextField courseScoreTextField;
    private JTextField coursePeriodTextField;
    private JButton okButton;
    private JButton cancelButton;
    private JPanel courseEditPanel;

    private final AdminClientView view;

    private final ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
    private final CourseMapperImpl courseMapper = (CourseMapperImpl) context.getBean("courseMapper");

    private final CourseCtrlImpl courseCtrl = new CourseCtrlImpl(courseMapper);

    public CourseEditView(AdminClientView view) {
        this.view = view;
        initialize();
    }

    private void initialize() {
        if (view.isCourseUpdate()) {
            parseCourse();
        }

        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                genCourse();
                view.initializeDataArea();
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
        frame.setContentPane(new CourseEditView(view).courseEditPanel);
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

    private void genCourse() {
        Course course = new Course(
                Integer.parseInt(courseIdTextField.getText()),
                courseNameTextField.getText(),
                courseKindTextField.getText(),
                Integer.parseInt(courseHourTextField.getText()),
                Integer.parseInt(classHourTextField.getText()),
                Integer.parseInt(testHourTextField.getText()),
                Integer.parseInt(maxStudentNumberTextField.getText()),
                Integer.parseInt(courseScoreTextField.getText()),
                coursePeriodTextField.getText()
        );

        courseCtrl.addCourse(course);
    }

    public void parseCourse() {
        Course course = courseMapper.selectCourse(view.getCourseId());
        courseIdTextField.setText(String.valueOf(course.getCourseId()));
        courseIdTextField.setEditable(false);
        courseNameTextField.setText(String.valueOf(course.getCourseName()));
        courseKindTextField.setText(String.valueOf(course.getCourseKind()));
        courseHourTextField.setText(String.valueOf(course.getCourseHour()));
        classHourTextField.setText(String.valueOf(course.getClassHour()));
        testHourTextField.setText(String.valueOf(course.getTestHour()));
        maxStudentNumberTextField.setText(String.valueOf(course.getCourseMaxStudentNumber()));
        courseScoreTextField.setText(String.valueOf(course.getCourseScore()));
        coursePeriodTextField.setText(String.valueOf(course.getCoursePeriod()));

        courseCtrl.updateCourse(course);
    }

    public CourseMapperImpl getCourseMapper() {
        return courseMapper;
    }
}
