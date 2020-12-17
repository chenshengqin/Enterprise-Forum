<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="true" %>
<jsp:useBean id="manager" class="forum.entity.Manager" scope="request"/>
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  	<title>管理员添加</title>
    <link rel="stylesheet" type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <c:import url="Banner.jsp"></c:import>
  
    <h1>管理员添加</h1>
    
    <sf:form method="POST" commandName="manager">
    <sf:errors path="*" cssClass="error"/><br/><br/>
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
  </body>
</html>
