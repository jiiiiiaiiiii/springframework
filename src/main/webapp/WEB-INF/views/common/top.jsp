<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link href="${pageContext.request.contextPath}/resources/bootstrap/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/bootstrap/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/jquery/jquery.min.js"></script>
</head>
<body>
	<div class="d-flex flex-column vh-100">
	
	<jsp:include page="/WEB-INF/views/common/nav.jsp"></jsp:include>
	
		<div class="flex-grow-1 m-2">
			<div class="d-flex row">
				<div class="col-md-4">
				
					<jsp:include page="/WEB-INF/views/common/menu.jsp"></jsp:include>

				</div>
				<div class="col-md-8">