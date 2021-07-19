$(document).ready(function(){
    $("btnLogin").kendoButton();
})

// $("#header").kendoAppBar({
//     items: [
//         {template: "<img id='logo' src='/images/logo.png' alt='Logo'>", type: "contentItem", className: "headerLeft"},
//         {template: "<span> logout </span>", type: "contentItem", className: "headerRight"},
//     ],
//     positionMode: 'sticky',
//     position: 'top',
//     themeColor: 'light',
// })

kendo.init('#btnLogin');