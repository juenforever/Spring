
<%@page import="kr.or.ddit.paging.model.PageVo"%>
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
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>postPagingList</title>

<!-- css, js -->
<%@include file="/WEB-INF/views/common/basicLib.jsp"%>

<style>
.userTr:hover {
	cursor: pointer;
}
</style>
<script>
	$(document).ready(function() {
		$(".userTr").on("click", function() {
			console.log("userTr click");
			var post_id = $(this).find(".yaya").text();
			$("#post_id").val(post_id);
			$("#frm").submit();
		});
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
							postPagingList.jsp <br> <br>
						</h2>
						<pre>
board_id :${board_id} <br><br> 
postList : ${postList }<br><br>
pageVo : ${pageVo }<br><br>
paginationSize = ${paginationSize }
</pre>
						<form id="frm"
							action="${cp}/post/detail"
							method="post" enctype="multipart/form-data">
							<input type="hidden" class="post_id" id="post_id" name="post_id">


							<div class="table-responsive">
								<table class="table table-striped">
									<tr>
										<th>게시글 번호</th>
										<th>제목</th>
										<th>작성자 아이디</th>
										<th>작성일시</th>
									</tr>
									<c:set var="index" value="1"></c:set>
									<c:forEach items="${postList }" var="post" varStatus="status">
										<c:choose>
											<c:when test="${post.post_delete eq 'no'}">
												<tr class="userTr" data-userid="${post.post_id }">
													<td class="yaya">${post.post_id }</td>
													<td align="left"><c:forEach var="i" begin="1"
															end="${post.re_level}">
											&nbsp;&nbsp;
											
											</c:forEach> <c:if test="${post.re_level !=0}">ㄴ</c:if>${post.post_title }</td>
													<td>${post.userId }</td>
													<td><fmt:formatDate value="${post.post_time }"
															pattern="yyyy-MM-dd" /></td>

													<input type="hidden" name="board_id" value="${board_id }">
													<input type="hidden" class="post_id"
														value="${post.post_id }">
											</c:when>
											<c:otherwise>
												<tr class="delete"">
													<td>${post.post_id }</td>
													<td>삭제된 게시글입니다</td>
													<td></td>
													<td></td>
												</tr>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</table>
							</div>
							<a
								href="${pageContext.request.contextPath }/post/register?board_id=
								${board_id}"
								class=" btn
							btn-default pull-right">새글등록</a>

							<div class="text-center">
								<ul class="pagination">
									<c:choose>
										<c:when test="${pageVo.page == 1 }">
											<li class="disabled"><span>«</span></li>
										</c:when>
										<c:otherwise>
											<li><a
												href="${pageContext.request.contextPath}/post/pagingList?page=${pageVo.page - 1}&pageSize=${pageVo.pageSize}&board_id=${board_id}">«</a>
											</li>
										</c:otherwise>
									</c:choose>
									<c:forEach begin="1" end="${paginationSize}" var="i">
										<c:choose>
											<c:when test="${pageVo.page == i}">
												<li class="active"><span>${i}</span></li>
											</c:when>
											<c:otherwise>
												<li><a
													href="${pageContext.request.contextPath}/post/pagingList?page=${i}&pageSize=${pageVo.pageSize}&board_id=${board_id}">${i}</a>
												</li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<c:choose>
										<c:when test="${pageVo.page == paginationSize}">
											<li class="disabled"><span>»</span></li>
										</c:when>
										<c:otherwise>
											<li><a
												href="${pageContext.request.contextPath}/post/pagingList?page=${pageVo.page+1}&pageSize=${pageVo.pageSize}&board_id=${board_id}">»</a>
											</li>
										</c:otherwise>
									</c:choose>
								</ul>
							</div>
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
