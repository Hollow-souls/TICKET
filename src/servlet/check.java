package servlet;

import jakarta.servlet.annotation.WebServlet;
import javabean.db_conn;
import javabean.get_md5;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author shkstart
 * @create 2022-01-2022/1/4-12:52
 */
@WebServlet("/login")
public class check extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.println("请使用默认方式提交数据，不要尝试非法操作");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String id = null;
        String password = null;
        HttpSession session = req.getSession();
        id = req.getParameter("id");
        password = req.getParameter("password");

        get_md5 MD5 = new get_md5();//创建一个获取MD5的对象，对password进行哈希
        password = MD5.getMD5(password);
        password = MD5.getMD5(password);//对password进行两次哈希，因为数据库中存的数据也是经过两次md5的结果
        System.out.println(password);


        db_conn conn = new db_conn();

        String sql = "select * from admin_user where user = '"+id+"'";


        try {
            ResultSet res = conn.executeQuery(sql);
            if(res.next()) {
                String pwd = res.getString(2);
                if(password.equals(pwd)) {
                    session.setAttribute("admin_id", id);
                    resp.sendRedirect("frame");
                }else {
                    System.out.println("密码错误"+res);
                    resp.sendRedirect("admin/index.jsp");
                }
            }else {
                System.out.println("账号错误");
                resp.sendRedirect("admin/index.jsp");
            }
        }catch(SQLException e) {
            System.out.println("出现不可预见性错误，错误信息如下："+e);
        }
    }
}
