<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>管理员列表</title>
    <link rel="stylesheet" 
          type="text/css" 
          href="<c:url value="/resources/styles.css" />" >
    <style>
    *{
        margin:0;
        padding:0;
    }
    .div1{
        margin:0 auto;
        width:370px;
    }
    </style>
  </head>
  <body>
  <div class="div1">
    <c:import url="Banner.jsp"></c:import>
     
    <div class="postList">
      <h1>管理员列表</h1>
      <ul class="postList">
        <c:forEach items="${paginationSupport.items}" var="manager" >
          <li id="post_<c:out value="${manager.id}"/>">
            <div class="posterName">管理员用户名: <c:out value="${manager.userName}" /></div>
            <c:if test = "${sessionScope.manager.userName != manager.userName}">
              <a href="<c:url value="/manager/showManager/deleteManager/${manager.id}" />">删除</a>
			</c:if>
          </li>
        </c:forEach>
      </ul>
    </div>
    每页${paginationSupport.pageSize}个管理者，  第${paginationSupport.currentPageNo}/${paginationSupport.totalPageCount}页,共${paginationSupport.totalCount}个管理者
    <c:if test="${paginationSupport.previousPage}">
      <a href="<c:url value="/manager/showManager?pageNo=${paginationSupport.currentPageNo-1}" />" >上一页</a> 
    </c:if>
    <c:if test="${paginationSupport.nextPage}">
      <a href="<c:url value="/manager/showManager?pageNo=${paginationSupport.currentPageNo+1}" />" >下一页</a>
    </c:if>
    <br>
    <a href="<c:url value="/manager" />">返回首页</a>
    <c:import url="Footer.jsp"></c:import>
        <div class="htmleaf-container">
	<div class="wrapper">
		<ul class="bg-bubbles">
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
		</ul>
	</div>
	</div>
    </div>
  </body>
</html>
