<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>회원가입</title>
<script type="text/javascript"
	src='<c:url value='/resources/js/jquery-2.2.4.min.js'/>'></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
	
// 필수 입력정보인 아이디, 비밀번호가 입력되었는지 확인하는 함수 Alert!!!
	function checkValue() {
		// 	  ID공백	
		if ($(".memberId").val() == "") {
			alert("아이디를 입력하세요.");
			return false;
		}
		
		// 	  비밀번호 공백
		if ($(".memberPwd1").val() == "") {
			alert("비밀번호를 입력하세요.");
			return false;
		} 

		// 	  비밀번호 확인란 공백
		if ($(".memberPwd2").val() == "") {
			alert("비밀번호를 확인하세요.");
			return false;
		} 

		//	  비밀번호와 비밀번호 확인에 입력된 값이 동일한지 확인
		if ($(".memberPwd1").val() != $(".memberPwd2").val()) {
			alert("비밀번호를 확인하세요.");
			return false;
		}

		// 	  이름 공백
		if ($(".memberName").val() == "") {
			alert("이름을 입력하세요");
			return false;
		} 

		// 	  출생연도 공백 or 4자리 미입력
		if ($(".memberBirth1").val() == ""
				|| $(".memberBirth1").val().length < 4) {
			alert("출생연도를 입력하세요");
			return false;
		} 

		// 	  생일 공백
		if ($(".memberBirth2").val() == "" || $(".memberBirth3").val() == "") {
			alert("생일을 선택하세요");
			return false;
		} 

		// 	  우편번호 미입력
		if ($(".memberAddr1").val() == "") {
			alert("주소를 입력하세요");
			return false;
		} 

		// 	  주소 미입력
		if ($(".memberAddr2").val() == "") {
			alert("주소를 입력하세요");
			return false;
		} 

		// 	  휴대전호 미선택
		if ($(".memberPhone1").val() == "") {
			alert("휴대전화 번호를 선택하세요");
			return false;
		}

		// 	  휴대전화 앞4자리 미입력
		if ($(".memberPhone2").val() == ""
				|| $(".memberPhone2").val().length < 4) {
			alert("휴대전화 번호를 입력하세요");
			return false;
		} 

		// 	  휴대전화 뒤4자리 미입력
		if ($(".memberPhone3").val() == ""
				|| $(".memberPhone3").val().length < 4) {
			alert("휴대전화 번호를 입력하세요");
			return false;
		} 

		// 	  카드사 미선택
		if ($(".memberBank").val() == "") {
			alert("카드를 선택하세요");
			return false;
		} 

		// 	  카드번호 4-1 미입력
		if ($(".memberAccount1").val() == ""
				|| $(".memberAccount1").val().length < 4) {
			alert("카드번호 번호를 입력하세요");
			return false;
		} 

		//	  카드번호 4-2 미입력			
		if ($(".memberAccount2").val() == ""
				|| $(".memberAccount2").val().length < 4) {
			alert("카드번호 번호를 입력하세요");
			return false;
		} 
		//	  카드번호 4-3 미입력
		if ($(".memberAccount3").val() == ""
				|| $(".memberAccount3").val().length < 4) {
			alert("카드번호 번호를 입력하세요");
			return false;
		} else
			return true;

		// 	  카드번호 4-4 미입력
		if ($(".memberAccount4").val() == ""
				|| $(".memberAccount4").val().length < 4) {
			alert("카드번호 번호를 입력하세요");
			return false;
		}

		// 	  유효기간 Year 4자리 미입력
		if ($(".memberValidYear").val() == ""
				|| $(".memberValidYear").val().length < 4) {
			alert("유효기간을 입력하세요");
			return false;
		}

		// 	  유효기간 Month 미선택
		if ($(".memberValidMonth").val() == "") {
			alert("유효기간을 선택하세요");
			return false;
		} 

	}// alert 발생 함수 끝

	// Jquery
	$(document).ready(function() {
		// ID 중복확인 Ajax
		$("#memberId").keyup(function() {
			$.ajax({
				url : "/controller/member/duplicateCheck",
				type : "post",
				data : "memberId=" + $(this).val(),
				dataType : "text",
				success : function(result) {
					$("#duplicateCheck").text(result);
				},
				error : function(err) {
					alert("err : " + err);
				}
			})
		}); //ID 중복확인 Ajax 끝

		//ID값에 영문 + 숫자만 입력가능
		$("input[name=memberId]").keyup(function() {
			if (!(event.keyCode >= 37 && event.keyCode <= 40)) {
				var inputVal = $(this).val();
				$(this).val(inputVal.replace(/[^a-z0-9]/gi, ''));
			}
		}); ////ID값에 영문 + 숫자만 입력가능 끝

		//ID값이 공백제거
		$("input[name=memberId]").keyup(function() {
			if ($(".memberId").val().replace(/ /g, '') == "") {

			}
		})

		//비밀번호가 일치하지 않을때 옆에 띄워주는거
		$(".memberPwd2").keyup(function() {
			if ($(".memberPwd1").val() != $(".memberPwd2").val()) {
				$(".memberPwd3").text('');
				$(".memberPwd3").html("불일치");
			} else {
				$(".memberPwd3").text('');
				$(".memberPwd3").html("일치");
			}
		})

		//숫자만 입력가능한 것들
		$(document).on("keyup", ".memberBirth1", function() { //출생연도
			$(this).val($(this).val().replace(/[^0-9]/gi, ""));
		});
		$(document).on("keyup", ".memberPhone2", function() { //휴대전화 앞4자리
			$(this).val($(this).val().replace(/[^0-9]/gi, ""));
		});
		$(document).on("keyup", ".memberPhone3", function() { //휴대전화 뒤4자리
			$(this).val($(this).val().replace(/[^0-9]/gi, ""));
		});
		$(document).on("keyup", ".memberAccount1", function() { //카드번호 첫번째 4자리 
			$(this).val($(this).val().replace(/[^0-9]/gi, ""));
		});
		$(document).on("keyup", ".memberAccount2", function() { //카드번호 두번째 4자리
			$(this).val($(this).val().replace(/[^0-9]/gi, ""));
		});
		$(document).on("keyup", ".memberAccount3", function() { //카드번호 세번째 4자리
			$(this).val($(this).val().replace(/[^0-9]/gi, ""));
		});
		$(document).on("keyup", ".memberAccount4", function() { //카드번호 네번째 4자리
			$(this).val($(this).val().replace(/[^0-9]/gi, ""));
		});
		$(document).on("keyup", ".memberValidYear", function() { //카드유효기간 4자리
			$(this).val($(this).val().replace(/[^0-9]/gi, ""));
		});

	}); //jquery 끝

	// Daum 주소API
	function sample6_execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 각 주소의 노출 규칙에 따라 주소를 조합한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var fullAddr = ''; // 최종 주소 변수
						var extraAddr = ''; // 조합형 주소 변수

						// 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
						if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
							fullAddr = data.roadAddress;

						} else { // 사용자가 지번 주소를 선택했을 경우(J)
							fullAddr = data.jibunAddress;
						}

						// 사용자가 선택한 주소가 도로명 타입일때 조합한다.
						if (data.userSelectedType === 'R') {
							//법정동명이 있을 경우 추가한다.
							if (data.bname !== '') {
								extraAddr += data.bname;
							}
							// 건물명이 있을 경우 추가한다.
							if (data.buildingName !== '') {
								extraAddr += (extraAddr !== '' ? ', '
										+ data.buildingName : data.buildingName);
							}
							// 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
							fullAddr += (extraAddr !== '' ? ' (' + extraAddr
									+ ')' : '');
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('memberAddr1').value = data.zonecode; //5자리 새우편번호 사용
						document.getElementById('memberAddr2').value = fullAddr;

						// 커서를 상세주소 필드로 이동한다.
						document.getElementById('memberAddr3').focus();
					}
				}).open();

	}// Daum 주소API 끝
</script>
</head>


<body>
	<div class="container">
		<fieldset>
			<legend class="col-lg-offset-2 col-lg-8">
				<span class="glyphicon glyphicon-user">회원가입</span>
			</legend>
			<br>
			<!--form-->
		<div class="col-md-12">			<form id="joinForm" class="form-horizontal  col-lg-offset-2 col-lg-8"
				method="post" onsubmit="return checkValue()"
				action="<c:url value="/member/joinUs"/>">

				<!-- 아이디 -->
				<div class="form-group">
					<label class="control-label col-md-2" for="id" style="float:left">아이디</label>
					<div class="col-md-6">
						<input type="text" class="memberId form-control " id="memberId" name="memberId"
							placeholder="아이디 입력" size="16" maxlength="16"> 
							</div>
							<div class="col-md-4">
							<span id="duplicateCheck">중복확인</span> <span id="memberIdCombine">(영문+숫자)조합</span>	
							</div>
				</div>

				<!-- 비밀번호 -->
				<div class="form-group">
					<label class="control-label col-md-2" for="pw">비밀번호</label>
						<div class="col-md-6">
						<input type="password" class="memberPwd1 form-control" id="memberPwd1"
							name="memberPwd1" placeholder="비밀번호 입력" size="16" maxlength="16">
						</div>
				</div>

				<!-- 비밀번호확인 -->
				<div class="form-group">
					<label class="control-label col-md-2" for="pwd">비밀번호 확인</label>
					<div class="col-md-6">
						<input type="password" class="memberPwd2 form-control" id="memberPwd2"
							name="memberPwd2" placeholder="비밀번호 확인" size="16" maxlength="16">
					</div>
					<div class="col-md-4"><span class="memberPwd3" id="memberPwd3">비밀번호 확인</span></div>
				</div>

				<!-- 이름 -->
				<div class="form-group">
					<label class="control-label col-md-2" for="name" maxlength="16">이름</label>
					<div class="col-md-6">
						<input type="text" class="memberName form-control" id="memberName"
							name="memberName" placeholder="이름" size="10" maxlength="5">
					</div>
				</div>

				<!-- 생년월일 -->
				<div class="form-group">
					<label class="control-label col-md-2" for="birth">생년월일</label>
					<div class="col-md-3">
						<input type="text" class="memberBirth1 form-control" id="memberBirth1"
							name="memberBirth1" placeholder="4자리" size="4" maxlength="4">
					</div>
					<div class="col-md-2">
						<select class="memberBirth2 form-control" id="memberBirth2" name="memberBirth2">
							<option value="">월</option>
							<option value="01">01</option>
							<option value="02">02</option>
							<option value="03">03</option>
							<option value="04">04</option>
							<option value="05">05</option>
							<option value="06">06</option>
							<option value="07">07</option>
							<option value="08">08</option>
							<option value="09">09</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
						</select></div>
						<div class="col-md-2">
						 <select class="memberBirth3 form-control" id="memberBirth3"
							name="memberBirth3">
							<option value="">일</option>
							<option value="01">01</option>
							<option value="02">02</option>
							<option value="03">03</option>
							<option value="04">04</option>
							<option value="05">05</option>
							<option value="06">06</option>
							<option value="07">07</option>
							<option value="08">08</option>
							<option value="09">09</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
							<option value="13">13</option>
							<option value="14">14</option>
							<option value="15">15</option>
							<option value="16">16</option>
							<option value="17">17</option>
							<option value="18">18</option>
							<option value="19">19</option>
							<option value="20">20</option>
							<option value="21">21</option>
							<option value="22">22</option>
							<option value="23">23</option>
							<option value="24">24</option>
							<option value="25">25</option>
							<option value="26">26</option>
							<option value="27">27</option>
							<option value="28">28</option>
							<option value="29">29</option>
							<option value="30">30</option>
							<option value="31">31</option>
						</select>
					</div>
				</div>

				<!-- 주소찾기 -->
				<div class="form-group">
					<label class="control-label col-md-2" for="addr">주소</label>
					<div class="col-md-8">
						<div class="input-group">
						<input type="text" class="memberAddr1 form-control" id="memberAddr1"
							name="memberAddr1" placeholder="우편번호"> 
							<span class="input-group-btn"> 
							<button type="button" class="btn btn-success" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">우편번호 찾기<i class="fa fa-search" aria-hidden="true"></i></button>
							</span>
						</div>
						<div class="col-md-6" style=" padding-top:10px;">
								 <input type="text"	class="memberAddr2 form-control" id="memberAddr2" name="memberAddr2"	placeholder="주소" size="40">
				 		</div>
				 		<div class="col-md-6" style="padding-right:0; padding-top:10px;">
						 		 <input type="text"	class="memberAddr3 form-control" id="memberAddr3" name="memberAddr3"	placeholder="상세주소" size="40">
						</div>
					</div>
					
					
				</div>
				<!-- 전화번호 입력 -->
				<div class="form-group">
					<label class="control-label col-md-2" for="cellphone">전화번호</label>
					<div class="col-md-3">
						<!-- 010선택 -->
						<select class="memberPhone1 form-control" id="memberPhone1" name="memberPhone1">
							<option value="">직접선택</option>
							<option value="010">010</option>
							<option value="011">011</option>
							<option value="016">016</option>
							<option value="019">019</option>
						</select>
						</div>
						<div class="col-md-3">
						<!-- 앞4자리 -->
						<input type="text" class="memberPhone2 form-control" id="memberPhone2"
							name="memberPhone2" size="4" maxlength="4"></div>
						<div class="col-md-3">
						<!-- 뒤4자리 -->
						<input type="text" class="memberPhone3 form-control" id="memberPhone3"
							name="memberPhone3" size="4" maxlength="4">
						</div>
				
				</div>

				<!-- 카드선택 -->
				<div class="form-group">
					<label class="control-label col-md-2" for="memberBank">카드선택 </label>
					<div class="col-md-4">
						<select class="memberBank form-control" id="memberBank" name="memberBank">
							<option value="">직접선택</option>
							<option value="KB국민카드">KB국민카드</option>
							<option value="신한카드">신한카드</option>
							<option value="NH농협카드">NH농협카드</option>
							<option value="IBK카드">IBK카드</option>
							<option value="하나카드">하나카드</option>
							<option value="우리카드">우리카드</option>
							<option value="씨티카드">씨티카드</option>
							<option value="삼성카드">삼성카드</option>
							<option value="현대카드">현대카드</option>
							<option value="롯데카드">롯데카드</option>
							<option value="BC카드">BC카드</option>
						</select>
					</div>
				</div>

				<!-- 카드번호 16자리 입력 -->
				<div class="form-group">
					<label class="control-label col-md-2" for="cardNumber">카드번호</label>
					<div class="col-md-2">
						<input type="text" class="memberAccount1 form-control" id="memberAccount1" name="memberAccount1" size="4" maxlength="4">
					</div>
					<div class="col-md-2">
						<input type="text" class="memberAccount2 form-control" id="memberAccount2" name="memberAccount2" size="4" maxlength="4">
				    </div>
			    	<div class="col-md-2">
						  <input type="text" class="memberAccount3 form-control" id="memberAccount3" name="memberAccount3" size="4" maxlength="4">
				    </div>
			    	<div class="col-md-2">
				    <input type="text" class="memberAccount4 form-control" id="memberAccount4" name="memberAccount4" size="4" maxlength="4">
					</div>
				</div>

				<!-- 카드 유효기간 선택/입력 -->
				<div class="form-group">
					<label class="control-label col-md-2" for="cardDate">유효기간</label>
					<div class="col-md-4">
						<!-- 입력 -->
						<input type="text" class="memberValidYear form-control" id="memberValidYear"
							name="memberValidYear" placeholder="4자리" size="4" maxlength="4">
						<!-- 선택 -->
					</div>
					<span class="col-md-1" style="padding-right:30px; padding-top:5px; width:3%;">년</span>
					<div class="col-md-4">
						<select class="memberValidMonth form-control" id="memberValidMonth"
							name="memberValidMonth">
							<option value="">월</option>
							<option value="01">01</option>
							<option value="02">02</option>
							<option value="03">03</option>
							<option value="04">04</option>
							<option value="05">05</option>
							<option value="06">06</option>
							<option value="07">07</option>
							<option value="08">08</option>
							<option value="09">09</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
						</select>
					</div>
				</div>

				<!-- 가입/취소 버튼 -->
				<div class="form-group">
					<div class="col-sm-offset-2 col-lg-10" style="text-align: center">
						<button type="submit" class="btn btn-primary" id="btn-join"	name="btn-join">회원가입
						<i class="fa fa-check spaceLeft"></i></button>
						<button type="reset" class="btn btn-danger" id="btn-cancel"	name="btn-cancel" onclick="location.href=<c:url value='/'/>">가입취소
						<i class="fa fa-times spaceLeft"></i></button>
					</div>
				</div>
				 
				<input type="hidden" name="memberBirth" value="#{}"> 
			</form>
		</div>
		</fieldset>
	</div>

</body>
</html>