$('#loginbtn').click(function () {
	console.log("fffd")
	var emailL = $('#emailL').val();
	var passL = $('#passwordL').val();
   if (IsEmail(emailL) == false) {
   		$('#emailL').addClass("error");
   		return false;
   	}
   	else
   	$('#emailL').removeClass("error");
});
$('#form').submit(function() {
   if(!$("#form input[type=text]").val() || !$("#form input[type=password]").val())
   {
    $("#form input[type=text]").addClass("error");
        return false;
   }
   else
   $("#form input[type=text]").removeClass("error");
       return true;

});
$('#signupbtn').click(function () {
	var emailS = $('#emailS').val();
	if (IsEmail(emailS) == false) {
		$('#emailS').addClass("error");
		return false;
	}
	else
	$('#emailS').removeClass("error");
        return true;

});
function IsEmail(email) {
	if (!(email.match(/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*$/))) {
		return false;
	} else {
		return true;
	}
}