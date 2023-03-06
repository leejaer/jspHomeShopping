<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ include file="../lay/header.jsp"%>
<script src="${contextPath}/resource/js/member/mod.js"></script>
	<div class="container my-3">
	<c:if test="${not empty ok}">
	<form action="" id="listForm">
		<table class="table">
			<tr>
				<th>번호</th>
				<td><input readonly="readonly" value="${detail.mno }" class="form-control modMno"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input readonly="readonly" value="${detail.name }" class="form-control"></td>
			</tr>
			<tr>
				<th>아이디</th>
				<td><input readonly="readonly" value="${detail.id }" class="form-control"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password"  class="form-control modPwd" ></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input  value="${detail.addr }" class="form-control modAddr"></td>
			</tr>
			<tr>
				<th>등급</th>
				<td><input readonly="readonly" value="${detail.buymount }" class="form-control"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input  value="${detail.email }" class="form-control modEmail" ></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input  value="${detail.tell }" class="form-control modTell"></td>
			</tr>
			<tr>
				<th>포인트</th>
				<td><input readonly="readonly" value="${detail.point }" class="form-control"></td>
			</tr>
			<tr>
				<th>가입일</th>
				<td><input readonly="readonly" value="${detail.joinDate }" class="form-control"></td>
			</tr>
		</table>
	</form>
	</c:if>
	<button class="btn btn-primary modifyMod">수정</button>
	<button class="btn btn-primary" onclick="history.go(-1)">이전으로</button>
</div>
	


<%@ include file="../lay/footer.jsp"%>




