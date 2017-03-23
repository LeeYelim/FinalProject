<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<div class="mypageMenu">
		<ul>
			<li>myPage</li>
			<li><a href="<c:url value='/mypage/wishList'/>">WishList</a></li>
			<li><a href="<c:url value='/mypage/salesItem'/>">판매물품목록</a></li>
			<li><a href="<c:url value='/mypage/rentalItem'/>">대여물품목록</a></li>
			<li><a href="<c:url value='/mypage/message'/>">쪽지함</a></li>
			<li><a href="<c:url value='/mypage/updateMemberForm'/>">회원정보수정</a></li>
			<li><a href="/controller/mypage/deleteLoginForm">회원탈퇴</a></li>
			<c:if test="${sessionScope.loginMemberId eq 'admin'}"><li><a href="<c:url value='/mypage/userManagement'/>">회원관리</a></li></c:if>
		</ul>
	</div>

	<!-- mobile mypageMenu -->
	<div class="mobileMypageMenu">
		<ul>
			<li><a href="<c:url value='/mypage/wishList'/>">WishList</a></li>
			<li><a href="<c:url value='/mypage/salesItem'/>">판매물품목록</a></li>
			<li><a href="<c:url value='/mypage/rentalItem'/>">대여물품목록</a></li>
			<li><a href="<c:url value='/mypage/message'/>">쪽지함</a></li>
			<li><a href="<c:url value='/mypage/updateMemberForm'/>">회원정보수정</a></li>
			<li><a href="/controller/mypage/deleteLoginForm">회원탈퇴</a></li>
			<c:if test="${sessionScope.loginMemberId eq 'admin'}"><li><a href="<c:url value='/mypage/userManagement'/>">회원관리</a></li></c:if>
		</ul>
	</div>


</body>
</html>