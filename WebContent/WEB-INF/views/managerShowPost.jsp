<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>浏览帖子</title>
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
      <h1>最近主题</h1>
      <ul class="postList">
        <c:forEach items="${paginationSupport.items}" var="post" >
          <li id="post_<c:out value="${post.id}"/>">
            <div class="postName"><c:out value="${post.postName}" /></div>
            <div class="postTime">
              <fmt:formatDate value="${post.postedTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
              by <c:out value="${post.poster.userName}" />
            </div>
            点击次数：<div class="clickTimes"><c:out value="${post.click}" /></div>
             回帖数：<div class="replyTimes"><c:out value="${post.follow}" /></div>
              | <a href="<c:url value="/posts/${post.id}" />">查看</a>
              | <a href="<c:url value="/manager/showPost/deletePost/${post.id}"/>">删帖</a> 
              <c:choose>
              	<c:when test = "${post.topped == true}">|已置顶</c:when>
              	<c:when test = "${post.topped == false }">
              		<a href="<c:url value="/manager/showPost/putToTop/${post.id}"/>">|置顶</a>
              	</c:when>
              </c:choose>
          </li>
        </c:forEach>
      </ul>
    </div>
    每页${paginationSupport.pageSize}条主题帖，  第${paginationSupport.currentPageNo}/${paginationSupport.totalPageCount}页,共${paginationSupport.totalCount}条主题帖
    <c:if test="${paginationSupport.previousPage}">
      <a href="<c:url value="/home?pageNo=${paginationSupport.currentPageNo-1}" />" >上一页</a> 
    </c:if>
    <c:if test="${paginationSupport.nextPage}">
      <a href="<c:url value="/home?pageNo=${paginationSupport.currentPageNo+1}" />" >下一页</a>
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
