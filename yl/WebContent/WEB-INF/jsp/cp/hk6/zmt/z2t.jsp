<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="overflow-x:hidden">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>webcom</title>
<%@include file="/commons/cp/jsp/cp_common.jsp"%>
</head>
<body id="HOP" ondragstart="window.event.returnValue=false" onselectstart="event.returnValue=false" oncontextmenu="window.event.returnValue=false">
	<noscript>
		<iframe src="*.htm" style="display:none"></iframe>
	</noscript>
	<div class="wrapCss_002">
		<table width="100%" class="tab_001" border="0" cellspacing="0" cellpadding="0">
			<tbody>
				<tr style='background: url("${resourceRoot}/cp/images/title_bg_004.gif") repeat-x left top;'>
					<td><span class="font_sty001"><strong>正2特下注</strong> </span></td>
					<%@include file="/commons/cp/jsp/hk6_results.jsp" %>
				</tr>
			</tbody>
		</table>

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
				<tr class="tbtitle">
					<td id="time_tm" nowrap="nowrap" colspan="15">
						<button class="button_a" onclick="javascript:location.href='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=ZMT&cpZuTypeCode=z1t';">
							<img width="16" height="16" align="absmiddle" src="${resourceRoot}/cp/images/add.gif?=594">正1特
						</button>
						<button class="button_b" onclick="javascript:location.href='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=ZMT&cpZuTypeCode=z2t';">
							<img width="16" height="16" align="absmiddle" src="${resourceRoot}/cp/images/add.gif?=594">正2特
						</button>
						<button class="button_a" onclick="javascript:location.href='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=ZMT&cpZuTypeCode=z3t';">
							<img width="16" height="16" align="absmiddle" src="${resourceRoot}/cp/images/add.gif?=594">正3特
						</button>
						<button class="button_a" onclick="javascript:location.href='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=ZMT&cpZuTypeCode=z4t';">
							<img width="16" height="16" align="absmiddle" src="${resourceRoot}/cp/images/add.gif?=594">正4特
						</button>
						<button class="button_a" onclick="javascript:location.href='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=ZMT&cpZuTypeCode=z5t';">
							<img width="16" height="16" align="absmiddle" src="${resourceRoot}/cp/images/add.gif?=594">正5特
						</button>
						<button class="button_a" onclick="javascript:location.href='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=ZMT&cpZuTypeCode=z6t';">
							<img width="16" height="16" align="absmiddle" src="${resourceRoot}/cp/images/add.gif?=594">正6特
						</button></td>
				</tr>
				<form name="order_form" id="order_form" action="${ctx}/order/goList?code=${results.gameTypeCode}" method="post" target="bet_order_frame" autocomplete="off">
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
					<td class="ball_ff"><span id="bl_118" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_118" class="input1" id="SP01" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>

					<td class="ball_g" id="hm11">11</td>
					<td class="ball_ff"><span id="bl_119" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_119" class="input1" id="SP11" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>

					<td class="ball_g" id="hm21">21</td>
					<td class="ball_ff"><span id="bl_120" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_120" class="input1" id="SP21" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>

					<td class="ball_b" id="hm31">31</td>
					<td class="ball_ff"><span id="bl_121" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_121" class="input1" id="SP31" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>

					<td class="ball_b" id="hm41">41</td>
					<td class="ball_ff"><span id="bl_122" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_122" class="input1" id="SP41" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>

				</tr>
				<tr class="tbtitle">
					<td align="center" class="ball_r" id="hm02">02</td>
					<td align="center" class="ball_ff" valign="middle"><span id="bl_123" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_123" class="input1" id="SP02" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>

					<td align="center" class="ball_r" id="hm12">12</td>
					<td class="ball_ff"><span id="bl_124" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_124" class="input1" id="SP12" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>

					<td class="ball_g" id="hm22">22</td>
					<td class="ball_ff"><span id="bl_125" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_125" class="input1" id="SP22" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm32">32</td>
					<td class="ball_ff"><span id="bl_126" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_126" class="input1" id="SP32" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm42">42</td>
					<td class="ball_ff"><span id="bl_127" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_127" class="input1" id="SP42" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
				</tr>
				<tr class="tbtitle">
					<td class="ball_b" id="hm03">03</td>
					<td align="center" class="ball_ff" valign="middle"><span id="bl_128" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_128" class="input1" id="SP03" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td align="center" class="ball_r" id="hm13">13</td>
					<td class="ball_ff"><span id="bl_129" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_129" class="input1" id="SP13" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td align="center" class="ball_r" id="hm23">23</td>
					<td class="ball_ff"><span id="bl_130" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_130" class="input1" id="SP23" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm33">33</td>
					<td class="ball_ff"><span id="bl_131" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_131" class="input1" id="SP33" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm43">43</td>
					<td class="ball_ff"><span id="bl_132" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_132" class="input1" id="SP43" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
				</tr>
				<tr class="tbtitle">
					<td class="ball_b" id="hm04">04</td>
					<td align="center" class="ball_ff" valign="middle"><span id="bl_133" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_133" class="input1" id="SP04" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm14">14</td>
					<td class="ball_ff"><span id="bl_134" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_134" class="input1" id="SP14" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td align="center" class="ball_r" id="hm24">24</td>
					<td class="ball_ff"><span id="bl_135" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_135" class="input1" id="SP24" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td align="center" class="ball_r" id="hm34">34</td>
					<td class="ball_ff"><span id="bl_136" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_136" class="input1" id="SP34" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm44">44</td>
					<td class="ball_ff"><span id="bl_137" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_137" class="input1" id="SP44" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
				</tr>
				<tr class="tbtitle">
					<td class="ball_g" id="hm05">05</td>
					<td align="center" class="ball_ff" valign="middle"><span id="bl_138" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_138" class="input1" id="SP05" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm15">15</td>
					<td class="ball_ff"><span id="bl_139" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_139" class="input1" id="SP15" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm25">25</td>
					<td class="ball_ff"><span id="bl_140" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_140" class="input1" id="SP25" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td align="center" class="ball_r" id="hm35">35</td>
					<td class="ball_ff"><span id="bl_141" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_141" class="input1" id="SP35" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td align="center" class="ball_r" id="hm45">45</td>
					<td class="ball_ff"><span id="bl_142" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_142" class="input1" id="SP45" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
				</tr>
				<tr class="tbtitle">
					<td class="ball_g" id="hm06">06</td>
					<td align="center" class="ball_ff" valign="middle"><span id="bl_143" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_143" class="input1" id="SP06" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm16">16</td>
					<td class="ball_ff"><span id="bl_144" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_144" class="input1" id="SP16" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm26">26</td>
					<td class="ball_ff"><span id="bl_145" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_145" class="input1" id="SP26" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm36">36</td>
					<td class="ball_ff"><span id="bl_146" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_146" class="input1" id="SP36" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td align="center" class="ball_r" id="hm46">46</td>
					<td class="ball_ff"><span id="bl_147" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_147" class="input1" id="SP46" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
				</tr>
				<tr class="tbtitle">
					<td align="center" class="ball_r" id="hm07">07</td>
					<td align="center" class="ball_ff" valign="middle"><span id="bl_148" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_148" class="input1" id="SP07" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm17">17</td>
					<td class="ball_ff"><span id="bl_149" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_149" class="input1" id="SP17" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm27">27</td>
					<td class="ball_ff"><span id="bl_150" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_150" class="input1" id="SP27" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm37">37</td>
					<td class="ball_ff"><span id="bl_151" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_151" class="input1" id="SP37" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm47">47</td>
					<td class="ball_ff"><span id="bl_152" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_152" class="input1" id="SP47" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
				</tr>
				<tr class="tbtitle">
					<td align="center" class="ball_r" id="hm08">08</td>
					<td align="center" class="ball_ff" valign="middle"><span id="bl_53" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_153" class="input1" id="SP08" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td align="center" class="ball_r" id="hm18">18</td>
					<td class="ball_ff"><span id="bl_154" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_154" class="input1" id="SP18" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm28">28</td>
					<td class="ball_ff"><span id="bl_155" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_155" class="input1" id="SP28" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm38">38</td>
					<td class="ball_ff"><span id="bl_156" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_156" class="input1" id="SP38" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm48">48</td>
					<td class="ball_ff"><span id="bl_157" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_157" class="input1" id="SP48" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
				</tr>
				<tr class="tbtitle">
					<td class="ball_b" id="hm09">09</td>
					<td align="center" class="ball_ff" valign="middle"><span id="bl_158" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_158" class="input1" id="SP09" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td align="center" class="ball_r" id="hm19">19</td>
					<td class="ball_ff"><span id="bl_159" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_159" class="input1" id="SP19" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td align="center" class="ball_r" id="hm29">29</td>
					<td class="ball_ff"><span id="bl_160" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_160" class="input1" id="SP29" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm39">39</td>
					<td class="ball_ff"><span id="bl_161" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_161" class="input1" id="SP39" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_g" id="hm49">49</td>
					<td class="ball_ff"><span id="bl_162" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_162" class="input1" id="SP49" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
				</tr>
				<tr class="tbtitle">
					<td class="ball_b" id="hm10">10</td>
					<td align="center" class="ball_ff" valign="middle"><span id="bl_163" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_163" class="input1" id="SP10" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_b" id="hm20">20</td>
					<td class="ball_ff"><span id="bl_164" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_164" class="input1" id="SP20" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td align="center" class="ball_r" id="hm30">30</td>
					<td class="ball_ff"><span id="bl_165" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_165" class="input1" id="SP30" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td align="center" class="ball_r" id="hm40">40</td>
					<td class="ball_ff"><span id="bl_166" rate="true">42</span>
					</td>
					<td class="ball_ff">
						<input name="Num_166" class="input1" id="SP40" onkeydown="return Yh_Text.CheckNumber2()" onblur="CheckInput(this)" type="text" size="4">
					</td>
					<td class="ball_ff" colspan="3">&nbsp;</td>
				</tr>
				<tr class="hset2">
					<td class="tbtitle" colspan="15"><input name="btnSubmit" class="button_a" id="btnSubmit" onclick="return ChkSubmit();" type="submit" value="下注"> &nbsp;&nbsp; <input name="Submit3" class="button_a" onclick="ClearData()" type="button"
						value="重设">
					</td>
				</tr>
				</form>

			</tbody>
		</table>

		<%@include file="/commons/cp/jsp/cp_hk6_right.jsp"%>
	</div>
	<script type="text/javascript">
		var cp_gameTypeCode_temp="${results.gameTypeCode}";
		var cp_typeCode_temp="ZMT";
		var cp_cateCode_temp="Z2T";
		var cp_xzlxCode_temp="";

	</script>
	<%@include file="/commons/cp/jsp/cp_fast_set_footer.jsp" %>
</body>
</html>
