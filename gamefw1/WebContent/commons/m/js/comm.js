String.prototype.trim = function () { return this.replace(/(^[\\s]*)|([\\s]*$)/g, "") }; String.prototype.lTrim = function () { return this.replace(/(^[\\s]*)/g, "") }; String.prototype.rTrim = function () { return this.replace(/([\\s]*$)/g, "") }; Array.prototype.remove = function (c) { for (var d = 0x0; d < this.length; d++) { if (this[d] == c) { this.splice(d, 0x1); break } } }; function checkIsNotEmpty(c) { if (c.trim() == "") return false; else return true }; function checkIsInteger(c) { if (c == "") return true; var d = parseInt(c); return !isNaN(d) }; function checkIntegerMinValue(c, d) { if (c == "") return true; if (typeof (d) != "\x73\x74\x72\x69\x6e\x67") d = d + ""; if (checkIsInteger(c) == true) { if (parseInt(c, 0xa) >= parseInt(d, 0xa)) return true; else return false } else return false }; function checkIntegerMaxValue(c, d) { if (c == "") return true; if (typeof (d) != "\x73\x74\x72\x69\x6e\x67") d = d + ""; if (checkIsInteger(c) == true) { if (parseInt(c, 0xa) <= parseInt(d, 0xa)) return true; else return false } else return false }; function isNotNegativeInteger(c) { if (c == "") return true; if (checkIsInteger(c) == true) { if (parseInt(c, 0xa) < 0x0) return false; else return true } else return false }; function checkIsDouble(c) { if (c == "") return true; var d = parseFloat(c); return !isNaN(d) }; function checkDoubleMinValue(c, d) { if (c == "") return true; if (typeof (d) != "\x73\x74\x72\x69\x6e\x67") d = d + ""; if (checkIsDouble(c) == true) { if (parseFloat(c) >= parseFloat(d)) return true; else return false } else return false }; function checkDoubleMaxValue(c, d) { if (c == "") return true; if (typeof (d) != "\x73\x74\x72\x69\x6e\x67") d = d + ""; if (checkIsDouble(c) == true) { if (parseFloat(c) <= parseFloat(d)) return true; else return false } else return false }; function isNotNegativeDouble(c) { if (c == "") return true; if (checkIsDouble(c) == true) { if (parseFloat(c) < 0x0) return false; else return true } else return false }; function checkIsValidDate(c) { if (c == "") return true; var d = /^((\\d{4})|(\\d{2}))-(\\d{1,2})-(\\d{1,2})$/g; if (!d.test(c)) return false; var e = c.split("\x2d"); if (parseInt(e[0x0], 0xa) < 0x64) e[0x0] = 0x7d0 + parseInt(e[0x0], 0xa) + ""; var f = new Date(e[0x0], (parseInt(e[0x1], 0xa) - 0x1) + "", e[0x2]); if (f.getYear() == e[0x0] && f.getMonth() == (parseInt(e[0x1], 0xa) - 0x1) + "" && f.getDate() == e[0x2]) return true; else return false }; function checkDateEarlier(c, d) { if (checkIsValidDate(c) == false || checkIsValidDate(d) == false) return false; if ((c == "") || (d == "")) return true; var e = c.split("\x2d"); var f = d.split("\x2d"); var g = new Date(e[0x0], parseInt(e[0x1].replace(/^0/, ""), 0xa) - 0x1, e[0x2]); var h = new Date(f[0x0], parseInt(f[0x1].replace(/^0/, ""), 0xa) - 0x1, f[0x2]); if (e[0x1].length == 0x1) e[0x1] = "\x30" + e[0x1]; if (e[0x2].length == 0x1) e[0x2] = "\x30" + e[0x2]; if (f[0x1].length == 0x1) f[0x1] = "\x30" + f[0x1]; if (f[0x2].length == 0x1) f[0x2] = "\x30" + f[0x2]; var i = e[0x0] + e[0x1] + e[0x2]; var j = f[0x0] + f[0x1] + f[0x2]; if (parseInt(i, 0xa) > parseInt(j, 0xa)) return false; else return true }; function checkEmail(c) { if (c == "") return true; if (c.charAt(0x0) == "\x2e" || c.charAt(0x0) == "\x40" || c.indexOf("\x40", 0x0) == -0x1 || c.indexOf("\x2e", 0x0) == -0x1 || c.lastIndexOf("\x40") == c.length - 0x1 || c.lastIndexOf("\x2e") == c.length - 0x1) return false; else return true }; function checkIsChinese(c) { if (c == "") return true; var d = /^([\\u4E00-\\u9FA5]|[\\uFE30-\\uFFA0])*$/gi; if (d.test(c)) return true; else return false }; String.prototype.realLength = function () { return this.replace(/[^\\x00-\\xff]/g, "\x2a\x2a").length }; function checkMask(c, d) { if (c == "") return true; var e = new RegExp(d, "\x67\x69"); if (e.test(c)) return true; else return false }; function getFilePostfix(c) { if (c == null) return null; var d = /(.*)\\.(.*)$/gi; if (typeof (c) == "\x6f\x62\x6a\x65\x63\x74") { if (c.value == null || c.value == "") return null; var e = d.exec(c.value); return RegExp.$2 } else if (typeof (c) == "\x73\x74\x72\x69\x6e\x67") { var e = d.exec(c); return RegExp.$2 } else return null }; function fmoney(c, d, e) { s = c; if (s == null || s == "") s = 0x0; d = d > 0x0 && d <= 0x14 ? d : 0x2; s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(d) + ""; var f = s.split("\x2e")[0x0].split("").reverse(), h = s.split("\x2e")[0x1]; t = ""; for (i = 0x0; i < f.length; i++) { if (e != null) { t += f[i] + ((i + 0x1) % 0x3 == 0x0 && (i + 0x1) != f.length ? "\x2c" : "") } else { t += f[i] + ((i + 0x1) % 0x64 == 0x0 && (i + 0x1) != f.length ? "\x2c" : "") } }; return t.split("").reverse().join("") + "\x2e" + h }; String.prototype.format = function (c) { var d = this; if (arguments.length > 0x0) { if (arguments.length == 0x1 && typeof (c) == "\x6f\x62\x6a\x65\x63\x74") { for (var e in c) { if (c[e] != undefined) { var f = new RegExp("\x28\x7b" + e + "\x7d\x29", "\x67"); d = d.replace(f, c[e]) } } } else { for (var e = 0x0; e < arguments.length; e++) { if (arguments[e] != undefined) { var f = new RegExp("\x28\x7b\x5b" + e + "\x5d\x7d\x29", "\x67"); d = d.replace(f, arguments[e]) } } } }; return d }; function PatchThree(c) { var d = c; if (d.toString().length == 0x1) d = "\x30\x30" + d.toString(); if (d.toString().length == 0x2) d = "\x30" + d.toString(); return d }; function CurentTime() { var c = new Date(); var d = c.getFullYear(); var e = c.getMonth() + 0x1; var f = c.getDate(); var g = c.getHours(); var h = c.getMinutes(); var i = d + "\x2d"; if (e < 0xa) i += "\x30"; i += e + "\x2d"; if (f < 0xa) i += "\x30"; i += f + "\x20"; if (g < 0xa) i += "\x30"; i += g + "\x3a"; if (h < 0xa) i += '\x30'; i += h; return (i) }; function CurentDate(c) { var d = new Date(); if (c != null) { var e = new Date(d.getFullYear(), d.getMonth(), d.getDate()); e.setDate(e.getDate() + c); return FormatDate(e) }; return FormatDate(d) }; function FormatDate(c) { var d = c.getFullYear(); var e = c.getMonth() + 0x1; var f = c.getDate(); var g = d + "\x2d"; if (e < 0xa) g += "\x30"; g += e + "\x2d"; if (f < 0xa) g += "\x30"; g += f + ""; return (g) }; function getSunday() { var c = new Date(); var d = c.getDay(); c.setDate(c.getDate() + (0x6 - d)); return c }; function getMonday() { var c; var d = new Date(); var e = d.getDay(); d.setDate(d.getDate() - e); return d }; function getMonthDays(c) { var d = new Date(nowYear, c, 0x1); var e = new Date(nowYear, c + 0x1, 0x1); var f = (e - d) / (0x3e8 * 0x3c * 0x3c * 0x18); return f }; function tryGetInt(c) { if (c == "") return 0x0; return parseInt(c) }; function tryGetFloat(c) { if (c == "") return 0.0; return parseFloat(c) }; function checkGetIsDouble(c) { if (!checkIsDouble($(c).val())) { alert($(c).val() + "\u4e0d\u662f\u5408\u6cd5\u6570\u5b57"); $(c).focus(); return 0x0 } else return tryGetFloat($(c).val()) }; function setSelectdOption(c, d) { var e = document.getElementById(c); if (e != null) for (var f = 0x0; f < e.options.length; f++) { if (e.options[f].value == d) { e.options[f].setAttribute("\x73\x65\x6c\x65\x63\x74\x65\x64", "\x73\x65\x6c\x65\x63\x74\x65\x64"); break } } }; function getArgs(c) { var d = new Object(); var e = location.search.substring(0x1); var f = e.split("\x26"); for (var g = 0x0; g < f.length; g++) { var h = f[g].indexOf('\x3d'); if (h == -0x1) continue; var i = f[g].substring(0x0, h); var j = f[g].substring(h + 0x1); d[i] = j }; return d[c] }; function FT() { }; var basea = "\x41\x42\x43\x44\x45\x46\x47\x48\x49\x4a\x4b\x4c\x4d\x4e\x4f\x50\x51\x52\x53\x54\x55\x56\x57\x58\x59\x5a\x61\x62\x63\x64\x65\x66\x67\x68\x69\x6a\x6b\x6c\x6d\x6e\x6f\x70\x71\x72\x73\x74\x75\x76\x77\x78\x79\x7a\x30\x31\x32\x33\x34\x35\x36\x37\x38\x39\x2b\x2f"; function base64encode(c) { var d, e, f; var g, h, i; f = c.length; e = 0x0; d = ""; while (e < f) { g = c.charCodeAt(e++) & 0xff; if (e == f) { d += basea.charAt(g >> 0x2); d += basea.charAt((g & 0x3) << 0x4); d += "\x3d\x3d"; break }; h = c.charCodeAt(e++); if (e == f) { d += basea.charAt(g >> 0x2); d += basea.charAt(((g & 0x3) << 0x4) | ((h & 0xF0) >> 0x4)); d += basea.charAt((h & 0xF) << 0x2); d += "\x3d"; break }; i = c.charCodeAt(e++); d += basea.charAt(g >> 0x2); d += basea.charAt(((g & 0x3) << 0x4) | ((h & 0xF0) >> 0x4)); d += basea.charAt(((h & 0xF) << 0x2) | ((i & 0xC0) >> 0x6)); d += basea.charAt(i & 0x3F) }; return d }; function toStdAmount(a) { var b = /\,/gi; var c = a.toString().replace(b, ""); var d = c.indexOf('\x2e'); var e = c.length; var f = /\D/; if ((d != -0x1 && e - d > 0x3) || f.test(c.slice(d + 0x1, e))) return -0x1; else { if (d == -0x1) c = c + '\x2e\x30\x30'; else if (d == 0x0) { if (e - d == 0x1) c = '\x30' + c + '\x30\x30'; if (e - d == 0x2) c = '\x30' + c + '\x30'; if (e - d == 0x3) c = '\x30' + c } else { if (e - d == 0x2) c = c + '\x30'; if (e - d == 0x1) c = c + '\x30\x30' }; var g = ""; g = c.slice(0x0, d); var h = new Number(g); g = h.toString(); if (g.length > 0x10) return -0x2; d = c.indexOf('\x2e'); c = g + c.slice(d); return c } }; function getCHCurrency(a) { var b = toStdAmount(a); if (b < 0x0) return b; var c = new Array("\u96f6", "\u58f9", "\u8d30", "\u53c1", "\u8086", "\u4f0d", "\u9646", "\u67d2", "\u634c", "\u7396"); var d = new Array('\u5143', '\u4e07', '\u4ebf', '\u4e07'); var e = new Array('\u62fe', '\u4f70', '\u4edf'); var f = '\u96f6'; var g = ""; var h = b.indexOf('\x2e'); var i = b.slice(0x0, h); var j = b.slice(h); var k = 0x0; k = i.length; var l = 0x0, m = 0x0, n = 0x0; var o = '\x33'; var p = '\x30'; g = d[0x0] + g; var q = false; var r = false; var s = true; for (l = 0x0; l < k; l++) { if (m == 0x0 && l != 0x0) { if (!r) { if ((n % 0x2) == 0x0) g = g.slice(0x1) } else { if (o == '\x30') g = f + g }; g = d[n] + g; q = r; r = false }; p = i.charAt(k - l - 0x1); if (o == '\x30' && p != o) { if (r) g = f + g }; if (p != '\x30') { if (m != 0x0) g = e[m - 0x1] + g; var x = '\x30'; x = c[p]; if (x == '\x30') return false; r = true; s = false; g = x + g }; o = p; m++; if (m == 0x4) { n++; m = 0x0 } }; if (s) { g = "\u96f6\u5143" } else { var x = 0x0; if (!r) { x++; if (!q) { x++ } }; if (x != 0x0) g = g.slice(x); if (g.charAt(0x0) == '\u96f6') g = g.slice(0x1) }; j = j.slice(0x1); k = j.length; var t = new Array('\x30', '\x30'); var u = new Array('\u89d2', '\u5206'); var v = 0x0; var w = '\x30'; t[0x0] = j.charAt(0x0); if (k > 0x1) t[0x1] = j.charAt(0x1); else t[0x1] = '\x30'; if ((t[0x0] == '\x30') && (t[0x1] == '\x30')) return g += '\u6574'; else if (s) g = ""; for (l = 0x0; l < 0x2; l++) { var x = t[l]; w = c[x]; if (l == 0x0) { if (g != "" || x != '\x30') g += w; if (x != '\x30') { g += u[0x0] } }; if (l == 0x1 && x != '\x30') g = g + w + u[0x1] }; return g };
String.prototype.replaceAll = function (reallyDo, replaceWith, ignoreCase) {
    if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
        return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi" : "g")), replaceWith);
    } else {
        return this.replace(reallyDo, replaceWith);
    }
}

function CheckKey(e) {
    var key = window.event ? e.keyCode : e.which;
    if (key == 13) return true;
    if ((key < 48 || key > 57) && (key > 95 || key < 106) && key != 0) {
        alert("下注金额仅能输入数字!!");
        return false;
    }
}

function CancelOrderGold(form) {
    $("input[name='number[]'][type=text]").val("");
}


function addTmsb(){
	var num1 = parseInt($("#num1").val());
	var num2 = parseInt($("#num2").val());
	var num3 = parseInt($("#num3").val());
	if(num1 == num2 || num2 == num3 || num1 == num3){
		alert("特码包三号码重复");
		return;
	}
	$("#TMSB_NUM").val(num1 + "," + num2 + "," + num3);
}

/**
 * 获取提交数据
 */
function doOrderSubmit(form,this_a){
	
	//验证是否选择包三
	var tmsb_num = $("#TMSB_NUM").val();
	var tmsb_money = $("input[type=number][enumcode='TMSB']").val();
	if(typeof(tmsb_num) != "undefined" && tmsb_num != ""){
		var tmsb_money = $("input[type=number][enumcode='TMSB']").val();
		console.log(tmsb_money);
		if("" == tmsb_money){
			alert("请输入特码包三金额");
			return;
		}
	}
	
	if(typeof(tmsb_money) != "undefined" && tmsb_money != ""){
		if(tmsb_num == ""){
			alert("请选择特码包三号码");
			return;
		}
	}
	
	var aOdds = $("input[name='number[]'][type=number]");
	 var errmsg="";
	 var minMoney,maxMoney,maxSumMoney,userMoney;
	 minMoney=$("#minMoney").val();//针对彩票玩法单注最低金额
	 maxMoney=$("#maxMoney").val();//最高金额
	 maxSumMoney=$("#maxSumMoney").val();
	 userMoney=$("#userMoney").val();
	 
	 var json = "{id: '{0}', rate: '{1}', val: '{2}', num:'{3}', cateName:'{4}', xzlxName:'{5}'}";
	 var jsons = "";
	 var total=0;
	    for (var i = 0; i < aOdds.length; i++) {
	    	var obj=aOdds[i];
	        if (obj.value != "") {
	        	var minSingle = $(obj).attr("minSingle");//针对号码单注最低金额
	        	var maxSingle = $(obj).attr("maxSingle");//最高金额
	        	if(minSingle != "" || maxSingle != ""){
	        		if(obj.value < parseFloat(minSingle) ||　obj.value > parseFloat(maxSingle)){
		        		errmsg="号码"+$(obj).attr("num")+"下注金额不符合要求！\n" + "最小单号投注金额："+minSingle+"元\n"+"最大单号投注金额:"+maxSingle+"元";
		        		break;
		        	}
	        	}else{
	        		if(obj.value<parseFloat(minMoney) ||　obj.value>parseFloat(maxMoney)){
		        		errmsg="下注金额不符合要求！\n" + "最小单号投注金额："+minMoney+"元\n"+"最大单号投注金额:"+maxMoney+"元";
		        		break;
		        	}
	        	}
	        	if(jsons!=""){
	        		jsons+=",";
	        	}
	            jsons += json.format(obj.id, $(obj).attr("rate"),obj.value,$(obj).attr("num"), $(obj).attr("cateName"),$(obj).attr("xzlxName"));
	            total+=parseFloat(obj.value);
	        }
	    }
	    if(total>userMoney){
	    	errmsg="余额不足!";
	    }else if(total>maxSumMoney){
	    	//errmsg="单笔交易最大投注额为:"+maxSumMoney+"元!";
	    }
	    if(errmsg==""){
	    	orderSubmit(jsons,form,tmsb_num,this_a);
	    }else{
	    	alert(errmsg);
	    }
}
/**
 * 订单列表提交
 */
function orderSubmit(jsons,form,tmsb_num,this_a){
	if(jsons!=""){
    	jsons="["+jsons+"]";
    	var jsonInput="<input type='hidden' id='jsonDatas' name='jsonDatas' value='"+jsons+"' />";
    	$("#"+form).append(jsonInput);
    	var tmsb ="<input type='hidden' id='tmsb' name='tmsb' value='"+tmsb_num+"' />";
    	$("#"+form).append(tmsb);
    	$("#jsonDatas").val(jsons);
    	$("#"+form).submit();
    }else {
        alert("未输入下注金额!!");
    }
}


/**
 * 获取提交数据
 */
function doSaveOrderData(form){
	var aOdds = $("input[name='number[]']");
	
	//var inputs=$("#"+form.id+" input[type=text]");
	 
	 var json = "{id: '{0}', val: '{1}'}";
	 var jsons = "";
	
	for (var i = 0; i < aOdds.length; i++) {
	    var obj=aOdds[i];
	       if (obj.value != "") {
		       	if(jsons!=""){
		        	jsons+=",";
		       	}
	           jsons += json.format(obj.id, obj.value);
	       }
	}
	if(jsons!=""){
    	jsons="["+jsons+"]";
	}
	return jsons;
	 
}

function checkCpSatus(code){
	var flag = false;
	$.ajax({
		url : rootPath+"/valid/checkflatsatus",
		type: "post",
		data : {"code":code},
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		timeout : 30000,
		dataType :"json",
		cache : false,
		async:false,
		error:function(){
			alert("网络连接有问题!");
		},
		success : function(obj) {
			if (obj.datas.status) {
				alert(obj.datas.msg);
			}else{
				flag = true;
			}
		}
	}); 
	return flag;
}

//下注方法
function saveOrder(form,this_a) {
	
	var cfgIds="";
	var jsons = "";
	var json = "{id: '{0}', val: '{1}'}";
	var aOdds = $("input[name='number[]']");
	for (var i = 0; i < aOdds.length; i++) {
	    var obj=aOdds[i];
	       if (obj.value != "") {
		       	if(jsons!=""){
		        	jsons+=",";
		        	cfgIds+=",";
		       	}
	           jsons += json.format(obj.id, obj.value);
	           cfgIds += obj.id;
	       }
	}
	if(jsons!=""){
    	jsons="["+jsons+"]";
	}
	var gameTypeCode=$("#gameTypeCode").val();
	var cpTypeCode = $("#cpTypeCode").val();
	var tmsb = $("#tmsb").val();
	var bigtypeCode = $("#bigtypeCode").val();
	if(!checkCpSatus(gameTypeCode)){
		return;
	}
	var token=$("#_form_token_uniq_id").val();
	 $.post(rootPath+"/m/mobileCpOrder/saveOrder",{ jsonDatas: jsons,gameTypeCode:gameTypeCode,ids:cfgIds,_form_token_uniq_id:token,cpTypeCode:cpTypeCode,tmsb:tmsb,bigtypeCode:bigtypeCode},function (data) {
		 if(""!=data){
	    	  var info = eval("(" + data + ")");
		      var resultCode = info.resultCode;
		      switch (resultCode) {
		            case 0:{
		            		alert("非法的注单请求!");
		            		history.go(-1);
		            		break;
		            }
		            case -1:{
		                    alert("您的可用额度不足！");
		                    history.go(-1);
		                    break;
		            }
		            case 400:{
		            		alert(info.message);
		            		history.go(-1);
		                    break;
		            }
		            case 401:{
			        		alert("该彩票已封盘，暂停投注,请先选择其他彩票投注！");
			        		location.href=rootPath+"/m/mobileCpMain/menu";
			                break;
		            }
		            case 110:{
			        		alert("该彩票正在维护，暂停投注,请先选择其他彩票投注！");
			        		location.href=rootPath+"/m/mobileCpMain/menu";
			                break;
		            }
		            case 200:{
		                    alert("下注成功，请到下注状况查看！");
		                    var a_url=rootPath+"/m/mobileCpAccount/getOrderList?gameTypeCode="+gameTypeCode;
		                    
		                    var stateObject = {};
		                    var title = "下注";
		                    var newUrl = rootPath+"/m/mobileCpMain/menu";
		                    history.pushState(stateObject,title,newUrl);
		                    
		                    this_a.href=a_url;
		                    this_a.setAttribute("onclick",'');
		                    this_a.click("return false");  
		                    break;
		            }
		        }
	       }else{
	    	   alert("请勿重复提交订单！");
	       }
	    });
}







function clearChk() {
    $("input[type=checkbox]").attr("checked", false).checkboxradio("refresh");
    $("#zu3zu6Rate").html("");
    $("#zu3zu6Str").val("");
    $("#money").val("");
}
//组选3 组选6
function GS_Chk(type,obj) {
    var maxcount = 10;
    var avgcount = 5;
    if (type.indexOf("组选六")>=0) {
        maxcount = 8;
        avgcount = 4;
    }
    var lists = "", count = 0;
    $('input[type=checkbox]:checked').each(function () {
        lists += $(this).val() + ",";
        count++;
    });

    if (count > maxcount) {
        alert("最多选" + maxcount + "个号码！");
        $(obj).attr("checked", false).checkboxradio("refresh");
                   return false;
    }

    //        if (count > maxcount) {
    //            alert("最多选8个号码！");
    //            return false;
    //        }
    var UID = $("#UID").val();
    if (count >= avgcount) {
        $("#ok").attr("disabled", false).button();
        $.ajax({
            type: "POST",
            url: "/Ajax/GetZXWebRates" + "?UID="+UID+"&t=" + new Date(),
            data: "GameType=" + $("#sGtype").val() + "&TP3=" + type + "&RateVal1=" + lists.substring(0, lists.length - 1)+"&UID="+UID,
            success: function (data) {
                var str = data;
                var ValRate = str.split('-')[1];
                var rate = (parseFloat(ValRate) / str.split('-')[0]).toFixed(2);
                //$("#sBetInfo").html(lists.substring(0, lists.length - 1));
                //$("#sBetRate").html("@@" + rate);
                $("#zu3zu6Str").attr({ tp3: type, value: lists.substring(0, lists.length - 1), rate: rate });
                $("#zu3zu6Rate").html(rate);
            }

        });
    }
    else {
        $("#ok").attr("disabled", true).button();
        $("#zu3zu6Rate").html("");
        $("#zu3zu6Str").val("");
        $("#money").val("");
    }

}



