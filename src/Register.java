import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

// about this class
/*
This screen for registering users. We ask for grade and submit.
Logic will check for level of grade and assign appropriate level. Modify to match your needs.
*/

public class Register {
    // frame
    private JFrame frame;
    // text fields - grade
    private JTextField grade;
    // button
    private JButton register;

    private JLabel loading;

    // constructor
    public Register(String username) {
        // create frame
        frame = new JFrame("Register");
        // create text fields
        grade = new JTextField(10);
        loading = new JLabel("Loading...");
        loading.setSize(100, 50);
        // bounds - bottom
        loading.setBounds(300, 600, 100, 50);
        // hide
        loading.setVisible(false);
        frame.add(loading);
        // size
        grade.setSize(100, 50);
        // placeholder
        grade.setText("Major");
        // create button
        register = new JButton("Register");
        register.setSize(100, 50);

        // call method register when button is clicked
        register.addActionListener(e -> {
            try {
                register(username);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        // add components to frame
        frame.add(grade);
        frame.add(register);

        // positioning

        // grade - top left
        grade.setBounds(10, 10, 100, 50);

        // register - top left + 50
        register.setBounds(10, 60, 100, 50);

        // set frame properties
        frame.setSize(800, 800);
        frame.setLayout(null);
    }

    // show
    public void show() {
        frame.setVisible(true);
    }

    // register - grade is below 10 set to "F", between 10 and 20 set to "D",
    // between 20 and 30 set to "C", between 30 and 40 set to "B", between 40 and 50
    // set to "A" - show pop up with grade and print
    private void register(String username) throws SQLException {
        // get grade
        String gradeString = grade.getText();
        // convert to int
        int gradeInt = Integer.parseInt(gradeString);
        // TODO: ADD ACTUAL major - eg 16 - 20 is CS, etc
        // if grade is below 10
        if (gradeInt < 11) {
            // show pop saying "Not allowed"
            JOptionPane.showMessageDialog(frame, "Not admitted");
        }
        // if grade is between 10 and 20
        else if (gradeInt >= 12 && gradeInt <= 14) {
            // set grade to "D"
            gradeString = "Business Studies";
        }
        // if grade is between 20 and 30
        else if (gradeInt >= 15 && gradeInt <= 17) {
            // set grade to "C"
            gradeString = "Global Challenges";
        }
        // if grade is between 30 and 40
        else if (gradeInt >= 18 && gradeInt <= 20) {
            // set grade to "B"
            gradeString = "Computer Studies";
        }
        // if grade is between 40 and 50
        else if (gradeInt >= 21) {
            // show pop saying "Not allowed"
            JOptionPane.showMessageDialog(frame, "Max is 20");
        }
        updateMajor(gradeString, username);
        // create pop up
        JFrame popUp = new JFrame("Major");
        // create label
        JLabel label = new JLabel("Major: " + gradeString);

        // ok button
        JButton ok = new JButton("OK");
        // size
        ok.setSize(100, 50);
        // position - bottom
        ok.setBounds(10, 110, 100, 50);
        // on click OK open usersShow screen
        ok.addActionListener(e -> {
            // close pop up
            popUp.dispose();
            // close register screen
            frame.dispose();
            // create usersShow screen
            showUsers usersShow = new showUsers();
            // show usersShow screen
            usersShow.show();
        });
        // size
        label.setSize(100, 50);
        // position
        label.setBounds(10, 10, 100, 50);
        // add label to pop up
        popUp.add(label);
        // add ok button to pop up
        popUp.add(ok);
        // set pop up properties
        popUp.setSize(300, 200);
        popUp.setLayout(null);
        popUp.setVisible(true);
    }

    // method to update major column in database
    public void updateMajor(String major, String username) throws SQLException {
        loading.setVisible(true);
        // create connection
        ConnectDatabase connectDatabase = new ConnectDatabase();
        Connection conn = connectDatabase.connectToDb();
        // create statement
        java.sql.Statement stmt = null;
        try {
            // create statement
            stmt = conn.createStatement();
            String rollNum = generateRollNum();
            // update major and rollNum columns in database
            String sql = "UPDATE users SET major = '" + major + "', rollNum = '" + rollNum + "' WHERE username = '"
                    + username + "'";
            // execute query
            stmt.executeUpdate(sql);
            loading.setVisible(false);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // generate rollNum - format - 2022/01, 2022/02, etc - 01, 02, are integers to
    // be incremented
    public String generateRollNum() {
        // check last rollNum
        ConnectDatabase connectDatabase = new ConnectDatabase();
        Connection conn = connectDatabase.connectToDb();

        // get all rollNums
        java.sql.Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT rollNum FROM users";
            java.sql.ResultSet rs = stmt.executeQuery(sql);
            // get last rollNum
            String lastRollNum = "";
            while (rs.next()) {

                lastRollNum = rs.getString("rollNum");
            }
            if (lastRollNum == null) {
                lastRollNum = "2022/01";
                return lastRollNum;
            }
            // get last rollNum year
            String lastRollNumYear = lastRollNum.substring(0, 4);
            // get last rollNum month
            String lastRollNumMonth = lastRollNum.substring(5, 7);
            // convert last rollNum month to int
            int lastRollNumMonthInt = Integer.parseInt(lastRollNumMonth);
            // increment last rollNum month
            lastRollNumMonthInt++;
            // return last rollNum year and last rollNum month
            return lastRollNumYear + "/" + lastRollNumMonthInt;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

}
