<div id="navBar">
	<div id="subDiv1">
		<button id="dthBtn" class="allBillingBtn">DTH</button>
	</div>
	<div id="subDiv2">
		<button id="wifiBtn" class="allBillingBtn">WiFi</button>
	</div>
	<div id="subDiv3">
		<button id="waterBtn" class="allBillingBtn">Water</button>
	</div>
	<div id="subDiv4">
		<button id="electricityBtn" class="allBillingBtn">Electricity</button>
	</div>
	<div id="subDiv5">
		<button id="mobileRechargeBtn" class="allBillingBtn">Mobile</button>
	</div>
	<div id="subDiv6">
		<button id="gasBtn" class="allBillingBtn">Gas</button>
	</div>
</div>
<div id="allBillsDiv" class="child">
	<%@include file="allBillsByUser.jsp" %>
</div>

<div id="payBillModal" class="modal">
	<!-- Modal content -->
	<div class="modal-content">
		<span id="closePayBillModal" class="close">&times;</span>
		<div>
			<span id="payBillMessage"></span>
		</div>
	</div>
</div>