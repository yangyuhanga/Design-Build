package testWeb.dao.impl;


import java.sql.*;
import java.sql.SQLException;




import testWeb.dao.UserDAO;
import testWeb.db.DBConnect;
import testWeb.vo.UserInfo;
public class UserDAOImpl implements UserDAO {

	public int insertByUserInfo(UserInfo userinfo) throws Exception{
		 DBConnect dbc = null;
		 //PreparedStatement statement = null;
	
		try {
			String sql ="INSERT INTO userinfo(userinfoid,username,gender,password,email) VALUES(?,?,?,?,?)";
			//连接数据库
			dbc = new DBConnect();
			
			//HttpServletResponse servletResponse = null;
			//HttpServletResponse response = (HttpServletResponse) servletResponse;

            PreparedStatement pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, userinfo.getUserinfoid());
			
			pstmt.setString(2, userinfo.getUsername());
			pstmt.setString(3, userinfo.getGender());
			pstmt.setString(4, userinfo.getPassword());
			pstmt.setString(5, userinfo.getEmail());
			pstmt.setInt(6, userinfo.getRobotid());
			 
			pstmt.executeUpdate();
			pstmt.close();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			//关闭数据库链接
			dbc.close();
		}
		return 0;
	}
	
	public int repeatByUserInfo(UserInfo userinfo) throws Exception{
		DBConnect dbc = null;
		 //PreparedStatement statement = null;
		 
		int flag =1;
		try {
			String sql="SELECT * FROM userinfo WHERE username =?";
			
            dbc = new DBConnect();
			//HttpServletResponse servletResponse = null;
			PreparedStatement pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, userinfo.getUsername());
			//pstmt.setString(2, userinfo.getUserinfoid());
			ResultSet rs = pstmt.executeQuery();
			//check 
			while (rs.next()) {
				if (rs.getString("username").equals(userinfo.getUsername())) {
				    flag = 0;
				}

			rs.close() ; 
            pstmt.close();
            }
		}
            catch (SQLException e){   
                System.out.println(e.getMessage());   
            }finally{   
                // 关闭数据库连接   
                dbc.close() ;   
            }   
		return flag;
		}

	
	public int queryByUserInfo(UserInfo userinfo) throws Exception {
		int flag = 0;
		String sql = "SELECT * from userinfo where username=?";
        PreparedStatement pstmt = null ;   
        DBConnect dbc = null;   
  
        // 下面是针对数据库的具体操作   
        try{   
            // 连接数据库   
            dbc = new DBConnect() ;   
            pstmt = dbc.getConnection().prepareStatement(sql) ; 
            pstmt.setString(1,userinfo.getUsername()) ;   
            // 进行数据库查询操作   
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){  
                // 查询出内容，将其与用户提交的内容对比 
                if(rs.getString("password").equals(userinfo.getPassword())){
                	flag = 1;
                } 
            }   
            rs.close() ; 
            pstmt.close();
            
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{   
            // 关闭数据库连接   
            dbc.close() ;   
        }   
		return flag;
	}
}

	

