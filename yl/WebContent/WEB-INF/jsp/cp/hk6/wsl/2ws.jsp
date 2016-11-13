<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html style="overflow-x:hidden">
<head>
<%@include file="/commons/cp/jsp/cp_common.jsp" %>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>webcom</title>
</head>
<body ondragstart="window.event.returnValue=false" id="HOP"
	oncontextmenu="window.event.returnValue=false"
	onselectstart="event.returnValue=false">

	<div class="wrapCss_004">
			<table class="tab_001" border="0" cellSpacing="0" cellPadding="0" width="100%">
				<tbody>
					<tr style='background: url("${resourceRoot}/cp/images/title_bg_004.gif") repeat-x left top;'>
						<td><span class="font_sty001"><strong>二尾连下注</strong> </span></td>
						<%@include file="/commons/cp/jsp/hk6_results.jsp" %>
					</tr>
				</tbody>
			</table>


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
					<tr class="hsetEm">
						<td class="tbtitle" colSpan="8"></td>
					</tr>

					<tr class="hset">
						<td class="tbtitle4" colSpan="8">类型：<input id="glx"
							onclick="document.location='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=WSL&cpZuTypeCode=2ws'"
							name="glx" value="2" CHECKED="checked" type="radio">
							二尾连&nbsp; <input id="glx"
							onclick="document.location='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=WSL&cpZuTypeCode=3ws'"
							name="glx" value="3" type="radio"> 三尾连&nbsp; <input
							id="glx"
							onclick="document.location='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=WSL&cpZuTypeCode=4ws'"
							name="glx" value="4" type="radio"> 四尾连&nbsp;</td>
					</tr>
					<form name="order_form" id="order_form" onsubmit="return SubChk(this);"
						action="${ctx}/order/goMultiGroupOrderList?code=HK6&type=WSL&cate=EWL&multilen=2" method="post"
						 target="bet_order_frame" autocomplete="off">
					<tr class="tbtitle2">
						<td>类</td>
						<td>勾选</td>
						<td>赔率</td>
						<td>号码</td>
						<td>类</td>
						<td>勾选</td>
						<td>赔率</td>
						<td>号码</td>
					</tr>
					<tr class="tbtitle">
						<td class="ball_bg">0尾</td>
						<td class="ball_ff">
							<input onclick="return SubChkBox(this)"name="Num_863" value="0" type="checkbox">
						</td>
						<td id="bl_863" class="ball_ff">3</td>

						<td style="white-space: nowrap;">&nbsp;10.20.30.40</td>
						<td class="ball_bg">5尾</td>
						<td class="ball_ff">
							<input onclick="return SubChkBox(this)"name="Num_868" value="5" type="checkbox">
						</td>
						<td id="bl_868" class="ball_ff">3</td>
						<td style="white-space: nowrap;">&nbsp;05.15.25.35.45</td>
					</tr>
					<tr class="tbtitle">
						<td class="ball_bg">1尾</td>
						<td class="ball_ff">
							<input onclick="return SubChkBox(this)"name="Num_864" value="1" type="checkbox">
						</td>
						<td id="bl_864" class="ball_ff">3</td>
						<td style="white-space: nowrap;">&nbsp;01.11.21.31.41</td>
						<td class="ball_bg">6尾</td>
						<td class="ball_ff">
							<input onclick="return SubChkBox(this)"name="Num_869" value="6" type="checkbox">
						</td>
						<td id="bl_869" class="ball_ff">3</td>
						<td style="white-space: nowrap;">&nbsp;06.16.26.36.46</td>
					</tr>
					<tr class="tbtitle">
						<td class="ball_bg">2尾</td>
						<td class="ball_ff">
							<input onclick="return SubChkBox(this)"	name="Num_865" value="2" type="checkbox">
						</td>
						<td id="bl_865" class="ball_ff">3</td>
						<td style="white-space: nowrap;">&nbsp;02.12.22.32.42</td>
						<td class="ball_bg">7尾</td>
						<td class="ball_ff">
							<input onclick="return SubChkBox(this)"name="Num_870" value="7" type="checkbox">
						</td>
						<td id="bl_870" class="ball_ff">3</td>
						<td style="white-space: nowrap;">&nbsp;07.17.27.37.47</td>
					</tr>
					<tr class="tbtitle">
						<td class="ball_bg">3尾</td>
						<td class="ball_ff">
							<input onclick="return SubChkBox(this)"	name="Num_866" value="3" type="checkbox">
						</td>
						<td id="bl_866" class="ball_ff">3</td>
						<td style="white-space: nowrap;">&nbsp;03.13.23.33.43</td>
						<td class="ball_bg">8尾</td>
						<td class="ball_ff">
							<input onclick="return SubChkBox(this)"	name="Num_871" value="8" type="checkbox">
						</td>
						<td id="bl_871" class="ball_ff">3</td>
						<td style="white-space: nowrap;">&nbsp;08.18.28.38.48</td>
					</tr>
					<tr class="tbtitle">
						<td class="ball_bg">4尾</td>
						<td class="ball_ff">
							<input onclick="return SubChkBox(this)"	name="Num_867" value="4" type="checkbox">
						</td>
						<td id="bl_867" class="ball_ff">3</td>
						<td style="white-space: nowrap;">&nbsp;04.14.24.34.44</td>
						<td class="ball_bg">9尾</td>
						<td class="ball_ff">
							<input onclick="return SubChkBox(this)"name="Num_872" value="9" type="checkbox">
						</td>
						<td id="bl_872" class="ball_ff">3</td>
						<td style="white-space: nowrap;">&nbsp;09.19.29.39.49</td>
					</tr>

					<tr class="hset2">
						<td class="tbtitle" colSpan="8"><input id="btnSubmit"
							class="button_a" name="btnSubmit" value="下注" type="submit">
							&nbsp;&nbsp; <input class="button_a" onclick="sel=','"
							name="Submit3" value="重设" type="reset"></td>
					</tr>
					</form>
				</tbody>
			</table>
	</div>
	<script type="text/javascript">

		function SubChk(obj) {
			var a = sel.split(",");
			var len = a.length - 2;
			var len2 = $("input:radio[name=glx]:checked").val();
			switch (len2) {
			case "2":
				if (len < 2) {
					alert("请选择不少於2个尾号！");
					return false;
				}
				break;
			case "3":
				if (len < 3) {
					alert("请选择不少於3个号码！");
					return false;
				}
				break;
			case "4":
				if (len < 3) {
					alert("请选择不少於4个号码！");
					return false;
				}
				break;
			}
			parent.k_meml.showOrder();

		}
		var sel = ",";
		function SubChkBox(obj) {
			if (obj.checked) {
				var a = sel.split(",");
				var len = a.length - 2;
				var len2 = $("input:radio[name=glx]:checked").val();
				if (len >= 6) {
					alert("只能选择6个尾数");
					return false;
				}
				sel += obj.value + ",";
			} else
				sel = sel.replace("," + obj.value + ",", ",");
		}
	</script>
	<script type="text/javascript">
		var cp_gameTypeCode_temp="${results.gameTypeCode}";
		var cp_typeCode_temp="WSL";
		var cp_cateCode_temp="EWL";
		var cp_xzlxCode_temp="";

	</script>
	<%@include file="/commons/cp/jsp/cp_fast_set_footer.jsp" %>

</body>
</html>