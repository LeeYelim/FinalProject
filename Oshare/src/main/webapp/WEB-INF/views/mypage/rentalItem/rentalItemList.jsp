<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	
	<script type="text/javascript">
		
		function notifyPayment() {
			$.post("<c:url value='/notify/alarmPayment?boardNo=${boardNo}'/>");
		}
			
		$(document).ready(function(){
			
			 // get방식 parameter 추출
			 var getParam = function(key){
			        var _parammap = {};
			        document.location.search.replace(/\??(?:([^=]+)=([^&]*)&?)/g, function () {
			            function decode(s) {
			                return decodeURIComponent(s.split("+").join(" "));
			            }
			 
			            _parammap[decode(arguments[1])] = decode(arguments[2]);
			        });
			        return _parammap[key];
			};
			
			//alert("boardNo = " + getParam('boardNo'));
			var boardNo = parseInt(getParam('boardNo'));

			if(boardNo>0) {
				// alert('notifyment()호출');
				notifyPayment();
			}
		});
	</script>
	
</head>
<body>
	<div class="rentalItemListForm">
	<div class="rentalItemListTitle">
	<span>대여물품목록</span>
	</div>
		<table id="rentalItemTable"  class="display" cellspacing="0" width="100%">
				<thead><tr>
					<th>사진</th>
					<th>제품명</th>
					<th>판매자 ID</th>
					<th>판매자 연락처</th>
					<th>반납일</th>
					<th>거래상태</th>
				</tr></thead>
			<tbody>
			<c:forEach items="${requestScope.rentallist}" var="board">
					<c:forEach items="${board.sharing}" var="sharing" >
						<tr><td>
							<a href="<c:url value='/board/goodsDetail?boardNo=${board.boardNo}'/>"><img src="<c:url value='/${board.filePath}'/>"alt="제품사진"></a>
						</td>
						<td> 
							${board.productName} 
						</td>
						<td>
							${sharing.sellerId}
						</td>
						<td>
							${sharing.member.memberPhone}
						</td>
						<td>
							${sharing.sharingEnd}
						</td>
						<td>
							<div class="rentalItemListTransactionStatus">
							<span>${sharing.transactionState}</span>
							<input type="hidden" id="'sharingNo" value="${sharing.sharingNo}">
							<input type="button" value="반납신청" id="${sharing.sharingNo}" name="${sharing.sellerId}">
							<input type="hidden" id="boardNo" value="${board.boardNo}">
							</div>
						</td></tr>
					</c:forEach>
				</c:forEach>
				</tbody>
			</table>
		</div>
	<div class="rentalItemListDialog">
		<form action="<c:url value="/reservation/applyReturn"/>" method="post" id="returnForm">
			<div class="rentalItemListDialogTitle">
				<span>반납신청</span> 
				<span class="material-icons rentalItemListClose ">&#xE5CD;</span>
			</div>
			<div class="rentalItemListDialogForm">
				<div class="rentalItemList">
					<select name="returnState">
						<option value="반납">반납</option>
						<option value="파손">파손</option>
						<option value="분실">분실</option>
					</select>
				</div>
				<div class="rentalItemEvaluation">
			<div id="rateYo"></div>
		</div>
			<div class="sellerEvaluation">
				<span>판매자평가 </span>
				<textarea id="memberReview"></textarea>
			</div>
			<div class="rentalItemListBtn">
	   		 	<input type="button" value="신청"><input type="button" value="취소">
	   		</div>
   		 </div>
	   	</form>
	   	</div>
  	<div class="saleItemListDialog">
	<div class="saleItemListDialogTitle">
	<span>비용청구</span>
	<span class="material-icons saleItemListClose ">&#xE5CD;</span>
	</div>	
	
	<form action="<c:url value="/reservation/updateTransactionState"/>" method="post" id=extraChargeFormRe>
		<div class="rentalFee">
			<span>대여료</span>
			<ul>
			<li>1일 대여비 : <span id="price"></span>원</li>
			<li>대여기간 : <span id="sharingDays"></span></li>
			<li>총 대여료 : <span id="totalPrice"></span>원</li>
			</ul>
		</div>
		<div class="annualFee">
			<span>연체료</span>
			<ul>
				<li>연체일 : <span id="overDue">0</span>일</li>
				<li>연체료(1일 대여료*150%) : <span id="overDueFee">0</span>원</li>
				<li>총 연체료 : <span id="totalOverDueFee">0</span>원</li>
			</ul>
		</div>
		<div class="saleItemAddMoney">
			<span>추가비용</span>
			<ul>
				<li><span>사유 : </span><span id="chargeReason"></span></li>
				<li><span>청구 비용 : </span><span id="demandFee"></span>
			</ul>
		</div>
		<div class="totalAmount">
			<ul>
				<li>총액 : <span id="totalMoney"></span>원</li>
				<li>결제한 금액 : <span id="paidMoney"></span>원</li>
			</ul>
		</div>
		<div class="totalCharges">
			<span>총 청구 금액 : </span><span id="charge">0</span>원
		</div>
		<div class="saleItemBtn">
			<input type="submit" value="확인">
			<input type="button" value="취소">
		</div>
		</form>
		</div>
	</body>
</html>
