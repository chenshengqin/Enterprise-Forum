<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>管理员编辑主题帖</title>
    
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
      <h1>编辑主题帖</h1> 
      <form method="POST" name="postForm" >
        id:<input type="text" name="id" value="${post.id}" readonly><br/>
      	主题：<c:out value="${post.postName}"/><br/>
      	<textarea name="postName" cols="80" rows="1"></textarea><br/>
	      <c:if test="${not empty emptyPostName}">
	        <font color="#FF0000">标题为空！</font>
	      </c:if><br/>
        内容：<c:out value="${post.message}"/><br/>
          <c:if test="${not empty emptyMessage}">
	        <font color="#FF0000">内容为空！</font>
	      </c:if><br/><br/>
        <textarea name="message" cols="80" rows="5"></textarea><br/><br/>
          <input type="submit" value="编辑主题帖" />
      </form>
    </div>
    <a href="<c:url value="/manager/showPost/${post.id}" />">返回主题帖与跟帖页面</a>
      
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