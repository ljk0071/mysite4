<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">

		<!-- header -->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->

		<!-- nav -->
		<c:import url="/WEB-INF/views/include/nav.jsp"></c:import>
		<!-- //nav -->

		<div id="container" class="clearfix">
			<!-- aside -->
			<c:import url="/WEB-INF/views/include/aside.jsp"></c:import>
			<!-- //aside -->

			<div id="content">

				<div id="content-head">
					<h3>게시판</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>게시판</li>
							<li class="last">일반게시판</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->
	
				<div id="board">
					<div id="list">
						<form action="./list" method="post">
							<div class="form-group text-right">
								<input type="text" name="keyword" value="">
								<button type="submit" id=btn_search>검색</button>
							</div>
						</form>
						<table >
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>글쓴이</th>
									<th>조회수</th>
									<th>작성일</th>
									<th>관리</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${pMap.rList}" var="rVo">
								<tr>
									<td>${rVo.no}</td>
									<td><a href="${pageContext.request.contextPath}/rboard/read/${rVo.no}/-1">${rVo.title}</a></td>
									<td>${rVo.name}</td>
									<td>${rVo.hit}</td>
									<td>${rVo.regDate}</td>
									<c:if test="${authUser.no == rVo.userNo}">
										<td><a href="./delete/${rVo.no}">[삭제]</a></td>
									</c:if>
								</tr>
							</c:forEach>
							</tbody>
						</table>
			
						<div id="paging">
						<c:choose>
							<c:when test="${pMap.keyword == null || pMap.keyword == ''}">
								<ul>
									<c:if test="${pMap.prev == true}">
										<li><a href="${pageContext.request.contextPath}/rboard/list?crtPage=${pMap.startPageNum-pMap.pageBtnCount}">◀</a></li>
									</c:if>
									<c:forEach begin="${pMap.startPageNum}" end="${pMap.endPageNum}" step="1" var="page">
										<c:choose>
											<c:when test="${pMap.crtPage==page}">
												<li class="active"><a href="${pageContext.request.contextPath}/rboard/list?crtPage=${page}">${page}</a></li>
											</c:when>
											<c:otherwise>
												<li><a href="${pageContext.request.contextPath}/rboard/list?crtPage=${page}">${page}</a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<c:if test="${pMap.next == true}">
										<li><a href="${pageContext.request.contextPath}/rboard/list?crtPage=${pMap.endPageNum+1}">▶</a></li>
									</c:if>
								</ul>
							</c:when>
							<c:otherwise>
								<ul>
									<c:if test="${pMap.prev == true}">
										<li><a href="${pageContext.request.contextPath}/rboard/list?crtPage=${pMap.startPageNum-pMap.pageBtnCount}&keyword=${pMap.keyword}">◀</a></li>
									</c:if>
									<c:forEach begin="${pMap.startPageNum}" end="${pMap.endPageNum}" step="1" var="page">
										<c:choose>
											<c:when test="${pMap.crtPage==page}">
												<li class="active"><a href="${pageContext.request.contextPath}/rboard/list?crtPage=${page}&keyword=${pMap.keyword}">${page}</a></li>
											</c:when>
											<c:otherwise>
												<li><a href="${pageContext.request.contextPath}/rboard/list?crtPage=${page}&keyword=${pMap.keyword}">${page}</a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<c:if test="${pMap.next == true}">
										<li><a href="${pageContext.request.contextPath}/rboard/list?crtPage=${pMap.endPageNum+1}&keyword=${pMap.keyword}">▶</a></li>
									</c:if>
								</ul>
							</c:otherwise>
						</c:choose>
							
							
							<div class="clear"></div>
						</div>
						<c:if test="${authUser != null}">
							<a id="btn_write" href="./writeform/0/0/0">글쓰기</a>
						</c:if>
					</div>
					<!-- //list -->
				</div>
				<!-- //board -->
			</div>
			<!-- //content  -->

		</div>
		<!-- //container  -->
		

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>

</html>
