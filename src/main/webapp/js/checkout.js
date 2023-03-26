$(document).ready(function () {



    for (var i = 0; i < localStorage.length; i++) {
        console.log(localStorage.getItem(localStorage.key(i)));
    }
    listProduct();
});

function listProduct() {
    let container = $(".order-products")[0];
    container.innerHTML = "";
    var jsonLaptops = $.parseJSON(localStorage.getItem("cart-a6d6b894-1c68-46e2-a5b0-3d43d97aec9c"))
    var productList = [];
    $.each(jsonLaptops, function (index, labtop) {
        console.log(labtop);
         $.get("checkout?uuid=" + labtop.productId,  function(data, status) {
           console.log(data)
           var obj = JSON.parse(data);
           console.log(JSON.parse(data));
           productList.push(JSON.stringify(data));
          price = `${labtop.quantity}` * `${obj.price}`;
          console.log(price)
          let newProduct = `<div class="order-col">
                                   <div>${labtop.quantity} x ${obj.name}</div>
                                   <div>${price}</div>
                                   </div>`;
                 container.innerHTML += newProduct;
         });
    });
}