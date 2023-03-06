<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../lay/header.jsp" %>
<script src="${contextPath}/resource/js/member/joinForm.js"></script>
  <body class="text-center">
    
    <!--  html 전체 영역을 지정하는 container -->
    <div id="container">
      <!--  login 폼 영역을 : loginBox -->
      <div id="loginBox">
      
        <!-- 로그인 페이지 타이틀 -->
        <div id="loginBoxTitle">회원가입</div>
        <!-- 아이디, 비번, 버튼 박스 -->
        <div id="inputBox">
          <div class="input-form-box"><span>아이디: </span><input type="text" name="id" class="form-control id"></div>
          <div class="input-form-box">아이디확인: <input type="text" class="idChecked" value="아이디 확인중"></div>
          <div class="input-form-box"><span>이름: </span><input type="text" name="name" class="form-control name"></div>
          <div class="input-form-box"><span>비밀번호: </span><input type="password" name="pwd" class="form-control pwd"></div>
          <div class="input-form-box"><span>비번확인: </span><input type="password" name="pwdCheck" class="form-control pwdCheck"></div>
     	  <div class="input-form-box"><span>체크:</span><input readonly="readonly" class="form-control pwdChecked"></div>
          <div class="input-form-box"><span>주소: </span><input type="text" name="addr" class="form-control addr"></div>
          <div class="input-form-box"><span>이메일: </span><input type="text" name="email" class="form-control email"></div>
          <div class="input-form-box"><span>전화번호: </span><input type="text" name="tel" class="form-control tel"></div>
          <div class="button-login-box" >
            <button type="button" class="btn btn-primary btn-xs memberJoin" style="width:100%">회원가입</button>
          </div>
        </div>
        
      </div>
    </div>

    <!-- Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

  </body>
<%@ include file="../lay/footer.jsp" %>