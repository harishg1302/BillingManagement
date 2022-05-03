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

	$('#dthBtn').on('click', function () {
		saveConnection();
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

	function getSuppliersListByType(connectionType) {

		var $suppliers = $('#suppliers');
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

	function saveConnection() {

		var connectionType = $('#connectionType').val();
		var connectionNumber = $('#connectionNumber').val();
		var supplierId = $('#suppliers').val();

		$.ajax({
			url: 'bill/saveConnection?connectionType=' + connectionType + "&connectionNumber=" + connectionNumber + "&supplierId=" + supplierId,
			type: "GET",
			success: $.proxy(function (data) {
				console.log('data: ' + data);
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
});