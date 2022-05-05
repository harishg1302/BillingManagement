<div id="mainConnectionDiv">
	<div>
		<h1><label id="currentAmount">Current Balance: $<span id="currentBalance">0</span></label></h1>
	</div>
	<div>
		<input type="text" id="toBeAddedAmount" maxlength="4" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
		<br/>
		<span id="walletErrorSpan" class="error" style="display: none;">Invalid amount</span>
	</div>
	<div>
		<button id="addWalletAmountBtn">Add Amount</button>
	</div>
</div>