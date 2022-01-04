package servlet;

import jakarta.servlet.annotation.WebServlet;
import javabean.db_conn;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shkstart
 * @create 2022-01-2022/1/4-12:56
 */
@WebServlet("/del_message")
public class del_user_message extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sort=req.getParameter("class");
        if(sort.equals("user")) {
            String user_name=req.getParameter("id");
            db_conn conn=new db_conn();
            String sql="delete from common_user where user_name='"+user_name+"'";
            conn.executeDelete(sql);
            resp.sendRedirect("admin/user_list.jsp");
        }else if (sort.equals("message")) {
            String id_str=req.getParameter("id");
            int id=Integer.parseInt(id_str);
            db_conn conn=new db_conn();

            String sql="delete from user_message where id='"+id+"'";
            //int res=
            conn.executeDelete(sql);
            //System.out.println(res);
            resp.sendRedirect("admin/feedback_list.jsp");
        }

    }
}
