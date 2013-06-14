<%@page import="here.wait.photo.share.service.PhoneMessageService"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="here.wait.photo.share.utils.AppliationContextUtility"%>
<%@ page language="java" import="here.wait.photo.share.service.PhoneMessageService"%>
<%@ page language="java" import="here.wait.photo.share.bean.PhoneMessage"%>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test_phone_message_list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <%
    List<PhoneMessage> list = ((PhoneMessageService)AppliationContextUtility.getBean("phoneMessageService")).getAll();
   	for(PhoneMessage p : list)
   	{
   		out.println("发送人:" + p.getSender() + ", 接收人:" + p.getReceiver() + ", 内容: " + p.getContent() + ", 时间:" + p.getCreateTime());
   		out.println("<br />");
   	}
   	%>
  </body>
</html>
