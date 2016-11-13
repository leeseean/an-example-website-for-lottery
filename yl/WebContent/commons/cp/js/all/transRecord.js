 
$(function() {
 
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
 
});
 