<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
  <head>
    <title>企业论坛</title>
	<c:import url="Banner.jsp"></c:import><hr>
    <link rel="stylesheet" 
          type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <a href="<c:url value="/posts" />">主题帖</a> |
    <c:choose>
    	<c:when test = "${not empty sessionScope.poster && not empty sessionScope.poster.id }">
    		<a href="<c:url value="/logout" />">注销</a> |
    	</c:when>
    	<c:otherwise>
    		<a href="<c:url value="/login" />">登录</a> |
    		<a href="<c:url value="/spitter/register" />">注册</a> |
    	</c:otherwise>
    </c:choose>
    <a href="<c:url value="/manager" />">管理</a>
    
    <hr><c:import url="Footer.jsp"></c:import>
  </body>
</html>
