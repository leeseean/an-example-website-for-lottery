<%@ page language="java"  pageEncoding="utf-8"%>
<%@ page import="java.util.*,com.mh.commons.utils.*,com.mh.web.util.HK6Util"%>
<%
	Map<String,List<String>> animalMap= AnimalUtil.getCurrAnimalCodeList();

 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html style="overflow-x:hidden">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>webcom</title>
<%@include file="/commons/cp/jsp/cp_common.jsp" %>
</head>
<body>

	<div class="wrapCss_004">
		<table class="tab_001" border="0" cellSpacing="0" cellPadding="0" width="100%">
			<tbody>
				<tr style='background: url("${resourceRoot}/cp/images/title_bg_004.gif") repeat-x left top;'>
					<td><span class="font_sty001"><strong>特肖下注</strong> </span></td>
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
					<td id="time_tm" colSpan="8" noWrap="nowrap">
						<button style="height: 22px;" class="button_b" onclick="document.location.href='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=TXTW&cpZuTypeCode=TMSX'">
							<img align="absmiddle" src="${resourceRoot}/cp/images/add.gif?=594" width="16" height="16">特码生肖
						</button>
						<button style="height: 22px;" class="button_a" onclick="document.location.href='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=TXTW&cpZuTypeCode=TTW'">
							<img align="absmiddle" src="${resourceRoot}/cp/images/add.gif?=594" width="16" height="16">特头尾
						</button>
					</td>
				</tr>

				<form name="order_form" id="order_form" action="${ctx}/order/goList?code=HK6"
					method="post" target="bet_order_frame" autocomplete="off">

				<tr class="tbtitle2">
					<td width="41">号码</td>
					<td width="50">赔率</td>
					<td width="55">金额</td>
					<td>号码</td>
					<td width="41">号码</td>
					<td width="50">赔率</td>
					<td width="55">金额</td>
					<td>号码</td>
				</tr>

				<tr>
					<td class="ball_bg"><span class="STYLE4">鼠</span></td>
					<td class="ball_ff"><span id="bl_591" rate="true">10.5</span></td>
					<td class="ball_ff">
						<input onblur="CheckInput(this)" style="height: 18px;" onkeydown="return Yh_Text.CheckNumber2()" class="input1" name="Num_591" size="4" type="text">
						<input name="class3_1" value="鼠" type="hidden">
					</td>
					<td class="tbtitle">
						<table border="0" align="left">
							<tbody>
								<tr>
									<%
										List<String> list = animalMap.get("鼠");
										for(int i=0;i<list.size();i++){
											String number = list.get(i);
											String className = HK6Util.colorClassNameMap.get(number);
									 %>
									 <td class="ball_<%=className%>" height="25" width="26" align="middle"><%=number %></td>
									 <%} %>
								</tr>
							</tbody>
						</table>
					</td>

					<td class="ball_bg"><span class="STYLE4">牛</span></td>
					<td class="ball_ff"><span id="bl_597" rate="true">10.5</span></td>
					<td class="ball_ff">
						<input onblur="CheckInput(this)" style="height: 18px;" onkeydown="return Yh_Text.CheckNumber2(event,this)" class="input1" name="Num_597" size="4" type="text" rate="true">
					</td>
					<td class="tbtitle">
						<table align="left">
							<tbody>
								<tr>
									<%
										list = animalMap.get("牛");
										for(int i=0;i<list.size();i++){
											String number = list.get(i);
											String className = HK6Util.colorClassNameMap.get(number);
									 %>
									 <td class="ball_<%=className%>" height="25" width="26" align="middle"><%=number %></td>
									 <%} %>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>

				<tr>
					<td class="ball_bg"><span class="STYLE4">虎</span></td>
					<td class="ball_ff"><span id="bl_592" rate="true">10.5</span>
					</td>
					<td class="ball_ff">
						<input onblur="CheckInput(this)" style="height: 18px;" onkeydown="return Yh_Text.CheckNumber2()" class="input1" name="Num_592" size="4" type="text">
						<input name ="class3_2" value="虎" type="hidden">
					</td>
					<td class="tbtitle">
						<table border="0" align="left">
							<tbody>
								<tr>
									<%
										list = animalMap.get("虎");
										for(int i=0;i<list.size();i++){
											String number = list.get(i);
											String className = HK6Util.colorClassNameMap.get(number);
									 %>
									 <td class="ball_<%=className%>" height="25" width="26" align="middle"><%=number %></td>
									 <%} %>
								</tr>
							</tbody>
						</table>
					</td>

					<td class="ball_bg"><span class="STYLE4">兔</span></td>
					<td class="ball_ff"><span id="bl_598" rate="true">10.5</span></td>
					<td class="ball_ff">
						<input onblur="CheckInput(this)" style="height: 18px;" onkeydown="return Yh_Text.CheckNumber2(event,this)" class="input1" name="Num_598" size="4" type="text" rate="true">
					</td>
					<td class="tbtitle">
						<table align="left">
							<tbody>
								<tr>
									<%
										list = animalMap.get("兔");
										for(int i=0;i<list.size();i++){
											String number = list.get(i);
											String className = HK6Util.colorClassNameMap.get(number);
									 %>
									 <td class="ball_<%=className%>" height="25" width="26" align="middle"><%=number %></td>
									 <%} %>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>


				<tr>
					<td class="ball_bg"><span class="STYLE4">龙</span></td>
					<td class="ball_ff"><span id="bl_593" rate="true">10.5</span></td>
					<td class="ball_ff">
						<input onblur="CheckInput(this)" style="height: 18px;" onkeydown="return Yh_Text.CheckNumber2()" class="input1" name="Num_593" size="4" type="text">
						<input name="class3_3" value="龙" type="hidden">
					</td>
					<td class="tbtitle">
						<table border="0" align="left">
							<tbody>
								<tr>
									<%
										list = animalMap.get("龙");
										for(int i=0;i<list.size();i++){
											String number = list.get(i);
											String className = HK6Util.colorClassNameMap.get(number);
									 %>
									 <td class="ball_<%=className%>" height="25" width="26" align="middle"><%=number %></td>
									 <%} %>
								</tr>
							</tbody>
						</table>
					</td>

					<td class="ball_bg"><span class="STYLE4">蛇</span></td>
					<td class="ball_ff"><span id="bl_599" rate="true">10.5</span></td>
					<td class="ball_ff">
						<input onblur="CheckInput(this)" style="height: 18px;" onkeydown="return Yh_Text.CheckNumber2(event,this)" class="input1" name="Num_599" size="4" type="text" rate="true">
					</td>
					<td class="tbtitle">
						<table align="left">
							<tbody>
								<tr>
									<%
										list = animalMap.get("蛇");
										for(int i=0;i<list.size();i++){
											String number = list.get(i);
											String className = HK6Util.colorClassNameMap.get(number);
									 %>
									 <td class="ball_<%=className%>" height="25" width="26" align="middle"><%=number %></td>
									 <%} %>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>


				<tr>
					<td class="ball_bg"><span class="STYLE4">马</span>
					</td>
					<td class="ball_ff"><span id="bl_594" rate="true">10.5</span>
					</td>
					<td class="ball_ff">
						<input onblur="CheckInput(this)" style="height: 18px;" onkeydown="return Yh_Text.CheckNumber2()" class="input1" name="Num_594" size="4" type="text">
					</td>
					<td class="tbtitle">
						<table border="0" align="left">
							<tbody>
								<tr>
									<%
										list = animalMap.get("马");
										for(int i=0;i<list.size();i++){
											String number = list.get(i);
											String className = HK6Util.colorClassNameMap.get(number);
									 %>
									 <td class="ball_<%=className%>" height="25" width="26" align="middle"><%=number %></td>
									 <%} %>
								</tr>
							</tbody>
						</table>
					</td>

					<td class="ball_bg"><span class="STYLE4">羊</span>
					</td>
					<td class="ball_ff"><span id="bl_600" rate="true">8.5</span></td>
					<td class="ball_ff">
						<input onblur="CheckInput(this)" style="height: 18px;"
						onkeydown="return Yh_Text.CheckNumber2(event,this)" class="input1"
						name="Num_600" size="4" type="text" rate="true">
					</td>
					<td class="tbtitle">
						<table align="left">
							<tbody>
								<tr>
									<%
										list = animalMap.get("羊");
										for(int i=0;i<list.size();i++){
											String number = list.get(i);
											String className = HK6Util.colorClassNameMap.get(number);
									 %>
									 <td class="ball_<%=className%>" height="25" width="26" align="middle"><%=number %></td>
									 <%} %>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>


				<tr>
					<td class="ball_bg"><span class="STYLE4">猴</span>
					</td>
					<td class="ball_ff"><span id="bl_595" rate="true">10.5</span>
					</td>
					<td class="ball_ff"><input onblur="CheckInput(this)"
						style="height: 18px;" onkeydown="return Yh_Text.CheckNumber2()"
						class="input1" name="Num_595" size="4" type="text">
					</td>
					<td class="tbtitle">
						<table border="0" align="left">
							<tbody>
								<tr>
									<%
										list = animalMap.get("猴");
										for(int i=0;i<list.size();i++){
											String number = list.get(i);
											String className = HK6Util.colorClassNameMap.get(number);
									 %>
									 <td class="ball_<%=className%>" height="25" width="26" align="middle"><%=number %></td>
									 <%} %>
								</tr>
							</tbody>
						</table>
					</td>

					<td class="ball_bg"><span class="STYLE4">鸡</span>
					</td>
					<td class="ball_ff"><span id="bl_601" rate="true">10.5</span>
					</td>
					<td class="ball_ff">
						<input onblur="CheckInput(this)" style="height: 18px;"
							onkeydown="return Yh_Text.CheckNumber2(event,this)" class="input1"
							name="Num_601" size="4" type="text" rate="true">
					</td>
					<td class="tbtitle">
						<table align="left">
							<tbody>
								<tr>
									<%
										list = animalMap.get("鸡");
										for(int i=0;i<list.size();i++){
											String number = list.get(i);
											String className = HK6Util.colorClassNameMap.get(number);
									 %>
									 <td class="ball_<%=className%>" height="25" width="26" align="middle"><%=number %></td>
									 <%} %>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>


				<tr>
					<td class="ball_bg"><span class="STYLE4">狗</span></td>
					<td class="ball_ff"><span id="bl_596" rate="true">10.5</span></td>
					<td class="ball_ff">
						<input onblur="CheckInput(this)"
							style="height: 18px;" onkeydown="return Yh_Text.CheckNumber2()"
							class="input1" name="Num_596" size="4" type="text">
					</td>
					<td class="tbtitle">
						<table border="0" align="left">
							<tbody>
								<tr>
									<%
										list = animalMap.get("狗");
										for(int i=0;i<list.size();i++){
											String number = list.get(i);
											String className = HK6Util.colorClassNameMap.get(number);
									 %>
									 <td class="ball_<%=className%>" height="25" width="26" align="middle"><%=number %></td>
									 <%} %>
								</tr>
							</tbody>
						</table>
					</td>

					<td class="ball_bg"><span class="STYLE4">猪</span></td>
					<td class="ball_ff"><span id="bl_602" rate="true">10.5</span></td>
					<td class="ball_ff">
						<input onblur="CheckInput(this)" style="height: 18px;" onkeydown="return Yh_Text.CheckNumber2(event,this)" class="input1" name="Num_602" size="4" type="text" rate="true">
					</td>
					<td class="tbtitle">
						<table align="left">
							<tbody>
								<tr>
									<%
										list = animalMap.get("猪");
										for(int i=0;i<list.size();i++){
											String number = list.get(i);
											String className = HK6Util.colorClassNameMap.get(number);
									 %>
									 <td class="ball_<%=className%>" height="25" width="26" align="middle"><%=number %></td>
									 <%} %>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td class="tbtitle" colSpan="8">
						<input id="btnSubmit" class="button_a" onclick="return ChkSubmit();" name="btnSubmit" value="下注" type="submit"> &nbsp;&nbsp;
						<input class="button_a" onclick="ClearData()" name="Submit3" value="重设" type="button">
					</td>
				</tr>
				</form>
			</tbody>
		</table>


	</div>
	<script type="text/javascript">
		var cp_gameTypeCode_temp="${results.gameTypeCode}";
		var cp_typeCode_temp="TXTW";
		var cp_cateCode_temp="TMSX";
		var cp_xzlxCode_temp="";

	</script>
 	<%@include file="/commons/cp/jsp/cp_fast_set_footer.jsp" %>

</body>
</html>