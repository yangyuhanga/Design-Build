package testWeb.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import testWeb.vo.DataProcessor;
import testWeb.vo.Records; 

public class DataReadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DataProcessor dataProcessor = new DataProcessor();
        dataProcessor.processData();
        
        HttpSession session = request.getSession();
		session.setAttribute("list", dataProcessor.getdataList());

        response.sendRedirect("menu.jsp"); // 重定向到结果页面
    }
}