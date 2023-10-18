package testWeb.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import testWeb.dao.impl.RecordsDAOImpl;
import testWeb.vo.Records;
import testWeb.vo.RobotInfo;
import testWeb.vo.UserInfo;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws IOException, ServletException{
		}
		
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws IOException, ServletException{
				UserInfo userinfo = new UserInfo();
				RobotInfo robotinfo = new RobotInfo();
				RecordsDAOImpl rdao = new RecordsDAOImpl();
				List<Records> list = new ArrayList<>();
				try {
					list = rdao.queryByRecord(userinfo,robotinfo);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				HttpSession session = req.getSession();
				session.setAttribute("list", list);
				res.sendRedirect("./image.jsp");
	}
}
