<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
	<link href="./css/styles.css" rel="stylesheet">
	<script language="JavaScript" type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="./js/menu.js" type="text/javascript"></script>
</head>
<body>
<div class="float-container">
	<div class="topnav">
		<a class="active" id="walletBtn">Wallet</a>
		<a id="connectionsBtn">Connections</a>
		<a id="billingBtn">Billing</a>
		<a id="logoutBtn">Logout</a>
	</div>
	<div id="walletDiv" class="float-child" style="display: none;">
		<%@include file="wallet.jsp" %>
	</div>
	<div id="connectionsDiv" class="float-child" style="display: none;">
		<%@include file="connections.jsp" %>
	</div>
	<div id="billingDiv" class="float-child" style="display: none;">
		<%@include file="billing.jsp" %>
	</div>
</div>
</body>
</html>
