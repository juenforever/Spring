
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<title>게시글 등록</title>

<%@include file="/WEB-INF/views/common/basicLib.jsp"%>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/se2/js/HuskyEZCreator.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/se2/photo_uploader/plugin/hp_SE2M_AttachQuickPhoto.js"
	charset="utf-8"></script>

<script>
	$(document)
			.ready(
					function() {

						var editor_object = [];
						nhn.husky.EZCreator
								.createInIFrame({
									oAppRef : editor_object,
									elPlaceHolder : "post_content",
									sSkinURI : "${pageContext.request.contextPath }/se2/SmartEditor2Skin.html"

								});

						$("#postRegisterBtn").click(
								function() {
									editor_object.getById["post_content"].exec(
											"UPDATE_CONTENTS_FIELD", []);
									$("#frm1").submit();
								})

						$("#fileRegisterBtn").on("click", function() {
							$("#frm2").submit();
						});

						function pasteHTML(filepath) {
							var sHTML = '<img src="${pageContext.request.contextPath }
	/path에서 설정했던 경로/'
									+ filepath + '">';
							oEditors.getById["post_content"].exec("PASTE_HTML",
									[ sHTML ]);
						}

					});
</script>
</head>

<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<%@include file="/WEB-INF/views/common/left.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">
							게시글 신규 등록<br> <br> postRegister.jsp<br> <br>
						</h2>
						<pre>
board_id : ${board_id }<br><br>
	</pre>
						<form id="frm1" class="form-horizontal" role="form"
							action="${pageContext.request.contextPath }/post/register"
							method="post" enctype="multipart/form-data">
							<input type="hidden" name="board_id" value="${board_id }" />
							board_id = ${board_id }
							<div class="form-group">
								<label for="post_title" class="col-sm-2 control-label">제목</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="post_title">
								</div>
							</div>
							<div class="form-group">
								<label for="post_content" class="col-sm-2 control-label">글내용</label>
								<div class="col-sm-10">
									<textarea class="form-control" id="post_content"
										name="post_content" rows="10" cols="50"></textarea>
								</div>
							</div>
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">첨부파일</label>
								파일추가/제거 <input type="button" value="파일추가" id="addFileBtn">
								<div class="col-sm-10" id="fileArea">
									<input type="file" name="upfile1" multiple><br> <input
										type="hidden" name="board_id" value="${board_id }">
								</div>
							</div>
						</form>
						<button id="postRegisterBtn" type="submit" class="btn btn-default">글등록</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

<script>
	var form = document.forms[0];
	var addFileBtn = document.getElementById("addFileBtn");
	var delFileBtn = document.getElementById("delFileBtn");
	var fileArea = document.getElementById("fileArea");
	var cnt = 2;

	form.onsubmit = function() {
		event.preventDefault();
	};
	addFileBtn.onclick = function() {
		if (cnt <= 5) {
			var element = document.createElement("input");
			element.type = "file";
			element.name = "upfile" + cnt;
			cnt++;
			fileArea.appendChild(element);
			fileArea.appendChild(document.createElement("br"));
		} else {
			alert("파일은 5개까지만 추가 가능합니다");
		}

	};
	delFileBtn.onclick = function() {
		if (cnt > 1) {
			cnt--;
			var inputs = fileArea.getElementsByTagName('input');
			fileArea.removeChild(inputs[cnt].nextElementSibling);
			fileArea.removeChild(inputs[cnt]);
		}
	}
</script>
</html>





