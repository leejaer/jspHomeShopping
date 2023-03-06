<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../lay/header.jsp" %>
<script src="${contextPath}/resource/js/product/productbasket.js"></script>
<script src="${contextPath}/resource/js/product/basketBuy.js"></script>
<script src="${contextPath}/resource/js/product/buy2.js"></script>
<div class="container">
	<div>
			<h1>결제 페이지</h1>
	</div>
 	<form action="" class="basket">
	  <table class="table">
	    <thead>
	      <tr>
	        <th>
	        	<!--  <button type="button" id="allSelect">선택</button>-->
	        	선택/<button type="button" id="allSolve">전체해제</button>
	        </th>
	        <th>사진</th>
	        <th>이름</th>
	        <th>가격</th>
	        <th>수량</th>
	        <th>쿠폰</th>
	      </tr>
	    </thead>
	    <tbody>
	      <c:forEach items="${vo}" var="l" varStatus="status">
		      <tr>
		        <td>
		       	 <input type="checkbox" class="pcheckbox">
		        </td>
		        <td>
					<c:if test="${not empty l.image}">
						<div class="preview">
								<img class="img" width="50" src="${contextPath}/filedownload?no=${l.pno}&image=${l.image}&path=product">
						</div>					
					</c:if>	
				</td>
		        <td>
		        	<a href="${contextPath}/product/detail?pno=${l.pno}">${l.name }</a>
				</td>
		        <td>
		        	${l.price }
		        </td>
		        <td>
		        	<input type="hidden" value="${l.pno}" class="ppp">
		       	 	<input type="hidden" value="${ok.id}" class="pid">
		        	<select name="count" class="buycount">
			        	<c:forEach begin="1" end="${l.currentmount}" varStatus="a" step="1">
						    <option value="${a.count}" >${a.count}</option>
						</c:forEach>
					</select>
		        </td>
		        <td></td>
		      </tr>
	      </c:forEach>
	    </tbody>
	  </table>
		  <button type="button" class="btn btn-success productB">구매</button>  
		  <button type="button" class="btn btn-danger productCancle">최소</button> 
		   	
	</form>
</div>
<%@ include file="../lay/footer.jsp" %>

<script>

</script>
