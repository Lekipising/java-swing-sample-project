import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {
    public Connection connectToDb() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:mysql://us-cdbr-east-05.cleardb.net:3306/heroku_a2e20695e59bc79";
            String user = "bec06936de5fb6";
            String password = "2e12d093";

            System.out.println("Connecting to database...");

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);

            System.out.println("Connected to database");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
