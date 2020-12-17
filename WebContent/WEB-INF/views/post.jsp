<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>${post.postName}</title>
    <link rel="stylesheet" 
          type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <c:import url="Banner.jsp"></c:import>
  
    <div class="postView">
      <div class="postId">id:<c:out value="${post.id}" /></div>
      主题：<div class="postName"><c:out value="${post.postName}" /></div>
      内容：<div class="postMessage"><c:out value="${post.message}" /></div>
      <div class="postTime">发帖时间：
        <fmt:formatDate value="${post.postedTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
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
    </div>
      
    <c:if test="${post.poster.userName == sessionScope.poster.userName}">
      <a href="<c:url value="/posts/edit/${post.id}" />">编辑主题帖 |</a>
    </c:if>
    <a href="<c:url value="/" />">返回主题帖列表</a>
    
    <div class="replyList">
      <h1>最近跟帖</h1>
      <ul class="replyList">
        <c:forEach items="${paginationSupport.items}" var="reply" >
          <li id="reply_<c:out value="${reply.id}"/>">
            <div class="replyMessage"><c:out value="${reply.message}" /></div>
            <div class="replyTime">
              <fmt:formatDate value="${reply.postedTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
              by <c:out value="${reply.poster.userName}" />
            </div>
          </li>
        </c:forEach>
      </ul>
    </div>
    每页${paginationSupport.pageSize}条跟帖，  第${paginationSupport.currentPageNo}/${paginationSupport.totalPageCount}页,共${paginationSupport.totalCount}条跟帖
    <c:if test="${paginationSupport.previousPage}">
      <a href="<c:url value="/posts/${post.id}?pageNo=${paginationSupport.currentPageNo-1}" />" >上一页</a> 
    </c:if>
    <c:if test="${paginationSupport.nextPage}">
      <a href="<c:url value="/posts/${post.id}?pageNo=${paginationSupport.currentPageNo+1}" />" >下一页</a>
    </c:if>
    
    <br/>
    <form method="POST">
      跟帖回复：<br/><textarea name="replyMessage" cols="80" rows="5"></textarea><br/><br/>
      <c:choose>
        <c:when test = "${not empty sessionScope.poster && not empty sessionScope.poster.id }">
          <input type="submit" value="回复" />
        </c:when>
        <c:otherwise>
          <input type="submit" value="回复" disabled/>
          <br/>对不起，你还没有登录，请登录后回复！
        </c:otherwise>
      </c:choose>
    </form>
    
    <c:import url="Footer.jsp"></c:import>
  </body>
</html>