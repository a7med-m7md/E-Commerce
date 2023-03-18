function goToUserPage(userId) {
    window.location.href = 'users?id=' + userId;
    console.log("SSSSS")
}

$(document).ready(function () {
    var target;
    $('tr[data-toggle="modal"]').click(function () {
        target = $(this).data('target');
        $(target).modal('show');
    });

    $('.closeBTN').click(function (){
        $(target).modal('hide')
        console.log("close")
    })
});



//
// $.get("users",function (response) {
//     let users = JSON.parse(response);
//     for (let user in users){
//         console.log(user.efNamemao)
//     }
// })
//
