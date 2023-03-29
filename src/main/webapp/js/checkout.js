import {DOMINO, PORT} from "./configuration.js";
import {ShoppingCart} from "./local-storage-handler.js";

var productList = [];
var totalPrice = 0;
$(document).ready(function () {
    $("#Out").hide();
    $("#afterOrder").hide();
    for (var i = 0; i < localStorage.length; i++) {
        console.log(localStorage.getItem(localStorage.key(i)));
    }
    listProduct();
    let userUUID = document.getElementById("userUUID").innerHTML;
    var jsonLaptops = $.parseJSON(localStorage.getItem(`cart-${userUUID}`))
    $("#placeOrder").click(function () {
        console.log(productList);
        $.ajax({
            url: 'checkout',//servlet url
            type: 'POST', //servlet request type
            data: JSON.stringify(jsonLaptops), //input data
            success: function (data) {
                console.log(data);
                console.log(data === "Out");
                console.log(data === "more");
                console.log(data === "Success");
                if (data === "Out") {
                    $("#Out").show();
                    $("#afterOrder").hide();
                    $("#Out").html(" Our Store Cant Fit Your Order");
                } else if (data === "more") {
                    $("#Out").show();
                    $("#afterOrder").hide();
                    $("#Out").html("More Than Your Credit Limit");

                } else if (data === "Success") {
                    const successModal = document.getElementById('successModal');

                    // Show the modal
                    $(successModal).modal('show');
                    successModal.addEventListener("hidden.bs.modal", function () {
                        // Refresh page
                        window.location.reload();
                    });
                    $(successModal).on("hidden.bs.modal", function () {
                        let cart = new ShoppingCart(userUUID);
                        cart.removeAll()
                        location.reload()
                    })
                    $("#afterOrder").show();
                    $("#Out").hide();
//                 window.location.href = "signin"
                }
            }
        });
    });
});

function listProduct() {
    let container = $(".order-products")[0];
    container.innerHTML = "";
    let userUUID = document.getElementById("userUUID").innerHTML;
    var jsonLaptops = $.parseJSON(localStorage.getItem(`cart-${userUUID}`))
    $.each(jsonLaptops, function (index, labtop) {
        console.log(labtop);
        $.get("checkout?uuid=" + labtop.productId, function (data, status) {
            console.log(data)
            var obj = JSON.parse(data);
            productList.push(data);
            console.log(data);
            var price = `${labtop.quantity}` * `${obj.price}`;
            totalPrice += price;
            let newProduct = `<div class="order-col">
                                   <div>${labtop.quantity} x ${obj.name}</div>
                                   <div>${price}</div>
                                   </div>`;
            container.innerHTML += newProduct;
            $("#totalPrice").text("");
            $('#totalPrice').text(`EG${totalPrice}`);
            $("#totalPrice").addClass("order-total");
        });
    });
}
