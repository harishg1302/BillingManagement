$(document).ready(function () {
	$('#allUsersDiv').hide();
	$('#generateBillDiv').hide();
	$("#datepicker").datepicker();
	clickOnUsersBtn();
	var connectionsList = [];

	$('#usersBtn').on('click', function () {
		$('#usersBtn').addClass('active');
		$('#saveBillBtn').removeClass('active');
		$('#logoutBtn').removeClass('active');
		clickOnUsersBtn();
	});
	$('#generateBillBtn').on('click', function () {
		$('#allUsersDiv').hide();
		$('#generateBillDiv').show();
	});
	$('#saveBillBtn').on('click', function () {
		$('#usersBtn').removeClass('active');
		$('#saveBillBtn').addClass('active');
		$('#logoutBtn').removeClass('active');
		saveGeneratedBill();
	});
	$('#logoutBtn').on('click', function () {
		$('#usersBtn').removeClass('active');
		$('#saveBillBtn').removeClass('active');
		$('#logoutBtn').addClass('active');
		logout();
	});

	$('#myModal').find('#connectionType').on('change', function () {
		var connectionType = $(this).val();
		getConnectionNumbersByType(connectionType);
	});

	$('#myModal').find('#connectionNumber').on('change', function () {
		var connectionNumber = $(this).val();
		setSupplierNameByConnectionNumber(connectionNumber);
	});

	function clickOnUsersBtn() {
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
		$.ajax({
			url: 'bill/generateBill/' + selectedUserId,
			type: "GET",
			success: $.proxy(function (data) {

				if (data.connectionList.length > 0) {
					var $modal = $('#myModal');
					$modal.find('#customerEmailId').text(data.emailId);
					$modal.find('#customerId').val(selectedUserId);
					$modal.show();
					$modal.find('#connectionType').empty();
					$modal.find('#connectionNumber').empty();
					connectionsList = data.connectionList;
					$.each(connectionsList, function (i, obj) {
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

	function saveGeneratedBill() {
		var $modal = $('#myModal');
		$.ajax({
			url: 'bill/saveBill?userId=' + $modal.find('#customerId').val() + "&billingDate=" + $modal.find('#datepicker').val()
				+ "&connectionId=" + $modal.find('#connectionId').val() + "&amount=" + $modal.find('#payableAmount').val(),
			type: "GET",
			contentType: 'application/json',
			success: $.proxy(function (data) {

			})
		});
	};

	function getConnectionNumbersByType(connectionType) {

		var $connectionNumbers = $('#myModal').find('#connectionNumber');
		$connectionNumbers.empty();
		$.each(connectionsList, function (i, obj) {

			if (connectionType == obj.connectionType) {
				$connectionNumbers.append($('<option>', {
					value: obj.connectionNumber,
					text: obj.connectionNumber
				}));
			}
		});

		$.each(connectionsList, function (i, obj) {

			if (connectionType == obj.connectionType) {
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