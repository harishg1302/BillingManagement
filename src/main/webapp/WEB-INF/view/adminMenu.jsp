<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
	<%--	<style>--%>
	<%--		div {--%>
	<%--			padding: 0px 0px 12px 1px;--%>
	<%--		}--%>

	<%--		a {--%>
	<%--			width: 30%;--%>
	<%--			height: 7%;--%>
	<%--			border-radius: 4px;--%>
	<%--			border: 1px solid #7d7474;--%>
	<%--		}--%>

	<%--		.float-container {--%>
	<%--			padding: 20px;--%>
	<%--		}--%>

	<%--		.float-child {--%>
	<%--			width: 36%;--%>
	<%--			float: left;--%>
	<%--			padding: 20px;--%>
	<%--		}--%>
	<%--	</style>--%>

	<style>
		body {
			margin: 0;
			font-family: Arial, Helvetica, sans-serif;
		}

		.topnav {
			overflow: hidden;
			background-color: #333;
		}

		.topnav a {
			float: left;
			color: #f2f2f2;
			text-align: center;
			padding: 14px 16px;
			text-decoration: none;
			font-size: 17px;
		}

		.topnav a:hover {
			background-color: #ddd;
			color: black;
		}

		.topnav a.active {
			background-color: #04AA6D;
			color: white;
		}
	</style>
	<script language="JavaScript" type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="./js/adminMenu.js" type="text/javascript"></script>
</head>
<body>

<div class="topnav">
	<a class="active" id="usersBtn" href="#home">Users</a>
	<%--	<button id="usersBtn">Users BTN</button>--%>
	<a href="#news">Generate Bill</a>
	<a href="#contact">Log out</a>
</div>
<%--<div class="float-container">--%>
<%--	<div class="float-child">--%>
<%--		<div>--%>
<%--			<button id="usersBtn">Users</button>--%>
<%--		</div>--%>
<%--		<div>--%>
<%--			<button id="generateBillBtn">Generate Bill</button>--%>
<%--		</div>--%>
<%--		<div>--%>
<%--			<button id="logout">Logout</button>--%>
<%--		</div>--%>
<%--	</div>--%>
<div id="allUsersDiv" class="float-child" style="display: none;">
	<%@include file="allUsers.jsp" %>
</div>
<div id="generateBillDiv" class="float-child" style="display: none;">
	<%@include file="generateBill.jsp" %>
</div>
<%--</div>--%>
</body>
</html>
