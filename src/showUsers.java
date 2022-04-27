import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class showUsers {
    // frame
    private JFrame frame;

    // labels for username and grade
    private JLabel usernameLabel;
    private JLabel gradeLabel;

    /// label - Username
    private JLabel username;
    // label - Grade
    private JLabel grade;

    // buttons to show and hide specific users for a major
    private JButton showA;
    private JButton showB;
    private JButton showC;

    // textarea for showing users arraylist
    private JTextArea textArea;

    // label for no users
    private JLabel noUsers;

    private JLabel loading;

    // constructor
    public showUsers() {
        // create frame
        frame = new JFrame("Users");

        // label for no users
        noUsers = new JLabel("No users in this major");
        // set size
        noUsers.setSize(200, 50);
        // set location
        noUsers.setLocation(10, 60);
        // hide label
        noUsers.setVisible(false);

        // add label to frame
        frame.add(noUsers);

        // create labels
        usernameLabel = new JLabel("Username");
        gradeLabel = new JLabel("Major");

        // size
        usernameLabel.setSize(100, 50);
        gradeLabel.setSize(100, 50);

        // bounds
        usernameLabel.setBounds(100, 50, 100, 50);
        gradeLabel.setBounds(200, 50, 100, 50);

        // add labels to frame
        frame.add(usernameLabel);
        frame.add(gradeLabel);

        textArea = new JTextArea();
        // set size
        textArea.setSize(200, 200);
        // set location
        textArea.setBounds(100, 90, 200, 200);
        // hide text area
        textArea.setVisible(false);
        frame.add(textArea);

        // create table for each major - A, B, C, D, E, F --

        // add components to frame
        frame.add(usernameLabel);
        frame.add(gradeLabel);

        // buttons
        showA = new JButton("Show Computer Science");
        showB = new JButton("Show Business Studies");
        showC = new JButton("Show Global Challenges");

        // add buttons to frame
        frame.add(showA);
        frame.add(showB);
        frame.add(showC);

        loading = new JLabel("Loading...");
        loading.setSize(100, 50);
        // bounds - bottom
        loading.setBounds(300, 600, 100, 50);
        // hide
        loading.setVisible(false);
        frame.add(loading);

        // buttons - set bounds
        showA.setBounds(10, 10, 200, 30);
        showB.setBounds(220, 10, 200, 30);
        showC.setBounds(440, 10, 200, 30);

        // buttons - add action listeners - switchTable
        showA.addActionListener(e -> {
            System.out.println("Show Computer Science");
            ArrayList<String> data = getUsers();
            // add data[0] to textArea

            textArea.setText(data.get(0));
            // show text area
            textArea.setVisible(true);
            loading.setVisible(false);
        });
        showC.addActionListener(e -> {
            System.out.println("Show Business Studies");
            ArrayList<String> data = getUsers();
            // add data[1] to textArea

            textArea.setText(data.get(1));
            // show text area
            textArea.setVisible(true);
            loading.setVisible(false);
        });

        showB.addActionListener(e -> {
            System.out.println("Show Global Challenges");
            ArrayList<String> data = getUsers();
            // add data[2] to textArea

            textArea.setText(data.get(2));
            // show text area
            textArea.setVisible(true);
            loading.setVisible(false);

        });

        // set frame properties
        frame.setSize(800, 800);
        frame.setLayout(null);
    }

    // get users from the database and return in form of Object[][]
    public ArrayList<String> getUsers() {
        loading.setVisible(true);
        ConnectDatabase connectDatabase = new ConnectDatabase();
        Connection conn = connectDatabase.connectToDb();
        // get users from database
        try {
            String query = "SELECT * FROM users";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            // string to hold users with major = Computer Science
            String majorA = "";
            // string to hold users with major = Global Challenges
            String majorB = "";
            // string to hold users with major = Business Studies
            String majorC = "";

            // ArrayList to the 3 majors
            ArrayList<String> majors = new ArrayList<String>();

            // get data from database
            while (rs.next()) {
                // get username
                String username = rs.getString("username");
                // get grade
                String grade = rs.getString("major");

                if (grade != null) {
                    // check major and add to appropriate string
                    if (grade.equals("Computer Studies")) {
                        majorA += username + " " + grade + "\n";
                    } else if (grade.equals("Global Challenges")) {
                        majorB += username + " " + grade + "\n";
                    } else if (grade.equals("Business Studies")) {
                        majorC += username + " " + grade + "\n";
                    }
                }

            }
            System.out.println("Major CS " + majorA);
            System.out.println("Major GC " + majorB);
            System.out.println("Major IBT " + majorC);
            majors.add(majorA);
            majors.add(majorB);
            majors.add(majorC);

            // return data
            return majors;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        // return users in form of Object[][]
        return null;
    }

    // show
    public void show() {
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        showUsers showUsers = new showUsers();
        showUsers.show();
    }

}
