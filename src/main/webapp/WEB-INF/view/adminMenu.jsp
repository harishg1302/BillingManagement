<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Admin Menu</title>
	<link href="./css/styles.css" rel="stylesheet">
	<script language="JavaScript" type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="./js/adminMenu.js" type="text/javascript"></script>
</head>
<body>
<div class="float-container">
	<div class="topnav">
		<a class="active" id="generateBillBtn" href="#home">Generate Bill</a>
		<a id="allUsersBillsBtn" href="#bills">All Bills</a>
		<a id="logoutBtn" href="#logOut">Log out</a>
	</div>
	<div id="allUsersGenerateBillDiv" class="float-child" style="display: none;">
		<%@include file="allUsers.jsp" %>
	</div>
	<div id="allUsersBillsDiv" class="float-child" style="display: none;">
		<%@include file="allUsersBills.jsp" %>
	</div>
	<div id="generateBillDiv" class="float-child" style="display: none;">
		<%@include file="generateBill.jsp" %>
	</div>
</div>
</body>
</html>
