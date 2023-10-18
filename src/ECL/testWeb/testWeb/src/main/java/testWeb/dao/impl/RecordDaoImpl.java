package testWeb.dao.impl;

import testWeb.dao.RecordDao;
import testWeb.db.DBConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RecordDaoImpl implements RecordDao {
    @Override
    public String queryRovoridByUserid(String userid) throws Exception {
        String sql = "SELECT * from record where userid=?";
        PreparedStatement pstmt = null ;
        DBConnect dbc = null;
        String robotid = "";
        // 下面是针对数据库的具体操作
        try{
            // 连接数据库
            dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql) ;
            pstmt.setString(1,userid) ;
            // 进行数据库查询操作
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                // 查询出内容，将其与用户提交的内容对比
                if(rs.getString("robotid") != null){
                    robotid = rs.getString("robotid");
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
        return robotid;
    }

    @Override
    public String queryRovoridByUsername(String username) throws Exception {
        String sql = "select * from record r,userinfo u where r.userid=u.userinfoid and username=?";
        PreparedStatement pstmt = null ;
        DBConnect dbc = null;
        String robotid = "";
        // 下面是针对数据库的具体操作
        try{
            // 连接数据库
            dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql) ;
            pstmt.setString(1,username) ;
            // 进行数据库查询操作
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                // 查询出内容，将其与用户提交的内容对比
                if(rs.getString("robotid") != null){
                    robotid = rs.getString("robotid");
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
        return robotid;
    }
}
