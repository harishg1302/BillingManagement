$(document).ready(function () {

	$('#registerBtn').on('click', function () {
		register();
	});

	function register() {

		var isFormValid = validation();
		if (isFormValid) {
			var data = {
				name: $('input[name=name]').val(),
				mobileNumber: $('input[name=mobileNumber]').val(),
				email: $('input[name=email]').val(),
				address: $('input[name=address]').val(),
				password: $('input[name=password]').val()
			}
			$.ajax({
				url: 'user/register',
				type: "POST",
				data: JSON.stringify(data),
				contentType: "application/json",
				success: $.proxy(function (data) {
					window.location.href = "login"
				})
			});
		}
	};

	function userNameValidation() {
		var name = $('input[name=name]').val();
		if (name.length == 0) {
			$('#nameErrorSpan').show();
			return false;
		} else {
			$('#nameErrorSpan').hide();
			return true;
		}
	}

	function mobileValidation() {
		var mobile = $('input[name=mobileNumber]').val();

		if (mobile.length < 10) {
			$('#mobileErrorSpan').show();
			return false;
		} else {
			$('#mobileErrorSpan').hide();
			return true;
		}
	}

	function emailValidation() {
		var email = $('input[name=email]').val();
		var pattern = /^\b[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b$/i

		if (!pattern.test(email)) {
			$('#emailErrorSpan').show();
			return false;
		} else {
			$('#emailErrorSpan').hide();
			return true;
		}
	}

	function passwordValidation() {
		var password = $('input[name=password]').val();
		if (password.length == 0) {
			$('#passwordErrorSpan').show();
			return false;
		} else {
			$('#passwordErrorSpan').hide();
			return true;
		}
	}

	function confirmPasswordValidation() {
		var password = $('input[name=password]').val();
		var confirmPassword = $('input[name=confirmPassword]').val();

		if (password != confirmPassword) {
			$('#ConfirmPasswordErrorSpan').show();
			return false;
		} else {
			$('#ConfirmPasswordErrorSpan').hide();
			return true;
		}
	}

	function validation() {
		var isValidName = userNameValidation();
		var isValidMobile = mobileValidation();
		var isValidEmail = emailValidation();
		var isValidPassword = passwordValidation();
		var isPasswordMatch = confirmPasswordValidation();
		if (isValidName && isValidMobile && isValidEmail && isValidPassword && isPasswordMatch) {
			return true;
		} else {
			return false;
		}
	}
});