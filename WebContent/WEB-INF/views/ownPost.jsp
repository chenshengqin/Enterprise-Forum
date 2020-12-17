<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>自己的主题帖</title>
    <link rel="stylesheet" 
          type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <c:import url="Banner.jsp"></c:import>
    
      <div class="postList">
      <h1>个人主题</h1>
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
    
    <br/><a href="<c:url value="/" />">返回主题帖列表</a>
    
    <c:import url="Footer.jsp"></c:import>
  </body>
</html>