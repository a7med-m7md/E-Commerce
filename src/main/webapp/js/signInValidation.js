$(document).ready(function () {

    $("#isinvalid").hide();
    // Validate Email
    function validateEmail() {
        let email = $("#emailL").val();
        console.log(email)
        if (email) {
            if ((email.match(/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/))) {
                $("#isinvalid").hide();
                emailError = true;
                return true;
            } else {
                $("#isinvalid").show();
                emailError = false;
                return false;
            }
        }
        else if (email.length == "") {
            $("#isinvalid").show();
            emailError = false;
            return false;
        }
    }
    //
    //  // Validate Password
    $("#passcheck").hide();
    let passwordError = true;
    $("#password").keyup(function () {
        validatePassword();
    });
    function validatePassword() {
        let passwordValue = $("#passwordL").val();
        if (passwordValue.length == "") {
            $("#passcheck").show();
            passwordError = true;
            return false;
        }
        if (passwordValue.length < 3 || passwordValue.length > 10) {
            $("#passcheck").show();
            $("#passcheck").html(
                "**length of your password must be between 3 and 10"
            );
            $("#passcheck").css("color", "red");
            passwordError = false;
            return true;
        } else {
            $("#passcheck").hide();
        }
    }
    $("#loginbtn").click(function () {

        validatePassword();
        validateEmail();

        console.log(emailError +" "+ passwordError);
        if (emailError == true && passwordError == true) {
            var loginEntity = JSON.stringify({
                email: $('#emailL').val(),
                password: $('#passwordL').val()
            })
            $.ajax({
                url: 'signin', //servlet url
                type: 'POST', //servlet request type
                contentType: 'application/json', //For input type
                data: loginEntity, //input data
                dataType: 'json'
            });
        }
    });
});