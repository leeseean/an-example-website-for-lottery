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
					<td><span class="font_sty001"><strong>正特尾下注</strong> </span></td>
					<%@include file="/commons/cp/jsp/hk6_results.jsp" %>
				</tr>
			</tbody>
		</table>
		<form name="order_form" id="order_form" action="${ctx}/order/goList?code=HK6"
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
					<tr class="tbtitle4 hset">
						<td height="20" colSpan="15"><b>正特尾下注</b></td>
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
					<tr>
						<td class="ball_bg"><strong>0尾</strong></td>
						<td class="ball_ff"><span id="bl_569" rate="true">2.05</span>	</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"style="height: 18px;" onkeydown="return Yh_Text.CheckNumber2()"class="input1" name="Num_569" size="4" type="text">
						</td>
						<td class="ball_bg"><strong>1尾</strong></td>
						<td class="ball_ff"><span id="bl_570" rate="true">1.76</span></td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"style="height: 18px;" onkeydown="return Yh_Text.CheckNumber2()"class="input1" name="Num_570" size="4" type="text"></td>
						<td class="ball_bg"><strong>2尾</strong>	</td>
						<td class="ball_ff"><span id="bl_571" rate="true">1.76</span></td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"style="height: 18px;" onkeydown="return Yh_Text.CheckNumber2()"class="input1" name="Num_571" size="4" type="text">	</td>
						<td class="ball_bg"><strong>3尾</strong></td>
						<td class="ball_ff"><span id="bl_572" rate="true">1.76</span>	</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"style="height: 18px;" onkeydown="return Yh_Text.CheckNumber2()"class="input1" name="Num_572" size="4" type="text"></td>
						<td class="ball_bg"><strong>4尾</strong>	</td>
						<td class="ball_ff"><span id="bl_573" rate="true">1.76</span>	</td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"style="height: 18px;" onkeydown="return Yh_Text.CheckNumber2()"class="input1" name="Num_573" size="4" type="text">	</td>
					</tr>
					<tr>
						<td class="ball_bg"><strong>5尾</strong></td>
						<td class="ball_ff"><span id="bl_574" rate="true">1.76</span></td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"style="height: 18px;" onkeydown="return Yh_Text.CheckNumber2()"class="input1" name="Num_574" size="4" type="text">
						</td>
						<td class="ball_bg"><strong>6尾</strong>	</td>
						<td class="ball_ff"><span id="bl_575" rate="true">1.76</span></td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"style="height: 18px;" onkeydown="return Yh_Text.CheckNumber2()"	class="input1" name="Num_575" size="4" type="text"></td>
						<td class="ball_bg"><strong>7尾</strong></td>
						<td class="ball_ff"><span id="bl_576" rate="true">1.76</span></td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"style="height: 18px;" onkeydown="return Yh_Text.CheckNumber2()"class="input1" name="Num_576" size="4" type="text"></td>
						<td class="ball_bg"><strong>8尾</strong></td>
						<td class="ball_ff"><span id="bl_577" rate="true">1.76</span></td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"style="height: 18px;" onkeydown="return Yh_Text.CheckNumber2()"class="input1" name="Num_577" size="4" type="text"></td>
						<td class="ball_bg"><strong>9尾</strong></td>
						<td class="ball_ff"><span id="bl_578" rate="true">1.76</span></td>
						<td class="ball_ff">
							<input onblur="CheckInput(this)"style="height: 18px;" onkeydown="return Yh_Text.CheckNumber2()"class="input1" name="Num_578" size="4" type="text">	</td>
					</tr>
				</tbody>
		</table>

		<table style="margin-top: 3px;" class="game_table" border="0"
			cellSpacing="1" cellPadding="0">
			<tbody>
				<tr class="tbtitle4 hset">
					<td height="20" colSpan="12"><b>一肖下注</b></td>
				</tr>
				<tr class="tbtitle2">
					<td width="41">号码</td>
					<td width="50">赔率</td>
					<td width="55">金额</td>
					<td colSpan="3">号码</td>
					<td width="41">号码</td>
					<td width="50">赔率</td>
					<td width="55">金额</td>
					<td colSpan="3">号码</td>
				</tr>


				<tr>
					<td class="ball_bg"><span class="STYLE4">鼠</span></td>
					<td class="ball_ff"><span id="bl_579" rate="true">2.05</span></td>
					<td class="ball_ff">
				 		<input onblur="CheckInput(this)"style="height: 18px;" onkeydown="return Yh_Text.CheckNumber2()"	class="input1" name="Num_579" size="4" type="text">
				 	</td>
					<td class="tbtitle" colSpan="3">
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
					<td class="ball_ff"><span id="bl_585" rate="true">2.05</span>	</td>
					<td class="ball_ff">
						<input onblur="CheckInput(this)"	style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2(event,this)" class="input1"	name="Num_585" size="4" type="text" rate="true">
					</td>
					<td class="tbtitle" colSpan="3">
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
					<td class="ball_ff"><span id="bl_580" rate="true">2.05</span></td>
					<td class="ball_ff">
						<input onblur="CheckInput(this)"style="height: 18px;" onkeydown="return Yh_Text.CheckNumber2()"class="input1" name="Num_580" size="4" type="text">
					</td>
					<td class="tbtitle" colSpan="3">
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

					<td class="ball_bg"><span class="STYLE4">兔</span>	</td>
					<td class="ball_ff"><span id="bl_586" rate="true">2.05</span></td>
					<td class="ball_ff">
						<input onblur="CheckInput(this)"style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2(event,this)" class="input1"	name="Num_586" size="4" type="text" rate="true">
					</td>
					<td class="tbtitle" colSpan="3">
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
					<td class="ball_bg"><span class="STYLE4">龙</span>	</td>
					<td class="ball_ff"><span id="bl_581" rate="true">2.05</span></td>
					<td class="ball_ff">
						<input onblur="CheckInput(this)"style="height: 18px;" onkeydown="return Yh_Text.CheckNumber2()"class="input1" name="Num_581" size="4" type="text">
					</td>
					<td class="tbtitle" colSpan="3">
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
					<td class="ball_ff"><span id="bl_587" rate="true">2.05</span></td>
					<td class="ball_ff">
						<input onblur="CheckInput(this)"style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2(event,this)" class="input1"	name="Num_587" size="4" type="text" rate="true">
					</td>
					<td class="tbtitle" colSpan="3">
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
					<td class="ball_bg"><span class="STYLE4">马</span></td>
					<td class="ball_ff"><span id="bl_582" rate="true">2.05</span></td>
					<td class="ball_ff">
						<input onblur="CheckInput(this)"style="height: 18px;" onkeydown="return Yh_Text.CheckNumber2()"		class="input1" name="Num_582" size="4" type="text">
					</td>
					<td class="tbtitle" colSpan="3">
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

					<td class="ball_bg"><span class="STYLE4">羊</span></td>
					<td class="ball_ff"><span id="bl_588" rate="true">1.75</span></td>
					<td class="ball_ff">
						<input onblur="CheckInput(this)" style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2(event,this)" class="input1"	name="Num_588" size="4" type="text" rate="true">
					</td>
					<td class="tbtitle" colSpan="3">
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
					<td class="ball_bg"><span class="STYLE4">猴</span></td>
					<td class="ball_ff"><span id="bl_583" rate="true">2.05</span></td>
					<td class="ball_ff">
						<input onblur="CheckInput(this)"style="height: 18px;" onkeydown="return Yh_Text.CheckNumber2()"class="input1" name="Num_583" size="4" type="text">
					</td>
					<td class="tbtitle" colSpan="3">
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

					<td class="ball_bg"><span class="STYLE4">鸡</span></td>
					<td class="ball_ff"><span id="bl_589" rate="true">2.05</span>	</td>
					<td class="ball_ff">
						<input onblur="CheckInput(this)"style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2(event,this)" class="input1"name="Num_589" size="4" type="text" rate="true">
					</td>
					<td class="tbtitle" colSpan="3">
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
					<td class="ball_ff"><span id="bl_584" rate="true">2.05</span></td>
					<td class="ball_ff">
						<input onblur="CheckInput(this)"style="height: 18px;" onkeydown="return Yh_Text.CheckNumber2()"class="input1" name="Num_584" size="4" type="text">	</td>
					<td class="tbtitle" colSpan="3">
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
					<td class="ball_ff"><span id="bl_590" rate="true">2.05</span></td>
					<td class="ball_ff">
						<input onblur="CheckInput(this)"	style="height: 18px;"onkeydown="return Yh_Text.CheckNumber2(event,this)" class="input1"name="Num_590" size="4" type="text" rate="true">
					</td>
					<td class="tbtitle" colSpan="3">
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
				<tr class="hset2">
					<td class="tbtitle" colSpan="12">
						<input id="btnSubmit" class="button_a" onclick="return ChkSubmit();" name="btnSubmit" value="下注" type="submit"> &nbsp;&nbsp;
						<input class="button_a" onclick="ClearData()" name="Submit3" value="重设" type="button">
					</td>
				</tr>
			</tbody>
		</table>
		</form>
	</div>
	<script type="text/javascript">
		var cp_gameTypeCode_temp="${results.gameTypeCode}";
		var cp_typeCode_temp="YXYZTW";
		var cp_cateCode_temp="";
		var cp_xzlxCode_temp="";

	</script>
	<%@include file="/commons/cp/jsp/cp_fast_set_footer.jsp" %>
</body>
</html>