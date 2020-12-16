﻿<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
  <head>
    <title>登录错误</title>
    
    <link rel="stylesheet" 
          type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
  <c:import url="Banner.jsp"></c:import>
  
    <h1>登录有误，请重新尝试</h1>
    <a href="<c:url value="/login" />">登录</a> |
    <a href="<c:url value="/poster/register" />">注册</a> 
    
    <c:import url="Footer.jsp"></c:import>
  </body>
</html>
