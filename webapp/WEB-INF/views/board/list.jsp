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
						<form action="./list" method="get">
							<div class="form-group text-right">
								<input type="text" name="title" value="">
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
							<c:choose>
								<c:when test="${sList == null}">
									<c:forEach items="${bList}" var="bVo" varStatus="status">
										<tr>
											<td>${bVo.no}</td>
											<td><a href="${pageContext.request.contextPath}/board/read/${bVo.no}">${bVo.title}</a></td>
											<td>${bVo.name}</td>
											<td>${bVo.hit}</td>
											<td>${bVo.regDate}</td>
											<c:if test="${authUser.no == bVo.userNo}">
												<td><a href="./delete/${bVo.no}">[삭제]</a></td>
											</c:if>
										</tr>
									</c:forEach>
								</c:when>
								<c:when test="${sList != null}">
									<c:forEach items="${sList}" var="sVo" varStatus="status">
										<tr>
											<td>${sVo.no}</td>
											<td><a href="${pageContext.request.contextPath}/board/read/${sVo.no}">${sVo.title}</a></td>
											<td>${sVo.name}</td>
											<td>${sVo.hit}</td>
											<td>${sVo.regDate}</td>
											<c:if test="${authUser.no == sVo.userNo}">
												<td><a href="./delete/${sVo.no}">[삭제]</a></td>
											</c:if>
										</tr>
									</c:forEach>
								</c:when>
							</c:choose>
							</tbody>
						</table>
			
						<div id="paging">
							<ul>
								<li><a href="">◀</a></li>
								<li class="active"><a href="">1</a></li>
								<li><a href="">2</a></li>
								<li><a href="">3</a></li>
								<li><a href="">4</a></li>
								<li><a href="">5</a></li>
								<li><a href="">6</a></li>
								<li><a href="">7</a></li>
								<li><a href="">8</a></li>
								<li><a href="">9</a></li>
								<li><a href="">10</a></li>
								<li><a href="">▶</a></li>
							</ul>
							
							
							<div class="clear"></div>
						</div>
						<c:if test="${authUser != null}">
							<a id="btn_write" href="./writeform">글쓰기</a>
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
