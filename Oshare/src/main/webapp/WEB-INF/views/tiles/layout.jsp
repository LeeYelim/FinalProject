<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/bootstrap.min.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/owl.carousel.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/jquery.rateyo.css'/>">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link href="<c:url value='/resources/css/fullcalendar.css'/>" rel="stylesheet" />
<link href="<c:url value='/resources/css/fullcalendar.print.css'/>" rel='stylesheet' media='print' />
<link href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" rel="stylesheet">
<link href="https://cdn.datatables.net/1.10.13/css/dataTables.jqueryui.min.css" rel="stylesheet">
<link href="<c:url value='/resources/css/font-awesome.min.css'/>" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/style.css'/>">
<title>Oshare</title>
<style>
#calendar {
	width: 495px;
	height: 600px;
	margin: 0 auto;
}
</style>
</head>
<body>
	<div id="main">

		<header>
			<tiles:insertAttribute name="header" />
		</header>

		<div id="contentTiles">
			<tiles:insertAttribute name="content" />
		</div>

		<footer>
			<tiles:insertAttribute name="footer" />
		</footer>
	</div>

	<div class="dialogBlack"></div>


	<script type="text/javascript" src="<c:url value='/resources/js/jquery-2.2.4.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/owl.carousel.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/jquery.rateyo.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/index_js.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/validityCheck.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/SmoothScroll.js'/>"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/1.10.13/js/dataTables.jqueryui.min.js"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/dataTable.js'/>"></script>
	<script src="<c:url value='/resources/js/moment.min.js'/>"></script>
	<script src="<c:url value='/resources/js/fullcalendar.min.js'/>"></script>
	<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.2.js"></script>

	<script>
	/* 
		1. 달력에 예약 날짜 보이기-select 
		2. 일정에 추가-insert
	*/
	$(document).ready(function() {
		var boardNo = ${boardDTO.boardNo};
		var today = new Date(); // 오늘 날짜
		var calendar = $('#calendar').fullCalendar({
			header: {
				left: 'prev,next',
				center: 'title',
				right: 'month'
			},
			defaultDate: today,
			navLinks: true, // can click day/week names to navigate views
			editable: true,
			selectable: true,
		    selectHelper: true,
		    select: function(start, end) {
		    	var reservations = $('#calendar').fullCalendar('clientEvents');  // 달력에 있는 모든 이벤트(예약)
		    	
		    	if(start<today) { // 오늘 이전 select X
					alert("오늘 이후로 신청 가능합니다."); 
					calendar.fullCalendar('unselect');
					return;
				 }
		    	
		    	// 달력에 그려진 내 예약 지우기
		    	for (var i = 0; reservations.length !== 0 && i < reservations.length; i++) {
		        	if(reservations[i].id=="myReservation") { 
		        		$('#calendar').fullCalendar('removeEvents', reservations[i].id);
		        	}
				};
		    	
				var duration = (end - start)/1000;
				if(duration == 1800) {
					// set default duration to 1 hr.
		         	end = start.add(30, 'mins');
		           return calendar.fullCalendar('select', start, end);
		        }
				
				var title = "내예약";
		        var eventData;
		        if (title && title.trim()) {
		        	eventData = {
		        			id : "myReservation",
		        			title: title,
		        			start: start,
		             		end: end,
		             		color : "#EE8787"
		           };
		           if(isOverlapping(start, end)==true) { // 현재 달력과 겹치는 일정이라면 select 불가, 겹치지 않다면 예약 추가
		        	   calendar.fullCalendar('renderEvent', eventData, true);
		           }
		         } 
		         calendar.fullCalendar('unselect');
		    },
			eventLimit: true, // allow "more" link when too many events
			eventResize : function( event, delta, revertFunc, ui, view ) {
				alertEvent(event, revertFunc);
			},
			eventDrop : function( event, delta, revertFunc, ui, view ) {
				alertEvent(event, revertFunc);
			}, 
	         /*
	         	DB의 일정 불러와 달력에 추가
	         **/
			events: function() {
				$.ajax({
					url:"/controller/reservation/selectByBoardNo",
					type:"post",
					dataType:"json",
					data:"boardNo="+boardNo, //임의로 값 넣음!!!
					allDay:true,
					success : function(result) { // List<ReservationDTO>
					var reservations = $('#calendar').fullCalendar('clientEvents'); // 달력에 있는 모든 이벤트(예약)
						
						// 달력에 그려진 모든 예약일정 지우기
						for (var i = 0; reservations.length !== 0 && i < reservations.length; i++) {
							if(reservations[i].id!="myReservation") { 
								$('#calendar').fullCalendar('removeEvents', reservations[i].id);
							}
						}
						// DB의 예약일정 달력에 추가
						$.each(result, function(index, item){
							var reservation = {
								id : item.boardNo, //1
								title : "예약불가",
								start : moment(item.startDate).format('YYYY-MM-DD'),
								end : moment(item.endDate).add(1, 'days').format('YYYY-MM-DD'), 
								editable : false,
								allDay:true,
								color:"#887BF8"
							}
							calendar.fullCalendar('renderEvent', reservation, true); 
						})  
					},
					error : function(err) {
					}
				})
			}
		});
		
		 // 달력에 존재하는 일정과 겹치는 지 확인
		function isOverlapping(start, end) {
			var startTime = new Date(start).getTime();
			var endTime = new Date(end-(1000*60*60*24)).getTime();
		    var reservations = calendar.fullCalendar('clientEvents');
		    var i;
		    for (i = 0; reservations.length !== 0 && i < reservations.length; i++) {
		    	var arrayStartTime = new Date(reservations[i].start).getTime();
		    	var arrayEndTime = new Date(reservations[i].end-(1000*60*60*24)).getTime();
		    	if(((arrayStartTime<=startTime) && (arrayEndTime>=endTime)) || ((arrayStartTime >= startTime) && (arrayStartTime<=endTime))
		    			|| ((arrayEndTime>=startTime)&&(arrayEndTime<=endTime))) {
		    		alert("==st"+startTime);
		    		return false; // 추가X
		        } 
		    }
		    return true;
		}
		
		function alertEvent(event, revertFunc) {
			if(event.start<today) {
				alert("오늘 이후로 신청 가능합니다."); 
				revertFunc();
			} 
		}
		
		$(".goodsPayment").click(function() {
			var result = confirm('신청하시겠습니까');

	        if(result) {
	        	var eventArray = $('#calendar').fullCalendar('clientEvents');
	        	for (var i = 0; eventArray.length !== 0 && i < eventArray.length; i++) {
	        		if(eventArray[i].id=="myReservation") { // 내가 선택한 예약이라면
	        			var start = eventArray[i].start.format('YYYYMMDD');
	    	        	var end = moment(eventArray[i].end-(1000*60*60*24)).format('YYYYMMDD');
	    	        	var boardNum = boardNo; // 나중에 값 변경필요
	    	        	//alert("end : " + moment(eventArray[i].end-(1000*60*60*24)).format('YYYYMMDD000000'));
	    	        	var form = $('<form></form>');
	    	        	    form.attr('action', "/controller/payment/goodsPayment");
	    	        	    form.attr('method', 'post');
	    	        	    form.appendTo('body');
	    	        	    var st = $("<input type='hidden' value="+start+" name='startDate'>");
	    	        	    var e = $("<input type='hidden' value="+end+" name='endDate'>");
	    	        	    var mId = $("<input type='hidden' value="+boardNum+" name='boardNo'>");
	    	        	    form.append(st);
	    	        	    form.append(e);
	    	        	    form.append(mId);
	    	        	    form.submit();
	    	        	// location.href="<c:url value='/reservation/apply?productid="+productid+"&start="+start+"&end="+end+"'/>";
	    	    		return;
	        		}	
	        	}
	        	alert("예약 날을 선택해 주세요");
	        } else {
	            //no
	            alert(11);
	        } 
		})
		
	});
	</script>

</body>
</html>