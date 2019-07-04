
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>게시글 등록</title>

<!-- css, js -->
<%@include file="/WEB-INF/views/common/basicLib.jsp"%>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>



</head>

<body>
	<h1>다중파일업로드</h1>
	<fieldset>
		<form>
			아이디:<input type="text" name="id"><br> 성명:<input
				type="text" name="name"><br> 나이:<input type="text"
				name="age"><br> 파일 추가/제거: <input type="button"
				value="파일추가" id="addFileBtn"> <input type="button"
				value="파일제거" id="delFileBtn">
			<hr>
			<div id="fileArea">
				<input type="file" name="upfile1"><br>
			</div>
			<input type="submit" value="저장">
		</form>
	</fieldset>


</body>
<script>
	var form = document.forms[0];
	var addFileBtn = document.getElementById("addFileBtn");
	var delFileBtn = document.getElementById("delFileBtn");
	var fileArea = document.getElementById("fileArea");
	var cnt = 2;

	form.onsubmit = function() {
		event.preventDefault();

		var inputs = fileArea.getElementsByTagName('input');
		for (var i = 0; i < inputs.length; i++) {
			if (inputs[i].value == "") {
				alert('파일을 선택하세요!');
				inputs[i].focus();
				return;
			}
		}

		this.action = "postReply";
		this.method = "post";
		this.enctype = "multipart/form-data";
		this.submit();

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





