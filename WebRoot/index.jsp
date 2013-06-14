<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
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
  用户模块:<br />
  <a href="test_user_register.jsp">测试用户注册</a><br />
  <a href="test_user_login.jsp">测试用户登录</a><br />
  <a href="test_get_fan_list.jsp">测试根据用户id获取粉丝列表</a><br />
  <a href="test_get_subscription_list.jsp">测试根据用户id获取订阅列表</a><br />
  <a href="test_user_add_subscription.jsp">测试订阅用户照片</a><br />
  <a href="test_user_delete_subscription.jsp">取消订阅用户照片</a><br />
  <a href="test_get_subscription_relation_count.jsp">获取订阅用户订阅量</a><br />
  <a href="test_get_fan_relation_count.jsp">获取订阅用户粉丝量</a><br />
  <a href="test_head_pic_upload.jsp">头像上传</a><br />
  <a href="test_get_user.jsp">获取用户</a><br />
  <a href="test_is_subscription_relation.jsp">订阅与否</a><br />
  <a href="test_search_user_by_name.jsp">搜索用户</a><br />
  <a href="test_user_like_photo_add.jsp">添加用户喜欢的相片</a><br />
  <a href="test_user_like_photo_delete.jsp">取消用户喜欢的相片</a><br />
  <a href="test_user_is_like_photo.jsp">用户是否喜欢的相片</a><br />
  <a href="test_user_like_photo_photo_count_get.jsp">获取用户喜欢相片的总数</a><br />
  
  <br>
  图片模块:<br />
  <a href="test_photo_upload.jsp">测试图片上传</a><br />
  <a href="test_photo_get_list_4_user_id.jsp">测试根据用户id获取相片列表</a><br />
  <a href="test_get_subscription_photo_list.jsp">测试根据用户id获取订阅相片列表</a><br />
  <a href="test_add_photo_comment.jsp">测试添加评论</a><br />
  <a href="test_get_photo_comment_list.jsp">测试获取评论列表</a><br />
  <a href="test_get_nearby_photo_list.jsp">测试获取附近的图片列表</a><br />
  <a href="test_get_photo_count.jsp">测试获取用户图片数量</a><br />
  <a href="test_get_random_blow_photo.jsp">测试随机获取吹图片</a><br />
  <a href="test_get_photo_comment_count.jsp">测试获取相片评论数</a><br />
  <a href="test_get_user_like_photo_photo_list.jsp">获取用户喜欢的图片列表</a><br />
  <a href="test_user_like_photo_users_count_get.jsp">获取喜欢该相片的用户数</a><br />
手机模块:<br />
  <a href="test_phone_content_add.jsp">添加短信内容</a><br />
  <a href="test_phone_message_list.jsp">短信列表</a><br />
  </body>
</html>
