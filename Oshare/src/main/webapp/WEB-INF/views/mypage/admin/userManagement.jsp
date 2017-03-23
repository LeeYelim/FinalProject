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
	<div id="userManagement">
		<ul class="nav nav-tabs">
			<li class="active"><a data-toggle="tab"	href="#userManagementAllUser">전체회원</a></li>
			<li><a data-toggle="tab" href="#userManagementDeclaration">신고당한회원</a></li>
			<li><a data-toggle="tab" href="#userManagementStop">정지회원</a></li>
		</ul>

		<div class="tab-content">
			<div id="userManagementAllUser" class="tab-pane fade in active">
	
					<table id="userManagementTable" class="display" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th><input type="checkbox" class="allCheck"></td>
							<th>아이디</th>
							<th>비밀번호</th>
							<th>이름</th>
							<th>생년월일</th>
							<th>주소</th>
							<th>계좌번호</th>
							<th>계정</th>
							<th>유효한 달</th>
							<th>유효한 년도</th>
							<th>공유수</th>
							<th>전화번호</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${requestScope.allUserMemberDTO}" var="userAllList">
						<tr>
							<td><input type="checkbox" name="allUserCheckBox" value="${userAllList.memberNo}" ></td>
							<td>${userAllList.memberId}</td>
							<td>${userAllList.memberPwd}</td>
							<td>${userAllList.memberName}</td>
							<td>${userAllList.memberBirth}</td>
							<td>${userAllList.memberAddr}</td>
							<td>${userAllList.memberAccount}</td>
							<td>
							<c:choose>
								<c:when test="${userAllList.memberGrade == 0}">
									일반
								</c:when>
								<c:when test="${userAllList.memberGrade == 1}">
									신고유저
								</c:when>
								<c:when test="${userAllList.memberGrade == 2}">
									정지유저
								</c:when>
								<c:otherwise>
									어드민
								</c:otherwise>
							</c:choose>
							</td>
							<td>${userAllList.memberValidMonth}</td>
							<td>${userAllList.memberValidYear}</td>
							<td>${userAllList.memberSharingCount}</td>
							<td>${userAllList.memberPhone}</td>
						</tr>
					</c:forEach>
					 </tbody>
				 </table>
				<div class="userManagementBtn">	
						<input type="button" value="신고">
						<input type="button" value="정지">
				</div>
			</div>
			<div id="userManagementDeclaration" class="tab-pane fade">
				
				<table id="userManagementDeclarationTable" class="display" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th><input type="checkbox" class="allCheck"></td>
							<th>아이디</th>
							<th>비밀번호</th>
							<th>이름</th>
							<th>생년월일</th>
							<th>주소</th>
							<th>계좌번호</th>
							<th>계정</th>
							<th>유효한 달</th>
							<th>유효한 년도</th>
							<th>공유수</th>
							<th>전화번호</th>
							<th>신고사유</th>
						</tr>
					</thead>
					<tbody>
		
					 </tbody>
					</table>
				<div class="userManagementBtn">	
						<input type="button" value="신고취소">
						<input type="button" value="정지">
				</div>
			</div>
			<div id="userManagementStop" class="tab-pane fade">
			
			<table id="userManagementStopTable" class="display" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th><input type="checkbox" class="allCheck"></td>
							<th>아이디</th>
							<th>비밀번호</th>
							<th>이름</th>
							<th>생년월일</th>
							<th>주소</th>
							<th>계좌번호</th>
							<th>계정</th>
							<th>유효한 달</th>
							<th>유효한 년도</th>
							<th>공유수</th>
							<th>전화번호</th>
							<th>신고사유</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="checkbox" name=""></td>
							<td>DEOND1234</td>
							<td>awec8745</td>
							<td>아이유</td>
							<td>1993.05.16</td>
							<td>경기 성남시 분당구 판교동</td>
							<td>205-4588-4755-6421</td>
							<td>일반</td>
							<td>12</td>
							<td>22</td>
							<td>14</td>
							<td>010-4843-5484</td>
							<td><input type="button" value="정지사유"></td>
						</tr>
					 </tbody>
					</table>
				<div class="userManagementBtn">	
						<input type="button" value="신고">
						<input type="button" value="정지취소">
				</div>
				
			</div>
			
		</div>
	</div>
	
	<div class="reasonForFiling">
	<div class="reasonForFilingTitle">
		<span class="material-icons reasonForFilingClose">&#xE5CD;</span>
		 <span>신고/정지 사유</span>
	 </div>
		<table id="reasonForFilingTable">
			<thead>
				<tr>
					<th>번호</th>
					<th>신고자</th>
					<th>신고대상자</th>
					<th>신고타입</th>
					<th>신고이유</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	

</body>
</html>
