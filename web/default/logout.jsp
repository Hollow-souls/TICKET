<%--
  Created by IntelliJ IDEA.
  User: yog
  Date: 2022/1/4
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.setAttribute("user_id", null);
    response.sendRedirect("index.jsp");
%>
