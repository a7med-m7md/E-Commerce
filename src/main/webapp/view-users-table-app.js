function goToUserPage(userId) {
    window.location.href = 'users?id=' + userId;
    console.log("SSSSS")
}

$(document).ready(function () {
    $('tr[data-toggle="modal"]').click(function () {
        var target = $(this).data('target');
        $(target).modal('show');
    });
});


//
// $.get("users",function (response) {
//     let users = JSON.parse(response);
//     for (let user in users){
//         console.log(user.efNamemao)
//     }
// })
//
