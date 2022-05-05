<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<!-- The Modal -->
<div id="myModal" class="modal">

	<!-- Modal content -->
	<div class="modal-content">
		<span id="closeModal" class="close">&times;</span>
		<div>
			<label>Customer Email Id: <span id="customerEmailId"></span><input type="hidden" id="customerId"></label>
			<br/>
			<span id="generateBillErrorSpan" class="error" style="display: none;">All Fields are mandatory</span>
		</div>
		<form id="saveBillForm">
			<div>
				<input type="hidden" id="connectionId">
				<h6 class="modalh6">Connection Type</h6>
				<select id="connectionType"></select>
			</div>
			<div>
				<h6 class="modalh6">Connection Number</h6>
				<select id="connectionNumber"></select>
			</div>
			<div>
				<h6 class="modalh6">Supplier Name</h6>
				<input type="text" id="supplierName" disabled="true">
			</div>
			<div>
				<h6 class="modalh6">Billing Date</h6>
				<input type="text" id="datepicker">
			</div>
			<div>
				<h6 class="modalh6">Due Days</h6>
				<input type="text" id="dueDays">
			</div>
			<div>
				<h6 class="modalh6">Amount</h6>
				<input type="text" id="payableAmount">
			</div>
		</form>
		<div>
			<button id="saveBillBtn">Generate Bill</button>
		</div>
	</div>
</div>

<div id="errorModal" class="modal">
	<!-- Modal content -->
	<div class="modal-content">
		<span id="closeErrorModal" class="close">&times;</span>
		<div>
			<span id="errorMessage"></span>
		</div>
	</div>
</div>