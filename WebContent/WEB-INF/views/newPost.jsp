<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>新建主题帖</title>
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
  
    <div class="postForm">
      <h1>新建主题帖</h1> 
      <form method="POST" name="postForm" >
      	主题：<br/><textarea name="postName" cols="80" rows="1" ></textarea><br/>
	      <c:if test="${not empty emptyPostName}">
	        <font color="#FF0000">标题为空！</font>
	      </c:if><br/>
        内容：<br/><textarea name="message" cols="80" rows="5"></textarea><br/>
          <c:if test="${not empty emptyMessage}">
	        <font color="#FF0000">内容为空！</font>
	      </c:if><br/><br/>
        <c:if test="${not sessionScope.poster.locked}">
          <input type="submit" value="发表主题帖"/>
        </c:if>
        <c:if test="${sessionScope.poster.locked}">
          <input type="submit" value="发表主题帖" disabled/>
          <br/>对不起，你的账户被锁定了！
        </c:if>
      </form>
    </div>
    <a href="<c:url value="/" />">返回首页</a>
      
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