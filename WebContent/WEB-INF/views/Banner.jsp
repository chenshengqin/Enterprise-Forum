<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Banner</title>
</head>
<body>
<img src="image/java.png">
	<h1>企业论坛
     <c:if test = "${not empty sessionScope.poster && not empty sessionScope.poster.id }">
    	，欢迎！用户<c:out value="${poster.trueName}" /><br/>
    </c:if>
    <c:if test = "${empty sessionScope.poster}">
    	，欢迎！游客<br/>
    </c:if>
    </h1>
    <hr>
</body>
</html>