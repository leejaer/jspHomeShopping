<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../lay/header.jsp" %>

  <div>
		<div class="container">
			  <p class="bg-primary text-white center"><h1>문의메일</h1>
		</div>

  </div>


<div class="container">
  <form target="_blank" action="https:메일주소" method="POST">

    <div class="form-group">

      <div class="form-row">

        <div class="col">

          <input type="text" name="name" class="form-control" placeholder="제목" required>

        </div>

        <div class="col">

          <input type="email" name="email" class="form-control" placeholder="이메일" required>

        </div>

      </div>

    </div>

    <div class="form-group">

      <textarea placeholder="내용" class="form-control" name="내용" rows="10" required></textarea>

    </div>

    <button type="submit" class="btn btn-lg btn-dark btn-block">보내기</button>

  </form>

</div>

<%@ include file="../lay/footer.jsp" %>