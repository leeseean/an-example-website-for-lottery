<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/inc.jsp"%>
<!DOCTYPE html>
<html class="no-js" lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>线路测试-${ctxMap['siteName'] }</title>
<meta name="description" content="">
<link rel="stylesheet"
	href="${resourceRoot}/web/modules/line/css/normalize.css">
<link rel="stylesheet"
	href="${resourceRoot}/web/modules/line/css/font.css">
<link rel="stylesheet"
	href="${resourceRoot}/web/modules/line/css/icons/flaticon.css">
<link rel="stylesheet"
	href="${resourceRoot}/web/modules/line/css/animate.css">
<link rel="stylesheet"
	href="${resourceRoot}/web/modules/line/css/line.css">
<link rel="shortcut icon"
	href="${resourceRoot}/web/ybb/assets/img/favicon.ico"
	type="image/vnd.microsoft.icon" />
<style type="text/css">
	#logo {
		margin-top: 3px;
		width: 361px;
		height: 87px;
	}
</style>
</head>
<body>
	<div class="wrapper font-hei">
		<div class="assist-line-header">
			<div class="inner clear">
				<h1 class="assist-line-logo left">
					<a href="${ctx}/index">
						<img src="${resourceRoot}/web/ybb/assets/img/index/logo.png" />
					</a>
					
					<!-- <a href="/" id="logo"> 
						<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="280" height="75">
	              			<param name="movie" value="/ylg98/resources-1.0/commons/web/ybb/assets/img/logo.swf">
	               			<param name="quality" value="high">
	               			<param name="wmode" value="transparent">
	                		<embed src="/ylg98/resources-1.0/commons/web/ybb/assets/img/logo.swf" width="280" height="75" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" wmode="transparent">
	           			</object>
					</a> -->
				</h1>
				<div class="assist-line-header-tips ar white">
					<h2 class="pt30 s40">
						<span class="s20">欢迎莅临</span> <span class="yellow">${ctxMap['lckMainDomain']
							}</span>
					</h2>
					<h3 class="pt10 s16 gray">
						如果线路检测对您有帮助，点击： <a href="javascript:void(0);" id="addToF"
							style="color: red"
							onclick="bookMarksite('http://${ctxMap['lckMainDomain'] }',document.title);">加入收藏</a>
					</h3>
				</div>
			</div>
		</div>
		<!-- /header -->
		<div class="assist-line-container pt20">
			<div class="inner clear">
				<div class="assist-line-main left">
					<div class="content">
						<div class="assist-line-main-tips s14 yellow">${ctxMap['lckMainContent']
							}</div>
						<div class="assist-line-url pt20 white">
							<div class="speed-row clear">
								<div class="speed-item speed-ms yellow b">延迟</div>
								<div class="speed-item speed-url yellow b">网址</div>
								<div class="speed-item speed-view yellow b">操作</div>
							</div>
							<script type="text/javascript">
  var domains="${ctxMap['lckDomain'] }";
  time = 1;
  setInterval("time++",100);
  var speedUrl = domains.split(",");
  function speedBtn(){
    document.write("<form name='speedTest' onsubmit='return false;'>");
    
    for(var i=0;i<speedUrl.length;i++){
      document.write("<div class='speed-row clear'><div class='speed-item speed-ms'><span class='text'><input type='text' id='txt"+i+"' value='测速中' class='center'></span><span class='bg-alpha'></span></div><div class='speed-item speed-url'><span class='text'><input type='text' id='url"+i+"' class='center'></span><span class='bg-alpha'></span></div><div class='speed-item speed-view'><span class='text'><input type='button' value='进入网站' onclick='window.open(this.form.url"+i+".value)'></span><span class='bg-alpha'></span></div></div>");
      }
      document.write("<div class='pt20 center'><a href='javascript:void(0);' onclick='location.reload();' class='speed-start assist-line-btn white'>重新测速</a></div></form>")
  }
  speedBtn();
  function auto(url,b){
  
    document.getElementById("url"+b).value = url;
    if(time>200){
      document.getElementById("txt"+b).value = "链接超时";
    }
    else{
    
      document.getElementById("txt"+b).value = time/10+"秒";
      
    }
    b++;
  }
  function run(){
    for(var i=0;i<speedUrl.length;i++){
      document.write("<div style='display:none'><img src='http://"+speedUrl[i]+"/"+Math.random()+" width='0' height='0' onerror=auto('http://"+speedUrl[i]+"',"+i+")></div>")
  	}
  }
  run();
</script>
							</ul>
						</div>
					</div>
					<div class="bg-alpha"></div>
				</div>
				<div class="assist-line-sidebar right wow fadeInLeft"
					data-wow-delay=".9s">
					<div class="item item-one item-myip al wow fadeInLeft"
						data-wow-delay=".3s">
						<div class="text font-hei">
							<h6 class="s24 yellow">我的IP</h6>
							<div class="pt10 s20 white">${localIp }</div>
						</div>
						<div class="bg-alpha"></div>
					</div>
					<div class="item item-style-two item-cache al wow fadeInLeft"
						data-wow-delay=".4s">
						<div class="text">
							<h6 class="s24 yellow">如何清除缓存</h6>
							<p class="pt10 white">
								操作步骤：打开<span class="yellow">IE浏览器</span>：选择：<span class="yellow">工具</span>-》<span
									class="yellow">Internet选项</span>-》在选择 <span class="yellow">(删除历史浏览记录)</span>-》<span
									class="yellow">删除</span>-》<span class="yellow">重启IE</span>
							</p>
						</div>
						<div class="bg-alpha"></div>
					</div>
					<div class="item item-last item-service al wow fadeInLeft"
						data-wow-delay=".5s">
						<div class="text">
							<h6 class="s24 yellow">联系在线客服</h6>
							<p class="pt10 white">如果以上方式仍未解决您的问题，请联系官方线路人员远程协助您。</p>
							<a href="${ctxMap['msg007'] }" target="_blank"
								class="assist-line-btn mt10 center white">联系客服</a>
						</div>
						<div class="bg-alpha"></div>
					</div>
					<c:if test="${!empty ctxMap['lckOtherContent'] }">
						<div class="item item-style-two item-cache al wow fadeInLeft"
							data-wow-delay=".4s">
							<div class="text">${ctxMap['lckOtherContent'] }</div>
							<div class="bg-alpha"></div>
						</div>
					</c:if>
				</div>
				<!-- /sidebar -->
			</div>
		</div>
		<!-- /container -->
		<div class="assist-line-footer">
			<div class="inner">
				<div class="assist-line-contact pt20">
					<ul class="clear">
						<li class="clear"><i class="left flaticon-qq3 s60 white"></i>
							<h6 class="left pl10 s16 al white">
								QQ客服<br>
								<a style="color: white"
									href="tencent://message/?uin=${ctxMap['siteQq'] }&Site=web&Menu=yes"
									target="_blank">${ctxMap['siteQq'] }</a>
							</h6></li>
						<li class="clear"><i class="left flaticon-2440 s60 white"></i>
							<h6 class="left pl10 s16 al white">
								免费热线<br>${ctxMap['siteTel'] }
							</h6></li>
						<li class="clear"><i class="left flaticon-phone72 s60 white"></i>
							<h6 class="left pl10 s16 al white">
								国际电话<br>${ctxMap['siteMobile'] }
							</h6></li>
						<li class="clear"><i class="left flaticon-chat81 s60 white"></i>
							<h6 class="left pl10 s16 al white">
								<a href="${ctxMap['msg007'] }" style="color: white"
									target="_blank">在线客服<br>7x24小时为你服务</a>
							</h6></li>
					</ul>
				</div>
				<div class="assist-line-copyright">
					<p class="pt30 gray">${ctxMap['lckFootContent'] }</p>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="${resourceRoot}/web/ybb/common/js/jquery.min.js"></script>
<script src="${resourceRoot}/web/ybb/common/js/wow.min.js"></script>
<script>
    new WOW().init();
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
  </script>
</html>