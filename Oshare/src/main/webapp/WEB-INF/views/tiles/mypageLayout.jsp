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
<link href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" rel="stylesheet">
<link href="https://cdn.datatables.net/1.10.13/css/dataTables.jqueryui.min.css" rel="stylesheet">
<link href="<c:url value='/resources/css/font-awesome.min.css'/>" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/style.css'/>">

<title>Oshare</title>
</head>
<body>
	<div id="main">
		<header>
			<tiles:insertAttribute name="header" />
		</header>
		<div class="container">
	
				<div id="mypageLeftMenuTiles">
					<tiles:insertAttribute name="mypageMenu" />
				</div>
				<div id="mypageContentTiles">
					<tiles:insertAttribute name="mypageContent" />
				</div>
	
		</div>
	</div>
		<footer>
			<tiles:insertAttribute name="footer" />
		</footer>
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
</body>
</html>