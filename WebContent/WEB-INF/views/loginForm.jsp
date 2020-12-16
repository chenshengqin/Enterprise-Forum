<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
  <head>
    <title>登录</title>
    
    <link rel="stylesheet" type="text/css" 
          href="<c:url value="/ex5/resources/style.css" />" >
  </head>
  <body>
    <c:import url="Banner.jsp"></c:import>
  
    <h1>登录</h1>
    <form method="POST">
      用户名: <input type="text" name="userName" /><br/><br/>
      　密码: <input type="password" name="password" /><br/><br/>
      <input type="submit" value="登录" />
    </form>
    
    <c:import url="Footer.jsp"></c:import>
  </body>
</html>
