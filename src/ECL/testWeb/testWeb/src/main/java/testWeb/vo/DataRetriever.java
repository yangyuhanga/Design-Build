package testWeb.vo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataRetriever {
    public static List<Records> retrieveDataFromDatabase() {
        List<Records> recordsList = new ArrayList<>();

        // 在此执行数据库查询操作
        String jdbcUrl = "jdbc:mysql://localhost:3306/java";
        String username = "root";
        String password = "root";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String query = "SELECT * FROM record"; 
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int recordid = resultSet.getInt("recordid");
                int userid = resultSet.getInt("userid");
                int robotid = resultSet.getInt("robotid");
                String time = resultSet.getString("time");
                int speed = resultSet.getInt("speed");
                String imageUrl = resultSet.getString("image");
                int distance = resultSet.getInt("distance");
                int direction = resultSet.getInt("direction");

                Records record = new Records(recordid, userid, robotid, time, speed, imageUrl, distance, direction);
                recordsList.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recordsList;
    }
    
   
    }