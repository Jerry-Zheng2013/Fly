	
	/*~发起警报~*/
	var audio = document.getElementById('myAudio');
    function startAlarm(accountStr, alarmFlag) {
    	if ("failed"==alarmFlag) {
    		audio.src = "../audio/empty501.wav";
    		audio.play();
			audio.addEventListener('ended', loop, false);
			//alert("官网账号【"+"】 订票失败了！");
    	} else if ("less"==alarmFlag) {
    		audio.src = "../audio/less501.wav";
			audio.play();
			audio.addEventListener('ended', loop, false);
			//alert("请注意，官网账号【"+"】 的余票已变少！");
    	}
    }
	/*~循环调用警报~*/
	function loop() {
	    setTimeout(function () { audio.play();}, 800);
	}
/*
(function () {
}());
*/