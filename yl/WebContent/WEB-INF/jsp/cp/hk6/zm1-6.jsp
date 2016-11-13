<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html style="overflow-x:hidden">
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>webcom</title>
<%@include file="/commons/cp/jsp/cp_common.jsp"%>
</head>
<body ondragstart="window.event.returnValue=false" id="HOP"
	oncontextmenu="window.event.returnValue=false"
	onselectstart="event.returnValue=false">


	<div class="wrapCss_004">
		<table class="tab_001" border="0" cellSpacing="0" cellPadding="0" width="100%">
			<tbody>
				<tr style='background: url("${resourceRoot}/cp/images/title_bg_004.gif") repeat-x left top;'>
					<td><span class="font_sty001"><strong>正码1-6下注</strong></span></td>
					<%@include file="/commons/cp/jsp/hk6_results.jsp" %>
				</tr>
			</tbody>
		</table>
		<form name="order_form" id="order_form" action="${ctx}/order/goList?code=${results.gameTypeCode}"
					method="post" target="bet_order_frame" autocomplete="off">
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
					<tr class="hset">
						<td class="tbtitle" colSpan="3" noWrap="nowrap">正码1</td>
						<td class="tbtitle" colSpan="3" noWrap="nowrap">正码2</td>
						<td class="tbtitle" colSpan="3" noWrap="nowrap">正码3</td>
					</tr>
					<tr class="tbtitle2">
						<td noWrap="nowrap">号码</td>
						<td noWrap="nowrap">赔率</td>
						<td noWrap="nowrap">金额</td>
						<td noWrap="nowrap">号码</td>
						<td noWrap="nowrap">赔率</td>
						<td noWrap="nowrap">金额</td>
						<td noWrap="nowrap">号码</td>
						<td noWrap="nowrap">赔率</td>
						<td noWrap="nowrap">金额</td>
					</tr>
					<tr>
						<td class="ball_bg">大</td>
						<td class="ball_ff"><span id="bl_412" rate="true">1.92</span></td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_419" maxLength="6" size="4" type="text"></td>
						<td class="ball_bg">大</td>
						<td class="ball_ff"><span id="bl_419" rate="true">1.92</span></td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_426" maxLength="6" size="4" type="text"></td>
						<td class="ball_bg">大</td>
						<td class="ball_ff"><span id="bl_414" rate="true">1.92</span></td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_426" maxLength="6" size="4" type="text"></td>
					</tr>
					<tr>
						<td class="ball_bg">小</td>
						<td class="ball_ff"><span id="bl_413" rate="true">1.92</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_413" maxLength="6" size="4" type="text"></td>
						<td class="ball_bg">小</td>
						<td class="ball_ff"><span id="bl_420" rate="true">1.92</span>						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_420" maxLength="6" size="4" type="text"></td>
						<td class="ball_bg">小</td>
						<td class="ball_ff">
						<span id="bl_427" rate="true">1.92</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_427" maxLength="6" size="4" type="text"></td>
					</tr>
					<tr>
						<td class="ball_bg">单</td>
						<td class="ball_ff"><span id="bl_414" rate="true">1.92</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_414" maxLength="6" size="4" type="text"></td>
						<td class="ball_bg">单</td>
						<td class="ball_ff"><span id="bl_421" rate="true">1.92</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_421" maxLength="6" size="4" type="text"></td>
						<td class="ball_bg">单</td>
						<td class="ball_ff"><span id="bl_428" rate="true">1.92</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_428" maxLength="6" size="4" type="text"></td>
					</tr>
					<tr>
						<td class="ball_bg">双</td>
						<td class="ball_ff"><span id="bl_415" rate="true">1.92</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_415" maxLength="6" size="4" type="text"></td>
						<td class="ball_bg">双</td>
						<td class="ball_ff"><span id="bl_422" rate="true">1.92</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_422" maxLength="6" size="4" type="text"></td>
						<td class="ball_bg">双</td>
						<td class="ball_ff"><span id="bl_429" rate="true">1.92</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_429" maxLength="6" size="4" type="text"></td>
					</tr>
					<tr>
						<td class="ball_bg">红波</td>
						<td class="ball_ff"><span id="bl_416" rate="true">2.7</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_416" maxLength="6" size="4" type="text"></td>
						<td class="ball_bg">红波</td>
						<td class="ball_ff"><span id="bl_423" rate="true">2.7</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_423" maxLength="6" size="4" type="text"></td>
						<td class="ball_bg">红波</td>
						<td class="ball_ff"><span id="bl_430" rate="true">2.7</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_430" maxLength="6" size="4" type="text"></td>
					</tr>
					<tr>
						<td class="ball_bg">绿波</td>
						<td class="ball_ff"><span id="bl_417" rate="true">2.85</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_417" maxLength="6" size="4" type="text"></td>
						<td class="ball_bg">绿波</td>
						<td class="ball_ff"><span id="bl_424" rate="true">2.85</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_424" maxLength="6" size="4" type="text"></td>
						<td class="ball_bg">绿波</td>
						<td class="ball_ff"><span id="bl_431" rate="true">2.85</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_431" maxLength="6" size="4" type="text"></td>
					</tr>
					<tr>
						<td class="ball_bg">蓝波</td>
						<td class="ball_ff"><span id="bl_418" rate="true">2.85</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_418" maxLength="6" size="4" type="text"></td>
						<td class="ball_bg">蓝波</td>
						<td class="ball_ff"><span id="bl_425" rate="true">2.85</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_425" maxLength="6" size="4" type="text"></td>
						<td class="ball_bg">蓝波</td>
						<td class="ball_ff"><span id="bl_432" rate="true">2.85</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_432" maxLength="6" size="4" type="text"></td>
					</tr>
					<tr class="hset">
						<td class="tbtitle" colSpan="3" noWrap="nowrap">正码4</td>
						<td class="tbtitle" colSpan="3" noWrap="nowrap">正码5</td>
						<td class="tbtitle" colSpan="3" noWrap="nowrap">正码6</td>
					</tr>
					<tr class="tbtitle2">
						<td noWrap="nowrap">号码</td>
						<td noWrap="nowrap">赔率</td>
						<td noWrap="nowrap">金额</td>
						<td noWrap="nowrap">号码</td>
						<td noWrap="nowrap">赔率</td>
						<td noWrap="nowrap">金额</td>
						<td noWrap="nowrap">号码</td>
						<td noWrap="nowrap">赔率</td>
						<td noWrap="nowrap">金额</td>
					</tr>
					<tr>
						<td class="ball_bg">大</td>
						<td class="ball_ff"><span id="bl_433" rate="true">1.92</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_433" maxLength="6" size="4" type="text"></td>
						<td class="ball_bg">大</td>
						<td class="ball_ff"><span id="bl_440" rate="true">1.92</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_440" maxLength="6" size="4" type="text"></td>
						<td class="ball_bg">大</td>
						<td class="ball_ff"><span id="bl_447" rate="true">1.92</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_447" maxLength="6" size="4" type="text"></td>
					</tr>
					<tr>
						<td class="ball_bg">小</td>
						<td class="ball_ff"><span id="bl_434" rate="true">1.92</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_434" maxLength="6" size="4" type="text"></td>
						<td class="ball_bg">小</td>
						<td class="ball_ff"><span id="bl_441" rate="true">1.92</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_441" maxLength="6" size="4" type="text"></td>
						<td class="ball_bg">小</td>
						<td class="ball_ff"><span id="bl_448" rate="true">1.92</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"						name="Num_448" maxLength="6" size="4" type="text"></td>
					</tr>
					<tr>
						<td class="ball_bg">单</td>
						<td class="ball_ff"><span id="bl_435" rate="true">1.92</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_435" maxLength="6" size="4" type="text"></td>
						<td class="ball_bg">单</td>
						<td class="ball_ff"><span id="bl_442" rate="true">1.92</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_442" maxLength="6" size="4" type="text"></td>
						<td class="ball_bg">单</td>
						<td class="ball_ff"><span id="bl_449" rate="true">1.92</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_449" maxLength="6" size="4" type="text"></td>
					</tr>
					<tr>
						<td class="ball_bg">双</td>
						<td class="ball_ff"><span id="bl_436" rate="true">1.92</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_436" maxLength="6" size="4" type="text"></td>
						<td class="ball_bg">双</td>
						<td class="ball_ff"><span id="bl_443" rate="true">1.92</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_443" maxLength="6" size="4" type="text"></td>
						<td class="ball_bg">双</td>
						<td class="ball_ff"><span id="bl_450" rate="true">1.92</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"	onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_450" maxLength="6" size="4" type="text"></td>
					</tr>
					<tr>
						<td class="ball_bg">红波</td>
						<td class="ball_ff"><span id="bl_437" rate="true">2.7</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"					name="Num_437" maxLength="6" size="4" type="text"></td>
						<td class="ball_bg">红波</td>
						<td class="ball_ff"><span id="bl_444" rate="true">2.7</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_444" maxLength="6" size="4" type="text"></td>
						<td class="ball_bg">红波</td>
						<td class="ball_ff"><span id="bl_451" rate="true">2.7</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_451" maxLength="6" size="4" type="text"></td>
					</tr>
					<tr>
						<td class="ball_bg">绿波</td>
						<td class="ball_ff"><span id="bl_438" rate="true">2.85</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_438" maxLength="6" size="4" type="text"></td>
						<td class="ball_bg">绿波</td>
						<td class="ball_ff"><span id="bl_445" rate="true">2.85</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_445" maxLength="6" size="4" type="text"></td>
						<td class="ball_bg">绿波</td>
						<td class="ball_ff"><span id="bl_452" rate="true">2.85</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_452" maxLength="6" size="4" type="text"></td>
					</tr>
					<tr>
						<td class="ball_bg">蓝波</td>
						<td class="ball_ff"><span id="bl_439" rate="true">2.85</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_439" maxLength="6" size="4" type="text"></td>
						<td class="ball_bg">蓝波</td>
						<td class="ball_ff"><span id="bl_446" rate="true">2.85</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"name="Num_446" maxLength="6" size="4" type="text"></td>
						<td class="ball_bg">蓝波</td>
						<td class="ball_ff"><span id="bl_453" rate="true">2.85</span>
						</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"onkeydown="return Yh_Text.CheckNumber2()" class="input1"					name="Num_453" maxLength="6" size="4" type="text"></td>
					</tr>

					<tr class="hset2">
						<td class="tbtitle" colSpan="9"><input id="btnSubmit"
							class="button_a" onclick="return ChkSubmit();" name="btnSubmit"
							value="下注" type="submit"> &nbsp;&nbsp; <input
							class="button_a" onclick="ClearData()" name="Submit3" value="重设"
							type="button"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		var cp_gameTypeCode_temp="${results.gameTypeCode}";
		var cp_typeCode_temp="ZM1-6";
		var cp_cateCode_temp="";
		var cp_xzlxCode_temp="";

	</script>
	<%@include file="/commons/cp/jsp/cp_fast_set_footer.jsp" %>
</body>
</html>