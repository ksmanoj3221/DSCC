package practical4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ServerInfoDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/dscc4";
    private static final String USER = "root"; // Change according to your MySQL configuration
    private static final String PASSWORD = "kSmanoj@18"; // Change according to your MySQL configuration

    public ServerInfo getServerInfo() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        
        // Get current date, time, and day
        Calendar calendar = Calendar.getInstance();
        String day = new SimpleDateFormat("EEEE").format(calendar.getTime()); // Get the day of the week
        String time = new SimpleDateFormat("hh:mm a").format(calendar.getTime()); // Get current time
        String date = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()); // Get current date

        // Insert current day, time, and date into the database
        String insertQuery = "INSERT INTO server_info (day, time, date) VALUES (?, ?, ?)";
        PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
        insertStatement.setString(1, day);
        insertStatement.setString(2, time);
        insertStatement.setString(3, date);
        insertStatement.executeUpdate();
        
        // Now, retrieve the last inserted record
        String selectQuery = "SELECT day, time, date FROM server_info ORDER BY id DESC LIMIT 1";
        PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = selectStatement.executeQuery();
        ServerInfo serverInfo = null;
        
        if (resultSet.next()) {
            serverInfo = new ServerInfo(
                resultSet.getString("day"),
                resultSet.getString("time"),
                resultSet.getString("date")
            );
        }
        
        // Clean up
        resultSet.close();
        selectStatement.close();
        insertStatement.close();
        connection.close();
        
        return serverInfo;
    }
}
