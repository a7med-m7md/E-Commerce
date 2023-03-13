$('#loginbtn').click(function () {
    console.log("fffd")
    var emailL = $('#emailL').val();
    var passL = $('#passwordL').val();
    if (emailL.length === 0 && passL.length === 0) {
        alert('Enter your name');
    }
    else if (IsEmail(emailL) == false) {
        alert('Invalid Email');
        return false;
    }
});
$('#signupbtn').click(function () {
    var emailS = $('#emailS').val();
    var passS = $('#passwordS').val();
    var firstName = $('#firstName').val();
    var lastName = $('#lastName').val();
    var date = $('#dateS').val();
    var job = $('#job').val();
    var address = $('#address').val();
    var creditL = $('#creditL').val();
    var gender = $("input[name='gender']:checked").val();
    if (emailS.length === 0 && passS.length === 0 &&
        firstName.length === 0 && lastName.length === 0 &&
        date.length === 0 && job.length === 0 &&
        address.length === 0 && creditL.length === 0 &&
        gender) {
        console.log(gender)
        alert('Enter your all Input');
    }
    else if (IsEmail(emailS) == false) {
        alert('Invalid Email');
        return false;
    }
});
function IsEmail(email) {
   if (!(email.match(/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*$/))) {
        return false;
    } else {
        return true;
    }
}