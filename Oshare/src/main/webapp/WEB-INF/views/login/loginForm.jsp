<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>로그인</title>
<script type="text/javascript">
	
function checkValid() {
	var f = window.document.loginForm;
	
	// 아이디 공백 체크
	if(f.id.value == "") {
		alert("아이디를 입력해주세요.");
		f.id.focus();
		return false;
	}
	
	// 비밀번호 공백 체크
	if(f.password.value == "") {
		alert("비밀번호를 입력해주세요.");
		f.password.focus();
		return false;
	}
}

</script>
</head>

<body>
	<div id="login">
		<div class="loginForm">
			<div class="loginTitle">
				<span>Login</span>
			</div>
			<form name="loginForm" action="<c:url value='/member/loginCheck'/>"
				method="post" onsubmit='return checkValid()'>
				<ul>
					<li><span>아이디</span> <input type="text" name="memberId" placeholder="아이디"></li>
					<li><span>비밀번호</span> <input type="password" name="memberPwd" placeholder="비밀번호"></li>
					<li><input type="submit" value="로그인"></li>
				</ul>
			</form>

			<div class="loginSub">
				<a href="<c:url value='/member/signupForm'/>">회원가입</a><a href="#">ID찾기</a><a
					href="#">비밀번호찾기</a>
			</div>
		</div>
	</div>

	<div id="mobileLogin">
		<div class="mobileLoginForm">
			<a href="<c:url value='/'/>"><img src="<c:url value='/resources/image/icon.png'/>" alt="logo"></a>

			<form action="<c:url value='/member/loginCheck'/>" method="post">
				<input type="text" name="id" placeholder="아이디"> 
				<input type="password" name="passowrd" placeholder="패스워드">
				 <input	type="submit" value="로그인">
			</form>

			<div class="mobileLoginSub">
				<a href="#">아이디 찾기</a> <a href="#">비밀번호 찾기</a> <a
					href="<c:url value='/member/signupForm'/>">회원가입</a>
			</div>
		</div>
	</div>
</body>
</html>