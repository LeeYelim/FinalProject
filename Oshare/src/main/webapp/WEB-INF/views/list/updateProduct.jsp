<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
<!-- 	<meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!-- 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<!-- 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
	<script type="text/javascript" src="<c:url value='/resources/editor/js/HuskyEZCreator.js'/>" charset="utf-8"></script>
	<script type="text/javascript">
		$(function(){
			
		    //전역변수
		    var obj = [];    
		    
		    //스마트에디터 프레임생성
		    nhn.husky.EZCreator.createInIFrame({
		        oAppRef: obj,
		        elPlaceHolder: "editor",
		        sSkinURI: "<c:url value='/resources/editor/SmartEditor2Skin.html'/>", 
		        htParams : {
		            // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
		            bUseToolbar : true,             
		            // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
		            bUseVerticalResizer : true,     
		            // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
		            bUseModeChanger : true, 
		        }
		    });
		    
		    //전송버튼
		    $("#savebutton").click(function(){
		        //id가 smarteditor인 textarea에 에디터에서 대입
		        obj.getById["editor"].exec("UPDATE_CONTENTS_FIELD", []);
		        //폼 submit
		        $("#frm").submit();
		    })
		});
	</script>
</head>
<body>

<div class="container">
	
	<!-- action : 에디터에 입력한 html 코드를 전달받을 Controller페이지 URL -->
	<form action="<c:url value='/editor/updateSubmit'/>" method="post" id="frm" class="form-horizontal" enctype="multipart/form-data">
		<fieldset>
		<c:choose>
			<c:when test="${boardDTO.boardType == 'sharing'}">
				<legend>셰어링 글 수정</legend>
			</c:when>
			<c:otherwise>
				<legend>렌탈 글 수정</legend>
			</c:otherwise>
		</c:choose>
			

			<input type="hidden" value="${sessionScope.loginMemberId}" name="memberId">			
			<input type="hidden" value="${boardDTO.boardNo}" name="boardNo">
			<div class="form-group">
				<label for="selectCategory" class="control-label col-lg-2">카테고리</label>	
				<div class="col-lg-8">
					${boardDTO.category}
				</div>
				<div class="col-lg-2"></div>
			</div>
			
			<div class="form-group">
				<label for="title" class="control-label col-lg-2">제목</label>
				<div class="col-lg-6">
					<input type="text" name="productName" id=title" class="form-control" value="${boardDTO.productName}">
				</div>
				<div class="col-lg-4"></div>
			</div>
			
			<div class="form-group">
				<label for="sharingPrice" class="control-label col-lg-2">일대여비용</label>
				<div class="col-lg-3">
					<input type="text" name="price" id=sharingPrice" class="form-control" value="${boardDTO.price}">
				</div>
				<div class="col-lg-1"><p class="form-control-static">원/일</p></div>
				
				<label for="productState" class="control-label col-lg-1">물건상태</label>
				<div class="col-lg-2">
					<select class="form-controler" id="productState" name="condition">
						<option>상</option>		
						<option>중</option>		
						<option>하</option>		
					</select>
				</div>
				<div class="col-lg-2"></div>
			</div>
			<div class="form-group">
				<label for="editor" class="control-label col-lg-2">상세내용</label>
		    	<div class="col-lg-9">
		    		<textarea name="detail" id="editor" rows="20" cols="118">${boardDTO.detail}</textarea>
		    	</div>
		    	<div class="col-lg-1"></div>
			</div>
			
			<div class="form-group">
		    	<button type="button" id="savebutton" value="서버전송" class="btn btn-default col-lg-offset-9 col-lg-1">수정</button>
		    	<button type="reset" id="reset" class="btn btn-default col-lg-1">취소</button>
		    	<div class="col-lg-1"></div>
			</div>
	
		</fieldset>		
	</form>
</div>

</body>
</html>













