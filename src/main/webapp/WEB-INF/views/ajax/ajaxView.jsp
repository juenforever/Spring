<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<script>
	$(document).ready(function() {
		console.log("ready");

		$("#requestData").on("click", function() {
			$.ajax({
				url : "/ajax/requestData",
				method : "post",
				success : function(data) {
					$("#page").text(data.pageVo.page);
					$("#pageSize").text(data.pageVo.pageSize);
				}
			});
		});
		//requestDataResponseBody 클릭시 이벤트 핸들러
		$("#requestData").on("click", function() {
			$.ajax({
				url : "/ajax/requestDataResponseBody",
				method : "post",
				success : function(data) {
					$("#pageResponseBody").text(data.page);
					$("#pageSizeResponseBody").text(data.pageSize);
				}
			});
		});
		
		//user클릭 시 이벤트 핸들러
		$("#user").on("click", function(){
			$.ajax({
				url:"/ajax/user",
				method: "post",
				data:"userId="+$("#userId").val(),
				success:function(data){
					/*
					name : <input type = "text" id  ="name" readonly/><br><br>
					alias : <input type = "text" id = "alias" readonly/><br><br>
					birth : <input type = "text" id = "birth" readonly/>
					$("#name").val(data.userVo.name);
					$("#alias").val(data.userVo.alias);				
					$("#birth").val(data.userVo.birth);				
					*/
					
					var html = "";
					html+="name : <input type =\"text\" id=\"name\" readonly value=\""+data.userVo.name+"\" />";
					html+="alias : <input type =\"text\" id=\"alias\" readonly value=\""+data.userVo.alias+"\" />";
					html+="birth : <input type =\"text\" id=\"birth\" readonly value=\""+data.userVo.birth+"\" />";
					
					$("#userJsonInfo").html(html);
				}
			});
		});
		//user클릭 시 이벤트 핸들러
		$("#userHtml").on("click", function(){
			$.ajax({
				url:"/ajax/userHtml",
				method: "post",
				data : $("#frm").serialize(),
				success: function(data){
					$("#userInfo").text(data);
				}
			});
		});
		
		//전송할 json 객체를 준비
		var user = {userId : "brown", pass = "brown1234"};
		$("#userJsonString").text(JSON.stringfy(user));
	});
	
</script>
<pre>ajaxView.jsp</pre>
<h2>ajax json 데이터 요청</h2>
<a id="requestData">데이터 가져오기</a><br><br>
page:<span id="page"></span><br>
pageSize:<span id="pageSize"></span>

<h2>ajax json 데이터 요청</h2>
<a id="requestDataResponseBody">데이터 가져오기</a><br><br>
page:<span id="pageResponseBody"></span><br>
pageSize:<span id="pageSizeResponseBody"></span>

<h2>ajax json 데이터 요청(user)</h2>
<a id = "user">데이터가져오기</a><br>
userId : <input type = "text" id = "userId" value = "brown"/><br><br>
<div id = "userJsonInfo"></div>;

<h2>ajax html 데이터 요청 (user)</h2>
<a id = "userHtml">데이터 가져오기</a><br>
<form id = "frm">
userId : <input type = "text" id = "userIdHtml" name = "userId" value = "brown"/>
</form>
<div id = "userInfo"></div>

<h2>ajax json 데이터 보내기</h2>
<a id = "userJsonStringBtn">데이터 보내기</a>
요청 보내내는 데이터 : <div id = "userJsonString"></div>
받는 데이터 : <div id = ""></div>