// assume productIds is an array of product IDs saved in localStorage
// loop through each product ID and make an AJAX GET request to retrieve product information
import {DOMINO, PORT} from "./configuration.js";
import {ShoppingCart} from "./store.js";


$(document).ready(function () {
    updateCart();
    $('#logout-btn').click(async function (){

        let userID = document.getElementById("userUUID").innerText;

        let storageDate = localStorage.getItem(`cart-${userID}`)
        if(storageDate){
            await $.post("logout", {
                data: storageDate,
                contentType: "application/x-www-form-urlencoded"
            })
        }

        $.get("logout")
        location.reload()
    })
})

export function updateCart() {
    var cartContainer = document.getElementById("cart-container");
    // var clearCartButton = document.getElementById("clear-cart");
    console.log("updated")
    // Remove all child nodes of the cart container element
    while (cartContainer.firstChild) {
        console.log("remove")
        cartContainer.removeChild(cartContainer.lastChild);
    }
    let userUUID = document.getElementById("userUUID").innerText
    if(!userUUID){
        userUUID = "00000000-0000-0000-0000-000000000000"
    }
    const userId = JSON.parse(localStorage.getItem(`cart-${userUUID}`));
    if (userId) {
        var totalSum = 0;
        var totalPrice = 0;
        document.getElementById('items-num').innerText = userId.length
        userId.forEach((product, index) => {
            $.get(`http://localhost:${PORT}/${DOMINO}/laptop?productId=${product.productId}`, response => {
                // handle the response from the server here
                let currentProduct = JSON.parse(response);

                console.log(currentProduct)
                let image = currentProduct.imagList[0]
                // create a new product widget
                var $newProduct = $("<div>", {class: "product-widget"});
                $newProduct.append($("<div>", {class: "product-img"}).append($("<img>", {
                    src: image,
                    alt: ""
                })));
                $newProduct.append($("<div>", {class: "product-body"}).append($("<h3 class='product-name'>", {class: currentProduct.name}).append($("<a>", {href: `http://localhost:${PORT}/store/product?uuidProduct=${currentProduct.uuid}`}).text(currentProduct.name))).append($("<h4>", {class: "product-price"}).append($("<span>", {class: "qty"}).text(userId[index].quantity + "x")).append("EG " + currentProduct.price)));
                // todo add delete functionality
                $newProduct.append($("<button>", {class: "delete", click: ()=>removeProduct(currentProduct.uuid)}).append($("<i>", {class: "fa fa-close"})));
                console.log("aaa" + currentProduct.uuid)
                // append the new product widget to the container
                $("#cart-container").append($newProduct);

                totalSum += userId[index].quantity
                totalPrice += userId[index].quantity * currentProduct.price;
                $('#item-number-sum').text(`${totalSum} Item(s) selected`);
                $('#item-total-price').text(`SUBTOTAL: EG${totalPrice}`)
            });
        });
    }

    function removeProduct(productId){
        console.log("×××")
        let cart = new ShoppingCart(userUUID);
        cart.removeItem({productId, quantity: 1});
    }
}