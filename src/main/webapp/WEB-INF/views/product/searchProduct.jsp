<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../lay/header.jsp"%>
	<div>
		<div class="container">
		  <p class="bg-primary text-white center">상품</p>
		</div>
 	</div>
	
	
	<div class="container">
    <div class="row">
     <c:forEach items="${product}" var="p">
      <div class="col-3">
        <div class="card">
          <div class="card-header">
            ${p.name }
          </div>
          <img src="images/card-image.png" alt="" />
          <input type="hidden" name="imageFileName" class="form-control viewMode">
			<div class="my-2">
				<c:if test="${not empty p.image}">
					<input type="hidden" name="originFileName" value="${p.image}">
					<div class="preview">
					<c:if test="${empty ok}">
						<a href="${contextPath}/member/loginForm"><img class="originImg" src="${contextPath}/filedownload?no=${p.pno}&image=${p.image}&path=product"></a>
					</c:if>
					<c:if test="${not empty ok}">
						<a href="${contextPath}/product/detail?pno=${p.pno}"><img class="originImg" src="${contextPath}/filedownload?no=${p.pno}&image=${p.image}&path=product"></a>
					</c:if>
					</div>
				</c:if>
				<c:if test="${empty p.image}">
					<div class="preview">
						<p>등록된 파일이 없습니다</p>
					</div>
				</c:if>
			</div>
          <div class="card-body">
            <h5 class="card-title">${p.price }원</h5>
            <p class="card-text">${p.content }</p>
          </div>
        </div>
      </div>
      </c:forEach>
    </div>
  </div>


<%@ include file="../lay/footer.jsp"%>

