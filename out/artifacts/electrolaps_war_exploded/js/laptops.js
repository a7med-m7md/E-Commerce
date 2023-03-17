/*
// $(document).ready(function() {
//     let laptops = getLaptops(0, 1, 'hp', 'PRICE', 0, 10);
// });
//
//
// function addToScrollbar(laptops) {
//     var jsonLaptops = $.parseJSON(laptops);
//     let container = $(".slick-track")[1];
//     $.each(jsonLaptops, function(index, labtop) {
//         //var labtop = $.parseJSON(value);
//         const blob = new Blob(labtop.imagByte, {type: "image/png"});
//         const url = URL.createObjectURL(blob);
//
//         container.innerHTML += `
//         < div className = "product" >
//             < div className = "product-img" >
//                 < img src = "${url}" alt = "" >
//                 < div className = "product-label" >
//                     < span className = "new" > NEW < /span>
//                 </div>
//             </div>
//             <div className="product-body">
//                 <p className="product-category">${labtop.laptopCategory}</p>
//                 <h3 className="product-name"><a href="#">${labtop.name}</a></h3>
//                 <h4 className="product-price">$${labtop.price} <del className="product-old-price">$990.00</del></h4>
//                 <div className="product-rating">
//                 `;
//
//         /!*for(var i ; i< labtop.rate ; i++){
//             container.innerHTML += '<i className="fa fa-star"></i>';
//         }*!/
//         container.innerHTML += `<i className="fa fa-star"></i>`;
//
//         container.innerHTML += `
//             </div>
//                 <div className="product-btns">
//                     <button className="add-to-wishlist"><i className="fa fa-heart-o"></i><span className="tooltipp">add to wishlist</span>
//                     </button>
//                     <button className="add-to-compare"><i className="fa fa-exchange"></i><span className="tooltipp">add to compare</span>
//                     </button>
//                     <button className="quick-view"><i className="fa fa-eye"></i><span className="tooltipp">quick view</span>
//                     </button>
//                 </div>
//             </div>
//             <div className="add-to-cart">
//                 <button className="add-to-cart-btn"><i className="fa fa-shopping-cart"></i> add to cart</button>
//             </div>
//         </div>
//     `;
//     });
// }
//
// function getLaptops(pageNumber,count,laptopCategory,sortedBy,minPrice,maxPrice){
//     $.ajax({
//         url: "http://localhost:8082/store/laptopservlet", // specify the URL of the API endpoint
//         type: "GET", // specify the type of request (GET in this case)
//         data: {
//             pageNumber : pageNumber,
//             count : count,
//             laptopCategory : laptopCategory,
//             sortedBy : sortedBy,
//             minPrice : minPrice,
//             maxPrice : maxPrice
//         },
//         success: function(data) { // define a callback function to handle the response
//             addToScrollbar(data);
//         },
//         error: function(jqXHR, textStatus, errorThrown) { // handle error cases
//             console.log("Request failed. Status code: " + jqXHR.status);
//         }
//     });
// }

$(document).ready(function() {
    const items = [
        {
            name: "Product 1",
            category: "Category 1",
            price: 10.99,
            rating: 4,
            image: "https://via.placeholder.com/300x200"
        },
        {
            name: "Product 2",
            category: "Category 2",
            price: 15.99,
            rating: 3.5,
            image: "https://via.placeholder.com/300x200"
        },
        {
            name: "Product 3",
            category: "Category 3",
            price: 20.99,
            rating: 5,
            image: "https://via.placeholder.com/300x200"
        },
        {
            name: "Product 4",
            category: "Category 4",
            price: 25.99,
            rating: 4.5,
            image: "https://via.placeholder.com/300x200"
        },
        {
            name: "Product 5",
            category: "Category 5",
            price: 30.99,
            rating: 3,
            image: "https://via.placeholder.com/300x200"
        },
    ];

    const carousel = document.querySelector('.carousel');
    const prevBtn = document.querySelector('.prev');
    const nextBtn = document.querySelector('.next');

    let position = 0;
    const itemWidth = 320;

// create items dynamically
    for (let i = 0; i < items.length; i++) {
        const item = items[i];
        const stars = '<div class="stars">' + getStarsHTML(2);
        // create the stars HTML based on the rating
        function getStarsHTML(rating) {
            let starsHTML = '';
            for (let j = 0; j < 5; j++) {
                if (j < rating) {
                    starsHTML += '<i class="fas fa-star"></i>';
                } else {
                    starsHTML += '<i class="far fa-star"></i>';
                }
            }
            return starsHTML;
        }

        // create the HTML for the item
        const itemHTML = `
    <div class="carousel-item">
      <img src="${item.image}" alt="${item.name}">
      <div class="name">${item.name}</div>
      <div class="category">${item.category}</div>
      <div class="price">$${item.price.toFixed(2)}</div>
      ${stars}
    </div>
  `;

        carousel.innerHTML += itemHTML;
    }

// add event listeners to the prev/next buttons
    prevBtn.addEventListener('click', () => {
        position += itemWidth;
        position = Math.min(position, 0);
        carousel.style.transform = `translateX(${position}px)`;
    });

    nextBtn.addEventListener('click', () => {
        position -= itemWidth;
        const maxPosition = -itemWidth * (items.length - 3);
        position = Math.max(position, maxPosition);
        carousel.style.transform = `translateX(${position}px)`;
    });

});*/
