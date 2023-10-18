package testWeb.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import testWeb.dao.UserDAO;
import testWeb.dao.impl.RecordsDAOImpl;
import testWeb.dao.impl.UserDAOImpl;
import testWeb.vo.Records;
import testWeb.vo.RobotInfo;
import testWeb.vo.UserInfo;

public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) 
    		throws IOException, ServletException{
    	}
    	
    public void doPost(HttpServletRequest req, HttpServletResponse res) 
    		throws IOException, ServletException{
    		
    	RecordsDAOImpl rdao = new RecordsDAOImpl();
    	Records records = new Records();
    	List<Records> list = new ArrayList<>();
    	
    	records.setUserid(Integer.parseInt(req.getParameter("userid")));
    	records.setRobotid(Integer.parseInt(req.getParameter("robotid")));
    	records.setTime(req.getParameter("time"));
   		records.setSpeed(Integer.parseInt(req.getParameter("speed")));
   		records.setDistance(Integer.parseInt(req.getParameter("distance")));
   		records.setDirection(Integer.parseInt(req.getParameter("direction")));
   		
   		try {
			rdao.addRecord(records);
			list = rdao.requeryByRecord(records);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
   		HttpSession session = req.getSession();
   		session.setAttribute("list", list);
		res.sendRedirect("./update.jsp");	
		
		//RequestDispatcher report = req.getRequestDispatcher("/login");
        //report.forward(req, res);
		
		}
		
}