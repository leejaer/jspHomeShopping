<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../lay/header.jsp" %>
		<table class="table">
		  <thead class="thead-inverse">
		    <tr>
		      <th>회원번호</th>
		      <th>이름</th>
		      <th>주소</th>
		      <th>가입일</th>
		      <th>밴</th>
		    </tr>
		  </thead>
		<c:forEach items="${memberList}" var="l">
		    <tr>
		      <td>${l.mno }</td>
		      <td>${l.name }</td>
		      <td>${l.addr }</td>
		      <td><fmt:formatDate value="${l.joinDate }" pattern="yyyy년 MM월 dd일"/></td>
		      <th>
		      
			      <form action="${contextPath}/member/ban" method="post">
			      		<input type="hidden" value="${l.mno}" name="mno">
			      		<button>강퇴 ${l.grade}</button>
			      </form>
		      
		      </th>
		    </tr>
		</c:forEach>
		  <tbody>
		  </tbody>
</table>



<%@ include file="../lay/footer.jsp" %>