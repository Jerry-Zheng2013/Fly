$(document).ready(function () {

    //加载表格数据
    $.get("/flight/all", function (data) {
        show(data);
    });

    $("#queryFlight").click(function () {
        var jsonObj = new Object();
        jsonObj.startCity = $("#queryStart").val();
        jsonObj.endCity = $("#queryEnd").val();
        $.ajax({
            url: "flight/city",
            contentType: "application/json;charset=utf-8",
            type: "POST",
            data: JSON.stringify(jsonObj),
            success: function (data) {
                $("#tbody-flight").empty();
                show(data);
            }

        })
    })
});

Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, // 月份
        "d+": this.getDate(), // 日
        "h+": this.getHours(), // 小时
        "m+": this.getMinutes(), // 分
        "s+": this.getSeconds(), // 秒
        "q+": Math.floor((this.getMonth() + 3) / 3), // 季度
        "S": this.getMilliseconds() // 毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

function show(data) {
    for (var i = 0; i < data.length; i++) {
        var tr = document.createElement('tr');
        //         "flightId": 1,
        //         "startTime": "2018-06-02T13:52:00.000+0000",
        //         "startCity": "北京",
        //         "endCity": "上海",
        //         "peopleNumber": 100,
        //         "leftTicket": 50,
        //         "ticketPrice": 1000
        var startTTd = document.createElement('td');
        startTTd.append(new Date(data[i].startTime).format("yyyy-MM-dd hh:mm:ss"));
        //new Date(data[i].startTime).format("yyyy-MM-dd hh:mm:ss");
        var startCTd = document.createElement('td');
        startCTd.append(data[i].startCity);
        var endCTd = document.createElement('td');
        endCTd.append(data[i].endCity);
        var leftTicketTd = document.createElement('td');
        leftTicketTd.append(data[i].leftTicket);
        var ticketPriceTd = document.createElement('td');
        ticketPriceTd.append(data[i].ticketPrice);
        tr.appendChild(startCTd);
        tr.appendChild(endCTd);
        tr.appendChild(startTTd);
        tr.appendChild(leftTicketTd);
        tr.appendChild(ticketPriceTd);
        var operateTd = document.createElement('td');
        var addA = document.createElement('a');
        addA.innerText = "订票";
        addA.href = "order.html?flightId=" + data[i].flightId;
        operateTd.appendChild(addA);
        tr.appendChild(operateTd);
        $("#tbody-flight").append(tr);
    }
}