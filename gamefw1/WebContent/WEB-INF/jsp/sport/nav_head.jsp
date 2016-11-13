<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!doctype html>
<html>
<head>
<title>皇冠体育</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${resourceRoot}/sport/css/mem_header_ft_cn.css" rel="stylesheet">
<script type="text/javascript" src="${resourceRoot}/sport/js/jquery.min.js"></script>
<script type="text/javascript" src="${resourceRoot}/sport/js/hg_header.js"></script>
<script type="text/javascript">
//设置美东时间
var dd2=new Date('${gmt_4_Date}');
$(document).ready(function () {
	setInterval("RefTime()",1000); 
});
function RefTime()
{
	dd2.setSeconds(dd2.getSeconds()+1);
	var myYears = ( dd2.getYear() < 1900 ) ? ( 1900 + dd2.getYear() ) : dd2.getYear();
	document.getElementById("head_date").innerHTML = myYears+"年"+fixNum(dd2.getMonth()+1)+"月"+fixNum(dd2.getDate())+" "+time(dd2);
}
function time(vtime){
    var s='';
    var d=vtime!=null?new Date(vtime):new Date();
    with(d){
        s=fixNum(getHours())+':'+fixNum(getMinutes())+':'+fixNum(getSeconds());
    }
    return(s);
}
function fixNum(num){
    return parseInt(num)<10?'0'+num:num;
}

function refreshRollCount() {
		$.ajax({
			url : "${ctx}/valid/refreshUserMoney",
			type : "POST",
			data : "{}",
			dataType : "json",
			success : function(result) {
				if(result.rs){
					$("#userMoneyId").html(parseFloat(result.datas.userMoney).toFixed(1));
				}else{
					//alert(result.msg);
					$("#userMoneyId").html("0");
				}
			},
			error : function() {
			}
		});
	}
	var uid = "${webUser.userName}";
	if(uid!=null && uid!=''){
		top.uid = "${webUser.userName}";
	}else{
		top.uid = "top_guest";
	}
</script>

</head>
<body id="HFT" class="bodyset" >
	<div style="z-index:3000;float: left; display:none;">
		<iframe id="memOnline" name="memOnline" scrolling="NO" frameborder="NO" border="0" height="500" width="800"></iframe>
	</div>
	<div id="container">
		<input type="hidden" id="uid" name="uid" value="ypx083sam13140059l163446974"> 
		<input type="hidden" id="langx" name="langx" value="zh-cn">
		<div id="header"><span><h1>&nbsp;</h1></span></div>
		<div id="welcome">
			<ul class="level1">
				<!--会员帐号-->
				<li class="name">
					<c:choose>
						<c:when test="${!empty webUser}">
							您好, <span id="userid">${webUser.userName}</span>
						</c:when>
						<c:otherwise>
							您好, 请先登录!
						</c:otherwise>
					</c:choose>
					<div id="head_date"></div>
				</li>
				<li class="rb" id="rb_btn">
					<a href="javascript:;" onclick="chg_button_bg('FT','rb');chg_nav('re');chg_type_class(1);chg_navli('re');chg_third(1);chg_type_last(1);chg_url('${ctx}/sport/goMatchCenter?timeType=roll&matchType=ft&rtype=re');"
						id="rbyshow" style="display:;">滚球</a>
				</li>
				<li class="today_on" id="today_btn"><a href="javascript:;"
					onclick="chg_button_bg('FT','today');chg_nav('today');chg_type_class(9);chg_navli('today');chg_third(9);chg_type_last(12);chg_url('${ctx}/sport/goMatchCenter?timeType=today&matchType=ft&rtype=r');"
					id="todayshow" style="display:;">今日赛事</a>
				</li>
				<li class="early" id="early_btn"><a href="javascript:;"
					onclick="chg_button_bg('FT','early');chg_nav('early');chg_type_class(17);chg_navli('early');chg_third(17);chg_type_last(38);chg_url('${ctx}/sport/goMatchCenter?timeType=tom&matchType=ft&rtype=r');"
					id="earlyshow" style="display:'';cursor:hand;">早盘</a>
				</li>
				<!--  
				<li class="early"><a target="_blank" href="${ctx}/goPageCenter?code=live">视讯直播</a></li>
				<li class="early"><a target="_blank" href="${ctx}/electronic?code=mg">电子游艺</a></li>
				<li class="early">
					<c:choose>
						<c:when test="${!empty webUser}">
							<a href="javascript:void(0)" onclick="winOpen('${ctx}/lottery',window.screen.width,window.screen.height,0,0,'game')" >彩票游戏</a>
						</c:when>
						<c:otherwise>
							<a href="javascript:void(0)" onclick="alert('您尚未登录，请先登录再进行游戏')" >彩票游戏</a>
						</c:otherwise>
					</c:choose>
				</li>
				-->
			</ul>
		</div>

		<!-- 滚球Menu Start -->
		<div id="nav_re" style="display:none;">
			<ul>
				<li class="ft">
					<a class="type_out" id="ftn1" href="#" onClick="chg_type_class(1);chg_third(1);chg_type_last(1);chg_url('${ctx}/sport/goMatchCenter?timeType=roll&matchType=ft&rtype=re');">足球
						(<strong class="game_sum" id="roll_re">0</strong>)</a>
				</li>
				<li class="tn"><a class="type_out" id="ftn2" href="#"
					onClick="chg_type_class(2);chg_third(2);chg_type_last(5);chg_url('${ctx}/sport/goMatchCenter?timeType=roll&matchType=bk&rtype=re_main');">篮球/美式足球
						(<strong class="game_sum" id="roll_re_main">0</strong>)</a>
				</li>
				<li class="tn"><a class="type_out" id="ftn3" href="#" onClick="chg_type_class(3);chg_third(3);chg_type_last(6);chg_url('${ctx}/sport/goMatchCenter2?matchType=tn&timeType=roll');">网球(<strong class="game_sum" id="RB_TN_games">0</strong>)</a></li>
				
				<li class="vb"><a class="type_out" id="ftn4" href="#" onClick="chg_type_class(4);chg_third(4);chg_type_last(7);chg_url('${ctx}/sport/goMatchCenter2?matchType=vb&timeType=roll');">排球(<strong class="game_sum" id="RB_VB_games">0</strong>)</a></li>
				
				<li class="bm"><a class="type_out" id="ftn5" href="#" onClick="chg_type_class(5);chg_third(5);chg_type_last(8);chg_url('${ctx}/sport/goMatchCenter2?matchType=bm&timeType=roll');">羽毛球(<strong class="game_sum" id="RB_BM_games">0</strong>)</a></li>
				
				<li class="tt"><a class="type_out" id="ftn6" href="#" onClick="chg_type_class(6);chg_third(6);chg_type_last(9);chg_url('${ctx}/sport/goMatchCenter2?matchType=tt&timeType=roll');">乒乓球(<strong class="game_sum" id="RB_TT_games">0</strong>)</a></li>
				
				<li class="bs"><a class="type_out" id="ftn7" href="#" onClick="chg_type_class(7);chg_third(7);chg_type_last(10);chg_url('${ctx}/sport/goMatchCenter2?matchType=bs&timeType=roll');">棒球(<strong class="game_sum" id="RB_BS_games">0</strong>)</a></li>
				
				<li class="op"><a class="type_out" id="ftn8" href="#" onClick="chg_type_class(8);chg_third(8);chg_type_last(11);chg_url('${ctx}/sport/goMatchCenter2?matchType=op&timeType=roll');">其他(<strong class="game_sum" id="RB_OP_games">0</strong>)</a></li>

			</ul>
		</div>

		<div id="type_re" style="display:none;">
			<ul id="third1" style="display:;">
				<li class="re">
					<a id="last1" class="type_on" href="javascript:void(0);" onClick="chg_type_last(1);chg_url('${ctx}/sport/goMatchCenter?timeType=roll&matchType=ft&rtype=re');">独赢 ＆ 让球 ＆ 大小 & 单 / 双</a>
				</li>
				<li class="result">
					<a id="result_class"  href="javascript:void(0);"onClick="chg_url('${ctx}/sport/getftRes');"class="type_out" >赛果</a>
				</li>
				<%--<li class="result"><a id="result_class" class="type_out" href="${ctx}/sport/help/qa" target="body">体育规则</a></li>--%>
			</ul>
			<ul id="third2" style="display:none;">
				<li class="re">
					<a id="last5" class="type_on" href="javascript:void(0);" onClick="chg_type_last(5);chg_url('${ctx}/sport/goMatchCenter?timeType=roll&matchType=bk&rtype=re_main');">独赢盘＆ 让分 ＆ 大小</a>
				</li>
				<li class="result">
					<a id="result_class" class="type_out" href="javascript:void(0);"onClick="chg_url('${ctx}/sport/getftRes');" >赛果</a>
				</li>
				<%--<li class="result"><a id="result_class" class="type_out" target="body" href="${ctx}/sport/help/qa">体育规则</a></li>--%> 

			</ul>
			<ul id="third3" style="display:none;">
				<li class="re">
					<a id="last6" class="type_on" href="javascript:void(0);" onClick="chg_type_last(6);chg_url('${ctx}/sport/goMatchCenter2?matchType=op&timeType=roll');">让盘＆大小</a>
				</li>
				<li class="result">
					<a id="result_class" class="type_out" href="#" onClick="chg_url('${ctx}/sport/getftRes');">赛果</a>
				</li>
				<%--<li class="result">
					<a id="result_class" class="type_out" target="body" href="${ctx}/sport/help/qa">体育规则</a>
				</li> --%>
			</ul>
			<ul id="third4" style="display:none;">
				<li class="re">
					<a id="last7" class="type_on" href="javascript:void(0);" onClick="chg_type_last(7);chg_url('${ctx}/sport/goMatchCenter2?matchType=op&timeType=roll');">独赢＆ 让分 ＆ 大小</a>
				</li>
				<li class="result">
					<a id="result_class" class="type_out" href="#" onClick="chg_url('${ctx}/sport/getftRes');">赛果</a>
				</li>
				<%--<li class="result">
					<a id="result_class" class="type_out" target="body" href="${ctx}/sport/help/qa">体育规则</a>
				</li>
			--%></ul>
			
			<ul id="third5" style="display:none;">
				<li class="re">
					<a id="last8" class="type_on" href="javascript:void(0);" onClick="chg_type_last(8);chg_url('${ctx}/sport/goMatchCenter2?matchType=op&timeType=roll');">独赢＆ 让局 ＆ 大小</a>
				</li>
				<li class="result">
					<a id="result_class" class="type_out" href="#" onClick="chg_url('${ctx}/sport/getftRes');">赛果</a>
				</li>
				<%--<li class="result">
					<a id="result_class" class="type_out" target="body" href="${ctx}/sport/help/qa">体育规则</a>
				</li>--%>
			</ul>
			
			<ul id="third6" style="display:none;">
				<li class="re">
					<a id="last9" class="type_on" href="javascript:void(0);" onClick="chg_type_last(9);chg_url('${ctx}/sport/goMatchCenter2?matchType=op&timeType=roll');">独赢＆ 让局 ＆ 大小</a>
				</li>
				<li class="result">
					<a id="result_class" class="type_out" href="#" onClick="chg_url('${ctx}/sport/getftRes');">赛果</a>
				</li>
				<%--<li class="result">
					<a id="result_class" class="type_out" target="body" href="${ctx}/sport/help/qa">体育规则</a>
				</li>--%>
			</ul>
			
			<ul id="third7" style="display:none;">
				<li class="re">
					<a id="last10" class="type_on" href="javascript:void(0);" onClick="chg_type_last(10);chg_url('${ctx}/sport/goMatchCenter2?matchType=op&timeType=roll');">让分 ＆ 大小</a>
				</li>
				<li class="result">
					<a id="result_class" class="type_out" href="#" onClick="chg_url('${ctx}/sport/getftRes');">赛果</a>
				</li>
				<%--<li class="result">
					<a id="result_class" class="type_out" target="body" href="${ctx}/sport/help/qa">体育规则</a>
				</li>--%>
			</ul>
			
			<ul id="third8" style="display:none;">
				<li class="re">
					<a id="last11" class="type_on" href="javascript:void(0);" onClick="chg_type_last(11);chg_url('${ctx}/sport/goMatchCenter2?matchType=op&timeType=roll');">让球 ＆ 大小</a>
				</li>
				<li class="result">
					<a id="result_class" class="type_out" href="#" onClick="chg_url('${ctx}/sport/getftRes');">赛果</a>
				</li>
				<%--<li class="result">
					<a id="result_class" class="type_out" target="body" href="${ctx}/sport/help/qa">体育规则</a>
				</li>--%>
			</ul>
		</div>

		<!-- 滚球Menu End -->

		<!-- Today Menu Start -->
		<div id="nav_today">
			<ul class="level1">
				<li class="ft"><span><a class="type_on" id="ftn9"
						href="#"
						onclick="chg_type_class(9);chg_third(9);chg_type_last(12);chg_url('${ctx}/sport/goMatchCenter?timeType=today&matchType=ft&rtype=r');">足球
							(<strong class="game_sum" id="today_r"></strong>)</a>
				</span>
				</li>
				<li class="bk"><span><a class="type_out" id="ftn10"
						href="#"
						onclick="chg_type_class(10);chg_third(10);chg_type_last(17);chg_url('${ctx}/sport/goMatchCenter?timeType=today&matchType=bk&rtype=r_main');">篮球/美式足球
							(<strong class="game_sum" id="today_bk_r_main"></strong>)</a>
				</span>
				</li>
				<li class="tn"><a class="type_out" id="ftn11" href="#" onClick="chg_type_class(11);chg_third(11);chg_type_last(19);chg_url('${ctx}/sport/goMatchCenter2?matchType=tn&timeType=today');">网球(<strong class="game_sum" id="RB_TN_games">0</strong>)</a></li>
			
				<li class="vb"><a class="type_out" id="ftn12" href="#" onClick="chg_type_class(12);chg_third(12);chg_type_last(22);chg_url('${ctx}/sport/goMatchCenter2?matchType=vb&timeType=today');">排球(<strong class="game_sum" id="RB_VB_games">0</strong>)</a></li>
				
				<li class="bm"><a class="type_out" id="ftn13" href="#" onClick="chg_type_class(13);chg_third(13);chg_type_last(26);chg_url('${ctx}/sport/goMatchCenter2?matchType=bm&timeType=today');">羽毛球(<strong class="game_sum" id="RB_BM_games">0</strong>)</a></li>
				
				<li class="tt"><a class="type_out" id="ftn14" href="#" onClick="chg_type_class(14);chg_third(14);chg_type_last(29);chg_url('${ctx}/sport/goMatchCenter2?matchType=tt&timeType=today');">乒乓球(<strong class="game_sum" id="RB_TT_games">0</strong>)</a></li>
				
				<li class="bs"><a class="type_out" id="ftn15" href="#" onClick="chg_type_class(15);chg_third(15);chg_type_last(32);chg_url('${ctx}/sport/goMatchCenter2?matchType=bs&timeType=today');">棒球(<strong class="game_sum" id="RB_BS_games">0</strong>)</a></li>
				
				<li class="op"><a class="type_out" id="ftn16" href="#" onClick="chg_type_class(16);chg_third(16);chg_type_last(35);chg_url('${ctx}/sport/goMatchCenter2?matchType=op&timeType=today');">其他(<strong class="game_sum" id="RB_OP_games">0</strong>)</a></li>

			</ul>
		</div>

		<div id="type_today" style="display:;">
			<ul id="third9" style="display:;">
				<li class="re"><a id="last12" class="type_on"
					href="javascript:void(0);"
					onClick="chg_type_last(12);chg_url('${ctx}/sport/goMatchCenter?timeType=today&matchType=ft&rtype=r');">独赢
						＆ 让球 ＆ 大小 & 单 / 双</a>

				</li>
				<li class="pd"><a id="last13" class="type_out"
					href="javascript:void(0);"
					onClick="chg_type_last(13);chg_url('${ctx}/sport/goMatchCenter?timeType=today&matchType=ft&rtype=pd');">波胆</a>

				</li>
				<li class="to"><a id="last14" class="type_out"
					href="javascript:void(0);"
					onClick="chg_type_last(14);chg_url('${ctx}/sport/goMatchCenter?timeType=today&matchType=ft&rtype=t');">总入球</a>

				</li>
				<li class="hf"><a id="last15" class="type_out"
					href="javascript:void(0);"
					onClick="chg_type_last(15);chg_url('${ctx}/sport/goMatchCenter?timeType=today&matchType=ft&rtype=f');">半场
						/ 全场</a>
				</li>
				<li class="hp3"><a id="last16" class="type_out"
					href="javascript:void(0);"
					onClick="chg_type_last(16);chg_url('${ctx}/sport/goMatchCenter?timeType=today&matchType=ft&rtype=p3');">综合过关</a>
				
				<li class="result">
					<a id="result_class" class="type_out" href="javascript:void(0);"onClick="chg_url('${ctx}/sport/getftRes');">赛果</a>
				</li>
				<%--<li class="result">
					<a id="result_class" class="type_out"target="body" href="${ctx}/sport/help/qa">体育规则</a>
				</li>--%> 
			</ul>
			<ul id="third10" style="display:none;">
				<li class="re"><a id="last17" class="type_on"
					href="javascript:void(0);"
					onClick="chg_type_last(17);chg_url('${ctx}/sport/goMatchCenter?timeType=today&matchType=bk&rtype=r_main');">独赢盘
						＆ 让分 ＆ 大小 </a>
				</li>
				<li class="hp3">
					<a id="last18" class="type_out" href=javascript:void(0); onClick="chg_type_last(18);chg_url('${ctx}/sport/goMatchCenter?timeType=today&matchType=bk&rtype=p3');">综合过关</a>
				</li>
				<li class="result"><a id="result_class" class="type_out"  href="javascript:void(0);"onClick="chg_url('${ctx}/sport/getftRes');" >赛果</a></li>
				 <%--<li class="result"><a id="result_class" class="type_out" target="body" href="${ctx}/sport/help/qa">体育规则</a></li>--%> 
			</ul>
			
			<ul id="third11" style="display:none;">
				<li class="re">
					<a id="last19" class="type_on" href="javascript:void(0);" onClick="chg_type_last(19);chg_url('${ctx}/sport/goMatchCenter2?matchType=op&timeType=today');">独赢 ＆ 让盘 ＆ 大小  </a>
				</li>
				<li class="hp3">
					<a id="last20" class="type_out" href=javascript:void(0); onClick="chg_type_last(20);chg_url('${ctx}/sport/goMatchCenter2?matchType=op&timeType=today');">综合过关</a>
				</li>
				<li class="hp3">
					<a id="last21" class="type_out" href=javascript:void(0); onClick="chg_type_last(21);chg_url('${ctx}/sport/goMatchCenter2?matchType=op&timeType=today');">冠军</a>
				</li>
				<li class="result"><a id="result_class" class="type_out" href="#" onClick="chg_url('${ctx}/sport/getftRes');">赛果</a></li>
				<%--<li class="result">
					<a id="result_class" class="type_out" target="body" href="${ctx}/sport/help/qa">体育规则</a>
				</li>--%>
			</ul>
			
			<ul id="third12" style="display:none;">
				<li class="re">
					<a id="last22" class="type_on" href="javascript:void(0);"
					
					onClick="chg_type_last(22);chg_url('${ctx}/sport/goMatchCenter2?matchType=op&timeType=today');">独赢 ＆ 让分 ＆ 大小 ＆ 单双  
					</a>
				</li>
				<li class="hp3">
					<a id="last23" class="type_out" href=javascript:void(0); onClick="chg_type_last(23);chg_url('${ctx}/sport/goMatchCenter2?matchType=op&timeType=today');">综合过关</a>
				</li>
				<li class="hp3">
					<a id="last24" class="type_out" href=javascript:void(0); onClick="chg_type_last(24);chg_url('${ctx}/sport/goMatchCenter2?matchType=op&timeType=today');">赛盘投注</a>
				</li>
				<li class="hp3">
					<a id="last25" class="type_out" href=javascript:void(0); onClick="chg_type_last(25);chg_url('${ctx}/sport/goMatchCenter2?matchType=op&timeType=today');">冠军</a>
				</li>
				<li class="result"><a id="result_class" class="type_out" href="#" onClick="chg_url('${ctx}/sport/getftRes');">赛果</a></li>
				<%--<li class="result">
					<a id="result_class" class="type_out" target="body" href="${ctx}/sport/help/qa">体育规则</a>
				</li>--%>
			</ul>
			
			<ul id="third13" style="display:none;">
				<li class="re"><a id="last26" class="type_on"
					href="javascript:void(0);"
					onClick="chg_type_last(11);chg_url('${ctx}/sport/goMatchCenter?timeType=today&matchType=bk&rtype=r_main');">独赢 ＆ 让局 ＆ 大小
					</a>
				</li>
				<li class="hp3">
					<a id="last27" class="type_out" href=javascript:void(0); onClick="chg_type_last(27);chg_url('${ctx}/sport/goMatchCenter2?matchType=op&timeType=today');">综合过关</a>
				</li>
				<li class="hp3">
					<a id="last28" class="type_out" href=javascript:void(0); onClick="chg_type_last(28);chg_url('${ctx}/sport/goMatchCenter2?matchType=op&timeType=today');">冠军</a>
				</li>
				<li class="result"><a id="result_class" class="type_out" href="#" onClick="chg_url('${ctx}/sport/getftRes');">赛果</a></li>
				<%--<li class="result">
					<a id="result_class" class="type_out" target="body" href="${ctx}/sport/help/qa">体育规则</a>
				</li>--%>
			</ul>
			<ul id="third14" style="display:none;">
				<li class="re"><a id="last29" class="type_on"
					href="javascript:void(0);"
					onClick="chg_type_last(11);chg_url('${ctx}/sport/goMatchCenter?timeType=today&matchType=bk&rtype=r_main');">独赢 ＆ 让局 ＆ 大小  
					</a>
				</li>
				<li class="hp3">
					<a id="last30" class="type_out" href=javascript:void(0); onClick="chg_type_last(30);chg_url('${ctx}/sport/goMatchCenter2?matchType=op&timeType=today');">综合过关</a>
				</li>
				<li class="hp3">
					<a id="last31" class="type_out" href=javascript:void(0); onClick="chg_type_last(31);chg_url('${ctx}/sport/goMatchCenter2?matchType=op&timeType=today');">冠军</a>
				</li>
				<li class="result"><a id="result_class" class="type_out" href="#" onClick="chg_url('${ctx}/sport/getftRes');">赛果</a></li>
				<%--<li class="result">
					<a id="result_class" class="type_out" target="body" href="${ctx}/sport/help/qa">体育规则</a>
				</li>--%>
			</ul>
			<ul id="third15" style="display:none;">
				<li class="re"><a id="last32" class="type_on"
					href="javascript:void(0);"
					onClick="chg_type_last(32);chg_url('${ctx}/sport/goMatchCenter?timeType=today&matchType=bk&rtype=r_main');">独赢 ＆ 让分 ＆ 大小 ＆ 单双  
					</a>
				</li>
				<li class="hp3">
					<a id="last33" class="type_out" href=javascript:void(0); onClick="chg_type_last(33);chg_url('${ctx}/sport/goMatchCenter2?matchType=op&timeType=today');">综合过关</a>
				</li>
				<li class="hp3">
					<a id="last34" class="type_out" href=javascript:void(0); onClick="chg_type_last(34);chg_url('${ctx}/sport/goMatchCenter2?matchType=op&timeType=today');">冠军</a>
				</li>
				<li class="result"><a id="result_class" class="type_out" href="#" onClick="chg_url('${ctx}/sport/getftRes');">赛果</a></li>
				<%--<li class="result">
					<a id="result_class" class="type_out" target="body" href="${ctx}/sport/help/qa">体育规则</a>
				</li>--%>
			</ul>
			<ul id="third16" style="display:none;">
				<li class="re"><a id="last35" class="type_on"
					href="javascript:void(0);"
					onClick="chg_type_last(35);chg_url('${ctx}/sport/goMatchCenter?timeType=today&matchType=bk&rtype=r_main');">独赢 ＆ 让分 ＆ 大小 ＆ 单双  
					</a>
				</li>
				<li class="hp3">
					<a id="last36" class="type_out" href=javascript:void(0); onClick="chg_type_last(36);chg_url('${ctx}/sport/goMatchCenter2?matchType=op&timeType=today');">综合过关</a>
				</li>
				<li class="hp3">
					<a id="last37" class="type_out" href=javascript:void(0); onClick="chg_type_last(37);chg_url('${ctx}/sport/goMatchCenter2?matchType=op&timeType=today');">冠军</a>
				</li>
				<li class="result"><a id="result_class" class="type_out" href="#" onClick="chg_url('${ctx}/sport/getftRes');">赛果</a></li>
				<%--<li class="result">
					<a id="result_class" class="type_out" target="body" href="${ctx}/sport/help/qa">体育规则</a>
				</li>--%>
			</ul>
		</div>
		<!-- Today Menu End -->

		<!-- Early Menu Start -->
		<div id="nav_early" style="display:none;">
			<ul class="level1">
				<li class="ft">
					<span class="ball">
						<a class="type_on" id="ftn17"
							onclick="chg_type_class(17);chg_third(17);chg_type_last(38);chg_url('${ctx}/sport/goMatchCenter?timeType=tom&matchType=ft&rtype=r');"
							href="#">足球 (<strong class="game_sum" id="tom_r"></strong>)</a>
					</span>
				</li>
				<li class="bk">
					<span class="ball">
						<a class="type_out" id="ftn18" onclick="chg_type_class(18);chg_third(18);chg_type_last(43);chg_url('${ctx}/sport/goMatchCenter?timeType=tom&matchType=bk&rtype=r_main');"
							href="#">篮球/美式足球(<strong class="game_sum" id="tom_bk_r_main"></strong>)</a>
					</span>
				</li>
				
				<li class="tn"><a class="type_out" id="ftn19" href="#" onClick="chg_type_class(19);chg_third(19);chg_type_last(45);chg_url('${ctx}/sport/goMatchCenter2?matchType=tn&timeType=tom');">网球(<strong class="game_sum" id="RB_TN_games">0</strong>)</a></li>
				
				<li class="vb"><a class="type_out" id="ftn20" href="#" onClick="chg_type_class(20);chg_third(20);chg_type_last(48);chg_url('${ctx}/sport/goMatchCenter2?matchType=vb&timeType=tom');">排球(<strong class="game_sum" id="RB_VB_games">0</strong>)</a></li>
				
				<li class="bm"><a class="type_out" id="ftn21" href="#" onClick="chg_type_class(21);chg_third(21);chg_type_last(52);chg_url('${ctx}/sport/goMatchCenter2?matchType=bm&timeType=tom');">羽毛球(<strong class="game_sum" id="RB_BM_games">0</strong>)</a></li>
				
				<li class="tt"><a class="type_out" id="ftn22" href="#" onClick="chg_type_class(22);chg_third(22);chg_type_last(55);chg_url('${ctx}/sport/goMatchCenter2?matchType=tt&timeType=tom');">乒乓球(<strong class="game_sum" id="RB_TT_games">0</strong>)</a></li>
				
				<li class="bs"><a class="type_out" id="ftn23" href="#" onClick="chg_type_class(23);chg_third(23);chg_type_last(58);chg_url('${ctx}/sport/goMatchCenter2?matchType=bs&timeType=tom');">棒球(<strong class="game_sum" id="RB_BS_games">0</strong>)</a></li>
				
				<li class="op"><a class="type_out" id="ftn24" href="#" onClick="chg_type_class(24);chg_third(24);chg_type_last(61);chg_url('${ctx}/sport/goMatchCenter2?matchType=op&timeType=tom');">其他(<strong class="game_sum" id="RB_OP_games">0</strong>)</a></li>

			</ul>
		</div>

		<div id="type_early" style="display:none;">
			<ul id="third17" style="display: block;width:720px">
				<li class="re">
					<a id="last38" class="type_on" href="javascript:void(0);" onClick="chg_type_last(38);chg_url('${ctx}/sport/goMatchCenter?timeType=tom&matchType=ft&rtype=r');">独赢＆ 让球 ＆ 大小＆ 单 / 双</a>
				</li>
				<li class="pd">
					<a id="last39" class="type_out" href="javascript:void(0);" onClick="chg_type_last(39);chg_url('${ctx}/sport/goMatchCenter?timeType=tom&matchType=ft&rtype=pd');">波胆</a>
				</li>
				<li class="to">
					<a id="last40" class="type_out" href="javascript:void(0);" onClick="chg_type_last(40);chg_url('${ctx}/sport/goMatchCenter?timeType=tom&matchType=ft&rtype=t');">总入球</a>
				</li>
				<li class="hf">
					<a id="last41" class="type_out" href="javascript:void(0);" onClick="chg_type_last(41);chg_url('${ctx}/sport/goMatchCenter?timeType=tom&matchType=ft&rtype=f');">半场/ 全场</a>
				</li>
				<li class="hp3">
					<a id="last42" class="type_out" href="javascript:void(0);" onClick="chg_type_last(42);chg_url('${ctx}/sport/goMatchCenter?timeType=tom&matchType=ft&rtype=p3');">综合过关</a>
				</li>
				<li class="result">
					<a id="result_class" class="type_out" href="javascript:void(0);"onClick="chg_url('${ctx}/sport/getftRes');">赛果</a>
				</li>
				 <%--<li class="result">
					<a id="result_class" class="type_out" target="body" href="${ctx}/sport/help/qa">体育规则</a></li>--%>
			</ul>
			<ul id="third18" style="display:block;width:720px">
				<li class="re">
					<a id="last43" class="type_on" href="javascript:void(0);" onClick="chg_type_last(43);chg_url('${ctx}/sport/goMatchCenter?timeType=tom&matchType=bk&rtype=r_main');">独赢盘＆ 让分 ＆ 大小 </a>
				</li>
				<li class="hp3">
					<a id="last44" class="type_out" href="javascript:void(0);" onclick="chg_type_last(44);chg_url('${ctx}/sport/goMatchCenter?timeType=tom&matchType=bk&rtype=p3');">综合过关</a>
				</li>
				<li class="result">
					<a id="result_class" class="type_out"  href="javascript:void(0);"onClick="chg_url('${ctx}/sport/getftRes');">赛果</a>
				</li>
				<%--<li class="result">
					<a id="result_class" class="type_out" target="body" href="${ctx}/sport/help/qa">体育规则</a>
				</li>--%>

			</ul>
			
			<ul id="third19" style="display:block;width:720px">
				<li class="re">
					<a id="last45" class="type_on" href="javascript:void(0);" onClick="chg_type_last(45);chg_url('${ctx}/sport/goMatchCenter2?matchType=tn&timeType=tom');">独赢 ＆ 让盘 ＆ 大小  </a>
				</li>
				<li class="hp3">
					<a id="last46" class="type_out" href=javascript:void(0); onClick="chg_type_last(46);chg_url('${ctx}/sport/goMatchCenter2?matchType=tn&timeType=tom');">综合过关</a>
				</li>
				<li class="hp3">
					<a id="last47" class="type_out" href=javascript:void(0); onClick="chg_type_last(47);chg_url('${ctx}/sport/goMatchCenter2?matchType=tn&timeType=tom');">冠军</a>
				</li>
				<li class="result"><a id="result_class" class="type_out" href="#" onClick="chg_url('${ctx}/sport/getftRes');">赛果</a></li>
				<%--<li class="result">
					<a id="result_class" class="type_out" target="body" href="${ctx}/sport/help/qa">体育规则</a>
				</li>--%>
			</ul>
			
			<ul id="third20" style="display:block;width:720px">
				<li class="re">
					<a id="last48" class="type_on" href="javascript:void(0);"
					
					onClick="chg_type_last(48);chg_url('${ctx}/sport/goMatchCenter2?matchType=vb&timeType=tom');">独赢 ＆ 让分 ＆ 大小 ＆ 单双  
					</a>
				</li>
				<li class="hp3">
					<a id="last49" class="type_out" href=javascript:void(0); onClick="chg_type_last(49);chg_url('${ctx}/sport/goMatchCenter2?matchType=vb&timeType=tom');">综合过关</a>
				</li>
				<li class="hp3">
					<a id="last50" class="type_out" href=javascript:void(0); onClick="chg_type_last(50);chg_url('${ctx}/sport/goMatchCenter2?matchType=vb&timeType=tom');">赛盘投注</a>
				</li>
				<li class="hp3">
					<a id="last51" class="type_out" href=javascript:void(0); onClick="chg_type_last(51);chg_url('${ctx}/sport/goMatchCenter2?matchType=vb&timeType=tom');">冠军</a>
				</li>
				<li class="result"><a id="result_class" class="type_out" href="#" onClick="chg_url('${ctx}/sport/getftRes');">赛果</a></li>
				<%--<li class="result">
					<a id="result_class" class="type_out" target="body" href="${ctx}/sport/help/qa">体育规则</a>
				</li>--%>
			</ul>
			
			<ul id="third21" style="display:block;width:720px">
				<li class="re"><a id="last52" class="type_on"
					href="javascript:void(0);"
					onClick="chg_type_last(52);chg_url('${ctx}/sport/goMatchCenter2?matchType=bm&timeType=tom');">独赢 ＆ 让局 ＆ 大小
					</a>
				</li>
				<li class="hp3">
					<a id="last53" class="type_out" href=javascript:void(0); onClick="chg_type_last(53);chg_url('${ctx}/sport/goMatchCenter2?matchType=bm&timeType=tom');">综合过关</a>
				</li>
				<li class="hp3">
					<a id="last54" class="type_out" href=javascript:void(0); onClick="chg_type_last(54);chg_url('${ctx}/sport/goMatchCenter2?matchType=bm&timeType=tom');">冠军</a>
				</li>
				<li class="result"><a id="result_class" class="type_out" href="#" onClick="chg_url('${ctx}/sport/getftRes');">赛果</a></li>
				<%--<li class="result">
					<a id="result_class" class="type_out" target="body" href="${ctx}/sport/help/qa">体育规则</a>
				</li>--%>
			</ul>
			<ul id="third22" style="display:block;width:720px">
				<li class="re"><a id="last55" class="type_on"
					href="javascript:void(0);"
					onClick="chg_type_last(55);chg_url('${ctx}/sport/goMatchCenter2?matchType=tt&timeType=tom');">独赢 ＆ 让局 ＆ 大小  
					</a>
				</li>
				<li class="hp3">
					<a id="last56" class="type_out" href=javascript:void(0); onClick="chg_type_last(56);chg_url('${ctx}/sport/goMatchCenter2?matchType=tt&timeType=tom');">综合过关</a>
				</li>
				<li class="hp3">
					<a id="last57" class="type_out" href=javascript:void(0); onClick="chg_type_last(57);chg_url('${ctx}/sport/goMatchCenter2?matchType=tt&timeType=tom');">冠军</a>
				</li>
				<li class="result"><a id="result_class" class="type_out" href="#" onClick="chg_url('${ctx}/sport/getftRes');">赛果</a></li>
				<%--<li class="result">
					<a id="result_class" class="type_out" target="body" href="${ctx}/sport/help/qa">体育规则</a>
				</li>--%>
			</ul>
			<ul id="third23" style="display:block;width:720px">
				<li class="re"><a id="last58" class="type_on"
					href="javascript:void(0);"
					onClick="chg_type_last(58);chg_url('${ctx}/sport/goMatchCenter2?matchType=bs&timeType=tom');">独赢 ＆ 让分 ＆ 大小 ＆ 单双  
					</a>
				</li>
				<li class="hp3">
					<a id="last59" class="type_out" href=javascript:void(0); onClick="chg_type_last(59);chg_url('${ctx}/sport/goMatchCenter2?matchType=bs&timeType=tom');">综合过关</a>
				</li>
				<li class="hp3">
					<a id="last60" class="type_out" href=javascript:void(0); onClick="chg_type_last(60);chg_url('${ctx}/sport/goMatchCenter2?matchType=bs&timeType=tom');">冠军</a>
				</li>
				<li class="result"><a id="result_class" class="type_out" href="#" onClick="chg_url('${ctx}/sport/getftRes');">赛果</a></li>
				<%--<li class="result">
					<a id="result_class" class="type_out" target="body" href="${ctx}/sport/help/qa">体育规则</a>
				</li>--%>
			</ul>
			<ul id="third24" style="display:block;width:720px">
				<li class="re"><a id="last61" class="type_on"
					href="javascript:void(0);"
					onClick="chg_type_last(61);chg_url('${ctx}/sport/goMatchCenter2?matchType=op&timeType=tom');">独赢 ＆ 让分 ＆ 大小 ＆ 单双  
					</a>
				</li>
				<li class="hp3">
					<a id="last62" class="type_out" href=javascript:void(0); onClick="chg_type_last(62);chg_url('${ctx}/sport/goMatchCenter2?matchType=op&timeType=tom');">综合过关</a>
				</li>
				<li class="hp3">
					<a id="last63" class="type_out" href=javascript:void(0); onClick="chg_type_last(63);chg_url('${ctx}/sport/goMatchCenter2?matchType=op&timeType=tom');">冠军</a>
				</li>
				<li class="result"><a id="result_class" class="type_out" href="#" onClick="chg_url('${ctx}/sport/getftRes');">赛果</a></li>
				<%--<li class="result">
					<a id="result_class" class="type_out" target="body" href="${ctx}/sport/help/qa">体育规则</a>
				</li>--%>
			</ul>
		</div>
		<!-- Early Menu End -->
		
		<div id="topMenu">
			<ul id="back">
				<li class="help" onmouseover="OnMouseOverEvent();"><a href="javascript:void(0)" style="cursor:hand">帮助</a><span></span></li>
			</ul>
			<div class="info" id="informaction" onmouseover="OnMouseOverEvent()" style="display: none;">
				<ul id="mose" onmouseout="OnMouseOutEvent();">
					<li class="help_on"><a href="javascript:void(0)">帮助</a></li>
					<li class="msg"><a href="javascript:void(0)" onclick="showMoreMsg('/gamefw1/sport/goMessageList');">&nbsp; 公告栏</a></li>
					<li class="roul"><a href="javascript:void(0)" onclick="winOpen('${ctx}/sport/help/qa',805,674,null,null,'规则说明')">&nbsp; 规则说明</a></li>
					<li class="odd"><a href="javascript:void(0)" 	>&nbsp; 盘口使用方法</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!--input  id=downloadBTN type=button style="width:80px;visibility:'hidden'"  onclick="onclickDown()" value="下载"-->
	<!--主选单-->
	<div id="mem_box">
	  <div id="mem_main">
	  <c:choose>
		<c:when test="${!empty webUser}">
			<span class="his"><a onclick="chg_url('${ctx}/sport/accountHis');" href="#" >帐户历史</a></span> | <span class="wag"><a onclick="chg_url('${ctx}/sport/accountOrder');" href="#" >交易状况</a> </span>
		</c:when>
		<c:otherwise>
			<span class="his"><a href="javascript:void(0)" onclick="alert('请先登录')" >帐户历史</a></span> | <span class="wag"><a href="javascript:void(0)" onclick="alert('请先登录')" >交易状况</a> </span>
		</c:otherwise>
	  </c:choose>
	  
	  </div>
	  <div id="credit_main">
			<span id="credit">人民币 <label id="userMoneyId">${webUser.userMoney}</label></span>
			<input name="" type="button" class="re_credit" value="" onClick="javascript:refreshRollCount();">
		</div>
	 </div>

	<!--帮助视窗-->
	<div id="qaView" style="display:none;" class="qaView">
		<!--div class="leg_head" onMousedown="initializedragie('legView')"></div-->
		<div>
			<iframe id="qaFrame" frameborder="no" border="0" allowtransparency="true"></iframe>
		</div>
		<div class="qa_foot"></div>
	</div>


	<div id="extra2">
		<a href="http://live228.com/app/member/mem_add.php?langx=zh-cn" target="_blank"></a>
	</div>
	<iframe id="reloadPHP" name="reloadPHP" width="0" height="0"></iframe>
	<iframe id="reloadPHP" name="reloadPHP1" width="0" height="0"></iframe>
</body>
<script type="text/javascript">
	setTimeout(refreshRollCount,60000);//60秒后执行

	function changeMatchCount(timeType, ft_rtype, bk_rtype, ft_count, bk_count) {
		document.getElementById(timeType + "_" + ft_rtype).innerHTML = ft_count;
		document.getElementById(timeType + "_" + bk_rtype).innerHTML = bk_count;
	}

	function leftRollFt() {
		chg_button_bg('FT', 'rb');
		chg_nav('re');
		chg_type_class(1);
		chg_navli('re');
		chg_third(1);
		chg_type_last(1);
		chg_url('${ctx}/sport/goMatchCenter?timeType=roll&matchType=ft&rtype=re');
	}

	function leftRollBk() {
		chg_button_bg('FT', 'rb');
		chg_nav('re');
		chg_type_class(2);
		chg_navli('re');
		chg_third(2);
		chg_type_last(5);
		chg_url('${ctx}/sport/goMatchCenter?timeType=roll&matchType=bk&rtype=re_main');
	}
</script>
</html>


