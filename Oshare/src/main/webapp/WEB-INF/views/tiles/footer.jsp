<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	<!-- icon CSS  -->
	
	/* Reset */
@import url(//codepen.io/chrisdothtml/pen/ojLzJK.css);
.social-buttons {
  height: 90px;
  margin: auto;
  font-size: 0;
  text-align: center;
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
}

.social-button {
  display: inline-block;
  background-color: #fff;
  width: 90px;
  height: 90px;
  line-height: 90px;
  margin: 0 10px;
  text-align: center;
  position: relative;
  overflow: hidden;
  opacity: .99;
  border-radius: 28%;
  box-shadow: 0 0 30px 0 rgba(0, 0, 0, 0.05);
  -webkit-transition: all 0.35s cubic-bezier(0.31, -0.105, 0.43, 1.59);
  transition: all 0.35s cubic-bezier(0.31, -0.105, 0.43, 1.59);
}
.social-button:before {
  content: '';
  background-color: #000;
  width: 120%;
  height: 120%;
  position: absolute;
  top: 90%;
  left: -110%;
  -webkit-transform: rotate(45deg);
          transform: rotate(45deg);
  -webkit-transition: all 0.35s cubic-bezier(0.31, -0.105, 0.43, 1.59);
  transition: all 0.35s cubic-bezier(0.31, -0.105, 0.43, 1.59);
}
.social-button .fa {
  font-size: 38px;
  vertical-align: middle;
  -webkit-transform: scale(0.8);
          transform: scale(0.8);
  -webkit-transition: all 0.35s cubic-bezier(0.31, -0.105, 0.43, 1.59);
  transition: all 0.35s cubic-bezier(0.31, -0.105, 0.43, 1.59);
}
.social-button.facebook:before {
  background-color: #3B5998;
}
.social-button.facebook .fa {
  color: #3B5998;
}
.social-button.twitter:before {
  background-color: #3CF;
}
.social-button.twitter .fa {
  color: #3CF;
}
.social-button.google:before {
  background-color: #DC4A38;
}
.social-button.google .fa {
  color: #DC4A38;
}
.social-button.dribbble:before {
  background-color: #F26798;
}
.social-button.dribbble .fa {
  color: #F26798;
}
.social-button.skype:before {
  background-color: #00AFF0;
}
.social-button.skype .fa {
  color: #00AFF0;
}
.social-button:focus:before, .social-button:hover:before {
  top: -10%;
  left: -10%;
}
.social-button:focus .fa, .social-button:hover .fa {
  color: #fff;
  -webkit-transform: scale(1);
          transform: scale(1);
}
	
</style>
</head>
<body>
	<div class="footerIconForm">
		<!-- <img src="" alt="footerIcon"> -->
		<h1>Oshare</h1>
		<div class="social-buttons"><a class="social-button facebook" href="#"><i class="fa fa-facebook"></i></a><a class="social-button twitter" href="#"><i class="fa fa-twitter"></i></a><a class="social-button google" href="#"><i class="fa fa-google"></i></a><a class="social-button dribbble" href="#"><i class="fa fa-github"></i></a><a class="social-button skype" href="#"><i class="fa fa-skype"></i></a>
		</div>
		<p>Intense JB © 2016 . Privacy Policy</p>
	</div>
	<div class="footerScrollTopUp">
	<i class="material-icons">&#xE5CE;</i>
	</div>	
</body>
</html>
