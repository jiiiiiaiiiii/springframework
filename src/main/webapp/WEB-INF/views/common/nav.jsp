<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
   
<nav class="navbar navbar-dark bg-dark">
	<div class="ms-2">
		<a class="navbar-brand" href="${pageContext.request.contextPath}">
			<img src="${pageContext.request.contextPath}/resources/image/logo-spring.png" alt="Logo" width="40" class="d-inline-block align-text-top">
			<span class="align-middle">전자정부프레임워크(Spring FrameWork)</span>
		</a>
	</div>			
	<div class="me-2">
		<c:if test="${login == null}">
			<%-- <a class="btn btn-success btn-sm" href="${pageContext.request.contextPath}/ch08/login">로그인</a> --%>
			<a class="btn btn-success btn-sm" href="${pageContext.request.contextPath}/ch13/loginForm">로그인</a>
		</c:if>
		<c:if test="${login != null}">
			<img width="40" src="${pageContext.request.contextPath}/resources/image/login.png" />
			<span class="text-white m-2">${login.mid}</span>
			<%-- <a class="btn btn-danger btn-sm" href="${pageContext.request.contextPath}/ch08/logout">로그아웃</a> --%>
			<a class="btn btn-danger btn-sm" href="${pageContext.request.contextPath}/ch13/logout">로그아웃</a>
		</c:if>
	</div>
</nav>