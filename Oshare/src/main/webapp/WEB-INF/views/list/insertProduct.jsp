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
		        if($("#title").val() == ""){
		    		alert("제목을 입력해주세요");
		    		$("#title input[name=title]").focus();
		    		return;
		        }
		        if($("#sharingPrice").val()==""){
		    		alert("일대비여비용을 입력해주세요")
		    		$("#sharingPrice").focus();
		    		return;
		        }
		        if($("#thumbnailImage").val()==""){
		    		alert("썸네일 이미지를 넣어주십시오");
		    		return;
		        }
				if($("#editor").val()==""){
		    		alert("상세내용을 입력해주십시오");
		    		$("#editor").focus();
		    		return;
		    	}
		        $("#frm").submit();
		    	
	    	})
		});
	</script>
</head>
<body>

<div class="container">
	
	<!-- action : 에디터에 입력한 html 코드를 전달받을 Controller페이지 URL -->
	<form action="<c:url value='/editor/submit'/>" method="post" id="frm" class="form-horizontal" enctype="multipart/form-data" >
		<fieldset>
		<c:choose>
			<c:when test="${requestScope.boardType == 'sharing'}">
				<legend>셰어링 글 등록</legend>
			</c:when>
			<c:otherwise>
				<legend>렌탈 글 등록</legend>
			</c:otherwise>
		</c:choose>
			
		
			<input type="hidden" value="${boardType}" name="boardType">
			<input type="hidden" value="${sessionScope.loginMemberId}" name="memberId">
			<input type="hidden" value="${sessionScope.loginMemberName}" name="memberName">			
			
			<div class="form-group">
				<label for="selectCategory" class="control-label col-lg-2">카테고리</label>	
				<div class="col-lg-8">
					<select class="form-controler" id="selectCategory" name="selectCategory">
						<option>취미/도서/티켓</option>		
						<option>가구/생활/주방/식품</option>		
						<option>출산/유아동</option>		
						<option>가전/디지털/컴퓨터</option>		
						<option>패션/뷰티</option>		
						<option>스포츠/레저용품</option>		
						<option>명품</option>		
					</select>
				</div>
				<div class="col-lg-2"></div>
			</div>
			
			<div class="form-group">
				<label for="title" class="control-label col-lg-2">제목</label>
				<div class="col-lg-6">
					<input type="text" name="title" id=title" class="form-control" placeholder="제목을 입력해주세요">
				</div>
				<div class="col-lg-4"></div>
			</div>
			
			<div class="form-group">
				<label for="sharingPrice" class="control-label col-lg-2">일대여비용</label>
				<div class="col-lg-3">
					<input type="text" name="sharingPrice" id=sharingPrice" class="form-control" placeholder="일대여비용을 입력해주세요">
				</div>
				<div class="col-lg-1"><p class="form-control-static">원/일</p></div>
				
				<label for="productState" class="control-label col-lg-1">물건상태</label>
				<div class="col-lg-2">
					<select class="form-controler" id="productState" name="productState">
						<option>상</option>		
						<option>중</option>		
						<option>하</option>		
					</select>
				</div>
				<div class="col-lg-2"></div>
			</div>
			
			<div class="form-group">
				<label for="thumbnailImage" class="control-label col-lg-2">썸네일</label>
				<div class="col-lg-3">
					<input type="file" id="thumbnailImage" name="file">
				</div>
			</div>
			
			<div class="form-group">
				<label for="editor" class="control-label col-lg-2">상세내용</label>
		    	<div class="col-lg-9">
		    		<textarea name="editor" id="editor" rows="20" cols="118"></textarea>
		    	</div>
		    	<div class="col-lg-1"></div>
			</div>
			
			<div class="form-group">
		    	<button type="button" id="savebutton" value="서버전송" class="btn btn-default col-lg-offset-9 col-lg-1">전송</button>
		    	<button type="reset" id="reset" class="btn btn-default col-lg-1">취소</button>
		    	<div class="col-lg-1"></div>
			</div>
	
		</fieldset>		
	</form>
</div>

</body>
</html>













