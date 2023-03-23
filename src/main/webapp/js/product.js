import {DOMINO, PORT} from "./configuration.js";

$(document).ready(function () {
    //Get product data
    getMyProduct("db950c3d-4f7c-4d5d-84d4-0fa520db7785");

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
    loadScript(`http://localhost:${PORT}/${DOMINO}/js/jquery.min.js`)
        .then(() => {
            console.log('Script loaded!');
        })
        .catch(() => {
            console.error('Script loading failed! Handle this error');
        });
    loadScript(`http://localhost:${PORT}/${DOMINO}/js/bootstrap.min.js`)
        .then(() => {
            console.log('Script loaded!');
        })
        .catch(() => {
            console.error('Script loading failed! Handle this error');
        });
    loadScript(`http://localhost:${PORT}/${DOMINO}/js/slick.min.js`)
        .then(() => {
            console.log('Script loaded!');
        })
        .catch(() => {
            console.error('Script loading failed! Handle this error');
        });
    loadScript(`http://localhost:${PORT}/${DOMINO}/js/nouislider.min.js`)
        .then(() => {
            console.log('Script loaded!');
        })
        .catch(() => {
            console.error('Script loading failed! Handle this error');
        });
    loadScript(`http://localhost:${PORT}/${DOMINO}/js/jquery.zoom.min.js`)
        .then(() => {
            console.log('Script loaded!');
        })
        .catch(() => {
            console.error('Script loading failed! Handle this error');
        });
    loadScript(`http://localhost:${PORT}/${DOMINO}/js/main.js`)
        .then(() => {
            console.log('Script loaded!');
        })
        .catch(() => {
            console.error('Script loading failed! Handle this error');
        });
});

function getMyProduct(productId){
    $.ajax({
        url: `http://localhost:${PORT}/${DOMINO}/laptop`, // specify the URL of the API endpoint
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

function addToPage(jsonLaptop){
    var laptop = $.parseJSON(jsonLaptop);
    let container = $("#myProduct")[0];

    let newProduct = `
        <!-- Product main img -->
        <div class="col-md-5 col-md-push-2">
            <div id="product-main-img">
    `;

    $.each(laptop.imagByteList, function (index, bytes) {
        let image = btoa(String.fromCharCode.apply(null, new Uint8Array(bytes)));
        newProduct += `
                <div class="product-preview">
                    <img src="data:image/png;base64,${image}"
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

    $.each(laptop.imagByteList, function (index, bytes) {
        let image = btoa(String.fromCharCode.apply(null, new Uint8Array(bytes)));
        newProduct += `
                <div class="product-preview">
                    <img src="data:image/png;base64,${image}"
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
                <h2 class="product-name">product name goes here</h2>
                <div>
                    <div class="product-rating">
    `;

    for(let i = 0 ; i<laptop.rate ; i++){
        newProduct += `
            <i class="fa fa-star"></i>
        `;
    }

    newProduct += `
                </div>
                    <a class="review-link" href="#">10 Review(s) | Add your review</a>
                </div>
                <div>
                    <h3 class="product-price">$${laptop.price} <del class="product-old-price">$${laptop.price}/del></h3>
                    <span class="product-available">In Stock</span>
                </div>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
        
                <div class="product-options">
                    <label>
                        Size
                        <select class="input-select">
                            <option value="0">X</option>
                        </select>
                    </label>
                    <label>
                        Color
                        <select class="input-select">
                            <option value="0">Red</option>
                        </select>
                    </label>
                </div>
        
                <div class="add-to-cart">
                    <div class="qty-label">
                        Qty
                        <div class="input-number">
                            <input type="number">
                                <span class="qty-up">+</span>
                                <span class="qty-down">-</span>
                        </div>
                    </div>
                    <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
                </div>
        
                <ul class="product-btns">
                    <li><a href="#"><i class="fa fa-heart-o"></i> add to wishlist</a></li>
                    <li><a href="#"><i class="fa fa-exchange"></i> add to compare</a></li>
                </ul>
        
                <ul class="product-links">
                    <li>Category:</li>
                    <li><a href="#">Headphones</a></li>
                    <li><a href="#">Accessories</a></li>
                </ul>
        
                <ul class="product-links">
                    <li>Share:</li>
                    <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                    <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                    <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                    <li><a href="#"><i class="fa fa-envelope"></i></a></li>
                </ul>
        
            </div>
        </div>
        <!-- /Product details -->
    `;

    container.innerHTML += newProduct;
}