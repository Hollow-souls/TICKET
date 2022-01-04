package servlet;

import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author shkstart
 * @create 2022-01-2022/1/4-12:59
 */
@WebServlet("/user_center")
public class userCenter extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("hhhh");
        HttpSession session = req.getSession();
        Object user_id = session.getAttribute("user_id");
        if(user_id != null) {
            RequestDispatcher rd = (RequestDispatcher) getServletContext().getRequestDispatcher("/index/user_center.jsp");
            rd.forward(req, resp);
            session.setAttribute("url", "/TICKET_war_exploded/user_center");
        }else {
            resp.sendRedirect("index/login_reg.jsp");
        }
    }
}
