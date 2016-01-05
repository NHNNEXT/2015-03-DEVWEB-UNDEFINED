<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel='stylesheet'
	href='http://fonts.googleapis.com/earlyaccess/nanumgothic.css'>
<link
	href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Lato:400,100,300,700,900'
	rel='stylesheet' type='text/css'>

<link rel="stylesheet" type="text/css" href="./css/intro.css">
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script type="text/javascript"></script>
</head>
<body>

	<div class="header">
		<div id="title"></div>
	</div>

	<%
		Object errorMessage = request.getAttribute("errorMessage");
	%>

	<c:if test="${not empty errorMessage}">
		<div class="control-group">
			<label class="error"> <%
 	out.println(errorMessage);
 %></label>
		</div>
	</c:if>


	<%
		Object userId = session.getAttribute("userId");
	%>
	<c:choose>
		<c:when test="${not empty userId}">
			<div id="logout">
				<a href="/users/logout">logout</a>
			</div>
			<div id="make">
				<a href="/newProject.jsp">make 
			</div>
		</c:when>
		<c:otherwise>
			<div id="signUp">Sign Up</div>
			<div id="login">Login</div>
		</c:otherwise>
	</c:choose>

	<div class="SignUp-box animated fadeInUp">
		<div class="box-header">
			<h2>Sign Up</h2>
		</div>
		<!-- <form class="form" action="/form_action.jsp" method="post"> -->
		<form class="form" action="/users/save" method="post">
			<label for="userId">UserId</label> <br /> <input type="text"
				id="userId" name="userId"> <br /> <label for="password">Password</label><br />
			<input type="password" id="password" name="password"> <br />
			<button type="submit">Sign up</button>
			<br />
		</form>
	</div>

	<div class="login-box animated fadeInUp">
		<div class="box-header">
			<h2>LogIn</h2>
		</div>
		<form class="form" action="/users/login" method="post">
			<label for="userId">UserId</label> <br /> <input type="text"
				name="userId"> <br /> <label for="password">Password</label><br />
			<input type="password" name="password"> <br />
			<button type="submit">Sign in</button>
			<br />
		</form>
		<a href="#"><p class="small">Forgot your password?</p></a>
	</div>

	<script type="text/javascript" src="./js/intro.js"></script>
	<script>
		$(document).ready(function() {

			$("#signUp").on("click", function() {
				$('.SignUp-box').fadeIn(1000).css('display', 'block');
			});

			$("#login").on("click", function() {
				$('.login-box').fadeIn(1000).css('display', 'block');
			});

			/* $("#logout").on("click", function() {
				$('.login-box').fadeIn(1000).css('display', 'block');
			}); */

			$('#logo').addClass('animated fadeInDown');
			$("input:text:visible:first").focus();
		});
		$('#username').focus(function() {
			$('label[for="username"]').addClass('selected');
		});
		$('#username').blur(function() {
			$('label[for="username"]').removeClass('selected');
		});
		$('#password').focus(function() {
			$('label[for="password"]').addClass('selected');
		});
		$('#password').blur(function() {
			$('label[for="password"]').removeClass('selected');
		});
	</script>
</body>
</html>
