// assume productIds is an array of product IDs saved in localStorage
// loop through each product ID and make an AJAX GET request to retrieve product information
import {DOMINO, PORT} from "./configuration.js";
import {ShoppingCart} from "./local-storage-handler.js";


document.addEventListener('DOMContentLoaded', function() {

    // code to execute after the DOM is loaded
});

$(document).ready(function () {
    let userUUID = document.getElementById("userUUID").innerText
    let items = JSON.parse(localStorage.getItem("cart-00000000-0000-0000-0000-000000000000"))
    console.log(userUUID.length)
    // Create a Promise that resolves when the $.get request is complete

    if (userUUID && items && items.length != 0 && userUUID != "00000000-0000-0000-0000-000000000000") {
        const cartPromise = new Promise(function (resolve, reject) {
            $.get(`cart`, function (response) {
                localStorage.setItem(`cart-${userUUID}`, response)
                console.log("VVVAA")
                console.log(response)

                // Resolve the Promise with the response data
                resolve(response);
            });
        });

        // Wait for the Promise to resolve before continuing
        cartPromise.then(function (response) {
            removeCartUIElement()
            if (userUUID && items && items.length != 0 && userUUID != "00000000-0000-0000-0000-000000000000") {
                const cart = new ShoppingCart(userUUID);
                cart.mergeWithGivenCart();
                console.log("UIDD " + userUUID)
                console.log("INSIDDE" + userUUID + "   " + 0)
            }

        });
    }
    updateCart()
    $('#logout-btn').click(async function () {
        let userID = document.getElementById("userUUID").innerText;
        let storageDate = localStorage.getItem(`cart-${userID}`);

        if (storageDate) {
            await $.post("logout", {
                data: storageDate,
                contentType: "application/x-www-form-urlencoded"
            });
        }

        $.get("logout")
        location.reload()
    })
});


export function updateCart() {
    autoLogin().then(function(response) {
        // Do something with the response

    }).catch(function(error) {
        // Handle the error
    });

    removeCartUIElement()
    let userUUID = document.getElementById("userUUID").innerText
    if (!userUUID) {
        userUUID = "00000000-0000-0000-0000-000000000000"
    }
    const userId = JSON.parse(localStorage.getItem(`cart-${userUUID}`));
    if (userId) {
        var totalSum = 0;
        var totalPrice = 0;
        document.getElementById('items-num').innerText = userId.length
        userId.forEach(async (product, index) => {
            await $.get(`laptop?productId=${product.productId}`, response => {
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
                $newProduct.append($("<div>", {class: "product-body"}).append($("<h3 class='product-name'>", {class: currentProduct.name}).append($("<a>", {href: `product?uuidProduct=${currentProduct.uuid}`}).text(currentProduct.name))).append($("<h4>", {class: "product-price"}).append($("<span>", {class: "qty"}).text(userId[index].quantity + "x")).append("EG " + currentProduct.price)));
                // todo add delete functionality
                $newProduct.append($("<button>", {
                    class: "delete",
                    click: () => removeProduct(currentProduct.uuid)
                }).append($("<i>", {class: "fa fa-close"})));
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

    function removeProduct(productId) {
        console.log("×××")
        let cart = new ShoppingCart(userUUID);
        cart.removeItem({productId, quantity: 1});
    }

}

export function removeCartUIElement() {
    var cartContainer = document.getElementById("cart-container");
    if(cartContainer) {
        // var clearCartButton = document.getElementById("clear-cart");
        console.log("updated")
        // Remove all child nodes of the cart container element
        while (cartContainer.firstChild) {
            console.log("remove")
            cartContainer.removeChild(cartContainer.lastChild);
        }
    }
}



function autoLogin() {
    return new Promise((resolve, reject) => {
        let email;
        let pass;

        console.log("CCCCC")
        console.log(document.cookie)

        if (!document.cookie.indexOf("loggedIn") != -1) {
            let cookies = document.cookie.split(';');

            for (let i = 0; i < cookies.length; i++) {
                let cookie = cookies[i].trim();
                console.log("=== " + cookie + " ====")
                if (cookie.startsWith('user_remember_cookie=')) {
                    let credentials = cookie.substring('user_remember_cookie='.length, cookie.length);
                    let values = credentials.split("=");
                    email = values[0];
                    pass = values[1];

                    $.post("signin", {
                        emailL: email,
                        passwordL: pass
                    }, function (response) {
                        document.cookie += ";loggedIn=true;";
                        console.log(username);
                        resolve(response);
                    }).fail(function (error) {
                        reject(error);
                    });

                    break;
                }
            }
        }
    });
}
