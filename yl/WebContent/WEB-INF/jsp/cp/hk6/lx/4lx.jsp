<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.mh.commons.utils.*,com.mh.web.util.HK6Util"%>
<%
	Map<String,List<String>> animalMap= AnimalUtil.getCurrAnimalCodeList();

 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html style="overflow-x:hidden">
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>webcom</title>
<%@include file="/commons/cp/jsp/cp_common.jsp" %>
<style>
.ball_bg{ width: auto;}
</style>
</head>
<body ondragstart="window.event.returnValue=false" id="HOP"
	oncontextmenu="window.event.returnValue=false"
	onselectstart="event.returnValue=false">



	<script language="JAVASCRIPT">
		var sel = ",";
		var lt_form = document.getElementById('lt_form');
		function SubChk(obj) {
			var a = sel.split(",");
			var len = a.length - 2;
			var len2 = $("input:radio[name=glx]:checked").val();
			var snum = 2;
			if (len2 == 3 || len2 == 4)
				snum = 3;
			else if (len2 == 5 || len2 == 6)
				snum = 4;
			else if (len2 == 7 || len2 == 8)
				snum = 5;
			if (len<snum || len>6) {
				alert("请选择" + snum + "~6个生肖");
				return false;
			}
			parent.k_meml.showOrder();
		}
		function SubChkBox(obj, n) {
			if (obj.checked) {
				var a = sel.split(",");
				var len = a.length - 1;
				var len2 = $("input:radio[name=glx]:checked").val();
				if (len > 6) {
					alert("最多只能选6个生肖！");
					return false;
				}
				sel += obj.value + ",";
			} else
				sel = sel.replace("," + obj.value + ",", ",");
		}
		function clearCheck() {
			sel = ",";
			$(":checkbox").attr("checked", false);
		}
	</script>

	<div class="wrapCss_004">
		<table class="tab_001" border="0" cellSpacing="0" cellPadding="0"
			width="100%">
			<tbody>
				<tr style='background: url("${resourceRoot}/cp/images/title_bg_004.gif") repeat-x left top;'>
					<td><span class="font_sty001"><strong>四肖连[中]下注</strong> </span></td>
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
							onclick="document.location='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=LX&cpZuTypeCode=2lx'"
							name="glx" value="1" type="radio">
							二肖连[中]&nbsp; <input id="glx"
							onclick="document.location='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=LX&cpZuTypeCode=3lx'"
							name="glx" value="3" type="radio"> 三肖连[中]&nbsp; <input
							id="glx"
							onclick="document.location='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=LX&cpZuTypeCode=4lx'"
							name="glx" value="5" CHECKED="checked" type="radio"> 四肖连[中]&nbsp; <input
							id="glx"
							onclick="document.location='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=LX&cpZuTypeCode=5lx'"
							name="glx" value="7" type="radio"> 五肖连[中]&nbsp;</td>
					</tr>

					<form name="order_form" id="order_form" onsubmit="return SubChk(this);" action="${ctx}/order/goMultiGroupOrderList?code=HK6&type=LX&cate=SILX&multilen=4" method="post" target="bet_order_frame" autocomplete="off">
					<tr class="tbtitle2">
						<td>生肖</td>
						<td>赔率</td>
						<td>勾选</td>
						<td width="33%">号码</td>
						<td>生肖</td>
						<td>赔率</td>
						<td>勾选</td>
						<td width="33%">号码</td>
					</tr>
					<tr>
						<td class="ball_bg"><span class="STYLE10">鼠</span>
						</td>
						<td class="ball_ff"><font id="bl_917">30</font>
						</td>
						<td class="ball_ff"><input id="sxsx61" name="sxsx61"
							value="0" type="hidden"> <input id="Num_917"
							onclick="return SubChkBox(this,1)" name="Num_917" value="鼠"
							type="checkbox"> <input id="sxbl1" name="sxbl1" value="0"
							type="hidden">
						</td>
						<td style="white-space: nowrap;" class="ball_bg">
						<%
							List<String> list = animalMap.get("鼠");
							for(int i=0;i<list.size();i++){
								String number = list.get(i);
								String className = HK6Util.colorClassNameMap.get(number);
						 %>
						 <span style="color: rgb(0, 0, 0);" class="ball_<%=className%>2"><%=number %></span>
						 <%} %>
						</td>
						<td class="ball_bg"><span class="STYLE10">牛 </span>
						</td>
						<td class="ball_ff"><font id="bl_923">30</font>
						</td>
						<td class="ball_ff"><input id="sxsx67" name="sxsx67"
							value="0" type="hidden"> <input id="Num_923"
							onclick="return SubChkBox(this,7)" name="Num_923" value="牛"
							type="checkbox"> <input id="sxbl7" name="sxbl7" value="0"
							type="hidden"></td>
						<td style="white-space: nowrap;" class="ball_bg">
						<%
							list = animalMap.get("牛");
							for(int i=0;i<list.size();i++){
								String number = list.get(i);
								String className = HK6Util.colorClassNameMap.get(number);
						 %>
						 <span style="color: rgb(0, 0, 0);" class="ball_<%=className%>2"><%=number %></span>
						 <%} %>
						</td>
					</tr>
					<tr>
						<td class="ball_bg"><span class="STYLE10">虎</span>
						</td>
						<td class="ball_ff"><font id="bl_918">30</font>
						</td>
						<td class="ball_ff"><input id="sxsx62" name="sxsx62"
							value="0" type="hidden"> <input id="Num_918"
							onclick="return SubChkBox(this,2)" name="Num_918" value="虎"
							type="checkbox"> <input id="sxbl2" name="sxbl2" value="0"
							type="hidden">
						</td>
						<td style="white-space: nowrap;" class="ball_bg">
						<%
							list = animalMap.get("虎");
							for(int i=0;i<list.size();i++){
								String number = list.get(i);
								String className = HK6Util.colorClassNameMap.get(number);
						 %>
						 <span style="color: rgb(0, 0, 0);" class="ball_<%=className%>2"><%=number %></span>
						 <%} %>
						</td>
						<td class="ball_bg"><span class="STYLE10">兔 </span>
						</td>
						<td class="ball_ff"><font id="bl_924">30</font>
						</td>
						<td class="ball_ff"><input id="sxsx68" name="sxsx68"
							value="0" type="hidden"> <input id="Num_924"
							onclick="return SubChkBox(this,8)" name="Num_924" value="兔"
							type="checkbox"> <input id="sxbl8" name="sxbl8" value="0"
							type="hidden"></td>
						<td style="white-space: nowrap;" class="ball_bg">
						<%
							list = animalMap.get("兔");
							for(int i=0;i<list.size();i++){
								String number = list.get(i);
								String className = HK6Util.colorClassNameMap.get(number);
						 %>
						 <span style="color: rgb(0, 0, 0);" class="ball_<%=className%>2"><%=number %></span>
						 <%} %>
					</tr>
					<tr>
						<td class="ball_bg"><span class="STYLE10">龙</span>
						</td>
						<td class="ball_ff"><font id="bl_919">30</font>
						</td>
						<td class="ball_ff"><input id="sxsx63" name="sxsx63"
							value="0" type="hidden"> <input id="Num_919"
							onclick="return SubChkBox(this,3)" name="Num_919" value="龙"
							type="checkbox"> <input id="sxbl3" name="sxbl3" value="0"
							type="hidden">
						</td>
						<td style="white-space: nowrap;" class="ball_bg">
						<%
							list = animalMap.get("龙");
							for(int i=0;i<list.size();i++){
								String number = list.get(i);
								String className = HK6Util.colorClassNameMap.get(number);
						 %>
						 <span style="color: rgb(0, 0, 0);" class="ball_<%=className%>2"><%=number %></span>
						 <%} %>
						</td>
						<td class="ball_bg"><span class="STYLE10">蛇 </span>
						</td>
						<td class="ball_ff"><font id="bl_925">30</font>
						</td>
						<td class="ball_ff"><input id="sxsx69" name="sxsx69"
							value="0" type="hidden"> <input id="Num_925"
							onclick="return SubChkBox(this,9)" name="Num_925" value="蛇"
							type="checkbox"> <input id="sxbl9" name="sxbl9" value="0"
							type="hidden"></td>
						<td style="white-space: nowrap;" class="ball_bg">
						<%
							list = animalMap.get("蛇");
							for(int i=0;i<list.size();i++){
								String number = list.get(i);
								String className = HK6Util.colorClassNameMap.get(number);
						 %>
						 <span style="color: rgb(0, 0, 0);" class="ball_<%=className%>2"><%=number %></span>
						 <%} %>

						</td>
					</tr>
					<tr>
						<td class="ball_bg"><span class="STYLE10">马</span>
						</td>
						<td class="ball_ff"><font id="bl_920">30</font>
						</td>
						<td class="ball_ff"><input id="sxsx64" name="sxsx64"
							value="0" type="hidden"> <input id="Num_920"
							onclick="return SubChkBox(this,4)" name="Num_920" value="马"
							type="checkbox"> <input id="sxbl4" name="sxbl4" value="0"
							type="hidden">
						</td>
						<td style="white-space: nowrap;" class="ball_bg">
						<%
							list = animalMap.get("马");
							for(int i=0;i<list.size();i++){
								String number = list.get(i);
								String className = HK6Util.colorClassNameMap.get(number);
						 %>
						 <span style="color: rgb(0, 0, 0);" class="ball_<%=className%>2"><%=number %></span>
						 <%} %>
						</td>
						<td class="ball_bg"><span class="STYLE10">羊 </span>
						</td>
						<td class="ball_ff"><font id="bl_926">3.5</font>
						</td>
						<td class="ball_ff"><input id="sxsx610" name="sxsx610"
							value="0" type="hidden"> <input id="Num_926"
							onclick="return SubChkBox(this,10)" name="Num_926" value="羊"
							type="checkbox"> <input id="sxbl10" name="sxbl10"
							value="0" type="hidden"></td>
						<td style="white-space: nowrap;" class="ball_bg">
						<%
							list = animalMap.get("羊");
							for(int i=0;i<list.size();i++){
								String number = list.get(i);
								String className = HK6Util.colorClassNameMap.get(number);
						 %>
						 <span style="color: rgb(0, 0, 0);" class="ball_<%=className%>2"><%=number %></span>
						 <%} %>
						</td>
					</tr>
					<tr>
						<td class="ball_bg"><span class="STYLE10">猴</span>
						</td>
						<td class="ball_ff"><font id="bl_921">30</font>
						</td>
						<td class="ball_ff"><input id="sxsx65" name="sxsx65"
							value="0" type="hidden"> <input id="Num_921"
							onclick="return SubChkBox(this,5)" name="Num_921" value="猴"
							type="checkbox"> <input id="sxbl5" name="sxbl5" value="0"
							type="hidden">
						</td>
						<td style="white-space: nowrap;" class="ball_bg">
						<%
							list = animalMap.get("猴");
							for(int i=0;i<list.size();i++){
								String number = list.get(i);
								String className = HK6Util.colorClassNameMap.get(number);
						 %>
						 <span style="color: rgb(0, 0, 0);" class="ball_<%=className%>2"><%=number %></span>
						 <%} %>

						</td>
						<td class="ball_bg"><span class="STYLE10">鸡 </span>
						</td>
						<td class="ball_ff"><font id="bl_927">30</font>
						</td>
						<td class="ball_ff"><input id="sxsx611" name="sxsx611"
							value="0" type="hidden"> <input id="Num_927"
							onclick="return SubChkBox(this,11)" name="Num_927" value="鸡"
							type="checkbox"> <input id="sxbl11" name="sxbl11"
							value="0" type="hidden"></td>
						<td style="white-space: nowrap;" class="ball_bg">
						<%
							list = animalMap.get("鸡");
							for(int i=0;i<list.size();i++){
								String number = list.get(i);
								String className = HK6Util.colorClassNameMap.get(number);
						 %>
						 <span style="color: rgb(0, 0, 0);" class="ball_<%=className%>2"><%=number %></span>
						 <%} %>
						</td>
					</tr>
					<tr>
						<td class="ball_bg"><span class="STYLE10">狗</span>
						</td>
						<td class="ball_ff"><font id="bl_922">30</font>
						</td>
						<td class="ball_ff"><input id="sxsx66" name="sxsx66"
							value="0" type="hidden"> <input id="Num_922"
							onclick="return SubChkBox(this,6)" name="Num_922" value="狗"
							type="checkbox"> <input id="sxbl6" name="sxbl6" value="0"
							type="hidden">
						</td>
						<td style="white-space: nowrap;" class="ball_bg">
						<%
							list = animalMap.get("狗");
							for(int i=0;i<list.size();i++){
								String number = list.get(i);
								String className = HK6Util.colorClassNameMap.get(number);
						 %>
						 <span style="color: rgb(0, 0, 0);" class="ball_<%=className%>2"><%=number %></span>
						 <%} %>
						</td>
						<td class="ball_bg"><span class="STYLE10">猪 </span>
						</td>
						<td class="ball_ff"><font id="bl_928">30</font>
						</td>
						<td class="ball_ff"><input id="sxsx612" name="sxsx612"
							value="0" type="hidden"> <input id="Num_928"
							onclick="return SubChkBox(this,12)" name="Num_928" value="猪"
							type="checkbox"> <input id="sxbl12" name="sxbl12"
							value="0" type="hidden"></td>
						<td style="white-space: nowrap;" class="ball_bg">
						<%
							list = animalMap.get("猪");
							for(int i=0;i<list.size();i++){
								String number = list.get(i);
								String className = HK6Util.colorClassNameMap.get(number);
						 %>
						 <span style="color: rgb(0, 0, 0);" class="ball_<%=className%>2"><%=number %></span>
						 <%} %>
						</td>
					</tr>
					<tr>
						<td class="tbtitle" height="35" colSpan="8" align="center"><input
							id="btnSubmit" class="button_a" name="btnSubmit" value="下注"
							type="submit"> &nbsp;&nbsp;<input class="button_a"
							onclick="sel=','" name="Submit3" value="重设" type="reset">
						</td>
					</tr>
				</form>
			</tbody>
		</table>
	</div>
	<script type="text/javascript">
		var cp_gameTypeCode_temp="${results.gameTypeCode}";
		var cp_typeCode_temp="LX";
		var cp_cateCode_temp="SILX";
		var cp_xzlxCode_temp="";

	</script>
	<%@include file="/commons/cp/jsp/cp_fast_set_footer.jsp" %>

</body>
</html>