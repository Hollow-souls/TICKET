package servlet;

import jakarta.servlet.annotation.WebServlet;
import javabean.db_conn;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author shkstart
 * @create 2022-01-2022/1/4-12:55
 */
@WebServlet("/add_order")
public class deal_order extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        if(session.getAttribute("user_id")!=null) {
            String user_id=session.getAttribute("user_id").toString();
            String f_i=req.getParameter("flight_id");
            String passenger_name=req.getParameter("passenger_name");
            String date=req.getParameter("date");
            String grade=req.getParameter("grade");
            String seat=req.getParameter("seat");
            String passenger_id=req.getParameter("passenger_id");
            String contact=req.getParameter("contact");
            String contact_phone=req.getParameter("contact_phone");

            int num=0;
            switch (grade){
                case "头等舱": num=(int) (Math.random()*10+1);break;
                case "商务舱": num=(int) (Math.random()*30+11);break;
                case "经济仓": num=(int) (Math.random()*50+31);break;
            }
            grade=grade.concat(seat).concat(String.valueOf(num));

            if(f_i!=""&&passenger_name!=""&&date!=""&&grade!=""&&passenger_id!=""&&contact!=""&&contact_phone!="") {
                db_conn conn=new db_conn();
                String sql="insert into t_order (f_n,order_user,p_name,date,grade,p_id,contact,c_p) values('"+f_i+"','"+user_id+"','"+passenger_name+"','"+date+"','"+grade+"','"+passenger_id+"','"+contact+"','"+contact_phone+"')";
                Integer res=conn.executeInsert(sql);
                System.out.println(res);
                if(res.equals(1)) {
                    resp.sendRedirect("default/order_list.jsp");
                }else {
                    resp.sendRedirect("default/order.jsp");
                }

            }else {
                resp.sendRedirect("default/order.jsp");
            }

        }else {
            resp.sendRedirect("default/order.jsp");
        }

    }
}
