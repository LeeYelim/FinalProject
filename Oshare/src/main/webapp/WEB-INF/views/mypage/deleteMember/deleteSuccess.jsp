<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<div class="col-lg-8 col-lg-offset-2 text-center">
		<div class="logo">
			<h1>확인 클릭</h1>
		</div>

		<div class="clearfix"></div>

		
		<div class="clearfix"></div>
		<br />
		<div class="col-lg-6  col-lg-offset-3">
			<div class="btn-group btn-group-justified">
			<form action="<c:url value='/member/deleteLoginCheck'/>" method="post">
			<input type="text" name="memberId" value="${memberId}" hidden>
			<input type="text" name="memberPwd" value="${memberPwd}" hidden>
				<input type="submit" class="btn btn-primary" value="확인">
				<a href="<c:url value="/"/>" class="btn btn-success">취소</a>
			</form>
			</div>

		</div>
	</div>


</div>
</body>
</html>