var balance_timer=null;
var countclose_timer = null;
var countopen_timer = null;
var shengXiaoMap = null;
var maxBetMap = null;
var minBetMap = null;
var maxBatchMap = null;
var typeMap = null;
var cateMap = null;
var numberMap = null;

var cp_gameTypeCode_temp="HK6";//一级分类
var cp_typeCode_temp="TM";//二级分类
var cp_cateCode_temp="";//三级分类
var cp_xzlxCode_temp="";//四级分类

var minMoney=10;//最小单注下注金额
var maxMoney=10000;//最大单注下注金额
var maxSumMoney=1000000;//最大总下注额
var userMoney=0;
var gpid_temp="";
var reTimeObj=null;//
var refresh_pl_time=30000;//刷新时间

var $ruleMain; // 游戏规则容器
var $ruleFilter;  // 容器规则筛选

$(function() {
	
	var $con = $('#memu_two');
	$('a.ui-link', $con).on('start_timer', function () {
		var $self = $(this),
			key = $(this).data('key'),
			dates = $(this).data('dates');
		
		var leftSeconds = dates['fengpan_time'];
		var kaijiangSeconds = dates['kaijiang_time'];
		var kaipanSeconds = dates['kaipan_time'];
		var status = dates['status'];
		
		//console.log(dates);
		
		if ($self.find('.r2').size() <= 0) {
			$self.append('<div class="r2"></div>');
		}
		var $timerEl = $self.find('.r2');
		function startFengpanKaiJiangTimerInterval() {
			
			// 开奖时间到后重新刷新页面 - 注意 只刷新当前显示的菜单栏 
			if (kaijiangSeconds == 0 && $('#'+key).parents('.nav-group').is(':visible')) {
				timer.dropCallback('startFengpanKaiJiangTimerInterval'+key);
				enabledOpenData();
			}
			else {
				if (kaipanSeconds > 0) {
					var timeStr = $.formatSecondToTime(kaipanSeconds);
					$timerEl.html(timeStr);
				}
				else {
					if (leftSeconds <= 0) {
						$timerEl.html('已封盘');
					}
					else {
						var timeStr = $.formatSecondToTime(leftSeconds);
						$timerEl.html(timeStr);
					}
				}
			}
			
			leftSeconds --;
			kaijiangSeconds --;
			kaipanSeconds --;
		}
		
		if (status != 0) {
			$timerEl.html('维护中');
		}
		else {
			// 时间钟
			timer.dropCallback('startFengpanKaiJiangTimerInterval'+key);
			timer.pushCallback(startFengpanKaiJiangTimerInterval, this, 'startFengpanKaiJiangTimerInterval'+key);
		}
	});
	
	// 开始导航倒计时
	$.startFengpanKaiJiangTimer = function(dates) {
		for (var key in dates) {
			$('#'+key + ' a.ui-link', $con)
				.data('dates', dates[key])
				.data('key', key)
				.trigger('start_timer');
		}
	};
	
	HookerLottoGetGameInfo.on('status', function (status, allData) {
		if (status == 2) {
			//console.log([status, allData]);
		}
	});
	
	// 封盘/ 开奖 倒计时
	HookerLottoGetGameInfo.on('timeMap', function (timeMap, allData) {
		var dates = {};
		for (var timerId in timeMap) {
			var times = timeMap[timerId].split('_');
			var fengpanTime = times[1]*1,
				status = times[2]*1,
				kaijiangTime = times[0]*1;
			
			dates[timerId] = {
					status: status,
					kaijiang_time: kaijiangTime,
					fengpan_time: fengpanTime,
					kaipan_time: times[3]*1
			};
		}
		// 开启菜单栏倒计时
		$.startFengpanKaiJiangTimer(dates);
		
		// 开启控制面板倒计时
		var gameCode = allData.game_type_code;
		if (typeof allData.timeMap[gameCode] != 'undefined') {
			var times = allData.timeMap[gameCode].split('_');
			// 测试代码
			var timers = [100, 100, 0, 10];
			var fengpanTime = times[1]*1,
				nextKaiPanTime = times[3]*1,
				kaijiangTime = times[0]*1;
			
			var status = times[2]*1;
			
			if (status == -1 ) {
				makeItToBeWeihu();
			}
			else {
				//  一般彩球 
				if(allData.is_ssc == 0) {
					if (nextKaiPanTime) {
						countNoralNextKaiPanTimer(nextKaiPanTime);
					}
					else {
						// 封盘倒计时
						countSscTimer(fengpanTime, kaijiangTime);	
					}
				}
				// 时时彩
				else{
					// 距离开盘
					if (nextKaiPanTime) {
						countNoralNextKaiPanTimer(nextKaiPanTime);
					}
					else {
						// 开奖封盘倒计时
						countSscTimer(fengpanTime, kaijiangTime);
					}
				}
			}
		}
		else {
			makeItToBeWeihu();
		}
	});
	

	
	//主菜单切换
	$("#memu ul li").click(function(){
		openGameRule('hide');
		
		$(this).siblings().removeClass("ui-active");
		$(this).addClass("ui-active");
		
		var this_id = $(this).attr("id");
		var thisObj = $("#"+this_id+"_1");
		
		
		thisObj.parent().find("ul li").removeClass("ui-active");
		thisObj.find("ul li").removeClass("ui-active");
		thisObj.find("ul li:first").addClass("ui-active");
		thisObj.siblings().hide();
		thisObj.show();
		
		var three_id = thisObj.find("ul li:first").attr("id");
		var threeObj = $("#"+three_id+"_1");
		threeObj.parent().find("ul li").removeClass("ui-active");
		threeObj.siblings().hide();
		threeObj.show();//三级菜单
		
		threeObj.find("li").removeClass("ui-active");
		threeObj.find("li:first").addClass("ui-active");
		
		initBetData(null,null);
	});
	
	//二级菜单切换
	$("#memu_two ul li").click(function(){
		openGameRule('hide');
		
		$(this).siblings().removeClass("ui-active");
		$(this).addClass("ui-active");
 
		var this_id = $(this).attr("id");
		var thisObj = $("#"+this_id+"_1");
		thisObj.parent().find("ul li").removeClass("ui-active");
		thisObj.siblings().hide();
		
		
		thisObj.show();//三级菜单
		
		thisObj.find("li").removeClass("ui-active");
		thisObj.find("li:first").addClass("ui-active");

		//加载模板
		initBetData(null,null);
	});
	
	//三级菜单
	$("#betmenu_data").on('click', "li", function() {
		openGameRule('hide');
		
		$(this).parent().parent().find("ul li").removeClass("ui-active");
		
		$(this).addClass('ui-active').siblings().removeClass('ui-active');
		
		
		$nextd = $(this).find('a');
		initBetData($nextd.attr('p'),$nextd.text());
	});
	
	Core._ajax_template(rootPath+ "/cp/allTemplate", "g", function () {
		Core._ajax_template(rootPath+ "/cp/betTemplate", "b", function () {
			var active_navs = cpparam.split('|');
			if ( active_navs[0] != "") {
				var firstLevelId = active_navs[0];
				var secondLevelId = active_navs[1];
				var thirdLevelId = active_navs[2];
				
				// 补齐参数
				if (typeof secondLevelId == 'undefined') {
					secondLevelId = $('#memu_two').find('div[id^="'+firstLevelId+'"]').find('li').eq(0).attr('id');
				}
				if (typeof thirdLevelId == 'undefined') {
					thirdLevelId = $('#memu_three').find('ul[id^="'+secondLevelId+'"]').find('li:eq(0)').find('a').attr('p');
				}
				
				// 设置 ui-active
				//第一层
				$('#'+firstLevelId).addClass('ui-active').siblings().removeClass('ui-active');
				
				// 第二层
				$('#memu_two')
					.find('.ui-active').removeClass('ui-active')
					.end()
					.find('.nav-group').removeAttr('style')
					.end()
					.find('div[id^="'+firstLevelId+'"]').css({display: 'block'}).find('#'+secondLevelId).addClass('ui-active');
				
				
				// 第三层
				$('#memu_three')
					.find('.ui-active').removeClass('ui-active')
					.end()
					.find('ul.cf').removeAttr('style').css({display: 'none'})
					.end()
					.find('ul[id^="'+secondLevelId+'"]').css({display: 'block'}).find('a[p="'+thirdLevelId+'"]').parent().addClass('ui-active');
			}
			else {
				//页面加载时显示   默认显示菜单选项第一个
				var firstLi = $("#memu ul").children().eq(0);	//一级菜单
				firstLi.addClass("ui-active");
				
				var two_id =  firstLi.attr("id");
				$("#"+two_id+"_1").show();	//二级菜单
				
			 
				var three = $("#"+two_id+"_1").find("ul li:first");
				three.addClass("ui-active");
				
				var threeObj = $("#"+three.attr("id")+"_1");
				threeObj.show();//三级菜单
				
				threeObj.find("li").removeClass("ui-active");
				threeObj.find("li:first").addClass("ui-active");
			}
			
			initBetData(null,null);
		});
	});
	
	if(balance_timer!=null){
		window.clearInterval(balance_timer);
	}
	balance_timer = window.setInterval("balance()",20000);
	
	// 游戏规则切换
	$ruleMain = $('.rule-main').parents('.pure-g:eq(0)');
	$ruleFilter = $('select[name="rule-filter"]', $ruleMain);
	$ruleFilter.change(function () {
		var id = $(this).val();
//		console.log([id, "#RULES_" + id ]);
		$('#RULES_'+id)
			.show()
			.siblings('div[id^="RULES_"]')
			.hide();
	});
});

function startLoadingMask() {
	var $appBody = $('.lotto-body');
	var $maskHtml = $('<div class="mask-loading"><p class="loading2"></p></div>');
	if ($appBody.find('.mask-loading').size() > 0) {
		$appBody.find('.mask-loading').show();
	}
	else{
		$appBody.prepend($maskHtml);
	}
}

function endLoadingMask() {
	var $appBody = $('.lotto-body');
	if ($appBody.find('.mask-loading').size() > 0) {
		$appBody.find('.mask-loading').hide();
	}
}

//初始化数据
function initBetData(p,n) {
	startLoadingMask();
	
	var $threeobj=$("#memu_three div>ul li.ui-active a");
	if(p==null){
		p =  $threeobj.attr('p');
	}
	if(n==null){
		n =  $threeobj.text();
	}
	
	var $twoobj = $("#memu_two div>ul li.ui-active a");
	var gpid=$twoobj.find("div:first").attr("gpid");
	
	gpid_temp = gpid;
	var pArr = p.split("_");
	
	Core.SetBetTemplate(p);
	
	getOpenResultInfo(gpid, pArr[1], function (data, gpid, typeid) {
		
		$("#fastbet_input").on("keyup click keydown", function(){
			
			if(!isInteger($(this).val())){
				$("#fastbet_input").val("");
			}
			$("#back_fastbet_input").val($(this).val());
			
			$("#betForm input[type='text']").each(function(index,value){
				var id =$(this).attr("id");
				if($("#pl_"+id).hasClass("ui-active")){
					$(this).val($("#fastbet_input").val());
				}
			});
		});
		
		//绑定快捷下注
		$("#back_fastbet_input").on("keyup click keydown", function(){
			
			if(!isInteger($(this).val())){
				$("#fastbet_input").val("");
				$("#back_fastbet_input").val("");
			}
			$("#fastbet_input").val($(this).val());
			$("#betForm input[type='text']").each(function(index,value){
				var id =$(this).attr("id");
				if($("#pl_"+id).hasClass("ui-active")){
					$(this).val($("#fastbet_input").val());
				}
			});
		});
		
		//验证输入框
		$("#betForm").on("click keyup keydown","input[type='text']", function(){
			if(!isInteger($(this).val())){
				$(this).val("");
			}
		});
		
		$("#left_menu_0").parent().addClass("ui-active");
		$("#left_menu_1").parent().removeClass("ui-active");
		//六合彩显示快捷下注
		if(gpid=='1'){
			$("#left_menu_0").html("快捷下注");
			$("#left_menu_0").attr("tmpid","fastbet");
			Core.SetTemplate("fastbet");//加载快捷下注模板
		}else{//显示长龙
			$("#left_menu_0").html("近期开奖号码");
			$("#left_menu_0").attr("tmpid","changlong");
			Core.SetTemplate("changlong");//加载近期开奖号码
		}
		
		//绑定右侧列表 tab切换
		$('.ui-tabs .ui-tabs-head .ui-link').on('click', function(event) {
			  var currentAttrValue = $(this).attr('data-url');
			  if($(this).text()!='快捷下注' && $(this).text()!='最近交易记录' && $(this).text()!='近期开奖号码'){
				  $("#typeBetName").html($(this).text());
			  }
			  $('.ui-tabs-item' + currentAttrValue).fadeIn(400).siblings().hide();
			  $(this).parent('li').addClass('ui-active').siblings().removeClass('ui-active');
			  
			 $("#betForm input[type='text']").each(function(index,value){
				var id =$(this).attr("id");
				$(this).val("");
				$("#pl_"+id).removeClass("ui-active");
			 });
			 $("#betForm input[type='checkbox']").each(function(index,value){
				$(this).removeAttr("checked");
				
			 });
			 $("#betForm input[type='radio']").each(function(index,value){
				$(this).removeAttr("checked");
			 });
			 
			 $(window).trigger('ui-tab:ui-link:clicked', $(this).data('url'));
			 
			 
			 event.preventDefault();
		});
		
		$("#gameBetName").html($twoobj.find("div:first").text());
		$("#gameBetName").attr('gpid',gpid); 
		$("#typeBetName").html(n);
		$("#typeBetName").attr('typeid',pArr[1]);
		
		endLoadingMask();
		
		callbackWhenOpenResultFinished(data, gpid, typeid);
	});
}

// 获取球色
function getColorFromTotal(total) {
	var red_total = [3, 6, 9, 12, 15, 18, 21, 24];
	var green_total = [1, 4, 7, 10, 16, 19, 22, 25];
	var blue_total = [2, 5, 8 , 11, 17, 20, 23, 26];
	var gray_total = [0, 13, 14, 27];
	var color = '';
	if (red_total.indexOf(1*total) !== -1) {
		color = 'rec-red';
	}
	else if (green_total.indexOf(1*total) !== -1) {
		color = 'rec-green';
	}
	else if (blue_total.indexOf(1*total) !== -1) {
		color = 'rec-blue';
	}
	else if (gray_total.indexOf(1*total) !== -1){
		color = 'rec-gray';
	}
	
	return color;
}

function callbackWhenOpenResultFinished(data, gpid, typeid) {
	// 发布数据更新事件
	HookerLottoGetGameInfo.update(data);
	if(data.status=='2') {
    	var htmlStr = "<div class=\"bet-status\"><div class=\"ui-item status-updata\" ><img src=\""+resourceRoot+"/cp/images/bet_status_updata.png\"></div></div>";
    	$("#betplay_data").html(htmlStr);
	}else{
		var lastResult = data.lastResult;
		var nextResult = data.nextResult;
		cp_gameTypeCode_temp = data.game_type_code;
		cp_typeCode_temp = data.cp_type_code;
		maxBatchMap = data.maxBatchMap;
		maxBetMap = data.maxBetMap;
		minBetMap = data.minBetMap;
		userMoney = data.balance;
		typeMap = data.betDesc[0];
		cateMap = data.betDesc[1];
		numberMap = data.betDesc[2];
		
		
		minMoney = minBetMap[''+cp_typeCode_temp+''];
		maxMoney = maxBetMap[''+cp_typeCode_temp+''];
		maxSumMoney = maxBatchMap[''+cp_typeCode_temp+''];
		
		$("#userBalance").html(data.balance);
		$("#betUsrWin").html(data.win_loss);
		$("#currentQs").html(nextResult.next_qs);
		$("#lastQs").html(lastResult.last_qs);
		if(cp_gameTypeCode_temp=='HK6'){
			shengXiaoMap = data.shengXiaoMap;
			var resultHtml = "";
			$.each(lastResult.last_result,function(index,value) { 
				if(index>5){
					resultHtml+=" <li class=\"ui-item\">+</li>";
				}
				resultHtml+=" <li class=\"ui-item\"><span class=\"ball "+getHK6ColorClassName(value)+"\">"+value+"</span></li>";
			});
			$("#lastResult").html(resultHtml);
		
		}
		else if (cp_gameTypeCode_temp == 'BJKL8' || cp_gameTypeCode_temp == 'CAKENO') {
			var resultHtml = "",
			total = 0;
			$.each(lastResult.last_result,function(index,value) {
				total += value*1;
				//var cls = getColorFromTotal(total);
				var cls = 'rec-gray';
				var symbox = '+';
				if (index == lastResult.last_result.length - 1) {
					symbox = '=';
				}
				resultHtml+=" <li class=\"ui-item\"><span class=\"rec " + cls + "\">"+value+"</span><span class=\"plus-symbol\">"+symbox+"</li>";
			});
			var $resultCon = $("#lastResult").html(resultHtml);
			var color = getColorFromTotal(total);
			$resultCon.append('<li class="ui-item"><span class="rec '+color+'">'+total+'</li>');
		}
		else{
			var resultHtml = "";
			$.each(lastResult.last_result,function(index,value) {
				var cls = '';
				// 北京赛车
				if (gpid == 10) {
					cls = ' ball-num-bksc-'+value+ ' ball-num-bksc ';
				}
				else {
					cls =  ' ball-num ball-num-'+value;
				}
				resultHtml+=" <li class=\"ui-item\"><span class=\"ball ball-blue" + cls + "\">"+value+"</span></li>";
			});
			$("#lastResult").html(resultHtml);
		}
	 

		//刷新赔率
		$.each(data.odds,function(key,val) {
           $("#pl_"+key).html(val);
        });
	}
	
	window.parent.postMessage(JSON.stringify({type: 'load_finish'}), '*');
}

function getOpenResultInfo(gpid,typeid, cb ){
	cb || (cb = function () {});
	$.ajax({
		url: rootPath + "/cp/lottoGetGameInfo/"+gpid+"/"+typeid,
		timeout : 60000,
		async: true,
		dataType:"json",
		type: "POST",
		success: function (data) {
			cb.apply(this, [data, gpid, typeid]);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown){
			//alert("获取游戏信息失败，连接服务超时！");
	    }
	});
}

function getHK6ColorClassName(number){

	var redWave = ['01','02','07','08','12','13','18','19','23','24','29','30','34','35','40','45','46'];
	var blueWave = ['03','04','09','10','14','15','20','25','26','31','36','37','41','42','47','48'];
	var greenWave = ['05','06','11','16','17','21','22','27','28','32','33','38','39','43','44','49'];
	if($.inArray(number, redWave) !=-1){
		return "ball-red";
	}else if($.inArray(number, blueWave)!=-1){
		return "ball-blue";
	}else if($.inArray(number, greenWave)!=-1){
		return "ball-green";
	}
}

function makeItToBeWeihu() {
	var htmlStr = "<div class=\"bet-status\"><div class=\"ui-item status-close\" >";
	htmlStr+="<img src=\""+resourceRoot+"/cp/images/bet_status_updata.png\">";
	htmlStr+="</div></div>";
	$("#betplay_data").html(htmlStr);
}

function makeItToBeFenPan() {
	var htmlStr = "<div class=\"bet-status\"><div class=\"ui-item status-close\" >";
	htmlStr+="<img src=\""+resourceRoot+"/cp/images/bet_status_close.png\">";
	htmlStr+="</div></div>";
	$("#betplay_data").html(htmlStr);
}

// 一般彩球
function countNoralTimer(intDiff) {

	enabledOpenData();
	
	timer.dropCallback('goOpen');
	timer.dropCallback('goClose');
	timer.dropCallback('goStart');
	timer.onceCallback(to);
	
	var $ssc = $('.item-ssc');
	var $fengpanHTML = $ssc.find('.ui-item-gson:eq(0)');
	$fengpanHTML.html('距离封盘：<strong class="red" id="closeTime">--:--</strong>');

	function to() {
	    if (intDiff <= 0 ) {
	    	makeItToBeWeihu();
	        disabledOpenData();
	    }
	    if (intDiff <= 0 ) {
	        $("#closeTime").html("已封盘");
	    }
	    else {
		    $('#closeTime').html($.formatSecondToChineseTime(intDiff));
	    }
	    intDiff--;
  }
}

function countNoralNextKaiPanTimer(startDiff) {
	var $ssc = $('.item-ssc');
	var $fengpanHTML = $ssc.find('.ui-item-gson:eq(0)');
	$fengpanHTML.html('距离开盘：<strong class="red" id="startTime">--:--</strong>');
	$ssc.find('.ui-item-gson:eq(1)').hide();
	
	disabledOpenData();
	
	timer.dropCallback('to');
	timer.dropCallback('goOpen');
	timer.dropCallback('goClose');
	timer.onceCallback(goStart);
	
	function goStart() {
		if (startDiff <= 0) {
			initBetData(null, null);
		}
		else {
			$("#startTime").html($.formatSecondToTime(startDiff));
		}
		startDiff--;
	}
}

//时时彩
function countSscTimer(closeDiff, openDiff) {

	enabledOpenData();
	
	var $ssc = $('.item-ssc');
	var $fengpanHTML = $ssc.find('.ui-item-gson:eq(0)');
	$fengpanHTML.html('距离封盘：<strong class="red" id="closeTime">--:--</strong>');
	$ssc.find('.ui-item-gson:eq(1)').show();
	
	timer.dropCallback('to');
	timer.dropCallback('goStart');
	timer.onceCallback(goOpen);
	timer.onceCallback(goClose);
	
	function goClose() {
	    if(closeDiff <= 0 ){
	        disabledOpenData();
	    } else {
	    	$("#closeTime").html($.formatSecondToTime(closeDiff));
	    }
	    closeDiff--;
	}
	
	function goOpen() {
	    if (openDiff == 0 ) {
	    	refreshOpenData();
	    }
	    else {
		    if(openDiff <= 0){
		        $("#openTime").html("已封盘");
		    } else {
		        $("#openTime").html($.formatSecondToTime(openDiff));
		    }
	    }
	    openDiff--;
	}
}

//禁用所有表单
function disabledOpenData(){
	$("#closeTime").html("已封盘");
	$("#betForm input[type='text']").each(function(index,value){
		$(this).prop("disabled","true");
		$("#pl_"+$(this).attr("id")).removeClass("ui-active");
	});
	$("#betForm input[type='checkbox']").each(function(index,value){
		$(this).prop("disabled","true");
	});
	$("#betForm input[type='radio']").each(function(index,value){
		$(this).prop("disabled","true");
	});
}

function refreshOpenData() {
	enabledOpenData();
	getOpenResultInfo($("#gameBetName").attr('gpid'),$("#typeBetName").attr('typeid'), function (data, gpid, typeid) {
		callbackWhenOpenResultFinished(data, gpid, typeid);
	});
}
 
//启用所有表单 
function enabledOpenData(){
	$("#betForm input[type='text']").each(function(index,value){
		$(this).removeAttr("disabled");
	});
	$("#betForm input[type='checkbox']").each(function(index,value){
		$(this).removeAttr("disabled");
	});
	$("#betForm input[type='radio']").each(function(index,value){
		$(this).removeAttr("disabled");
	});
}
 
//获取余额
function balance(){
	$.ajax({
		url: rootPath + "/cp/LottoGetBalance",
		timeout : 30000,
		async: true,
		dataType:"json",
		type: "POST",
		success: function (data){
			if(data!=null){
				$("#userBalance").html(data.balance);
				userMoney = data.balance;
			}
		},
		error : function(){
			$("#userBalance").html(0.0);
		} 
	});
}

function changeLeftMenu(index){
	if(index==0){
		
		Core.SetTemplate($("#left_menu_0").attr("tmpid"));
		
		$("#left_menu_0").parent().addClass("ui-active");
		$("#left_menu_1").parent().removeClass("ui-active");
	}else{
		
		$("#left_menu_0").parent().removeClass("ui-active");
		$("#left_menu_1").parent().addClass("ui-active");
		
		Core.SetTemplate("transRecord");
	}
}

function openGameRule(type) {
	
	if ($('.loading').size() > 0) return ;
	if ($ruleMain.is(':visible') || type == 'hide') {
		$ruleMain.hide();
		$('#leftHtml').show();
		$('#rightHtml .lotto-main').show();
	}
	else {
		$ruleMain.show();
		$('#leftHtml').hide();
		$('#rightHtml .lotto-main').hide();
	}
	
	window.parent.postMessage(JSON.stringify({type: 'load_finish'}), '*');
}


