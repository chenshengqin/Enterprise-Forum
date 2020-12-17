<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>管理员主页</title>
    <link rel="stylesheet" 
          type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
  
  <body>
    <c:import url="Banner.jsp"></c:import>
  	<a href="<c:url value="/manager/showPost" />">编辑帖子</a> | 
  	   	<a href="<c:url value="/manager/showPoster" />">查看用户列表</a> | 
   	<a href="<c:url value="/manager/showManager" />">查看管理员列表</a> | 
   	<a href="<c:url value="/manager/register" />">新增管理员</a> |
   	<a href="<c:url value="/manager/modify" />">修改个人信息</a> | 
	<a href="<c:url value="/manager/logout" />">注销</a> |
   	<a href="<c:url value="/" />">主页</a>
    
    <c:import url="Footer.jsp"></c:import>
  </body>
</html>
