package servlet;

import jakarta.servlet.annotation.WebServlet;
import javabean.db_conn;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author shkstart
 * @create 2022-01-2022/1/4-12:49
 */

@WebServlet("/add_flight")
public class addFlight extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        String flight_id=req.getParameter("flight_id");
        String start_place=req.getParameter("start_place");
        String end_place=req.getParameter("end_place");
        String start_airport=req.getParameter("start_airport");
        String end_airport=req.getParameter("end_airport");
        String take_off_time=req.getParameter("take_off_time");
        String landing_time=req.getParameter("landing_time");
        String first_class_price_str=req.getParameter("first_class_price");
        Integer first_class_price=Integer.parseInt(first_class_price_str);
        String business_class_price_str=req.getParameter("business_class_price");
        Integer business_class_price=Integer.parseInt(business_class_price_str);
        String economy_class_price_str=req.getParameter("economy_class_price");
        Integer economy_class_price=Integer.parseInt(economy_class_price_str);

        db_conn conn=new db_conn();
        String sql="select * from flight where f_n='"+flight_id+"'";
        ResultSet res=conn.executeQuery(sql);
        try {
            if(res.next()) {
                resp.setContentType("text/html;charset=utf-8");
                PrintWriter out=resp.getWriter();
                out.println("您输入的航班号重复了，请选择其他航班号添加，5s后返回");
                resp.setHeader("refresh", "5;url=admin/flight_add.jsp");
            }else {
                sql="insert into flight values('"+flight_id+"','"+start_place+"','"+end_place+"','"+start_airport+"','"+end_airport+"','"+take_off_time+"','"+landing_time+"','"+first_class_price+"','"+business_class_price+"','"+economy_class_price+"')";
                //Integer res=
                conn.executeInsert(sql);
                //System.out.println(res);
                //System.out.println(sql);
                resp.sendRedirect("admin/flight_list.jsp");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
