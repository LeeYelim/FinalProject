<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here!!nn</title>
</head>
<body>
	<div class="container">
		<div class="col-xs-2">
			<div class="goodsListCategory">
				<ul>		
					<c:choose>
						<c:when test="${boardDTO.boardType == 'sharing'}">
							<li><a href="<c:url value='/board/goodsList'/>">${boardDTO.boardType}</a></li>	
							<li><a href="<c:url value='/board/goodsList?productCategory=취미/도서/티켓'/>">취미 / 도서 / 티켓</a></li>
							<li><a href="<c:url value='/board/goodsList?productCategory=가구/생활/주방/식품'/>">가구 / 생활 / 주방</a></li>
							<li><a href="<c:url value='/board/goodsList?productCategory=출산/유아동'/>">출산 / 유아</a></li>
							<li><a href="<c:url value='/board/goodsList?productCategory=가전/디지털/컴퓨터'/>">가전 / 디지털 / 컴퓨터</a></li>
							<li><a href="<c:url value='/board/goodsList?productCategory=패션/뷰티'/>">패션 / 뷰티</a></li>
							<li><a href="<c:url value='/board/goodsList?productCategory=스포츠/레저용품'/>">스포츠 / 레져용품</a></li>
							<li><a href="<c:url value='/board/goodsList?productCategory=명품'/>">명품</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="<c:url value='/board/goodsRentalList'/>">${boardDTO.boardType}</a></li>	
							<li><a href="<c:url value='/board/goodsRentalList?productCategory=취미/도서/티켓'/>">취미 / 도서 / 티켓</a></li>
							<li><a href="<c:url value='/board/goodsRentalList?productCategory=가구/생활/주방/식품'/>">가구 / 생활 / 주방</a></li>
							<li><a href="<c:url value='/board/goodsRentalList?productCategory=출산/유아동'/>">출산 / 유아</a></li>
							<li><a href="<c:url value='/board/goodsRentalList?productCategory=가전/디지털/컴퓨터'/>">가전 / 디지털 / 컴퓨터</a></li>
							<li><a href="<c:url value='/board/goodsRentalList?productCategory=패션/뷰티'/>">패션 / 뷰티</a></li>
							<li><a href="<c:url value='/board/goodsRentalList?productCategory=스포츠/레저용품'/>">스포츠 / 레져용품</a></li>
							<li><a href="<c:url value='/board/goodsRentalList?productCategory=명품'/>">명품</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
		<div class="col-xs-10">

			<!-- mobile goodsListCategory + goodsDetailSearch -->
			<div class="mobileGoodsListForm">
				<div class="mobileGoodsListCategory">
					<span class="material-icons goodsListPrev">&#xE408;</span>
					<ul>		
					<c:choose>
						<c:when test="${boardDTO.boardType == 'sharing'}">
							<li class="item"><a href="<c:url value='/board/goodsList'/>">${boardDTO.boardType}</a></li>	
							<li class="item"><a href="<c:url value='/board/goodsList?productCategory=취미/도서/티켓'/>">취미 / 도서 / 티켓</a></li>
							<li class="item"><a href="<c:url value='/board/goodsList?productCategory=가구/생활/주방/식품'/>">가구 / 생활 / 주방</a></li>
							<li class="item"><a href="<c:url value='/board/goodsList?productCategory=출산/유아동'/>">출산 / 유아</a></li>
							<li class="item"><a href="<c:url value='/board/goodsList?productCategory=가전/디지털/컴퓨터'/>">가전 / 디지털 / 컴퓨터</a></li>
							<li class="item"><a href="<c:url value='/board/goodsList?productCategory=패션/뷰티'/>">패션 / 뷰티</a></li>
							<li class="item"><a href="<c:url value='/board/goodsList?productCategory=스포츠/레저용품'/>">스포츠 / 레져용품</a></li>
							<li class="item"><a href="<c:url value='/board/goodsList?productCategory=명품'/>">명품</a></li>
						</c:when>
						<c:otherwise>
							<li class="item"><a href="<c:url value='/board/goodsRentalList'/>">${boardDTO.boardType}</a></li>	
							<li class="item"><a href="<c:url value='/board/goodsRentalList?productCategory=취미/도서/티켓'/>">취미 / 도서 / 티켓</a></li>
							<li class="item"><a href="<c:url value='/board/goodsRentalList?productCategory=가구/생활/주방/식품'/>">가구 / 생활 / 주방</a></li>
							<li class="item"><a href="<c:url value='/board/goodsRentalList?productCategory=출산/유아동'/>">출산 / 유아</a></li>
							<li class="item"><a href="<c:url value='/board/goodsRentalList?productCategory=가전/디지털/컴퓨터'/>">가전 / 디지털 / 컴퓨터</a></li>
							<li class="item"><a href="<c:url value='/board/goodsRentalList?productCategory=패션/뷰티'/>">패션 / 뷰티</a></li>
							<li class="item"><a href="<c:url value='/board/goodsRentalList?productCategory=스포츠/레저용품'/>">스포츠 / 레져용품</a></li>
							<li class="item"><a href="<c:url value='/board/goodsRentalList?productCategory=명품'/>">명품</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
					<span class="material-icons goodsListNext">&#xE409;</span>
				</div>
			</div>

			<div class="col-xs-6">
				<div class="goodsImg">
					<img src="<c:url value='/'/>${boardDTO.filePath}" alt="goodsDetailMainImg">
				</div>
				<div class="sellerInformation">
					<ul>
					<input type="text" name='goodsDetailBoardNo' value="${boardDTO.boardNo}" hidden> 
					<input type="text" name='user' value="${sessionScope.loginMemberId}" hidden disabled>
					<li>판매자 정보 : <span class="sellerName">${boardDTO.memberDTO.memberName}</span></li>
						<li>판매자 아이디 : <span class="sellerId">${boardDTO.memberDTO.memberId}</span></li>
						<li><div class="rentalPointForm">
								대여 횟수 <span class="rentalPoint">${boardDTO.memberDTO.memberSharingCount}</span>
							</div>
								
							<div class="starGrade">
							<input type="text" name="starGrade" value="${gradeDTO.memberPoint}" hidden>
								<span class="sellerStar"></span>
							</div>
							<c:if test="${!empty sessionScope.loginMemberId}">
							<div class="declaration">
								<img src="<c:url value='/resources/image/siren.png'/>"
									alt="신고버튼">
							</div></li>
							</c:if>
						<li><div class="sellerSub">
						<c:choose>
							<c:when test="${empty sessionScope.loginMemberId}"><span style="width:100%">프로필보기</span></c:when>
							<c:otherwise><span>프로필보기</span><span>쪽지보내기</span></c:otherwise>
						</c:choose>
							</div></li>
					</ul>
				</div>
				<c:set value="${boardDTO.memberDTO.memberId}" var="memberId"/>
				<c:if test="${sessionScope.loginMemberId == memberId || sessionScope.loginMemberId == 'admin'}">
				<div class="goodsDetailChage">
					<span><a href="<c:url value='/editor/boardUpdate?boardNo=${boardDTO.boardNo}'/>">수정하기</a></span><span><a href="<c:url value='/board/boardDelete?boardNo=${boardDTO.boardNo}'/>">삭제하기</a></span>
				</div>
				</c:if>
			</div>
			<div class="col-xs-6">
				<div class="goodsDetailIcon">
					 <span class="material-icons" id="cartIcon">&#xE8CC;</span>
				</div>
				<div class="goodsDetailInformation">
					<ul>
						<li>제품이름 : <span class="goodsName">${boardDTO.productName}</span></li>
						<li>대여비 : <span class="goodsPrice">${boardDTO.price}</span>원/일</li>
						<li>상품상태 : <span class="goodsCondition">${boardDTO.condition}</span></li>
						
					</ul>
				</div>
				<div id="calendar"></div>
				<div class="goodsPayment">
					<form name="requestForm" method=post action="">
						<c:if test="${!empty sessionScope.loginMemberId}"><button type="button">결제하기</button></c:if>
					</form>
				</div>
			</div>

			<div class="col-xs-12">
				<ul class="nav nav-tabs goodsDetailNavaction">
					<li class="active"><a data-toggle="tab"
						href="#goodsInformation">상품정보</a></li>
					<li><a data-toggle="tab" id="ww" href="#goodsReview">상품후기</a></li>
					<li><a data-toggle="tab" id="qq" href="#goodsQuestion">Q &amp; A</a></li>
					<li><a data-toggle="tab" href="#instructionsResume">안내사항</a></li>
				</ul>

				<div class="tab-content">
					<div id="goodsInformation" class="tab-pane fade in active">
					${boardDTO.detail}
					</div>
					<div id="goodsReview" class="tab-pane fade">
						<div class="goodsReviewFrame">

						</div>

						<div class="goodsReviewPlus">
							<c:if test="${!empty sessionScope.loginMemberId}" ><span>후기남기기</span></c:if>
						</div>

						
					</div>
					<div id="goodsQuestion" class="tab-pane fade">
						<div class="goodsQuestionFrame">
						
						</div>
						<div class="goodsQuestionPlus">
							<c:if test="${!empty sessionScope.loginMemberId}" ><span>질문하기</span></c:if>
						</div>
						
					</div>
					<div id="instructionsResume" class="tab-pane fade">
						<img src="<c:url value='/resources/image/useguide1.PNG'/>"
							alt="goodsDetailMainImg"><p>
						<img src="<c:url value='/resources/image/useguide2.PNG'/>"
							alt="goodsDetailMainImg"><p>	
						<img src="<c:url value='/resources/image/useinfo1.PNG'/>"
							alt="goodsDetailMainImg"><p>	
						<img src="<c:url value='/resources/image/useinfo2.PNG'/>"
							alt="goodsDetailMainImg"><p>
						<img src="<c:url value='/resources/image/useinfo3.PNG'/>"
							alt="goodsDetailMainImg">	
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="saleProfile">
		<div class="saleProfileTitle">
			<span>프로필</span> <span class="material-icons saleProfileClose">&#xE5CD;</span>
		</div>
		<div class="saleProfileImg">
			<img src="<c:url value='/resources/image/sampleProfile.png'/>"
				alt="프로필이미지">
		</div>
		<div class="saleProfileImformation">
			아이디 : <span>${boardDTO.memberDTO.memberId}</span>
		</div>
		<span></span>
		<div class="saleReview">
			<span>판매자 후기</span>
			<table id="saleReviewTable" class="display" cellspacing="0" width="100%">
			  <thead>
				<tr>
					<th>글번호</th>
					<th>내용</th>
					<th>작성일</th>
					<th>작성자</th>
				</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<div class="saleProduct">
			<span>판매중인 제품</span>
			<table id="saleProductTable" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>글번호</th>
					<th>제품</th>
					<th>제품명</th>
				</tr>
			</thead>
			<tbody>
				
			</tbody>
			</table>
			
		</div>
	</div>
	<div class="saleMessage">
		<div class="saleMessageTitle">
			<span>쪽지쓰기</span> <span class="material-icons saleMessageClose">&#xE5CD;</span>
		</div>
		<form>
		<input type="text" value="${sessionScope.loginMemberId}" name="sender" hidden>
			<div class="saleMessageRecipient">
				<span>받는이 </span> <input type="text" name="receiver">
			</div>
			<div class="saleMessageContent">
				<span>쪽지내용</span>
				<textarea name="content"></textarea>
			</div>
			<div class="saleMessageBtn">
				<input type="button" value="쪽지보내기">
			</div>
		</form>
	</div>
	<div class="declarationDialog">
		<div class="declarationTitle">
			<span>신고하기</span> <span class="material-icons declarationClose">&#xE5CD;</span>
		</div>
		<form action="" method="">
			<div class="declarationList">
				<span>신고사유</span> <select name="declarationType">
					<option value="비속어사용">비속어사용</option>
					<option value="태도분량">태도분량</option>
					<option value="타홍보">타홍보</option>
					<option value="사기성">사기성</option>
				</select>
			</div>
			<input type="text" name="declarationSubject" value="${sessionScope.loginMemberId}"  hidden>
			<input type="text" name="declarationReporter" value="${boardDTO.memberDTO.memberId}" hidden >
			<div class="reasonForReport">
				<span>신고이유</span>
				<textarea name="declarationReason"></textarea>
			</div>
			<div class="declarationBtn">
				<input type="button" value="전송"> 
			</div>
		</form>
	</div>
	
</body>
</html>