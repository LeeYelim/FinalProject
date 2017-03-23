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
	<div class="container">
		<div class="col-xs-2">
			<div class="goodsListCategory">
				<ul>		
					<c:choose>
						<c:when test="${boardType == 'sharing'}">
							<li><a href="<c:url value='/board/goodsList'/>">${boardType}</a></li>	
							<li><a href="<c:url value='/board/goodsList?productCategory=취미/도서/티켓'/>">취미 / 도서 / 티켓</a></li>
							<li><a href="<c:url value='/board/goodsList?productCategory=가구/생활/주방/식품'/>">가구 / 생활 / 주방</a></li>
							<li><a href="<c:url value='/board/goodsList?productCategory=출산/유아동'/>">출산 / 유아</a></li>
							<li><a href="<c:url value='/board/goodsList?productCategory=가전/디지털/컴퓨터'/>">가전 / 디지털 / 컴퓨터</a></li>
							<li><a href="<c:url value='/board/goodsList?productCategory=패션/뷰티'/>">패션 / 뷰티</a></li>
							<li><a href="<c:url value='/board/goodsList?productCategory=스포츠/레저용품'/>">스포츠 / 레져용품</a></li>
							<li><a href="<c:url value='/board/goodsList?productCategory=명품'/>">명품</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="<c:url value='/board/goodsRentalList'/>">${boardType}</a></li>	
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
					<ul class="owl-carousel">
					<c:choose>
						<c:when test="${boardType == 'sharing'}">
							<li class="item"><a href="<c:url value='/board/goodsList'/>">${boardType}</a></li>	
							<li class="item"><a href="<c:url value='/board/goodsList?productCategory=취미/도서/티켓'/>">취미 / 도서 / 티켓</a></li>
							<li class="item"><a href="<c:url value='/board/goodsList?productCategory=가구/생활/주방/식품'/>">가구 / 생활 / 주방</a></li>
							<li class="item"><a href="<c:url value='/board/goodsList?productCategory=출산/유아동'/>">출산 / 유아</a></li>
							<li class="item"><a href="<c:url value='/board/goodsList?productCategory=가전/디지털/컴퓨터'/>">가전 / 디지털 / 컴퓨터</a></li>
							<li class="item"><a href="<c:url value='/board/goodsList?productCategory=패션/뷰티'/>">패션 / 뷰티</a></li>
							<li class="item"><a href="<c:url value='/board/goodsList?productCategory=스포츠/레저용품'/>">스포츠 / 레져용품</a></li>
							<li class="item"><a href="<c:url value='/board/goodsList?productCategory=명품'/>">명품</a></li>
						</c:when>
						<c:otherwise>
							<li class="item"><a href="<c:url value='/board/goodsRentalList'/>">${boardType}</a></li>	
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
			<div id="goodsListItemForm">
			<c:if test="${empty list}">
			  <div class="BoardNoPosts">
				<img src="<c:url value='/resources/image/prohibition.png'/>" alt="게시물이 없을 경우 나오는 이미지">
				<h1>게시물이 존재하지 않습니다</h1>
				<a href="<c:url value='/'/>">메인페이지로 돌아가기</a>
			  </div>
			</c:if>
			<c:forEach items="${list}" var="sharingItem">
				<div class="goodsListItem">
					<ul>
						<li><img src="<c:url value='${sharingItem.filePath}'/>" alt="book">
						<a href="<c:url value='/board/goodsDetail?boardNo=${sharingItem.boardNo}'/>">
						<span class="material-icons goodsDetailViewSee">&#xE8F4;</span>
						</a></li>
						<li>제품명 : ${sharingItem.productName}</li>
						<li>가격 : ${sharingItem.price}원 / 일</li>
						<li>
						<script type="text/javascript">
							var className="material-icons heartWish";
						</script>
						
						<c:choose>
							<c:when test="${empty wishs}">
								<span class="material-icons heartWish" id="${sharingItem.boardNo}" >&#xE87D;</span>
							</c:when>
							<c:otherwise>
								<span class="material-icons heartWish" id="${sharingItem.boardNo}">&#xE87D;</span>
								<c:forEach items="${wishs}" var="wish">
									<c:choose>
									<c:when test="${sharingItem.boardNo eq wish.boardNo}">
										<script type="text/javascript">
											$(function() {
												$("#${sharingItem.boardNo}").css({"color":"#ff0000"});
											})									
										</script>						
									</c:when> 
									</c:choose>
								</c:forEach>
							</c:otherwise> 
						</c:choose>
							
						</li>
					</ul>
				</div>
				</c:forEach>
				<c:if test="${!empty sessionScope.loginMemberId}">
				<div class="goodsListInsertBtn">
				<a href="<c:url value='/editor/insertProduct'/>?boardType=${requestScope.boardType}"><span class="glyphicon glyphicon-pencil">제품등록</span></a>
				</div>
				</c:if>
				
				<div class="goodsListNavaction">
					<c:if test="${count>0}">${pagingHtml}</c:if>
				</div>		
			</div>
		</div>
	</div>

</body>
</html>