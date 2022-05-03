<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>

<style>
	body {
		font-family: Arial, Helvetica, sans-serif;
	}

	div {
		padding: 0px 0px 12px 1px;
	}

	input, button, select {
		width: 40%;
		height: 7%;
		border-radius: 4px;
		border: 1px solid #7d7474;
	}

	.modal {
		display: none;
		position: fixed; /* Stay in place */
		z-index: 1; /* Sit on top */
		padding-top: 100px; /* Location of the box */
		left: 0;
		top: 0;
		width: 100%; /* Full width */
		height: 100%; /* Full height */
		overflow: auto; /* Enable scroll if needed */
		background-color: rgb(0, 0, 0); /* Fallback color */
		background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
	}

	/* Modal Content */
	.modal-content {
		background-color: #fefefe;
		margin: auto;
		padding: 20px;
		border: 1px solid #888;
		width: 50%;
	}

	/* The Close Button */
	.close {
		color: #aaaaaa;
		float: right;
		font-size: 28px;
		font-weight: bold;
	}

	.close:hover,
	.close:focus {
		color: #000;
		text-decoration: none;
		cursor: pointer;
	}

	h6 {
		margin-bottom: 6px;
		margin-top: 1px;
		color: #62626a;
	}
</style>

<!-- The Modal -->
<div id="myModal" class="modal">

	<!-- Modal content -->
	<div class="modal-content">
		<span id="closeModal" class="close">&times;</span>
		<div>
			<label>Customer Email Id: <span id="customerEmailId"></span><input type="hidden" id="customerId"></label>
		</div>
		<form id="saveBillForm">
			<div>
				<input type="hidden" id="connectionId">
				<h6>Connection Type</h6>
				<select id="connectionType"></select>
			</div>
			<div>
				<h6>Connection Number</h6>
				<select id="connectionNumber"></select>
			</div>
			<div>
				<h6>Supplier Name</h6>
				<input type="text" id="supplierName">
			</div>
			<div>
				<h6>Billing Date</h6>
				<input type="text" id="datepicker">
			</div>
			<div>
				<h6>Amount</h6>
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