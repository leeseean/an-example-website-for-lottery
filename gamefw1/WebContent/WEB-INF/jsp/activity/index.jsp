<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>抽奖首页</title>
  
  <link rel="stylesheet" media="all" href="${resourceRoot}/activity/css/lottery_index.css"></link>
  <link rel="stylesheet" href="${resourceRoot}/activity/js/artDialog/skins/default.css"></link>
  
  <script src="${resourceRoot}/activity/js/artDialog/artDialog.js?skin=default" type="text/javascript"></script>
  <script type="text/javascript" src="${resourceRoot}/activity/js/jquery.min.js"></script>
	<script type="text/javascript" src="${resourceRoot}/activity/js/jquery.min.js"></script>
	<script type="text/javascript" src="${resourceRoot}/activity/js/jquery.rotate.min.js"></script>
	<script type="text/javascript" src="${resourceRoot}/activity/js/jquery.jplayer.min.js"></script>
	<script type="text/javascript" src="${resourceRoot}/activity/js/zebra_pin.js" ></script>
	<script type="text/javascript" src="${resourceRoot}/activity/js/Xslider.js"></script>
  <script>
	var root= "${resourceRoot}";
	var rootPath = "${ctx}";
</script>
  <script type="text/javascript" src="${resourceRoot}/activity/js/lottery.js"></script>
 
  <script type="text/javascript">
  $(document).ready(function() {


    winWidth = $(window).width();
    if (winWidth < 1210) {
      document.getElementById('li1').style.width = 280 + "px";
      document.getElementById('li2').style.width = 280 + "px";
      document.getElementById('li3').style.width = 280 + "px";
      document.getElementById('li4').style.width = 280 + "px";
      document.getElementById('li5').style.width = 280 + "px";
      document.getElementById('img1').style.width = 230 + "px";
      document.getElementById('img2').style.width = 230 + "px";
      document.getElementById('img3').style.width = 230 + "px";
      document.getElementById('img4').style.width = 230 + "px";
      document.getElementById('img5').style.width = 230 + "px";
    }
    getWinningListData();

  });
  </script>
</head>

<body style='overflow:scroll;overflow-x:hidden'>
 
  <span id="dzp_model" style=" position:absolute; top:220px"></span>
  <span id="dzp_rules" style=" position:absolute; top:820px"></span>
  <div id="pin2" style="height:46px;background-color:#330304;width:100%">
    <div style="width:965px;margin:0 auto;">
      <div style="width:201px;height:43px;float:left; margin-left:30px"><img src="${ctx}${activityInfo.logoImages}" /></div>
      <ul class="menu">
        <li class="menu_li"><a href="${activityInfo.webUrl}" target="_blank">官方首页</a></li>
        <li class="menu_li"><a href="${activityInfo.messageUrl}" target="_blank">在线客服</a></li>
        <li class="menu_li"><a href="#dzp_model">抽奖转盘</a></li>
        <li class="menu_li"><a href="#dzp_rules">抽奖规则</a></li>
        <li class="menu_li"><a href="javascript:querylist();">中奖查询</a></li>
      </ul>
    </div>
  </div>
  <div style="background-color:#840000;">
    <div style="background:url(${ctx}${activityInfo.pageBgimages}) center -30px no-repeat;">
      <div style="height:260px;"></div>
      <div style="width:965px; height:610px;margin:0 auto;border:0px solid #fff;">
        <div style="width:400px;float:left; margin-left:50px; margin-top:265px;">
          <div id="msg1" style="overflow:hidden; width:270px; height:85px; margin-left:80px;text-align:left;color:#fff;">
            ${activityInfo.lottCondition}
          </div>
          <div id="msg2" style="height:70px;"></div>
          <div id=dem style="overflow:hidden; width:300px; height:130px; text-align:left;margin-left:87px;">
            <div id=dem1 style="text-align:left">
              <table id="lottery_winners_data" width="100%" border="0" cellspacing="0" cellpadding="0">
              	<tbody id="lottery_winners_tbody">
              	</tbody>
              </table>
            </div>
            <div id=dem2></div>
            <script>

			//以下方法是滚动各种钥匙的中奖信息
			var MyMar;
			function show_tzj() {
				var speed = 60;
				dem2.innerHTML = dem1.innerHTML;
				function Marquee() {
					if (dem2.scrollHeight- dem.scrollTop <= 0) {
						dem.scrollTop = 0;
					} else {
						dem.scrollTop++;
						if (dem.scrollTop > 80) {
							// scrollHeight
						}
					}
				}
				MyMar = setInterval(Marquee, speed);
				dem.onmouseover = function() {
					clearInterval(MyMar);
				};
				dem.onmouseout = function() {
					MyMar = setInterval(Marquee,speed);
				};
			}
												</script>
          </div>
        </div>
        <div style="float:left; width:480px; margin-right:30px; margin-top:10px">
          <div class="ly-plate">
          	<object width="510" height="510" align="middle" classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0" id="lottery">
				<param value="always" name="allowScriptAccess" />
				<param value="${resourceRoot}/activity/images/lottery.swf" name="movie" />
				<param value="high" name="quality" />
				<param value="total_num=${activityInfo.prizeNums}&bg=${ctx}${activityInfo.rouletteImages}&pointer=${resourceRoot}/activity/images/pointer.png&btn=${resourceRoot}/activity/images/btn.swf&style=1&auto_play=1" name="FlashVars"/>
				<param value="transparent" name="wmode"/>
				<param value="false" name="menu"/>
				<embed FlashVars="total_num=${activityInfo.prizeNums}&bg=${ctx}${activityInfo.rouletteImages}&pointer=${resourceRoot}/activity/images/pointer.png&btn=${resourceRoot}/activity/images/btn.swf&style=1&auto_play=1" width="510" height="510" align="middle" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" allowscriptaccess="always" wmode="transparent" name="lottery" menu="false" quality="high" src="${resourceRoot}/activity/images/lottery.swf"></embed>
  			</object> 
  			 <!--
  			<iframe src="${resourceRoot}/activity/jsp/demo4.html" scrolling="no" frameborder="0" width="520" height="525"></iframe>
  			-->
          </div>
          <div style="height:55px; text-align:center;">
            <a href="javascript:querylist();"><img src="${resourceRoot}/activity/images/button1.png" width="245" height="55" style="margin-left:40px" /></a>
          </div>
        </div>
      </div>
    </div>
    <div style="width:962px;margin:0 auto;background:url(${resourceRoot}/activity/images/cj2.jpg) top center repeat-y;">
      <div style="background:url(${resourceRoot}/activity/images/cj1.jpg) top center no-repeat;height:94px;"></div>
      <div style=" line-height:16px; color:#fff; font-size:14px;background:url(${resourceRoot}/activity/images/cj2.jpg) top center repeat-y;">
        <div style="margin:0px 40px 0px 40px;">
          ${activityInfo.lottRule}
        </div>
      </div>
      <div style="background:url(${resourceRoot}/activity/images/cj3.jpg) top center no-repeat;height:60px;"></div>
    </div>
    <div style="">
      <div style="width:965px; height:80px; margin:0 auto; color:#747474; text-align:center;">
        <div style="text-align:center;">
          </br>
          <span style="color:#a9a9a9"></span></br>
        </div>
      </div>
    </div>
  </div>
  <div class="actGotop">
    <a href="javascript:;" title="返回顶部"></a>
  </div>
  <div class="alert alert-info" role="alert" style="display:none">
    <span id="progressbar_value">20</span>
  </div>
  <div id="jquery_jplayer_1" class="jp-jplayer"></div>
  <div id="jquery_jplayer_0" class="jp-jplayer0"></div>
 <!--  <div style="display:none">
    <img src="${resourceRoot}/activity/images/pointer_load.png" />
    <img src="${resourceRoot}/activity/images/pointer_load_on.png" />
    <img src="${resourceRoot}/activity/images/pointer_load_static.png" />
  </div> -->
  </div>
  <script type="text/javascript">
  /* 扩展窗口外部方法 */
 /* $.dialog.notice = function(options) {
    var opts = options || {},
      api, aConfig, hide, wrap, top,
      duration = opts.duration || 800;
    var config = {
      id: 'Notice',
      left: '100%',
      top: '100%',
      fixed: true,
      drag: false,
      resize: false,
      init: function(here) {
        api = this;
        aConfig = api.config;
        wrap = api.DOM.wrap;
        top = parseInt(wrap[0].style.top);
        hide = top + wrap[0].offsetHeight;

        wrap.css('top', hide + 'px')
          .animate({
            top: top + 'px'
          }, duration, function() {
            opts.init && opts.init.call(api, here);
          });
      },
      close: function(here) {
        wrap.animate({
          top: hide + 'px'
        }, duration, function() {
          opts.close && opts.close.call(this, here);
          aConfig.close = $.noop;
          api.close();
        });

        return false;
      }
    };
    for (var i in opts) {
      if (config[i] === undefined) config[i] = opts[i];
    }
    return $.dialog(config);
  };
  */
  </script>
</body>
</html>
