$(document).ready(function () {
	$('#walletDiv').hide();
	$('#connectionsDiv').hide();
	$('#billingDiv').hide();
	clickOnWalletBtn();

	$('#walletBtn').on('click', function () {
		$('#billingBtn').removeClass('active');
		$('#connectionsBtn').removeClass('active');
		$('#logoutBtn').removeClass('active');
		$('#walletBtn').addClass('active');
		clickOnWalletBtn();
	});
	$('#connectionsBtn').on('click', function () {
		$('#connectionsDiv').show();
		$('#walletDiv').hide();
		$('#billingDiv').hide();
		$('#connectionsBtn').addClass('active');
		$('#billingBtn').removeClass('active');
		$('#logoutBtn').removeClass('active');
		$('#walletBtn').removeClass('active');
		resetConnectionDiv();
		getAllConnectionsByUser();
	});

	$('#billingBtn').on('click', function () {
		$('#connectionsDiv').hide();
		$('#walletDiv').hide();
		$('#billingDiv').show();
		$('#billingBtn').addClass('active');
		$('#connectionsBtn').removeClass('active');
		$('#logoutBtn').removeClass('active');
		$('#walletBtn').removeClass('active');
	});

	$('#logoutBtn').on('click', function () {
		$('#logoutBtn').addClass('active');
		$('#connectionsBtn').removeClass('active');
		$('#billingBtn').removeClass('active');
		$('#walletBtn').removeClass('active');
		logout();
	});

	$('#addWalletAmountBtn').on('click', function () {
		updateWalletBalance();
	});

	$('#connectionType').on('change', function () {
		var connectionType = $(this).val();
		getSuppliersListByType(connectionType);
	});

	$('#addConnection').on('click', function () {
		saveConnection();
	});

	$('#connectionNumber').on('keyup', function () {
		$('#mobileNumberErrorSpan').hide();
	});

	$('#toBeAddedAmount').on('keyup', function () {
		$('#walletErrorSpan').hide();
	});

	$('#connectionType').on('change', function () {
		connectionTypeValidation($(this).val());
	});

	$('.allBillingBtn').on('click', function (e) {
		e.stopImmediatePropagation();
		e.preventDefault();
		var $currentBtn = $(this);
		resetResetBillingBtn($currentBtn);
		getAllBillsByConnectionTypeAndUser($currentBtn.text().toUpperCase());
		$(document).find('.payBill').off("click");

		$(document).on('click', '.payBill', function (event) {
			var $billRow = $(event.currentTarget);
			payBill($billRow, event);
		});
	});

	$(document).on('click', '#closePayBillModal', function () {
		$('#payBillModal').hide();
	});

	function clickOnWalletBtn() {
		$('#walletDiv').show();
		$('#connectionsDiv').hide();
		$('#billingDiv').hide();
		$('#walletErrorSpan').hide();
		$('#toBeAddedAmount').val('');
		getWalletBalance();
	}

	function getWalletBalance() {

		$.ajax({
			url: 'wallet/getByUserId',
			type: "GET",
			success: $.proxy(function (data) {
				if (data != "" && data != undefined) {
					console.log('data: ' + data.balance);
					$('#currentBalance').text(data.balance)
				}
			})
		});
	};

	function logout() {

		$.ajax({
			url: 'logout',
			type: "GET",
			success: $.proxy(function (data) {
				window.location.href = "login"
			})
		});
	};

	function isValidAmount(toBeAddedBalance) {
		if (toBeAddedBalance.length == 0) {
			$('#walletErrorSpan').show();
			return false;
		} else {
			$('#walletErrorSpan').hide();
			return true;
		}
	}

	function updateWalletBalance() {

		var currentBalance = $('#currentBalance').text();
		var toBeAddedBalance = $('#toBeAddedAmount').val();

		var isValidWalletForm = isValidAmount(toBeAddedBalance)
		if (isValidWalletForm) {
			var totalBalance = 0;
			if ($.isNumeric(currentBalance)) {
				totalBalance = parseInt(currentBalance) + parseInt(toBeAddedBalance);
			} else {
				totalBalance = parseInt(toBeAddedBalance);
			}
			$.ajax({
				url: 'wallet/updateBalance/' + totalBalance,
				type: "PUT",
				success: $.proxy(function (data) {
					$('#currentBalance').text(data.balance);
					$('#toBeAddedAmount').val('');
				})
			});
		}
	};

	function connectionTypeValidation(connectionType) {

		if (connectionType == 0) {
			$('#connectionTypeErrorSpan').text('Invalid connection type');
			$('#connectionTypeErrorSpan').show();
			return false;
		} else {
			$('#connectionTypeErrorSpan').hide();
			return true;
		}
	}

	function mobileValidation(mobile, connectionType) {

		if (connectionType == 'MOBILE' && mobile.length < 10) {
			$('#mobileNumberErrorSpan').text('Invalid mobile number');
			$('#mobileNumberErrorSpan').show();
			return false;
		} else {
			$('#mobileNumberErrorSpan').hide();
			return true;
		}
	}

	function saveConnection() {

		var connectionType = $('#connectionType').val().toUpperCase();
		var connectionNumber = $('#connectionNumber').val();
		var supplierId = $('#suppliers').val();

		var isConnectionTypeValid = connectionTypeValidation(connectionType);
		var isMobileNumberValid = mobileValidation(connectionNumber, connectionType);
		if (isConnectionTypeValid && isMobileNumberValid) {
			$.ajax({
				url: 'bill/saveConnection?connectionType=' + connectionType + "&connectionNumber=" + connectionNumber + "&supplierId=" + supplierId,
				type: "GET",
				success: function (data, textStatus, xhr) {
					console.log('status : ' + xhr.status);
					if (xhr.status == 200) {
						resetConnectionDiv();
						getAllConnectionsByUser();
					} else if (xhr.status == 226) {
						$('#mobileNumberErrorSpan').text('Mobile number is already in used.');
						$('#mobileNumberErrorSpan').show();
					} else {
						console.log('Some error occurred');
					}
				},
				error: function (data, textStatus, xhr) {
					console.log('data error : ' + data.responseText);
				}
			});
		}
	};

	function getSuppliersListByType(connectionType) {

		if (connectionType.toUpperCase() == 'MOBILE') {
			$('#connectionNumberDiv').show();
		} else {
			$('#connectionNumberDiv').hide();
		}
		var $suppliers = $('#suppliers');
		$suppliers.empty();
		$.ajax({
			url: 'bill/getSuppliersByConnectionType/' + connectionType,
			type: "GET",
			success: $.proxy(function (data) {
				console.log('data: ' + data.balance);
				$.each(data, function (i, obj) {
					$suppliers.append($('<option>', {
						value: obj.id,
						text: obj.name
					}));
				});
			})
		});
	};

	function resetConnectionDiv() {
		$('#connectionType').val('0');
		$('#suppliers').val('');
		$('#connectionNumber').val('');
		$('#connectionTypeErrorSpan').hide();
		$('#mobileNumberErrorSpan').hide();
		$('#connectionNumberDiv').hide();
	}

	function getAllConnectionsByUser() {

		var $connectionsTBody = $('#allConnectionsTBody');
		$connectionsTBody.empty();
		$.ajax({
			url: 'bill/getConnectionsByUserId',
			type: "GET",
			success: $.proxy(function (data) {
				$.each(data, function (i, obj) {
					$connectionsTBody.append("" +
						"<tr>" +
						"<td>" + ++i + "<input type='hidden' id='userId' value=" + obj.id + "></td>" +
						"<td>" + obj.connectionType + "</td>" +
						"<td>" + obj.supplier.name + "</td>" +
						"<td>" + obj.connectionNumber + "</td>" +
						"</tr>");
				});
			})
		});
	};

	function resetResetBillingBtn($currentBtn) {
		$('.allBillingBtn').removeClass('active');
		$currentBtn.addClass('active');
		$('#connectionType').val('0');
		$('#suppliers').val('');
		$('#connectionNumber').val('');
	}

	function getAllBillsByConnectionTypeAndUser(connectionType) {

		var $allBillsTBody = $('#allBillsTBody');
		$allBillsTBody.empty();
		$.ajax({
			url: 'bill/getBillsByUserIdAndConnectionType/' + connectionType,
			type: "GET",
			success: $.proxy(function (data) {
				$.each(data, function (i, obj) {
					if (obj.billStatus == 'PAID') {
						$allBillsTBody.append("" +
							"<tr>" +
							"<td>" + ++i + "<input type='hidden' id='billId' value=" + obj.id + "></td>" +
							"<td>" + obj.billingDate + "</td>" +
							"<td>" + obj.dueDate + "</td>" +
							"<td>" + obj.connection.supplier.name + "</td>" +
							"<td>" + obj.connection.connectionNumber + "</td>" +
							"<td>" + obj.amount + "</td>" +
							"<td id='lateFees'>" + obj.lateFee + "</td>" +
							"<td>" + obj.totalAmount + "</td>" +
							"<td id='billStatus'>" + obj.billStatus + "</td>" +
							"<td><button class='payBill' disabled>Pay</button></td>" +
							"</tr>");
					} else {
						$allBillsTBody.append("" +
							"<tr>" +
							"<td>" + ++i + "<input type='hidden' id='billId' value=" + obj.id + "></td>" +
							"<td>" + obj.billingDate + "</td>" +
							"<td>" + obj.dueDate + "</td>" +
							"<td>" + obj.connection.supplier.name + "</td>" +
							"<td>" + obj.connection.connectionNumber + "</td>" +
							"<td>" + obj.amount + "</td>" +
							"<td id='lateFees'>" + obj.lateFee + "</td>" +
							"<td>" + obj.totalAmount + "</td>" +
							"<td id='billStatus'>" + obj.billStatus + "</td>" +
							"<td><button class='payBill'>Pay</button></td>" +
							"</tr>");
					}
				});
			})
		});
	};

	function payBill($billRow, e) {

		e.stopImmediatePropagation();
		e.preventDefault();

		var billId = $billRow.closest('tr').find('input:hidden').val()
		var lateFees = $billRow.closest('tr').find('#lateFees').text()
		$.ajax({
			url: 'bill/payBill/' + billId + '/' + lateFees,
			type: "PUT",
			success: $.proxy(function (data) {
				var $payBillModal = $('#payBillModal');
				$payBillModal.show();
				if (data == 'AMOUNT_INSUFFICIENT') {
					$payBillModal.find('#payBillMessage').text('You do not have sufficient balance in your wallet.');
				} else {
					$billRow.prop('disabled', true);
					$billRow.parent().parent().find('td#billStatus').text('PAID');
					$payBillModal.find('#payBillMessage').text('Bill paid successfully.');
				}
			})
		});
	};
});