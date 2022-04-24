import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class showUsers {
    // frame
    private JFrame frame;
    // sample users with username, grade
    private String[] users = { "user1", "user2", "user3", "user4", "user5" };
    private String[] grades = { "A", "B", "C", "D", "F" };

    // labels for username and grade
    private JLabel usernameLabel;
    private JLabel gradeLabel;

    // table
    private JTable table;

    // columns
    private String[] columnNames = { "Username", "Grade" };

    // rows
    private Object[][] data = getUsers();

    // constructor
    public showUsers() {
        // create frame
        frame = new JFrame("Users");
        // create labels
        usernameLabel = new JLabel("Username");
        gradeLabel = new JLabel("Grade");
        // create table
        table = new JTable(data, columnNames);
        // create scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        // add components to frame
        frame.add(usernameLabel);
        frame.add(gradeLabel);
        frame.add(scrollPane);

        // table - columns to match column names
        scrollPane.setBounds(10, 60, 400, 200);

        // set frame properties
        frame.setSize(500, 300);
        frame.setLayout(null);
    }

    // get users from the database and return in form of Object[][]
    public Object[][] getUsers() {
        ConnectDatabase connectDatabase = new ConnectDatabase();
        Connection conn = connectDatabase.connectToDb();
        // get users from database
        try {
            Statement stmt = null;

            String sql = "SELECT * FROM users";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            // new object of undefined length
            Object[][] data = new Object[rs.getFetchSize()][2];
            System.out.println("Size " + rs.getFetchSize());

            // get data from database
            while (rs.next()) {
                // get username
                String username = rs.getString("username");
                // get grade
                String grade = rs.getString("major");
                // add username and grade to data
                data[rs.getRow()][0] = username;
                data[rs.getRow()][1] = grade;

            }
            // return data
            return data;

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
        return data;
    }

    // show
    public void show() {
        frame.setVisible(true);
    }

}
