import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class showUsers {
    // frame
    private JFrame frame;

    // labels for username and grade
    private JLabel usernameLabel;
    private JLabel gradeLabel;

    // table
    private JTable tableA;
    private JTable tableB;
    private JTable tableC;
    private JTable tableD;
    private JTable tableE;
    private JTable tableF;

    // columns
    private String[] columnNames = { "Username", "Major" };

    // rows
    private Object[][] data = getUsers();

    // buttons to show and hide specific users for a major
    private JButton showA;
    private JButton showB;
    private JButton showC;
    private JButton showD;
    private JButton showE;
    private JButton showF;

    // variable to hold the active major default to be A
    private String activeMajor = "A";

    // label for no users
    private JLabel noUsers;

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
        // create table for each major - A, B, C, D, E, F --
        // TODO: ADD ACTUAL MAJORS
        Object[][] tableAUsers = getMajorUsers("A", data);
        Object[][] tableBUsers = getMajorUsers("B", data);
        Object[][] tableCUsers = getMajorUsers("C", data);
        Object[][] tableDUsers = getMajorUsers("D", data);
        Object[][] tableEUsers = getMajorUsers("E", data);
        Object[][] tableFUsers = getMajorUsers("F", data);

        tableA = new JTable(tableAUsers, columnNames);
        tableB = new JTable(tableBUsers, columnNames);
        tableC = new JTable(tableCUsers, columnNames);
        tableD = new JTable(tableDUsers, columnNames);
        tableE = new JTable(tableEUsers, columnNames);
        tableF = new JTable(tableFUsers, columnNames);
        // create scroll pane
        JScrollPane scrollPaneA = new JScrollPane(tableA);
        JScrollPane scrollPaneB = new JScrollPane(tableB);
        JScrollPane scrollPaneC = new JScrollPane(tableC);
        JScrollPane scrollPaneD = new JScrollPane(tableD);
        JScrollPane scrollPaneE = new JScrollPane(tableE);
        JScrollPane scrollPaneF = new JScrollPane(tableF);
        // add components to frame
        frame.add(usernameLabel);
        frame.add(gradeLabel);
        frame.add(scrollPaneA);
        frame.add(scrollPaneB);
        frame.add(scrollPaneC);
        frame.add(scrollPaneD);
        frame.add(scrollPaneE);
        frame.add(scrollPaneF);

        // table - columns to match column names
        scrollPaneA.setBounds(10, 60, 400, 200);
        scrollPaneB.setBounds(10, 60, 400, 200);
        scrollPaneC.setBounds(10, 60, 400, 200);
        scrollPaneD.setBounds(10, 60, 400, 200);
        scrollPaneE.setBounds(10, 60, 400, 200);
        scrollPaneF.setBounds(10, 60, 400, 200);

        // hide scroll pane
        scrollPaneA.setVisible(false);
        scrollPaneB.setVisible(false);
        scrollPaneC.setVisible(false);
        scrollPaneD.setVisible(false);
        scrollPaneE.setVisible(false);
        scrollPaneF.setVisible(false);

        // buttons
        showA = new JButton("Show A");
        showB = new JButton("Show B");
        showC = new JButton("Show C");
        showD = new JButton("Show D");
        showE = new JButton("Show E");
        showF = new JButton("Show F");

        // add buttons to frame
        frame.add(showA);
        frame.add(showB);
        frame.add(showC);
        frame.add(showD);
        frame.add(showE);
        frame.add(showF);

        // buttons - set bounds
        showA.setBounds(10, 10, 100, 30);
        showB.setBounds(120, 10, 100, 30);
        showC.setBounds(230, 10, 100, 30);
        showD.setBounds(340, 10, 100, 30);
        showE.setBounds(450, 10, 100, 30);
        showF.setBounds(560, 10, 100, 30);

        // JScrollPane list
        JScrollPane[] scrollPanes = { scrollPaneA, scrollPaneB, scrollPaneC, scrollPaneD, scrollPaneE, scrollPaneF };

        // buttons - add action listeners - switchTable
        showA.addActionListener(e -> {
            switchTable("A", tableAUsers, scrollPaneA, scrollPanes);
        });
        showB.addActionListener(e -> {
            switchTable("B", tableBUsers, scrollPaneB, scrollPanes);
        });
        showC.addActionListener(e -> {
            switchTable("C", tableCUsers, scrollPaneC, scrollPanes);
        });
        showD.addActionListener(e -> {
            switchTable("D", tableDUsers, scrollPaneD, scrollPanes);
        });
        showE.addActionListener(e -> {
            switchTable("E", tableEUsers, scrollPaneE, scrollPanes);
        });
        showF.addActionListener(e -> {
            switchTable("F", tableFUsers, scrollPaneF, scrollPanes);
        });

        tableA.setVisible(true);
        tableB.setVisible(false);
        tableC.setVisible(false);
        tableD.setVisible(false);
        tableE.setVisible(false);
        tableF.setVisible(false);

        // set frame properties
        frame.setSize(500, 300);
        frame.setLayout(null);
    }

    // hide all tables
    private void hideAllTables() {
        tableA.setVisible(false);
        tableB.setVisible(false);
        tableC.setVisible(false);
        tableD.setVisible(false);
        tableE.setVisible(false);
        tableF.setVisible(false);

    }

    // hide all panes
    private void hideAllPanes(JScrollPane[] scrollPane) {
        // loop and hide all panes
        for (int i = 0; i < scrollPane.length; i++) {
            scrollPane[i].setVisible(false);
        }
    }

    // switchTable - switch the active major
    public void switchTable(String major, Object[][] users, JScrollPane pane, JScrollPane[] scrollPane) {
        hideAllPanes(scrollPane);
        Boolean isEmpty = true;
        // check if there are users in the major
        for (int i = 0; i < users.length; i++) {
            if (users[i][0] != null) {
                isEmpty = false;
                break;
            }
        }
        // switch the active major
        activeMajor = major;
        // switch the table
        switch (major) {
            case "A":
                if (isEmpty) {
                    hideAllTables();
                    noUsers.setVisible(true);
                } else {
                    pane.setVisible(true);
                    noUsers.setVisible(false);
                    tableA.setVisible(true);
                    tableB.setVisible(false);
                    tableC.setVisible(false);
                    tableD.setVisible(false);
                    tableE.setVisible(false);
                    tableF.setVisible(false);
                }

                break;
            case "B":
                if (isEmpty) {
                    hideAllTables();
                    noUsers.setVisible(true);
                } else {
                    pane.setVisible(true);
                    noUsers.setVisible(false);
                    tableA.setVisible(false);
                    tableB.setVisible(true);
                    tableC.setVisible(false);
                    tableD.setVisible(false);
                    tableE.setVisible(false);
                    tableF.setVisible(false);
                }
                break;
            case "C":
                if (isEmpty) {
                    hideAllTables();
                    noUsers.setVisible(true);
                } else {
                    pane.setVisible(true);
                    noUsers.setVisible(false);
                    tableA.setVisible(false);
                    tableB.setVisible(false);
                    tableC.setVisible(true);
                    tableD.setVisible(false);
                    tableE.setVisible(false);
                    tableF.setVisible(false);
                }
                break;
            case "D":
                if (isEmpty) {
                    hideAllTables();
                    noUsers.setVisible(true);
                } else {
                    pane.setVisible(true);
                    noUsers.setVisible(false);
                    tableA.setVisible(false);
                    tableB.setVisible(false);
                    tableC.setVisible(false);
                    tableD.setVisible(true);
                    tableE.setVisible(false);
                    tableF.setVisible(false);
                }
                break;
            case "E":
                if (isEmpty) {
                    hideAllTables();
                    noUsers.setVisible(true);
                } else {
                    pane.setVisible(true);
                    noUsers.setVisible(false);
                    tableA.setVisible(false);
                    tableB.setVisible(false);
                    tableC.setVisible(false);
                    tableD.setVisible(false);
                    tableE.setVisible(true);
                    tableF.setVisible(false);
                }
                break;
            case "F":
                if (isEmpty) {
                    hideAllTables();
                    noUsers.setVisible(true);
                } else {
                    pane.setVisible(true);
                    noUsers.setVisible(false);
                    tableA.setVisible(false);
                    tableB.setVisible(false);
                    tableC.setVisible(false);
                    tableD.setVisible(false);
                    tableE.setVisible(false);
                    tableF.setVisible(true);
                }
                break;
        }
    }

    // get users from specific majors
    public Object[][] getMajorUsers(String major, Object[][] allUsers) {
        Object[][] majorUsers = new Object[allUsers.length][2];
        int count = 0;
        for (int i = 0; i < allUsers.length; i++) {
            if (allUsers[i][0] == null) {
                break;
            }
            if (allUsers[i][1].equals(major)) {
                majorUsers[count][0] = allUsers[i][0];
                majorUsers[count][1] = allUsers[i][1];
                count++;
            }
        }
        return majorUsers;
    }

    // get users from the database and return in form of Object[][]
    public Object[][] getUsers() {
        ConnectDatabase connectDatabase = new ConnectDatabase();
        Connection conn = connectDatabase.connectToDb();
        // get users from database
        try {

            String query = "SELECT * FROM users";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            // new object of undefined length
            Object[][] data = new Object[10][2];

            // get data from database
            while (rs.next()) {
                // get username
                String username = rs.getString("username");
                // get grade
                String grade = rs.getString("major");
                System.out.println(username + " " + grade);
                // add username and grade to data
                data[rs.getRow() - 1][0] = username;
                data[rs.getRow() - 1][1] = grade;

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
