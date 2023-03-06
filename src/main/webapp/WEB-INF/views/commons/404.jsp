<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../lay/header.jsp" %>

<title>404</title>
</head>
<body>
<div class="container">
	<div class="jumbotron text-center">
		<img class="404" width="1200" src="https://img.freepik.com/free-vector/error-404-concept-for-landing-page_52683-12850.jpg?w=1380&t=st=1672205715~exp=1672206315~hmac=5ff1dbb5d5d0374349c04e81d8f0aef2129233778de46d683db3de23e0fd1982">
		<a href="${contextPath}/main" class="btn">메인페이지로</a>
		<button onclick="history.go(-1)">이전으로</button>
	</div>
</div>

<%@ include file="../lay/footer.jsp" %>