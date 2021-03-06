<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>


</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		
		<c:import url="/WEB-INF/views/include/nav.jsp"></c:import>
		<!-- //nav -->

		<c:import url="/WEB-INF/views/include/galleryAside.jsp"></c:import>
		<!-- //aside -->


		<div id="content">

			<div id="content-head">
				<h3>갤러리</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>갤러리</li>
						<li class="last">갤러리</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->


			<div id="gallery">
				<div id="list">
			
					
						<c:if test="${authUser != null}">
							<button id="btnImgUpload">이미지올리기</button>
						</c:if>
						<div class="clear"></div>

			
					<ul id="viewArea">
						<!-- 이미지반복영역 -->
						<c:forEach items="${gList}" var="gVo">
							<li id="listItem${gVo.no}">
								<div class="view">
									<img data-content="${gVo.content}" data-userno="${gVo.userNo}" data-no="${gVo.no}" id="imgItem${gVo.no}" class="imgItem" src="${pageContext.request.contextPath}/upload/${gVo.saveName}">
									<div class="imgWriter">작성자:${gVo.name}</div>
									<input type="hidden" name="authUserNo" value="${authUser.no}">
								</div>
							</li>
						</c:forEach>
						<!-- 이미지반복영역 -->
						
						
					</ul>
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

	
		
	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지등록</h4>
				</div>
				
				<form method="post" action="./upload" enctype="multipart/form-data" >
					<div class="modal-body">
						<div class="form-group">
							<label for="addModalContent" class="form-text">글작성</label>
							<input id="addModalContent" type="text" name="content" value="" >
							<input type="hidden" name="no" value="${authUser.no}">
						</div>
						<div class="form-group">
							<label for="file" class="form-text">이미지선택</label>
							<input id="file" type="file" name="img" value="" >
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn" id="btnUpload">등록</button>
					</div>
				</form>
				
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	


	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewModal">
		<div class="modal-dialog" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">
					
					<div class="formgroup">
						<img id="viewModelImg" src =""> <!-- ajax로 처리 : 이미지출력 위치-->
					</div>
					
					<div class="formgroup">
						<p id="viewModelContent"></p>
					</div>
					
				</div>
					<div class="modal-footer">
						<input type="hidden" name="no" value="">
						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
						<button type="button" class="btn btn-danger" id="btnDel">삭제</button>
					</div>
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->	


</body>

<script type="text/javascript">



</script>
<script type="text/javascript">

	var gNo;
	

	$("#list").on("click","#btnImgUpload", function() {
		$("[name='content']").val("");
		$("[name='img']").val("");
		$("#addModal").modal("show");
	})
	
	$("#viewArea").on("click",".imgItem", function() {
		var authUserNo = $("[name='authUserNo']").val();
		var $this = $(this);
		var no = $this.data("no");
		gNo = no;
		var userNo = $this.data("userno");
		var content = $this.data("content");
		var gVo = $("#imgItem"+no).attr("src");
		$("#viewModelImg").attr("src", gVo);
		$("#viewModelContent").text(content);
		if( userNo != authUserNo) {
			$("#btnDel").hide();
		} else {
			$("#btnDel").show();
		}
		$("#viewModal").modal("show");
	})
	
	$("#viewModal").on("click", "#btnDel", function() {
		$.ajax({

			url : "${pageContext.request.contextPath}/gallery/delete",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(gNo),

			dataType : "json",
			success : function(result) {
				if (result == -1) {
					console.log("실패")
				} else {
					$("#listItem"+gNo).remove();
					$("#viewModal").modal("hide");
					console.log("성공")
				}
					
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	})
	
	

	
</script>




</html>

