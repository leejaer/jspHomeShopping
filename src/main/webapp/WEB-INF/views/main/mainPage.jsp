<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../lay/header.jsp" %>
<body>
	<!-- Swiper -->
<div class="swiper mySwiper">
  <div class="swiper-wrapper">
   <c:if test="${empty ok}">
    <div class="swiper-slide"><a href="${contextPath}/member/loginForm"><img src="${contextPath}/resource/image/2.jpg"></a></div>
    <div class="swiper-slide"><a href="${contextPath}/member/loginForm"><img src="${contextPath}/resource/image/3.jpg"></a></div>
    <div class="swiper-slide"><a href="${contextPath}/member/loginForm"><img src="${contextPath}/resource/image/4.jpg"></a></div>
   </c:if>
   <c:if test="${not empty ok}">
    <div class="swiper-slide"><a href="${contextPath}/product/detail?pno=17"><img src="${contextPath}/resource/image/3.jpg"></a></div>
    <div class="swiper-slide"><a href="${contextPath}/product/detail?pno=18"><img src="${contextPath}/resource/image/4.jpg"></a></div>
    <div class="swiper-slide"><a href="${contextPath}/product/detail?pno=16"><img src="${contextPath}/resource/image/2.jpg"></a></div>
   </c:if> 
  </div>
  <div class="swiper-button-next"></div>
  <div class="swiper-button-prev"></div>
  <div class="swiper-pagination"></div>
</div>
</head>

<body>
  <div>
		<div class="container">
		  <p class="bg-primary text-white center">추천 상품</p>
		</div>
  </div>
  

  <div class="container">
    <div class="row">
     <c:forEach items="${ProductList}" var="p">
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
						<a href="${contextPath}/product/detail?pno=${p.pno}">바로가기</a>
					</div>
				</c:if>
			</div>
          <div class="card-body">
            <h5 class="card-title">${p.price }원</h5>
            <p class="card-text">${p.content }</p>
            <p class="card-text">${p.like1 }</p>
          </div>
        </div>
      </div>
      </c:forEach>
    </div>
  </div>
  <div>
	<div class="container">
 		<p class="bg-primary text-white center">댓글</p>
 		<div class="row">
 			<div class="col-6">
	 			<table class="table table-hover">
				    <thead>
				      <tr>
				        <th colspan="4">댓글</th>
				      </tr>
				    </thead>
				    <tbody>
				    <c:forEach items="${repl}" begin="0" end="4" var="r">
				      <tr>
				        <td>${r.pno }</td>
				        <td>${r.id }</td>
				        <td><a href="${contextPath }/product/detail?pno=${r.pno } ">${r.replcontent }</a></td>
				        <td>${r.replStar }</td>
				      </tr>
				    </c:forEach>
				    </tbody>
				</table>
 			</div>
 			<div class="col-6">
 				<table class="table table-hover">
				    <thead>
				      <tr>
				        <th colspan="5">고객의 소리</th>
				      </tr>
				    </thead>
				    <tbody>
					    <tr>
					        <td>***<td>
					        <td>18</td>
					        <td>배송 빨라서 좋았어요</td>
					        <td>****</td>
					    </tr>
				    </tbody>
				</table>
 			</div>
 		</div>
	</div>
  </div>	
  

<!-- Swiper JS -->
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>


<!-- Initialize Swiper -->
<script>
  var swiper = new Swiper(".mySwiper", {
    spaceBetween: 30,
    centeredSlides: true,
    autoplay: {
      delay: 2500,
      disableOnInteraction: false,
    },
    pagination: {
      el: ".swiper-pagination",
      clickable: true,
    },
    navigation: {
      nextEl: ".swiper-button-next",
      prevEl: ".swiper-button-prev",
    },
  });

</script>


  
  
</body>
<%@ include file="../lay/footer.jsp" %>