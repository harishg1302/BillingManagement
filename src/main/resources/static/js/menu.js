$(document).ready(function () {
	$('#walletDiv').hide();
	$('#profileDiv').hide();
	$('#billingDiv').hide();
	clickOnWalletBtn();

	$('#walletBtn').on('click', function () {
		clickOnWalletBtn();
	});
	$('#profileBtn').on('click', function () {
		$('#profileDiv').show();
		$('#walletDiv').hide();
		$('#billingDiv').hide();
	});

	$('#billingBtn').on('click', function () {
		$('#profileDiv').hide();
		$('#walletDiv').hide();
		$('#billingDiv').show();
	});

	$('#logoutBtn').on('click', function () {
		logout();
	});

	$('#addWalletAmountBtn').on('click', function () {
		updateWalletBalance();
	});

	$('#connectionType').on('change', function () {
		var connectionType = $(this).val();
		getSuppliersListByType(connectionType);
	});

	function clickOnWalletBtn() {
		$('#walletDiv').show();
		$('#profileDiv').hide();
		$('#billingDiv').hide();
		getWalletBalance();
	}

	function getWalletBalance() {

		$.ajax({
			url: 'wallet/getByUserId',
			type: "GET",
			success: $.proxy(function (data) {
				console.log('data: ' + data.balance);
				$('#currentBalance').text(data.balance)
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
				console.log('data: ' + data.balance);
				$('#currentBalance').text(data.balance)
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
						value: obj.name,
						text: obj.name
					}));
				});
			})
		});
	};

	function saveConnection() {

		var $suppliers = $('#suppliers');
		$.ajax({
			url: 'bill/getSuppliersByConnectionType',
			type: "GET",
			success: $.proxy(function (data) {
				console.log('data: ' + data.balance);
				$.each(data, function (i, obj) {
					$suppliers.append($('<option>', {
						value: obj.name,
						text: obj.name
					}));
				});
			})
		});
	};
});