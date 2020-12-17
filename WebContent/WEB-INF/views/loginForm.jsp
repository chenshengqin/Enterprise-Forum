<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录</title>
    
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
  
    <h1>登录</h1>
    <form method="POST">
      用户名: <input type="text" name="userName" /><br/><br/>
      　密码: <input type="password" name="password" /><br/><br/>
      <input type="submit" value="登录" />
    </form>
    
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
