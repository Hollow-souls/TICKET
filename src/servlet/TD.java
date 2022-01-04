package servlet;

import jakarta.servlet.annotation.WebServlet;
import javabean.db_conn;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shkstart
 * @create 2022-01-2022/1/4-17:11
 */
@WebServlet("/TD")
public class TD extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("mm");
        String id=req.getParameter("id");
        db_conn conn=new db_conn();
        String sql="delete from t_order where id='"+id+"'";
        conn.executeDelete(sql);
        conn.closeDB();
        resp.sendRedirect("default/order_list.jsp");
    }
}
