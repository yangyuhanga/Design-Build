package Test.dao;

import Test.vo.RobotData;

import java.util.List;

public interface RobotDao {

    RobotData getLatestRobotData();

    List<RobotData> getRobotMovementHistory();

    void insertRobotData(RobotData robotData);

}