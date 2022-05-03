<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
	<link href="./css/styles.css" rel="stylesheet">
	<script language="JavaScript" type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="./js/adminMenu.js" type="text/javascript"></script>
</head>
<body>
<div class="topnav">
	<a class="active" id="usersBtn" href="#home">Users</a>
	<a id="generateBillBtn" href="#bills">Generate Bill</a>
	<a id="logoutBtn" href="#logOut">Log out</a>
</div>
<div id="allUsersDiv" class="float-child" style="display: none;">
	<%@include file="allUsers.jsp" %>
</div>
<div id="generateBillDiv" class="float-child" style="display: none;">
	<%@include file="generateBill.jsp" %>
</div>
</body>
</html>
