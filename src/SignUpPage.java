import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SignUpPage {
    // frame
    private JFrame frame;
    // text fields - username, password, confirm password
    private JTextField username;
    private JTextField password;
    private JTextField confirmPassword;
    // button
    private JButton signUp;

    // constructor
    public SignUpPage() {
        // create frame
        frame = new JFrame("Sign Up");
        // create text fields
        username = new JTextField(10);
        // size
        username.setSize(100, 50);
        // placeholder
        username.setText("Username");
        password = new JTextField(10);
        password.setSize(100, 50);
        password.setText("Password");
        confirmPassword = new JTextField(10);
        confirmPassword.setSize(100, 50);
        confirmPassword.setText("Confirm Password");
        // create button
        signUp = new JButton("Sign Up");
        signUp.setSize(100, 50);

        // call method signUp when button is clicked
        signUp.addActionListener(e -> signUp());

        // add components to frame
        frame.add(username);
        frame.add(password);
        frame.add(confirmPassword);
        frame.add(signUp);

        // positioning

        // username - top left
        username.setBounds(10, 10, 100, 50);

        // password - top left + 50
        password.setBounds(10, 60, 100, 50);

        // confirm password - top left + 100
        confirmPassword.setBounds(10, 110, 100, 50);

        // sign up - top left + 150
        signUp.setBounds(10, 160, 100, 50);

        // set frame properties
        frame.setSize(500, 300);
        frame.setLayout(null);
    }

    // show the SIGN UP page
    public void show() {
        frame.setVisible(true);
    }

    // method to sign up
    public void signUp() {
        // get username, password, confirm password
        String user = username.getText();
        String pass = password.getText();
        String confirmPass = confirmPassword.getText();
        // check if username, password, confirm password are empty
        if (user.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
            // show error message
            JOptionPane.showMessageDialog(frame, "Please fill in all fields");
        } else {
            // check if password and confirm password are the same
            if (pass.equals(confirmPass)) {
                // show success message
                JOptionPane.showMessageDialog(frame, "Sign up successful");
                // close the frame and open login page
                frame.dispose();
                LoginPage loginPage = new LoginPage();
                loginPage.show();
            } else {
                // show error message
                JOptionPane.showMessageDialog(frame, "Password and confirm password do not match");
            }
        }
    }

}
