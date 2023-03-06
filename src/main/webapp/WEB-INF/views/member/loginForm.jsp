<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../lay/header.jsp" %>
<script src="${contextPath}/resource/js/member/loginForm.js"></script>
</head>
  <body class="text-center">
    
    <!--  html 전체 영역을 지정하는 container -->
    <div id="container">
      <!--  login 폼 영역을 : loginBox -->
      <div id="loginBox">
      
        <!-- 로그인 페이지 타이틀 -->
        <div id="loginBoxTitle">Home Shopping</div>
        <!-- 아이디, 비번, 버튼 박스 -->
        <div id="inputBox">
          <div class="input-form-box"><span>아이디 </span><input type="text" name="id" class="form-control loginId"></div>
          <div class="input-form-box"><span>비밀번호 </span><input type="password" name="pw" class="form-control loginPwd"></div>
          <div class="button-login-box" >
            <button type="button" class="btn btn-primary btn-xs LoginBtn" style="width:100%">로그인</button>
          </div>
        </div>
        
      </div>
    </div>

    <!-- Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

  </body>
</html>