$(function(){
	$("#betForm .ui-item-son").each(function(value){
		var id =$(this).attr("id");
	 	if( typeof(id) != "undefined" && id.indexOf("pl_")!=-1 ){
	 		var cid = id.substring(3);
			$(this).on("click", function(){
				var fastbet_input = $("#fastbet_input").val();
				 var $input = $("#"+cid);
				 if ($input.is(":disabled")) {
				 	return ;
				 }
				 
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
	
	$('.sub-mod-head-links li').click(function () {
		$(this).addClass('active').siblings().removeClass('active');
		
		$(this).parent().siblings('.ui-table').animate({
			opacity: 0
		},  {
			duration: 200,
			always: function () {
				$(this).css({opacity: 1});
			}
		});
		
		var $form = $(this).parents('#betForm');
		cp_cateCode_temp=  $form.find('.ui-tabs-head .ui-item.ui-active > a').data('url').substring(1);
		cp_xzlxCode_temp= $(this).find('a').data('url').substring(1);
		refreshPL();
		
	});
	
});