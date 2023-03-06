<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="ok" value="${sessionScope.ok}"/>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈쇼핑</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<link rel="stylesheet" href="css/login.css?v=1234">
<script src="${contextPath}/resource/js/member/common.js"></script>
<script> const contextPath="${contextPath}"</script>
<script src="${contextPath}/resource/js/service/main.js"></script>
<style>


	.preview a{
		display: block;
	}
	.preview a img{
		width: 100%;
		height: 230px;
	}


 * { padding: 0; margin: 0; }

        html, body {
          height: 100%;
          background: #ffffff;
        }
        
        #container {
          display: flex;
          flex-direction: row;
          justify-content: center;
          align-items: center;
          height: 100%;
        }
        
        #loginBox {
          width: 700px;
          text-align: center;
          background-color: #ffffff;
        }
        .input-form-box {
          border: 0px solid #ff0000;
          display: flex;
          margin-bottom: 5px;
        }
        .input-form-box > span {
          display: block;
          text-align: left;
          padding-top: 15px;
          min-width: 80px;
        }
        .button-login-box {
          margin: 20px 0;
        }
        #loginBoxTitle {
          color:#000000;
          font-weight: bold;
          font-size: 20px;
          text-transform: uppercase;
          padding: 5px;
          margin-bottom: 10px;
          background: linear-gradient(to right, #3325, #ff0000);
          -webkit-background-clip: text;
          -webkit-text-fill-color: transparent;
        }
        #inputBox {
          margin: 15px;
        }
        
        #inputBox button {
          padding: 3px 5px;
        }
        
p { margin:20px 0px; }
ul {list-style-type:none;

/* 좌측 여백 없애기 */
padding-left:0px;
/* 우측 정렬 하기 */
float:right;
}


ul li {display:inline;

/* li요소의 좌측 1px의 테두리 만들기 */
border-left: 1px solid #c0c0c0;
/* 테두리와 메뉴 간격 벌리기, padding: 위 오른쪽 아래 왼쪽; */
padding: 0px 10px 0px 10px;
/* 메뉴와 테두리 사이 간격 벌리기, margin: 위 오른쪽 아래 왼쪽; */
margin: 5px 0px 5px 0px;
}
ul li:first-child {

/* li의 첫번째 요소 좌측에는 테두리 없애기 */
border-left: none;

}

  html,
  body {
    position: relative;
    height: 100%;
  }

  body {
    background: #eee;
    font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
    font-size: 14px;
    color: #000;
    margin: 0;
    padding: 0;
  }

  .swiper {
    width: 100%;
    height: 100%;
  }

  .swiper-slide {
    text-align: center;
    font-size: 18px;
    background: #fff;

    /* Center slide text vertically */
    display: -webkit-box;
    display: -ms-flexbox;
    display: -webkit-flex;
    display: flex;
    -webkit-box-pack: center;
    -ms-flex-pack: center;
    -webkit-justify-content: center;
    justify-content: center;
    -webkit-box-align: center;
    -ms-flex-align: center;
    -webkit-align-items: center;
    align-items: center;
  }

  .swiper-slide img {
    display: block;
    width: 100%;
  	height: 200%;
    object-fit: cover;
  }
</style>


</head>

<div>
  <a href="${contextPath}/main"> <img class="imghead1" width="1900" src="${contextPath}/resource/image/머리.png"></a>
</div>

		<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		  <form class="form-inline" action="${contextPath }/product/searchDetail">
		    <input class="form-control mr-sm-2" name="search" type="text" placeholder="Search">
		    <button class="btn btn-success searchBtn" type="submit">검색</button>
		  </form>
		</nav>



	<c:if test="${empty ok}">
    <ul>
	    <li><a href="${contextPath}/member/loginForm">로그인</a></li>
    	<li><a href="${contextPath}/member/joinForm">회원가입</a></li>
    </ul>
    </c:if>
    <c:if test="${not empty ok}">
    <ul>
    	<li><a href="${contextPath}/member/memberDetail?id=${ok.id}">${ok.id }</a></li>
    <c:if test="${not empty grade}">
    	<li><a href="${contextPath}/product/addForm">판매등록</a></li>
    	<li><a href="${contextPath}/member/memberList">회원리스트</a></li>
    	<li><a href="${contextPath}/product/bill">주문 리스트(관리자용)</a></li>
    </c:if>
    	<li><a href="${contextPath}/product/myBill?id=${ok.id}">주문 리스트</a></li>
    	<li><a href="${contextPath}/member/basketList?id=${ok.id}">장바구니</a></li>
   		<li ><a href="${contextPath}/member/logout">로그아웃</a></li>
    	<li><a href="${contextPath}/member/cumstomerService">문의메일</a></li>
    	<li><a href="#" class="cal">달력</a></li>
    </ul>
    </c:if>
<div>
	<br>
</div><br>

<div>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	  <!-- Brand -->
	  <a class="navbar-brand" href="#"><img class="imghead" width="50" src="${contextPath}/resource/image/1.jpg"></a>
	
	  <!-- Links -->
	  <ul class="navbar-nav">
	    <li class="nav-item">
	      <a class="nav-link" href="${contextPath}/product/search?class1=티셔츠">티셔츠</a>
	    </li>
	  </ul>
	  <ul class="navbar-nav">
	    <li class="nav-item">
	      <a class="nav-link" href="${contextPath}/product/search?class1=점퍼">점퍼</a>
	    </li>
	  </ul>
	  <ul class="navbar-nav">
	    <li class="nav-item">
	      <a class="nav-link" href="${contextPath}/product/search?class1=니트">니트</a>
	    </li>
	  </ul>
	  <ul class="navbar-nav">
	    <li class="nav-item">
	      <a class="nav-link" href="${contextPath}/product/search?class1=가디건">가디건</a>
	    </li>
	  </ul>
	  <ul class="navbar-nav">
	    <li class="nav-item">
	      <a class="nav-link" href="${contextPath}/product/search?class1=코트">코트</a>
	    </li>
	  </ul>
	  <ul class="navbar-nav">
	    <li class="nav-item">
	      <a class="nav-link" href="${contextPath}/product/search?class1=셔츠">셔츠</a>
	    </li>
	  </ul>
	  <ul class="navbar-nav">
	    <li class="nav-item">
	      <a class="nav-link" href="${contextPath}/product/search?class1=팬츠">팬츠</a>
	    </li>
	  </ul>
	  <ul class="navbar-nav">
	    <li class="nav-item">
	      <a class="nav-link" href="${contextPath}/product/search?class1=자켓">자켓</a>
	    </li>
	  </ul>
	  <ul class="navbar-nav">
	    <li class="nav-item">
	      <a class="nav-link" href="${contextPath}/product/search?class1=수트">수트</a>
	    </li>
	  </ul>
	  <ul class="navbar-nav">
	    <li class="nav-item">
	      <a class="nav-link" href="${contextPath}/product/search?class1=언더/라운지웨어">언더/라운지웨어</a>
	    </li>
	  </ul>
	</nav>
</div>
