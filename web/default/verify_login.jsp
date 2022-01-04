<%--
  Created by IntelliJ IDEA.
  User: yog
  Date: 2022/1/4
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if(session.getAttribute("user_id")==null){
        response.sendRedirect("/TICKET_war_exploded/index/login_reg.jsp");
        //为什么要写绝对路径？因为用户从servlet中正常访问和用户直接访问页面时，路径是不一样的，所以
        //写成绝对路径，防范错误发生
    }
%>
