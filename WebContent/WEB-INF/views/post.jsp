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
      <c:out value="${post.id}" />
      <div class="postName"><c:out value="${post.postName}" /></div>
      <div class="postMessage"><c:out value="${post.message}" /></div>
      <div>
        <span class="postTime"><c:out value="${post.postedTime}" /></span>
      </div>
    </div>
    <c:if test="${post.postName} == ${session.userName}">
      <a href="<c:url value="/posts/edit/${post.id}" />">编辑主题帖</a>
    </c:if>
    <a href="<c:url value="/" />">返回主题帖列表</a>
    
    <div class="postList">
      <h1>最近跟帖</h1>
      <ul class="postList">
        <c:forEach items="${paginationSupport.items}" var="post" >
          <li id="post_<c:out value="${reply.id}"/>">
            <div class="replyMessage"><c:out value="${post.message}" /></div>
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
    
    <c:import url="Footer.jsp"></c:import>
  </body>
</html>