$(document).ready(function () {
    //登录功能
    $("#dashboard").click(function () {
    	location.href="/flight/dashboard";
    });
    
    $("#managesFlightTicks").click(function () {
    	location.href="/flight/allFlightList";
    });
});
