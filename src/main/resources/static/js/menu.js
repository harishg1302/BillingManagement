$(document).ready(function () {
	$('#walletDiv').hide();
	$('#profileDiv').hide();
	$('#billingDiv').hide();

	$('#walletBtn').on('click', function () {
		$('#walletDiv').show();
		$('#profileDiv').hide();
		$('#billingDiv').hide();
		getWalletBalance();
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
    			    window.location.href="login"
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
});