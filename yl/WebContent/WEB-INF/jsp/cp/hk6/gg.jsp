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
					<td><span class="font_sty001"><strong>正码过关下注</strong> </span></td>
					<%@include file="/commons/cp/jsp/hk6_results.jsp" %>
				</tr>
			</tbody>
		</table>
		<script language="javascript">
			function SubChk(obj) {
				var checkCount = 0;
				for (i = 1; i <= 6; i++) {
					var o = document.getElementsByName("Num_" + i);
					for (k = 0; k < o.length; k++) {
						if (o[k].checked) {
							checkCount++;
						}
					}
				}
				if (checkCount <= 1) {
					alert("过关至少2串");
					return false;
				}
				parent.k_meml.showOrder();
			}
		</script>
		<form name="order_form" id="order_form" action="${ctx}/order/goGroupOrderList?code=${results.gameTypeCode}&type=${results.cpTypeCode}"
					method="post" target="bet_order_frame" autocomplete="off">
			<table class="game_table" border="0" cellSpacing="1" cellPadding="0">
				<tbody>

					<tr class="tbtitle">
						<td height="25" align="left" id="time_td" colspan="15">
								<font>当前期数：<font class="colorDate"><b>${results.nextQs}</b></font>
								期&nbsp;&nbsp;&nbsp;&nbsp;</font>
								<span class="STYLE1">距离封盘时间：</span> <font><strong><span
									id="span_dt_dt"><font color="#7aff00"><b>-</b>0</font> 分
										<font color="#7aff00"><b>0</b> </font> 秒</span> </strong></font>
										<script language="javascript">
											gametimeRef("${results.nextKjsj}",'span_dt_dt');
										</script>
						</td>
					</tr>
					<tr>
						<td class="tbtitle" height="25" colSpan="2">正码一</td>
						<td class="tbtitle" colSpan="2">正码二</td>
						<td class="tbtitle" colSpan="2">正码三</td>
						<td class="tbtitle" colSpan="2">正码四</td>
						<td class="tbtitle" colSpan="2">正码五</td>
						<td class="tbtitle" colSpan="2">正码六</td>
					</tr>
					<tr class="tbtitle2">
						<td>号码</td>
						<td>赔率</td>
						<td>号码</td>
						<td>赔率</td>
						<td>号码</td>
						<td>赔率</td>
						<td>号码</td>
						<td>赔率</td>
						<td>号码</td>
						<td>赔率</td>
						<td>号码</td>
						<td>赔率</td>
					</tr>
					<tr>
						<td class="tbtitle">单</td>
						<td class="ball_f0">
							<input id="Num_1" name="Num_1"	value="414" type="radio"> <font id="bl_454"	color="#db0000">1.9</font></td>
						<td class="tbtitle">单</td>
						<td class="ball_f0">
							<input id="Num_2" name="Num_2"value="421" type="radio"> <font id="bl_461"color="#db0000">1.9</font></td>
						<td class="tbtitle">单</td>
						<td class="ball_f0">
							<input id="Num_3" name="Num_3"value="428" type="radio"> <font id="bl_468"color="#db0000">1.9</font></td>
						<td class="tbtitle">单</td>
						<td class="ball_f0">
							<input id="Num_4" name="Num_4"value="435" type="radio"> <font id="bl_475"color="#db0000">1.9</font></td>
						<td class="tbtitle">单</td>
						<td class="ball_f0">
							<input id="Num_5" name="Num_5"value="442" type="radio"> <font id="bl_482"color="#db0000">1.9</font></td>
						<td class="tbtitle">单</td>
						<td class="ball_f0">
							<input id="Num_6" name="Num_6"value="449" type="radio"> <font id="bl_489"color="#db0000">1.9</font></td>
					</tr>
					<tr>
						<td class="tbtitle">双</td>
						<td class="ball_f0">
							<input id="Num_1" name="Num_1"value="415" type="radio"> <font id="bl_455"color="#db0000">1.9</font></td>
						<td class="tbtitle">双</td>
						<td class="ball_f0">
							<input id="Num_2" name="Num_2"value="422" type="radio"> <font id="bl_462"color="#db0000">1.9</font></td>
						<td class="tbtitle">双</td>
						<td class="ball_f0">
							<input id="Num_3" name="Num_3"value="429" type="radio"> <font id="bl_469"color="#db0000">1.9</font></td>
						<td class="tbtitle">双</td>
						<td class="ball_f0">
							<input id="Num_4" name="Num_4"value="436" type="radio"> <font id="bl_476"color="#db0000">1.9</font></td>
						<td class="tbtitle">双</td>
						<td class="ball_f0">
							<input id="Num_5" name="Num_5"value="443" type="radio"> <font id="bl_483"color="#db0000">1.9</font></td>
						<td class="tbtitle">双</td>
						<td class="ball_f0">
							<input id="Num_6" name="Num_6"value="450" type="radio"> <font id="bl_490"color="#db0000">1.9</font></td>
					</tr>
					<tr>
						<td class="tbtitle">大</td>
						<td class="ball_f0">
							<input id="Num_1" name="Num_1" value="412" type="radio"> <font id="bl_456"color="#db0000">1.9</font></td>
						<td class="tbtitle">大</td>
						<td class="ball_f0">
							<input id="Num_2" name="Num_2"value="419" type="radio"> <font id="bl_463"color="#db0000">1.9</font></td>
						<td class="tbtitle">大</td>
						<td class="ball_f0">
							<input id="Num_3" name="Num_3"value="426" type="radio"> <font id="bl_470"color="#db0000">1.9</font></td>
						<td class="tbtitle">大</td>
						<td class="ball_f0">
							<input id="Num_4" name="Num_4"value="433" type="radio"> <font id="bl_477"color="#db0000">1.9</font></td>
						<td class="tbtitle">大</td>
						<td class="ball_f0">
							<input id="Num_5" name="Num_5"value="440" type="radio"> <font id="bl_484"color="#db0000">1.9</font></td>
						<td class="tbtitle">大</td>
						<td class="ball_f0">
							<input id="Num_6" name="Num_6"value="447" type="radio"> <font id="bl_491"color="#db0000">1.9</font></td>
					</tr>
					<tr>
						<td class="tbtitle">小</td>
						<td class="ball_f0">
							<input id="Num_1" name="Num_1"value="413" type="radio"> <font id="bl_457"color="#db0000">1.9</font></td>
						<td class="tbtitle">小</td>
						<td class="ball_f0">
							<input id="Num_2" name="Num_2"value="420" type="radio"> <font id="bl_464"color="#db0000">1.9</font></td>
						<td class="tbtitle">小</td>
						<td class="ball_f0">
							<input id="Num_3" name="Num_3"value="427" type="radio"> <font id="bl_471"color="#db0000">1.9</font></td>
						<td class="tbtitle">小</td>
						<td class="ball_f0">
							<input id="Num_4" name="Num_4"value="434" type="radio"> <font id="bl_478"color="#db0000">1.9</font></td>
						<td class="tbtitle">小</td>
						<td class="ball_f0">
							<input id="Num_5" name="Num_5"value="441" type="radio"> <font id="bl_485"color="#db0000">1.9</font></td>
						<td class="tbtitle">小</td>
						<td class="ball_f0">
							<input id="Num_6" name="Num_6"	value="448" type="radio"> <font id="bl_492"color="#db0000">1.9</font></td>
					</tr>
					<tr>
						<td class="tbtitle">红波</td>
						<td class="ball_f0">
							<input id="Num_1" name="Num_1"value="416" type="radio"> <font id="bl_458"color="#db0000">2.7</font></td>
						<td class="tbtitle">红波</td>
						<td class="ball_f0">
							<input id="Num_2" name="Num_2"value="423" type="radio"> <font id="bl_465"color="#db0000">2.7</font></td>
						<td class="tbtitle">红波</td>
						<td class="ball_f0">
							<input id="Num_3" name="Num_3"value="430" type="radio"> <font id="bl_472"color="#db0000">2.7</font></td>
						<td class="tbtitle">红波</td>
						<td class="ball_f0">
							<input id="Num_4" name="Num_4"value="437" type="radio"> <font id="bl_479"color="#db0000">2.7</font></td>
						<td class="tbtitle">红波</td>
						<td class="ball_f0">
							<input id="Num_5" name="Num_5"value="444" type="radio"> <font id="bl_486"color="#db0000">2.7</font></td>
						<td class="tbtitle">红波</td>
						<td class="ball_f0">
							<input id="Num_6" name="Num_6"value="451" type="radio"> <font id="bl_493"	color="#db0000">2.7</font></td>
					</tr>
					<tr>
						<td class="tbtitle">绿波</td>
						<td class="ball_f0">
							<input id="Num_1" name="Num_1"value="417" type="radio"> <font id="bl_459"color="#db0000">2.85</font></td>
						<td class="tbtitle">绿波</td>
						<td class="ball_f0">
							<input id="Num_2" name="Num_2"value="424" type="radio"> <font id="bl_466"color="#db0000">2.85</font></td>
						<td class="tbtitle">绿波</td>
						<td class="ball_f0">
							<input id="Num_3" name="Num_3"value="431" type="radio"> <font id="bl_473"color="#db0000">2.85</font></td>
						<td class="tbtitle">绿波</td>
						<td class="ball_f0">
							<input id="Num_4" name="Num_4"value="438" type="radio"> <font id="bl_480"color="#db0000">2.85</font></td>
						<td class="tbtitle">绿波</td>
						<td class="ball_f0">
							<input id="Num_5" name="Num_5"value="445" type="radio"> <font id="bl_487"color="#db0000">2.85</font></td>
						<td class="tbtitle">绿波</td>
						<td class="ball_f0">
							<input id="Num_6" name="Num_6"value="452" type="radio"> <font id="bl_494"color="#db0000">2.85</font></td>
					</tr>
					<tr>
						<td class="tbtitle">蓝波</td>
						<td class="ball_f0">
							<input id="Num_1" name="Num_1"value="418" type="radio"> <font id="bl_460"	color="#db0000">2.85</font></td>
						<td class="tbtitle">蓝波</td>
						<td class="ball_f0">
							<input id="Num_2" name="Num_2"value="425" type="radio"> <font id="bl_467"color="#db0000">2.85</font></td>
						<td class="tbtitle">蓝波</td>
						<td class="ball_f0">
							<input id="Num_3" name="Num_3"value="432" type="radio"> <font id="bl_474"	color="#db0000">2.85</font></td>
						<td class="tbtitle">蓝波</td>
						<td class="ball_f0">
							<input id="Num_4" name="Num_4"value="439" type="radio"> <font id="bl_481"color="#db0000">2.85</font></td>
						<td class="tbtitle">蓝波</td>
						<td class="ball_f0">
							<input id="Num_5" name="Num_5"value="446" type="radio"> <font id="bl_488"color="#db0000">2.85</font></td>
						<td class="tbtitle">蓝波</td>
						<td class="ball_f0">
							<input id="Num_6" name="Num_6"value="453" type="radio"> <font id="bl_495"color="#db0000">2.85</font></td>
					</tr>

					<tr class="hset2">
						<td class="tbtitle" colSpan="12">
							<input id="btnSubmit" class="button_a" onclick="return SubChk();" name="btnSubmit" value="下注" type="submit"> &nbsp;&nbsp;
							<input class="button_a" onclick="document.order_form.reset()" name="Submit3" value="重设" type="button"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		var cp_gameTypeCode_temp="${results.gameTypeCode}";
		var cp_typeCode_temp="GG";
		var cp_cateCode_temp="";
		var cp_xzlxCode_temp="";

	</script>
	<%@include file="/commons/cp/jsp/cp_fast_set_footer.jsp" %>
</body>
</html>