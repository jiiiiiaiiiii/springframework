<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/views/common/top.jsp" %>

<div class="card">
	<div class="card-header">로그인 양식</div>
	<div class="card-body">
		<!-- test: if문의 조건식 => else문 관련 태그는 없음 -->
		<c:if test="${login == null}">	
			<%-- test: 조건식 --%>
			<form class="m-2" method="post" action="login">
				<div class="form-group mb-2">
					<label for="mid">아이디</label> 
					<input type="text" class="form-control" id="mid" name="mid">
				</div>

				<div class="form-group mb-2">
					<label for="mpassword">패스워드</label> 
					<input type="password" class="form-control" id="mpassword" name="mpassword">
				</div>

				<button type="submit" class="btn btn-info btn-sm">로그인</button>
			</form>
		</c:if>
		
		<c:if test="${login != null}">
			<a href="logout" class="btn btn-success btn-sm">로그아웃</a>
		</c:if>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/bottom.jsp"></jsp:include>