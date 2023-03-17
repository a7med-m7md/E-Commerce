$(document).ready(function() {
    $.ajax({
        type: "GET",
        url: "category",
        success: function(response) {

            var categoryList = JSON.parse(response);
            console.log(categoryList)
            for (var i = 0; i < categoryList.length; i++) {
                var category = categoryList[i];
                console.log(category.categoryName)
                var li = $("<li>").addClass("list-group-item d-flex justify-content-between align-items-center");
                var div = $("<div>").addClass("custom-control custom-radio");
                var input = $("<input>").attr("type", "radio").attr("id", "category" + i).attr("name", "category").addClass("custom-control-input");
                var label = $("<label>").attr("for", "category" + i).addClass("custom-control-label").text(category.categoryName);
                div.append(input);
                div.append(label);
                li.append(div);
                li.append($("<button>").addClass("btn btn-sm btn-outline-danger ml-2").html("<i class='fa fa-trash'></i>"));
                $("#list-category-val").append(li);
            }
        }
    });
});


function addNewCategory(event){
    event.preventDefault();
    let categoryName = $("#category-name").val();
    $.post("category", {"category-name": categoryName}, function () {
        var li = $("<li>").addClass("list-group-item d-flex justify-content-between align-items-center");
        var div = $("<div>").addClass("custom-control custom-radio");
        var input = $("<input>").attr("type", "radio").attr("id", "category1" ).attr("name", "category").addClass("custom-control-input");
        var label = $("<label>").attr("for", "category" ).addClass("custom-control-label").text(categoryName);
        div.append(input);
        div.append(label);
        li.append(div);
        li.append($("<button>").addClass("btn btn-sm btn-outline-danger ml-2").html("<i class='fa fa-trash'></i>"));
        $("#list-category-val").append(li);
    })
}