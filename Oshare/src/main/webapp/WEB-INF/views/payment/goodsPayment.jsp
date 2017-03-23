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

<form action="<c:url value="/reservation/apply"/>" method="post" id="paymentForm">
<!-- <form action="" method="post"> -->
<div class="container">
	<div class="goodsPaymentForm">
		<table>
		<tr>
			<td>대여제품</td>
			<td>거래방식</td>
			<td>수량</td>
			<td>기간</td>
			<td>대여비</td>
			<td>총액</td>
		</tr>
		<tr>
			<td id="productName">${board.productName}</td>
			<td>
			<select name="">
			<option>택배</option>
			<option>직거래</option>
			</select>
			</td>
			<td>
			<input type="number" name="productCount" value="1">
			</td>
			<td>
				<span>${reservation.startDate} ~ ${reservation.endDate} (${diffDays}일)</span></td>
			<td id="price">${board.price}</td>
			<td id="totPrice">${totalPayment}</td>	
		</tr>
		</table>	
	</div>
	<div class="userInformation">
		<span>주문자 정보</span>
		<ul>
			<li>이름 : <span id="buyerName">${member.memberName}</span></li>
			<li>아이디 : <span>${member.memberId}</span></li>
			<li>휴대폰 : <span id="buyerPhone">${member.memberPhone}</span></li>
			<li>카드번호 : <span>${member.memberAccount}</span></li>
		</ul>
	</div>
	<div class="shippingInformation">
		<span>배송지 정보</span>
		<ul class="nav nav-tabs">
			<li class="active"><a data-toggle="tab" href="#latelyShipping">최근배송지</a></li>
			<li><a data-toggle="tab" href="#newShipping">새로운배송지</a></li>
		</ul>

		<div class="tab-content">
		
			<div id="latelyShipping" class="tab-pane fade in active">
				<div class="latelyShippingForm">
					<ul>
					<li>수령인 : <span>${member.memberName}</span></li>
					<li>배송지 : <span id="buyerAddr">${member.memberAddr}</span></li>
					<li>연락처 : <span>${member.memberPhone}</span></li>
					<li>카드정보 : 
					<select name="">
						<option value="321-1234-5512">${member.memberBank} ${member.memberAccount}</option>
					</select>
					</li>
					</ul>				
				</div>
			</div>
			<div id="newShipping" class="tab-pane fade">
				<div class="newShippingForm">
					<form action="" method="">
					<ul>
						<li><span>수령인 :</span> <input type="text" name=""></li>
						<li><span>배송지명 :</span> <input type="text" name=""><span style="float:inherit"><i class="fa fa-search" aria-hidden="true"></i></span></li>
						<li><span id="">배송지 :</span> <input type="text" name=""></li>
						<li><span>연락처 :</span>
						<select name="">
							<option value="010">010</option>
							<option value="011">011</option>
							<option value="013">013</option>
							<option value="012">012</option>
						</select>
						<input type="number" name="">
						<input type="number" name="">
						
						</li>
					</ul>
					</form>
				</div>
			</div>
	</div>
</div>
	<div class="paymentMoney">
		<span>결제금액</span>
		<ul>
			<li>대여비 : <span id=paymentPrice>${board.price}</span></li>
			<li>대여기간 : <span id="sharingDays">${diffDays}</span>일</li>
			<li>결제예정 금액 : <span id="paymentTotal">${totalPayment}</span></li>
		</ul>
	</div>
	<div class="goodsPaymentBtn">
		<input type="button" value="결제">
			<input type="hidden" name="start" value="${reservation.startDate}">
			<input type="hidden" name="end" value="${reservation.endDate}">
			<input type="hidden" name="boardNo" value="${board.boardNo}">
			<input type="hidden" name="sellerId" value="${board.memberDTO.memberId}">
			<input type="hidden" name="buyerId" value="${member.memberId}">
			<input type="hidden" id="totalPrice" name="totalPrice" value="${totalPayment}">
		<input type="button" value="취소" onclick="location.href=<c:url value='/'/>">
	</div>
</div>
</form>
</body>
</html>