
package testWeb.vo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class DataProcessor {
	 private List<Records> dataList = new ArrayList<>();
	 public List<Records> getdataList(){
		 return dataList;
		 
	 }
	 
	private void clearDatabase(Connection connection) {
	    try {
	        String deleteQuery = "DELETE FROM record"; // 清除record表中的所有数据
	        PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
	        deleteStatement.executeUpdate();
	        
	        // 重置自增计数器
	        try {
	            String resetAutoIncrementQuery = "ALTER TABLE record AUTO_INCREMENT = 1";
	            PreparedStatement resetStatement = connection.prepareStatement(resetAutoIncrementQuery);
	            resetStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // 处理重置自增计数器失败的情况
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // 处理清除数据失败的情况
	    }
	}

	
    public void processData() {
        // 定义文件路径
        String filePath = "E:\\桌面\\小车程序\\视频传输模块\\PC端\\pythonProject\\1.txt"; // 替换为实际文件路径

        // 建立数据库连接
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "root");
            clearDatabase(connection);
        
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        // 使用try-with-resources来读取文件并处理数据
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int speed = 0; // 初始化速度
            String time = null; // 初始化时间
            int distance = 0; // 初始化距离
       
            

            while ((line = reader.readLine()) != null) {
                // 如果行中包含 "SPEED"，则进行处理
                if (line.contains("SPEED")) {
                    // 解析速度、时间和距离的值
                    speed = parseSpeed(line);
                    time = parseTime(line);
                    distance = parseDistance(line);

                    // 进行方向转换
                    int direction = 0;
                    if (line.contains("go ahead")) {
                        direction = 1;
                    } else if (line.contains("turn_left")) {
                        direction = 2;
                    } else if (line.contains("turn_right")) {
                        direction = 3;
                    } else if (line.contains("go back")) {
                        direction = 4;
                    }
                 // 创建 Records 对象并设置属性值
                    Records record = new Records(0,0,0,time,speed,null,distance,direction);
                    record.setSpeed(speed);
                    record.setTime(time);
                    record.setDistance(distance);
                    record.setDirection(direction);

                    // 将数据添加到 dataList
                    dataList.add(record);

                    // 将数据插入数据库
                    try {
                        String insertQuery = "INSERT INTO record (speed, direction, time, distance) VALUES (?, ?, ?, ?)";
                        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                        preparedStatement.setInt(1, speed);
                        preparedStatement.setInt(2, direction);
                        preparedStatement.setString(3, time);
                        preparedStatement.setInt(4, distance);
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 关闭数据库连接
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  
    
    
    
    // 解析速度值
    private static int parseSpeed(String line) {
        // 使用正则表达式匹配速度值
        Pattern pattern = Pattern.compile("SPEED: (\\d+)");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            String speedStr = matcher.group(1);
            int speed = Integer.parseInt(speedStr);
            return speed;
        } else {
            // 如果没有匹配到速度值，可以返回一个默认值或者抛出异常
            throw new IllegalArgumentException("No SPEED value found in the line: " + line);
        }
    }

    // 解析时间值
    private static String parseTime(String line) {
        // 使用正则表达式匹配时间值
        Pattern pattern = Pattern.compile("TIME: (\\d+ m \\d+ s)");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            String timeStr = matcher.group(1);
            return timeStr;
        } else {
            // 如果没有匹配到时间值，可以返回一个默认值或者抛出异常
            throw new IllegalArgumentException("No TIME value found in the line: " + line);
        }
    }

    // 解析距离值
    private static int parseDistance(String line) {
        // 使用正则表达式匹配距离值
        Pattern pattern = Pattern.compile("DISTANCE: (\\d+)");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            String distanceStr = matcher.group(1);
            int distance = Integer.parseInt(distanceStr);
            return distance;
        } else {
            // 如果没有匹配到距离值，可以返回一个默认值或者抛出异常
            throw new IllegalArgumentException("No DISTANCE value found in the line: " + line);
        }
    }
}

