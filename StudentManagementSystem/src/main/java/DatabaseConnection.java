import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnection {
    public static Connection getConnection() {
        try{
            String url = "jdbc:postgresql://localhost:5432/Assignment3";
            String username = "postgres";
            String password = "newpassword123";

            Connection connection = DriverManager.getConnection(url, username, password);
            //System.out.println("Connected to database successfully!");
            return connection;

        } catch (Exception e) {
            //System.out.println("Failed to connect: " + e.getMessage());
            return null;
        }
    }


    // Simple test
    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("Connection test successful!");
        } else {
            System.out.println("Connection test failed!");
        }
    }


}

