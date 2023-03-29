function validateForm() {
    const laptopName = document.getElementById("laptop-name").value;
    const laptopPrice = document.getElementById("laptop-price").value;
    const laptopDescription = document.getElementById("laptop-description").value;
    const laptopQuantity = document.getElementById("laptop-quantity").value;
    const laptopPhoto = document.getElementById("laptop-photo").value;

    var radios = document.querySelectorAll('#list-category-val input[type=radio]');


    var isChecked = false;
    for (var i = 0; i < radios.length; i++) {
        if (radios[i].checked) {
            isChecked = true;
            break;
        }
    }

    if (!isChecked || laptopName === "" || laptopPrice === "" || laptopDescription === "" || laptopQuantity === "" || laptopPhoto === "") {
        alert("Please fill in all fields");
        return false;
    }

    return true;
}


