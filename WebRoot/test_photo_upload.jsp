<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'test_user_login.jsp' starting page</title>

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
	<form action="servlet/PhotoUpload" method="post" enctype="multipart/form-data" >
		userId:<input type="text" name="userId" /><br /> 
		photo:<input type="file" name="photo" /><br /> 
		content:<input type="text" name="content" /><br /> 
		longitude:<input type="text" name="longitude" /><br /> 
		latitude:<input type="text" name="latitude" /><br /> 
		address:<input type="text" name="address" /><br /> 
		isBlow:<input type="text" name="isBlow" /><br /> 
		<input type="submit" value="submit">
	</form>
</body>
</html>
