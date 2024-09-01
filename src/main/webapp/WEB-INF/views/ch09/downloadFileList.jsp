<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/views/common/top.jsp" %>

<div class="card">
	<div class="card-header">파일 다운로드 목록</div>
	<div class="card-body">
		<ul>
			<c:forEach items="${fileNames}" var="fileName">
				<li class="rounded-circle">
					<!-- 사용자가 올린 이미지는 정적 파일이 아님, webapp밖에 위치하기 때문에 다운로드 형태로 -->
					<img src="downloadFile?fileName=${fileName}" width="30" height="30" />
					<a href="downloadFile?fileName=${fileName}">${fileName}</a>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/bottom.jsp"></jsp:include>