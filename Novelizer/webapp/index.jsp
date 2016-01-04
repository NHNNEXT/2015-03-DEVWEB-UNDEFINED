<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Novelizer</title>
	<link rel='stylesheet'
	href='http://fonts.googleapis.com/earlyaccess/nanumgothic.css'>
	<link
	href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Lato:400,100,300,700,900'
	rel='stylesheet' type='text/css'>

	<link rel="stylesheet" type="text/css" href="css/intro.css">
	<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
	<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
	<script type="text/javascript"></script>
</head>
<body>
	<div class="header">
		<div id="title">Novelizer</div>
	</div>
	<div id="signUp">Sign Up</div>
	<div id="login">Login</div>
	
	<!-- 회원 가입 화 -->
	<div id ="sign-up-box" class="animated fadeInUp">
		<div class="box-header">
			<h2>SIGN UP</h2>
		</div>
		<form class="loginForm" action="#" method="post">
			<label for="username">Username</label> <br /> 
			<input type="text" id="username"> <br /> 
			<label for="password">Password</label><br /> 
			<input type="password" id="password"> <br />
			<button type="submit">
				<a href="newproject.html">Sign Up</a>
			</button>
		</form>
	</div>

	<!-- 로그인 박스화면 -->
	<div class="login-box animated fadeInUp">
		<div class="box-header">
			<h2>LogIn</h2>
		</div>
		<form class="loginForm" action="newProject.html" method="post">
			<label for="username">Username</label> <br /> 
			<input type="text" id="username"> <br /> 
			<label for="password">Password</label><br /> 
			<input type="password" id="password"> <br />
			<button type="submit">
				<a href="newproject.html">Sign In</a>
			</button>
		</form>
		<br /> <a href="#"><p class="small">Forgot your password?</p></a>
	</div>

	

	<script type="text/javascript" src="js/intro.js"></script>

</body>
</html>