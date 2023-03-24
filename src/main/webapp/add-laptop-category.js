$(document).ready(function () {
    let lapCat = document.getElementById("laptop-category");
    if (lapCat){
        lapCat = lapCat.innerHTML;
    }
    $.ajax({
        type: "GET",
        url: "category",
        success: function (response) {

            var categoryList = JSON.parse(response);
            console.log(categoryList)
            for (var i = 0; i < categoryList.length; i++) {
                var category = categoryList[i];
                console.log(category.categoryName)
                console.log(category.laptopCategoryUuid)
                var li = $("<li>").addClass("list-group-item d-flex justify-content-between align-items-center");
                var div = $("<div>").addClass("custom-control custom-radio");
                var input = $("<input>").attr("type", "radio").attr("id", category.laptopCategoryUuid).attr("name", "category").addClass("custom-control-input").attr("id", category.categoryName);
                console.log("CAT :: " + lapCat)
                var label = $("<label>").attr("for", category.laptopCategoryUuid).addClass("custom-control-label").text(category.categoryName);
                div.append(input);
                div.append(label);
                li.append(div);
                li.append($("<button>").addClass("btn btn-sm btn-outline-danger ml-2").html("<i class='fa fa-trash'></i>"));
                $("#list-category-val").append(li);
                if(lapCat == category.categoryName){
                    document.getElementById(lapCat).checked = true;
                }
            }
        }
    });

});

// $('#category-name').on('change', function() {
//     var category = $(this).val();
//     console.log(category + " <<==")
//     $('#hidden-category').val(category)
// });

$("#list-category-val").on("change", "input[type=radio]", function() {
    var selectedCategory = $(this).siblings("label").text();
    console.log("Should change to " + selectedCategory)
    $('#hidden-category').val(selectedCategory)
    console.log($('#hidden-category').val() + " vvval")
});

function addNewCategory(event) {
    event.preventDefault();
    let categoryName = $("#category-name").val();
    $.post("category", {"category-name": categoryName}, function (response) {
        let categoryInfo = JSON.parse(response);
        var li = $("<li>").addClass("list-group-item d-flex justify-content-between align-items-center");
        var div = $("<div>").addClass("custom-control custom-radio");
        var input = $("<input>").attr("type", "radio").attr("id", categoryInfo.laptopCategoryUuid).attr("name", "category").addClass("custom-control-input");
        var label = $("<label>").attr("for", categoryInfo.laptopCategoryUuid).addClass("custom-control-label").text(categoryName);
        div.append(input);
        div.append(label);
        li.append(div);
        li.append($("<button>").addClass("btn btn-sm btn-outline-danger ml-2").html("<i class='fa fa-trash'></i>"));
        $("#list-category-val").append(li);
    })
}