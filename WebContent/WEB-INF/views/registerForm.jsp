<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="true" %>
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  	<title>注册</title>
    <link rel="stylesheet" type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <c:import url="Banner.jsp"></c:import>
  
    <h1>注册</h1>
    
    <sf:form method="POST" commandName="poster">
    <sf:errors path="*" cssClass="error"/><br/><br/>
	　姓名：<sf:input path="trueName" /><sf:errors path="trueName" cssClass="error"/><br/><br/>
	　邮箱：<sf:input path="email" /><sf:errors path="email" cssClass="error"/>  <br/><br/>
	用户名：<sf:input path="userName" /><sf:errors path="userName" cssClass="error"/><br/><br/>
	　密码：<sf:password  path="password" /><sf:errors path="password" cssClass="error"/><br/><br/>
      <input type="submit" value="注册" />
    </sf:form>
    
    <c:import url="Footer.jsp"></c:import>
  </body>
</html>
