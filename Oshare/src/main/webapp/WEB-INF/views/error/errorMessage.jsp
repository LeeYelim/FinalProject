<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<div class="container">
	<div class="col-lg-8 col-lg-offset-2 text-center">
		<div class="logo">
			<h1>Error !</h1>
		</div>
		<p class="lead text-muted">${errorMsg}</p>
		<div class="clearfix"></div>
		<div class="col-lg-6 col-lg-offset-3">
			<form action="index.html">

				<div class="input-group">
					<input type="text" placeholder="search ..." class="form-control" />
					<span class="input-group-btn">
						<button class="btn btn-primary" type="button">
							<i class="icon-align-left"></i>
						</button>
					</span>
				</div>

			</form>
		</div>
		<div class="clearfix"></div>
		<br />
		<div class="col-lg-6  col-lg-offset-3">
			<div class="btn-group btn-group-justified">
				<a href="<c:url value="/member/loginForm"/>" class="btn btn-primary">Login</a>
				<a href="<c:url value="/"/>" class="btn btn-success">Return
					Website</a>
			</div>

		</div>
	</div>


</div>
</body>
</html>