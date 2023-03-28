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
                return false;
            } else {
                $("#isinvalid").show();
                emailError = false;
                return false;
            }
        }
    }
    //validate Password
    $("#passcheck").hide();
    let passwordError = true;
    $("#password").keyup(function () {
        validatePassword();
    });
    function validatePassword() {
        let passwordValue = $("#passwordL").val();
        if (passwordValue.length == "") {
            $("#passcheck").show();
            passwordError = false;
            return false;
        }
        if (passwordValue.length < 3 || passwordValue.length > 10) {
            $("#passcheck").show();
            $("#passcheck").html(
                "**length of your password must be between 3 and 10"
            );
            $("#passcheck").css("color", "red");
            passwordError = true;
            return false;
        } else {
            $("#passcheck").hide();
        }
    }

    $("#loginbtn").click(function () {
        validatePassword();
        validateEmail();
        if (passwordError == true && emailError == true) {
            var user = JSON.stringify({
                eMail: $('#emailL').val(),
                password: $('#passwordL').val()
            })
            $.ajax({
                url: 'signin', //servlet url
                type: 'POST', //servlet request type
                data: user,
                success: function (data) {
                    window.location.href = "signin"
                }
            });
            return true;
        } else {
            return false;
        }
    });

});