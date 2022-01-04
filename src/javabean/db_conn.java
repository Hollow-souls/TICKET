package javabean;

import java.sql.*;

/**
 * @author shkstart
 * @create 2022-01-2022/1/4-12:46
 */
public class db_conn {
    public Connection conn = null;
    public ResultSet res = null;
    public Statement st = null;


    public  db_conn() {
        String URL="jdbc:mysql://localhost:3309/fly_ticket_pre_book?serverTimezone=UTC";
        String USER="root";
        String PWD="190591071";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }try{
            conn = DriverManager.getConnection(URL,USER,PWD);
            st=conn.createStatement();
        }catch(SQLException e){
            System.out.println(e);
        }
    }


    public int executeInsert(String sql){
        int num=0;
        try{
            num=st.executeUpdate(sql);
        }
        catch(SQLException e){
            System.out.println("添加数据出错信息"+e.getMessage());
        }
        return num;
    }


    public ResultSet executeQuery(String sql){
        res=null;
        try{
            res=st.executeQuery(sql);
        }
        catch(SQLException e){
            System.out.print("查询出错信息:"+e.getMessage());
        }
        return res;
    }


    public int Update(String sql){
        int num=0;
        try{
            num=st.executeUpdate(sql);
        }catch(SQLException ex){
            System.out.print("执行修改有错误："+ex.getMessage());
        }
        return num;
    }


    public int executeDelete(String sql){
        int num=0;
        try{
            num=st.executeUpdate(sql);
        }
        catch(SQLException e){
            System.out.print("执行删除有错误:"+e.getMessage());
        }
        return num;
    }


    public void closeDB(){
        try{
            st.close();
            conn.close();
        }
        catch(Exception e){
            System.out.print("执行关闭Connection对象有错误:"+e.getMessage());
        }
    }
}
