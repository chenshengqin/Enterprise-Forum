<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true" %>
<html>
  <head>
    <title>企业论坛</title>
	<c:import url="Banner.jsp"></c:import><hr>
    <link rel="stylesheet" 
          type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <a href="<c:url value="/posts" />">新建主题帖</a> |
    <c:choose>
    	<c:when test = "${not empty sessionScope.poster && not empty sessionScope.poster.id }">
    		<a href="<c:url value="/logout" />">注销</a> |
    	</c:when>
    	<c:otherwise>
    		<a href="<c:url value="/login" />">登录</a> |
    		<a href="<c:url value="/spitter/register" />">注册</a> |
    	</c:otherwise>
    </c:choose>
    <a href="<c:url value="/manager" />">管理</a>
    
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
