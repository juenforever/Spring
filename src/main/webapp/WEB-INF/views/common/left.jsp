<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		<li class="active"><a href="${cp}/board/create?userId=${userId}">게시판관리</a></li>
		<li class="active"><a href="${cp}/main">Main <span class="sr-only">(current)</span></a></li>
		<li class="active"><a href="${cp}/user/list">사용자리스트</a></li>
		<li class="active"><a href="${cp}/user/pagingList">사용자페이징리스트</a></li>
		<li class="active"><a href="${cp}/lprod/pagingList">lprod 페이징	리스트</a></li>
		<li class="active"><a href="${cp}/user/pagingListAjaxView">사용자 페이징 리스트(ajax)</a></li>
	</ul>
</div>