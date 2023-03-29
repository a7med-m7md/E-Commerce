$(document).ready(function () {



    // Validate Username
    $("#isinvalid").hide();
    $("#usercheck").hide();
    $("#dateCheck").hide();
    $("#genderCheck").hide();
    $("#jobCheck").hide();
    $("#addressCheck").hide();
    $("#creditCheck").hide();
    $("#interestsCheck").hide();
    let usernameError = true;
    let duplicatedError = true;
    $("#usernames").keyup(function () {
        validateUsername();
    });
    $("#lastusercheck").hide();
    let lastUserNameError = true;
    $("#lastName").keyup(function () {
        validateLastname();
    });
    function validateFirstname() {
        let usernameValue = $("#firstName").val();
        let lastname = $("#firstName").val();
        if (usernameValue.length == "" || lastname.length == "") {
            $("#usercheck").show();
            usernameError = false;
            return false;
        } else if (usernameValue.length < 3 || usernameValue.length > 10) {
            $("#usercheck").show();
            $("#usercheck").html("**length of username must be between 3 and 10");
            usernameError = false;
            return false;
        } else {
            $("#usercheck").hide();
        }
    }

    function validateLastname() {
        let usernameValue = $("#lastName").val();
        let lastname = $("#lastName").val();
        if (usernameValue.length == "") {
            $("#lastusercheck").show();
            lastusercheck = false;
            return false;
        } else if (usernameValue.length < 3 || usernameValue.length > 10) {
            $("#lastusercheck").show();
            $("#lastusercheck").html("**length of username must be between 3 and 10");
            lastusercheck = false;
            return false;
        } else {
            $("#lastusercheck").hide();
            lastusercheck = true;
        }
    }

    // Validate Email
    function validateEmail() {
        let email = $("#emailS").val();
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
    //
    //  // Validate Password
    $("#passcheck").hide();
    let passwordError = true;
    $("#password").keyup(function () {
        validatePassword();
    });
    function validatePassword() {
        let passwordValue = $("#passwordS").val();
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
    // validate Date
    function validateDate() {
        let dateS = $("#dateS").val();
        if (dateS.length == "") {
            $("#dateCheck").show();
            dateError = false;
            return false;
        } else if (dateS.length < 3 || dateS.length > 10) {
            $("#dateCheck").show();
            $("#dateCheck").html("**length of date must be between 3 and 10");
            dateError = false;
            return false;
        } else {
            $("#dateCheck").hide();
            dateError = true;
        }
    }
    // validate Gender
    function validateGender() {
        if (!$("input[name='gender']:checked").is(":checked")) {
            $("#genderCheck").show();
            genderError = false;
            return false;
        } else {
            $("#genderCheck").hide();
            genderError = true;
            return true;
        }
    }
    // validate JOB
    function validateJob() {
        let job = $("#job").val();
        if (job.length == "") {
            $("#jobCheck").show();
            jobError = false;
            return false;
        } else if (job.length < 3 || job.length > 10) {
            $("#jobCheck").show();
            $("#jobCheck").html("**length of date must be between 3 and 10");
            jobError = false;
            return false;
        } else {
            $("#jobCheck").hide();
            jobError = true;
        }
    }
    // validate ADDRESS
    function validateAddress() {
        let address = $("#address").val();
        if (address.length == "") {
            $("#addressCheck").show();
            addressError = false;
            return false;
        } else if (job.length < 3 || job.length > 10) {
            $("#addressCheck").show();
            $("#addressCheck").html("**length of date must be between 3 and 10");
            addressError = false;
            return false;
        } else {
            $("#addressCheck").hide();
            addressError = true;
        }
    }
    // validate CREDIT
    function validateCredit() {
        let credit = $("#creditL").val();
        if (credit.length == "") {
            $("#creditCheck").show();
            creditError = false;
            return false;
        } else if (job.length < 3 || job.length > 10) {
            $("#creditCheck").show();
            $("#creditCheck").html("**length of date must be between 3 and 10");
            creditError = false;
            return false;
        } else {
            $("#creditCheck").hide();
            creditError = true;
        }
    }
    // validate Interests
    function validateInterests() {
        let interests = $("#interests").val();
        if (interests.length == "") {
            $("#interestsCheck").show();
            interestsError = false;
            return false;
        } else {
            $("#interestsCheck").hide();
            interestsError = true;
            return false;
        }
    }
    // Validate Email
    $("#emailS").blur(function () {
        $.get("signup?email=" + $("#emailS").val(), ajaxCallBack);
        console.log("External content loaded successfully!");
    });
    function ajaxCallBack(responseTxt, statusTxt, xhr) {
        var errorMeassage = $("<label>").addClass("emailE");
        if (responseTxt == "Exist") {
            duplicatedError = false;
            $(".emailE").empty();
            errorMeassage.append("This Email is used");
            errorMeassage.addClass("errorMeassage");
            $("#emaildev").append(errorMeassage);
        }
        else {
            $(".emailE").empty();
            $(".emailE").removeClass("errorMeassage");
            $(".emailE").remove();
            duplicatedError = true;
        }
    }
    //  // Submit button
    $("#signupbtn").click(function () {
        validateFirstname();
        validateLastname();
        validatePassword();
        validateEmail();
        validateDate();
        validateGender();
        validateJob();
        validateAddress();
        validateCredit();
        validateInterests();
                console.log(usernameError + " " + passwordError + " " +
                    emailError + " " + lastusercheck + " " + dateError + " " +
                    genderError + " " + jobError + " " + addressError + " " +
                    creditError + " " + interestsError + " " +
                    duplicatedError);
        if ((usernameError == true && passwordError == true
            && emailError == true && lastusercheck == true
            && dateError == true && genderError == true
            && jobError == true && addressError == true
            && creditError == true && interestsError == true
            && duplicatedError == true)
        ) {
            var user = JSON.stringify({
                birthDay: $('#dateS').val(),
                fName: $('#firstName').val(),
                lName: $('#lastName').val(),
                gender: $("input[name='gender']:checked").val(),
                password: $('#passwordS').val(),
                job: $('#job').val(),
                eMail: $('#emailS').val(),
                creditLimit: $('#creditL').val(),
                address: $('#address').val(),
                interests: $('#interests').val()
                //        role : "false"
            })
            $.ajax({
                url: 'signup', //servlet url
                type: 'POST', //servlet request type
                contentType: 'application/json', //For input type
                data: user, //input data
                dataType: 'json',
                success: function (data) {
                    window.location.href = "signin"
                }
            });
            return true;
        } else {
            let button = document.getElementById('saveBtn');
            button.innerHTML = ''
            return false;
        }
    });
});


function validateEmail() {
    const emailInput = document.getElementById("emailL");
    const email = emailInput.value;
    const emailRegex = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/; // regular expression for email validation
    if (emailRegex.test(email)) {
        // valid email
        return true;
    } else {
        // invalid email
        alert("Please enter a valid email address.");
        return false;
    }

    const passwordInput = document.getElementById("passwordL");
    const password = passwordInput.value;
    if(password.length < 6){
        alert("Please enter a valid email password.");
        return false;
    }

}
