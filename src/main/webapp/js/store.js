import {DOMINO, PORT} from "./configuration.js";
import {updateCart} from "./cart-widget.js";
import {ShoppingCart} from "./local-storage-handler.js";


var carts = [];

var shoppingCart;
var currentUserUUID;
$(document).ready(function () {
    //Detect count of pages
    getPagesCount();
////////
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
    const filter = document.querySelectorAll('.filter-btn')[0];
    filter.addEventListener("click", function() {
        getPage();
    });


    //Get all categories in db
    getCategories();

    //Get page of products from db
    getPage();

    // shoppingCart = new ShoppingCart();

    //Handel pages buttons
    const products = document.querySelectorAll('#productImage');
    products.forEach(li => {
        li.addEventListener('click', event => {
            let uuid = document.querySelector(`#uuid + *`);
            $.ajax({
                url: `product?uuid=${uuid}`, // specify the URL of the API endpoint
                type: "GET", // specify the type of request (GET in this case)
                success: function (data) { // define a callback function to handle the response
                    addToCategory(data);
                }, error: function (jqXHR, textStatus, errorThrown) { // handle error cases
                    console.log("Request failed. Status code: " + jqXHR.status);
                },
                async: false
            });
        });
    });
});

function getPagesCount() {
    $.ajax({
        url: `pagesCount`, // specify the URL of the API endpoint
        type: "GET", // specify the type of request (GET in this case)
        success: function (data) { // define a callback function to handle the response
            let count = $("#count")[0].value;
            let numberOfPages = Math.ceil(Math.round(data / count));
            let container = $(".store-pagination")[0];
            for (let i = 2; i <= numberOfPages; i++) {
                container.innerHTML += `
                    <li><a>${i}</a></li>
                `;
            }
            container.innerHTML += '<li><a><i class="fa fa-angle-right"></i></a></li>';
        }, error: function (jqXHR, textStatus, errorThrown) { // handle error cases
            console.log("Request failed. Status code: " + jqXHR.status);
        },
        async: false
    });
}

function getCategories() {
    $.ajax({
        url: `category`, // specify the URL of the API endpoint
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
    // Get the URL search parameters
    var searchParams = new URLSearchParams(window.location.search);
    // Get the value of the "myParam" parameter
    var myParamValue = searchParams.get("category");

    //Get json from string
    let jsonCategories = $.parseJSON(categories);

    let category = $(".checkbox-filter")[0];
    jsonCategories.forEach(async (item, index) => {
        category.innerHTML += `
        <div class="input-checkbox">
            <input type="checkbox" id="${item.categoryName}" ${item.categoryName == myParamValue ? 'checked' : ''}>
                <label for ="${item.categoryName}">
                    <span></span>
                    ${item.categoryName}
                </label>
        </div>
        `;
    });
}

function addTopage(laptops) {
    var jsonLaptops = $.parseJSON(laptops);
    let container = $("#products")[0];
    $.each(jsonLaptops, function (index, labtop) {

        let newProduct = `
                <div class="col-md-4 col-xs-6">
                    <div class="product">
                        <input type="hidden" id="uuid" value="${labtop.uuid}">
                        <div class="product-img">
                            <img id="productImage" src="${labtop.imagList[0]}" 
                                    width="263" 
                                    height="263"
                                    alt="">
                        </div>
                        <div class="product-body">
                            <p class="product-category">${labtop.laptopCategory}</p>
                            <h3 class="product-name"><a href="product?uuidProduct=${labtop.uuid}" class="two-lines">${labtop.name}</a></h3>
                            <h4 class="product-price">EG ${labtop.price}</h4>
                            <div class="product-rating">
                    `;
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
        const product = document.querySelectorAll(".add-to-cart-btn");
        for (let i = 0; i < product.length; i++) {
            product[i].addEventListener("click", async function () {
                product[i].classList.toggle(jsonLaptops[i].uuid);
                await $.get("addToCard?uuid=" + jsonLaptops[i].uuid, ajaxCallBack);
                // updateCart()
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

    //Detect checked categories
    const checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
    const values = [];
    checkboxes.forEach((checkbox) => {
        values.push(checkbox.id);
    });

    //Detect filters data
    let laptopCategory = values;
    let pageNumber = activeLiNumber;
    let count = $("#count")[0].value;
    let sortedBy = $("#sortBy")[0].value;
    let minPrice = $("#price-min")[0].value;
    let maxPrice = $("#price-max")[0].value;

    if (minPrice == "")
        minPrice = 0;
    if (maxPrice == "")
        maxPrice = 100000;

    $.ajax({
        url: `laptoppagination`, // specify the URL of the API endpoint
        type: "GET", // specify the type of request (GET in this case)
        data: {
            pageNumber: pageNumber,
            count: count,
            laptopCategory: laptopCategory.join(','),
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
///
    shoppingCart = new ShoppingCart(currentUserUUID);
    shoppingCart.addItem(item)

    return true;
}

function addToCardInLocalStorge(labtop) {
    localStorage.setItem("labtop", JSON.stringify(labtop));
    return true;
}


/// User Cart Class


function getUserId() {
    return currentUserUUID;
}











