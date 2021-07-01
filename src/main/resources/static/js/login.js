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

    //启动
    $("#startdemo-btn").click(function () {
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
