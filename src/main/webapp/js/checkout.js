import { DOMINO, PORT } from "./configuration.js";
var productList = [];
var totalPrice = 0;
$(document).ready(function () {
    for (var i = 0; i < localStorage.length; i++) {
        console.log(localStorage.getItem(localStorage.key(i)));
    }
    listProduct();
    console.log(productList);
    $("#placeOrder").click(function () {
        $.ajax({
            url: 'checkout',//servlet url
            type: 'POST', //servlet request type
            contentType: 'application/json', //For input type
            data: JSON.stringify(productList), //input data
            dataType: 'json',
            success: function (data) {
                console.log(data);
            }
        });
    });
});

function listProduct() {
    let container = $(".order-products")[0];
    container.innerHTML = "";

    let userUUID = document.getElementById("userUUID").innerHTML;
    var jsonLaptops = $.parseJSON(localStorage.getItem(`cart-${userUUID}`))
    //    var jsonLaptops = $.parseJSON(localStorage.getItem("cart-e060af7d-1849-450d-942f-d1ddd338a98c"))
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
