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
          href="<c:url value="/resources/styles.css" />" >
    <style>
    *{
        margin:0;
        padding:0;
    }
    .div1{
        margin:0 auto;
        width:370px;
    }
    </style>
  </head>
  
  <body>
  <div class="div1">
    <c:import url="Banner.jsp"></c:import>
  	<a href="<c:url value="/manager/showPost" />">编辑帖子</a> | 
  	   	<a href="<c:url value="/manager/showPoster" />">查看用户列表</a> | 
<<<<<<< HEAD
   	<a href="<c:url value="/manager/showManager" />">查看管理员列表</a> | 
   	<a href="<c:url value="/manager/register" />">新增管理员</a> |
   	<a href="<c:url value="/manager/modify" />">修改个人信息</a> | 
	<a href="<c:url value="/manager/logout" />">注销</a> |
=======
   	<a href="<c:url value="/manager/showManager" />">查看管理员列表</a> | <br>
   	<a href="<c:url value="/showManager" />">修改个人信息</a> | 
>>>>>>> a24115ac0a528959fe82fe3658f911684e595673
   	<a href="<c:url value="/" />">主页</a>
    
    <c:import url="Footer.jsp"></c:import>
        <div class="htmleaf-container">
	<div class="wrapper">
		<ul class="bg-bubbles">
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
		</ul>
	</div>
	</div>
    </div>
  </body>
</html>
