package ctbu.qx.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginView extends View{
    private final static JFrame frame = new JFrame("LoginView");

    private JButton okButton;
    private JButton exitButton;
    private JCheckBox adminCheckBox;
    private JCheckBox studentCheckBox;
    private JTextField studentIdTextField;
    private JPanel loginPanel;

    public LoginView() {
        initialize();
    }

    private void initialize() {

        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(-1);
            }
        });

        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // 创建其他窗口
                if (adminCheckBox.isSelected()) {
                    AdminClientView adminClientView = new AdminClientView();
                    adminClientView.launch();
                    exit();
                } else if (studentCheckBox.isSelected() && !studentIdTextField.getText().equals("")){
                    StudentClientView studentClientView = new StudentClientView(getThis());
                    studentClientView.launch();
                    exit();
                }
            }
        });

        adminCheckBox.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (adminCheckBox.isSelected()) {
                    if (studentCheckBox.isSelected())
                        studentCheckBox.setSelected(false);
                }
            }
        });

        studentCheckBox.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (studentCheckBox.isSelected()) {
                    if (adminCheckBox.isSelected())
                        adminCheckBox.setSelected(false);
                }
            }
        });
    }

    @Override
    public void launch() {

        frame.setContentPane(new LoginView().loginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int x = (int)(toolkit.getScreenSize().getWidth()-frame.getWidth())/2;
        int y = (int)(toolkit.getScreenSize().getHeight()-frame.getHeight())/2;
        frame.setLocation(x-200, y-100);

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void exit() {
        frame.setVisible(false);
        frame.dispose();
    }

    public int getStudentId() {
        return Integer.parseInt(studentIdTextField.getText());
    }

    public LoginView getThis() {
        return this;
    }
}
