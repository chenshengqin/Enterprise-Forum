<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>编辑主题帖</title>
    <link rel="stylesheet" 
          type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
  
  <body>
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
        <c:if test="${post.poster.userName == sessionScope.poster.userName}">
          <input type="submit" value="编辑主题帖" />
        </c:if>
        <c:if test="${post.poster.userName != sessionScope.poster.userName}">
          <input type="submit" value="编辑主题帖" disabled/>
          <br/>对不起，你不是发帖人！
        </c:if>
      </form>
    </div>
    <a href="<c:url value="/posts/${post.id}" />">返回主题帖与跟帖页面</a>
      
    <c:import url="Footer.jsp"></c:import>
  </body>
  
</html>