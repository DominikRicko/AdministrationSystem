import "@progress/kendo-ui";

let testDiv = document.getElementById("test");

$("#test").kendoAppBar({
    themeColor: "inherit",
    items: [
        { template: '<a class="k-button" href="\\#"><span class="k-icon k-i-menu"></span></a>', type: "contentItem" },
        { width: 16, type: "spacer" },
        { template: '<h3 class="title">All Products</h3>', type: "contentItem" },
        { width: 16, type: "spacer" },
        { template: '<a class="k-button k-clear-search" href="\\#">Clear search</a>', type: "contentItem" },
        { type: "spacer" },
        { template: kendo.template($("#search-template").html()), type: "contentItem" },
        { width: 8, type: "spacer" },
        { template: '<a class="k-button k-toggle-button" href="\\#"><span class="k-icon k-i-saturation"></span></a>', type: "contentItem" }
    ]
}).on("click", ".k-button", function (e) {
    e.preventDefault();
}).on("click", ".k-clear-search", function (e) {
    $("#appbar input.k-input").val("").trigger("input");
}).on("click", ".k-toggle-button", function (e) {
    var chartElement = $("#chart");
    var gridElement = $("#grid");

    if (chartElement.is(":visible")) {
        chartElement.hide();
        gridElement.css("width", "100%");
    } else {
        chartElement.show();
        gridElement.css("width", "");
    }
});



