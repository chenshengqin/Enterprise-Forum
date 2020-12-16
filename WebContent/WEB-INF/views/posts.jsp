<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>主题帖</title>
    <c:import url="Banner.jsp"></c:import><hr>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <div class="postForm">
      <h1>新建主题帖</h1> 
      <form method="POST" name="postForm" >
      	主题：<br/><textarea name="postName" cols="80" rows="1" ></textarea><br/>
        内容：<br/><textarea name="message" cols="80" rows="5"></textarea><br/><br/>
        <input type="submit" value="发表主题帖" />
      </form>
    </div>
    <a href="<c:url value="/" />">返回首页</a>

	<hr>
    <div class="postList">
      <h1>最近主题</h1>
      <ul class="postList">
        <c:forEach items="${paginationSupport.items}" var="post" >
          <li id="post_<c:out value="${post.id}"/>">
            <div class="postName"><c:out value="${post.postName}" /></div>
            <div class="postMessage"><c:out value="${post.message}" /></div>
            <div class="postTime">
              <fmt:formatDate value="${post.postedTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
              by <c:out value="${post.poster.userName}" />
            </div>
          </li>
        </c:forEach>
      </ul>
      </div>
    	每页${paginationSupport.pageSize}条主题帖，  第${paginationSupport.currentPageNo}/${paginationSupport.totalPageCount}页,共${paginationSupport.totalCount}条主题帖
      <c:if test="${paginationSupport.previousPage}">
      	<a href="<c:url value="/posts?pageNo=${paginationSupport.currentPageNo-1}" />" >上一页</a> 
      </c:if>
      <c:if test="${paginationSupport.nextPage}">
      	<a href="<c:url value="/posts?pageNo=${paginationSupport.currentPageNo+1}" />" >下一页</a>
      </c:if>
    
      
      <hr><c:import url="Footer.jsp"></c:import>
  </body>
</html>