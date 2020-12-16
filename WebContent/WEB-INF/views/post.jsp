<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
  <head>
    <title>${post.postName}</title>
    <c:import url="Banner.jsp"></c:import><hr>
    <link rel="stylesheet" 
          type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <div class="postView">
      <div class="postName"><c:out value="${post.postName}" /></div>
      <div class="postMessage"><c:out value="${post.message}" /></div>
      <div>
        <span class="postTime"><c:out value="${post.postedTime}" /></span>
      </div>
    </div>
    <a href="<c:url value="/posts" />">返回主题帖列表</a>
    <div class="postList">
      <h1>最近回复</h1>
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
    
    <hr><c:import url="Footer.jsp"></c:import>
  </body>
</html>