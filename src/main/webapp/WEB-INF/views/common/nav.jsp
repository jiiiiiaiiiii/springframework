<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
 <%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  
   
<nav class="navbar navbar-dark bg-dark">
	<div class="ms-2">
		<a class="navbar-brand" href="${pageContext.request.contextPath}">
			<img src="${pageContext.request.contextPath}/resources/image/logo-spring.png" alt="Logo" width="40" class="d-inline-block align-text-top">
			<span class="align-middle">전자정부프레임워크(Spring FrameWork)</span>
		</a>
	</div>			
	<div class="me-2">
		<%-- <c:if test="${login == null}">
			<a class="btn btn-success btn-sm" href="${pageContext.request.contextPath}/ch08/login">로그인</a>
			<a class="btn btn-success btn-sm" href="${pageContext.request.contextPath}/ch13/loginForm">로그인</a>
		</c:if>
		<c:if test="${login != null}">
			<img width="40" src="${pageContext.request.contextPath}/resources/image/login.png" />
			<span class="text-white m-2">${login.mid}</span>
			<a class="btn btn-danger btn-sm" href="${pageContext.request.contextPath}/ch08/logout">로그아웃</a>
			<a class="btn btn-danger btn-sm" href="${pageContext.request.contextPath}/ch13/logout">로그아웃</a>
		</c:if> --%>
		
		<sec:authorize access="isAnonymous()">	<!-- 익명인지?(=로그인 안했을 때) -->
			<a class="btn btn-success btn-sm" href="${pageContext.request.contextPath}/ch17/loginForm">로그인</a>
		</sec:authorize>
		
		<sec:authorize access="isAuthenticated()">	<!-- 인증되었는지?(=로그인 했을 때) -->
			<img width="40" src="${pageContext.request.contextPath}/resources/image/login.png" />
			<span class="text-white m-2"><sec:authentication property="principal.username"/></span>	<!-- 로그인한 사용자의 아이디 -->
			<a class="btn btn-danger btn-sm" href="${pageContext.request.contextPath}/logout">로그아웃</a>
		</sec:authorize>
	</div>
</nav>