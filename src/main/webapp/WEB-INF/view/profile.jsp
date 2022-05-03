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
</style>
<center>
	<div>
		<div>
			<select id="connectionType">
				<option value="DTH">DTH</option>
				<option value="Electricity">Electricity</option>
				<option value="Water">Water</option>
				<option value="Mobile Recharge">Mobile Recharge</option>
				<option value="WiFi">WiFi</option>
				<option value="Gas">Gas</option>
			</select>
		</div>
		<div>
			<select id="suppliers">
				<option value="supplier1">supplier1</option>
				<option value="supplier2">supplier2</option>
			</select>
		</div>
		<div>
			<button id="addConnection">Add Connection</button>
		</div>

	</div>
</center>