package Test.dao.impl;

import Test.dao.RobotDao;
import Test.db.Dbconnect;
import Test.vo.RobotData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RobotdaoImpl implements RobotDao {

    @Override
    public List<RobotData> getRobotMovementHistory() {
        List<RobotData> movementHistory = new ArrayList<>();
        try {
            // 连接数据库
            Connection connection = Dbconnect.getConnection();

            // 查询机器人的运动历史数据
            String query = "SELECT speed, distance, direction, time FROM record ORDER BY time ASC";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                double speed = resultSet.getDouble("speed");
                double distance = resultSet.getDouble("distance");
                String direction = resultSet.getString("direction");
                String time = resultSet.getString("time");

                RobotData robotData = new RobotData(speed, distance, direction, time);
                movementHistory.add(robotData);
            }

            // 关闭数据库连接
            resultSet.close();
            preparedStatement.close();
            Dbconnect.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movementHistory;
    }

	@Override
	public RobotData getLatestRobotData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertRobotData(RobotData robotData) {
		// TODO Auto-generated method stub
		
	}
}