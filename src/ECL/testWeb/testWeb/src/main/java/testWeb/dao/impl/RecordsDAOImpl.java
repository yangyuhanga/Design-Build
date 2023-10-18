package testWeb.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import testWeb.dao.RecordsDAO;
import testWeb.db.DBConnect;
import testWeb.vo.Records;
import testWeb.vo.RobotInfo;
import testWeb.vo.UserInfo;


public class RecordsDAOImpl implements RecordsDAO {

	@Override
	public List<Records> queryByRecord(UserInfo userinfo, RobotInfo robotinfo) throws Exception {
		// TODO Auto-generated method stub
		List<Records> list = new ArrayList<>();//把数据库读出的数据存在ArrayList中
		//UserInfo user = new UserInfo();
		//RobotInfo robot = new RobotInfo();
		
		String sql = "select * from record r,userinfo u where r.userid=u.userinfoid and username=?";
		PreparedStatement pstmt = null ;
        DBConnect dbc = null;
  
        // 下面是针对数据库的具体操作   
        try{   
            // 连接数据库   
            dbc = new DBConnect() ;   
            pstmt = dbc.getConnection().prepareStatement(sql); 
            pstmt.setString(1,userinfo.getUsername()); 
            ResultSet rs = pstmt.executeQuery();
  
            while(rs.next()){ 
            	Records con = new Records(); 
            	int recordid = rs.getInt("recordid");
            	int userid = rs.getInt("userid");
            	int robotid = rs.getInt("robotid");
            	String time = rs.getString("time");
            	int speed = rs.getInt("speed");
                String imageUrl = rs.getString("image");
                int distance = rs.getInt("distance");
                int direction = rs.getInt("direction");
                con = new Records(recordid,userid,robotid,time,speed,imageUrl,distance,direction);
                list.add(con);
           
            } 
            
            rs.close() ; 
            pstmt.close() ;
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{   
            // 关闭数据库连接   
            dbc.close() ;   
        }   
		return list;
	}
	
	@Override
	public List<Records> requeryByRecord(Records records) throws Exception {
		// TODO Auto-generated method stub
		List<Records> list = new ArrayList<>();//把数据库读出的数据存在ArrayList中
		//UserInfo user = new UserInfo();
		//RobotInfo robot = new RobotInfo();
		
		String sql = "select * from record where userid=?";
		PreparedStatement pstmt = null ;
        DBConnect dbc = null;
  
        // 下面是针对数据库的具体操作   
        try{   
            // 连接数据库   
            dbc = new DBConnect() ;   
            pstmt = dbc.getConnection().prepareStatement(sql); 
            pstmt.setInt(1,records.getUserid()); 
            ResultSet rs = pstmt.executeQuery();
  
            while(rs.next()){ 
            	Records con = new Records(); 
            	int recordid = rs.getInt("recordid");
            	int userid = rs.getInt("userid");
            	int robotid = rs.getInt("robotid");
            	String time = rs.getString("time");
            	int speed = rs.getInt("speed");
                String imageUrl = rs.getString("image");
                int distance = rs.getInt("distance");
                int direction = rs.getInt("direction");
                con = new Records(recordid,userid,robotid,time,speed,imageUrl,distance,direction);
                list.add(con);
           
            } 
            
            rs.close() ; 
            pstmt.close() ;
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{   
            // 关闭数据库连接   
            dbc.close() ;   
        }   
		return list;
	}
	
	
	@Override
	public void addRecord(Records records) throws Exception{
	
        String sql = "insert into record(userid,robotid,time,speed,distance,direction) values (?,?,?,?,?,?)";
        PreparedStatement pstmt = null ;
        DBConnect dbc = null;
        
        try{      
            dbc = new DBConnect() ;   
            pstmt = dbc.getConnection().prepareStatement(sql); 
            
            pstmt.setInt(1, records.getUserid());
            pstmt.setInt(2, records.getRobotid());
            pstmt.setString(3, records.getTime());
            pstmt.setInt(4, records.getSpeed());
            pstmt.setInt(5, records.getDistance());
            pstmt.setInt(6, records.getDirection());
            

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // 关闭数据库连接
            if (pstmt != null) {
                pstmt.close();
            }
            dbc.close();  
        }   
        
	}
	
	@Override
	public void deleteRecord(UserInfo userinfo) throws Exception {
	    // 第一步：根据用户名查找与之关联的userid
	    String findUserIdSql = "SELECT userinfoid FROM userinfo WHERE username = ?";
	    PreparedStatement findUserIdStmt = null;
	    int userId = -1;

	    DBConnect dbc = null;

	    try {
	        dbc = new DBConnect();
	        findUserIdStmt = dbc.getConnection().prepareStatement(findUserIdSql);
	        findUserIdStmt.setString(1, userinfo.getUsername());

	        ResultSet rs = findUserIdStmt.executeQuery();

	        if (rs.next()) {
	            userId = rs.getInt("userinfoid");
	        }

	        rs.close();
	        findUserIdStmt.close();
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }

	    // 第二步：使用找到的userid删除相关记录
	    if (userId != -1) {
	        String deleteRecordSql = "DELETE FROM record WHERE userid = ?";
	        PreparedStatement deleteRecordStmt = null;

	        try {
	            deleteRecordStmt = dbc.getConnection().prepareStatement(deleteRecordSql);
	            deleteRecordStmt.setInt(1, userId);
	            deleteRecordStmt.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        } finally {
	            if (deleteRecordStmt != null) {
	                deleteRecordStmt.close();
	            }
	        }
	    }

	    // 关闭数据库连接
	    dbc.close();
	}

	public List<Records> getAllRecords() throws Exception {
    List<Records> list = new ArrayList<>();
    String sql = "SELECT * FROM record"; // Change this query to match your database schema
    PreparedStatement pstmt = null;
    DBConnect dbc = null;

    try {
        dbc = new DBConnect();
        pstmt = dbc.getConnection().prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Records con = new Records();
            int recordid = rs.getInt("recordid");
            int userid = rs.getInt("userid");
            int robotid = rs.getInt("robotid");
            String time = rs.getString("time");
            int speed = rs.getInt("speed");
            String imageUrl = rs.getString("image");
            int distance = rs.getInt("distance");
            int direction = rs.getInt("direction");
            con = new Records(recordid, userid, robotid, time, speed, imageUrl, distance, direction);
            list.add(con);
        }

        rs.close();
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    } finally {
        if (pstmt != null) {
            pstmt.close();
        }
        dbc.close();
    }

    return list;
}
}