
$(function() {
	getNewRecord();
	if(internalRecordLeft!=null){
		window.clearInterval(internalRecordLeft);
	}
	internalRecordLeft = window.setInterval("getNewRecord()",20000);
});

function getNewRecord(){
	$.ajax({
		url: rootPath + "/cpResult/getNewsResult/"+gpid_temp,
		timeout : 30000,
		async: false,
		dataType:"json",
		type: "GET",
		success: function (obj) {
			if(obj.state=="1"){
				var datas = obj.data;
				var innerHtml = "<div class=\"pure-g\"  >";
				for (var i = 0; i < datas.length; i++){
					var resuls = datas[i].kjjg;
					var qs = datas[i].qs;
					if(qs.indexOf('-')!=-1){
						qs= qs.substr(qs.indexOf('-')+1);
					}
					var color = 'rec-gray';
					if(datas[i].code=='BJKL8'|| datas[i].code=='CAKENO'){
						var dataArr = datas[i].kjjg.split(',');
						var d1q = dataArr[0];
						var d2q = dataArr[1];
						var d3q = dataArr[2];
						var total =  parseInt(d1q)+ parseInt(d2q)+ parseInt(d3q);
						d1q = '<span class="rec rec-small '+color+'">'+d1q+'</span>';
						d2q = '<span class="rec rec-small '+color+'">'+d2q+'</span>';
						d3q = '<span class="rec rec-small '+color+'">'+d3q+'</span>';
						color = getColorFromTotal(total);
						total = '<span class="rec rec-small '+color+'">'+total+'</span>';
						resuls = d1q +'<span class="symbol">+</span>'+d2q+'<span class="symbol">+</span>'+d3q+'<span class="symbol">=</span>'+ total;
					}
					
					innerHtml += "<div class=\"pure-u-9-24\"><div class=\"ui-item-child\" style=\"margin-left: 1px;text-align: left;font-size: 8px;\"><div class=\"middle-text font1\">"+qs+"</div></div></div>";
					innerHtml += "<div class=\"pure-u-15-24\" ><div class=\"ui-item-child\" style=\"margin-left: 1px;text-align: left;font-size: 8px;\"><div class=\"middle-text font1 \">"+resuls+"</div></div></div>";
				
				}
				innerHtml+="</div>";
				$("#new_number").html(innerHtml);
				 
			}
		}
	});
	
}
