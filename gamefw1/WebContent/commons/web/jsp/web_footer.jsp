<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<div class="footer-wrap footer-home">
<div class="footer">
	<div class="inner">
		<div class="copyright">
			<div class="one">
				<ul class="clear">
					
					<li><a href="${ctx}/goPageCenter?_0&code=help&pgSn=deposite">存取款帮助</a></li>
					<li><a href="${ctx}/goPageCenter?_1&code=help&pgSn=problem">常见问题</a></li>
					<li><a href="${ctx}/goPageCenter?_2&code=help&pgSn=cookies">合作伙伴</a></li>
					<li><a href="${ctx}/goPageCenter?_3&code=help&pgSn=about">关于我们</a></li>
					<li><a href="${ctx}/goPageCenter?_4&code=help&pgSn=contact">联系我们</a></li>
				</ul>
			</div>
			<div class="two mt5">Copyright (c) 美高梅娱乐城 Reserved</div>
		</div>
		<!-- /.casino -->
	</div>
	<!-- /.inner -->
</div>
<!-- /.footer -->
</div>
<!-- /.footer-wrap -->
 
<div class="ele-serv-btn"><a href="javascript:void(0);" class="block"></a></div>
<div class="ele-serv-body">
	<ul>
		<li><a href="http://messenger.providesupport.com/messenger/0zbzj68kpa3931cy4vhdp32s6w.html?ps_l=https%3A//admin2.providesupport.com/zh_CN/view/my-account/setup-instructions/code-test%3Bwsid%3Dijm268oguoo6zxdr69jwx7sn"
			target="_blank" class="lnk lnk-live block">在线客服</a></li>
		<li><a href="${ctx}/register" class="lnk lnk-signup block">免费开户</a></li>
		<li><a href="${ctx}/goPageCenter?code=promos" class="lnk lnk-promos block">优惠活动</a></li>
		<li><a href="tencent://message/?uin=280727555&amp;Site=web&amp;Menu=yes" target="_blank" class="lnk lnk-qq block">QQ客服</a></li>
 
	</ul>
<div class="ele-serv-close"><a href="javascript:void(0);" class="block"></a></div>
<script src="${resourceRoot}/web/ybb/common/js/superslide.min.js" type="text/javascript"></script>
<script src="${resourceRoot}/web/ybb/assets/js/plugins.js" type="text/javascript"></script>
<!--[if gt IE 9]>
<![endif]-->
<script src="${resourceRoot}/web/ybb/common/js/wow.min.js" type="text/javascript"></script>
<script>
	jQuery('.domain').slide({
		mainCell : '.domain-body ul',
		effect : 'top',
		delayTime : 100,
		autoPlay : true,
		mouseOverStop : false
	});
	jQuery('.nav').slide({
		type : 'menu',
		titCell : '.nav-item',
		targetCell : '.nav-lv2-wrap',
		titOnClassName : 'curr',
		effect : 'slideDown',
		delayTime : 300,
		triggerTime : 0,
		defaultPlay : false,
		returnDefault : true
	});
	var ary = location.href.split("_");
	jQuery('.slide-tab').slide({
		titCell : '.slide-menu li',
		mainCell : '.slide-content ul',
		titOnClassName : 'curr',
		defaultIndex : ary[1],
		trigger : 'click',
		effect : 'fade'
	});
	// wow.js
	// new WOW().init();
	// wow = new WOW({});
	//wow.init();
	$(function() {
		$('.acc-title').click(
				function() {
					$(this).addClass('curr').next('.acc-body').slideToggle(100)
							.siblings('.acc-body').slideUp('fast');
					$(this).siblings().removeClass('curr');
				});
	});
	// 設為首頁
	function setFirst(sURL) {
		try {
			document.body.style.behavior = 'url(#default#homepage)';
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
	// 加入最愛
	function bookMarksite(sURL, sTitle) {
		try {
			window.external.addFavorite(sURL, sTitle);
		} catch (e) {
			try {
				window.sidebar.addPanel(sTitle, sURL, "");
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
		// Service
		$('.ele-serv-btn').click(function() {
			$(this).fadeOut();
			$('.ele-serv-body').animate({
				right : '0px'
			});
		});
		$('.ele-serv-body .ele-serv-close').click(function() {
			$('.ele-serv-body').animate({
				right : '-148px'
			});
			$('.ele-serv-btn').fadeIn(2000);
		});
	});
</script>
 

 