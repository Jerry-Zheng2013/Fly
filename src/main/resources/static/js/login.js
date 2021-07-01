
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
        
        var loginJson = { "mode": "memberLogin", "memberId": "17656175477", "password": "z1310305", "openId": "" };

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
        }
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

