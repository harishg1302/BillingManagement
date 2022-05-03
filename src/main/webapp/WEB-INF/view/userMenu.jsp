<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
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

    .float-container{
        margin-top: -22px;
    }
    </style>
	<script language="JavaScript" type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="./js/menu.js" type="text/javascript"></script>
</head>
<body>
<div class="float-container">
	<div class="topnav">
      <a class="active" id="walletBtn">Wallet</a>
      <a id="profileBtn">Profile</a>
      <a id="billingBtn">Billing</a>
      <a id="logoutBtn">Logout</a>
    </div>
	<div id="walletDiv" class="float-child" style="display: none;">
		<%@include file="wallet.jsp" %>
	</div>
	<div id="profileDiv" class="float-child" style="display: none;">
		<%@include file="profile.jsp" %>
	</div>
	<div id="billingDiv" class="float-child" style="display: none;">
		<%@include file="billing.jsp" %>
	</div>
</div>
</body>
</html>
