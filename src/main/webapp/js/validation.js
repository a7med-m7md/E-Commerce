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
        interests: $('#interests').val(),
    })
    $.ajax({
        url: 'SignUp', //servlet url
        type: 'POST', //servlet request type
        contentType: 'application/json', //For input type
        data: user, //input data
        dataType: 'json'
    });
    window.location.href = "index.jsp"
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
$("#emailS").blur(function () {
    $.get("SignUp?email=" + $("#emailS").val(), ajaxCallBack);
    console.log("External content loaded successfully!");
});
function ajaxCallBack(responseTxt, statusTxt, xhr) {
    if (responseTxt == "Exist") {
        $("#emailE").empty();
        var errorMeassage = $("<label>").addClass("emailE");
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
