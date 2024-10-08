<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>

<%-- @include 지시자의 역할: 외부의 파일의 '내용'(코드)을 가져와서 삽입시켜줌 = 소스코드 복사 --%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>

<div class="card">
	<div class="card-header">로그인한 사용자 정보 보기</div>
	<div class="card-body">
		<p>mid: ${member.mid}</p>
		<p>mname: ${member.mname}</p>
		<p>mpassword: ${member.mpassword}</p>
		<p>menabled: ${member.menabled}</p>
		<p>mrole: ${member.mrole}</p>
		<p>memail: ${member.memail}</p>
	</div>
</div>

<!-- include 액션의 역할: 외부의 JSP를 '실행하고 그 결과'를 삽입시켜줌 = 실행결과만 -->
<jsp:include page="/WEB-INF/views/common/bottom.jsp"></jsp:include>