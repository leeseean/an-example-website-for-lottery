 
var redWave = ['01','02','07','08','12','13','18','19','23','24','29','30','34','35','40','45','46'];
var blueWave = ['03','04','09','10','14','15','20','25','26','31','36','37','41','42','47','48'];
var greenWave = ['05','06','11','16','17','21','22','27','28','32','33','38','39','43','44','49'];

var great = ['25','26','27','28','29','30','31','32','33','34','35','36','37','38','39','40','41','42','43','44','45','46','47','48'];
var closeGreat = ['07','08','09','16','17','18','19','25','26','27','28','29','34','35','36','37','38','39','43','44','45','46','47','48'];

var small  = ['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24'];
var closeSmall = ["01","02","03","04","05","06","10","11","12","13","14","15","20","21","22","23","24","30","31","32","33","40","41","42"];

var single = ['1','3','5','7','9','11','13','15','17','19','21','23','25','27','29','31','33','35','37','39','41','43','45','47'];
var closeSingle = ['1','3','5','7','9','10','12','14','16','18','21','23','25','27','29','30','32','34','36','38','41','43','45','47'];

var double = ['2','4','6','8','10','12','14','16','18','20','22','24','26','28','30','32','34','36','38','40','42','44','46','48'];
var closeDouble = ['2','4','6','8','11','13','15','17','19','20','22','24','26','28','31','33','35','37','39','40','42','44','46','48'];

var redSingle = ["01","07","13","19","23","29","35","45"];
var redDouble = ["02","08","12","18","24","30","34","40","46"];
var redGreat = ["29","30","34","35","40","45","46"];

var greatSingle = ["25","27","29","31","33","35","37","39","41","43","45","47"];
var greatDouble = ["26","28","30","32","34","36","38","40","42","44","46","48"];
var smallSingle = ["01","03","05","07","09","11","13","15","17","19","21","23"];

 
var tail_0 = ["10","20","30","40"];
var tail_1 = ["01","11","21","31","41"];
var tail_2 = ["02","12","22","32","42"];

 
$(function(){
	
	$("#betQuick div div div").click(function(){
		$(this).toggleClass("ui-active");
	});
	
	$("label input[type='checkbox']").click(function(){
		var id = $(this).attr("id");
		var data = getData(id);
		var flag = $(this).is(':checked');
		$("#row_number div div").each(function(){
			for(var i = 0;i < data.length;i++ ){ 
			    if(parseInt($(this).text()) == parseInt(data[i])){
			    	if(flag){
			    		$(this).addClass("ui-active");
			    	}else{
			    		$(this).removeClass("ui-active");
			    	}
				}
	 	    }
		});
	});
	
 
	$("#resetColor").click(function(){
 
		$("label input[type='checkbox']").each(function(){
			$(this).attr("checked",false);
		});
 
		$("#row_number div div").each(function(){
	    	$(this).removeClass("ui-active");
		});
 
		$("#fastbet_money_input").val("");
	});
	
 
	$("#selectAll").click(function(){
		var flag = $(this).is(':checked');
		$("#row_number div div").each(function(){
	    	if(flag){
	    		$(this).addClass("ui-active");
	    	}else{
	    		$(this).removeClass("ui-active");
	    	}
		});
	});
	
	
	$.ajax({
        type: 'post',
        async:true,
        dataType: 'json',
        url: rootPath+"/cp/server",
      	data:{code:"HK6",code2:"TM",code3:"TM_A"},
        timeout: 5000,
        success: function (data) {
        	if(!data.rs){
    			return;
    		}
    		var result = data.datas;
    		$.each(result,function(index,rs){
    			
    			$("#FASTNUM_"+rs.number).attr("plid",rs.pl);
    			$("#FASTNUM_"+rs.number).attr("pcid",rs.id);
    		 
    		});
        }
    });
});
 
function createHK6FastBetOrder(){
	console.log('createHK6FastBetOrder');
	 if(!webUser){
		 showMessage("提示","请先登录");
		 return;
	 }
	 var json = "{uid: '{0}', rate: '{1}', xzje: '{2}', number:'{3}'}";
	 var jsons = "";
	 var total=0;
	 var trHtml="";
	 var xzje = $("#fastbet_money_input").val();
	 if(xzje=='' || parseFloat(xzje)<=0){
		 showMessage("提示","请输入金额！");
		 return;
	 }
	 $("#row_number div div.ui-active").each(function(){
		 total++;
	 });
	 if(total<=0){
		 showMessage("提示","请选择下注类型！");
		 return;
	 }
	var totalMoney =0;
	var errmsg = "";
	$("#row_number div div.ui-active").each(function(){
		console.log($(this).attr("pcid"));
		var id = $(this).attr("pcid");
		var rate = $(this).attr("plid");
		var num = $(this).text();
		if(parseFloat(xzje)<parseFloat(minMoney)||parseFloat(xzje)>parseFloat(maxMoney)){
    		errmsg="下注金额不符合要求！\n" +"最小投注金额："+minMoney+"元\n";
    		showMessage("提示",errmsg);
    		return;
    	}
		if(parseFloat(xzje)>parseFloat(maxMoney)){
    		errmsg="下注金额不符合要求！\n 最大投注金额:"+maxMoney+"元";
    		showMessage("提示",errmsg);
    		return;
    	}
	  	if(jsons!=""){
    		jsons+=",";
    	}
        jsons += json.format(id, rate,xzje,num);
        totalMoney+=parseFloat(xzje);
        trHtml += getTrTtml("特码A",num,rate,xzje);
		
	});
	if (totalMoney > userMoney) {
		errmsg = "余额不足!";
	} else if (total > maxSumMoney) {
		errmsg = "单笔交易最大投注额为:" + maxSumMoney + "元!";
	}
	if (errmsg == "") {
		var divHtml = getOrderDivHtml(total, totalMoney, trHtml);
		doHK6OrderSubmitShow(divHtml, jsons,"normal");
	} else {
		showMessage("提示", errmsg);
	}
}

function doHK6OrderSubmitShow(divHtml,jsons,orderFlag){
	if (inIframe()) {
		var data = {
				type: 'showHK6DivHtmlMessage',
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
		    background: '#000000',
		    opacity: 0.7,
		    content: divHtml,
		    ok: function (){
				if($("input[id = 'oddsAdapt']").attr("checked")=='checked'){
					oddsAdapt = true;
				}else{
					oddsAdapt = false;
				}
				submitData(jsons,orderFlag);
				
		        return true;
		    },
		    cancel: function(){
		    	$("#resetColor").trigger("click");
		    }
		});
	}

	$("#resetColor").trigger("click");
}

function getData(id)
{
	var data = [];
	if(id == 'redWave'){
		data = redWave;
	}else if(id == 'blueWave'){
		data = blueWave;
	}else if(id == 'greenWave'){
		data = greenWave;
	}else if(id == 'great'){
		data = great;
	}else if(id == 'small'){
		data = small;
	}else if(id == 'single'){
		data = single;
	}else if(id == 'closeSingle'){
		data = closeSingle;
	}else if(id == 'double'){
		data = double;
	}else if(id == 'closeDouble'){
		data = closeDouble;
	}else if(id == 'mouse'){
		data = shengXiaoMap.SHU;
	}else if(id == 'cow'){
		data = shengXiaoMap.NIU;
	}else if(id == 'tiger'){
		data = shengXiaoMap.HU;
	}else if(id == 'rabbit'){
		data = shengXiaoMap.TU;
	}else if(id == 'dragon'){
		data = shengXiaoMap.LONG;
	}else if(id == 'snake'){
		data = shengXiaoMap.SHE;
	}else if(id == 'horse'){
		data = shengXiaoMap.MA;
	}else if(id == 'sheep'){
		data = shengXiaoMap.YANG;
	}else if(id == 'monkey'){
		data = shengXiaoMap.HOU;
	}else if(id == 'chicken'){
		data = shengXiaoMap.JI;
	}else if(id == 'dog'){
		data = shengXiaoMap.GOU;
	}else if(id == 'pig'){
		data = shengXiaoMap.ZHU;
	}else if(id == 'closeGreat'){
		data = closeGreat;
	}else if(id == 'closeSmall'){
		data = closeSmall;
	}else if(id == 'redGreat'){
		data = redGreat;
	}else if(id == 'redDouble'){
		data = redDouble;
	}else if(id == 'redSingle'){
		data = redSingle;
	}else if(id == 'greatSingle'){
		data = greatSingle;
	}else if(id == 'greatDouble'){
		data = greatDouble;
	}else if(id == 'smallSingle'){
		data = smallSingle;
	}else if(id == 'tail_0'){
		data = tail_0;
	}else if(id == 'tail_1'){
		data = tail_1;
	}else if(id == 'tail_2'){
		data = tail_2;
	}
	return data;
}


$("#fastbet_money_input").on("keyup click keydown", function(){
	
	if(!isInteger($(this).val())){
		$("#fastbet_money_input").val("");
	}
 
});
