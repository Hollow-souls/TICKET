<%--
  Created by IntelliJ IDEA.
  User: yog
  Date: 2022/1/4
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="verify_login.jsp" %><%--包含验证用户是否登录的代码 --%>
<jsp:useBean id="img_url" class="javabean.get_img" scope="session" />
<jsp:setProperty property="user_id" name="img_url" value="<%=session.getAttribute(\"user_id\") %>"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户中心</title>
    <link type="text/css" rel="stylesheet" href="/TICKET_war_exploded/index/css/core.css">
    <link type="text/css" rel="stylesheet" href="/TICKET_war_exploded/index/css/icon.css">
    <link type="text/css" rel="stylesheet" href="/TICKET_war_exploded/index/css/home.css">
</head>
<body>

<div class="ydc-entered" style="height:11%;width:100%;">
    <div class="ydc-header-content ydc-flex">

        <div class="ydc-column">
            <div class="ydc-column-user">
                <div class="ydc-user-photo">
                    <a href="javascript:;"> <img
                            src="<%=img_url.getImg() %>" title="" alt="">
                    </a>
                </div>
                <div class="ydc-user-info">

                    <div class="ydc-user-info-func ydc-flex">
							<span class="ydc-tag">
								<% if(session.getAttribute("user_id").equals("admin")){
                                    out.println("东风");
                                }else{
                                    out.println(session.getAttribute("user_id"));
                                }
                                %>
							</span>
                        <span class="ydc-mal"><i
                                class="ydc-icon ydc-icon-mail fl"></i><em>0</em></span> <a
                            href="/TICKET_war_exploded/index/logout.jsp">退出</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="ydc-content-slide ydc-body" style="height:86%;">
    <div class="ydc-flex" style="height:100%;">
        <!-- left begin -->
        <div class="ydc-column ydc-column-2">
            <div class="ydc-menu">
                <ul>
                    <li class="ydc-menu-item"><span class="ydc-menu-sub-title">
								<i class="ydc-icon ydc-icon-home fl"></i>首页
						</span>
                        <ul>
                            <li><a target="frame" href="/TICKET_war_exploded/index/user_info.jsp"
                                   class="active">个人资料</a></li>
                            <li><a target="frame" href="/TICKET_war_exploded/index/edit_info.jsp">修改信息</a></li>
                        </ul></li>

                    <li class="ydc-menu-item">
							<span class="ydc-menu-sub-title">
								<i class="ydc-icon ydc-icon-record fl"></i>订单
							</span>
                        <ul>
                            <li><a target="frame" href="/TICKET_war_exploded/index/order_list.jsp">订单列表</a></li>
                        </ul>
                    </li>

                    <li class="ydc-menu-item"><span class="ydc-menu-sub-title">
								<i class="ydc-icon ydc-icon-file fl"></i>管理
						</span>
                        <ul>
                            <li><a href="/TICKET_war_exploded/default/index.jsp">返回首页</a></li>
                        </ul></li>


                </ul>
            </div>
        </div>
        <!-- left end -->

        <!-- right start -->
        <div style="width:76%;height:100%;">
            <iframe name="frame" scrolling="auto" width="100%" height="100%" frameborder="0" src="/TICKET_war_exploded/index/user_info.jsp"></iframe>
        </div>
        <!-- right end -->
    </div>
</div>

</body>
</html>
