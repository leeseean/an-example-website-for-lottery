$(document).ready(function(){
	$("#menu04").hover(function(){
		$(".slotsmenu").stop(false,true).fadeIn();
		},function(){
		$(".slotsmenu").stop(false,true).fadeOut();
		})
	$(".slotsmenu").hover(function(){
		$(".slotsmenu").stop(true,true).css("display","block");
		},function(){
		$(".slotsmenu").stop(true,true).fadeOut();
		})

	$("#menu-sport").hover(function(){
		$(".sportmenu").stop(false,true).fadeIn();
		},function(){
		$(".sportmenu").stop(false,true).fadeOut();
		})
	$(".sportmenu").hover(function(){
		$(".sportmenu").stop(true,true).css("display","block");
		},function(){
		$(".sportmenu").stop(true,true).fadeOut();
		})

	$("#menu03").hover(function(){
		$(".livemenu").stop(false,true).fadeIn();
		},function(){
		$(".livemenu").stop(false,true).fadeOut();
		})
	$(".livemenu").hover(function(){
		$(".livemenu").stop(true,true).css("display","block");
		},function(){
		$(".livemenu").stop(true,true).fadeOut();
		})

	$("#menu05").hover(function(){
		$(".lotterymenu").stop(false,true).fadeIn();
		},function(){
		$(".lotterymenu").stop(false,true).fadeOut();
		})
	$(".lotterymenu").hover(function(){
		$(".lotterymenu").stop(true,true).css("display","block");
		},function(){
		$(".lotterymenu").stop(true,true).fadeOut();
		})
});

