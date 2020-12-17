<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>个人信息</title>
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
  
    <h1>个人信息</h1>
    用户名：<c:out value="${poster.userName}" /><br/><br/>
    　姓名：<c:out value="${poster.trueName}" /><br/><br/>
    　邮箱：<c:out value="${poster.email}" /><br/><br/>
    
    <c:if test = "${not empty sessionScope.poster }">
    		<a href="<c:url value="/" />">首页</a>
   	</c:if>
   	
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
