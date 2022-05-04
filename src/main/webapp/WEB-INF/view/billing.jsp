<div class="parent">
	<div id="addConnectionDiv" class="child">
		<div>
			<button id="dthBtn" class="allBillingBtn">DTH</button>
		</div>
		<div>
			<button id="wifiBtn" class="allBillingBtn">WiFi</button>
		</div>
		<div>
			<button id="waterBtn" class="allBillingBtn">Water</button>
		</div>
		<div>
			<button id="electricityBtn" class="allBillingBtn">Electricity</button>
		</div>
		<div>
			<button id="mobileRechargeBtn" class="allBillingBtn">Mobile</button>
		</div>
		<div>
			<button id="gasBtn" class="allBillingBtn">Gas</button>
		</div>
	</div>
	<div id="allConnectionsDiv" class="child">
		<%@include file="allBillsByUser.jsp" %>
	</div>
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