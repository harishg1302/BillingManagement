<style>
	div {
		padding: 0px 0px 12px 1px;
	}

	input, button, select {
		width: 30%;
		height: 7%;
		border-radius: 4px;
		border: 1px solid #7d7474;
	}

	h6 {
		margin-bottom: 6px;
		margin-top: 1px;
		color: #62626a;
	}

	.connection {
		text-align: left;
		margin-left: -23%;
	}
</style>
<center>
	<div>
		<div>
			<h6><span class="connection">Connection Type</span></h6>
			<select id="connectionType">
				<option value="None">Select</option>
				<option value="DTH">DTH</option>
				<option value="Electricity">Electricity</option>
				<option value="Water">Water</option>
				<option value="Mobile Recharge">Mobile Recharge</option>
				<option value="WiFi">WiFi</option>
				<option value="Gas">Gas</option>
			</select>
		</div>
		<div>
			<h6><span class="connection">Suppliers</span></h6>
			<select id="suppliers"></select>
		</div>
		<div>
			<h6><span class="connection">Connection Number</span></h6>
			<input type="text" id="connectionNumber">
		</div>
		<div>
			<button id="addConnection">Add Connection</button>
		</div>

	</div>
</center>