<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html style="overflow-x:hidden">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>webcom</title>
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
					<td><span class="font_sty001"><strong>特A下注</strong> </span></td>
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
					<td class="ball_ff"><span id="bl_1" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_1" class="input1" id="SP01" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm11">11</td>
					<td class="ball_ff"><span id="bl_2" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_2" class="input1" id="SP11" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm21">21</td>
					<td class="ball_ff"><span id="bl_3" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_3" class="input1" id="SP21" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm31">31</td>
					<td class="ball_ff"><span id="bl_4" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_4" class="input1" id="SP31" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm41">41</td>
					<td class="ball_ff"><span id="bl_5" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_5" class="input1"  id="SP41" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
				</tr>
				<tr class="tbtitle">
					<td class="ball_r" id="hm02">02</td>
					<td class="ball_ff"><span id="bl_6" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_6" class="input1" id="SP02" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_r" id="hm12">12</td>
					<td class="ball_ff"><span id="bl_7" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_7" class="input1" id="SP12" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm22">22</td>
					<td class="ball_ff"><span id="bl_8" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_8" class="input1" id="SP22" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm32">32</td>
					<td class="ball_ff"><span id="bl_9" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_9" class="input1" id="SP32" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm42">42</td>
					<td class="ball_ff"><span id="bl_10" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_10" class="input1" id="SP42" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
				</tr>
				<tr class="tbtitle">
					<td class="ball_b" id="hm03">03</td>
					<td class="ball_ff"><span id="bl_11" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_11" class="input1" id="SP03" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_r" id="hm13">13</td>
					<td class="ball_ff"><span id="bl_12" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_12" class="input1" id="SP13" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_r" id="hm23">23</td>
					<td class="ball_ff"><span id="bl_13" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_13" class="input1" id="SP23" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm33">33</td>
					<td class="ball_ff"><span id="bl_14" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_14" class="input1" id="SP33" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm43">43</td>
					<td class="ball_ff"><span id="bl_15" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_15" class="input1" id="SP43" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
				</tr>
				<tr class="tbtitle">
					<td class="ball_b" id="hm04">04</td>
					<td class="ball_ff"><span id="bl_16" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_16" class="input1" id="SP04" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm14">14</td>
					<td class="ball_ff"><span id="bl_17" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_17" class="input1" id="SP14" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_r" id="hm24">24</td>
					<td class="ball_ff"><span id="bl_18" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_18" class="input1" id="SP24" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_r" id="hm34">34</td>
					<td class="ball_ff"><span id="bl_19" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_19" class="input1" id="SP34" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm44">44</td>
					<td class="ball_ff"><span id="bl_20" rate="true">47.5</span>
					</td>
					<td class="ball_ff">
						<input name="Num_20" class="input1" id="SP44" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
				</tr>
				<tr class="tbtitle">
					<td class="ball_g" id="hm05">05</td>
					<td class="ball_ff"><span id="bl_21" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_21" class="input1" id="SP05" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm15">15</td>
					<td class="ball_ff"><span id="bl_22" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_22" class="input1" id="SP15" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm25">25</td>
					<td class="ball_ff"><span id="bl_23" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_23" class="input1" id="SP25" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_r" id="hm35">35</td>
					<td class="ball_ff"><span id="bl_24" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_24" class="input1" id="SP35" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_r" id="hm45">45</td>
					<td class="ball_ff"><span id="bl_25" rate="true">47.5</span>
					</td>
					<td class="ball_ff">
						<input name="Num_25" class="input1" id="SP45" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
				</tr>
				<tr class="tbtitle">
					<td class="ball_g" id="hm06">06</td>
					<td class="ball_ff"><span id="bl_26" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_26" class="input1" id="SP06" onkeydown="return Yh_Text.CheckNumber2()"  onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm16">16</td>
					<td class="ball_ff"><span id="bl_27" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_27" class="input1" id="SP16" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm26">26</td>
					<td class="ball_ff"><span id="bl_28" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_28" class="input1" id="SP26" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm36">36</td>
					<td class="ball_ff"><span id="bl_29" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_29" class="input1" id="SP36" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_r" id="hm46">46</td>
					<td class="ball_ff"><span id="bl_30" rate="true">47.5</span>
					</td>
					<td class="ball_ff">
						<input name="Num_30" class="input1" id="SP46" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
				</tr>
				<tr class="tbtitle">
					<td class="ball_r" id="hm07">07</td>
					<td class="ball_ff"><span id="bl_31" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_31" class="input1" id="SP07" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm17">17</td>
					<td class="ball_ff"><span id="bl_32" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_32" class="input1" id="SP17" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm27">27</td>
					<td class="ball_ff"><span id="bl_33" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_33" class="input1" id="SP27" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm37">37</td>
					<td class="ball_ff"><span id="bl_34" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_34" class="input1" id="SP37" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm47">47</td>
					<td class="ball_ff"><span id="bl_35" rate="true">47.5</span>
					</td>
					<td class="ball_ff">
						<input name="Num_35" class="input1" id="SP47" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
				</tr>
				<tr class="tbtitle">
					<td class="ball_r" id="hm08">08</td>
					<td class="ball_ff"><span id="bl_36" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_36" class="input1" id="SP08" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_r" id="hm18">18</td>
					<td class="ball_ff"><span id="bl_37" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_37" class="input1" id="SP18" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm28">28</td>
					<td class="ball_ff"><span id="bl_38" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_38" class="input1" id="SP28" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm38">38</td>
					<td class="ball_ff"><span id="bl_39" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_39" class="input1" id="SP38" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm48">48</td>
					<td class="ball_ff"><span id="bl_40" rate="true">47.5</span>
					</td>
					<td class="ball_ff">
						<input name="Num_40" class="input1" id="SP48" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
				</tr>
				<tr class="tbtitle">
					<td class="ball_b" id="hm09">09</td>
					<td class="ball_ff"><span id="bl_41" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_41" class="input1" id="SP09" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_r" id="hm19">19</td>
					<td class="ball_ff"><span id="bl_42" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_42" class="input1" id="SP19" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_r" id="hm29">29</td>
					<td class="ball_ff"><span id="bl_43" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_43" class="input1" id="SP29" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm39">39</td>
					<td class="ball_ff"><span id="bl_44" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_44" class="input1" id="SP39" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm49">49</td>
					<td class="ball_ff"><span id="bl_45" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_45" class="input1" id="SP49" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
				</tr>
				<tr class="tbtitle">
					<td class="ball_b" id="hm10">10</td>
					<td class="ball_ff"><span id="bl_46" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_46" class="input1" id="SP10" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm20">20</td>
					<td class="ball_ff"><span id="bl_47" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_47" class="input1" id="SP20" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_r" id="hm30">30</td>
					<td class="ball_ff"><span id="bl_48" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_48" class="input1" id="SP30" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_r" id="hm40">40</td>
					<td class="ball_ff"><span id="bl_49" rate="true">47.5</span></td>
					<td class="ball_ff">
						<input name="Num_49" class="input1" id="SP40" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_ff" colspan="3">&nbsp;</td>
				</tr>
				<tr class="tbtitle">
					<td class="ball_bg">单</td>
					<td class="ball_ff"><span id="bl_50" rate="true">1.93</span></td>
					<td class="ball_ff">
						<input name="Num_50" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_bg">大</td>
					<td class="ball_ff"><span id="bl_51" rate="true">1.93</span></td>
					<td class="ball_ff">
						<input name="Num_51" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this,51)" type="text" size="4">
					</td>
					<td class="ball_bg">合单</td>
					<td class="ball_ff"><span id="bl_52" rate="true">1.93</span></td>
					<td class="ball_ff">
						<input name="Num_52" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_bg ball_reb">红波</td>
					<td class="ball_ff"><span id="bl_53" rate="true">2.75</span></td>
					<td class="ball_ff">
						<input name="Num_53" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_bg ball_blue">蓝波</td>
					<td class="ball_ff"><span id="bl_54" rate="true">2.85</span></td>
					<td class="ball_ff">
						<input name="Num_54" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4" maxlength="6">
					</td>
				<tr>
					<td class="ball_bg">双</td>
					<td class="ball_ff"><span id="bl_55" rate="true">1.93</span></td>
					<td class="ball_ff">
						<input name="Num_55" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_bg">小</td>
					<td class="ball_ff"><span id="bl_56" rate="true">1.93</span></td>
					<td class="ball_ff">
						<input name="Num_56" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4"></td>
					<td class="ball_bg">合双</td>
					<td class="ball_ff"><span id="bl_57" rate="true">1.93</span></td>
					<td class="ball_ff">
						<input name="Num_57" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_bg ball_gree">绿波</td>
					<td class="ball_ff"><span id="bl_58" rate="true">2.85</span></td>
					<td class="ball_ff">
						<input name="Num_58" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="tbtitle" colspan="3"></td>
				</tr>
				<tr>
					<td class="ball_bg">家禽</td>
					<td class="ball_ff"><span id="bl_59" rate="true">1.93</span></td>
					<td class="ball_ff">
						<input name="Num_59" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4"></td>
					<td class="ball_bg">野兽</td>
					<td class="ball_ff"><span id="bl_60" rate="true">1.93</span></td>
					<td class="ball_ff">
						<input name="Num_60" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_bg">合大</td>
					<td class="ball_ff"><span id="bl_61" rate="true">1.93</span></td>
					<td class="ball_ff">
						<input name="Num_61" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_bg">合小</td>
					<td class="ball_ff"><span id="bl_62" rate="true">1.93</span></td>
					<td class="ball_ff">
						<input name="Num_62" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_bg">尾大</td>
					<td class="ball_ff"><span id="bl_63" rate="true">1.93</span></td>
					<td class="ball_ff">
						<input name="Num_63" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
				</tr>
				<tr>
					<td class="ball_bg">尾小</td>
					<td class="ball_ff"><span id="bl_64" rate="true">1.93</span></td>
					<td class="ball_ff">
						<input name="Num_64" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_bg">大单</td>
					<td class="ball_ff"><span id="bl_65" rate="true">3.8</span></td>
					<td class="ball_ff">
						<input name="Num_65" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_bg">小单</td>
					<td class="ball_ff"><span id="bl_66" rate="true">3.8</span></td>
					<td class="ball_ff">
						<input name="Num_66" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_bg">大双</td>
					<td class="ball_ff"><span id="bl_67" rate="true">3.8</span>
					</td>
					<td class="ball_ff">
						<input name="Num_67" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_bg">小双</td>
					<td class="ball_ff"><span id="bl_68" rate="true">3.8</span></td>
					<td class="ball_ff">
						<input name="Num_68" class="input1" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
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
		var cp_typeCode_temp="TM";
		var cp_cateCode_temp="";
		var cp_xzlxCode_temp="";

	</script>
	<%@include file="/commons/cp/jsp/cp_fast_set_footer.jsp" %>
</body>
</html>