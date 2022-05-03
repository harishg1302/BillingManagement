$(document).ready(function () {
	$('#allUsersDiv').hide();
	$('#generateBillDiv').hide();
	// $("#datepicker").datepicker();

	$('#usersBtn').on('click', function () {
		$('#allUsersDiv').show();
		$('#generateBillDiv').hide();
		getAllUsers();
		$(document).on('click', '#addCustomerBill', function (event) {
			var $selectedUser = $(event.currentTarget);
			getDataForGenerateBill($selectedUser);
		});
		$(document).on('click', '#closeModal', function () {
			$('#myModal').hide();
		});
	});
	$('#generateBillBtn').on('click', function () {
		$('#allUsersDiv').hide();
		$('#generateBillDiv').show();
	});
	$('#saveBillBtn').on('click', function () {
		saveGeneratedBill();
	});

	function getAllUsers() {

		var usersTBody = $('#allUsersTBody');
		$.ajax({
			url: 'user/allUsers',
			type: "GET",
			success: $.proxy(function (data) {
				console.log('data: ' + data);
				var jsonObject = JSON.stringify(data);
				$.each(data, function (i, obj) {
					usersTBody.append("<tr><td>" + ++i + "<input type='hidden' id='userId' value=" + obj.id + "></td><td>" + obj.name + "</td><td>" + obj.email + "</td><td><button id='addCustomerBill'>Add Bill</button></td></tr>");
				});
			})
		});
	};

	function getDataForGenerateBill($selectedUser) {

		var selectedUserId = $selectedUser.closest('tr').find('input:hidden').val()
		$('#generateBillDiv').show();
		$('#myModal').show();
		var $modal = $('#myModal');
		console.log('selectedUserId : ' + selectedUserId);
		$.ajax({
			url: 'bill/generateBill/' + selectedUserId,
			type: "GET",
			success: $.proxy(function (data) {
				console.log('data: ' + data);
				$modal.find('#customerEmailId').text(data.emailId);
				$modal.find('#customerId').val(selectedUserId);
				$.each(data.connectionList, function (i, obj) {
					$modal.find('#connectionId').val(obj.id);
					$modal.find('#connectionType').append($('<option>', {
						value: obj.connectionType,
						text: obj.connectionType
					}));

					$modal.find('#connectionNumber').append($('<option>', {
						value: obj.connectionNumber,
						text: obj.connectionNumber
					}));

					$modal.find('#supplierName').val(obj.supplier.name);
				});
			})
		});
	};

	function saveGeneratedBill() {
		var $modal = $('#myModal');
		$.ajax({
			url: 'bill/saveBill?userId=' + $modal.find('#customerId').val() + "&billingDate=" + $modal.find('#datepicker').val()
				+ "&connectionId=" + $modal.find('#connectionId').val() + "&amount=" + $modal.find('#payableAmount').val(),
			type: "GET",
			contentType: 'application/json',
			success: $.proxy(function (data) {
				console.log('data: ' + data);

			})
		});
	};
});