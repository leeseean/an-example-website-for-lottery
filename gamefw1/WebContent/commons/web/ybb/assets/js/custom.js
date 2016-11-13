// Plugins
// swfobject
var el = document.getElementById("logo-swf");
swfobject.embedSWF(root + "/web/ybb/assets/img/logo.swf", el, 290, 130, 10,
		null, null, {
			wmode : 'transparent'
		});
var el = document.getElementById("ban-swf");
swfobject.embedSWF(root + "/web/ybb/assets/img/ban.swf", el, 950, 434, 10,
		null, null, {
			wmode : 'transparent'
		});
// YBB-MD-CS
// document.writeln("<div class=\"ybb-md-cs\">");
// document.writeln("<div class=\"btn-open\"><a
// href=\"javascript:void(0);\"></a></div>");
// document.writeln("<div class=\"ybb-md-ct\">");
// document.writeln("<ul>");
// document.writeln("<li class=\"item-live center\"><a
// href=\""+web_online_contact+"\" target=\"_blank\">在线客服</a></li>");
// document.writeln("<li class=\"item-signup center\"><a
// href=\"/register\">免费开户</a></li>");
// document.writeln("<li class=\"item-promos center\"><a
// href=\"/goPageCenter?code=promos\">优惠活动</a></li>");
// document.writeln("<li class=\"item-qq center\"><a
// href=\"tencent://message/?uin="+web_qq_contact+"&amp;Site=web&amp;Menu=yes\"
// target=\"_blank\">QQ客服</a></li>");
// document.writeln("</ul>");
// document.writeln("<a href=\"javascript:void(0);\" class=\"btn-close\"></a>");
// document.writeln("</div>");
// document.writeln("</div>");
 $(function(){
 $('.ybb-md-cs .btn-open').click(function(){
 $(this).hide();
 $('.ybb-md-cs .ybb-md-ct').animate({ right: '0' });
 });
 $('.ybb-md-cs .btn-close').click(function(){
 $('.ybb-md-cs .ybb-md-ct').animate({ right: '-148px' });
 $('.ybb-md-cs .btn-open').fadeIn(2000);
 });
 });
jQuery(".gonggaolist").slide({
	titCell : ".hd ul",
	mainCell : ".bd ul",
	autoPage : true,
	effect : "topLoop",
	autoPlay : true,
	trigger : "click"
});
$('.ybb-try').slide({
	titCell : '.ybb-md-tt li',
	mainCell : '.ybb-md-ct ul',
	trigger : 'click'
});
// YBB-JS-Home
function setFirst(sURL) {
	try {
		document.body.style.behavior = "url(#default#homepage)";
		document.body.setHomePage(sURL);
	} catch (e) {
		if (window.netscape) {
			try {
				netscape.security.PrivilegeManager
						.enablePrivilege("UniversalXPConnect");
			} catch (e) {
				alert("抱歉，此操作被浏览器拒绝！\n\r请在浏览器地址栏输入“about:config”并回车\n\r然后将 [signed.applets.codebase_principal_support]的值设置为'true',双击即可。");
			}
		} else {
			alert("抱歉，您的浏览器不支持，请按照下面步骤操作：\n\r1.打开浏览器设置。\n\r2.点击设置网页。\n\r3.输入："
					+ sURL + "点击确定。");
		}
	}
}
// YBB-JS-Fav
function bookMarksite(sURL, sTitle) {
	try {
		window.external.addFavorite(sURL, sTitle);
	} catch (e) {
		try {
			window.sidebar.addPanel(sTitle, sURL, '');
		} catch (e) {
			alert("抱歉，您所使用的浏览器无法完成此操作。\n\r加入收藏失败，请使用Ctrl+D进行添加");
		}
	}
}
$(function() {
	// 圖片 淡出效果
	$('.fade > a').hover(function() {
		$(this).stop().animate({
			'opacity' : 0
		}, 500);
	}, function() {
		$(this).stop().animate({
			'opacity' : 1
		}, 500);
	});
});
$(function() {
	$('.nav-item-active').hover(function() {
		var arr = ($(this).attr('class')).split(' ');
		var classStr = arr[1];
		$('.nav-sub-item').hide();
		$('.nav-sub').find('.' + classStr).show();
		$('.nav-sub').stop(true).animate({
			'height' : '180px'
		});
	}, function() {
		$('.nav-sub').stop(true).animate({
			'height' : '0px'
		});
	});
	$('.nav-sub').hover(function() {
		$('.nav-sub').stop(true).animate({
			'height' : '180px'
		});
	}, function() {
		$('.nav-sub').stop(true).animate({
			'height' : '0px'
		});
	});
});
$('.float-right').float({
	position : 'rm'
});

// Live item click -> delegate to child element 
$('.live-items-main-page li').click(function () {
	var $a = $(this).find('a');
	if ($a.attr('onclick')) {
		eval($a.attr('onclick'));
	}
});

(function ($) {
	$(function () {
		$('.api-float-left').float({
			position : 'lm'
		});
		$('.api-float-right').float({
			position : 'rm'
		});
	});
	
	$('.ybb-promos .group').accordion({
		  active: false,
		  collapsible: true,
		  animate: 500,
		  heightStyle: 'content',
		  header: '.ybb-md-tt'
	}); 
})(jQuery);


