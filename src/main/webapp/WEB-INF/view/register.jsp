<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Register</title>
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
<h1> Register </h1>
<form action="register" method="post">
	<div>
		<%--		<div>--%>
		<%--			<input type="text" name="id" placeholder="Id">--%>
		<%--		</div>--%>
		<div>
			<input type="text" name="name" placeholder="User Name">
		</div>
		<div>
			<input type="text" name="mobileNumber" placeholder="Mobile Number">
		</div>
		<div>
			<input type="text" name="email" placeholder="Email Id">
		</div>
		<div>
			<input type="text" name="address" placeholder="Address">
		</div>
		<div>
			<input type="password" name="password" placeholder="Password">
		</div>

		<div>
			<input type="submit" value="Register">
		</div>
	</div>
</form>
</body>
</html>
