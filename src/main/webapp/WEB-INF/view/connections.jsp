<div class="parent">
	<div id="addConnectionDiv" class="child">
		<div>
			<h6><span class="connection">Connection Type</span></h6>
			<select id="connectionType">
				<option value="0">Select</option>
				<option value="DTH">DTH</option>
				<option value="ELECTRICITY">Electricity</option>
				<option value="WATER">Water</option>
				<option value="MOBILE">Mobile</option>
				<option value="WIFI">WiFi</option>
				<option value="GAS">Gas</option>
			</select>
		</div>
		<div>
			<h6><span class="connection">Suppliers</span></h6>
			<select id="suppliers"></select>
		</div>
		<div id="connectionNumberDiv" style="display: none;">
			<h6><span class="connection">Mobile Number</span></h6>
			<input type="text" id="connectionNumber" maxlength="10" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
			<br/>
			<span id="mobileNumberErrorSpan" class="error" style="display: none;"></span>
		</div>
		<div>
			<button id="addConnection">Add Connection</button>
		</div>
	</div>
	<div id="allConnectionsDiv" class="child">
		<%@include file="allConnections.jsp" %>
	</div>
</div>