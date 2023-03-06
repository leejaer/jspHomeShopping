<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../lay/header.jsp" %>

<div class="container">
	 <div class="jumbotron">
			<h1>주문 리스트</h1>
	</div>
	<table class="table">
		<tr>
			<td>주문번호</td>
			<td>주문아이디</td>
			<td>수량</td>
			<td>물건번호</td>
			<td>물건가격</td>
			<td>주문취소</td>
		</tr>
		<c:forEach items="${b}" var="b">
				<tr>
					<td>${b.pbno}</td>
					<td>${b.id}</td>
					<td>${b.cnt}</td>
					<td>${b.pno}</td>
					<td>${b.price}</td>
					<td>
					<form action="${contextPath}/product/billCanlcel" method="post">
					<input type="hidden" value="${b.pbno}" name="pbno">
					<input type="hidden" value="${b.id}" name="id">
					<input type="hidden" value="${b.cnt}" name="cnt">
					<input type="hidden" value="${b.price}" name="price">
						<button>취소</button>
					</form>
					</td>
				</tr>
		</c:forEach>
	</table>
<a href="${contextPath}/main" class="btn btn-primary">메인으로</a>
</div>
	

<%@ include file="../lay/footer.jsp" %>

<script>

</script>
