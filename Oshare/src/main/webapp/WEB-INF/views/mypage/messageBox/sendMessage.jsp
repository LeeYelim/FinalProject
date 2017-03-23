<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="sendMessageForm">
		<ul class="nav nav-tabs">
			<li class="active"><a data-toggle="tab" href="#writeNote">쪽지쓰기</a></li>
			<li><a data-toggle="tab" href="#inbox" class="receiverMessage">받은쪽지함</a></li>
			<li><a data-toggle="tab" href="#sent" class="senderMessage">보낸쪽지함</a></li>
		</ul>

		<div class="tab-content">

			<div id="writeNote" class="tab-pane fade in active">
				<div class="sendMessageTabForm">
			   <%-- <form action="<c:url value='/mypage/sendMessageInsert'/>" metohd="" onsubmit="return sendMessageValidityCheck()"> --%>
					<form id="msgForm" name="msgForm" action="" method="post" onsubmit="return sendMessageValidityCheck()">
						<input type="text" value="${sessionScope.loginMemberId}"b name="sender" hidden>
						<div class="sender">
							<span>송신자 ${sessionScope.loginMemberId}</span>
						</div>
						<div class="recipient">
							<span>수신자</span> <input type="text" name="receiver">
						</div>
						<div class="noteContent">
							<span>쪽지내용</span>
							<textarea name="content"></textarea>
						</div>

						<div class="sendMessageBtn">
							<input type="button" id="insertBtn" value="쪽지보내기">
						</div>
					</form>
				</div>
			</div>
			<div id="inbox" class="tab-pane fade">
				<div class="inMessage">
					
						<table id="inMessageTable" class="display" cellspacing="0" width="100%">
						<thead>
						<tr>
					 	<th><input type="checkbox" class="allCheck"></th>
								<th>내용</th>
								<th>보낸사람</th>
								<th>시간</th> 
						</tr>
							</thead>
						</table>
					
				</div>
			
					<div class="inSendMessageBtn">
						<input type="button" value="삭제">
					</div>
				
			</div>
			<div id="sent" class="tab-pane fade">
				<div class="outMessage" >
				
						<table id="outMessageTable" class="display" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th><input type="checkbox" class="allCheck"></th>
								<th>내용</th>
								<th>받는사람</th>
								<th>시간</th>
							</tr>
						</thead>
							
						</table>
				
				</div>
			
					<div class="outSendMessageBtn">
						<input type="button" value="삭제">
					</div>
				
			</div>
		</div>
	</div>
</body>
</html>
