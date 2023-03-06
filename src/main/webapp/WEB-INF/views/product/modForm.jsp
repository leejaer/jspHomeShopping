<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../lay/header.jsp" %>
<script src="${contextPath}/resource/js/product/image.js"></script>

<div class="container">

	<div class="jumbotron review">
    	<input type="hidden" name="imageFileName" class="form-control viewMode">
		<div class="my-2">
			<c:if test="${not empty product.image}">
				<div class="preview">
					<a href="${contextPath}/product/detail?pno=${product.pno}"><img class="originImg" src="${contextPath}/filedownload?no=${product.pno}&image=${product.image}&path=product"></a>
				</div>
			</c:if>
			<c:if test="${empty product.image}">
				<div class="preview">
					<p>등록된 파일이 없습니다</p>
				</div>
			</c:if>
		</div>
	</div>
	<div class="container">
				<h5>물품 상세정보</h5>
	<form action="${contextPath}/product/mod" method="post" enctype="multipart/form-data" class="productDetail">
		<div class="form-group"  >
			<h1>${product.class1}</h1>
		</div>
		<div class="form-group" >
			물품명: <input type="text"class="form-control" name="name"  value="${product.name}">
		</div>
		<div class="form-group" >
			내용: <textarea rows="10" class="form-control" name="content"  >${product.content}</textarea>
		</div>
		<div class="form-group" >
			작성자: <input type="text" class="form-control" name="writer" value="${product.writer}" readonly="readonly">
		</div>
		<div class="form-group" >
			물품 수량: <input type="text" class="form-control" name="currentmount" value="${product.currentmount}" >
		</div>
		<div class="form-group" >
			물품 가격: <input type="text" class="form-control" name="price" value="${product.price}">
		</div>
		<div class="form-group">
			이미지: <input type="file" class="form-control" name="imageFileName">
		</div>
		<input type="hidden" name="origin" value="${product.image}">
		<input type="hidden" name="pno" value="${product.pno}">
		<button>물품 수정</button>
		<button onclick="history.go(-1)">이전으로</button>
	</form>

</div>


</div>


<%@ include file="../lay/footer.jsp" %>