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
<img src="/EnterpriseForumSystem/image/java.png">
	<h1>企业论坛
	<c:choose>
      <c:when test = "${not empty sessionScope.poster && not empty sessionScope.poster.id }">
        ，欢迎！用户<c:out value="${sessionScope.poster.userName}" /><br/>
      </c:when>
      <c:when test = "${not empty sessionScope.manager && not empty sessionScope.manager.id }">
        ，欢迎！管理员<c:out value="${sessionScope.manager.userName}" /><br/>
      </c:when>
      <c:otherwise>
        ，欢迎！游客<br/>
      </c:otherwise>
    </c:choose>
    </h1>
    <hr>
</body>
</html>