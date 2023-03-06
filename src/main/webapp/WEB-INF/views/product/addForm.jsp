<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../lay/header.jsp"%>
<script src="${contextPath}/resource/js/product/image.js"></script>
<div class="container">
	<div class="jumbotron review">
	
	
	</div>
	
	
	<div class="container">
		<div class="jumbotron preview">
				<h1>물건 등록</h1>
		</div>
	
	<form action="${contextPath}/product/add" method="post" enctype="multipart/form-data">
		<div class="form-group" >
			물품 분류: <select name="class1">
						    <option value="티셔츠">티셔츠</option>
						    <option value="점퍼">점퍼</option>
						    <option value="니트">니트</option>
						    <option value="가디건">가디건</option>
						    <option value="코트">코트</option>
						    <option value="셔츠">셔츠</option>
						    <option value="팬츠">팬츠</option>
						    <option value="자켓">자켓</option>
						    <option value="수트">수트</option>
						    <option value="언더/라운지웨어">언더/라운지웨어</option>
						</select>
		</div>
		<div class="form-group" >
			물품명: <input type="text"class="form-control" name="name" >
		</div>
		<div class="form-group" >
			내용: <textarea rows="10" class="form-control" name="content"></textarea>
		</div>
		<div class="form-group" >
			작성자: <input type="text" class="form-control" name="writer" value="${ok.id}" readonly="readonly">
		</div>
		<div class="form-group" >
			물품 수량: <input type="text" class="form-control" name="currentmount" >
		</div>
		<div class="form-group" >
			물품 가격: <input type="text" class="form-control" name="price" >
		</div>
		<div class="form-group" >
			이미지: <input type="file" class="form-control" name="imageFileName" >
		</div>
		 <img src="images/card-image.png" alt="" />
       
		<button class="btn btn-primary">물품 등록</button>
	</form>

</div>


</div>

<%@ include file="../lay/footer.jsp"%>