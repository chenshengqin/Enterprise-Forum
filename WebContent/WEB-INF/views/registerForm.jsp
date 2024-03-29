﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="true" %>
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  	<title>注册</title>
    
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
  
    <h1>注册</h1>
    <a href="<c:url value="/" />">主页</a>
    
    <sf:form method="POST" commandName="poster">
	 姓名：<sf:input path="trueName" /><sf:errors path="trueName" cssClass="error"/><br/><br/>
	 邮箱：<sf:input path="email" /><sf:errors path="email" cssClass="error"/><br/><br/>
	用户名：<sf:input path="userName" /><sf:errors path="userName" cssClass="error"/>
	<c:if test="${not empty errSameUserName}">
	    <font color="#FF0000">与已有用户名重复！</font>
	</c:if><br/><br/>
	 密码：<sf:password  path="password" /><sf:errors path="password" cssClass="error"/><br/><br/>
    <input type="submit" value="注册" />
    </sf:form>
    
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
