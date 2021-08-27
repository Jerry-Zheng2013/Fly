$(document).ready(function () {
    //登录功能
    $("#dashboard").click(function () {
    	location.href="/flight/dashboard";
    });
    
    $("#managesFlightTicks").click(function () {
    	location.href="/flight/allFlightList";
    });
    
    $("#historyFlightTicks").click(function () {
    	location.href="/flight/historyFlightList";
    });
    
    $(".cancel_ticket").click(function () {
    	var orderStatus = $(this).parent().find('.orderStatus').text();
    	if ("正常"==orderStatus) {
    		//正常的票，暂停订票
        	var oiId = $(this).parent().find('.oiId').text();
        	var orderNo = $(this).parent().find('.orderNo').text();
        	var accountNo = $(this).parent().find('.accountNo').text();
        	accountNo=accountNo.replace(/\s+/g,"");
            var jsonObj = new Object();
            jsonObj.oiId = oiId;
            jsonObj.orderNo = orderNo;
            jsonObj.accountNo = accountNo;
            $.ajax({
                url: "/demo/cancel",
                contentType: "application/json;charset=utf-8",
                type: "POST",
                data: JSON.stringify(jsonObj),
                success: function () {
                    //alert("提交成功");
                    location.reload();
                }
            });
    	}else if ("订单暂停"==orderStatus) {
    		//重新预定
        	var oiId = $(this).parent().find('.oiId').text();
        	var tripCode = $(this).parent().find('.tripCode').text();
        	var flightNo = $(this).parent().find('.flightNo').text();
        	var cabinCode = $(this).parent().find('.cabinCode').text();
        	var ticketNumber=window.prompt('请输购票票数','');
        	
            var jsonObj = new Object();
            jsonObj.oiId = oiId;
            jsonObj.tripCode = tripCode;
            jsonObj.flghtNo = flightNo;
            jsonObj.cabinCode = cabinCode;
            jsonObj.ticketNumber = ticketNumber;
            $.ajax({
                url: "/demo/readd",
                contentType: "application/json;charset=utf-8",
                type: "POST",
                data: JSON.stringify(jsonObj),
                success: function () {
                    //alert("提交成功");
                    location.reload();
                }
            });
    	} else {
    		//正常结束,压票失败、取消失败、暂停失败
    		//确认是否删除
    		if (confirm("确认要删除订单？")) {
    			var oiId = $(this).parent().find('.oiId').text();
            	var jsonObj = new Object();
                jsonObj.oiId = oiId;
                $.ajax({
                    url: "/demo/delete",
                    contentType: "application/json;charset=utf-8",
                    type: "POST",
                    data: JSON.stringify(jsonObj),
                    success: function () {
                        location.reload();
                    }
                });
    		}
    	}
    });

    
    $(".delete_ticket").click(function () {
		//正常结束,压票失败、取消失败、暂停失败
		//确认是否删除
		if (confirm("确认要删除订单？")) {
			var oiId = $(this).parent().find('.oiId').text();
        	var jsonObj = new Object();
            jsonObj.oiId = oiId;
            $.ajax({
                url: "/demo/delete",
                contentType: "application/json;charset=utf-8",
                type: "POST",
                data: JSON.stringify(jsonObj),
                success: function () {
                    location.reload();
                }
            });
		}
    });
    

    $("#startPlay").click(function () {
    	//查询丢票记录，循环查询，每个一分钟查询一次
        setTimeout(function(){
        	var jsonObj2 = new Object();
        	setInterval(function () {
        		$.ajax({
        			async: false,
        			url: "/demo/getlost",
        			contentType: "application/json;charset=utf-8",
        			type: "POST",
        			data: jsonObj2,
        			success(data){
        				console.log("=====查询到的丢票记录信息为["+data+"]=====");
        				var resultStr = data;
        				if(resultStr) {
        					var accountStr = resultStr.split("K")[0];
        					var codeStr = resultStr.split("K")[1];
        					startAlarm(accountStr, codeStr);
        				}
        			}
        		})
        	},20000);
        }, 1000);
    });
    
    $("#stopAlarm").click(function () {
    	//删除丢票记录
    	var jsonObj1 = new Object();
        $.ajax({
            url: "/demo/deletelost",
            contentType: "application/json;charset=utf-8",
            type: "POST",
            data: JSON.stringify(jsonObj1),
            success: function () {
                location.reload();
            }
        });
    });
    
    
    
    
    
    
    
    
    
    
    
	/*~发起警报~*/
    function startAlarm2() {
	    audio.addEventListener('ended', loop, false);
	    audio.play();
    }
    /*~关闭警报~*/
    function stopAlarm2() {
	    audio.removeEventListener('ended', loop, false);
	    audio.pause();
    }
	/*~循环调用警报~*/
	function loop2() {
	    setTimeout(function () { audio.play(); }, 700);
	}
    
    
    
    
});
