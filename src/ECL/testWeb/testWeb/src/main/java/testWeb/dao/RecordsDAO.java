package testWeb.dao;
import java.util.List;

import testWeb.vo.Records;
import testWeb.vo.RobotInfo;
import testWeb.vo.UserInfo;

public interface RecordsDAO {
	public List<Records> queryByRecord (UserInfo userinfo, RobotInfo robotinfo) throws Exception;	
	public List<Records> requeryByRecord(Records records) throws Exception;//添加后再查询
	public void addRecord (Records records) throws Exception;
	public void deleteRecord (UserInfo userinfo) throws Exception;
	
}
