<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<form action="" method="">
			<div class="shoppingBasketForm">
				<div class="shoppingBasketTitle">
				<span>장바구니</span>
				</div>
				<table>
					<tr>
						<td><input type="checkbox" disabled></td>
						<td>제품</td>
						<td>제품명</td>
						<td>예약일</td>
						<td>총액</td>
					</tr>
	<c:choose>
    <c:when test="${empty requestScope.cartList}">
	<tr>
        <td colspan="5" style="width:100%;">
            <p align="center"><b><span>등록된 상품이 없습니다.</span></b></p>
        </td>
    </tr>
    </c:when>
    <c:otherwise>
	<c:forEach items="${requestScope.cartList}" var="cartDTO">
		    <tr>
		    	
		     	<td>
		            <input type="checkbox" name="check">
		        </td>
		        <td>
					<span>
							<a href="<c:url value='/board/goodsDetail?boardNo=${cartDTO.boardNo}'/>">
					<img src="<c:url value='${cartDTO.filePath}'/>"alt="장바구니 이미지">
					</a>
					</span>
		        </td>
		         <td>
		           <span>${cartDTO.productName}</span>
		        </td>
		        <td>
		         <span>${cartDTO.cartStart}~${cartDTO.cartEnd }</span>
		        </td>
		         
		        <td>
		           <span>
		            <fmt:formatNumber value="${cartDTO.cartPrice}"/></span>
		        </td>
		    </tr>
    </c:forEach>
	</c:otherwise>
    </c:choose>
</table>
			</div>
			<div class="shoppingBasketTotalAmountForm">
				<div class="shoppingBasketTotalAmount">
					<span>총액 : <span> <fmt:formatNumber value="${total}"/></span>원</span>
				</div>
				<div class="shoppingBasketTotalAmountBtn">
					<input type="button" name="paymen" value="결제하기">
				</div>
			</div>
		</form>
	</div>
	<br>
</body>
</html>