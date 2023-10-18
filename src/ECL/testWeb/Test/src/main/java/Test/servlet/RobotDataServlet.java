package Test.servlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RobotDataServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DBURL = "jdbc:mysql://127.0.0.1:3306/javawebdb";
    private final String DBUSER = "root";
    private final String DBPASSWORD = "root";
    private Connection conn = null;


    @Override
    public void init() throws ServletException {
        try {
            // 注册数据库驱动
            Class.forName(DBDRIVER);

            // 建立数据库连接
            conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ServletException("Failed to initialize database connection.", e);
        }
    }


    @Override
    public void destroy() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // 查询最新的机器人数据
            String query = "SELECT speed, distance, direction, time FROM record ORDER BY time DESC LIMIT 1";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // 将数据以 JSON 格式返回给前端
            if (resultSet.next()) {
                double speed = resultSet.getDouble("speed");
                double distance = resultSet.getDouble("distance");
                String direction = resultSet.getString("direction");
                String time = resultSet.getString("time");

                String jsonData = "{\"speed\":" + speed + ", \"distance\":\"" + distance + "\", \"direction\":\"" + direction + "\", \"time\":\"" + time + "\"}";

                response.setContentType("application/json");
                response.getWriter().write(jsonData);
            }

            // 关闭数据库连接
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}