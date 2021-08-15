	
	/*~发起警报~*/
	var audio = document.getElementById('myAudio');
    function startAlarm(accountStr, alarmFlag) {
    	if ("failed"==alarmFlag) {
    		audio.src = "../audio/empty502.wav";
    		audio.play();
			audio.addEventListener('ended', loop, false);
			//alert("官网账号【"+"】 订票失败了！");
    	} else if ("less"==alarmFlag) {
    		audio.src = "../audio/less502.wav";
			audio.play();
			audio.addEventListener('ended', loop, false);
			//alert("请注意，官网账号【"+"】 的余票已变少！");
    	}
    }
	/*~循环调用警报~*/
	function loop() {
		if(alarmTimes<2) {
			setTimeout(function () { audio.play();}, 800);
			alarmTimes++;
		} else {
	    	//删除丢票记录
	    	var jsonObj1 = new Object();
	        $.ajax({
	            url: "/demo/deletelost",
	            contentType: "application/json;charset=utf-8",
	            type: "POST",
	            data: JSON.stringify(jsonObj1),
	            success: function () {
	                //location.reload();
	            }
	        });
		}
	}
/*
(function () {
}());
*/