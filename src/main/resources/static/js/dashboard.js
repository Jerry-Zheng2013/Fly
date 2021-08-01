$(document).ready(function () {
    //登录功能
    $("#dashboard").click(function () {
    	location.href="/flight/dashboard";
    });
    
    $("#managesFlightTicks").click(function () {
    	location.href="/flight/allFlightList";
    });
    
    $(".cancel_ticket").click(function () {
        if ($(this).parent().find('.orderStatus').text() == '1'){
            var ticket_number=window.prompt('请输购票票数','');  
	        if( ticket_number=="" || ticket_number==null )  
	        {  
	            ticket_number="你没有输入票数";
	            alert(ticket_number);
	        } else {
	            location.href="/demo/readd?ticket_number=" + ticket_number;
	        }
        } else {
            location.href="/demo/cancel";
        }
        
    });
});
