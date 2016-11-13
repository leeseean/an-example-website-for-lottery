<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html style="overflow-x:hidden">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>webcom</title>
<%@include file="/commons/cp/jsp/cp_common.jsp" %>
<body >
	<div class="wrapCss_004">
		<table class="tab_001" border="0" cellSpacing="0" cellPadding="0" width="100%">
			<tbody>
				<tr style='background: url("${resourceRoot}/cp/images/title_bg_004.gif") repeat-x left top;'>
					<td><span class="font_sty001"><strong>半波下注</strong> </span></td>
					<%@include file="/commons/cp/jsp/hk6_results.jsp" %>
				</tr>
			</tbody>
		</table>
		<table class="game_table" border="0" cellSpacing="1" cellPadding="0">

		</table>

		<table class="game_table" border="0" cellSpacing="1" cellPadding="2" style="background-color:#f1f1f;">
			<tbody>
				<tr class="tbtitle">
					<td id="time_tm" colSpan="4" noWrap="nowrap">
						<button style="height: 22px;" class="button_b" onclick="document.location.href='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=BB&cpZuTypeCode=BB';">
							<img align="absmiddle" src="${resourceRoot}/cp/images/add.gif?=594" width="16" height="16">半波
						</button>
						<button style="height: 22px;" class="button_a" onclick="document.location.href='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=BB&cpZuTypeCode=BBB';">
							<img align="absmiddle" src="${resourceRoot}/cp/images/add.gif?=594" width="16" height="16">半半波
						</button>
					</td>
				</tr>
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

				<form name="order_form" id="order_form" action="${ctx}/order/goList?code=HK6"
					method="post" target="bet_order_frame" autocomplete="off">
				<tr class="tbtitle2">
					<td>号码</td>
					<td>赔率</td>
					<td>金额</td>
					<td>号码</td>
				</tr>
				<tr class="tbtitle">
					<td width="41"><font color="#ff0000">红单</font></td>
					<td class="ball_ff" width="50"><span id="bl_545" rate="true">5.61</span>
					</td>
					<td class="ball_ff" width="55">
						<input onblur="CheckInput(this)" style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2();" class="input1"name="Num_545" size="4" type="text">
					</td>
					<td><span style="color: rgb(0, 0, 0);" class="ball_r2">01</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">07</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">13</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">19</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">23</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">29</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">35</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">45</span>
					</td>
				</tr>
				<tr class="tbtitle">
					<td width="41"><font color="#ff0000">红双</font></td>
					<td class="ball_ff" width="50"><span id="bl_546" rate="true">5.05</span>
					</td>
					<td class="ball_ff" width="55">
						<input onblur="CheckInput(this)" style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2();" class="input1"name="Num_546" size="4" type="text">
					</td>
					<td><span style="color: rgb(0, 0, 0);" class="ball_r2">02</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">08</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">12</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">18</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">24</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">30</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">34</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">40</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">46</span>
					</td>
				</tr>
				<tr class="tbtitle">
					<td width="41"><font color="#ff0000">红大</font></td>
					<td class="ball_ff" width="50"><span id="bl_547" rate="true">6.51</span>
					</td>
					<td class="ball_ff" width="55">
						<input onblur="CheckInput(this)" style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2();" class="input1"name="Num_547" size="4" type="text">
					</td>
					<td><span style="color: rgb(0, 0, 0);" class="ball_r2">29</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">30</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">34</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">35</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">40</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">45</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">46</span>
					</td>
				</tr>
				<tr class="tbtitle">
					<td width="41"><font color="#ff0000">红小</font></td>
					<td class="ball_ff" width="50"><span id="bl_548" rate="true">4.52</span>
					</td>
					<td class="ball_ff" width="55">
						<input onblur="CheckInput(this)" style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2();" class="input1"name="Num_548" size="4" type="text">
					</td>
					<td><span style="color: rgb(0, 0, 0);" class="ball_r2">01</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">02</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">07</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">08</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">12</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">13</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">18</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">19</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">23</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">24</span>
					</td>
				</tr>
				<tr class="tbtitle">
					<td width="41"><font color="#00b000">绿单</font></td>
					<td class="ball_ff" width="50"><span id="bl_549" rate="true">5.61</span>
					</td>
					<td class="ball_ff" width="55">
						<input onblur="CheckInput(this)" style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2();" class="input1"name="Num_549" size="4" type="text">
					</td>
					<td><span style="color: rgb(0, 0, 0);" class="ball_g2">05</span><span
						style="color: rgb(0, 0, 0);" class="ball_g2">11</span><span
						style="color: rgb(0, 0, 0);" class="ball_g2">17</span><span
						style="color: rgb(0, 0, 0);" class="ball_g2">21</span><span
						style="color: rgb(0, 0, 0);" class="ball_g2">27</span><span
						style="color: rgb(0, 0, 0);" class="ball_g2">33</span><span
						style="color: rgb(0, 0, 0);" class="ball_g2">39</span><span
						style="color: rgb(0, 0, 0);" class="ball_g2">43</span>
					</td>
				</tr>
				<tr class="tbtitle">
					<td width="41"><font color="#00b000">绿双</font></td>
					<td class="ball_ff" width="50"><span id="bl_550" rate="true">6.51</span>
					</td>
					<td class="ball_ff" width="55">
						<input onblur="CheckInput(this)" style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2();" class="input1"name="Num_550" size="4" type="text">
					</td>
					<td><span style="color: rgb(0, 0, 0);" class="ball_g2">06</span><span
						style="color: rgb(0, 0, 0);" class="ball_g2">16</span><span
						style="color: rgb(0, 0, 0);" class="ball_g2">22</span><span
						style="color: rgb(0, 0, 0);" class="ball_g2">28</span><span
						style="color: rgb(0, 0, 0);" class="ball_g2">32</span><span
						style="color: rgb(0, 0, 0);" class="ball_g2">38</span><span
						style="color: rgb(0, 0, 0);" class="ball_g2">44</span>
					</td>
				</tr>
				<tr class="tbtitle">
					<td width="41"><font color="#00b000">绿大</font></td>
					<td class="ball_ff" width="50"><span id="bl_552" rate="true">5.61</span>
					</td>
					<td class="ball_ff" width="55">
						<input onblur="CheckInput(this)" style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2();" class="input1"name="Num_552" size="4" type="text">
					</td>
					<td><span style="color: rgb(0, 0, 0);" class="ball_g2">27</span><span
						style="color: rgb(0, 0, 0);" class="ball_g2">28</span><span
						style="color: rgb(0, 0, 0);" class="ball_g2">32</span><span
						style="color: rgb(0, 0, 0);" class="ball_g2">33</span><span
						style="color: rgb(0, 0, 0);" class="ball_g2">38</span><span
						style="color: rgb(0, 0, 0);" class="ball_g2">39</span><span
						style="color: rgb(0, 0, 0);" class="ball_g2">43</span><span
						style="color: rgb(0, 0, 0);" class="ball_g2">44</span>
					</td>
				</tr>
				<tr class="tbtitle">
					<td width="41"><font color="#00b000">绿小</font></td>
					<td class="ball_ff" width="50"><span id="bl_551" rate="true">6.51</span></td>
					<td class="ball_ff" width="55">
						<input onblur="CheckInput(this)" style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2();" class="input1"name="Num_551" size="4" type="text">
					</td>
					<td>
						<span style="color: rgb(0, 0, 0);" class="ball_g2">05</span>
						<span style="color: rgb(0, 0, 0);" class="ball_g2">06</span>
						<span style="color: rgb(0, 0, 0);" class="ball_g2">11</span>
						<span style="color: rgb(0, 0, 0);" class="ball_g2">16</span>
						<span style="color: rgb(0, 0, 0);" class="ball_g2">17</span>
						<span style="color: rgb(0, 0, 0);" class="ball_g2">21</span>
						<span style="color: rgb(0, 0, 0);" class="ball_g2">22</span>
					</td>
				</tr>
				<tr class="tbtitle">
					<td width="41"><font color="#0000ff">蓝单</font></td>
					<td class="ball_ff" width="50"><span id="bl_553" rate="true">5.61</span></td>
					<td class="ball_ff" width="55">
						<input onblur="CheckInput(this)" style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2();" class="input1"name="Num_553" size="4" type="text">
					</td>
					<td><span style="color: rgb(0, 0, 0);" class="ball_b2">03</span><span
						style="color: rgb(0, 0, 0);" class="ball_b2">09</span><span
						style="color: rgb(0, 0, 0);" class="ball_b2">15</span><span
						style="color: rgb(0, 0, 0);" class="ball_b2">25</span><span
						style="color: rgb(0, 0, 0);" class="ball_b2">31</span><span
						style="color: rgb(0, 0, 0);" class="ball_b2">37</span><span
						style="color: rgb(0, 0, 0);" class="ball_b2">41</span><span
						style="color: rgb(0, 0, 0);" class="ball_b2">47</span>
					</td>
				</tr>
				<tr class="tbtitle">
					<td width="41"><font color="#0000ff">蓝双</font></td>
					<td class="ball_ff" width="50"><span id="bl_554" rate="true">5.61</span>
					</td>
					<td class="ball_ff" width="55">
						<input	onblur="CheckInput(this)" style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2();" class="input1"	name="Num_554" size="4" type="text">
					</td>
					<td>
						<span style="color: rgb(0, 0, 0);" class="ball_b2">04</span><span
						style="color: rgb(0, 0, 0);" class="ball_b2">10</span><span
						style="color: rgb(0, 0, 0);" class="ball_b2">14</span><span
						style="color: rgb(0, 0, 0);" class="ball_b2">20</span><span
						style="color: rgb(0, 0, 0);" class="ball_b2">26</span><span
						style="color: rgb(0, 0, 0);" class="ball_b2">36</span><span
						style="color: rgb(0, 0, 0);" class="ball_b2">42</span><span
						style="color: rgb(0, 0, 0);" class="ball_b2">48</span>
					</td>
				</tr>
				<tr class="tbtitle">
					<td width="41"><font color="#0000ff">蓝大</font></td>
					<td class="ball_ff" width="50"><span id="bl_555" rate="true">5.05</span></td>
					<td class="ball_ff" width="55">
						<input onblur="CheckInput(this)" style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2();" class="input1"name="Num_555" size="4" type="text">
					</td>
					<td>
						<span style="color: rgb(0, 0, 0);" class="ball_b2">25</span>
						<span style="color: rgb(0, 0, 0);" class="ball_b2">26</span>
						<span style="color: rgb(0, 0, 0);" class="ball_b2">31</span>
						<span style="color: rgb(0, 0, 0);" class="ball_b2">36</span>
						<span style="color: rgb(0, 0, 0);" class="ball_b2">37</span>
						<span style="color: rgb(0, 0, 0);" class="ball_b2">41</span>
						<span style="color: rgb(0, 0, 0);" class="ball_b2">42</span>
						<span style="color: rgb(0, 0, 0);" class="ball_b2">47</span>
						<span style="color: rgb(0, 0, 0);" class="ball_b2">48</span>
					</td>
				</tr>
				<tr class="tbtitle">
					<td width="41"><font color="#0000ff">蓝小</font></td>
					<td class="ball_ff" width="50"><span id="bl_556" rate="true">6.51</span></td>
					<td class="ball_ff" width="55">
						<input onblur="CheckInput(this)" style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2();" class="input1"name="Num_556" size="4" type="text"></td>
					<td>
						<span style="color: rgb(0, 0, 0);" class="ball_b2">03</span>
						<span style="color: rgb(0, 0, 0);" class="ball_b2">04</span>
						<span style="color: rgb(0, 0, 0);" class="ball_b2">09</span>
						<span style="color: rgb(0, 0, 0);" class="ball_b2">10</span>
						<span style="color: rgb(0, 0, 0);" class="ball_b2">14</span>
						<span style="color: rgb(0, 0, 0);" class="ball_b2">15</span>
						<span style="color: rgb(0, 0, 0);" class="ball_b2">20</span>
					</td>
				</tr>
				</form>
				<tr class="hset2">
					<td class="tbtitle" colSpan="4">
						<input id="btnSubmit"class="button_a" onclick="return ChkSubmit();" name="btnSubmit"value="下注" type="submit"> &nbsp;&nbsp;
						<input class="button_a" onclick="ClearData()" name="Submit3" value="重设"type="button">
					</td>
				</tr>
			</tbody>
		</table>

	</div>
	<script type="text/javascript">
		var cp_gameTypeCode_temp="${results.gameTypeCode}";
		var cp_typeCode_temp="BB";
		var cp_cateCode_temp="BB";
		var cp_xzlxCode_temp="";

	</script>
	<%@include file="/commons/cp/jsp/cp_fast_set_footer.jsp" %>
</body>
</html>