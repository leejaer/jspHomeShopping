<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../lay/header.jsp" %>
<script src="${contextPath}/resource/js/product/image.js"></script>
<script src="${contextPath}/resource/js/product/del.js"></script>
<script src="${contextPath}/resource/js/product/basket.js"></script>
<script src="${contextPath}/resource/js/product/mod.js"></script>
<script src="${contextPath}/resource/js/product/buy.js"></script>
<script src="${contextPath}/resource/js/member/like.js"></script>
<script src="${contextPath}/resource/js/product/hashTag.js"></script>

<style>
	.preview a{
		display: block;
	}
	.preview a img{
		width: 100%;
		height: 1000px;
	}
</style>
<div class="container">
	<div class="jumbotron review">
		
    	<input type="hidden" name="imageFileName" class="form-control viewMode">
		<div class="my-2">
			<c:if test="${not empty product.image}">
				<input type="hidden" name="originFileName" value="${product.image}">
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
		<form action="#" method="post" class="productDetail">
			<div class="form-group"  >
				<h1>${product.class1}</h1>
			</div>
			<div class="form-group" >
				물품명: <input type="text"class="form-control name" name="name" readonly="readonly" value="${product.name}">
			</div>
			<div class="form-group" >
				내용: <textarea rows="10" class="form-control" name="content" readonly="readonly" >${product.content}</textarea>
			</div>
			<div class="form-group" >
				작성자: <input type="text" class="form-control" name="writer" value="${product.writer}" readonly="readonly">
			</div>
			<div class="form-group" >
				물품 가격: <input type="text" class="form-control" name="price" value="${product.price}" readonly="readonly">
			</div>
			<input type="hidden" name="pno" class="pnoClass" value="${product.pno}">
			<input type="hidden" name="userid" class="userid" value="${ok.id}">
			<c:if test="${not empty grade}">
			<button class="btn btn-primary productMod" >물품 수정</button>
			</c:if>
			<button class="btn btn-primary getBasket">장바구니</button>
			<c:if test="${not empty grade}">
			<button class="btn btn-primary productDel" >물품 삭제</button>
			</c:if>
			<button class="btn btn-primary productBuy" >물품 구매</button>
			<button class="btn btn-primary productlike">좋아요</button>
			<br>
			<c:if test="${not empty grade}">
			<div class="tr_hashTag_area">
			   <p><strong>해시태그 등록</strong></p>
	           <div class="form-group">
	                <input type="hidden" value="" name="tag" id="rdTag" />
	           </div>
	           
	           <ul id="tag-list"></ul>
		        <div class="form-group">
		            	<input type="text" id="tag" size="7" placeholder="엔터로 해시태그를 등록해주세요." style="width: 300px"/>
		           </div>
					<button class="btn btn-primary hashTag"> 해시태그 등록</button>
				</div>
			</c:if>
				<div>
					<div class='tag-item tag11'>			
						<c:forEach items="${hash}" var="h">
							<a href="${contextPath }/product/searchDetail?search=${h.tagId}">${h.tagId}/</a>	
						</c:forEach>
					</div>
				</div>
		</form>
		<div>
		<br>
		</div>
			<div class="container">
				  <table class="table table-bordered table-sm">
					    <tbody>
					      <tr>
					        <td>${ok.id }</td>
					        <td><input type="text" class="form-control repl"></td>
					        <td>
								<select class="replName">
									<option value="1">1</option>
								    <option value="2">2</option>
								    <option value="3">3</option>
								    <option value="4">4</option>
							    	<option value="5">5</option>
								</select>
							</td>
					      </tr>
					    </tbody>
				  </table>
				<button type="button" class="repleBut">댓글 달기</button>
			</div>
			
			<div class="container">
			  <table class="table table-hover">
			    <tbody>
			      <c:forEach items="${repl}" var="r">
						<colgroup>
				            <col width=10%>
				            <col width=65%>
				            <col width=5%>
				            <col width=10%>
				            <col width=10%>
			        	</colgroup>
				      <tr>
					        <td>${r.id}</td>
					        <td><input  type="text" readonly="readonly" class="form-control replcontent" value="${r.replcontent}"></td>
					        <td>${r.replStar}</td>
					        <td><input  type="hidden" class="replrno" value="${r.rno}">${r.replyDate}</td>
						 	<c:if test="${ok.id eq r.id || not empty grade}">
						    	<td><button type="button" class="float-left replMod">수정</button><button type="button" class="float-left repldel">삭제</button></td>
					       	</c:if>
				      </tr>
			      </c:forEach>
	
			    </tbody>
			  </table>
			
			</div>

		
</div>


</div>


<%@ include file="../lay/footer.jsp" %>