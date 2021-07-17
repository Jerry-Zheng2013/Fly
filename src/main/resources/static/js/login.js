
$(document).ready(function () {
    //登录功能
    $("#login-btn1").click(function () {
        var name = $("#name").val();
        var pwd = $("#pwd").val();
        var jsonObj = new Object();
        jsonObj.username = name;
        jsonObj.password = pwd;
        if (name == null || name == "") {
            alert("用户名不能为空！")
        } else if (pwd == "" || pwd == null) {
            alert("密码不能为空！")
        } else {
            $.ajax({
                url: "/user/login",
                data: JSON.stringify(jsonObj),
                type: "POST",
                // async:false,
                // dataType:"json",
                contentType: "application/json; charset=utf-8",
                success: function (data, staus) {
                    alert("登录成功");
                    if (data.isManager = "1") {
                    	//window.location = "dashboard.html";
                    	location.href="/flight/dashboard";
                    }
                },
                error: function () {
                    alert("登录失败")
                }

            });
        }
    });
    //注册跳转
    $("#login-btn2").click(function () {
        window.location = "registered.html"
    });

    //预定
    $("#adddemo-btn").click(function () {
        var name = $("#name").val();
        var pwd = $("#pwd").val();
        var jsonObj = new Object();
        jsonObj.username = name;
        jsonObj.password = pwd;
        debugger;
        var loginJsonArr = [
        	{"mode":"memberLogin","memberId":"15083142384","password":"ZXC123456","openId":""},
        	{"mode":"memberLogin","memberId":"15083142384","password":"ZXC123456","openId":""},
        	{"mode":"memberLogin","memberId":"15136795044","password":"ZXC123456","openId":""},
        	{"mode":"memberLogin","memberId":"18236194478","password":"ZXC123456","openId":""},
        	{"mode":"memberLogin","memberId":"13409246697","password":"ZXC123456","openId":""},
        	{"mode":"memberLogin","memberId":"15236469495","password":"ZXC123456","openId":""},
        	{"mode":"memberLogin","memberId":"18240695516","password":"ZXC123456","openId":""},
        	{"mode":"memberLogin","memberId":"15903874413","password":"ZXC123456","openId":""},
        	{"mode":"memberLogin","memberId":"13419857462","password":"ZXC123456","openId":""},
        	{"mode":"memberLogin","memberId":"18738520948","password":"ZXC123456","openId":""},
        	{"mode":"memberLogin","memberId":"13460424048","password":"ZXC123456","openId":""},
        	{"mode":"memberLogin","memberId":"18790628324","password":"ZXC123456","openId":""},
        	{"mode":"memberLogin","memberId":"13460499659","password":"ZXC123456","openId":""},
        	{"mode":"memberLogin","memberId":"13460472798","password":"ZXC123456","openId":""},
        	{"mode":"memberLogin","memberId":"15037307404","password":"ZXC123456","openId":""},
        	{"mode":"memberLogin","memberId":"13462248152","password":"ZXC123456","openId":""},
        	{"mode":"memberLogin","memberId":"13462314295","password":"ZXC123456","openId":""},
        	{"mode":"memberLogin","memberId":"13462247535","password":"ZXC123456","openId":""},
        	{"mode":"memberLogin","memberId":"15090390474","password":"ZXC123456","openId":""},
        	{"mode":"memberLogin","memberId":"18240664262","password":"ZXC123456","openId":""},
        	{"mode":"memberLogin","memberId":"18790620994","password":"ZXC123456","openId":""},
        	{"mode":"memberLogin","memberId":"18238641164","password":"ZXC123456","openId":""},
        	{"mode":"memberLogin","memberId":"13460470511","password":"ZXC123456","openId":""},
        	{"mode":"memberLogin","memberId":"15137374854","password":"ZXC123456","openId":""},
        	{"mode":"memberLogin","memberId":"13409245350","password":"ZXC123456","openId":""},
        	{"mode":"memberLogin","memberId":"13460424637","password":"ZXC123456","openId":""},
        	{"mode":"memberLogin","memberId":"13462238479","password":"ZXC123456","openId":""},
        	{"mode":"memberLogin","memberId":"13409226514","password":"ZXC123456","openId":""},
        	{"mode":"memberLogin","memberId":"18237333054","password":"ZXC123456","openId":""},
        	{"mode":"memberLogin","memberId":"13506712251","password":"ZXC123456","openId":""}
        	];
        for (var i=0;i<loginJsonArr.length;i++) {
        	var enen = encrypt(JSON.stringify(loginJsonArr[i]));
        	console.log(enen);
        }
        
        /**
        
        var loginJson = {"mode": "memberLogin", "memberId": "17656175477", "password": "z1310305", "openId": "" };
        var loginJsonEn = encrypt(JSON.stringify(loginJson)); //加密数据
       //var ss = decrypt(loginJsonEn);
        jsonObj.loginStr = loginJsonEn;

        if (name == null || name == "") {
            alert("用户名不能为空！")
        } else if (pwd == "" || pwd == null) {
            alert("密码不能为空！")
        } else {
            $.ajax({
                url: "/demo/add",
                data: JSON.stringify(jsonObj),
                type: "POST",
                contentType: "application/json; charset=utf-8",
                success: function (data, staus) {
                    alert(data);
                },
                error: function () {
                    alert("调用失败")
                }

            });
        }、
        */
    });

    //暂停
    $("#stopdemo-btn").click(function () {
        var name = $("#name").val();
        var pwd = $("#pwd").val();
        var jsonObj = new Object();
        jsonObj.username = name;
        jsonObj.password = pwd;
        if (name == null || name == "") {
            alert("用户名不能为空！")
        } else if (pwd == "" || pwd == null) {
            alert("密码不能为空！")
        } else {
            $.ajax({
                url: "/demo/cancel",
                data: JSON.stringify(jsonObj),
                type: "POST",
                contentType: "application/json; charset=utf-8",
                success: function (data, staus) {
                    alert(data);
                },
                error: function () {
                    alert("调用失败")
                }

            });
        }
    });

    //重新启动
    $("#restartdemo-btn").click(function () {
        var name = $("#name").val();
        var pwd = $("#pwd").val();
        var jsonObj = new Object();
        jsonObj.username = name;
        jsonObj.password = pwd;
        if (name == null || name == "") {
            alert("用户名不能为空！")
        } else if (pwd == "" || pwd == null) {
            alert("密码不能为空！")
        } else {
            $.ajax({
                url: "/demo/readd",
                data: JSON.stringify(jsonObj),
                type: "POST",
                contentType: "application/json; charset=utf-8",
                success: function (data, staus) {
                    alert(data);
                },
                error: function () {
                    alert("调用失败")
                }

            });
        }
    });

});


/**
 * 加密（依赖aes.js）
 * @param word 加密的字符串
 * @returns {*}
 */
function encrypt(word) {
  var key = CryptoJS.enc.Utf8.parse("bWFsbHB3ZA==WNST");
  var srcs = CryptoJS.enc.Utf8.parse(word);
  var encrypted = CryptoJS.AES.encrypt(srcs, key, {
    mode: CryptoJS.mode.ECB,
    padding: CryptoJS.pad.Pkcs7,
  });
  return encrypted.toString();
}


/**
 * 解密
 * @param word 解密的字符串
 * @returns {*}
 */
function decrypt(word) {
  //alert(word);
  var key = CryptoJS.enc.Utf8.parse("bWFsbHB3ZA==WNST");
  var decrypt = CryptoJS.AES.decrypt(word, key, {
    mode: CryptoJS.mode.ECB,
    padding: CryptoJS.pad.Pkcs7,
  });
  //alert(CryptoJS.enc.Utf8.stringify(decrypt).toString());
  return CryptoJS.enc.Utf8.stringify(decrypt).toString();
}

