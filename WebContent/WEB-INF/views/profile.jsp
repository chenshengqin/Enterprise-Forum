<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
  <head>
    <c:import url="Banner.jsp"></c:import><hr>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <h1>个人信息</h1>
    用户名：<c:out value="${poster.userName}" /><br/><br/>
    　姓名：<c:out value="${poster.trueName}" /><br/><br/>
    　邮箱：<c:out value="${poster.email}" /><br/><br/>
    
    <c:if test = "${not empty sessionScope.poster }">
    		<a href="<c:url value="/" />">首页</a>
   	</c:if>
   	
   	<hr><c:import url="Footer.jsp"></c:import>
  </body>
</html>
