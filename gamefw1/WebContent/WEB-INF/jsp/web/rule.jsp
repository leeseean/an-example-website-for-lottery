<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.mh.commons.conf.CommonConstant" %>
<%@ page import="com.mh.web.servlet.MySessionContext" %>
<%@ page import="com.mh.commons.utils.ServletUtils" %>


<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  <title>協議與規則</title>
  <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=no;">
  <style type="text/css">
  *{ margin: 0; padding: 0;}
  body{ font-size: 12px; text-align: center;}
  a{ text-decoration: none; transition: all .5s ease;}
  /*
   * 協議規則
   * Rule
   */
  .ybb-pg-ar{
    background-color: #181818;
  }
    .ybb-ar-bd{
      margin: auto;
      width: 1000px;
    }
    .ybb-ar-hd{
      height: 160px;
      background: url(${resourceRoot}/web/ybb/common/images/ybb-ar.png) no-repeat;
    }
    .ybb-ar-hd img{
      margin-top: 40px;
    }
    .ybb-ar-tt{
      padding: 15px 0;
      font-size: 24px;
      color: #fff;
    }
    .ybb-ar-ct{
      background-color: #f3f3f3;
    }
    .ybb-ar-vf ul{ font-size: 0;}
    .ybb-ar-vf li{
      margin-top: 15px;
      display: inline-block;
      *display: inline;
      *zoom: 1;
      font-size: 14px;
    }
    .ybb-ar-vf a{
      margin: 0 5px;
      padding: 0 15px;
      display: block;
      height: 30px;
      line-height: 30px;
      border-radius: 15px;
      color: #fff;
      background-color: #333;
    }
    .ybb-ar-vf a:hover{
      color: #eb4;
      background-color: #000;
    }
    .ybb-ar-st-tt{
      font-size: 0;
    }
    .ybb-ar-st-tt li{
      display: inline-block;
      *display: inline;
      *zoom: 1;
    }
    .ybb-ar-st-tt a{
      padding: 15px 20px;
      display: block;
      height: 100%;
      font-size: 14px;
      border-bottom: 4px solid #ccc;
      color: #666;
    }
    .ybb-ar-st-tt a:hover{
      border-bottom-color: #666;
    }
    .ybb-ar-st-tt .active a{
      font-weight: bold;
      border-bottom: 4px dotted #666;
      color: #222;
    }
    .ybb-ar-st-ct{
      text-align: left;
    }
    .ybb-ar-st-ct h3{
      padding: 15px 0;
      font-weight: bold;
      text-align: center;
    }
    .ybb-ar-st-ct h6{
      padding-left: 40px;
      font-weight: bold;
    }
    .ybb-ar-st-ct ol{
      margin: 0 20px 0 40px;
      padding-bottom: 15px;
      list-style-type: decimal;
    }
    .ybb-ar-st-ct li{
      padding-top: 5px;
      line-height: 1.5;
    }
    .ybb-ar-ft{
      padding: 30px 0;
      color: #ccc;
    }
  </style>
<script type="text/javascript">
		
	function loginOut(){
		$.ajax({
			    url: "${ctx}/loginOut",
			    type: "post",
			    data: null,
			    timeout : 30000,
			    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			   	dataType:"json",
			    error: function(){
			       // alert("用户退出系统出错~");
			    },
			    success: function(obj){
			    	if(obj.rs){
			    		window.location.href="${ctx}/index";
			    	}else{
			    		// alert("用户退出系统出错~");
			    	}
				}
			});
	}
	function loginComfire(){
		$.ajax({
			    url: "${ctx}/loginComfire",
			    type: "post",
			    data: null,
			    timeout : 30000,
			    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			   	dataType:"json",
			    error: function(){
			       // alert("用户退出系统出错~");
			    },
			    success: function(obj){
			    	if(obj.rs){
			    		window.location.href="${ctx}/index";
			    	}
				}
			});
	}
	
</script>
</head>
<body class="ybb-pg-ar font-hei" onload="test()">
<!-- 協議規則 -->
<div class="ybb-ar-bd">
  <div class="ybb-ar-hd">
  	<object type="application/x-shockwave-flash" data="${resourceRoot}/web/ybb/assets/img/index/logo.png" width="312" height="118">
                      <param name="quality" value="high">
                      <param name="wmode" value="transparent">
                      <param name="swfversion" value="15.0.0.0">
    </object>
  </div>
  <div class="ybb-ar-tt">协议与规则</div>
  <div class="ybb-ar-ct">
    <div class="ybb-ar-vf">
      <ul class="clear">
        <li><a href="javascript:void(0);" onclick="loginOut();">我不同意</a></li>
        <li><a href="javascript:void(0);" onclick="loginComfire();" >我同意</a></li>
      </ul>
    </div>
    <div class="ybb-ar-st">
      <ul class="ybb-ar-st-tt ybb-tab-tt clear">
        <li><a href="javascript:void(0);">通则</a></li>
        <li><a href="javascript:void(0);">线上娱乐场</a></li>
        <li><a href="javascript:void(0);">运动投注</a></li>
        <li><a href="javascript:void(0);">其他游戏</a></li>
      </ul>
      <div class="ybb-ar-st-ct ybb-tab-ct">
        <div>
          <h3>通则</h3>
          <ol>
            <li>为避免于本网站投注时之争议，请会员务必于进入网站前详阅本娱乐场所定之各项规则，客户一经“我同意”进入本网站进行投注时，即被视为已接受本娱乐场的所有协议与规则。</li>
            <li>会员有责任确保自己的帐户以及登入资料的保密性，以会员帐号及密码进行的任何网上投注，将被视为有效。敬请不定时做密码变更之动作。若帐号密码被盗用，进行的投注，本公司一概不负赔偿责任。</li>
            <li>本公司保留不定时更改本协定或游戏规则或保密条例，更改之条款将从更改发生后指定之日起生效，并保留一切有争议事项及最后的决策权。</li>
            <li>用户须达到居住地国家法律规定之合法年龄方可使用线上娱乐场或网站。</li>
            <li>网上投注如未能成功提交，投注将被视为无效。</li>
            <li>凡玩家于出牌途中且尚无结果前自动或强制断线时，并不影响比赛之结果。</li>
            <li>如遇发生不可抗拒之灾害，骇客入侵，网络问题造成数据丢失的情况，以本公司公告为最终方案。</li>
            <li>特此声明，本公司将会对所有的电子交易进行记录，如有任何争议，本公司将会以注单记录为准。</li>
            <li>本公司保留更改、修改现有条款或增加任何适当条款的权利，而条款改动后将会在会员端跑马灯上公布。</li>
            <li>无论在任何情况下，本公司具有最终的解释权。</li>
            <li>若经本公司发现会员以不正当手法<利用外挂程式>进行投注或以任何非正常方式进行的个人、团体投注有损公司利益之投注事情发生，本公司保留权利取消该类注单以及注单产生之红利，并停用该会员帐号。</li>
            <li>无风险投注包括在百家乐同时投注的庄闲，单双，大小。龙虎斗中同时投注龙虎，龙双龙单，虎双虎单。在轮盘同时投注黑红，单双，大小等相反的下注，本公司保留权利取消所有优惠、该注单以及注单产生之红利，并停用该会员账号之权利，本公司不承担对此做出任何说明及解释之责任。</li>
            <li>若本公司发现会员有重复申请帐号行为时，保留取消、收回会员所有优惠红利，以及优惠红利所产生的盈利之权利。每位玩家、每一住址、每一电子邮箱、每一电话号码、相同支付卡/信用卡号码，以及共享电脑环境 (例如:网吧、其他公共用电脑等)只能够拥有一个会员帐号，各项优惠只适用于每位客户在本公司唯一的帐户。</li>
          </ol>
        </div>
        <div>
          <h3>娱乐场协议与规则</h3>
          <ol>
            <li>当您下注后，请等待显示“您共下注XXXX元”，这个讯息在中间的讯息窗口可以看见。</li>
            <li>开牌后，若您已有下注，请确认您的输赢金额，这个讯息在中间的讯息窗口可以看见。</li>
            <li>您的“总下注金额”及“赢得金额”亦会每局显示于右上角的视窗中，请会员详加确认。</li>
            <li>当会员在游戏中途发生网路问题而断线，所有已被确定的投注仍然有效。会员再重新登入时，就可以查询游戏端内的“下注记录”查询发牌结果，会员的额度也会随着当局的输赢增加或减少。</li>
            <li>如果您在讯息窗口看到“开牌”的话，而您的游戏画面未显示投注金额，这代表该局您的投注不成功，这有可能下注的时间太迟或是因为网路问题而没有被系统接受。</li>
            <li>百家乐游戏在本网内设计为每手牌局前不销牌。</li>
            <li>当会员进入游戏，超过5局没下注会有提示；若您连续10局未下注的时候将会被游戏弹出至首页，请会员重新登入。若会员于下注开牌期间强行登出，帐号将被系统锁住三分钟，请会员稍后再重新登入。</li>
            <li>本网上游戏是在现场把牌通过扫描后将牌例显示在会员端荧幕上，故若牌没扫描到必将重新扫描一遍，若还是没有感应则把牌翻开由现场公务输入牌卡数码，牌例便会显示在会员端荧幕上。</li>
            <li>当荷官不小心同时从牌靴中抽出两张牌，而扫描到的不是按顺序正确的那张牌：</li>
            <p>（a）若牌局已开牌，而结果不符，系统将根据现况决定牌的先后摆放顺序之开牌结果进行手动开牌，并换上新牌靴开始新局。</p>
            <p>（b）若牌局未开牌，则由现场公务决定牌的先后摆放顺序，牌局会如常继续。</p>
            <li>在洗牌或将牌放入牌靴过程中，有牌不慎曝光，荷官会把牌叠起并重新洗牌，牌局将重新开始。</li>
            <li>牌局进行中，未扬牌前(该张牌未于视讯显示点数花色前)，牌不慎离开台面，牌丢落地上，或离开视讯范围，则该牌局予以注销作废，所有下注本金退回。</li>
            <li>若该牌已经过扫描且已扬牌后，该牌不慎离开台面，牌丢落地上，或离开视讯范围，因其不影响游戏之正确结果，牌由现场工务摆回后，该牌局正常行其结果仍视为有效。</li>
            <li>派错牌例（已不须补牌，荷官仍补牌）现场工务会把多补的那张牌放到牌靴底，牌局结果依视讯显示为准，牌局将如常进行；若该张多补的牌已亮开，公务将在做完以上同样程序后换上新牌靴，牌局会重新开始。</li>
            <li>荷官未依闲、庄发牌正确顺序将牌放错位置，由工务将牌依正确顺序摆放回位置后牌局将照常继续。</li>
            <li>开牌过程中，牌有感应但无显示，荷官已亮牌（如:已派出数张牌，但第一张牌有感应但未显示，至第二、三张牌显示在错误的闲、庄位置上），现场工务会依牌的正确次序输入代码，牌局将如常继续。</li>
            <li>同一张牌，扫描了一次出现2张（闲、庄位置各一张）</li>
            <p>（a）若牌局已开牌，而结果不符，系统将根据视讯荷官完成该局之正确结果进行手动开牌，牌局也将在牌路无误的情况下如常继续。</p>
            <p>（b）若牌局未开牌，荷官避开扫描如常开牌后，工务将输入牌之正确数码，并修正不符那张牌的花色、点数，牌局会如常继续。</p>
            <li>电脑、扫描器出现异常、牌局中断、牌无法扫描又无法输入牌卡数码时，那一个牌局便会作废，所有程式将被关闭，并重开程式，牌局将重新开始。但若荷官发牌视讯已有结果，则以视讯开牌为主，会由系统完成开配。</li>
            <li>有关例行性维护、网路问题、视讯中断、牌局作废、注销情况等事宜，皆可于会员端左上角处公告栏上得知最新讯息。</li>
            <li>发牌视讯仅保留三日，若有异议请于游戏当日起三日内提出，三日后恕不受理。</li>
            <li>本娱乐场之视讯为真人直播，故该局游戏若因国际线路传输问题出现争议，将以视讯看到牌局结果决定输赢。</li>
            <li>本娱乐场所提供之牌路仅供参考，若因国际线路问题或其他因素造成牌路显示有误，所有游戏结果将以视讯开牌及游戏记录为主。</li>
            <li>本公司保留一切有争议事项的修正及最后的决策。</li>
            <li>本娱乐场保留随时更改、修订或删除游戏、游戏规则（包括机率及赔率）及协议条款的权利而无须作事先通知。</li>
            <li>本娱乐场保留随时修订、撤销或中止任何投注的权利而无须作事先通知，亦无须作任何解释。</li>
            <li>本娱乐场记录每一项于本网站伺服器内执行的交易及投注功能。若会员认为向本娱乐场提供的资料与本网站资料库中的资料记录之间出现了任何声称的差异，一切均以本网站资料库的资料为准。</li>
            <li>当会员已于本娱乐场之游戏厅内下注，而电脑出现连线异常导致牌局中断时，会员最后押注仍视为有效，本娱乐场将以会员于本网站资料库的交易记录为准。</li>
            <li>会员在本娱乐场之游戏厅内任何游戏的押注记录均视为有效，会员需自行承担下注后的风险。</li>
            <li>若经本公司发现会员以不正当手法<利用外挂程式>进行投注或以任何非正常方式进行的个人、团体投注有损公司利益之投注事情发生，本公司保留取消该类注单之权利。</li>
          </ol>
        </div>
        <div>
          <h3>运动投注协议与规则</h3>
          <ol>
            <li>当您在下注之后，请等待显示下注成功信息。</li>
            <li>为了避免出现争论，您必须在下注之后检查下注状况。</li>
            <li>任何[体育投注]的投诉必须在开赛之前提出，本公司不会受理任何开赛之后的投诉。</li>
            <li>所有赛事投注项目(包括滚球)，出现任何打字错误或非人为故意失误之注单，一律取消。</li>
            <li>除滚球外，开赛后接受的投注，将被视为“无效”。</li>
            <li>倘若发生不可抗拒之灾害或骇客入侵破坏行为，造成网站故障或资料毁损等情况，以本公司网站公告讯息为最后处理方案，请各会员投注后列印资料本公司才接受投诉！</li>
            <li>即日起任何异常或争议之注单，将不计算结果；并转移至当日历史帐户于结果栏位注明"已注销"。</li>
            <li>下注时出现同时同分同秒及内容相同2笔以上之注单，本公司只承认一笔有效注单，其余一律注销。</li>
            <li>由于走地时系统资料处理繁忙，如发现刻意于“走地进球后之交易资料一律取消，不便之处，敬请见谅！</li>
            <li>走地进球后，如因网页系统或线路状况..等问题而导致比分或赔率无法更新，所有相关交易资料“一律取消”。</li>
            <li>滚球注单下注过程中若显示[等待中]，该注单尚未被本公司确认接受；客户可至下注状况与帐户历史确认，若该滚球注单确定未被本公司接受，可至帐户历史查看该注单结果会显示[未接受]并且画横线！</li>
            <li>在赛事开赛前,比赛队伍之主场或中立场若有任何更改,本公司将依据盘口与行情差距而保留更改前的注单是否有效之权利,所有判决将于走马灯公告上公布!</li>
            <li>早餐取消之注单可于"下注状况"查询,当日以前(含当日)取消之注单将放于"帐户历史"并于结果栏位注明"取消",敬请各会员查询后重新交易!</li>
            <li>若经本公司发现会员以不正当手法&lt;利用外挂程式&gt;进行投注或以任何非正常方式进行的个人、团体投注有损公司利益之投注事情发生，本公司保留取消该类注单之权利，并于注单取消时，备注此注单为『非法下注』或『系统注销』(含完赛后之注单)。</li>
          </ol>
        </div>
        <div>
          <h3>其他游戏</h3>
          <h6>彩票类游戏</h6>
          <ol>
            <li>当您在下注之后，请等待显示“下注成功”资讯。</li>
            <li>为了避免出现争论，您必须在下注之后检查“下注状况”。</li>
            <li>任何的投诉必须在开彩之前提出，本公司不会受理任何开彩之后的投诉。</li>
            <li>所有投注项目，公布赔率时出现的任何打字错误或非故意人为失误，本公司保留改正错误和按正确赔率结算投注的权利。</li>
            <li>开彩后的投注，将被视为“无效”。所有赔率将不时浮动，派彩时的赔率将以确认投注时之赔率为准。</li>
            <li>所有赛果以官方网站公布的结果为依据，若因官网延迟、错误或取消；本公司保留对已下注注单的裁决权。</li>
            <li>若发生两颗球同时吸起，将依照在结果区之号码球为主。</li>
            <li>在游戏尚未完成开配之前，若因机器问题而导致有结果产生，该局将一律注销。</li>
            <li>在下注时间尚未结束之前，若因任何因素导致有结果产生，该局将一律注销。</li>
            <li>若该局己有结果但与实际结果不符，系统将根据视讯完成该局之正确结果进行手动开牌。</li>
          </ol>
          <h6>电子游艺</h6>
          <ol>
            <li>电子游戏中奖画面与派彩结果不符时，本公司将以资料库最终结果为依据。</li>
            <li>彩池金额是以满注中奖结果显示，玩家中奖系以押注比例分配彩池金额。</li>
            <li>老虎机游戏过程中如遇断线情况将以资料库最终结果为依据，本公司保留对已下注注单的裁决权。</li>
            <li>21点游戏补牌中如遇玩家断线，则视玩家为不补牌；游戏结果将视为有效。</li>
            <li>红狗游戏过程中如遇断线情况将视为玩家不加注进行补牌，完成该局游戏结果。</li>
          </ol>
        </div>
      </div>
    </div>
  </div>
  <div class="ybb-ar-ft">Copyright (c) 168娱乐城 Reserved</div>
</div>
<!-- /協議規則 -->
</body>

<script src="${resourceRoot}/web/ybb/common/js/jquery.min.js" type="text/javascript"></script>
<script>
$(function(){
  $('.ybb-tab-tt > li').first().addClass('active').siblings().removeClass('active');
  $('.ybb-tab-ct > div').first().fadeIn().siblings().hide();
  $('.ybb-tab-tt li').click(function(){
    var index = $('.ybb-tab-tt li').index(this);
    $(this).addClass('active').siblings().removeClass('active');
    $('.ybb-tab-ct > div').eq(index).fadeIn().siblings().hide();
  });
});

$(function(){
	if('${status}'+'1'=='true'+'1'){
		alert('${content}');
	}
});
</script>
</html>