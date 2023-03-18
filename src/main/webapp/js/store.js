/*
$(document).ready(function () {
    getCategories();
    //let laptops = getLaptops(1, 10, 'hp', 'PRICE', 0, 10000);


});

function getCategories(){
    $.ajax({
        url: "http://localhost:8082/store/category", // specify the URL of the API endpoint
        type: "GET", // specify the type of request (GET in this case)
        success: function (data) { // define a callback function to handle the response
            addToCategory(data);
        }, error: function (jqXHR, textStatus, errorThrown) { // handle error cases
            console.log("Request failed. Status code: " + jqXHR.status);
        }
    });
}

function addToCategory(categories){
    alert(categories);
}

function addToScrollbar(laptops) {
    var jsonLaptops = $.parseJSON(laptops);
    let container = $(".products-slick")[1];
    $.each(jsonLaptops, function (index, labtop) {
        //var labtop = $.parseJSON(value);
        const blob = new Blob(labtop.imagByteList[0], {type: "image/png"});
        const url = URL.createObjectURL(blob);
        let c = btoa(String.fromCharCode.apply(null, new Uint8Array(labtop.imagByteList[0])));
        let newProduct = `
            <div class="product">
                <div class="product-img">
                    <img src="data:image/png;base64,${c}" 
                            width="263" 
                            height="263"
                            alt="">
                </div>
                <div class="product-body">
                    <p class="product-category">${labtop.laptopCategory}</p>
                    <h3 class="product-name"><a href="#">${labtop.name}</a></h3>
                    <h4 class="product-price">$${labtop.price} <del class="product-old-price">$990.00</del></h4>
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
            `;
        container.innerHTML += newProduct;
    });

}

function getLaptops(pageNumber, count, laptopCategory, sortedBy, minPrice, maxPrice) {
    $.ajax({
        url: "http://localhost:8082/store/laptopservlet", // specify the URL of the API endpoint
        type: "GET", // specify the type of request (GET in this case)
        data: {
            pageNumber: pageNumber,
            count: count,
            laptopCategory: laptopCategory,
            sortedBy: sortedBy,
            minPrice: minPrice,
            maxPrice: maxPrice
        }, success: function (data) { // define a callback function to handle the response
            addToScrollbar(data);
        }, error: function (jqXHR, textStatus, errorThrown) { // handle error cases
            console.log("Request failed. Status code: " + jqXHR.status);
        }
    });
}
*/
