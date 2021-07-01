
var Base = {
  serverAddress: "",
  // 判断域名地址
  checkhost: function () {
    // console.log(window.location);
    var location = window.location;
    if (
      location.hostname === "localhost" ||
      location.hostname === "127.0.0.1"
    ) {
      this.serverAddress = "http://172.21.126.164:8040";
    } else {
      this.serverAddress = "http://" + location.host;
    }
  },
  /*
        重置文档大小
    */
  resetContainerSize: function (id, min_width, min_height) {
    // var _win = window;
    // var _win_w = _win.innerWidth,
    //     _win_h = _win.innerHeight,
    //     min_w = min_width ? min_width : 1920,
    //     min_h = min_height ? min_height : 1080;
    // var bg = document.getElementById(id),
    //     container = document.getElementById('container');
    // if(bg){
    //     if(_win_w < min_w){
    //         bg.style.width = min_w + 'px';
    //     }
    //     // if(_win_h < min_h){
    //     //     bg.style.height = min_h + 'px';
    //     // }
    //     // if(container){
    //     //     console.log(bg.offsetHeight)
    //     //     container.style.paddingTop = bg.offsetHeight * 0.13 + 'px';
    //     // }
    // }
  },
  // 深拷贝对象
  // deepCopy: function(source) {
  //   var result = {};
  //   for (var key in source) {
  //     result[key] = typeof source[key] === 'object'
  //       ? deepCopy(source[key])
  //       : source[key];
  //   }
  //   return result;
  // },
  /*
        初始化下拉选择
        data-drop-value
        data-drop-obj
        changeSelect: 点击下拉框选择，方法接口，自定义
    */
  // initCommSelector: function() {
  //   $('ul.common-select').each(function() {
  //     var $ul = $(this),
  //       $container = $ul.parent('.btn-group'),
  //       $button = $ul.siblings('button.dropdown-toggle'),
  //       $input = $ul.siblings('input[type="hidden"]'),
  //       ifFlagmode = $ul.hasClass('selectmode');
  //     if ($container.length && $input.length) {
  //       var dropObj = new Object;
  //       dropObj.$ul = $ul;
  //       dropObj.$button = $button;
  //       dropObj.$input = $input;
  //       dropObj.changeSelect = function() {};
  //       $ul.children('li').each(function() {
  //         var $li = $(this),
  //           val_0 = $li.attr('data-drop-value');
  //         if ($li.hasClass('selected')) {
  //           $input.val(val_0);
  //           if (!ifFlagmode) {
  //             $button.text($li.text());
  //           }
  //         }
  //         $li.bind('click.initCommSelector', function() {
  //           if (!$li.hasClass('selected')) {
  //             $li.siblings('.selected').removeClass('selected');
  //             $li.addClass('selected');
  //             var val = $li.attr('data-drop-value'),
  //               text = $li.text();
  //             $input.val(val);
  //             if (!ifFlagmode) {
  //               $button.text(text);
  //             }
  //             dropObj.changeSelect(val, text, dropObj);
  //           }
  //         });
  //       });

  //       $ul.data('data-drop-obj', dropObj);
  //     }
  //   })
  // },
  // bindDropFnc: function($selector, fnc) {
  //   var $drop = $($selector);
  //   if ($drop.length && fnc) {
  //     var rankDrop = $drop.data('data-drop-obj');
  //     rankDrop.changeSelect = fnc;
  //   }
  // },
  /*
        是否DOM对象
    */
  isDomObject: function (obj) {
    var isDOM =
      typeof HTMLElement === "object"
        ? function (obj) {
            return obj instanceof HTMLElement;
          }
        : function (obj) {
            return (
              obj &&
              typeof obj === "object" &&
              obj.nodeType === 1 &&
              typeof obj.nodeName === "string"
            );
          };
    return isDOM;
  },
  /*
        是否jQuery对象
    */
  isJqueryObject: function (obj) {
    return obj instanceof jQuery;
  },
  /*
        获取jQuery对象
    */
  getJqueryObject: function (obj) {
    obj = typeof obj == "string" ? $(obj) : obj;
    if (this.isDomObject(obj)) {
      return $(obj);
    } else if (this.isJqueryObject(obj)) {
      return obj;
    }
    return null;
  },
  /*
        初始化自定义下拉选择
        data-btngrp-value
        data-btngrp-obj
        changeButton
    */
  initCommBtnGrp: function () {
    $(".common-dropdown").each(function () {
      // .button-group
      var $dropdown = $(this),
        $text = $dropdown.children("a.dropdown-text"),
        $dropmenu = $dropdown.children("ul.dropdown-menu"),
        $dropmenuItems = $dropmenu.children("li"),
        fieldName = $dropdown.data("fieldname") || "",
        defaultValue = $dropdown.data("defaultvalue") || "",
        $input,
        $defaultLi;
      if (!$dropmenuItems || !$dropmenuItems.length) {
        return false;
      }
      $dropdown.prepend('<input type="hidden" name="' + fieldName + '" />');
      $input = $dropdown.children('input[type="hidden"]');
      if (!$text || !$input) {
        return false;
      }
      $dropmenuItems.each(function () {
        var $li = $(this),
          value = $li.data("value");
        $li.bind("click.initCommBtnGrp", function () {
          var value = $li.data("value"),
            text = $li.children("span").text();
          if (value != undefined && text != undefined) {
            $text.text(text);
            $input.val(value);
          } else {
            console.log(value + "," + text);
          }
        });
        if (defaultValue && value === defaultValue) {
          $defaultLi = $li;
        }
      });
      if ($defaultLi && $defaultLi.length) {
        $defaultLi.click();
      }
    });
  },
  initCommBtnGrp2: function (id) {
    $(id + ".common-dropdown").each(function () {
      // .button-group
      var $dropdown = $(this),
        $text = $dropdown.children("a.dropdown-text"),
        $dropmenu = $dropdown.children("ul.dropdown-menu"),
        $dropmenuItems = $dropmenu.children("li"),
        fieldName = $dropdown.data("fieldname") || "",
        defaultValue = $dropdown.data("defaultvalue") || "",
        $input,
        $defaultLi;
      if (!$dropmenuItems || !$dropmenuItems.length) {
        return false;
      }

      console.log($dropdown.children('input[type="hidden"]').length);
      if ($dropdown.children('input[type="hidden"]').length === 0) {
        $dropdown.prepend('<input type="hidden" name="' + fieldName + '" />');
      }
      $input = $dropdown.children('input[type="hidden"]');
      if (!$text || !$input) {
        return false;
      }
      $dropmenuItems.each(function () {
        var $li = $(this),
          value = $li.data("value");
        $li.bind("click.initCommBtnGrp", function () {
          var value = $li.data("value"),
            text = $li.children("span").text();
          if (value != undefined && text != undefined) {
            $text.text(text);
            $input.val(value);
          } else {
            console.log(value + "," + text);
          }
        });
        if (defaultValue && value === defaultValue) {
          $defaultLi = $li;
        }
      });
      if ($defaultLi && $defaultLi.length) {
        $defaultLi.click();
      }
    });
  },

  // bindBtngrpFnc: function($selector, fnc) {
  //   var $group = $($selector);
  //   if ($group.length && fnc) {
  //     var rankGroup = $group.data('data-btngrp-obj');
  //     rankGroup.changeButton = fnc;
  //   }
  // },

  /*
        数据请求
    */
  query: function (url, callback, postdata) {
    if (url) {
      // var posturl = this.serverAddress + url;
      // console.log(posturl);
      $.ajax({
        type: "GET",
        url: url,
        data: postdata,
        dataType: "JSON", //jsonp
        success: function (data) {
          // console.log(data);
          if (callback) {
            callback(data);
          }
        },
        error: function (er) {
          console.log(er);
        },
      });
    } else {
      console.log("url error : " + url);
    }
  },
  queryPost: function (url, postdata, callback) {
	  debugger;
    // postdata
    if (url) {
      //var posturl = this.serverAddress + url;
      // console.log(posturl);
      postdata = encrypt(postdata); //加密数据
      url = "https://higo.flycua.com/ffp/member/login"
    	  // /ffp/member/login
      $.ajax({
        type: "POST",
        url: url,
        data: postdata,
        dataType: "JSON", //jsonp
        contentType: "application/json;charset=utf-8",
        success: function (data) {
          //console.log(11,data);
          var data1 = decrypt(data.errordesc); //解密数据
          //console.log(12,data1);
          var decryptedData = JSON.parse(data1); //json字符串转为对象 decryptedData
          //console.log("decryptedData",decryptedData);
          //  if(decryptedData.errorcode === "UnLoginError"){
          // 	window.location.href = 'login.html';
          //  }
          if (callback) {
            callback(decryptedData);
          }
        },
        error: function (er) {
          //alert(er.status);
          console.log(er);
          // if(er.status!="200"){
          //   alert('网络异常');
          // }
        },
      });
    } else {
      console.log("url error : " + url);
      alert("网络异常");
    }
  },
  getUrlData: function () {
    var location = window.location,
      argStr = location.search;
    argStr = argStr.replace(/%20/g, "");
    argStr = argStr.replace(/^\?/g, "");
    // console.log(location)
    var argStrArr = argStr.split("&"),
      argsObj = {};
    // console.log(argStrArr)
    for (i = 0; i < argStrArr.length; i++) {
      var arg = argStrArr[i];
      if (arg.indexOf("=") > -1) {
        var arr = arg.split("=");
        argsObj[arr[0]] = arr[1];
      }
    }
    //console.log(argsObj)
    return argsObj;
  },
  // 格式化日期
  dateDataReform: function (dateStr, splitStr) {
    if (dateStr && typeof dateStr === "string") {
      var spliter = splitStr || "-";
      if (dateStr.indexOf(spliter) > -1) {
        var re = new RegExp(spliter, "g");
        var res = dateStr.replace(re, "");
        return res;
      } else {
        if (dateStr.length === 8) {
          var year = dateStr.slice(0, 4),
            month = dateStr.slice(4, 6),
            date = dateStr.slice(6);
          return year + spliter + month + spliter + date;
        }
        if (dateStr.length === 6) {
          var year = dateStr.slice(0, 4),
            month = dateStr.slice(4);
          return year + spliter + month;
        } else {
          var year = dateStr.slice(0, 4),
            month = dateStr.slice(4, 6),
            date = dateStr.slice(6, 8),
            time = dateStr.slice(8);
          return year + spliter + month + spliter + date + time;
        }
        // return dateStr;
      }
    }
    return "";
  },
  // 深拷贝对象
  deepCopy: function (o) {
    //source
    // var result={};
    // for (var key in source) {
    //     result[key] = typeof source[key]==='object'? deepCoyp(source[key]): source[key];
    // }
    // return result;
    if (o instanceof Array) {
      var n = [];
      for (var i = 0; i < o.length; ++i) {
        n[i] = this.deepCopy(o[i]);
      }
      return n;
    } else if (o instanceof Object) {
      var n = {};
      for (var i in o) {
        n[i] = this.deepCopy(o[i]);
      }
      return n;
    } else {
      return o;
    }
  },
  tableDataReform: function (data) {
    if (!data && !(data === 0)) {
      return "--";
    }
    return data;
  },
  htmlDataReform: function (data) {
    if (!data && !(data === 0)) {
      return "";
    }
    return data;
  },
  tenMillionReform: function (arg) {
    // var arg = arguments;
    if (arg) {
      if (typeof arg === "number") {
        return (arg / 10000000).toFixed(2);
      } else if (arg instanceof Array && arg.length) {
        for (var i = 0; i < arg.length; i++) {
          arg[i] = (arg[i] / 10000000).toFixed(2);
        }
        return arg;
      } else {
        console.log("arguments error.");
        console.log(arg);
      }
    }
    return false;
  },
  thousandReform: function (arg) {
    // var arg = arguments;
    if (arg) {
      if (typeof arg === "number") {
        return (arg / 1000).toFixed(2);
      } else if (arg instanceof Array && arg.length) {
        for (var i = 0; i < arg.length; i++) {
          arg[i] = (arg[i] / 1000).toFixed(2);
        }
        return arg;
      } else {
        console.log("arguments error.");
        console.log(arg);
      }
    }
    return false;
  },
  rateReform: function (data, fixnum) {
    if (!data && !(data === 0)) {
      return "";
    }
    var n = fixnum ? (data * 100).toFixed(fixnum) : Math.round(data * 100);
    return n + "%";
  },
  /*
        ifDelCol: 是否删除列，true: 删除列, false: 删除行
    */
  coordDataReform: function (data, ifDelCol) {
    if (
      data &&
      typeof data == "object" &&
      data instanceof Array &&
      data.length
    ) {
      var isDelCol = ifDelCol || false,
        result = {},
        del = [];
      for (var i = 0; i < data.length; i++) {
        if (data[i] && typeof data[i] == "object") {
          // && data[i][xAxis]
          // result.xAxis.push(data[i][xAxis]);
          for (var prop in data[i]) {
            if (!result[prop]) {
              result[prop] = [];
            }
            result[prop].push(data[i][prop]);

            if (!data[i][prop] && data[i][prop] != 0) {
              if (isDelCol) {
                del.push(i);
              } else {
                del.push(prop);
              }
            }
          }
        } else {
          console.log("data error : " + data[i]);
        }
      }
      // console.log(del)
      if (del.length) {
        for (var j = del.length - 1; j > -1; j--) {
          if (isDelCol) {
            for (var p in result) {
              result[p].splice([del[j]], 1);
            }
          } else {
            // if(result[del[j]]){
            result[del[j]] = [];
            // }
          }
        }
      }
      // console.log(result);
      return result;
    } else {
      console.log("data error : " + data);
      return false;
    }
  },

  objToArrayReform: function (data) {
    if (data && typeof data == "object") {
      var result = [];
      for (var p in data) {
        if (data[p] && data[p].length) {
          for (var i = 0; i < data[p].length; i++) {
            if (!result[i]) {
              result[i] = {};
            }
            result[i][p] = data[p][i];
          }
        } else {
          console.log(p + " : " + data[p]);
        }
      }
      if (result.length) {
        return result;
      }
      return false;
    } else {
      console.log("参数不正确");
      return false;
    }
  },
  stringToArray: function (s, ifGetEmpty) {
    if (s) {
      if (typeof s === "string") {
        var arr = s.split(","),
          fields = [];
        for (var i = 0; i < arr.length; i++) {
          if (arr[i] || ifGetEmpty) {
            var f;
            if (arr[i]) {
              f = arr[i].replace(/\s/g, "");
            }
            if (f) {
              fields.push(f);
            } else {
              fields.push("");
            }
          }
        }
        return fields;
      } else if (s instanceof Array) {
        return s;
      }
    }
    return [];
  },
  /*****
        num : string/number
        color : 'blue','org'
        field : 'l','m'
    *****/
  numberToGraph: function (num, clr, sz) {
    if (num || num === 0) {
      var s = num, // console.log(s);
        color = clr === "blue" || clr === "org" ? clr : "blue",
        size = sz === "l" || sz === "m" ? sz : "m";
      if (typeof s === "number") {
        s = s + "";
      }
      if (typeof s === "string") {
        // var n = parseInt(s);
        // if(typeof n != 'number'){
        //     return false;
        // }
        if (s.length) {
          var grhStr = "";
          for (var i = 0; i < s.length; i++) {
            var sn = s[i];
            if (sn === ".") {
              sn = "dot";
            } else if (sn === "%") {
              sn = "pct";
            } else if (sn === ",") {
              sn = "comma";
            } else if (sn === "-") {
              sn = "minus";
            }
            grhStr +=
              '<i class="i-n i-n-' +
              size +
              " i-n-" +
              color +
              "-" +
              sn +
              '"></i>';
          }
        }
        return grhStr;
      }
    }
    return false;
  },
  getHTMLObj: function (id) {
    if (id && typeof id === "string") {
      var obj = document.getElementById(id);
      if (obj) {
        return obj;
      }
    }
    return false;
  },
  /*****
        data : array
        flds : array
        width : array/string
        tdContentFnc : function(i, j, data[i][flds[j]]) / function(i, data[i])
    *****/
  getTableLinesString: function (data, flds, width, tdContentFnc) {
    if (
      data &&
      flds &&
      data instanceof Array &&
      flds instanceof Array &&
      data.length
    ) {
      // && flds.length
      var trs = "",
        widthArr = _base.stringToArray(width, true);

      for (var i = 0; i < data.length; i++) {
        trs += "<tr>";

        if (flds.length) {
          for (var j = 0; j < flds.length; j++) {
            var td = "",
              w = "";
            if (tdContentFnc) {
              td = tdContentFnc(i, j, data[i][flds[j]]);
            } else {
              td =
                data[i][flds[j]] || data[i][flds[j]] === 0
                  ? data[i][flds[j]]
                  : "";
            }
            if (i === 0 && widthArr.length) {
              w = widthArr[j] ? ' width="' + widthArr[j] + '"' : "";
            }
            trs += "<td " + w + ">" + (td || td === 0 ? td : "") + "</td>";
          }
        } else {
          var tds = "";
          if (tdContentFnc) {
            tds = tdContentFnc(i, data[i]);
          } else {
            if (typeof data[i] === "string") {
              tds = data[i];
            } else if (typeof data[i] === "number") {
              tds = data[i] + "";
            } else {
              console.log("数据类型错误: " + i);
            }
          }
          // console.log(tds)
          trs += tds;
        }

        trs += "</tr>";
      }
      return trs;
    } else {
      console.log("参数不正确");
    }
    return false;
  },
  /*****
        id : string/obj
        data : array
        field : string/array
    *****/
  initTableLine: function (id, data, fields, width, tdContentFnc) {
    var _base = this;
    if (id && data) {
      // && fields
      var tableLineCon = _base.getHTMLObj(id),
        flds = _base.stringToArray(fields);
      if (tableLineCon && data instanceof Array && data.length) {
        // && flds && flds.length && typeof tableLineCon == 'object'
        var trs = _base.getTableLinesString(data, flds, width, tdContentFnc);
        if (tbody) {
          var tbody = "<tbody>" + trs + "</tbody>";
          tableLineCon.innerHTML = tbody;
        }
      } else {
        console.log("参数不正确");
      }
    } else {
      console.log("参数不正确");
    }
  },
  /*****
        opt{
            id : string
            classname : string
            style : string
            data : array
            fields : string/array
            thead : string/arrar
            width : string/arrar
            con : string/obj
            ifGetTblStr : boolean,
            tdContentFnc : function
        }
    *****/
  initTable: function (opt) {
    var _base = this,
      id = opt.id ? ' id="' + opt.id + '" ' : "",
      classname = opt.classname ? ' class="' + opt.classname + '" ' : "",
      style = opt.style ? ' style="' + opt.style + '" ' : "",
      data = opt.data,
      fields = _base.stringToArray(opt.fields),
      ths = _base.stringToArray(opt.thead, true),
      width = _base.stringToArray(opt.width, true),
      con = _base.getHTMLObj(opt.con),
      ifGetTblStr = opt.ifGetTblStr === true ? true : false,
      tdContentFnc = opt.tdContentFnc ? opt.tdContentFnc : false;

    if ((con || ifGetTblStr) && data && data instanceof Array && data.length) {
      // && fields && fields instanceof Array && fields.length
      var trs = _base.getTableLinesString(data, fields, width, tdContentFnc),
        thead = "",
        tbody = "",
        tableHTML = "";
      if (trs) {
        // console.log(trs)
        tbody = "<tbody>" + trs + "</tbody>";
      }
      if (ths.length) {
        var thTR = "",
          w = "";
        for (var i = 0; i < ths.length; i++) {
          w = width.length ? (width[i] ? ' width="' + width[i] + '"' : "") : "";
          thTR += "<th " + w + ">" + (ths[i] ? ths[i] : "") + "</th>";
        }
        thead = "<thead><tr>" + thTR + "</tr></thead>";
      }
      if (tbody) {
        //thead &&
        tableHTML =
          "<table " + id + classname + style + ">" + thead + tbody + "</table>";
      }
      if (tableHTML) {
        if (con) {
          // && typeof con === 'object'
          con.innerHTML = tableHTML;
        }
        if (ifGetTblStr) {
          return tableHTML;
        }
      }
    } else {
      console.log("参数不正确");
    }
  },
  /*
        data: array
        liContentFnc: function
    */
  getListLines: function (data, liContentFnc) {
    if (data && data instanceof Array && data.length) {
      var lis = "";
      for (var i = 0; i < data.length; i++) {
        var liContent = "";
        if (liContentFnc) {
          liContent = liContentFnc(i, data[i]);
        } else {
          if (data[i]) {
            if (typeof data[i] === "string") {
              liContent = data[i];
            } else if (typeof data[i] === "number") {
              liContent = data[i] + "";
            } else {
              console.log("数据类型错误: " + i);
            }
          }
        }
        lis += "<li>" + liContent + "</li>";
      }
      return lis;
    }
    console.log("参数不正确");
    return "";
  },

  /*****
        opt{
            id : string
            classname : string
            style : string
            data : array
            // fields : string/array
            con : string/obj
            ifGetListStr : boolean,
            liContentFnc : function
        }
    *****/
  initList: function (opt) {
    var _base = this,
      id = opt.id ? ' id="' + opt.id + '" ' : "",
      classname = opt.classname ? ' class="' + opt.classname + '" ' : "",
      style = opt.style ? ' style="' + opt.style + '" ' : "",
      data = opt.data,
      // fields = _base.stringToArray(opt.fields),
      con = _base.getHTMLObj(opt.con),
      ifGetListStr = opt.ifGetListStr === true ? true : false,
      liContentFnc = opt.liContentFnc ? opt.liContentFnc : false;

    if ((con || ifGetListStr) && data && data instanceof Array && data.length) {
      var linesString = _base.getListLines(data, liContentFnc);
      if (linesString) {
        if (con) {
          con.innerHTML = linesString;
        }
        if (ifGetListStr) {
          return linesString;
        }
      } else {
        console.log("参数不正确");
      }
    } else {
      console.log("参数不正确");
    }
  },
  /*
        模态框
        options: {
            title
            content
            hasFoot
            submitButtonText
            cancelButtonText
        }
    */
  Modal: function (options) {
    function modalObj() {}

    if (typeof modalObj.flag == "undefined") {
      modalObj.prototype.show = function () {
        if (this.$modal) {
          this.$modal.modal("show");
        }
      };
      modalObj.prototype.hide = function () {
        if (this.$modal) {
          this.$modal.modal("hide");
        }
      };

      modalObj.flag = true; //第一次定义完之后，之后的对象就不需要再进来这块代码了
    }

    function init(options) {
      var title = options.title,
        hasFoot = options.hasFoot === false ? false : true,
        content = options.content || "";

      var stitle = title
          ? '<div class="modal-header">' +
            '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>' +
            '<h4 class="modal-title" id="gridSystemModalLabel">' +
            title +
            "</h4>" +
            "</div>"
          : "",
        sFoot = hasFoot
          ? '<div class="modal-footer">' +
            '<button type="button" class="btn btn-default" data-dismiss="modal">' +
            (options.cancelButtonText || "取消") +
            "</button>" +
            '<button type="button" class="btn btn-primary">' +
            (options.submitButtonText || "保存") +
            "</button>" +
            "</div>"
          : "",
        sModal =
          '<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">' +
          '<div class="modal-dialog modal-lg" role="document">' +
          '<div class="modal-content">' +
          stitle +
          content +
          sFoot +
          "</div>" +
          "</div>" +
          "</div>";

      var modal = new modalObj();
      modal["$modal"] = $(sModal);

      return modal;
    }

    var modal = init(options);
    return modal;
  },
  // 显示提示
  showAlert: function (msg, delayTime) {
    var _this = this,
      alertModal = _this.Modal({
        content: '<div class="alert-modal-msg">' + msg + "</div>",
        hasFoot: false,
      });
    alertModal.show();
    window.setTimeout(function () {
      alertModal.hide();
    }, delayTime || 3000);
  },

  // 显示提示
  //className:父级类明
  NewAlert: function (msg, className, delayTime) {
    $('.alert-new-msg').remove();
    $(className).append('<div class="alert-new-msg">' + msg + "</div>");
    $(".alert-new-msg").show();
    window.setTimeout(function () {
      $(".alert-new-msg").hide();
    }, delayTime || 3000);
  },
  /*
        定义验证
        fields:         验证的字段及验证方式
        formSelector:   表单选择器, 默认 'form'
        options:        其他参数设置
    */
  initFormValidate: function (fields, formSelector, options) {
    if (fields) {
      formSelector = formSelector || "form";
      var $form = this.getJqueryObject(formSelector);
      if ($form && $form.length) {
        var validateOption = {
          message: "This value is not valid",
          feedbackIcons: {
            valid: "glyphicon glyphicon-ok",
            invalid: "glyphicon glyphicon-remove",
            validating: "glyphicon glyphicon-refresh",
          },
        };
        if (options) {
          $.extend(validateOption, options);
        }
        validateOption["fields"] = fields;
        $form.bootstrapValidator(validateOption);
      } else {
        console.log("表单未定义!");
      }
    } else {
      console.log("验证字段未定义!");
    }
  },
  /*
        初始化
    */
  init: function () {
    this.checkhost();
    // this.initCommSelector();
    //this.initCommBtnGrp();
  },

  // extend : function (){
  //     var args = arguments;
  //     if(args.length && args.length > 1){
  //         for(var i = 1; i < args.length; i++){
  //             for (var prop in args[i]){
  //                 args[i - 1][prop] = args[i][prop];
  //             }
  //         }
  //     }
  // },
};

Base.init();

$.fn.extend({
  loadpage: function (options) {
    var defaults = {
      url: "",
      argu: null,
      // chartsOption: null,
      callback: null,
    };
    var opts = $.extend(defaults, options);
    var _this = this;
    var $this = $(this);

    if (opts.url && $this.length) {
      $.ajaxSetup({
        cache: false, //close AJAX cache
      });
      $this.load(opts.url, function () {
        // Base.initCommSelector();

        if (opts.callback) {
          opts.callback();
        }
      });
    }
  },
});

/*
    自定义表单验证规则
*/
$.extend($.fn.bootstrapValidator.validators, {
  // 手机号
  mobile: {
    validate: function (validator, $field, options) {
      var reg = new RegExp(
          /^((11[0-9])|(12[0-9])|(13[0-9])|(14[0-9])|(15([0-9])|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\d{8}$)/
        ),
        val = $field.val();
      return reg.test(val) || val === "";
    },
  },
  // 邮箱
  email: {
    validate: function (validator, $field, options) {
      var reg = new RegExp(
          /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/
        ),
        val = $field.val();
      return reg.test(val) || val === "";
    },
  },
  // 密码
  password: {
    validate: function (validator, $field, options) {
      var reg = new RegExp(/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$/),
        val = $field.val();
      return reg.test(val) || val === "";
    },
  },
  // 验证姓，名类型和长度
  nameType: {
    validate: function (validator, $field, options) {
      var reg = new RegExp(/^[\u4e00-\u9fa5]{1,10}$|^[a-zA-Z]{1,30}$/),
        val = $field.val();
      return reg.test(val) || val === "";
    },
  },
  // 验证拼音姓，名类型和长度
  nameEnType: {
    validate: function (validator, $field, options) {
      var reg = new RegExp(/^[a-zA-Z]{1,30}$/),
        val = $field.val();
      return reg.test(val) || val === "";
    },
  },
  // 验证邮编格式
  zipCode: {
    validate: function (validator, $field, options) {
      var reg = new RegExp(/^[0-9][0-9]{5}$/),
        val = $field.val();
      return reg.test(val) || val === "";
    },
  },
  // 验证日期格式
  dateType: {
    validate: function (validator, $field, options) {
      var reg = new RegExp(/^\d{4}-\d{2}-\d{2}$/),
        val = $field.val();
      return reg.test(val) || val === "";
    },
  },
  // 验证姓名
  contactName: {
    validate: function (validator, $field, options) {
      var reg = new RegExp(/^[\u4e00-\u9fa5]{1,20}$|^[a-zA-Z]{1,20}$/),
        val = $field.val();
      return reg.test(val) || val === "";
    },
  },
  // 验证姓，名长度
  nameLength: {
    validate: function (validator, $field, options) {
      var val = $field.val(),
        allFields = validator._cacheFields,
        relatedFields = Base.stringToArray(options["relatedFields"]),
        invalidFields = validator.$invalidFields;

      function getStrLeng(str) {
        var realLength = 0;
        var len = str.length;
        var charCode = -1;
        for (var i = 0; i < len; i++) {
          charCode = str.charCodeAt(i);
          if (charCode >= 0 && charCode <= 128) {
            realLength += 1;
          } else {
            // 如果是中文则长度加3
            realLength += 3;
          }
        }
        return realLength;
      }
      var isValid = true;
      if (val) {
        if (getStrLeng(val) > 30) {
          for (var i = 0; i < relatedFields.length; i++) {
            var field = relatedFields[i];
            if (!field) {
              continue;
            }
            validator.updateStatus(field, "INVALID", "nameLength"); //验证失败
          }
          isValid = false;
        }
      }
      return isValid;
    },
  },
  // n个字段中有一个必填
  oneReqired: {
    validate: function (validator, $field, options) {
      var val = $field.val(),
        allFields = validator._cacheFields,
        relatedFields = Base.stringToArray(options["relatedFields"]),
        invalidFields = validator.$invalidFields;
      for (var i = 0; i < relatedFields.length; i++) {
        var field = relatedFields[i];
        if (!field) {
          continue;
        }
        var $fieldObj = allFields[field];
        if ($fieldObj && $fieldObj.length) {
          var relatedVal = $fieldObj.val();
          if (relatedVal) {
            if (!val) {
              validator.updateStatus(field, "VALID", "oneReqired");
              val = relatedVal;
              break;
            }
          } else {
            if (val) {
              validator.updateStatus(field, "NOT_VALIDED", "oneReqired");
            } else {
              validator.updateStatus(field, "INVALID", "oneReqired");
            }
          }
        }
      }
      return val ? true : false;
    },
  },
  // 是否都为中文 或 都为英文
  isSameType: {
    validate: function (validator, $field, options) {
      var isValid = true;
      var val = $field.val(),
        allFields = validator._cacheFields,
        relatedFields = Base.stringToArray(options["relatedFields"]),
        invalidFields = validator.$invalidFields;
      console.log(relatedFields);
      for (var i = 0; i < relatedFields.length; i++) {
        var field = relatedFields[i];
        if (!field) {
          continue;
        }

        var $fieldObj = allFields[field];
        if ($fieldObj && $fieldObj.length) {
          var relatedVal = $fieldObj.val();
          console.log(val); // 光标聚焦的值
          console.log(relatedVal); // 光标位聚焦的值
          var reg = new RegExp(/^[A-z]+$|^[\u4E00-\u9FA5]+$/);
          var formValues = val + relatedVal;
          if (!reg.test(formValues)) {
            validator.updateStatus(field, "INVALID", "isSameType"); //验证失败
            isValid = false;
          } else {
            validator.updateStatus(field, "VALID", "isSameType"); //验证通过
            isValid = true;
          }
        }
      }
      return isValid;
    },
  },
  // 验证身份证
  identityCodeValid: {
    validate: function (validator, $field, options) {
      //$field.toUpperCase();
      $field.val($field.val().toUpperCase());
      var val = $field.val(),
        allFields = validator._cacheFields,
        relatedFields = Base.stringToArray(options["relatedFields"]),
        invalidFields = validator.$invalidFields;
      console.log($field);

      function identityCodeValid(code) {
        var city = {
          11: "北京",
          12: "天津",
          13: "河北",
          14: "山西",
          15: "内蒙古",
          21: "辽宁",
          22: "吉林",
          23: "黑龙江 ",
          31: "上海",
          32: "江苏",
          33: "浙江",
          34: "安徽",
          35: "福建",
          36: "江西",
          37: "山东",
          41: "河南",
          42: "湖北 ",
          43: "湖南",
          44: "广东",
          45: "广西",
          46: "海南",
          50: "重庆",
          51: "四川",
          52: "贵州",
          53: "云南",
          54: "西藏 ",
          61: "陕西",
          62: "甘肃",
          63: "青海",
          64: "宁夏",
          65: "新疆",
          71: "台湾",
          81: "香港",
          82: "澳门",
          91: "国外 ",
        };
        if (code.length !== 18) {
          return "error!长度错误";
        }
        if (
          !code ||
          !/^\d{6}(18|19|20)\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2]\d|3[0-1])\d{3}(\d|[xX])$/i.test(
            code
          )
        ) {
          return "error!身份证号格式错误"; //\d|[xX] \d|X
        }
        if (!city[code.substr(0, 2)]) {
          return "error!地址编码错误";
        }
        // 18位身份证需要验证最后一位校验位
        var codes = code.split("");
        // 加权因子
        var factor = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
        // 校验位
        var parity = [1, 0, "X", 9, 8, 7, 6, 5, 4, 3, 2];
        var sum = 0;
        var ai = 0;
        var wi = 0;
        for (var i = 0; i < 17; i++) {
          ai = codes[i];
          wi = factor[i];
          sum += ai * wi;
        }
        var last = parity[sum % 11];
        if (parity[sum % 11] != codes[17].toUpperCase()) {
          return "error!校验位错误";
        }
        return code;
      }
      var isValid = true;
      for (var i = 0; i < relatedFields.length; i++) {
        var field = relatedFields[i];
      }
      if (val) {
        var code = identityCodeValid(val);
        if (code !== val) {
          validator.updateStatus(relatedFields[0], "INVALID", "idno"); //验证失败
          isValid = false;
        }
      }
      return isValid;
    },
  },
  // 外国人永久居住证
  foreignersResidencePermitValid: {
    validate: function (validator, $field, options) {
      var val = $field.val(),
        allFields = validator._cacheFields,
        relatedFields = Base.stringToArray(options["relatedFields"]),
        invalidFields = validator.$invalidFields;
      var isValid = true;
      var reg = new RegExp(/^[a-zA-Z0-9]+$/);

      if (val) {
        var code = reg.test(val);
        if (!code) {
          validator.updateStatus(relatedFields[0], "INVALID", "idno"); //验证失败
          isValid = false;
        }
      }
      return isValid;
    },
  },
});
//退出
function loginOut() {
  var postData = {
    mode: "memberLogout",
  };
  Base.queryPost(
    "/ffp/member/login",
    JSON.stringify(postData),
    function (data) {
      if (data) {
        if (data.errorcode === "0000") {
          sessionStorage.setItem("user_memberId", "");
          window.location.href = "login.html";
        } else {
          Base.showAlert(data.errordesc);
        }
      }
    }
  );
}
var countdown_one = 60,
  countdown_two = 60,
  countdown_thr = 60,
  countdown_four = 60;

function settime(obj, countdown) {
  if (countdown == 0) {
    obj.attr("disabled", false);
    obj.removeClass("getYzmbgcolor");
    //obj.removeattr("disabled");
    obj.text("发送动态密码");
    countdown = 60;
    return;
  } else {
    obj.attr("disabled", true);
    obj.addClass("getYzmbgcolor");
    obj.text("重新发送(" + countdown + ")");
    countdown--;
  }
  setTimeout(function () {
    settime(obj, countdown);
  }, 1000);
}

$(".mobile_icon").hover(
  function () {
    $("#mobile_img").show();
  },
  function () {
    $("#mobile_img").hide();
  }
);
$(".weixin_icon").hover(
  function () {
    $("#weixin_img").show();
  },
  function () {
    $("#weixin_img").hide();
  }
);

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

//isDuringDate 比较当前时间是否在指定时间段内
function isDuringDate(beginDateStr, endDateStr) {
  var curDate = new Date(),
    beginDate = new Date(beginDateStr),
    endDate = new Date(endDateStr);
  if (curDate >= beginDate && curDate <= endDate) {
    return true;
  }
  return false;
}

//根据身份证获取年龄
function discriCard(paramAge) {
  //获取输入身份证号码
  var UUserCard = paramAge;
  //var UUserCard = "320322201005157314";
  //获取出生日期
  UUserCard.substring(6, 10) +
    "-" +
    UUserCard.substring(10, 12) +
    "-" +
    UUserCard.substring(12, 14);
  //获取年龄
  var myDate = new Date();
  var month = myDate.getMonth() + 1;
  var day = myDate.getDate();
  var age = myDate.getFullYear() - UUserCard.substring(6, 10) - 1;
  if (
    UUserCard.substring(10, 12) < month ||
    (UUserCard.substring(10, 12) == month && UUserCard.substring(12, 14) <= day)
  ) {
    age++;
  }
  return age;
  //年龄 age
}
