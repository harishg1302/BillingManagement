$(document).ready(function () {
	$('#allUsersGenerateBillDiv').hide();
	$('#generateBillDiv').hide();
	$("#datepicker").datepicker({minDate: 0, maxDate: "+1M"});
	clickOnAllUsersGenerateBillBtn();
	var connectionsList = [];

	$('#generateBillBtn').on('click', function () {
		$('#generateBillBtn').addClass('active');
		$('#allUsersBillsBtn').removeClass('active');
		$('#logoutBtn').removeClass('active');
		clickOnAllUsersGenerateBillBtn();
	});
	$('#allUsersBillsBtn').on('click', function () {
		$('#allUsersBillsBtn').addClass('active');
		$('#generateBillBtn').removeClass('active');
		$('#logoutBtn').removeClass('active');
		$('#allUsersGenerateBillDiv').hide();
	});
	$('#logoutBtn').on('click', function () {
		$('#generateBillBtn').removeClass('active');
		$('#saveBillBtn').removeClass('active');
		$('#logoutBtn').addClass('active');
		logout();
	});
	$('#saveBillBtn').on('click', function () {
		saveGeneratedBill();
	});

	$('#myModal').find('#connectionType').on('change', function () {
		var connectionType = $(this).val();
		getConnectionNumbersByType(connectionType);
	});

	$('#myModal').find('#connectionNumber').on('change', function () {
		var connectionNumber = $(this).val();
		setSupplierNameByConnectionNumber(connectionNumber);
	});

	function clickOnAllUsersGenerateBillBtn() {
		$('#allUsersGenerateBillDiv').show();
		$('#generateBillDiv').hide();
		getAllUsers();
		$(document).on('click', '#addCustomerBill', function (event) {
			event.stopImmediatePropagation();
			event.preventDefault();
			var $selectedUser = $(event.currentTarget);
			getDataForGenerateBill($selectedUser, event);
		});
		$(document).on('click', '#closeModal', function () {
			resetGenerateBillModal();
			$('#myModal').hide();
		});
		$(document).on('click', '#closeErrorModal', function () {
			$('#errorModal').hide();
		});
	}

	function getAllUsers() {

		var usersTBody = $('#allUsersTBody');
		usersTBody.empty();
		$.ajax({
			url: 'user/allUsers',
			type: "GET",
			success: $.proxy(function (data) {
				$.each(data, function (i, obj) {
					usersTBody.append("<tr>" +
						"<td>" + ++i + "<input type='hidden' id='userId' value=" + obj.id + "></td>" +
						"<td>" + obj.name + "</td>" +
						"<td>" + obj.email + "</td>" +
						"<td><button id='addCustomerBill'>Add Bill</button></td>" +
						"</tr>");
				});
			})
		});
	};

	function getDataForGenerateBill($selectedUser, e) {

		e.stopImmediatePropagation();
		e.preventDefault();

		var selectedUserId = $selectedUser.closest('tr').find('input:hidden').val()
		var $modal = $('#myModal');
		resetGenerateBillModal();
		$('#generateBillDiv').show();
		$.ajax({
			url: 'bill/generateBill/' + selectedUserId,
			type: "GET",
			success: $.proxy(function (data) {

				if (data.connectionList.length > 0) {
					$modal.find('#customerEmailId').text(data.emailId);
					$modal.find('#customerId').val(selectedUserId);
					$modal.show();
					connectionsList = data.connectionList;
					const uniqueConnectionTypes = [...new Map(connectionsList.map(item => [item['connectionType'], item])).values()];
					$.each(uniqueConnectionTypes, function (i, obj) {
						$modal.find('#connectionId').val(obj.id);
						$modal.find('#connectionType').append($('<option>', {
							value: obj.connectionType.toUpperCase(),
							text: obj.connectionType
						}));
					});
					getConnectionNumbersByType(connectionsList[0].connectionType);
				} else {
					connectionsList = [];
					var $errorModal = $('#errorModal');
					$errorModal.find('#errorMessage').text('There is no connection available for this user.');
					$errorModal.show();
				}
			})
		});
	};

	function resetGenerateBillModal() {

		var $modal = $('#myModal');
		$modal.find('#connectionType').empty();
		$modal.find('#connectionNumber').empty();
		$modal.find('#supplierName').val('');
		$modal.find('#dueDays').val('');
		$modal.find('#datepicker').val('');
		$modal.find('#payableAmount').val('');
	}

	function saveGeneratedBill() {
		var $modal = $('#myModal');
		var customerId = $modal.find('#customerId').val();
		var billingDate = $modal.find('#datepicker').val();
		var dueDays = $modal.find('#dueDays').val();
		var connectionId = $modal.find('#connectionId').val();
		var payableAmount = $modal.find('#payableAmount').val();
		$.ajax({
			url: 'bill/saveBill?userId=' + customerId + "&billingDate=" + billingDate + "&connectionId=" + connectionId + "&amount=" + payableAmount + "&dueDays=" + dueDays,
			type: "GET",
			contentType: 'application/json',
			success: $.proxy(function (data) {
				$('#myModal').hide();
			})
		});
	};

	function getConnectionNumbersByType(connectionType) {

		var $connectionNumbers = $('#myModal').find('#connectionNumber');
		$connectionNumbers.empty();
		$.each(connectionsList, function (i, obj) {

			if (connectionType.toUpperCase() == obj.connectionType.toUpperCase()) {
				$connectionNumbers.append($('<option>', {
					value: obj.connectionNumber,
					text: obj.connectionNumber
				}));
			}
		});

		$.each(connectionsList, function (i, obj) {

			if (connectionType.toUpperCase() == obj.connectionType.toUpperCase()) {
				setSupplierNameByConnectionNumber(obj.connectionNumber);
				return false;
			}
		});

	};

	function setSupplierNameByConnectionNumber(connectionNumber) {

		var $supplierName = $('#myModal').find('#supplierName');
		$supplierName.val('');
		$.each(connectionsList, function (i, obj) {
			if (connectionNumber == obj.connectionNumber) {
				$('#myModal').find('#connectionId').val(obj.id);
				$supplierName.val(obj.supplier.name);
			}
		});
	};

	function logout() {

		$.ajax({
			url: 'logout',
			type: "GET",
			success: $.proxy(function () {
				window.location.href = "login"
			})
		});
	};
});