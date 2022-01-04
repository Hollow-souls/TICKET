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
 * @create 2022-01-2022/1/4-12:53
 */
@WebServlet("/login_reg")
public class check_login_reg extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.println("该程序不接受直接访问，请不要尝试非法操作");
        resp.setHeader("refresh", "2;url=index/login_reg.jsp");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String log_name = req.getParameter("log_name");
        String log_pwd = req.getParameter("log_pwd");
        String reg_name = req.getParameter("reg_name");
        String reg_pwd1 = req.getParameter("reg_pwd1");
        String reg_pwd2 = req.getParameter("reg_pwd2");

        if(log_name!=null&&log_pwd!=null&&reg_name==null&&reg_pwd1==null&&reg_pwd2==null) {

            go_login(log_name, log_pwd, req, resp);

        }
        else if(log_name==null&&log_pwd==null&&reg_name!=null&&reg_pwd1!=null&&reg_pwd2!=null&&reg_pwd1.equals(reg_pwd2)) {

            go_reg(reg_name, reg_pwd1, req, resp);

        }
        else {

            resp.setContentType("text/html;charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.println("请不要尝试非法操作");


            resp.setHeader("refresh", "2;url=index/login_reg.jsp");
        }
    }

    protected void go_login(String name, String pwd, HttpServletRequest req, HttpServletResponse resp) {

        db_conn conn = new db_conn();
        get_md5 MD5 = new get_md5();
        pwd = MD5.getMD5(pwd);
        pwd = MD5.getMD5(pwd);

        HttpSession session = req.getSession();
        String sql = "select * from common_user where user_name = '"+name+"'";
        ResultSet res = conn.executeQuery(sql);
        try {

            if(res.next()) {
                String user_pwd = res.getString(2);

                if(pwd.equals(user_pwd)) {

                    session.setAttribute("user_id", name);

                    if(session.getAttribute("url")!=null) {
                        String url = session.getAttribute("url").toString();
                        try{
                            resp.sendRedirect(url);
                        }
                        catch (IOException e) {
                            System.out.println("出错信息如下："+e);
                        }
                    }else {
                        try {
                            resp.sendRedirect("user_center");
                        }
                        catch (IOException e) {
                            System.out.println("出错信息入下："+e);
                        }

                    }
                }else {
                    try {
                        resp.setContentType("text/html;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        out.println("账号或密码错误，请重新登录");
                        resp.setHeader("refresh", "2;url=index/login_reg.jsp");
                    }catch (IOException e) {
                        System.out.println("出错信息如下："+e);
                    }

                }
            }else {
                try {
                    resp.setContentType("text/html;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.println("账号或密码错误，请重新登录");
                    resp.setHeader("refresh", "2;url=index/login_reg.jsp");
                }catch (IOException e) {
                    System.out.println("出错信息如下："+e);
                }
            }
            conn.closeDB();
        }
        catch (SQLException e) {
            System.out.println("出错信息如下："+e);
        }
    }
    protected void go_reg(String name, String pwd1,HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html;charset=utf-8");


        db_conn conn = new db_conn();
        get_md5 MD5 = new get_md5();
        pwd1 = MD5.getMD5(pwd1);
        pwd1 = MD5.getMD5(pwd1);
        String sql = "select * from common_user where user_name = '"+name+"'";
        ResultSet res = conn.executeQuery(sql);
        try {
            PrintWriter out = resp.getWriter();
            try {
                if(res.next()) {
                    out.println("该用户名已被占用，请使用其它用户名重新注册");
                    resp.setHeader("refresh", "2;url=index/login_reg.jsp");
                }else {
                    sql = "insert into common_user (user_name,user_pwd) values('"+name+"','"+pwd1+"')";
                    int in_res = conn.executeInsert(sql);
                    //System.out.println(in_res);
                    if(in_res==1) {
                        out.println("恭喜您注册成功，2秒之后跳转到登录页面");
                        resp.setHeader("refresh", "2;url=index/login_reg.jsp");
                    }else {
                        out.println("出错啦，请联系开发人员进行修补bug");
                        resp.setHeader("refresh", "2;url=index/login_reg.jsp");
                    }
                }
            }catch (Exception e) {
                System.out.print("错误信息如下："+e);
            }
        }catch (IOException e) {
            System.out.println("出错啦"+e);
        }

        conn.closeDB();
    }
}
