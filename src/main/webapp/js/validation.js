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
$('#form').submit(function () {
    if (!$("#form input[type=text]").val() || !$("#form input[type=password]").val()) {
        $("#form input[type=text]").addClass("error");
        return false;
    }
    else
        $("#form input[type=text]").removeClass("error");
    return true;
});
$('#signupbtn').click(function () {
    console.log("helloooo")
    var emailS = $('#emailS').val();
    console.log("helyyyyyyloooo")
    if (IsEmail(emailS) == false) {
        $('#emailS').addClass("error");
    }
    else {
        $('#emailS').removeClass("error");
    }

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
    console.log(user)
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

});
function IsEmail(email) {
    if (!(email.match(/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*$/))) {
        return false;
    } else {
        return true;
    }
}
$("#emailS").blur(function () {
    $.get("signup?email=" + $("#emailS").val(), ajaxCallBack);
    console.log("External content loaded successfully!");
});
function ajaxCallBack(responseTxt, statusTxt, xhr) {

    var errorMeassage = $("<label>").addClass("emailE");
    if (responseTxt == "Exist") {
        $(".emailE").empty();
        errorMeassage.append("This Email is used");
        errorMeassage.addClass("errorMeassage");
        $("#emaildev").append(errorMeassage);
    }
    else {
        $(".emailE").empty();
        $(".emailE").removeClass("errorMeassage");
        $(".emailE").remove();
    }

}
