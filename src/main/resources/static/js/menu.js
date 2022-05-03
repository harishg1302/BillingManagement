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
});