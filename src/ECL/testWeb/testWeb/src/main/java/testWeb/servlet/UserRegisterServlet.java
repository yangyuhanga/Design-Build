package testWeb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.regex.Pattern;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import testWeb.vo.*;
import testWeb.dao.impl.*;

public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Constructor of the object.
	public UserRegisterServlet() {
		super();
	}
	//Destruction of the servlet.
	public void destroy() {
		super.destroy();
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws IOException, ServletException{
	}
	
@SuppressWarnings("null")
public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws IOException, ServletException{
	String userinfoid=req.getParameter("userinfoid");
	String username=req.getParameter("username");
	String gender=req.getParameter("gender");
	String password=req.getParameter("password");
	String email=req.getParameter("email");
	Random random = new Random();

    // 生成一个随机整数
    int robotid = random.nextInt(1000); // 返回任意整数
	
	UserInfo userinfo = new UserInfo();
	userinfo.setUserinfoid(userinfoid);
	userinfo.setUsername(username);
	userinfo.setGender(gender);
	userinfo.setPassword(password);	
	userinfo.setEmail(email);
	userinfo.setRobotid(robotid);
	

	UserDAOImpl dao =new UserDAOImpl(); 
	int flag=0;
	
	// 验证用户名
	if (username == null || username.isEmpty()) {
	    String errorMessage = "The username cannot be empty. Please return to the previous page and re-enter";
	    res.getWriter().println("<div style=\"background-color: #f2f2f2; padding: 20px;\">");
	    res.getWriter().println("<p style=\"font-family: Arial, sans-serif; color: red;\">" + errorMessage + "</p>");
	    res.getWriter().println("</div>");
	    return;
	}

    //验证性别
 
    if (gender == null || gender.isEmpty()) {
       // res.getWriter().println("Please select gender");
    	String errorMessage = "Please select gender";
	    res.getWriter().println("<div style=\"background-color: #f2f2f2; padding: 20px;\">");
	    res.getWriter().println("<p style=\"font-family: Arial, sans-serif; color: red;\">" + errorMessage + "</p>");
	    res.getWriter().println("</div>");
        return;
    } else if (!gender.equals("M") && !gender.equals("F")) {
        //res.getWriter().println("Gender must be Male or Female.");
    	String errorMessage = "Gender must be Male or Female.";
	    res.getWriter().println("<div style=\"background-color: #f2f2f2; padding: 20px;\">");
	    res.getWriter().println("<p style=\"font-family: Arial, sans-serif; color: red;\">" + errorMessage + "</p>");
	    res.getWriter().println("</div>");
        return;
    }


    // 验证密码
    if (password == null || password.isEmpty()) {
        //res.getWriter().println("The password cannot be empty. Please return to the previous page and re-enter.");
    	String errorMessage = "The password cannot be empty. Please return to the previous page and re-enter.";
	    res.getWriter().println("<div style=\"background-color: #f2f2f2; padding: 20px;\">");
	    res.getWriter().println("<p style=\"font-family: Arial, sans-serif; color: red;\">" + errorMessage + "</p>");
	    res.getWriter().println("</div>");
    	return;
    } else if (password.length() < 5) {
        //res.getWriter().println("The password must contain at least 5 characters.");
    	String errorMessage = "The password must contain at least 5 characters.";
	    res.getWriter().println("<div style=\"background-color: #f2f2f2; padding: 20px;\">");
	    res.getWriter().println("<p style=\"font-family: Arial, sans-serif; color: red;\">" + errorMessage + "</p>");
	    res.getWriter().println("</div>");
    	return;
    }

    // 验证邮箱
    Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    if (email == null || email.isEmpty()) {
        res.getWriter().println("The mailbox cannot be empty. Please return to the previous page and re-enter.");
        return;
    } else if (!emailPattern.matcher(email).matches()) {
        res.getWriter().println("Please enter a valid email address.");
        return;
    }
  
	try {
		 flag=dao.repeatByUserInfo(userinfo);
		 System.out.println(flag);
		 if(flag == 1){   
			 dao.insertByUserInfo(userinfo);
			 HttpSession session=req.getSession();   
	         session.setAttribute("username", userinfo.getUsername());   
	         //session.setAttribute("userinfoid", userinfo.getUserinfoid()); 
	         PrintWriter pw =res.getWriter();
	         pw.println("<script type=\"text/javascript\">");
	            pw.println("alert('Successfully Registered!');");
	           pw.println("window.location.href='login.jsp';");
	            pw.println("</script>");	         
	        System.out.println("Successfully Registered!");
	        //res.sendRedirect("./login.jsp");	        
	        } else {  
	        	PrintWriter pw =res.getWriter();
	        	pw.println("<script type=\"text/javascript\">");
	            pw.println("alert('The username has been registerd, please change another username');");
	            pw.println("window.location.href='register.jsp';");
	            pw.println("</script>");	        	
	        	System.out.println("The username has already exists.");
	        }

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
}
}
	
