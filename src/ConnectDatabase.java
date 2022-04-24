import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class ConnectDatabase {
    public Connection connectToDb() {
        Dotenv dotenv = Dotenv.load();
        Connection conn = null;
        try {
            // db parameters
            String url = dotenv.get("DB_URL");
            String user = dotenv.get("DB_USER");
            String password = dotenv.get("DB_PASS");

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
