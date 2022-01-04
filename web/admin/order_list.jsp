<%@ page import="javabean.db_conn" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
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
    <link rel="stylesheet" type="text/css" href="asset/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="asset/css/plugins/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="asset/css/plugins/animate.min.css"/>
    <link href="asset/css/style.css" rel="stylesheet">
</head>
<body>

<div class="panel">
    <div class="panel-body">
        <div class="col-md-12">
            <h3 class="animated fadeInLeft">订单列表</h3>
            <p class="animated fadeInDown">订单管理<span class="fa-angle-right fa"></span>订单列表</p>
        </div>
    </div>
</div>


<div class="col-md-12 padding-0 form-element">

    <div class="panel">
        <div class="panel-heading">
            <h3>列表内容</h3>
        </div>

        <div class="panel-body">
            <div class="responsive-table">
                <div id="datatables-example_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                    <div class="row">
                        <div class="col-sm-12">
                            <table class="table table-striped table-bordered dataTable no-footer" width="100%" cellspacing="0"  style="width: 100%;">
                                <thead>
                                <tr role="row">
                                    <th class="sorting_asc"style="width:15%;">用户名</th>
                                    <th class="sorting" style="width:10%;">航班号</th>
                                    <th class="sorting"  style="width:10%;">乘机人</th>
                                    <th class="sorting" style="width:15%;">乘机日期</th>
                                    <th class="sorting" style="width:10%;">舱位</th>
                                    <th class="sorting"  style="width:15%;">证件号码</th>
                                    <th class="sorting"  style="width:10%;">联系人</th>
                                    <th class="sorting"  style="width:15%;">联系电话</th>

                                </tr>
                                </thead>

                                <tbody>
                                <%
                                    db_conn conn=new db_conn();
                                    String sql="select * from t_order";
                                    ResultSet res=conn.executeQuery(sql);
                                    String row=null;
                                    while(res.next()){
                                        int i=0;
                                        List list=new ArrayList();
                                        list.add(res.getString(1));
                                        session.setAttribute("list",list);
                                        session.setAttribute("i",i);
                                %>

                                <tr>
                                    <td>
                                        <%=res.getString(3) %>
                                    </td>
                                    <td><p><%=res.getString(4) %></p></td>
                                    <td><%=res.getString(5) %></td>
                                    <td><%=res.getString(6) %></td>
                                    <td><%=res.getString(7) %></td>
                                    <td><%=res.getString(8) %></td>
                                    <td><%=res.getString(9) %></td>
                                    <td><a href="/TICKET_war_exploded/TD?id=${list[i]}" class="btn btn-danger btn-sm">退订</a></td>
                                </tr>

                                <%
                                        i++;
                                    }
                                %>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
