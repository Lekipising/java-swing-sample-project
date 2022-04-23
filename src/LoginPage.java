import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

// create a login frame with 2 input fields and 1 button using swing
public class LoginPage {
    // frame
    private JFrame frame;
    // text fields
    private JTextField username;
    private JPasswordField password;
    // button
    private JButton login;

    // constructor
    public LoginPage() {
        // create frame
        frame = new JFrame("Login");
        // create text fields
        username = new JTextField(10);
        // username size
        username.setSize(100, 50);
        // placeholder
        username.setText("Username");
        password = new JPasswordField(10);
        password.setSize(100, 50);
        password.setText("Password");
        // create button
        login = new JButton("Login");
        login.setSize(100, 50);

        // call method login when button is clicked
        login.addActionListener(e -> login());

        // add components to frame
        frame.add(username);
        frame.add(password);
        frame.add(login);

        // positioning

        // username - top left
        username.setBounds(10, 10, 100, 50);

        // password - top left + 50
        password.setBounds(10, 60, 100, 50);

        // login - top left + 100
        login.setBounds(10, 110, 100, 50);

        // set frame properties
        frame.setSize(500, 300);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    // event handler for button
    public void login() {
        // get username and password
        String user = username.getText();
        String pass = password.getText();
        // check if username and password are correct
        if (user.equals("admin") && pass.equals("admin")) {
            // if correct, show message
            JOptionPane.showMessageDialog(frame, "Login Successful!");
        } else {
            // if incorrect, show message
            JOptionPane.showMessageDialog(frame, "Login Failed!");
        }
    }

    // show frame
    public void show() {
        frame.setVisible(true);
    }

}
