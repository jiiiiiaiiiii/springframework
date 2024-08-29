<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/views/common/top.jsp" %>

<div class="card">
	<div class="card-header">session에 저장된 데이터 이용</div>
	<div class="card-body">
		<div class="m-3">
			<p>mid: ${member.mid}</p>
			<p>mname: ${member.mname}</p>
			<p>memail: ${member.memail}</p>
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/bottom.jsp"></jsp:include>