import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

// About this class
/*
This is login screen. Has username and password fields.
When user clicks login button, it checks if the username and password are correct.
then shows register screen
*/

// create a login frame with 2 input fields and 1 button using swing
public class LoginPage {
    // frame
    private JFrame frame;
    // text fields
    private JTextField username;
    private JPasswordField password;
    // button
    private JButton login;
    private JLabel loading;

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
        login.addActionListener(e -> {
            try {
                login();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        // add components to frame
        frame.add(username);
        frame.add(password);
        frame.add(login);

        loading = new JLabel("Loading...");
        loading.setSize(100, 50);
        // bounds - bottom
        loading.setBounds(300, 500, 100, 50);
        // hide
        loading.setVisible(false);
        frame.add(loading);

        // username - top left
        username.setBounds(10, 10, 100, 50);

        // password - top left + 50
        password.setBounds(10, 60, 100, 50);

        // login - top left + 100
        login.setBounds(10, 110, 100, 50);

        // set frame properties
        frame.setSize(800, 700);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    // event handler for button
    public void login() throws SQLException {
        loading.setVisible(true);
        // get username and password
        String user = username.getText();
        String pass = password.getText();
        // check if username and password are correct

        if (loginProcess(user, pass)) {
            // if correct, show message
            JOptionPane.showMessageDialog(frame, "Login Successful!");
            Boolean existsInDb = checkRegistrationStatus(user);

            // close current frame and open registration page if user is not in database
            // otherwise show the showUsers screen
            if (!existsInDb) {
                loading.setVisible(false);
                frame.setVisible(false);
                Register registerPage = new Register(user);
                registerPage.show();
            } else {
                frame.setVisible(false);
                showUsers showUsers = new showUsers();
                showUsers.show();
            }
        } else {
            // if incorrect, show message
            JOptionPane.showMessageDialog(frame, "Login Failed!");
        }
    }

    // check if username exists in database
    public boolean loginProcess(String user, String pass) {
        ConnectDatabase connectDatabase = new ConnectDatabase();
        Connection conn = connectDatabase.connectToDb();

        // check if username exists in database
        // if exists and password is correct, return true
        // if not, return false

        String sql = "SELECT * FROM users WHERE username = '" + user + "'";
        try {
            // create statement
            Statement stmt = conn.createStatement();
            // execute query
            ResultSet rs = stmt.executeQuery(sql);
            // check if username exists
            if (rs.next()) {
                // check if password is correct
                if (rs.getString("password").equals(pass)) {
                    // return major column
                    return true;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;

    }

    // check if logged in user is registered in database
    public Boolean checkRegistrationStatus(String rs) throws SQLException {
        ConnectDatabase connectDatabase = new ConnectDatabase();
        Connection conn = connectDatabase.connectToDb();

        // check if column major is null
        // if null, user is not registered
        // if not null, user is registered

        String sql = "SELECT major FROM users WHERE username = '" + rs + "'";
        try {
            // create statement
            Statement stmt = conn.createStatement();
            // execute query
            ResultSet rs2 = stmt.executeQuery(sql);
            // check if major is null
            if (rs2.next()) {
                if (rs2.getString("major") == null) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    // show frame
    public void show() {
        frame.setVisible(true);
    }

}
