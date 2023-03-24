import {DOMINO, PORT} from "./configuration.js";

$(document).ready(function () {
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
        });
    });

    //Get all categories in db
    getCategories();

    //Get page of products from db
    getPage();
});
function getCategories(){
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

function addToCategory(categories){
    let jsonCategories = $.parseJSON(categories);

    let category = $(".checkbox-filter")[0];
    jsonCategories.forEach(async (item) => {
        category.innerHTML += `
        <div className="input-checkbox">
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
            <div className="row" id="products">
                <input type="hidden" value="${labtop.uuid}">
                <div class="product">
                    <div class="product-img">
                        <img src="data:image/png;base64,${image}" 
                                width="263" 
                                height="263"
                                alt="">
                                <div className="product-label">
                                    <span className="sale">-30%</span>
                                    <span className="new">NEW</span>
                                </div>
                    </div>
                    <div class="product-body">
                        <p class="product-category">${labtop.laptopCategory}</p>
                        <h3 class="product-name"><a href="#">${labtop.name}</a></h3>
                        <h4 class="product-price">$${labtop.price} <del class="product-old-price">$${labtop.price}</del></h4>
                        <div class="product-rating">
                    `;
        for(var i=0 ; i< labtop.rate ; i++){
            newProduct += '<i className="fa fa-star"></i>';
        }

        newProduct += `
                        </div>
                        <div class="product-btns">
                            <button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
                            <button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>
                            <button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
                        </div>
                    </div>
                    <div class="add-to-cart">
                        <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
                    </div>
                </div>
            </div>
            `;
        container.innerHTML += newProduct;
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

