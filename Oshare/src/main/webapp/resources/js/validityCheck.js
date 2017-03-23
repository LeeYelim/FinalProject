
// 헤더 검색 header
function headerSearchValidityCheck(){
	if($(".HeaderSearchForm form input[type=text]").val() == ""){
		alert("검색어를 입력해주세요");
		return false;
	}
	return true;
}

// 헤더 모바일 검색 header
function mobileHeaderSearchValidityCheck(){
	if($(".mobileHeaderNavactionMenuSearch input[type=text]").val() == ""){
		alert("검색어를 입력해주세요");
		$(".mobileHeaderNavactionMenuSearch input[type=text]").focus();
		return false;
	}
	return true;
}

//모바일 로그인 Login
function mobileLoginValidityCheck(){
	if($("#mobileLogin .mobileLoginForm input[name=memberId]").val()== ""){
		alert("아이디를 입력해주세요");
		$("#mobileLogin .mobileLoginForm input[name=memberId]").focus();
		return false;
	}
	if($("#mobileLogin .mobileLoginForm input[name=memberPwd]").val()== ""){
		alert("비밀번호를 입력해주세요");
		$("#mobileLogin .mobileLoginForm input[name=memberPwd]").focus();
		return false;
	}
	return true;
}

//리스트 상세보기 뷰 검색 goodsDetail + goodsList
function detailSearchValidityCheck(){
	if($(".goodsReservation input[name=goodsReservation]").val() == ""){
		alert("예약일을 입력해주세요")
		$(".goodsReservation input[name=goodsReservation]").focus();
		return false;
	}
	
	if($(".goodsSearch input[name=goodsSearch]").val() == ""){
		alert("검색어을 입력해주세요")
		$(".goodsSearch input[name=goodsSearch]").focus();
		return false;
	}
	return true;
}

//모바일 리스트 상세보기 뷰 검색 goodsDetail + goodsList
function mobileDetailSearchValidityCheck(){
	if($(".mobileGoodsReservation input[name=goodsReservation]").val() == ""){
		alert("예약일을 입력해주세요")
		$(".mobileGoodsReservation input[name=goodsReservation]").focus();
		return false;
	}
	if($(".mobileGoodsSearch input[name=goodsSearch]").val() == ""){
		alert("검색어을 입력해주세요")
		$(".mobileGoodsSearch input[name=goodsSearch]").focus();
		return false;
	}
	return true;
}

//비용청구  saleItem
function saleItemAddMoneyValidityCheck(){
	if($(".saleItemAddMoney textarea").val() == ""){
		alert("사유를 입력해주세요");
		$(".saleItemAddMoney textarea").focus();
		return false;
	}
	
	if($(".saleItemAddMoney input[type=number]").val() == ""){
		alert("청구 비용을 입력해주세요")
		$(".saleItemAddMoney input[type=number]").focus();
		return false;
	}
	
	return true;
}

//반납신청 rentalItem
function rentalItemValidityCheck(){
	if($(".sellerEvaluation textarea").val() == ""){
		alert("판매자 평가을 입력해주세요 ");
		$(".sellerEvaluation textarea").focus();
		return false;
	}
	return true;
}

//쪽지보내기 message
var receiverBoolean = true;

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

//회원관리 userManagement
function userManagementValidityCheck(){
if($(".userManagementSearch select").val() == "전체회원"){
	return true;
}else{
	if($(".userManagementSearch input[type=text]").val() ==""){
		alert("검색어을 입력해주세요");
		$(".userManagementSearch input[type=text]").focus();
		return false;
	}
}
	return true;
}




