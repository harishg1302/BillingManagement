<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Login</title>
	<style>
		div {
			padding: 0px 0px 12px 1px;
		}

		input {
			width: 30%;
			height: 7%;
			border-radius: 4px;
			border: 1px solid #7d7474;
		}

		.error {
			color: red;
			font-size: 11px;
		}

	</style>
	<script language="JavaScript" type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>

		function emailValidation() {
			var email = $('input[name=email]').val();
			var pattern = /^\b[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b$/i

			if (!pattern.test(email)) {
				$('#emailErrorSpan').show();
				return false;
			} else {
				$('#emailErrorSpan').hide();
				return true;
			}
		}

		function passwordValidation() {
			var password = $('input[name=password]').val();
			if (password.length == 0) {
				$('#passwordErrorSpan').show();
				return false;
			} else {
				$('#passwordErrorSpan').hide();
				return true;
			}
		}

		function validation() {
			var isValidEmail = emailValidation();
			var isValidPassword = passwordValidation();
			if (isValidEmail && isValidPassword) {
				return true;
			} else {
				return false;
			}
		}
	</script>
</head>
<body>
<center>
	<h1> Billing Management</h1>
	<h2> Login </h2>
	<form action="login" method="post" onsubmit="return validation()">
		<div>
			<div>
				<span id="loginErrorSpan" class="error">${errorMessage}</span>
				<br/>
				<input type="text" name="email" placeholder="Email">
				<br/>
				<span id="emailErrorSpan" class="error" style="display: none;">Invalid email</span>
			</div>
			<div>
				<input type="password" name="password" placeholder="Password">
				<br/>
				<span id="passwordErrorSpan" class="error" style="display: none;">Invalid Password</span>
			</div>
			<div>
				<input type="submit" value="Login">
				<br/><br/>
				<a href="register">Sign Up</a>
			</div>
		</div>
	</form>
</center>
</body>
</html>
