import { DOMINO, PORT } from "./configuration.js";
import {updateCart} from "./cart-widget.js";

var carts = [];

var shoppingCart;
var currentUserUUID;
$(document).ready(function () {
    shoppingCart = new ShoppingCart();
    //Handel pages buttons
    const liElements = document.querySelectorAll('.store-pagination li');
    liElements.forEach(li => {
        li.addEventListener('click', event => {
            // Remove the active class from all li elements
            liElements.forEach(otherLi => {
                otherLi.classList.remove('active');
            });

            // Add the active class to the clicked li element
            const clickedLi = event.target.closest('li');
            clickedLi.classList.add('active');

            //Send ajax to server to get new page of products
            getPage();
            updateCart();
        });
    });

    //Get all categories in db
    getCategories();

    //Get page of products from db
    getPage();
});
function getCategories() {
    $.ajax({
        url: `http://localhost:${PORT}/${DOMINO}/category`, // specify the URL of the API endpoint
        type: "GET", // specify the type of request (GET in this case)
        success: function (data) { // define a callback function to handle the response
            addToCategory(data);
        }, error: function (jqXHR, textStatus, errorThrown) { // handle error cases
            console.log("Request failed. Status code: " + jqXHR.status);
        },
        async: false
    });
}

function addToCategory(categories) {
    let jsonCategories = $.parseJSON(categories);

    let category = $(".checkbox-filter")[0];
    jsonCategories.forEach(async (item) => {
        category.innerHTML += `
        <div class="input-checkbox">
            <input type="checkbox" id="brand-1">
                <label htmlFor="brand-1">
                    <span></span>
                    ${item.categoryName}
                    <small>(578)</small>
                </label>
        </div>
        `;
    });
}

function addTopage(laptops) {
    var jsonLaptops = $.parseJSON(laptops);
    let container = $("#products")[0];
    $.each(jsonLaptops, function (index, labtop) {
        // const blob = new Blob(labtop.imagByteList[0], {type: "image/png"});
        // const url = URL.createObjectURL(blob);
        let image = btoa(String.fromCharCode.apply(null, new Uint8Array(labtop.imagByteList[0])));

        let newProduct = `
                <div class="col-md-4 col-xs-6">
                    <div class="product">
                        <input type="hidden" id="uuid" value="${labtop.uuid}">
                        <div class="product-img">
                            <img id="productImage" src="data:image/png;base64,${image}"
                                    width="263"
                                    height="263"
                                    alt="">
                                    <div class="product-label">
                                        <span class="sale">-30%</span>
                                        <span class="new">NEW</span>
                                    </div>
                        </div>
                        <div class="product-body">
                            <p class="product-category">${labtop.laptopCategory}</p>
                            <h3 class="product-name"><a href="product?uuidProduct=${labtop.uuid}">${labtop.name}</a></h3>
                            <h4 class="product-price">$${labtop.price} <del class="product-old-price">$${labtop.price}</del></h4>
                            <div class="product-rating">
                    `;
        for(var i=0 ; i< labtop.rate ; i++){
            newProduct += '<i className="fa fa-star"></i>';
        }

        newProduct += `
                            </div>
                        </div>
                        <div class="add-to-cart">
                            <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
                        </div>
                    </div>
                </div>
            </div>
            `;
        container.innerHTML += newProduct;
        const product = document.querySelectorAll(".product");
        for (let i = 0; i < product.length; i++) {
            product[i].addEventListener("click", async function () {
                product[i].classList.toggle(jsonLaptops[i].uuid);
                await $.get("addToCard?uuid=" + jsonLaptops[i].uuid, ajaxCallBack);
                updateCart()
            });

        }

    });

}

function getPage() {
    //Empty the current page
    let container = $("#products")[0];
    container.innerHTML = "";

    //Detect the new page number
    const activeLi = document.querySelector('.store-pagination li.active');
    const activeLiNumber = parseInt(activeLi.textContent);

    //Detect filters data
    let pageNumber = activeLiNumber;
    let count = $("#count")[0].value;
    let laptopCategory = "";//$("")[0];
    let sortedBy = $("#sortBy")[0].value;
    let minPrice = $("#price-min")[0].value;
    let maxPrice = $("#price-max")[0].value;

    if(minPrice == "")
        minPrice = 0;
    if(maxPrice == "")
        maxPrice = 100000;

    $.ajax({
        url: `http://localhost:${PORT}/${DOMINO}/laptoppagination`, // specify the URL of the API endpoint
        type: "GET", // specify the type of request (GET in this case)
        data: {
            pageNumber: pageNumber,
            count: count,
            laptopCategory: laptopCategory,
            sortedBy: sortedBy,
            minPrice: minPrice,
            maxPrice: maxPrice
        }, success: function (data) { // define a callback function to handle the response
            addTopage(data);
        }, error: function (jqXHR, textStatus, errorThrown) { // handle error cases
            console.log("Request failed. Status code: " + jqXHR.status);
        },
        async: false
    });
}
function ajaxCallBack(responseTxt, statusTxt, xhr) {
    console.log("TXT")
    // console.log(JSON.parse(responseTxt))
    // console.log(JSON.stringify(responseTxt.laptopId))
    // carts.push(JSON.stringify(responseTxt.laptopId));
    $.ajax({
        type: "POST",
        url: "addToCard",
        data: JSON.stringify(responseTxt.laptopId)
    });
    currentUserUUID = responseTxt.userId;
    console.log("USer : " + currentUserUUID)
    // localStorage.setItem("Cart", JSON.stringify(responseTxt));

    let item = {
        // userId: responseTxt.userId,
        productId: responseTxt.laptopId,
        quantity: 1
    }

    shoppingCart = new ShoppingCart();
    shoppingCart.addItem(item)

    return true;
}

function addToCardInLocalStorge(labtop) {
    localStorage.setItem("labtop", JSON.stringify(labtop));
    return true;
}



/// User Cart Class


class ShoppingCart {
    constructor() {
        this.items = [];
        this.loadFromLocalStorage();
    }

    addItem(item) {
        let existingItem = this.items.find(
            (cartItem) => cartItem.productId === item.productId
        );
        if (existingItem) {
            existingItem.quantity += item.quantity;
        } else {
            this.items.push(item);
        }
        this.saveToLocalStorage();
    }


    removeItem(item) {
        const index = this.items.indexOf(item);
        if (index !== -1) {
            this.items.splice(index, 1);
            this.saveToLocalStorage();
        }
    }

    updateItemQuantity(item, quantity) {
        item.quantity = quantity;
        this.saveToLocalStorage();
    }

    clear() {
        this.items = [];
        this.saveToLocalStorage();
    }

    saveToLocalStorage() {
        const userId = getUserId(); // get user ID from session or cookie
        if (userId) {
            const key = `cart-${userId}`;
            const value = JSON.stringify(this.items);
            localStorage.setItem(key, value);
        }
    }

    loadFromLocalStorage() {
        const userId = getUserId(); // get user ID from session or cookie
        if (userId) {
            const key = `cart-${userId}`;
            const value = localStorage.getItem(key);
            if (value) {
                console.log("exist")
                this.items = JSON.parse(value);
            }
        }
    }
}

function getUserId() {
    return currentUserUUID;
}












