/*
$(document).ready(function() {
    let laptops = getLaptops(0, 1, 'hp', 'PRICE', 0, 10);
});


function addToScrollbar(laptops) {
    var jsonLaptops = $.parseJSON(laptops);
    let container = document.getElementsByClassName('slick-track')[1];
    $.each(jsonLaptops, function(index, labtop) {
        //var labtop = $.parseJSON(value);
        const blob = new Blob(labtop.imagByte, {type: "image/png"});
        const url = URL.createObjectURL(blob);



        container.innerHTML += `
        <div class="product slick-slide slick-cloned" data-slick-index="-4" tabindex="-1" style="width: 293px;">
            <div className="product-img">
                <img src="${url}" alt="">
            </div>
            <div className="product-body">
                <p className="product-category">${labtop.laptopCategory}</p>
                <h3 className="product-name"><a href="#">${labtop.name}</a></h3>
                <h4 className="product-price">$${labtop.price} <del className="product-old-price">$990.00</del></h4>
                <div className="product-rating">
                `;

        /!*for(var i ; i< labtop.rate ; i++){
            container.innerHTML += '<i className="fa fa-star"></i>';
        }*!/
        container.innerHTML += `<i className="fa fa-star"></i>`;

        container.innerHTML += `
                </div>
                <div className="product-btns">
                    <button className="add-to-wishlist" tabIndex="-1"><i className="fa fa-heart-o"></i><span
                        className="tooltipp">add to wishlist</span></button>
                    <button className="add-to-compare" tabIndex="-1"><i className="fa fa-exchange"></i><span
                        className="tooltipp">add to compare</span></button>
                    <button className="quick-view" tabIndex="-1"><i className="fa fa-eye"></i><span
                        className="tooltipp">quick view</span></button>
                </div>
            </div>
            <div className="add-to-cart">
                <button className="add-to-cart-btn" tabIndex="-1"><i className="fa fa-shopping-cart"></i> add to cart
                </button>
            </div>
        </div>
    `;
    });
}

function getLaptops(pageNumber,count,laptopCategory,sortedBy,minPrice,maxPrice){
    $.ajax({
        url: "http://localhost:8082/store/laptopservlet", // specify the URL of the API endpoint
        type: "GET", // specify the type of request (GET in this case)
        data: {
            pageNumber : pageNumber,
            count : count,
            laptopCategory : laptopCategory,
            sortedBy : sortedBy,
            minPrice : minPrice,
            maxPrice : maxPrice
        },
        success: function(data) { // define a callback function to handle the response
            addToScrollbar(data);
        },
        error: function(jqXHR, textStatus, errorThrown) { // handle error cases
            console.log("Request failed. Status code: " + jqXHR.status);
        }
    });
}*/
