<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="nav">
			<ul class="clearfix">
				<li><a href="">입사지원서</a></li>
				<li><a href="${pageContext.request.contextPath}/rboard/list">게시판</a></li>
				<li><a href="${pageContext.request.contextPath}/gallery/list">갤러리</a></li>
				<li><a href="${pageContext.request.contextPath}/guest/list">방명록</a></li>
			</ul>
	</div>
</body>
</html>