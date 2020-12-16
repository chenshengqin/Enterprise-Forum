<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
  
    
    
    
    <hr><c:import url="Footer.jsp"></c:import>
  </body>
</html>