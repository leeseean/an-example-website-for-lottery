<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html style="overflow-x:hidden">
<head>
<base href="<%=basePath%>">

<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>webcom</title>
<%@include file="/commons/cp/jsp/cp_common.jsp" %>
</head>
<body ondragstart="window.event.returnValue=false" id="HOP"
	oncontextmenu="window.event.returnValue=false"
	onselectstart="event.returnValue=false">

	<div class="wrapCss_004">
		<table class="tab_001" border="0" cellSpacing="0" cellPadding="0"
			width="100%">
			<tbody>
				<tr style='background: url("${resourceRoot}/cp/images/title_bg_004.gif") repeat-x left top;'>
					<td><span class="font_sty001"><strong>特头尾下注</strong></span></td>
					<%@include file="/commons/cp/jsp/hk6_results.jsp" %>
				</tr>
			</tbody>
		</table>

		<table class="game_table" border="0" cellSpacing="1" cellPadding="0">
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
						<td id="time_tm" colSpan="15" noWrap="nowrap">
							<button style="height: 22px;" class="button_a" onclick="document.location.href='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=TXTW&cpZuTypeCode=TMSX'">
								<img align="absmiddle" src="${resourceRoot}/cp/images/add.gif?=594" width="16" height="16">特码生肖
							</button>
							<button style="height: 22px;" class="button_b" onclick="document.location.href='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=TXTW&cpZuTypeCode=TTW'">
								<img align="absmiddle" src="${resourceRoot}/cp/images/add.gif?=594" width="16" height="16">特头尾
							</button>
						</td>
					</tr>
 					<form name="order_form" id="order_form" action="${ctx}/order/goList?code=HK6"
						method="post" target="bet_order_frame" autocomplete="off">
					<tr class="tbtitle4 hset">
						<td colSpan="15">&nbsp;特码头数</td>
					</tr>
					<tr class="tbtitle2">
						<td width="40">号码</td>
						<td width="40">赔率</td>
						<td width="40">金额</td>
						<td width="40">号码</td>
						<td width="40">赔率</td>
						<td width="40">金额</td>
						<td width="40">号码</td>
						<td width="40">赔率</td>
						<td width="40">金额</td>
						<td width="40">号码</td>
						<td width="40">赔率</td>
						<td width="40">金额</td>
						<td width="40">号码</td>
						<td width="40">赔率</td>
						<td width="40">金额</td>
					</tr>
					<tr>
						<td class="tbtitle" height="35">0头</td>
						<td class="ball_ff" height="25"><span id="bl_603" rate="true">5</span></td>
						<td class="ball_ff" height="25">
							<input onblur="CheckInput(this)" style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_603" size="4" type="text">
						</td>
						<td class="tbtitle" height="35">1头</td>
						<td class="ball_ff" height="25"><span id="bl_604" rate="true">4.3</span></td>
						<td class="ball_ff" height="25">
							<input onblur="CheckInput(this)" style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_604" size="4" type="text">
						</td>
						<td class="tbtitle" height="35">2头</td>
						<td class="ball_ff" height="25"><span id="bl_605" rate="true">4.3</span></td>
						<td class="ball_ff" height="25">
							<input onblur="CheckInput(this)" style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_605" size="4" type="text">
						</td>
						<td class="tbtitle" height="35">3头</td>
						<td class="ball_ff" height="25"><span id="bl_606"
							rate="true">4.3</span>
						</td>
						<td class="ball_ff" height="25">
							<input onblur="CheckInput(this)" style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_606" size="4" type="text">
						</td>
						<td class="tbtitle" height="35">4头</td>
						<td class="ball_ff" height="25"><span id="bl_607" rate="true">4.3</span></td>
						<td class="ball_ff" height="25">
							<input onblur="CheckInput(this)" style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_607" size="4" type="text">
						</td>
					</tr>
					<tr class="tbtitle4 hset">
						<td colSpan="15">&nbsp;特码尾数</td>
					</tr>
					<tr>
						<td class="tbtitle" height="35">0尾</td>
						<td class="ball_ff" height="25"><span id="bl_608" rate="true">11</span></td>
						<td class="ball_ff" height="25">
							<input onblur="CheckInput(this)" style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_608" size="4" type="text">
						</td>
						<td class="tbtitle" height="35">1尾</td>
						<td class="ball_ff" height="25"><span id="bl_609" rate="true">9.2</span>
						</td>
						<td class="ball_ff" height="25">
							<input onblur="CheckInput(this)" style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_609" size="4" type="text">
						</td>
						<td class="tbtitle" height="35">2尾</td>
						<td class="ball_ff" height="25"><span id="bl_610" rate="true">9.2</span>
						</td>
						<td class="ball_ff" height="25">
							<input onblur="CheckInput(this)" style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_610" size="4" type="text">
						</td>
						<td class="tbtitle" height="35">3尾</td>
						<td class="ball_ff" height="25"><span id="bl_611" rate="true">9.2</span></td>
						<td class="ball_ff" height="25">
							<input onblur="CheckInput(this)" style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_611" size="4" type="text">
						</td>
						<td class="tbtitle" height="35">4尾</td>
						<td class="ball_ff" height="25"><span id="bl_612" rate="true">9.2</span>
						</td>
						<td class="ball_ff" height="25">
							<input onblur="CheckInput(this)" style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_612" size="4" type="text">
						</td>
					</tr>
					<tr>
						<td class="tbtitle" height="35">5尾</td>
						<td class="ball_ff" height="25"><span id="bl_613" rate="true">9.2</span></td>
						<td class="ball_ff" height="25">
							<input onblur="CheckInput(this)" style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_613" size="4" type="text">
						</td>
						<td class="tbtitle" height="35">6尾</td>
						<td class="ball_ff" height="25"><span id="bl_614" rate="true">9.2</span></td>
						<td class="ball_ff" height="25">
							<input onblur="CheckInput(this)" style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_614" size="4" type="text">
						</td>
						<td class="tbtitle" height="35">7尾</td>
						<td class="ball_ff" height="25"><span id="bl_615" rate="true">9.2</span></td>
						<td class="ball_ff" height="25">
							<input onblur="CheckInput(this)" style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_615" size="4" type="text">
						</td>
						<td class="tbtitle" height="35">8尾</td>
						<td class="ball_ff" height="25"><span id="bl_616" rate="true">9.2</span></td>
						<td class="ball_ff" height="25">
							<input onblur="CheckInput(this)" style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_616" size="4" type="text">
						</td>
						<td class="tbtitle" height="35">9尾</td>
						<td class="ball_ff" height="25"><span id="bl_617" rate="true">9.2</span></td>
						<td class="ball_ff" height="25">
							<input onblur="CheckInput(this)" style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_617" size="4" type="text">
						</td>
					</tr>
				</form>
			</tbody>
		</table>
		<table class="game_table" border="0" cellSpacing="1" cellPadding="0">
			<tbody>
				<tr>
					<td class="tbtitle">
						<input id="btnSubmit" class="button_a" onclick="return ChkSubmit();" name="btnSubmit" value="下注" type="submit"> &nbsp;&nbsp;
						<input class="button_a" onclick="ClearData()" name="Submit3" value="重设" type="button">
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<script type="text/javascript">
		var cp_gameTypeCode_temp="${results.gameTypeCode}";
		var cp_typeCode_temp="TXTW";
		var cp_cateCode_temp="TTW";
		var cp_xzlxCode_temp="";

	</script>
	<%@include file="/commons/cp/jsp/cp_fast_set_footer.jsp" %>
</body>
</html>