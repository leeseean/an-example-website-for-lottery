
var cp_gameTypeCode_temp="HK6";//一级分类
var cp_typeCode_temp="TM";//二级分类
var cp_cateCode_temp="";//三级分类
var cp_xzlxCode_temp="";//四级分类

var minMoney=10;//最小单注下注金额
var maxMoney=10000;//最大单注下注金额
var maxSumMoney=1000000;//最大总下注额
var userMoney_0=50000;

var reTimeObj=null;//
var refresh_pl_time=30000;//刷新时间

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
        timeout: 5000,
        success: init
    });
    if(reTimeObj!=null){
    	window.clearTimeout(reTimeObj);
    }
    reTimeObj=setTimeout("refreshPL()", refresh_pl_time);
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
				obj.addClass("ui-refresh");
			}else{
				obj.removeClass("ui-refresh");
			}
		});
    }catch(e){
    	alert(e.message)
    }
}

//获取下注号码等信息，生成前台订单
function createWebOrder(){
	if(!webUser){
		showMessage("提示","请先登录！");
		return;
	}
	var aOdds = $("input[name='number[]'][type=text]");
	
	//var inputs=$("#"+form.id+" input[type=text]");
	 var errmsg="";
	 var game_bet_type_name=$("#gameBetName").html()+"-"+$("#typeBetName").html();//二级分类
	 var userMoney=userMoney_0;//用户余额;
	 var json = "{uid: '{0}', rate: '{1}', val: '{2}', num:'{3}'}";
	 var jsons = "";
	 var total=0;
	 var trHtml="";
	 var sumCount=0;
	    for (var i = 0; i < aOdds.length; i++) {
	    	var obj=aOdds[i];
	        if (obj.value != "" && obj.value!="0") {
	        	var id=$(obj).attr("cid");
	        	var rate=$("#pl_"+id).html();
	        	
	        	var num=$("#num_"+id).html();
	        	var xzje=obj.value;
	        	
	        	
	        	if(parseFloat(xzje)<parseFloat(minMoney)||parseFloat(xzje)>parseFloat(maxMoney)){
	        		errmsg="下注金额不符合要求！\n" +
	        				"最小单号投注金额："+minMoney+"元\n"+"最大单号投注金额:"+maxMoney+"元";
	        		
	        		break;
	        	}
	        	if(jsons!=""){
	        		jsons+=",";
	        	}
	            jsons += json.format(id, rate,xzje,num);
	            total+=parseFloat(xzje);
	            sumCount++;
	            trHtml += getTrTtml(game_bet_type_name,num,rate,xzje);
	        }
	    }
	    if(sumCount==0){
	    	return false;
	    }
	    if(total>userMoney){
	    	errmsg="余额不足!";
	    }else if(total>maxSumMoney){
	    	errmsg="单笔交易最大投注额为:"+maxSumMoney+"元!";
	    }
	    if(errmsg==""){
	    	var divHtml=getOrderDivHtml(sumCount,total,trHtml);
	    	doOrderSubmitShow(divHtml,jsons);
	    }else{
	    	showMessage("下注提醒",errmsg);
	    }
}

//弹出框
function showMessage(title,content){
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

//tr
function getTrTtml(name,num,rate,xzje){
	var trHtml = "<tr><td>"+name+"："+num+"</td><td>"+rate+"</td><td>"+xzje+"</td></tr>";
	return trHtml;
}

//订单展示拼装
function getOrderDivHtml(count,total,trHtml){
	 var divHtml = "<div id = 'showOrder' width = '100%' style='padding: 1px;width: 400px;min-width: 300px;min-height: 50px;max-height: 300px;overflow-y: auto;'>" +
		"<table class='table_data table-tj' width='100%'><tr><th style = 'background:#9CF;width:100%' colspan= '3'>适应赔率" +
		"<input id = 'oddsAdapt' type='checkbox' checked>注单："+count+"条,投注总金额:"+total+"</th></tr>"; 
	 divHtml += "<tr><td>号 码</td><td>赔 率</td><td>金 额</td></tr>";
	divHtml+=trHtml;
	divHtml += "</table></div>";
	return divHtml;
}

//订单提交展示
function doOrderSubmitShow(divHtml,jsons){
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
			submitData(jsons);
			
	        return true;
	    },
	    cancel: function(){
	    	//wholeRefresh(heartbeatVal);//取消效果
	    }
	});
}



//提交异步请求数据到action
function submitData(jsons,cfgIds){
	if(jsons!=""){
    	jsons="["+jsons+"]";
	}
	var gameTypeCode="HK6";
	var aj = $.ajax({
	    url:rootPath+"/cpOrder/saveOrder", 
	    data:{jsons:jsons,cfgIds:cfgIds,gameTypeCode:gameTypeCode},
	    type:'post',
	    cache:false,
	    timeout : 30000,
	    dataType:'json',
	    success:function(data){
	    	if(data != null){
	    		if(""!=data){
			    	  var info =data;
				      var resultCode = info.resultCode;
				        
				      switch (resultCode) {
				            case 0:
				            	{
				            		showMessage("下注提示","非法的注单请求!");
				            		break;
				            	}
				            case -1:
				                {
				            	showMessage("下注提示","您的可用额度不足！");
				                    break;
				                }
				            
				            case 400:
				                {
					            	showMessage("下注提示","注单提交失败!");
				                    break;
				                }
				            case 401:
				            	{
					        		showMessage("下注提示","该彩票已封盘，暂停投注,请先选择其他彩票投注！");
					                break;
				            	}
				            case 200:
				                {
				                    showMessage("下注提示","下注成功，请查看注单状态!");
				                    break;
				                }
				        }
			       }else{
			    	   showMessage("下注提示","请勿重复提交订单！");
			       }
	    	}else{
	    		showMessage("出错提示","无响应，请查看注单状态!");
	    	}
	     }, 
	     error : function(){
	    	 showMessage("出错提示","下注失败，连接服务异常请查看注单状态！");
	     }    
	});  
}


 


