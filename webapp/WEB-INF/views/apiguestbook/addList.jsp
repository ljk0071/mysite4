<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/assets/css/mysite.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js"></script>
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
			<c:import url="/WEB-INF/views/include/aside.jsp"></c:import>
			<!-- //aside -->

			<div id="content">

				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<%-- 					<form action="${pageContext.request.contextPath}/api/guestbook/add" method="post"> --%>
					<table id="guestAdd">
						<colgroup>
							<col style="width: 70px;">
							<col>
							<col style="width: 70px;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<td><label class="form-text" for="input-uname">이름</label></td>
								<td><input id="input-uname" type="text" name="name"
									value=""></td>
								<td><label class="form-text" for="input-pass">패스워드</label></td>
								<td><input id="input-pass" type="password" name="password"
									value=""></td>
							</tr>
							<tr>
								<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
							</tr>
							<c:if test="${param.result == 'fail'}">
								<p style="color: red">빈칸을 모두 채워주세요.</p>
							</c:if>
							<tr class="button-area">
								<td colspan="4" class="text-center"><button id="btnSubmit"
										type="submit">등록</button></td>
							</tr>
						</tbody>

					</table>

					<!-- 					</form>	 -->
					<div id="list">
						<!-- js영역 -->
					</div>
					<!-- //guestRead -->


				</div>
				<!-- //guestbook -->

			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>

<script type="text/javascript">
	$(document).ready(function() {
		fetchList();
	});

	$("#btnSubmit").on("click", function() {

		var name = $("[name='name']").val();
		var password = $("[name='password']").val();
		var content = $("[name=content]").val();

		//데이터 객체로 묶기
		var gVo = {
			name : name,
			password : password,
			content : content
		};
		$.ajax({

			url : "${pageContext.request.contextPath}/api/guestbook/add",
			type : "post",
			// 		contentType : "application/json",
			data : gVo,

			dataType : "json",
			success : function(gVo) {

				render(gVo, "up");

				$("[name='name']").val("");
				$("[name='password']").val("");
				$("[name='content']").val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});

	function fetchList() {
		$.ajax({

			url : "${pageContext.request.contextPath}/api/guestbook/list",
			type : "post",
			// 		contentType : "application/json",
			// 		data : {name: ”홍길동"},

			dataType : "json",
			success : function(gList) {

				for (var i = 0; i < gList.length; i++) {
					render(gList[i], "down");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}

	function render(gVo, opt) {
		// 	$("#list").append(gVo.name + "<br>")
		var str = "";
		str += "<table class='guestRead'>";
		str += "	<colgroup>";
		str += "		<col style='width: 10%;'>";
		str += "		<col style='width: 40%;'>";
		str += "		<col style='width: 40%;'>";
		str += "		<col style='width: 10%;'>";
		str += "	</colgroup>";
		str += "	<tr>";
		str += "		<td>" + gVo.no + "</td>";
		str += "		<td>" + gVo.name + "</td>";
		str += "		<td>" + gVo.regDate + "</td>";
		str += "		<td><a href='./deleteform?no=" + gVo.no
				+ " target='balnk'>[삭제]</a></td>";
		str += "	</tr>";
		str += "	<tr>";
		str += "		<td colspan=4 class='text-left'>" + gVo.content + "</td>";
		str += "	</tr>";
		str += "</table>";

		if (opt == "down") {
			$("#list").append(str);
		} else if (opt == "up") {
			$("#list").prepend(str);
		} else {
			console.log("opt오류");
		}
	}
</script>

</html>