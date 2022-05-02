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
	</style>
</head>
<body>
<h1> Login </h1>
<form action="/login" method="post">
	<div>
		<div>
			<input type="text" name="id" placeholder="User Id">
		</div>
		<div>
			<input type="password" name="password" placeholder="Password">
		</div>
		<div>
			<input type="submit" value="Login">
			<a href="user/register">Sign Up</a>
		</div>
	</div>
</form>
</body>
</html>