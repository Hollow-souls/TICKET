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
 * @create 2022-01-2022/1/4-12:56
 */
@WebServlet("/frame")
public class frame extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("admin_id") != null) {
            RequestDispatcher rd=getServletContext().getRequestDispatcher("/admin/frame.jsp");
            rd.forward(req, resp);
        }else {
            resp.sendRedirect("admin/index.jsp");
        }

    }
}
