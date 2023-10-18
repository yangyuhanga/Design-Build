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
import testWeb.vo.UserInfo;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteServlet() {
        super();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) 
            throws IOException, ServletException {
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) 
            throws IOException, ServletException {
        // 从请求参数中获取用户名
        String username = req.getParameter("username");

        // 创建 UserInfo 对象并设置用户名
        UserInfo userinfo = new UserInfo();
        userinfo.setUsername(username);

        RecordsDAOImpl rdao = new RecordsDAOImpl();

        try {
            // 删除记录
            rdao.deleteRecord(userinfo);
            
            // 重新查询记录并更新会话中的列表数据
            List<Records> list = rdao.queryByRecord(userinfo, null);

            // 传入 Record 对象
            HttpSession session = req.getSession();
            session.setAttribute("list", list);

            // 传入 username
            HttpSession session2 = req.getSession();
            session2.setAttribute("username", username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        res.sendRedirect("./delete.jsp");
    }
}