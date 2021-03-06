import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

    // loading label
    private JLabel loading;

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

        loading = new JLabel("Loading...");
        loading.setSize(100, 50);
        // bounds - bottom
        loading.setBounds(300, 500, 100, 50);
        // hide
        loading.setVisible(false);
        frame.add(loading);

        // call method signUp when button is clicked
        signUp.addActionListener(e -> {
            loading.setVisible(true);
            signUp();
        });

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
        frame.setSize(800, 700);
        frame.setLayout(null);
    }

    // show the SIGN UP page
    public void show() {
        frame.setVisible(true);
    }

    // method to sign up
    public void signUp() {
        loading.setVisible(true);
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

                saveUser(user, pass);
                loading.setVisible(false);
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

    // save new user to the database
    public void saveUser(String username, String pass) {
        ConnectDatabase connectDatabase = new ConnectDatabase();
        Connection conn = connectDatabase.connectToDb();

        // insert into database
        try {
            System.out.println("Inserting records into the table...");

            String sql = "INSERT INTO users (username, password) VALUES ('" + username + "', '" + pass + "')";
            conn.createStatement().executeUpdate(sql);
            System.out.println("Inserted records into the table");
            // show success message
            JOptionPane.showMessageDialog(frame, "Sign up successful");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
