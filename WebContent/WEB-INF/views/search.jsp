<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="MyTag" uri="MyTag"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>搜索</title>
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
    
    <a href="<c:url value="/" />">主页</a>
    <form method="POST">
      <textarea name="searchText" cols="50" rows="2"></textarea>
      <input type="submit" value="搜索" />
    </form>
    
    <c:if test="${not empty List}">
    <c:choose>
      <c:when test="${not empty sessionScope.pageNo}">
        <MyTag:PostFilter postList="${List}" searchText="${sessionScope.searchText}" pageNo="${sessionScope.pageNo}"/>
      </c:when>
      <c:otherwise>
        <MyTag:PostFilter postList="${List}" searchText="${sessionScope.searchText}" pageNo="1"/>
      </c:otherwise>
    </c:choose>
    </c:if>
    
    <div class="postList">
      <h1>主题</h1>
      <ul class="postList">
        <c:forEach items="${paginationSupport.items}" var="post" >
          <li id="post_<c:out value="${post.id}"/>">
            <div class="postName"><a href="<c:url value="/posts/${post.id}" />">${post.postName}</a></div>
            <div class="postTime">
              <fmt:formatDate value="${post.postedTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
              by <c:out value="${post.poster.userName}" />
            </div>
            <div class="clickTimes">点击次数：<c:out value="${post.click}" /></div>
            <div class="replyTimes">回帖数：<c:out value="${post.follow}" /></div>
            <div class="postTopped">置顶：
              <c:if test="${post.topped}">
                已置顶
              </c:if>
              <c:if test="${not post.topped}">
                未置顶
              </c:if>
            </div>
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