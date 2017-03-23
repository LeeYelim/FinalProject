<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>

</style>
	<script type="text/javascript" src="<c:url value='/resources/js/jquery-2.2.4.min.js'/>"></script>
	<script type="text/javascript">
		
		// 메세지 도착 알림
		function notifyMsg() {
			$.post("<c:url value='/notify/sendMessageInsert'/>", $('#msgForm').serialize());
		}

		var index=0;
		var msgAlarmColor = ["red","black"];
		var payAlarmColor = ["red","black"];
		var intervalMsgColor;
		var intervalPayColor;
		
		// 메세지 알림 도착, 아이콘 효과
		function changeMsgColor() {
			if(index==msgAlarmColor.length) index=0;
			$('#msgIcon').css('color', msgAlarmColor[index++]);
		}
		function startMessageAlarm() {
			intervalMsgColor = setInterval('changeMsgColor()', 500);
		}
		function endMessageAlarm() {
			clearTimeout(intervalMsgColor);
			$('#msgIcon').css('color','black');
		}

		// 결제 알림 도착, 아이콘 효과
		function changePayColor() {
			if(index==payAlarmColor.length) index=0;
			$('#payIcon').css('color', payAlarmColor[index++]);
		}
		function startPaymentAlarm() {
			intervalPayColor = setInterval('changePayColor()', 500);
		}
		function endPaymentAlarm() {
			clearTimeout(intervalPayColor);
			$('#payIcon').css('color','black');
		}
		
		$(document).ready(function(){
			
			$('#insertBtn').click(function(){
				if(sendMessageValidityCheck()){	
					notifyMsg();
					$(".recipient input[type=text]").val("");
					$(".noteContent textarea").val(""); 
				 }
			});
			
			function sendMessageValidityCheck(){
				if($(".recipient input[type=text]").val() == ""){
					alert("받는이를 입력해주세요");
					$(".recipient input[type=text]").focus();
					return false;
				}
				
				if($(".noteContent textarea").val() == ""){
					alert("쪽지내욤을 입력해주세요");
					$(".noteContent textarea").focus();
					return false;
				}
				
				if(!receiverBoolean){
					alert("수신자가 존재하지않습니다 다시 입력해주십시오");
					$(".recipient input[name=receiver]").val("");
					$(".recipient input[name=receiver]").focus();	
					return false;
				}
				
				return true;
			}

			//쪽지 수신자 유효성 검사
			$(document).on("keyup",".recipient input[name=receiver]",function(){
				$.ajax({
					url: "/controller/member/receiverSelect" , //서버요청이름
					type : "post" , //method방식 (get , post) 
					dataType : "text" , //요청결과타입 (text ,xml , html , json)
					data : "receiver="+$(".recipient input[name=receiver]").val(),
					success : function(result){
						if(result <=0){
							receiverBoolean = false;
						}else{
							receiverBoolean = true;
						}
					} , //성공
					error : function(err){
						alert("err :"+err)
						receiverBoolean = false;
					} , //실패
				});
			});	
	
			
			
			
			
			
			// 메세지 알람끄기
			$('#msgIcon').click(function(){
				endMessageAlarm();
			});
			$('#payIcon').click(function(){
				endPaymentAlarm();
			});
			
			var contextpath = '${pageContext.request.contextPath}';
			
			// 알람 event source 연결
			var source = new EventSource(contextpath + "/notify/connect");
			
			var loginMemberId = "${sessionScope.loginMemberId}";
			
			// 쪽지 알림 감지
			source.addEventListener('spring', function(event){
				// 해당 쪽지 알림 감지
				if(loginMemberId == event.data) {
					startMessageAlarm();
				}
			});
			
			// 결제 알림 감지
			source.addEventListener('payment', function(event){
				// 해당 결제 알림 감지
				if(loginMemberId == event.data) {
					startPaymentAlarm();
				}
			});
	
		});
	</script>
</head>
<body>
	<div class="container">
		<div class="headerNavactionUser">
			<ul>
				<c:choose>
					<c:when test="${empty sessionScope.loginMemberId}">
						<li><a href="<c:url value='/member/loginForm'/>">Login</a></li>
						<li><a href="<c:url value='/member/signupForm'/>">Join us</a>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							${sessionScope.loginMemberId} 님 안녕하세요
						</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<div class="headerNavaction container">
			<div class="headerIcon">
				<a href="<c:url value='/'/>"><img src="<c:url value='/resources/image/icon.png'/>" alt="logo"></a>
			</div>
			<ul>
			<c:if test="${!empty sessionScope.loginMemberId}">
				<li style="padding:0">					
					<div class="myPageList">
								<a href="<c:url value='/mypage/wishList'/>">My Page</a>
								<ul>
									<li><a href="<c:url value='/mypage/wishList'/>">WishList</a></li>
									<li><a href="<c:url value='/mypage/salesItem'/>">판매물품목록</a></li>
									<li><a href="<c:url value='/mypage/rentalItem'/>">대여물품목록</a></li>
									<li><a href="<c:url value='/mypage/message'/>">쪽지함</a></li>
									<li><a href="<c:url value='/mypage/updateMemberForm'/>">회원정보수정</a></li>
									<li><a href="<c:url value='/mypage/deleteLoginForm'/>">회원탈퇴</a></li>
									<c:if test="${sessionScope.loginMemberId eq 'admin'}"><li><a href="<c:url value='/mypage/userManagement'/>">회원관리</a></li></c:if>
									<li><a href="<c:url value='/member/logout'/>">로그아웃</a></li>
								</ul>
						</div>				
				</li>	
				</c:if>		
				<li><a href="<c:url value='/'/>">Home</a></li>
				<li><a href="<c:url value='/board/goodsList'/>">Sharing</a></li>
				<li><a href="<c:url value='/board/goodsRentalList'/>">Rental</a></li>
				<div class="headerIconNavaction">
					<li><span class="material-icons headerSearchIcon" id="Bsearch">search</span></li>
					<li><a href="<c:url value='/mypage/cartList'/>"><span
							class="material-icons">shopping_cart</span></a></li>
					<li><a href="#"><span class="material-icons" id="msgIcon">notifications_none</span></a></li>
					<li><a href="#"><span class="material-icons" id="payIcon">attach_money</span></a></li>
				</div>
			</ul>
			<div class="HeaderSearchForm">
				<form action="<c:url value="/board/goodsList"/>" method="" onsubmit="return headerSearchValidityCheck()">
					<input type="text" name="searchBar" placeholder="Search">
				</form>
				<div class="HeaderSearchClose">
					<span class="material-icons">&#xE5CD;</span>
				</div>
			</div>
		</div>
		<div class="mobileHeaderNavaction">
			<ul>
				<li>
					<div class="mobileHeaderNavactionBtn">
						<span></span> <span></span> <span></span>
					</div>
				</li>
				<li>
					<div class="mobileHeaderNavactionLogo">
						<a href="<c:url value='/'/>">Logo</a>
					</div>
				</li>
				<li>
					<div class="mobileHeaderNavactionIcon">
						<ul>
							<li><a href="<c:url value='/mypage/shoppingBasket'/>"><span
									class="material-icons">shopping_cart</span></a></li>
							<li><a href="#"><span class="material-icons">notifications_none</span></a></li>
							<li><a href="#"><span class="material-icons">attach_money</span></a></li>
						</ul>
					</div>
				</li>
			</ul>
			<!-- mobile menu -->
			<div class="mobileHeaderNavactionMenu">
				<ul>
					<li>
						<div class="mobileHeaderNavactionMenuSearch">
							<form action="" method=""
								onsubmit="return mobileHeaderSearchValidityCheck()">
								<input type="text" name=""> <input type="submit"
									class="material-icons" value="&#xE8B6;">
							</form>
						</div>
					</li>
					<c:choose>
						<c:when test="${!empty sessionScope.loginMemberId}">
							<li><a href="<c:url value='/mypage/wishList'/>">My Page</a></li>
							<li><a href="<c:url value='/'/>">Home</a></li>
							<li><a href="<c:url value='/board/goodsList'/>">Sharing</a></li>
							<li><a href="<c:url value='/board/goodsRentalList'/>">Rental</a></li>
							<li><a href="<c:url value='/mypage/deleteLoginForm'/>">Member Leave</a></li>	
							<li><a href="<c:url value='/member/logout'/>">logout</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="<c:url value='/'/>">Home</a></li>
							<li><a href="<c:url value='/board/goodsList'/>">Sharing</a></li>
							<li><a href="<c:url value='/board/goodsRentalList'/>">Rental</a></li>
							<li><a href="<c:url value='/member/loginForm'/>">Login</a></li>
							<li><a href="<c:url value='/member/signupForm'/>">Join us</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>