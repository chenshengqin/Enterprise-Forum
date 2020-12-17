<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>主页</title>
    <link rel="stylesheet" 
          type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <c:import url="Banner.jsp"></c:import>
  
    
    <c:choose>
      <c:when test = "${not empty sessionScope.poster && not empty sessionScope.poster.id }">
        <a href="<c:url value="/posts/newPost" />">新建主题帖</a> |
        <a href="<c:url value="/posts/ownPost" />">查看自己的主题帖</a> |
        <a href="<c:url value="/logout" />">注销</a> |
      </c:when>
      <c:otherwise>
        <a href="<c:url value="/login" />">登录</a> |
        <a href="<c:url value="/poster/register" />">注册</a> |
      </c:otherwise>
    </c:choose>
    <a href="<c:url value="/search" />">搜索</a> |
    <a href="<c:url value="/manager" />">管理</a>
    
    <hr>
    <div class="postList">
      <h1>最近主题</h1>
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
  </body>
</html>
