package testWeb.db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class car_number {
    public static void main(String[] args) {
        String fileName = "E:\\桌面\\新建 Text Document.txt"; // 替换为实际的txt文件路径
        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/java"; // 替换为您的数据库连接字符串
        String username = "root"; // 替换为数据库用户名
        String password = "root"; // 替换为数据库密码

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = br.readLine()) != null) {
                // 使用正则表达式提取speed、distance、direction和time数据
                Pattern pattern = Pattern.compile("speed: (\\d+) distance: (\\d+) direction: (\\d+) time: (\\d+) m (\\d+) s");
                Matcher matcher = pattern.matcher(line);

                if (matcher.find()) {
                    int speed = Integer.parseInt(matcher.group(1));
                    int distance = Integer.parseInt(matcher.group(2));
                    int direction = Integer.parseInt(matcher.group(3));
                    int minutes = Integer.parseInt(matcher.group(4));
                    int seconds = Integer.parseInt(matcher.group(5));

                    // 将提取的数据插入数据库中
                    String sql = "INSERT INTO tracking (speed, distance, direction, Recordid, time_seconds) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, speed);
                    preparedStatement.setInt(2, distance);
                    preparedStatement.setInt(3, direction);
                    preparedStatement.setInt(4, minutes);
                    preparedStatement.setInt(5, seconds);
                    preparedStatement.executeUpdate();
                }
            }

            br.close();
            connection.close();
            System.out.println("数据已成功插入数据库！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
