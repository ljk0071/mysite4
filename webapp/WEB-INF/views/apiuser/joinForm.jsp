<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js"></script>

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
					<h3>회원가입</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>회원</li>
							<li class="last">회원가입</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->
	
				<div id="user">
					<div id="joinForm">
						<!-- <form action="./joinOk" method="post"> -->
							<c:if test="${param.result == 'fail'}">
								<p style="color:red">빈칸을 전부 채워주세요.</p>
							</c:if>
								
							<!-- 아이디 -->
							<div class="form-group">
								<label class="form-text" for="input-uid">아이디</label> 
								<input type="text" id="input-uid" name="id" value="" placeholder="아이디를 입력하세요">
								<button type="button" id="ovrlapchk">중복체크</button>
							</div>
	
							<!-- 비밀번호 -->
							<div class="form-group">
								<label class="form-text" for="input-pass">패스워드</label> 
								<input type="password" id="input-pass" name="pw" value="" placeholder="비밀번호를 입력하세요"	>
							</div>
	
							<!-- 이메일 -->
							<div class="form-group">
								<label class="form-text" for="input-name">이름</label> 
								<input type="text" id="input-name" name="name" value="" placeholder="이름을 입력하세요">
							</div>
	
							<!-- //나이 -->
							<div class="form-group">
								<span class="form-text">성별</span> 
								
								<label for="rdo-male">남</label> 
								<input type="radio" id="rdo-male" name="gender" value="male" > 
								
								<label for="rdo-female">여</label> 
								<input type="radio" id="rdo-female" name="gender" value="female" > 
	
							</div>
	
							<!-- 약관동의 -->
							<div class="form-group">
								<span class="form-text">약관동의</span> 
								
								<input type="checkbox" id="chk-agree" value="agree" name="terms">
								<label for="chk-agree">서비스 약관에 동의합니다.</label> 
							</div>
							<!-- 버튼영역 -->
							<div class="button-area">
								<button type="submit" id="btn-submit">회원가입</button>
							</div>
							
						<!-- </form> -->
						
					</div>
					<!-- //joinForm -->
				</div>
				<!-- //user -->
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

	var chk = 0;

	$("#btn-submit").on("click", function() {
		
		var id = $("[name='id']").val();
		var pw = $("[name='pw']").val();
		var name = $("[name='name']").val();
		var gender = $("[name='gender']").val();
		var agree = $("#chk-agree").is(":checked");
		var uVo = {}
		uVo.id = id;
		uVo.pw = pw;
		uVo.name = name;
		uVo.gender = gender;
		
		if ( pw == "" || name == "" || gender == "") {
			alert("빈 칸을 모두 채워주세요");
		} else if( agree == false ) {
			alert("약관에 동의해주세요");
		} else {
			if ( chk == 1 ) {
				$.ajax({
					
					url : "${pageContext.request.contextPath}/api/joinCheck",
					type : "post",
			 		contentType : "application/json",
					data : JSON.stringify(uVo),
			
					dataType : "json",
					success : function(cnt) {
						if (cnt == 1) {
							window.location.href = "${pageContext.request.contextPath}/api/joinOk";
						}
					},
					error : function(XHR, status, error) {
						console.error(status + " : " + error);
					}
				});
			}
			else {
				alert("아이디 중복검사 후 시도해주세요");		
			}
		}
		
		
		
	});

	$("#ovrlapchk").on("click", function() {

		var id = $("[name='id']").val();
		
		$.ajax({
			
			url : "${pageContext.request.contextPath}/api/ovrlapchk",
			type : "post",
			data : id,
	
			dataType : "json",
			success : function(result) {
				if ( result == 1 ) {
					alert("사용 가능한 아이디입니다");
					return chk = 1;
				} else {
					alert("중복된 아이디입니다");
					return chk = -1;
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
</script>

</html>