<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.mh.commons.utils.*,com.mh.web.util.HK6Util"%>
<%
	Map<String,List<String>> animalMap= AnimalUtil.getCurrAnimalCodeList();

 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html style="overflow-x:hidden">
<head>
<%@include file="/commons/cp/jsp/cp_common.jsp" %>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>webcom</title>
<style>
.ball_bg{ width: auto;}
</style>
</head>
<body ondragstart="window.event.returnValue=false" id="HOP"
	oncontextmenu="window.event.returnValue=false"
	onselectstart="event.returnValue=false">

	<script language="javascript">
		function SubChk(obj) {
			var type_nums = $("input[name=glx]:checked").val();
			if (type_nums == 1)
				type_min = 2;
			else if (type_nums == 2)
				type_min = 4;
			else
				type_min = 6;
			if (type_min > $(":checkbox:checked").length) {
				alert("至少需要选择" + type_min + "个生肖！");
				return false;
			}
			parent.k_meml.showOrder();
		}
		function clearCheck() {
			$(":checkbox").attr("checked", false);
		}
	</script>

	<div class="wrapCss_004">
		<table class="tab_001" border="0" cellSpacing="0" cellPadding="0"
			width="100%">
			<tbody>
				<tr style='background: url("${resourceRoot}/cp/images/title_bg_004.gif") repeat-x left top;'>
					<td><span class="font_sty001"><strong>二肖下注</strong> </span></td>
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
							onclick="document.location='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=HX&cpZuTypeCode=2x'"
							name="glx" value="1" CHECKED="checked" type="radio">
							二肖&nbsp; <input id="glx"
							onclick="document.location='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=HX&cpZuTypeCode=4x'"
							name="glx" value="2" type="radio"> 四肖&nbsp; <input
							id="glx"
							onclick="document.location='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=HX&cpZuTypeCode=6x'"
							name="glx" value="3" type="radio"> 六肖</td>
				</tr>
				<form name="order_form" id="order_form" onsubmit="return SubChk(this);" action="${ctx}/order/goMultiGroupOrderList?code=HK6&type=HX&cate=EX&multilen=2" method="post" target="bet_order_frame" autocomplete="off">
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
					<td class="ball_ff"><font id="bl_941">5.7</font>
					</td>
					<td class="ball_ff"><input id="Num_941" name="Num_941" value="鼠"
						type="checkbox">
					</td>
					<td style="white-space: nowrap;" class="ball_bg">
					<%
						List<String> list = animalMap.get("鼠");
						for(int i=0;i<list.size();i++){
							String number = list.get(i);
							if("49".equals(number)){
								continue;
							}
							String className = HK6Util.colorClassNameMap.get(number);
					 %>
					 <span style="color: rgb(0, 0, 0);" class="ball_<%=className%>2"><%=number %></span>
					 <%} %>
					</td>
					<td class="ball_bg"><span class="STYLE10">牛 </span>
					</td>
					<td class="ball_ff"><font id="bl_947">5.7</font>
					</td>
					<td class="ball_ff"><input id="Num_947" name="Num_947" value="牛"
						type="checkbox"></td>
					<td style="white-space: nowrap;" class="ball_bg">
					<%
						list = animalMap.get("牛");
						for(int i=0;i<list.size();i++){
							String number = list.get(i);
							if("49".equals(number)){
								continue;
							}
							String className = HK6Util.colorClassNameMap.get(number);
					 %>
					 <span style="color: rgb(0, 0, 0);" class="ball_<%=className%>2"><%=number %></span>
					 <%} %>

					</td>
				</tr>
				<tr>
					<td class="ball_bg"><span class="STYLE10">虎</span>
					</td>
					<td class="ball_ff"><font id="bl_942">5.7</font>
					</td>
					<td class="ball_ff"><input id="Num_942" name="Num_942" value="虎"
						type="checkbox">
					</td>
					<td style="white-space: nowrap;" class="ball_bg">
					<%
						list = animalMap.get("虎");
						for(int i=0;i<list.size();i++){
							String number = list.get(i);
							if("49".equals(number)){
								continue;
							}
							String className = HK6Util.colorClassNameMap.get(number);
					 %>
					 <span style="color: rgb(0, 0, 0);" class="ball_<%=className%>2"><%=number %></span>
					 <%} %>
					</td>
					<td class="ball_bg"><span class="STYLE10">兔 </span>
					</td>
					<td class="ball_ff"><font id="bl_948">5.7</font>
					</td>
					<td class="ball_ff"><input id="Num_948" name="Num_948" value="兔"
						type="checkbox"></td>
					<td style="white-space: nowrap;" class="ball_bg">
					<%
						list = animalMap.get("兔");
						for(int i=0;i<list.size();i++){
							String number = list.get(i);
							if("49".equals(number)){
								continue;
							}
							String className = HK6Util.colorClassNameMap.get(number);
					 %>
					 <span style="color: rgb(0, 0, 0);" class="ball_<%=className%>2"><%=number %></span>
					 <%} %>
					</td>
				</tr>
				<tr>
					<td class="ball_bg"><span class="STYLE10">龙</span>
					</td>
					<td class="ball_ff"><font id="bl_943">5.7</font>
					</td>
					<td class="ball_ff"><input id="Num_943" name="Num_943" value="龙"
						type="checkbox">
					</td>
					<td style="white-space: nowrap;" class="ball_bg">
					<%
						list = animalMap.get("龙");
						for(int i=0;i<list.size();i++){
							String number = list.get(i);
							if("49".equals(number)){
								continue;
							}
							String className = HK6Util.colorClassNameMap.get(number);
					 %>
					 <span style="color: rgb(0, 0, 0);" class="ball_<%=className%>2"><%=number %></span>
					 <%} %>
					</td>
					<td class="ball_bg"><span class="STYLE10">蛇 </span>
					</td>
					<td class="ball_ff"><font id="bl_949">5.7</font>
					</td>
					<td class="ball_ff"><input id="Num_949" name="Num_949" value="蛇"
						type="checkbox"></td>
					<td style="white-space: nowrap;" class="ball_bg">
					<%
						list = animalMap.get("蛇");
						for(int i=0;i<list.size();i++){
							String number = list.get(i);
							if("49".equals(number)){
								continue;
							}
							String className = HK6Util.colorClassNameMap.get(number);
					 %>
					 <span style="color: rgb(0, 0, 0);" class="ball_<%=className%>2"><%=number %></span>
					 <%} %>
					</td>
				</tr>
				<tr>
					<td class="ball_bg"><span class="STYLE10">马</span>
					</td>
					<td class="ball_ff"><font id="bl_944">5.7</font>
					</td>
					<td class="ball_ff"><input id="Num_944" name="Num_944" value="马"
						type="checkbox">
					</td>
					<td style="white-space: nowrap;" class="ball_bg">
					<%
						list = animalMap.get("马");
						for(int i=0;i<list.size();i++){
							String number = list.get(i);
							if("49".equals(number)){
								continue;
							}
							String className = HK6Util.colorClassNameMap.get(number);
					 %>
					 <span style="color: rgb(0, 0, 0);" class="ball_<%=className%>2"><%=number %></span>
					 <%} %>
					</td>
					<td class="ball_bg"><span class="STYLE10">羊 </span>
					</td>
					<td class="ball_ff"><font id="bl_950">5.5</font>
					</td>
					<td class="ball_ff"><input id="Num_950" name="Num_950" value="羊"
						type="checkbox"></td>
					<td style="white-space: nowrap;" class="ball_bg">
					<%
						list = animalMap.get("羊");
						for(int i=0;i<list.size();i++){
							String number = list.get(i);
							if("49".equals(number)){
								continue;
							}
							String className = HK6Util.colorClassNameMap.get(number);
					 %>
					 <span style="color: rgb(0, 0, 0);" class="ball_<%=className%>2"><%=number %></span>
					 <%} %>
					</td>
				</tr>
				<tr>
					<td class="ball_bg"><span class="STYLE10">猴</span>
					</td>
					<td class="ball_ff"><font id="bl_945">5.7</font>
					</td>
					<td class="ball_ff"><input id="Num_945" name="Num_945" value="猴"
						type="checkbox">
					</td>
					<td style="white-space: nowrap;" class="ball_bg">
					<%
						list = animalMap.get("猴");
						for(int i=0;i<list.size();i++){
							String number = list.get(i);
							if("49".equals(number)){
								continue;
							}
							String className = HK6Util.colorClassNameMap.get(number);
					 %>
					 <span style="color: rgb(0, 0, 0);" class="ball_<%=className%>2"><%=number %></span>
					 <%} %>
					</td>
					<td class="ball_bg"><span class="STYLE10">鸡 </span>
					</td>
					<td class="ball_ff"><font id="bl_951">5.7</font>
					</td>
					<td class="ball_ff"><input id="Num_951" name="Num_951" value="鸡"
						type="checkbox"></td>
					<td style="white-space: nowrap;" class="ball_bg">
					<%
						list = animalMap.get("鸡");
						for(int i=0;i<list.size();i++){
							String number = list.get(i);
							if("49".equals(number)){
								continue;
							}
							String className = HK6Util.colorClassNameMap.get(number);
					 %>
					 <span style="color: rgb(0, 0, 0);" class="ball_<%=className%>2"><%=number %></span>
					 <%} %>
					</td>
				</tr>
				<tr>
					<td class="ball_bg"><span class="STYLE10">狗</span>
					</td>
					<td class="ball_ff"><font id="bl_946">5.7</font>
					</td>
					<td class="ball_ff"><input id="Num_946" name="Num_946" value="狗"
						type="checkbox">
					</td>
					<td style="white-space: nowrap;" class="ball_bg">
					<%
						list = animalMap.get("狗");
						for(int i=0;i<list.size();i++){
							String number = list.get(i);
							if("49".equals(number)){
								continue;
							}
							String className = HK6Util.colorClassNameMap.get(number);
					 %>
					 <span style="color: rgb(0, 0, 0);" class="ball_<%=className%>2"><%=number %></span>
					 <%} %>
					</td>
					<td class="ball_bg"><span class="STYLE10">猪 </span>
					</td>
					<td class="ball_ff"><font id="bl_952">5.7</font>
					</td>
					<td class="ball_ff"><input id="Num_952" name="Num_952" value="猪"
						type="checkbox"></td>
					<td style="white-space: nowrap;" class="ball_bg">
					<%
						list = animalMap.get("猪");
						for(int i=0;i<list.size();i++){
							String number = list.get(i);
							if("49".equals(number)){
								continue;
							}
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
		var cp_typeCode_temp="HX";
		var cp_cateCode_temp="EX";
		var cp_xzlxCode_temp="";

	</script>
	<%@include file="/commons/cp/jsp/cp_fast_set_footer.jsp" %>

</body>
</html>