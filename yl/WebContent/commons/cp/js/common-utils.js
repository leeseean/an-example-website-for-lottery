// JavaScript Document
String.prototype.format = function(args) {
    var result = this;
    if (arguments.length < 1) {
        return result;
    }

    var data = arguments;       //如果模板参数是数组
    if (arguments.length == 1 && typeof (args) == "object") {
        //如果模板参数是对象
        data = args;
    }
    for (var key in data) {
        var value = data[key];
        if (undefined != value) {
            result = result.replace("{" + key + "}", value);
        }
    }
    return result;
};
/**
 * 
 * trim
 * 
 * @return
 */
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};

String.prototype.contains = function(s){
	return this.indexOf(s) != -1;
};

String.prototype.startWith = function(s){
	return this.indexOf(s) == 0;
};

function toInt(s){
	if(!isEmpty(s) && isNumber(s)){
		return parseInt(s,10);
	}
	return 0;
};

function toJson(jsonString){
	if(isNotEmpty(jsonString)){
		return eval('(' + jsonString + ')');
	}
	return null;
};

function toJsonString(jsonObject){
	if(jsonObject != null){
		return JSON.stringify(jsonObject);
	}
	return null;
};

function getType(id) {
	return document.getElementById(id).type;
};

/**
 * 四舍五入
 * rate -- 保留小数位数
 */
function round(numeric, rate){
    if(rate < 0){
        return numeric;
    }
    var m = 1;
    for(var i = 0; i < rate; i++){
        m *= 10;
    }
    return Math.round(numeric * m) / m;
};
	
/**
 * instead getElementById()
 * 
 * @param id
 * @return
 */
function getElementByID(id) {
	if (document.all) {
		return document.all(id);
	} else {
		return document.getElementById(id);
	}

};

/**
 * 验证 Email
 * 
 * @param email
 * @returns
 */
function isValidEmail(email) {
	var emailReg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
	return emailReg.test(email);
};

/**
 * 匹配帐号是否合法(字母开头，允许5-16字节，允许字母数字下划线)
 * 
 * @param name
 * @returns
 */
function isValidAccount(account) {
	var nameReg = /^[a-zA-Z][a-zA-Z0-9]{4,15}$/; // 不允许下划线
	// var nameReg = /^[a-zA-Z][a-zA-Z0-9_]{4,15}$/; //允许下划线
	return nameReg.test(account);
}

/** ******************* 判断浏览器 *********************** */
function getBrowser() {
	if (navigator.userAgent.indexOf("MSIE") > 0)
		return 1;
	if (navigator.userAgent.indexOf("Firefox") > 0)
		return 2;
	if (navigator.userAgent.indexOf("Safari") > 0)
		return 3;
	if (navigator.userAgent.indexOf("Camino") > 0)
		return 4;
	if (navigator.userAgent.indexOf("Gecko") > 0)
		return 5;

	return 0;
};

/** **************************************************** */
/**
 * 
 * 
 * @param obj
 * @return
 */
function isNotEmpty(obj) {

	return !isEmpty(obj);

};
/**
 * 
 * 
 * @param obj
 * @return
 */
function isEmpty(obj) {

	return (typeof obj == "undefined" || obj == null || obj == "");

};

function isNumber(number) {

	return !isNaN(number);

};

function isValidTime(time) {
	// 23:13
	var partrn = /^[0-2]{1}[0-9]{1}:[0-5]{1}[0-9]{1}$/;

	if (partrn.exec(time)) {
		if (parseInt(time.split(":")[0], 10) <= 23)
			if (parseInt(time.split(":")[1], 10) <= 59)
				return true;
	}
	return false;
};

function isAlphabets(s) {
	var regu = "^[A-Z]+$";
	var re = new RegExp(regu);
	if (s.toUpperCase().search(re) != -1) {
		return true;
	}
	return false;
};

/**
 * to upper case
 * 
 * @return
 */
function toUpper() {
	$(this).attr('value', $(this).attr('value').toUpperCase());
};

function getDateInterval(startDate, endDate) {

	return Math.floor(Math.abs((startDate.getTime() - endDate.getTime())
			/ (1000 * 60 * 60 * 24)));

};

function getDays(year, month) {
	if (month == 4 || month == 6 || month == 9 || month == 11) {
		return 30;
	} else if (month == 2) {
		return isLeapYear(year) ? 29 : 28;
	} else {
		return 31;
	}
};

function isValidDate(year, month, day) {
	if (day < 1 || month < 1 || month > 12)
		return false;
	if (month == 2) {
		if (isLeapYear(year))
			return day <= 29;
		else
			return day <= 28;
	} else if (month == 4 || month == 6 || month == 9 || month == 11) {
		return day <= 30;
	} else {
		return day <= 31;
	}
};

function isLeapYear(year) {
	return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
}

function jsSelectIsExitItem(objSelect, objItemValue) {
	var isExit = false;
	for ( var i = 0; i < objSelect.options.length; i++) {
		if (objSelect.options[i].value == objItemValue) {
			isExit = true;
			break;
		}
	}
	return isExit;
};

function jsAddItemToSelect(objSelect, objItemText, objItemValue) {

	if (jsSelectIsExitItem(objSelect, objItemValue)) {

	} else {
		var varItem = new Option(objItemText, objItemValue);
		objSelect.options.add(varItem);

	}
};

function jsRemoveItemFromSelect(objSelect, objItemValue) {

	if (jsSelectIsExitItem(objSelect, objItemValue)) {
		for ( var i = 0; i < objSelect.options.length; i++) {
			if (objSelect.options[i].value == objItemValue) {
				objSelect.options.remove(i);
				break;
			}
		}

	} else {

	}
};

function jsRemoveSelectedItemFromSelect(objSelect) {
	var length = objSelect.options.length - 1;
	for ( var i = length; i >= 0; i--) {
		if (objSelect[i].selected == true) {
			objSelect.options[i] = null;
		}
	}
};

function jsUpdateItemToSelect(objSelect, objItemText, objItemValue) {

	if (jsSelectIsExitItem(objSelect, objItemValue)) {
		for ( var i = 0; i < objSelect.options.length; i++) {
			if (objSelect.options[i].value == objItemValue) {
				objSelect.options[i].text = objItemText;
				break;
			}
		}

	} else {

	}
};

function jsSelectItemByValue(objSelect, objItemValue) {
	for ( var i = 0; i < objSelect.options.length; i++) {
		if (objSelect.options[i].value == objItemValue) {
			objSelect.options[i].selected = true;
			isExit = true;
			break;
		}
	}
};

function jsSelectItemByText(objSelect, objItemText) {

	var isExit = false;
	for ( var i = 0; i < objSelect.options.length; i++) {
		if (objSelect.options[i].text == objItemText) {
			objSelect.options[i].selected = true;
			isExit = true;
			break;
		}
	}

	if (isExit) {

	} else {

	}

};

function preview(oper) {
	if (oper < 10) {
		bdhtml = window.document.body.innerHTML;// 获取当前页的html代码
		sprnstr = "<!--startprint" + oper + "-->";// 设置打印开始区域
		eprnstr = "<!--endprint" + oper + "-->";// 设置打印结束区域
		prnhtml = bdhtml.substring(bdhtml.indexOf(sprnstr) + 18); // 从开始代码向后取html

		prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));// 从结束代码向前取html
		window.document.body.innerHTML = prnhtml;
		window.print();
		window.document.body.innerHTML = bdhtml;

	} else {
		window.print();
	}
};

/**
 * 触发单击事件
 * @param el
 */
function clickEvent(el) {
	if (document.all) {
		el.fireEvent('onclick');
	} else {
		var evt = document.createEvent("MouseEvents");
		evt.initEvent("click", true, true);
		el.dispatchEvent(evt);
	}
};

/**
 * 转换秒到时间格式字符串
 */
function toTime(second){
	var temp = null;
	var sec = second % 60;
	var min = (second - sec) / 60;
	if(min < 10){
		temp = "0" + min;
	} else {
		temp = min;
	}
	if(sec < 10){
		temp += ":0" + sec;
	} else {
		temp += ":" + sec;
	}
	return temp;
};

function padPrefix(s,pad,len){
	if(isEmpty(s)){
		return s;
	};
	var strLen = s.length;
	if (strLen >= len) {
		return s;
	}
	for (var i = 0; i < len - strLen; i++) {
		s = pad + s;
	}

	return s;
};

/**
 *	判断是否为非负整数
 */
function isInteger(num){
	return /^\+?[1-9][0-9]*$/.test(num);
};

/**
"^\\d+$"　　//非负整数 (正整数  +  0)
"^[0-9]*[1-9][0-9]*$"　　//正整数
"^((-\\d+)|(0+))$"　　//非正整数(负整数  +  0)
"^-[0-9]*[1-9][0-9]*$"　　//负整数
"^-?\\d+$"　　　　//整数
"^\\d+(\\.\\d+)?$"　　//非负浮点数(正浮点数  +  0)      
"^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$"　　//正浮点数
"^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$"　　//非正浮点数(负浮点数  +  0)
"^(-(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*)))$"　　//负浮点数
"^(-?\\d+)(\\.\\d+)?$"　　//浮点数
*/