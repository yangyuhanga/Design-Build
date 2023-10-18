package testWeb.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import testWeb.dao.UserDAO;
import testWeb.dao.impl.RecordDaoImpl;
import testWeb.dao.impl.RecordsDAOImpl;
import testWeb.dao.impl.UserDAOImpl;
import testWeb.vo.Records;
import testWeb.vo.RobotInfo;
import testWeb.vo.UserInfo;

public class UserLoginServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException{
		}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
		    throws IOException, ServletException{
			 UserInfo userinfo = new UserInfo();
			 userinfo.setUsername(req.getParameter("username"));
			 userinfo.setPassword(req.getParameter("password"));
			 
			 
			RobotInfo robotinfo = new RobotInfo();
			userinfo.setUsername(req.getParameter("username"));
			userinfo.setPassword(req.getParameter("password"));
				
			RecordsDAOImpl rdao = new RecordsDAOImpl();
			List<Records> list = new ArrayList<>();
			 
			 UserDAO dao = new UserDAOImpl();   
		     int flag = 0;
		     try {
					flag = dao.queryByUserInfo(userinfo);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			} 
			 if(flag == 1){   
				 HttpSession session=req.getSession(); 
				 RecordDaoImpl recordDao = new RecordDaoImpl();
				 String robotid = null;
				 try {
					 robotid = recordDao.queryRovoridByUsername(userinfo.getUsername());
					 list = rdao.queryByRecord(userinfo,robotinfo);
				 } catch (Exception e) {
					 throw new RuntimeException(e);
				 }
				 //连接robot id
		         session.setAttribute("username", userinfo.getUsername());   
		         session.setAttribute("robotid", robotid);
		         //查询记录
		         HttpSession session2 = req.getSession();
		         session2.setAttribute("list", list);
		         
		         res.sendRedirect("./welcome.jsp");
		        } else {   
		         res.sendRedirect("./error.jsp");
		        }
		 }
	
}
