<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html style="overflow-x:hidden">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>特码B</title>
<%@include file="/commons/cp/jsp/cp_common.jsp" %>
</head>
<body id="HOP" ondragstart="window.event.returnValue=false" oncontextmenu="window.event.returnValue=false" onselectstart="event.returnValue=false">
	<noscript>
		<iframe src="*.htm" style="display:none"></iframe>
	</noscript>
	<div class="wrapCss_002">
		<table width="100%" class="tab_001" border="0" cellspacing="0" cellpadding="0">
			<tbody>
				<tr>
					<td><span class="font_sty001"><strong>特B下注</strong> </span></td>
					<%@include file="/commons/cp/jsp/hk6_results.jsp" %>
				</tr>
			</tbody>
		</table>
		<form name="order_form" id="order_form" action="${ctx}/order/goList?code=${results.gameTypeCode}"
					method="post" target="bet_order_frame" autocomplete="off">
		<table class="game_table" border="0" cellspacing="1" cellpadding="0">
			<tbody>
				<tr class="tbtitle">
					<td height="25" align="left" id="time_td" colspan="15">
							<font>当前期数：<font class="colorDate"><b>${results.nextQs}</b></font>
							期&nbsp;&nbsp;&nbsp;&nbsp;下注金额：<span id="allgold">0</span>&nbsp;&nbsp;&nbsp;&nbsp;</font>
							<span class="STYLE1">距离封盘时间：</span> <font><strong><span
								id="span_dt_dt"><font color="#7aff00"><b>-</b>0</font> 分
									<font color="#7aff00"><b>0</b> </font> 秒</span> </strong></font>
									<script language="javascript">
										gametimeRef("${results.nextKjsj}",'span_dt_dt');
									</script>
					</td>
				</tr>
				<tr class="tbtitle2">
					<td>号码</td>
					<td>赔率</td>
					<td>金额</td>
					<td>号码</td>
					<td>赔率</td>
					<td>金额</td>
					<td>号码</td>
					<td>赔率</td>
					<td>金额</td>
					<td>号码</td>
					<td>赔率</td>
					<td>金额</td>
					<td>号码</td>
					<td>赔率</td>
					<td>金额</td>
				</tr>
				<tr class="tbtitle">
					<td class="ball_r" id="hm01">01</td>
					<td class="ball_ff"><span id="bl_35500" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35500" class="input1" id="SP01" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm11">11</td>
					<td class="ball_ff"><span id="bl_35510" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35510" class="input1" id="SP11" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm21">21</td>
					<td class="ball_ff"><span id="bl_35520" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35520" class="input1" id="SP21" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm31">31</td>
					<td class="ball_ff"><span id="bl_35530" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35530" class="input1" id="SP31" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm41">41</td>
					<td class="ball_ff"><span id="bl_35540" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35540" class="input1"  id="SP41" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
				</tr>
				<tr class="tbtitle">
					<td class="ball_r" id="hm02">02</td>
					<td class="ball_ff"><span id="bl_35501" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35501" class="input1" id="SP02" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_r" id="hm12">12</td>
					<td class="ball_ff"><span id="bl_35511" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35511" class="input1" id="SP12" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm22">22</td>
					<td class="ball_ff"><span id="bl_35521" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35521" class="input1" id="SP22" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm32">32</td>
					<td class="ball_ff"><span id="bl_35531" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35531" class="input1" id="SP32" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm42">42</td>
					<td class="ball_ff"><span id="bl_35541" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35541" class="input1" id="SP42" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
				</tr>
				<tr class="tbtitle">
					<td class="ball_b" id="hm03">03</td>
					<td class="ball_ff"><span id="bl_35502" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35502" class="input1" id="SP03" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_r" id="hm13">13</td>
					<td class="ball_ff"><span id="bl_35512" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35512" class="input1" id="SP13" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_r" id="hm23">23</td>
					<td class="ball_ff"><span id="bl_35522" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35522" class="input1" id="SP23" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm33">33</td>
					<td class="ball_ff"><span id="bl_35532" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35532" class="input1" id="SP33" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm43">43</td>
					<td class="ball_ff"><span id="bl_35542" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35542" class="input1" id="SP43" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
				</tr>
				<tr class="tbtitle">
					<td class="ball_b" id="hm04">04</td>
					<td class="ball_ff"><span id="bl_35503" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35503" class="input1" id="SP04" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm14">14</td>
					<td class="ball_ff"><span id="bl_35513" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35513" class="input1" id="SP14" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_r" id="hm24">24</td>
					<td class="ball_ff"><span id="bl_35523" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35523" class="input1" id="SP24" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_r" id="hm34">34</td>
					<td class="ball_ff"><span id="bl_35533" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35533" class="input1" id="SP34" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm44">44</td>
					<td class="ball_ff"><span id="bl_35543" rate="true">47.5</span>
					</td>
					<td class="ball_ff">
						<input name="Num_35543" class="input1" id="SP44" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
				</tr>
				<tr class="tbtitle">
					<td class="ball_g" id="hm05">05</td>
					<td class="ball_ff"><span id="bl_35504" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35504" class="input1" id="SP05" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm15">15</td>
					<td class="ball_ff"><span id="bl_35514" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35514" class="input1" id="SP15" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm25">25</td>
					<td class="ball_ff"><span id="bl_35524" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35524" class="input1" id="SP25" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_r" id="hm35">35</td>
					<td class="ball_ff"><span id="bl_35534" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35534" class="input1" id="SP35" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_r" id="hm45">45</td>
					<td class="ball_ff"><span id="bl_35544" rate="true">47.5</span>
					</td>
					<td class="ball_ff">
						<input name="Num_35544" class="input1" id="SP45" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
				</tr>
				<tr class="tbtitle">
					<td class="ball_g" id="hm06">06</td>
					<td class="ball_ff"><span id="bl_35505" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35505" class="input1" id="SP06" onkeydown="return Yh_Text.CheckNumber2()"  onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm16">16</td>
					<td class="ball_ff"><span id="bl_35515" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35515" class="input1" id="SP16" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm26">26</td>
					<td class="ball_ff"><span id="bl_35525" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35525" class="input1" id="SP26" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm36">36</td>
					<td class="ball_ff"><span id="bl_35535" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35535" class="input1" id="SP36" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_r" id="hm46">46</td>
					<td class="ball_ff"><span id="bl_35545" rate="true">47.5</span>
					</td>
					<td class="ball_ff">
						<input name="Num_35545" class="input1" id="SP46" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
				</tr>
				<tr class="tbtitle">
					<td class="ball_r" id="hm07">07</td>
					<td class="ball_ff"><span id="bl_35506" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35506" class="input1" id="SP07" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm17">17</td>
					<td class="ball_ff"><span id="bl_35516" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35516" class="input1" id="SP17" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm27">27</td>
					<td class="ball_ff"><span id="bl_35526" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35526" class="input1" id="SP27" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm37">37</td>
					<td class="ball_ff"><span id="bl_35536" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35536" class="input1" id="SP37" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm47">47</td>
					<td class="ball_ff"><span id="bl_35546" rate="true">47.5</span>
					</td>
					<td class="ball_ff">
						<input name="Num_35546" class="input1" id="SP47" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
				</tr>
				<tr class="tbtitle">
					<td class="ball_r" id="hm08">08</td>
					<td class="ball_ff"><span id="bl_35507" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35507" class="input1" id="SP08" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_r" id="hm18">18</td>
					<td class="ball_ff"><span id="bl_35517" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35517" class="input1" id="SP18" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm28">28</td>
					<td class="ball_ff"><span id="bl_35527" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35527" class="input1" id="SP28" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm38">38</td>
					<td class="ball_ff"><span id="bl_35537" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35537" class="input1" id="SP38" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm48">48</td>
					<td class="ball_ff"><span id="bl_35547" rate="true">47.5</span>
					</td>
					<td class="ball_ff">
						<input name="Num_35547" class="input1" id="SP48" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
				</tr>
				<tr class="tbtitle">
					<td class="ball_b" id="hm09">09</td>
					<td class="ball_ff"><span id="bl_35508" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35508" class="input1" id="SP09" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_r" id="hm19">19</td>
					<td class="ball_ff"><span id="bl_35518" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35518" class="input1" id="SP19" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_r" id="hm29">29</td>
					<td class="ball_ff"><span id="bl_35528" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35528" class="input1" id="SP29" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm39">39</td>
					<td class="ball_ff"><span id="bl_35538" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35538" class="input1" id="SP39" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm49">49</td>
					<td class="ball_ff"><span id="bl_35548" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35548" class="input1" id="SP49" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
				</tr>
				<tr class="tbtitle">
					<td class="ball_b" id="hm10">10</td>
					<td class="ball_ff"><span id="bl_35509" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35509" class="input1" id="SP10" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm20">20</td>
					<td class="ball_ff"><span id="bl_35519" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35519" class="input1" id="SP20" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_r" id="hm30">30</td>
					<td class="ball_ff"><span id="bl_35529" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35529" class="input1" id="SP30" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_r" id="hm40">40</td>
					<td class="ball_ff"><span id="bl_35539" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_35539" class="input1" id="SP40" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_ff" colspan="3">&nbsp;</td>
				</tr>
				<tr class="tbtitle">
					<td class="ball_bg">单</td>
					<td class="ball_ff"><span id="bl_35550" rate="true">1.93</span></td>
					<td class="ball_ff">
						<input name="Num_35550" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_bg">大</td>
					<td class="ball_ff"><span id="bl_35556" rate="true">1.93</span></td>
					<td class="ball_ff">
						<input name="Num_35556" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this,51)" type="text" size="4">
					</td>
					<td class="ball_bg">合单</td>
					<td class="ball_ff"><span id="bl_35552" rate="true">1.93</span></td>
					<td class="ball_ff">
						<input name="Num_35552" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_bg ball_reb">红波</td>
					<td class="ball_ff"><span id="bl_35565" rate="true">2.75</span></td>
					<td class="ball_ff">
						<input name="Num_35565" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_bg ball_blue">蓝波</td>
					<td class="ball_ff"><span id="bl_35567" rate="true">2.85</span></td>
					<td class="ball_ff">
						<input name="Num_35567" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4" maxlength="6">
					</td>
				<tr>
					<td class="ball_bg">双</td>
					<td class="ball_ff"><span id="bl_35551" rate="true">1.93</span></td>
					<td class="ball_ff">
						<input name="Num_35551" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_bg">小</td>
					<td class="ball_ff"><span id="bl_35560" rate="true">1.93</span></td>
					<td class="ball_ff">
						<input name="Num_35560" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4"></td>
					<td class="ball_bg">合双</td>
					<td class="ball_ff"><span id="bl_35553" rate="true">1.93</span></td>
					<td class="ball_ff">
						<input name="Num_35553" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_bg ball_gree">绿波</td>
					<td class="ball_ff"><span id="bl_35566" rate="true">2.85</span></td>
					<td class="ball_ff">
						<input name="Num_35566" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="tbtitle" colspan="3"></td>
				</tr>
				<tr>
					<td class="ball_bg">家禽</td>
					<td class="ball_ff"><span id="bl_35559" rate="true">1.93</span></td>
					<td class="ball_ff">
						<input name="Num_35559" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4"></td>
					<td class="ball_bg">野兽</td>
					<td class="ball_ff"><span id="bl_35568" rate="true">1.93</span></td>
					<td class="ball_ff">
						<input name="Num_35568" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_bg">合大</td>
					<td class="ball_ff"><span id="bl_35554" rate="true">1.93</span></td>
					<td class="ball_ff">
						<input name="Num_35554" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_bg">合小</td>
					<td class="ball_ff"><span id="bl_35555" rate="true">1.93</span></td>
					<td class="ball_ff">
						<input name="Num_35555" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_bg">尾大</td>
					<td class="ball_ff"><span id="bl_35563" rate="true">1.93</span></td>
					<td class="ball_ff">
						<input name="Num_35563" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
				</tr>
				<tr>
					<td class="ball_bg">尾小</td>
					<td class="ball_ff"><span id="bl_35564" rate="true">1.93</span></td>
					<td class="ball_ff">
						<input name="Num_35564" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_bg">大单</td>
					<td class="ball_ff"><span id="bl_35557" rate="true">3.8</span></td>
					<td class="ball_ff">
						<input name="Num_35557" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_bg">小单</td>
					<td class="ball_ff"><span id="bl_35561" rate="true">3.8</span></td>
					<td class="ball_ff">
						<input name="Num_35561" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_bg">大双</td>
					<td class="ball_ff"><span id="bl_35558" rate="true">3.8</span>
					</td>
					<td class="ball_ff">
						<input name="Num_35558" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_bg">小双</td>
					<td class="ball_ff"><span id="bl_35562" rate="true">3.8</span></td>
					<td class="ball_ff">
						<input name="Num_35562" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
				</tr>
				<tr class="hset2">
					<td class="tbtitle" colspan="15">
						<input name="btnSubmit" class="button_a" id="btnSubmit" onclick="return ChkSubmit();" type="submit" value="下注"> &nbsp;&nbsp;
						<input name="Submit3" class="button_a" onclick="ClearData()" type="button" value="重设">
					</td>
				</tr>
			</tbody>
		</table>
		</form>
		<%@include file="/commons/cp/jsp/cp_hk6_right.jsp" %>
	</div>
	<script type="text/javascript">
		var cp_gameTypeCode_temp="${results.gameTypeCode}";
		var cp_typeCode_temp="TM_B";
		var cp_cateCode_temp="";
		var cp_xzlxCode_temp="";

	</script>
	<%@include file="/commons/cp/jsp/cp_fast_set_footer.jsp" %>
</body>
</html>