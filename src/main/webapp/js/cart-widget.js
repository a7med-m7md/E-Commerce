// assume productIds is an array of product IDs saved in localStorage
// loop through each product ID and make an AJAX GET request to retrieve product information
$(document).ready(function () {
    const userUUID = document.getElementById("userUUID").innerText
    const userId = JSON.parse(localStorage.getItem(`cart-${userUUID}`));
    userId.forEach((product, index) => {
        $.get(`http://localhost:8081/store/laptop?productId=${product.productId}`, response => {
            // handle the response from the server here
            let currentProduct = JSON.parse(response);

            let image = btoa(String.fromCharCode.apply(null, new Uint8Array(currentProduct.imagByteList[0])));

            // create a new product widget
            var $newProduct = $("<div>", {class: "product-widget"});
            $newProduct.append($("<div>", {class: "product-img"}).append($("<img>", {
                src: `data:image/png;base64,${image}`,
                alt: ""
            })));
            $newProduct.append($("<div>", {class: "product-body"}).append($("<h3 class='product-name'>", {class: currentProduct.name}).append($("<a>", {href: `http://localhost:8081/store/product?uuidProduct=${currentProduct.uuid}`}).text(currentProduct.name))).append($("<h4>", {class: "product-price"}).append($("<span>", {class: "qty"}).text(userId[index].quantity + "x")).append("EG " + currentProduct.price)));
            // todo add delete functionality
            $newProduct.append($("<button>", {class: "delete"}).append($("<i>", {class: "fa fa-close"})));

// append the new product widget to the container
            $("#cart-container").append($newProduct);

        });
    });

})



