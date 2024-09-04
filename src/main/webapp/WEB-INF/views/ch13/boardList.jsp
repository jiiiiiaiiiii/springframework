<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <!-- formatting -->

<%@ include file="/WEB-INF/views/common/top.jsp" %>

<div class="card">
	<div class="card-header">게시물 목록</div>
	<div class="card-body">
		<table class="table table-sm table-bordered">
			<tr>
				<th style="width: 30px">번호</th>
				<th style="width: 300px">제목</th>
				<th style="width: 70px">글쓴이</th>
				<th style="width: 70px">날짜</th>
				<th style="width: 70px">조회수</th>
			</tr>
			<c:forEach items="${list}" var ="board">
				<tr>
					<th>${board.bno}</th>
					<th>${board.btitle}</th>
					<th>${board.mid}</th>
					<th><fmt:formatDate value="${board.bdate}" pattern="yyyy-MM-dd"/></th>
					<th>${board.bhitcount}</th>
				</tr>			
			</c:forEach>
		</table>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/bottom.jsp"></jsp:include>