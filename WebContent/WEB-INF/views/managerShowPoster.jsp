<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户列表</title>
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
      <h1>用户列表</h1>
      <ul class="postList">
        <c:forEach items="${paginationSupport.items}" var="poster" >
          <li id="post_<c:out value="${poster.id}"/>">
            <div class="posterName">用户名: <c:out value="${poster.userName}" /></div>
            <c:if test = "${poster.locked == false}">
            |<a href="<c:url value="/manager/showPoster/banPoster/${poster.id}" />">锁定</a>
            </c:if>
			<c:if test = "${poster.locked == true}">
            |<a href="<c:url value="/manager/showPoster/unbanPoster/${poster.id}" />">解除锁定</a>
            </c:if>
          </li>
        </c:forEach>
      </ul>
    </div>
    每页${paginationSupport.pageSize}个用户，  第${paginationSupport.currentPageNo}/${paginationSupport.totalPageCount}页,共${paginationSupport.totalCount}个用户
    <c:if test="${paginationSupport.previousPage}">
      <a href="<c:url value="/showPoster?pageNo=${paginationSupport.currentPageNo-1}" />" >上一页</a> 
    </c:if>
    <c:if test="${paginationSupport.nextPage}">
      <a href="<c:url value="/showPoster?pageNo=${paginationSupport.currentPageNo+1}" />" >下一页</a>
    </c:if>
    
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
