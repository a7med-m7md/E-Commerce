import {DOMINO, PORT} from "./configuration.js";

$(document).ready(function () {
    let container1 = $(".products-slick")[0];
    getLaptops(container1, 1, 10, '', 'PRICE', 0, 10000);

    let container2 = $(".products-slick")[1];
    getLaptops(container2, 1, 10, '', 'RATE', 0, 10000);
});
function addToScrollbar(container, laptops) {
    var jsonLaptops = $.parseJSON(laptops);
    $.each(jsonLaptops, function (index, labtop) {
        let image = btoa(String.fromCharCode.apply(null, new Uint8Array(labtop.imagByteList[0])));
        let newProduct = `
            <div class="product">
                <input type="hidden" value="${labtop.uuid}">
                <div class="product-img">
                    <img src="data:image/png;base64,${image}"
                            width="263" 
                            height="263"
                            alt="">
                </div>
                <div class="product-body">
                    <p class="product-category">${labtop.laptopCategory}</p>
                    <h3 class="product-name"><a href="product?uuidProduct=${labtop.uuid}">${labtop.name}</a></h3>
                    <h4 class="product-price">$${labtop.price} <del class="product-old-price">$${labtop.price}</del></h4>
                    <div class="product-rating">
                    `;
        for(var i=0 ; i< labtop.rate ; i++){
            newProduct += '<i class="fa fa-star"></i>';
        }

        newProduct += `
                    </div>
                </div>
                <div class="add-to-cart">
                    <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
                </div>
            </div>
            `;
        container.innerHTML += newProduct;
    });
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
}

function getLaptops(container, pageNumber, count, laptopCategory, sortedBy, minPrice, maxPrice) {
    $.ajax({
        url: `http://localhost:${PORT}/${DOMINO}/laptopservlet`, // specify the URL of the API endpoint
        type: "GET", // specify the type of request (GET in this case)
        data: {
            pageNumber: pageNumber,
            count: count,
            laptopCategory: laptopCategory,
            sortedBy: sortedBy,
            minPrice: minPrice,
            maxPrice: maxPrice
        }, success: function (data) { // define a callback function to handle the response
            addToScrollbar(container, data);
        }, error: function (jqXHR, textStatus, errorThrown) { // handle error cases
            console.log("Request failed. Status code: " + jqXHR.status);
        },
        async : false
    });
}
