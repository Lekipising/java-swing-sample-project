import javax.swing.JButton;
import javax.swing.JFrame;

public class Home {
    // frame
    private JFrame frame;

    // signup and login buttons
    private JButton signup;
    private JButton login;

    // constructor
    public Home() {
        // create frame
        frame = new JFrame("Home");

        // create buttons
        signup = new JButton("Sign Up");
        // size
        signup.setSize(100, 50);
        // position
        signup.setBounds(10, 10, 100, 50);
        login = new JButton("Login");
        // size
        login.setSize(100, 50);
        // position
        login.setBounds(10, 60, 100, 50);

        // call method signUp when button is clicked
        signup.addActionListener(e -> signUp());

        // call method login when button is clicked
        login.addActionListener(e -> login());

        // add components to frame
        frame.add(signup);
        frame.add(login);

        // positioning

        // signup - top left
        signup.setBounds(10, 10, 100, 50);

        // login - top left + 50
        login.setBounds(10, 60, 100, 50);

        // set frame properties
        frame.setSize(500, 300);
        frame.setLayout(null);
    }

    // show the frame
    public void show() {
        frame.setVisible(true);
    }

    // sign up - closes the current frame and opens the sign up page
    public void signUp() {
        frame.setVisible(false);
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.show();
    }

    // login - closes the current frame and opens the login page
    public void login() {
        frame.setVisible(false);
        LoginPage loginPage = new LoginPage();
        loginPage.show();
    }

}
