<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%-- jstl 라이브러리
 - 표준태그 라이브러리
 - jsp 안에 자바 코드 작성 지양 -> jstl 이용(if, for문 등 자바코드와 관련된 태그를 갖고 있음)
 - prefix: custom으로 작성하나, 관례적으로 제어(control)관련 사항 => "c"로 작성 
 --%>

<%@ include file="/WEB-INF/views/common/top.jsp" %>

<div class="card">
	<div class="card-header">장바구니</div>
	<div class="card-body">
		<ul>
		
		<%-- items: 배열, 리스트 등을 참조
				- cart: session에 저장된 key값
				- var의 타입: cart에 저장된 객체 = Ch06Item
				- ${key값. 필드} <= getter에서 return 받은 값 
		--%>
		<c:forEach items="${cart.contents}" var="item">
			<li class="m-2">
				<span>${item.pname}</span> <!-- ${el}: 바로 필드로 접근하는 형태로 작성하나, getter에서 return받은 값이 저장됨 -->
				<a href="deleteItem?pno=${item.pno}" class="btn btn-danger btn-sm">삭제</a>
			</li>
		</c:forEach>
		</ul>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/bottom.jsp"></jsp:include>