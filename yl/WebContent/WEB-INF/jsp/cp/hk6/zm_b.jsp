<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html style="overflow-x:hidden">
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>webcom</title>
<%@include file="/commons/cp/jsp/cp_common.jsp"%>
</head>
<body ondragstart="window.event.returnValue=false" id="HOP" oncontextmenu="window.event.returnValue=false" onselectstart="event.returnValue=false">
	<div class="wrapCss_002">
		<table class="tab_001" border="0" cellSpacing="0" cellPadding="0" width="100%">
			<tbody>
				<tr style='background: url("${resourceRoot}/cp/images/title_bg_004.gif") repeat-x left top;'>
					<td><span class="font_sty001"><strong>正B下注</strong> </span></td>
					<%@include file="/commons/cp/jsp/hk6_results.jsp" %>
				</tr>
			</tbody>
		</table>
		<form name="order_form" id="order_form" action="${ctx}/order/goList?code=${results.gameTypeCode}" method="post" target="bet_order_frame" autocomplete="off">
			<table class="game_table" border="0" cellSpacing="1" cellPadding="0">
				<tbody>
					<tr class="tbtitle">
						<td height="25" align="left" id="time_td" colspan="15"><font>当前期数：<font class="colorDate"><b>${results.nextQs}</b> </font> 期&nbsp;&nbsp;&nbsp;&nbsp;下注金额：<span id="allgold">0</span>&nbsp;&nbsp;&nbsp;&nbsp;</font> <span class="STYLE1">距离封盘时间：</span> <font><strong><span
									id="span_dt_dt"><font color="#7aff00"><b>-</b>0</font> 分 <font color="#7aff00"><b>0</b> </font> 秒</span> </strong> </font> <script language="javascript">
										gametimeRef("${results.nextKjsj}",
												'span_dt_dt');
									</script></td>
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
						<td id="hm01" class="ball_r">01</td>
						<td class="ball_ff"><span id="bl_35600" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP01" class="input1" name="Num_35600" size="4" type="text"></td>
						<td id="hm11" class="ball_g">11</td>
						<td class="ball_ff"><span id="bl_35610" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP11" class="input1" name="Num_35610" size="4" type="text"></td>
						<td id="hm21" class="ball_g">21</td>
						<td class="ball_ff"><span id="bl_35620" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP21" class="input1" name="Num_35620" size="4" type="text">
						</td>
						<td id="hm31" class="ball_b">31</td>
						<td class="ball_ff"><span id="bl_35630" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP31" class="input1" name="Num_35630" size="4" type="text">
						</td>
						<td id="hm41" class="ball_b">41</td>
						<td class="ball_ff"><span id="bl_35640" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP41" class="input1" name="Num_35640" size="4" type="text">
						</td>
					</tr>
					<tr class="tbtitle">
						<td id="hm02" class="ball_r">02</td>
						<td class="ball_ff" vAlign="middle"><span id="bl_35601" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP02" class="input1" name="Num_35601" size="4" type="text">
						</td>
						<td id="hm12" class="ball_r">12</td>
						<td class="ball_ff"><span id="bl_35611" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP12" class="input1" name="Num_35611" size="4" type="text">
						</td>
						<td id="hm22" class="ball_g">22</td>
						<td class="ball_ff"><span id="bl_35621" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP22" class="input1" name="Num_35621" size="4" type="text">
						</td>
						<td id="hm32" class="ball_g">32</td>
						<td class="ball_ff"><span id="bl_35631" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP32" class="input1" name="Num_35631" size="4" type="text">
						</td>
						<td id="hm42" class="ball_b">42</td>
						<td class="ball_ff"><span id="bl_35641" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP42" class="input1" name="Num_35641" size="4" type="text">
						</td>
					</tr>
					<tr class="tbtitle">
						<td id="hm03" class="ball_b">03</td>
						<td class="ball_ff" vAlign="middle"><span id="bl_35602" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP03" class="input1" name="Num_35602" size="4" type="text">
						</td>
						<td id="hm13" class="ball_r">13</td>
						<td class="ball_ff"><span id="bl_35612" rate="true">7.15</span></td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP13" class="input1" name="Num_35612" size="4" type="text">
						</td>
						<td id="hm23" class="ball_r">23</td>
						<td class="ball_ff"><span id="bl_35622" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP23" class="input1" name="Num_35622" size="4" type="text">
						</td>
						<td id="hm33" class="ball_g">33</td>
						<td class="ball_ff"><span id="bl_35632" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP33" class="input1" name="Num_35632" size="4" type="text">
						</td>
						<td id="hm43" class="ball_g">43</td>
						<td class="ball_ff"><span id="bl_35642" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP43" class="input1" name="Num_35642" size="4" type="text">
						</td>
					</tr>
					<tr class="tbtitle">
						<td id="hm04" class="ball_b">04</td>
						<td class="ball_ff" vAlign="middle"><span id="bl_35603" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP04" class="input1" name="Num_35603" size="4" type="text">
						</td>
						<td id="hm14" class="ball_b">14</td>
						<td class="ball_ff"><span id="bl_35613" rate="true">7.15</span></td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP14" class="input1" name="Num_35613" size="4" type="text">
						</td>
						<td id="hm24" class="ball_r">24</td>
						<td class="ball_ff"><span id="bl_35623" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP24" class="input1" name="Num_35623" size="4" type="text">
						</td>
						<td id="hm34" class="ball_r">34</td>
						<td class="ball_ff"><span id="bl_35633" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP34" class="input1" name="Num_35633" size="4" type="text">
						</td>
						<td id="hm44" class="ball_g">44</td>
						<td class="ball_ff"><span id="bl_35643" rate="true">7.15</span></td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP44" class="input1" name="Num_35643" size="4" type="text">
						</td>
					</tr>
					<tr class="tbtitle">
						<td id="hm05" class="ball_g">05</td>
						<td class="ball_ff" vAlign="middle"><span id="bl_35604" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP05" class="input1" name="Num_35604" size="4" type="text">
						</td>
						<td id="hm15" class="ball_b">15</td>
						<td class="ball_ff"><span id="bl_35614" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP15" class="input1" name="Num_35614" size="4" type="text">
						</td>
						<td id="hm25" class="ball_b">25</td>
						<td class="ball_ff"><span id="bl_35624" rate="true">7.15</span></td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP25" class="input1" name="Num_35624" size="4" type="text">
						</td>
						<td id="hm35" class="ball_r">35</td>
						<td class="ball_ff"><span id="bl_35634" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP35" class="input1" name="Num_35634" size="4" type="text">
						</td>
						<td id="hm45" class="ball_r">45</td>
						<td class="ball_ff"><span id="bl_35644" rate="true">7.15</span></td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP45" class="input1" name="Num_35644" size="4" type="text">
						</td>
					</tr>
					<tr class="tbtitle">
						<td id="hm06" class="ball_g">06</td>
						<td class="ball_ff" vAlign="middle"><span id="bl_35605" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP06" class="input1" name="Num_35605" size="4" type="text">
						</td>
						<td id="hm16" class="ball_g">16</td>
						<td class="ball_ff"><span id="bl_35615" rate="true">7.15</span></td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP16" class="input1" name="Num_35615" size="4" type="text">
						</td>
						<td id="hm26" class="ball_b">26</td>
						<td class="ball_ff"><span id="bl_35625" rate="true">7.15</span></td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP26" class="input1" name="Num_35625" size="4" type="text">
						</td>
						<td id="hm36" class="ball_b">36</td>
						<td class="ball_ff"><span id="bl_35635" rate="true">7.15</span></td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP36" class="input1" name="Num_35635" size="4" type="text">
						</td>
						<td id="hm46" class="ball_r">46</td>
						<td class="ball_ff"><span id="bl_35645" rate="true">7.15</span></td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP46" class="input1" name="Num_35645" size="4" type="text">
						</td>
					</tr>
					<tr class="tbtitle">
						<td id="hm07" class="ball_r">07</td>
						<td class="ball_ff" vAlign="middle"><span id="bl_35606" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP07" class="input1" name="Num_35606" size="4" type="text">
						</td>
						<td id="hm17" class="ball_g">17</td>
						<td class="ball_ff"><span id="bl_35616" rate="true">7.15</span></td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP17" class="input1" name="Num_35616" size="4" type="text">
						</td>
						<td id="hm27" class="ball_g">27</td>
						<td class="ball_ff"><span id="bl_35626" rate="true">7.15</span></td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP27" class="input1" name="Num_35626" size="4" type="text">
						</td>
						<td id="hm37" class="ball_b">37</td>
						<td class="ball_ff"><span id="bl_35636" rate="true">7.15</span></td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP37" class="input1" name="Num_35636" size="4" type="text">
						</td>
						<td id="hm47" class="ball_b">47</td>
						<td class="ball_ff"><span id="bl_35646" rate="true">7.15</span></td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP47" class="input1" name="Num_35646" size="4" type="text">
						</td>
					</tr>
					<tr class="tbtitle">
						<td id="hm08" class="ball_r">08</td>
						<td class="ball_ff" vAlign="middle"><span id="bl_35607" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP08" class="input1" name="Num_35607" size="4" type="text"></td>
						<td id="hm18" class="ball_r">18</td>
						<td class="ball_ff"><span id="bl_35617" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP18" class="input1" name="Num_35617" size="4" type="text"></td>
						<td id="hm28" class="ball_g">28</td>
						<td class="ball_ff"><span id="bl_35627" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP28" class="input1" name="Num_35627" size="4" type="text"></td>
						<td id="hm38" class="ball_g">38</td>
						<td class="ball_ff"><span id="bl_35637" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP38" class="input1" name="Num_35637" size="4" type="text"></td>
						<td id="hm48" class="ball_b">48</td>
						<td class="ball_ff"><span id="bl_35647" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP48" class="input1" name="Num_35647" size="4" type="text"></td>
					</tr>
					<tr class="tbtitle">
						<td id="hm09" class="ball_b">09</td>
						<td class="ball_ff" vAlign="middle"><span id="bl_35608" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP09" class="input1" name="Num_35608" size="4" type="text"></td>
						<td id="hm19" class="ball_r">19</td>
						<td class="ball_ff"><span id="bl_35618" rate="true">7.15</span></td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP19" class="input1" name="Num_35618" size="4" type="text">
						</td>
						<td id="hm29" class="ball_r">29</td>
						<td class="ball_ff"><span id="bl_35628" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP29" class="input1" name="Num_35628" size="4" type="text">
						</td>
						<td id="hm39" class="ball_g">39</td>
						<td class="ball_ff"><span id="bl_35638" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP39" class="input1" name="Num_35638" size="4" type="text">
						</td>
						<td id="hm49" class="ball_g">49</td>
						<td class="ball_ff"><span id="bl_35648" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP49" class="input1" name="Num_35648" size="4" type="text"></td>
					</tr>
					<tr class="tbtitle">
						<td id="hm10" class="ball_b">10</td>
						<td class="ball_ff" vAlign="middle"><span id="bl_35609" rate="true">7.15</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP10" class="input1" name="Num_35609" size="4" type="text">
						</td>
						<td id="hm20" class="ball_b">20</td>
						<td class="ball_ff"><span id="bl_35619" rate="true">7.15</span></td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP20" class="input1" name="Num_35619" size="4" type="text">
						</td>
						<td id="hm30" class="ball_r">30</td>
						<td class="ball_ff"><span id="bl_35629" rate="true">7.15</span></td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP30" class="input1" name="Num_35629" size="4" type="text">
						</td>
						<td id="hm40" class="ball_r">40</td>
						<td class="ball_ff"><span id="bl_35639" rate="true">7.15</span></td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" id="SP40" class="input1" name="Num_35639" size="4" type="text">
						</td>
						<td class="ball_ff" rowSpan="2" colSpan="3">&nbsp;</td>
					</tr>
					<tr class="tbtitle">
						<td class="tbtitle">总单</td>
						<td class="ball_ff"><span id="bl_35650" rate="true">1.93</span><span></span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" class="input1" name="Num_35650" size="4" type="text">
						</td>
						<td class="tbtitle">总双</td>
						<td class="ball_ff"><span id="bl_35651" rate="true">1.93</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" class="input1" name="Num_35651" size="4" type="text">
						</td>
						<td class="tbtitle">总大</td>
						<td class="ball_ff"><span id="bl_35652" rate="true">1.93</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" class="input1" name="Num_35652" size="4" type="text">
						</td>
						<td class="tbtitle">总小</td>
						<td class="ball_ff"><span id="bl_35653" rate="true">1.93</span>
						</td>
						<td class="ball_ff"><input onblur="CheckInput(this)" onkeydown="return Yh_Text.CheckNumber2()" class="input1" name="Num_35653" size="4" type="text"></td>
					</tr>
					<tr class="hset2">
						<td class="tbtitle" colSpan="15"><input id="btnSubmit" class="button_a" onclick="return ChkSubmit();" name="btnSubmit" value="下注" type="submit"> &nbsp;&nbsp; <input class="button_a" onclick="ClearData()" name="Submit3" value="重设"
							type="button">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<%@include file="/commons/cp/jsp/cp_hk6_right.jsp"%>
	</div>
	<script type="text/javascript">
		var cp_gameTypeCode_temp="${results.gameTypeCode}";
		var cp_typeCode_temp="ZM_B";
		var cp_cateCode_temp="";
		var cp_xzlxCode_temp="";

	</script>
	<%@include file="/commons/cp/jsp/cp_fast_set_footer.jsp"%>
</body>
</html>