<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录</title>
    <link rel="stylesheet" type="text/css" 
          href="<c:url value="/ex5/resources/style.css" />" >
  </head>
  <body>
 
  
    <h1>管理员登录</h1>
    <form method="POST">
      用户名: <input type="text" name="userName" /><br/><br/>
      　密码: <input type="password" name="password" /><br/><br/>
      <input type="submit" value="登录" />
    </form>
    
    <c:import url="Footer.jsp"></c:import>
  </body>
</html>
