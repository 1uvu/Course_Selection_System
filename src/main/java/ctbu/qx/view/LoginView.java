package ctbu.qx.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

    public static void main(String[] args) {
        LoginView view = new LoginView();
        view.launch();
    }


    private void initialize() {

        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("exit button clicked");
                System.exit(-1);
            }
        });

        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("ok button clicked");
                // 创建其他窗口
                if (adminCheckBox.isSelected()) {
                    AdminClientView adminClientView = new AdminClientView();
                    adminClientView.launch();
                    exit();
                } else if (studentCheckBox.isSelected() && studentIdTextField.getText() != null){
                    StudentClientView studentClientView = new StudentClientView(Integer.parseInt(studentIdTextField.getText()));
                    studentClientView.launch();
                    exit();
                }
            }
        });

        adminCheckBox.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (adminCheckBox.isSelected()) {
                    System.out.println("admin selected");
                    if (studentCheckBox.isSelected())
                        studentCheckBox.setSelected(false);
                }
            }
        });

        studentCheckBox.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (studentCheckBox.isSelected()) {
                    System.out.println("student selected");
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
        frame.setLocation(x, y);

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void exit() {
        frame.setVisible(false);
        frame.dispose();
    }


}
