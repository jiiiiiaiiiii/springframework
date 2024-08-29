<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/views/common/top.jsp" %>

<div class="card">
	<div class="card-header">로그인 정보</div>
	<div class="card-body">
		<p>mid: ${login.mid}</p>
		<p>mname: ${login.mname}</p>
		<p>mpassword: ${login.mpassword}</p>
		<p>memail: ${login.memail}</p>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/bottom.jsp"></jsp:include>