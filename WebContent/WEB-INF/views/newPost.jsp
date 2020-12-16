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
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
  <c:import url="Banner.jsp"></c:import>
  
    <div class="postForm">
      <h1>新建主题帖</h1> 
      <form method="POST" name="postForm" >
      	主题：<br/><textarea name="postName" cols="80" rows="1" ></textarea><br/>
        内容：<br/><textarea name="message" cols="80" rows="5"></textarea><br/><br/>
        <input type="submit" value="发表主题帖" />
      </form>
    </div>
    <a href="<c:url value="/" />">返回首页</a>
      
    <hr><c:import url="Footer.jsp"></c:import>
  </body>
</html>