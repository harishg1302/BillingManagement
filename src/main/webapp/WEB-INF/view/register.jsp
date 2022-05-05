<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Register</title>
	<style>
		div {
			padding: 0px 0px 12px 1px;
		}

		input, button {
			width: 30%;
			height: 7%;
			border-radius: 4px;
			border: 1px solid #7d7474;
		}

		.error {
			color: red;
		}

	</style>
	<script language="JavaScript" type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="./js/register.js" type="text/javascript"></script>
</head>
<body>
<center>
	<h1> Billing Management</h1>
	<h2> Register </h2>
	<div>
		<div>
			<input type="text" name="name" placeholder="User Name">
			<br/>
			<span id="nameErrorSpan" class="error" style="display: none;">Invalid name</span>
		</div>
		<div>
			<input type="text" name="mobileNumber" placeholder="Mobile Number" maxlength="10" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
			<br/>
			<span id="mobileErrorSpan" class="error" style="display: none;">Invalid mobile number</span>
		</div>
		<div>
			<input type="text" name="email" placeholder="Email Id">
			<br/>
			<span id="emailErrorSpan" class="error" style="display: none;">Invalid email</span>
		</div>
		<div>
			<input type="text" name="address" placeholder="Address">
		</div>
		<div>
			<input type="password" name="password" placeholder="Password">
			<br/>
			<span id="passwordErrorSpan" class="error" style="display: none;">Invalid Password</span>
		</div>
		<div>
			<input type="password" name="confirmPassword" placeholder="Confirm Password">
			<br/>
			<span id="ConfirmPasswordErrorSpan" class="error" style="display: none;">Password does not match</span>
		</div>

		<div>
			<button id="registerBtn">Register</button>
		</div>
	</div>
</center>
</body>
</html>
