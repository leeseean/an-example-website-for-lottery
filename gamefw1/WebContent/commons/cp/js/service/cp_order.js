
refreshPL();
//赔率
function refreshPL() {
	var url=rootPath+"/cp/server";
	url = url+"?tim="+(new Date()).getTime();
	$.ajax({
        type: 'post',
        async:true,
        dataType: 'json',
        url: url,
      	data:{code:cp_gameTypeCode_temp,code2:cp_typeCode_temp,code3:cp_cateCode_temp,code4:cp_xzlxCode_temp},
        timeout: 20000,
        success: init
    });
	if(reTimeObj!=null){
    	window.clearInterval(reTimeObj);
    }
    
    reTimeObj=window.setInterval("refreshPL()", refresh_pl_time);
}
function init(data) {
	try{
		if(!data.rs){
			return;
		}
		var result = data.datas;
		$.each(result,function(index,rs){
			var obj=$("#pl_"+rs.id);
			var old=obj.text();
	        var rate=rs.pl;
			obj.text(rate);	
			if(parseFloat(old)!=parseFloat(rate)){
				//obj.addClass("ui-refresh");
			}else{
				//obj.removeClass("ui-refresh");
			}
		});
    }catch(e){
    	alert(e.message);
    }
}

//常规订单
function createWebOrder(){
	if(!webUser){
		showMessage("提示","请先登录！");
		return;
	}
	var total=0;
	var cidArr = new Array();
	var xzjeArr = new Array();
	$("#betForm input[type='text'][name='number[]']").each(function(){
 
		if($.trim($(this).val())!=''){
			total++;
			cidArr.push($(this).attr("id"));
			xzjeArr.push($(this).val());
		}
	});
	
	if(total<=0){
		showMessage("提示","请选择需要下注的号码！");
		return;
	}
	
	var json = "{uid: '{0}', rate: '{1}', xzje: '{2}', number:'{3}'}";
	var buffer = "";
	var trHtml = "";
	var descBuff = "";
	var _typeName = "";
	var totalMoney = 0;
	
	var tsMsg = "";
	$.each(cidArr, function(index, _cid) {
		_xzje = xzjeArr[index];
		var _rate=$("#pl_"+_cid).html();
		var _nameArr = _cid.split('-');
		_typeName = typeMap[''+_nameArr[0]+''];
		var _number = numberMap[''+_nameArr[2]+''];
		var _cateName = cateMap[''+_nameArr[1]+''];
		if(_nameArr[1]=='KL8TM' && _number=='特码包三'){
			var tmsb1 = $("#tmsb1").val();
			var tmsb2 = $("#tmsb2").val();
			var tmsb3 = $("#tmsb3").val();
			if((tmsb1==null || tmsb1=='') || (tmsb2==null || tmsb2=='') || (tmsb3==null || tmsb3=='')){
				tsMsg = "特码包三中选择的号码不能为空！";
				return;
			}
			
			_number = tmsb1+","+tmsb2+","+tmsb3;
			_cateName = "特码包三";
			if(tmsb1==tmsb2||tmsb1==tmsb3||tmsb2==tmsb3){
				tsMsg = "特码包三中选择的三个号码不能一样！";
				return;
			}
		}
		
		if(parseFloat(_xzje)<parseFloat(minMoney)){
			tsMsg = "单笔最小投注金额为"+minMoney+"元!";
			return;
		}
		if(parseFloat(_xzje)>parseFloat(maxMoney)){
			tsMsg ="单笔最大投注金额为"+maxMoney+"元!";
			return;
		}
		
		totalMoney += parseFloat(_xzje);
 
		if(buffer!=""){
			buffer+=",";
    	}
		buffer += json.format(_cid, _rate,_xzje,_number);
		trHtml += getTrTtml(_cateName, _number, _rate, _xzje);
		
	});
	if(tsMsg!=""){
		showMessage("提示",tsMsg);
		return;
	}
 
	if (totalMoney > userMoney) {
		showMessage("提示","余额不足！");
		return;
	}
 
	doOrderSubmitShow(getOrderDivHtml(total, totalMoney, trHtml), buffer,"normal");
 
}

// 串关订单
function createSingleGroupOrder(){
	if(!webUser){
		showMessage("提示","请先登录！");
		return;
	}
	var xzje = $("#fastbet_input").val();
	if($.trim(xzje)==''){
		showMessage("提示","请输入下注金额！");
		return;
	}
	if(parseFloat(xzje)<parseFloat(minMoney)){
		showMessage("提示","单笔最小投注金额："+minMoney+"元!");
		return;
	}
	if(parseFloat(xzje)>parseFloat(maxMoney)){
		showMessage("提示","单笔最大投注金额："+minMoney+"元!");
		return;
	}
	
	
	var total=0;
	$("#betForm input[type='radio']:checked").each(function(){
		total++;
	});
	if(total<2){
		showMessage("提示","请任选2-6个号码为一投注组合！");
		return;
	}
	var totalMoney = parseFloat(xzje)*total;
	if (totalMoney > userMoney) {
		showMessage("提示","余额不足！");
		return;
		
	}
	
	var json = "{uid: '{0}', rate: '{1}', xzje: '{2}', number:'{3}'}";
	var buffer = "";
	var trHtml = "";
	var descBuff = "";
	var _typeName = "";
	var rateTotal =1;
	$("#betForm input[type='radio']:checked").each(function(){
		var cid = $(this).attr("id");
		var _rate=$("#pl_"+cid).html();
		var _nameArr = cid.split('-');
		_typeName = typeMap[''+_nameArr[0]+''];
		var _number = numberMap[''+_nameArr[2]+''];
		var _desc = cateMap[''+_nameArr[1]+'']+''+_number;
		
		if(buffer!=""){
			buffer+=",";
    	}
		if(descBuff!=""){
			descBuff +=",";
		}
		descBuff +=_desc;
		buffer += json.format(cid, _rate,xzje,_number);
		rateTotal = parseFloat(_rate)*rateTotal;
	});
	var rate = parseFloat(rateTotal).toFixed(2);
	
	trHtml += getTrTtml(_typeName,descBuff,rate,xzje);
 
	doOrderSubmitShow(getOrderDivHtml(total, totalMoney, trHtml), buffer,"cl");
 
}

//跳转到组合订单页面
function goMulitOrder(multilen,cpTypeCode,cpCateCode){
	if(!webUser){
		showMessage("提示","请先登录！");
		return;
	}
	var _xzje = $("#fastbet_input").val();
	if($.trim(_xzje)==''){
		showMessage("提示","请输入下注金额！");
		return;
	}
	if(parseFloat(_xzje)<parseFloat(minMoney)){
		showMessage("提示","单笔最小投注金额："+minMoney+"元!");
		return;
	}
	if(parseFloat(_xzje)>parseFloat(maxMoney)){
		showMessage("提示","单笔最大投注金额："+minMoney+"元!");
		return;
	}
	var cidArr = new Array();
	$("#betForm input[type='checkbox']:checked").each(function(){
		cidArr.push($(this).attr("id"));
	});
 
	if(cidArr.length<=0){
		showMessage("提示","请选择下注号码！");
		return;
	}
	var cids = cidArr.toString();
	var json = "{token: '{0}', xzje: {1},typeCode:'{2}',cateCode:'{3}'}";
	var trHtml = "";
	$.ajax({
	    url:rootPath+"/cpOrder/goMultiGroupOrder", 
	    data:{"cids":cids,"gameCode":cp_gameTypeCode_temp,"multilen":multilen},
	    type:'post',
	    cache:false,
	    timeout : 50000,
	    dataType:'json',
	    success:function(data){
	    	var orderList = data.orderList;
	    	if(orderList.length>0){
	    		for(var i=0;i<orderList.length;i++){
	    			var _rate = orderList[i].rate;
	    			var _number = orderList[i].number;
	    			var _cateName = orderList[i].cateName;
	    			trHtml += getTrTtml(_cateName, _number, _rate, _xzje);
	    		}
	    		var buffer = json.format(data.token,_xzje,cpTypeCode,cpCateCode);
	    		var totalMoney =  parseFloat(_xzje)*orderList.length;
	    		doOrderSubmitShow(getOrderDivHtml(orderList.length, totalMoney, trHtml), buffer,"mul");
	    	}else{
	    		showMessage("提示","下注异常！");
	    	}
	     }, 
	     error : function(){
	    	 showMessage("提示","下注异常！");
	     }    
	});
 
}

function inIframe(){
	try {
		return window.self !== window.top;
	}
	catch (e) {
		return false;
	}
}

//弹出框
function showMessage(title,content){
	if (inIframe()) {
		var data = {
				type: 'showMessage',
				title: title,
				content: content
		};
		window.parent.postMessage(JSON.stringify(data), '*');
	}
	else {
		art.dialog({
			title:title,
			id: 'message_order',
			fixed: true,
		    lock: true,
		    background: '#000000', // 背景色
		    opacity: 0.7,	// 透明度
		    content: content,
		    ok: function (){
		        return true;
		    }
		});
	}

}

//tr
function getTrTtml(name,num,rate,xzje){
 
	
	var trHtml = "<tr><td>"+name+"："+num+"</td><td>"+rate+"</td><td>"+xzje+"</td></tr>";
	return trHtml;
}

 
//订单展示拼装
function getOrderDivHtml(total,totalMoney,trHtml){
	 var divHtml = "<div id = 'showOrder' width = '100%' style='padding: 1px;width: 400px;min-width: 300px;min-height: 50px;max-height: 300px;overflow-y: auto;'>" +
		"<table class='table_data table-tj' width='100%'><tr><th style = 'background:#9CF;width:100%' colspan= '3'>适应赔率" +
		"<input id = 'oddsAdapt' type='checkbox' checked>注单："+total+"条,投注总金额:"+totalMoney+"</th></tr>"; 
	 divHtml += "<tr><td>号 码</td><td>赔 率</td><td>金 额</td></tr>";
	divHtml+=trHtml;
	divHtml += "</table></div>";
	return divHtml;
}

window.onmessage = function (e) {
	try {
		var data = e.data;
		if (data.type == 'showHK6DivHtmlMessage') {
			if (data.cancel == 'true') {
				$("#resetColor").trigger("click");
			}
			else if (data.ok == 'true') {
				var jsons = data.jsons;
				var orderFlag = data.orderFlag;
				
				if($("input[id = 'oddsAdapt']").attr("checked")=='checked'){
					oddsAdapt = true;
				}else{
					oddsAdapt = false;
				}
				submitData(jsons,orderFlag);
				
		        return true;
			}
		}
		else {
			if (data.cancel == 'true') {
				clearBetForm();
			}
			else if (data.ok == 'true') {
				var jsons = data.jsons;
				var orderFlag = data.orderFlag;
				//是否默认适应赔率
				if($("input[id = 'oddsAdapt']").attr("checked")=='checked'){
					oddsAdapt = true;
				}else{
					oddsAdapt = false;
				}
				//是否正确注单(注单验证)
				//shortPrompt("正在提交注单",1);
				submitData(jsons,orderFlag);
			}
		}

	}
	catch (e) {
		//
	}
}

//订单提交展示
function doOrderSubmitShow(divHtml,jsons,orderFlag){
	if (inIframe()) {
		var data = {
				type: 'showDivHtmlMessage',
				title: "下注明细（请确认注单）",
				content: divHtml,
				jsons: jsons,
				orderFlag: orderFlag
		};
		window.parent.postMessage(JSON.stringify(data), '*');
	}
	else {
		art.dialog({
			title:"下注明细（请确认注单）",
			id: 'zhudanId',
			fixed: true,
		    lock: true,
		    background: '#000000', // 背景色
		    opacity: 0.7,	// 透明度
		    content: divHtml,
		    ok: function (){
				//是否默认适应赔率
				if($("input[id = 'oddsAdapt']").attr("checked")=='checked'){
					oddsAdapt = true;
				}else{
					oddsAdapt = false;
				}
				//是否正确注单(注单验证)
				//shortPrompt("正在提交注单",1);
				submitData(jsons,orderFlag);
				
		        return true;
		    },
		    cancel: function(){
		    	clearBetForm();
		    }
		});
	}
}

//提交异步请求数据到action
function submitData(jsons,orderFlag){
	if(!webUser){
		showMessage("提示","请先登录！");
		return;
	}
	if(jsons!=""){
    	jsons="["+jsons+"]";
	}
	startLoadingMask();
	$.ajax({
	    url:rootPath+"/cpOrder/saveOrder", 
	    data:{"jsons":jsons,"gameCode":cp_gameTypeCode_temp,"orderFlag":orderFlag},
	    type:'post',
	    cache:false,
	    timeout : 50000,
	    dataType:'text',
	    success:function(data){
	    	showMessage("提示",data);
	    	clearBetForm();
	    	endLoadingMask();
	    	

	    	 
	    	$.ajax({
	    		url: rootPath + "/cpOrder/getCpOrderRecord/"+$("#gameBetName").attr("gpid"),
	    		timeout : 30000,
	    		async: false,
	    		dataType:"json",
	    		type: "GET",
	    		success: function (data) {
	    			if(data!=null && data.length>0){
	    				var innerHtml = "<div class=\"pure-g\"  >";
	    				for(var i=0;i<data.length;i++){
	    					innerHtml += "<div class=\"pure-u-7-24\"><div class=\"ui-item-child\" style=\"margin-left: 1px;text-align: left;font-size: 8px;\"><div class=\"middle-text\">"+data[i].QS+"</div></div></div>";
	    					innerHtml += "<div class=\"pure-u-17-24\" ><div class=\"ui-item-child\" style=\"margin-left: 1px;text-align: left;font-size: 8px;\"><div class=\"middle-text\">"+data[i].CONTENT+"</div></div></div>";
	    				}
	    				innerHtml+="</div>";
	    				$("#new_order").html(innerHtml);
	    				$("#new_order").show();
	    				$("#no_order").hide();
	    			}else{
	    				$("#new_order").hide();
	    				$("#no_order").show();
	    			}
	    		}
	    	});
	     

	    	
	    	
	    	
	     }, 
	     error : function(){
	    	showMessage("提示","下注异常！");
		    endLoadingMask();
	     }    
	});
	
}


function clearBetForm(){
	sel = ",";//快乐十分连码清空
	$("#betForm input[type='text']").each(function(){
		$(this).val("");
		var id =$(this).attr("id");
		if($("#pl_"+id).hasClass("ui-active")){
			$("#pl_"+id).removeClass("ui-active");
		}
	});
	$("#betForm input[type='radio']").each(function(){
		$(this).removeAttr('checked');
	});
	$("#betForm input[type='checkbox']").each(function(){
		$(this).removeAttr('checked');
	});
	
	$("#fastbet_input").val("");
	$("#back_fastbet_input").val("");
	$("#betInfo").html("");
	$("#betRate").html("");
	
 
	
	$("#QSW_betInfo").html("");
	$("#QSW_betRate").html("");
	
	$("#ZSW_betInfo").html("");
	$("#ZSW_betRate").html("");
	
	$("#HSW_betInfo").html("");
	$("#HSW_betRate").html("");
	
	$(window).trigger('clearedResetForm');
}


 


