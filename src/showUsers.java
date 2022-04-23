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
    private Object[][] data = { { "user1", "A" }, { "user2", "B" }, { "user3", "C" }, { "user4", "D" },
            { "user5", "F" } };

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

    // show
    public void show() {
        frame.setVisible(true);
    }

}
