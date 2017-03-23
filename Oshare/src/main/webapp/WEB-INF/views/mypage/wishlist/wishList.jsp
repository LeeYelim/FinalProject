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

	<div class="wishListForm">
		<div class="wishListTitle">
			<span>WishList</span>
		</div>
		<table id="wishListTable" class="display" cellspacing="0" width="100%"/>
		<thead>
			<tr>
				<th>제품</th>
				<th>제품명</th>
			</tr>
		</thead>
		<tbody>
		 <c:forEach items="${list}" var="wishlist">
			<tr>
			
				<td>
					<input type="text" value="${wishlist.boardNo}" hidden>
					<a href="<c:url value='/board/goodsDetail?boardNo=${wishlist.boardNo}'/>">
					<img src="<c:url value='/${wishlist.board.filePath}'/>"alt="asd"></a>
				</td>
				<td><span class="material-icons close wishListClose ">&#xE5CD;</span>
				<h3>${wishlist.board.productName}</td>
		    </tr>	
		</c:forEach>
    </tbody>
		</table>
		
	</div>
	

</body>
</html>