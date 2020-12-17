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
    <c:import url="managerBanner.jsp"></c:import>
     
    <hr>
    <div class="postList">
      <h1>最近主题</h1>
      <ul class="postList">
        <c:forEach items="${paginationSupport.items}" var="manager" >
          <li id="post_<c:out value="${manager.id}"/>">
            <div class="posterName">管理员用户名: <c:out value="${manager.userName}" /></div>
            |<a href="<c:url value="/manager/showPoster/banPoster/${poster.id}" />">禁言</a>
            

          </li>
        </c:forEach>
      </ul>
    </div>
    每页${paginationSupport.pageSize}个用户，  第${paginationSupport.currentPageNo}/${paginationSupport.totalPageCount}页,共${paginationSupport.totalCount}个用户
    <c:if test="${paginationSupport.previousPage}">
      <a href="<c:url value="/home?pageNo=${paginationSupport.currentPageNo-1}" />" >上一页</a> 
    </c:if>
    <c:if test="${paginationSupport.nextPage}">
      <a href="<c:url value="/home?pageNo=${paginationSupport.currentPageNo+1}" />" >下一页</a>
    </c:if>
    
    <c:import url="Footer.jsp"></c:import>
  </body>
</html>
