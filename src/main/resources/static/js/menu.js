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

	$('.allBillingBtn').on('click', function () {
		var $currentBtn = $(this);
		resetResetBillingBtn($currentBtn);
		getAllBillsByConnectionTypeAndUser($currentBtn.text().toUpperCase());
	});

	function clickOnWalletBtn() {
		$('#walletDiv').show();
		$('#connectionsDiv').hide();
		$('#billingDiv').hide();
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

	function updateWalletBalance() {

		var currentBalance = $('#currentBalance').text();
		var toBeAddedBalance = parseInt($('#toBeAddedAmount').val());

		var totalBalance = 0;
		if ($.isNumeric(currentBalance)) {
			totalBalance = parseInt(currentBalance) + toBeAddedBalance;
		} else {
			totalBalance = toBeAddedBalance;
		}
		$.ajax({
			url: 'wallet/updateBalance/' + totalBalance,
			type: "PUT",
			success: $.proxy(function (data) {
				$('#currentBalance').text(data.balance);
				$('#toBeAddedAmount').val('');
			})
		});
	};

	function saveConnection() {

		var connectionType = $('#connectionType').val();
		var connectionNumber = $('#connectionNumber').val();
		var supplierId = $('#suppliers').val();

		$.ajax({
			url: 'bill/saveConnection?connectionType=' + connectionType + "&connectionNumber=" + connectionNumber + "&supplierId=" + supplierId,
			type: "GET",
			success: $.proxy(function (data) {
				resetConnectionDiv();
				getAllConnectionsByUser();
			})
		});
	};

	function getSuppliersListByType(connectionType) {

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
	}

	function getAllConnectionsByUser() {

		var $connectionsTBody = $('#allConnectionsTBody');
		$connectionsTBody.empty();
		$.ajax({
			url: 'bill/getConnectionsByUserId/1',
			type: "GET",
			success: $.proxy(function (data) {
				var jsonObject = JSON.stringify(data);
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
			url: 'bill/getBillsByConnectionType/' + connectionType,
			type: "GET",
			success: $.proxy(function (data) {
				var jsonObject = JSON.stringify(data);
				$.each(data, function (i, obj) {
					$allBillsTBody.append("" +
						"<tr>" +
						"<td>" + ++i + "<input type='hidden' id='billId' value=" + obj.id + "></td>" +
						"<td>" + obj.connection.supplier.name + "</td>" +
						"<td>" + obj.supplier.name + "</td>" +
						"<td>" + obj.connection.connectionNumber + "</td>" +
						"<td>" + obj.amount + "</td>" +
						"<td>" + obj.billStatus + "</td>" +
						"<td><button id='payBill'>Pay</button></td>" +
						"</tr>");
				});
			})
		});
	};
});