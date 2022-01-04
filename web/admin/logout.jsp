<%--
  Created by IntelliJ IDEA.
  User: yog
  Date: 2022/1/4
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
<%
    session.setAttribute("admin_id", null);
    response.sendRedirect("/TICKET_war_exploded/admin/index.jsp");
%>
</body>
</html>
