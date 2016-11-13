$(function(){
			$.ajax({
		        type: "post",
		        url:  rootPath + "/cp/szzhbetTemplate",
		        data:{sz:"sz"},
		        dataType: "text",
		        error: function(a, c, e) {
		        	alert("服务忙,请稍后再试！");
		        },
		        success: function(data) {
		        	$('#SZZH').append(data);
		        },
		        complete: function(a, c) {
		        	
		        }
		    });
		
		
			$.ajax({
		        type: "post",
		        url:  rootPath + "/cp/szzhbetTemplate",
		        data:{sz:"bsgdw"},
		        dataType: "text",
		        error: function(a, c, e) {
		        	//alert("服务忙,请稍后再试！");
		        },
		        success: function(data) {
		        	$('#BSGDW').append(data);
		        	
		        },
		        complete: function(a, c) {
		        	f1();
		        }
		    });
		
		function f1(){
			$("#betForm .ui-item-son").each(function(value){
        		var id =$(this).attr("id");
        	 	if( typeof(id) != "undefined" && id.indexOf("pl_")!=-1 ){
        	 		var cid = id.substring(3);
        			$(this).on("click", function(){
        				var fastbet_input = $("#fastbet_input").val();
        				if($(this).hasClass("ui-active")){
        				    $(this).removeClass("ui-active");
        				    $("#"+cid).val("");
        				}else{
        				    $(this).addClass("ui-active"); 
        				    $("#"+cid).val(fastbet_input);
        				}
        			});
        		}
        	});
		}
		
}); 


