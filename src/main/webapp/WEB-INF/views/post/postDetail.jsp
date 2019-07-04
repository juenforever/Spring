
<%@page import="kr.or.ddit.paging.model.PageVo"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<title>사용자 상세조회</title>
<%@include file="/WEB-INF/views/common/basicLib.jsp"%>
<script>
	$(document).ready(function() {

		$("#postModifyBtn").on("click", function() {
			$("#postModifyfrm").submit();
		});
		$("#postDeleteBtn").on("click", function() {
			$("#postDeletefrm").submit();
		});
		$("#postReBtn").on("click", function() {
			$("#postRefrm").submit();
		});
		$("#replyBtn").on("click", function() {
			$("#replyfrm").submit();
		})
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
					<div class="c blog-main">
						<h2 class="sub-header">게시글 상세조회 postDetail.jsp</h2>
						<pre>
						board_id : ${board_id } <br><br> 
						post_id : ${post_id }<br><br> 
						replyList : ${replyList }<br><br>
						attachmentList : ${attachmentList }<br><br> 
						postVo : ${postVo }<br><br>
						</pre>
						
						<div class="form-group col-sm-8">
							<label for="userNm" class="col-sm-2 control-label">제목</label>
							<div class="col-sm-10">
								<p>${postVo.post_title}</p>
								<!-- 								<input type="text" class="form-control" id="userId" -->
								<%-- 									name="userId" value="${postVo.post_title}"> --%>
							</div>
						</div>
						<div class="form-group col-sm-8">
							<label for="userNm" class="col-sm-2 control-label">글내용</label>
							<div class="col-sm-10">
								<label class="control-label">${postVo.post_content}</label>
							</div>
						</div>
						<div class="form-group col-sm-8">
							<label for="userNm" class="col-sm-2 control-label">첨부파일</label>
							<div class="col-sm-10">
								<c:forEach items="${attachmentList}" var="attachment"><br>
									<label for="attachmentName" class="col-sm-5 control-label">
										<a href="${pageContext.request.contextPath }/attachmentDownload?board_id=${board_id}&post_id=${post_id }&attachment_id=${attachment.attachment_id }"
										   class=" btn btn-default pull-right">다운로드</a>${attachment.attachment_name }
									</label><br>
								</c:forEach>
							</div>
						</div>
						<div class="form-group col-sm-8">
							<label for="userNm" class="col-sm-2 control-label">댓글</label>
							<div class="col-sm-10">
								<c:forEach items="${replyList}" var="reply"><br>
									<label for="replyName" class="col-sm-10 control-label">${reply.reply_content }<br>
									[작성자 : ${reply.userId } 작성시간 : <fmt:formatDate	value="${reply.reply_time }" pattern="yyyy년MM월dd일hh시mm분ss초" />]<br>
									</label>
									${attachment.attachment_id}
									<c:if test="${reply.userId eq userId }">
										<a href="${cp}/reply/delete?reply_id=${reply.reply_id }&post_id=${post_id }&board_id=${board_id }"	
										   class=" btn btn-default pull-right">X</a><br>
									</c:if>
								</c:forEach>
							</div>
						</div>
						<div class="form-group col-sm-8">
							<label for="userNm" class="col-sm-2 control-label"></label>
							<div class="col-sm-10">
								<form id="replyfrm" method="post"
									action="${cp}/reply/register">
									<div id="reply_box">
										<textarea name="reply_content" maxlength="500"></textarea>
										<input type="hidden" name="post_id" value="${post_id}">
										<input type="hidden" name="board_id" value="${board_id }">
										<button id="replyBtn" type="submit" class="btn btn-default"	name="button">댓글저장</button>
									</div>
								</form>
							</div>
						</div>
						<div class="form-group col-sm-8 text-right">
							<form id="postModifyfrm" method="post" action="${cp}/post/modifyPath">
								<button id="postModifyBtn" type="button" class="btn btn-default" name="button">수정</button>
								<input type="hidden" value="${post_id }" name="post_id">
								<input type="hidden" value="${board_id }" name="board_id">
							</form>
							<c:if test="${postVo.userId eq userId }">
								<form id="postDeletefrm" method="post" action="${cp}/post/delete?board_id=${board_id}">
									<button id="postDeleteBtn" type="button" class="btn btn-default" name="button">삭제</button>
									<input type="hidden" value="${post_id }" name="post_id">
									<input type="hidden" value="${board_id }" name="board_id">
								</form>
							</c:if>
							<form id="postRefrm" method="get" action="${cp}/post/reply?board_id="${board_id }">
								<input type="hidden" name="board_id" value="${board_id }" /> 
								<input type="hidden" name="post_id" value="${post_id }" />
								<button id="postReBtn" type="button" class="btn btn-default" name="button">답글</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>





