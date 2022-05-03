<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>

<style>
	body {
		font-family: Arial, Helvetica, sans-serif;
	}

	/* The Modal (background) */
	.modal {
		display: none; /* Hidden by default */
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
		width: 80%;
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
</style>

<!-- The Modal -->
<div id="myModal" class="modal">

	<!-- Modal content -->
	<div class="modal-content">
		<span id="closeModal" class="close">&times;</span>
		<%--		<form id="saveBillForm">--%>
		<div>
			<label>Customer Email Id: <span id="customerEmailId"></span><input type="hidden" id="customerId"></label>
		</div>
		<form id="saveBillForm">
			<div>
				<input type="hidden" id="connectionId">
				<select id="connectionType"></select>
			</div>
			<div>
				<select id="connectionNumber"></select>
			</div>
			<div>
				<%--				<select id="suppliers">--%>
				<%--					<option value="1">Supplier1</option>--%>
				<%--					<option value="2">Supplier2</option>--%>
				<%--					<option value="3">Supplier3</option>--%>
				<%--				</select>--%>
				<input type="text" id="supplierName">
			</div>
			<div>
				<input type="text" id="datepicker">
			</div>
			<div>
				<input type="text" id="payableAmount">
			</div>
		</form>
		<div>
			<button id="saveBillBtn">Generate Bill</button>
		</div>
	</div>
	<%--		</form>--%>
</div>


</div>

<script>
	// Get the modal
	var modal = document.getElementById("myModal");

	// Get the button that opens the modal
	var btn = document.getElementById("addCustomerBill");

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];

	// When the user clicks the button, open the modal
	// btn.onclick = function () {
	// 	modal.style.display = "block";
	// }
	//
	// // When the user clicks on <span> (x), close the modal
	// span.onclick = function () {
	// 	modal.style.display = "none";
	// }
	//
	// // When the user clicks anywhere outside of the modal, close it
	// window.onclick = function (event) {
	// 	if (event.target == modal) {
	// 		modal.style.display = "none";
	// 	}
	// }
</script>