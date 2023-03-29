import {DOMINO, PORT} from "./configuration.js";
import {ShoppingCart} from "./local-storage-handler.js";

$(document).ready(function () {
    // Get the URL search parameters
    var searchParams = new URLSearchParams(window.location.search);
    // Get the value of the "myParam" parameter
    var myParamValue = searchParams.get("uuidProduct");

    //Get product data
    getMyProduct(myParamValue);

    // definition
    function loadScript(scriptUrl) {
        const script = document.createElement('script');
        script.src = scriptUrl;
        document.body.appendChild(script);

        return new Promise((res, rej) => {
            script.onload = function() {
                res();
            }
            script.onerror = function () {
                rej();
            }
        });
    }

// use
    loadScript(`js/jquery.min.js`)
        .then(() => {
            console.log('Script loaded!');
        })
        .catch(() => {
            console.error('Script loading failed! Handle this error');
        });
    loadScript(`js/bootstrap.min.js`)
        .then(() => {
            console.log('Script loaded!');
        })
        .catch(() => {
            console.error('Script loading failed! Handle this error');
        });
    loadScript(`js/slick.min.js`)
        .then(() => {
            console.log('Script loaded!');
        })
        .catch(() => {
            console.error('Script loading failed! Handle this error');
        });
    loadScript(`js/nouislider.min.js`)
        .then(() => {
            console.log('Script loaded!');
        })
        .catch(() => {
            console.error('Script loading failed! Handle this error');
        });
    loadScript(`js/jquery.zoom.min.js`)
        .then(() => {
            console.log('Script loaded!');
        })
        .catch(() => {
            console.error('Script loading failed! Handle this error');
        });
    loadScript(`js/main.js`)
        .then(() => {
            console.log('Script loaded!');
        })
        .catch(() => {
            console.error('Script loading failed! Handle this error');
        });
});

export function getMyProduct(productId){
    $.ajax({
        url: `laptop`, // specify the URL of the API endpoint
        type: "GET", // specify the type of request (GET in this case)
        data: {
            productId: productId
        }, success: function (data) { // define a callback function to handle the response
            addToPage(data);
        }, error: function (jqXHR, textStatus, errorThrown) { // handle error cases
            console.log("Request failed. Status code: " + jqXHR.status);
        },
        async: false
    });
}


// todo it returns undefined
function addToPage(jsonLaptop){
    console.log(jsonLaptop)
    if(jsonLaptop) {
        var laptop = $.parseJSON(jsonLaptop);
        let container = $("#myProduct")[0];

        let newProduct = `
        <!-- Product main img -->
        <div class="col-md-5 col-md-push-2">
            <div id="product-main-img">
    `;

        $.each(laptop.imagList, function (index, bytes) {
            newProduct += `
                <div class="product-preview">
                    <img src="${bytes}"
                     width="600" 
                     height="600"
                     alt="">
                </div>
        `;
        });

        newProduct += `
            </div>
        </div>
        <!-- /Product main img -->
    `;

        newProduct += `
        <!-- Product thumb imgs -->
        <div class="col-md-2  col-md-pull-5">
            <div id="product-imgs">
    `;

        $.each(laptop.imagList, function (index, bytes) {
            newProduct += `
                <div class="product-preview">
                    <img src="${bytes}"
                     width="153" 
                     height="153"
                     alt="">
                </div>
        `;
        });

        newProduct += `
            </div>
        </div>
        <!-- /Product main img -->
    `;

        newProduct += `
        <!-- Product details -->
        <div class="col-md-5">
            <div class="product-details">
                <h2 class="product-name">${laptop.name}</h2>
                <div>
                    <div class="product-rating">
    `;
        let update = document.getElementById("updateBTN");
        if (update) {
            update.setAttribute("href", `updateProduct?uuid=${laptop.uuid}`);
        }
        // for (let i = 0; i < laptop.rate; i++) {
        //     newProduct += `
        //     <i class="fa fa-star"></i>
        // `;
        // }

        let expression = `
            <span class="product-available"> ${laptop.quantities> 0 ? 'In Stock' : 'Out of Stock'}  </span>
        `
        newProduct += `
                
                
                    <h3 class="product-price">EG ${laptop.price}</h3>
                   ${expression}
                
        
                <div class="add-to-cart">
                    <div class="qty-label">
                        Quantity
                        <div class="input-number">
                            <input type="number" id="qty-val" value="1" min="1"  required>
                                <span class="qty-up">+</span>
                                <span class="qty-down">-</span>
                        </div>
                    </div>
                    <button class="add-to-cart-btn" id="add-tocart-btn"><i class="fa fa-shopping-cart" ></i>add to cart</button>
                </div>
        
                <ul class="product-links">
                    <li>Category:</li>
                    <li><a href="store?category=${laptop.laptopCategory}">${laptop.laptopCategory}</a></li>
                </ul>        
            </div>
        </div>
        <!-- /Product details -->
    `;

        container.innerHTML += newProduct;

        //Add description to details tap
        let desc = $('#desc')[0];
        desc.innerHTML += `
        <p>${laptop.description}</p>
    `;

        document.getElementById("add-tocart-btn").addEventListener('click', function () {
            let quantityVal = document.getElementById("qty-val").value;
            if (quantityVal) {
                let productId = laptop.uuid
                let quantity = parseInt(document.getElementById("qty-val").value);
                let item = {
                    productId,
                    quantity
                }
                console.log(item)
                console.log("UUID : " + document.getElementById("userUUID").innerHTML)
                let cart = new ShoppingCart(document.getElementById("userUUID").innerHTML);
                cart.addItem(item)
            } else {
                alert("Quantity must be specified")
            }
        })

        document.querySelector(".qty-up").addEventListener('click', function () {
            let quantity = document.getElementById("qty-val");
            quantity.value = parseInt(quantity.value) + 1;
        })
        document.querySelector(".qty-down").addEventListener('click', function () {
            let quantity = document.getElementById("qty-val").value;
            quantity.value = parseInt(quantity.value) - 1;
        })
    }
}


addToPage()






function addToCard(uuid){
    console.log("Hello "+ uuid)
}