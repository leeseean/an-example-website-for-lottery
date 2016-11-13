<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html style="overflow-x:hidden">
<head>
<%@include file="/commons/cp/jsp/cp_common.jsp"%>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>webcom</title>
</head>
<body ondragstart="window.event.returnValue=false" id="HOP" oncontextmenu="window.event.returnValue=false" onselectstart="event.returnValue=false">
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
		<table class="tab_001" border="0" cellSpacing="0" cellPadding="0" width="100%">
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

				<form name="order_form" id="order_form" action="${ctx}/goLeft?action=n1&amp;class2=特B&amp;lx=0&amp;uid=5f42ce51144b7c99bb0ef&amp;langx=zh-cn" method="post" target="bet_order_frame" autocomplete="off"></form>

				<tr class="hset">
					<td class="tbtitle4" colSpan="8">类型：<input id="glx" onclick="document.location='main.php?action=hx/hx.jsp'" name="glx" value="1" CHECKED="checked" type="radio"> 二肖&nbsp; <input id="glx" onclick="document.location='main.php?action=hx/sx.jsp'"
						name="glx" value="2" type="radio"> 四肖&nbsp; <input id="glx" onclick="document.location='main.php?action=hx/lx.jsp'" name="glx" value="3" type="radio"> 六肖</td>
				</tr>

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
					<td class="ball_ff"><input id="num1" name="num1" value="鼠" type="checkbox">
					</td>
					<td style="white-space: nowrap;" class="ball_bg"><span style="color: rgb(0, 0, 0);" class="ball_r2">08</span><span style="color: rgb(0, 0, 0);" class="ball_b2">20</span><span style="color: rgb(0, 0, 0);" class="ball_g2">32</span><span
						style="color: rgb(0, 0, 0);" class="ball_g2">44</span>
					</td>
					<td class="ball_bg"><span class="STYLE10">牛 </span>
					</td>
					<td class="ball_ff"><font id="bl_947">5.7</font>
					</td>
					<td class="ball_ff"><input id="num7" name="num7" value="牛" type="checkbox"></td>
					<td style="white-space: nowrap;" class="ball_bg"><span style="color: rgb(0, 0, 0);" class="ball_r2">07</span><span style="color: rgb(0, 0, 0);" class="ball_r2">19</span><span style="color: rgb(0, 0, 0);" class="ball_b2">31</span><span
						style="color: rgb(0, 0, 0);" class="ball_g2">43</span>
					</td>
				</tr>
				<tr>
					<td class="ball_bg"><span class="STYLE10">虎</span>
					</td>
					<td class="ball_ff"><font id="bl_942">5.7</font>
					</td>
					<td class="ball_ff"><input id="num2" name="num2" value="虎" type="checkbox">
					</td>
					<td style="white-space: nowrap;" class="ball_bg"><span style="color: rgb(0, 0, 0);" class="ball_g2">06</span><span style="color: rgb(0, 0, 0);" class="ball_r2">18</span><span style="color: rgb(0, 0, 0);" class="ball_r2">30</span><span
						style="color: rgb(0, 0, 0);" class="ball_b2">42</span>
					</td>
					<td class="ball_bg"><span class="STYLE10">兔 </span>
					</td>
					<td class="ball_ff"><font id="bl_948">5.7</font>
					</td>
					<td class="ball_ff"><input id="num8" name="num8" value="兔" type="checkbox"></td>
					<td style="white-space: nowrap;" class="ball_bg"><span style="color: rgb(0, 0, 0);" class="ball_g2">05</span><span style="color: rgb(0, 0, 0);" class="ball_g2">17</span><span style="color: rgb(0, 0, 0);" class="ball_r2">29</span><span
						style="color: rgb(0, 0, 0);" class="ball_b2">41</span>
					</td>
				</tr>
				<tr>
					<td class="ball_bg"><span class="STYLE10">龙</span>
					</td>
					<td class="ball_ff"><font id="bl_943">5.7</font>
					</td>
					<td class="ball_ff"><input id="num3" name="num3" value="龙" type="checkbox">
					</td>
					<td style="white-space: nowrap;" class="ball_bg"><span style="color: rgb(0, 0, 0);" class="ball_b2">04</span><span style="color: rgb(0, 0, 0);" class="ball_g2">16</span><span style="color: rgb(0, 0, 0);" class="ball_g2">28</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">40</span>
					</td>
					<td class="ball_bg"><span class="STYLE10">蛇 </span>
					</td>
					<td class="ball_ff"><font id="bl_949">5.7</font>
					</td>
					<td class="ball_ff"><input id="num9" name="num9" value="蛇" type="checkbox"></td>
					<td style="white-space: nowrap;" class="ball_bg"><span style="color: rgb(0, 0, 0);" class="ball_b2">03</span><span style="color: rgb(0, 0, 0);" class="ball_b2">15</span><span style="color: rgb(0, 0, 0);" class="ball_g2">27</span><span
						style="color: rgb(0, 0, 0);" class="ball_g2">39</span>
					</td>
				</tr>
				<tr>
					<td class="ball_bg"><span class="STYLE10">马</span>
					</td>
					<td class="ball_ff"><font id="bl_944">5.7</font>
					</td>
					<td class="ball_ff"><input id="num4" name="num4" value="马" type="checkbox">
					</td>
					<td style="white-space: nowrap;" class="ball_bg"><span style="color: rgb(0, 0, 0);" class="ball_r2">02</span><span style="color: rgb(0, 0, 0);" class="ball_b2">14</span><span style="color: rgb(0, 0, 0);" class="ball_b2">26</span><span
						style="color: rgb(0, 0, 0);" class="ball_g2">38</span>
					</td>
					<td class="ball_bg"><span class="STYLE10">羊 </span>
					</td>
					<td class="ball_ff"><font id="bl_950">5.5</font>
					</td>
					<td class="ball_ff"><input id="num10" name="num10" value="羊" type="checkbox"></td>
					<td style="white-space: nowrap;" class="ball_bg"><span style="color: rgb(0, 0, 0);" class="ball_r2">01</span><span style="color: rgb(0, 0, 0);" class="ball_r2">13</span><span style="color: rgb(0, 0, 0);" class="ball_b2">25</span><span
						style="color: rgb(0, 0, 0);" class="ball_b2">37</span>
					</td>
				</tr>
				<tr>
					<td class="ball_bg"><span class="STYLE10">猴</span>
					</td>
					<td class="ball_ff"><font id="bl_945">5.7</font>
					</td>
					<td class="ball_ff"><input id="num5" name="num5" value="猴" type="checkbox">
					</td>
					<td style="white-space: nowrap;" class="ball_bg"><span style="color: rgb(0, 0, 0);" class="ball_r2">12</span><span style="color: rgb(0, 0, 0);" class="ball_r2">24</span><span style="color: rgb(0, 0, 0);" class="ball_b2">36</span><span
						style="color: rgb(0, 0, 0);" class="ball_b2">48</span>
					</td>
					<td class="ball_bg"><span class="STYLE10">鸡 </span>
					</td>
					<td class="ball_ff"><font id="bl_951">5.7</font>
					</td>
					<td class="ball_ff"><input id="num11" name="num11" value="鸡" type="checkbox"></td>
					<td style="white-space: nowrap;" class="ball_bg"><span style="color: rgb(0, 0, 0);" class="ball_g2">11</span><span style="color: rgb(0, 0, 0);" class="ball_r2">23</span><span style="color: rgb(0, 0, 0);" class="ball_r2">35</span><span
						style="color: rgb(0, 0, 0);" class="ball_b2">47</span>
					</td>
				</tr>
				<tr>
					<td class="ball_bg"><span class="STYLE10">狗</span>
					</td>
					<td class="ball_ff"><font id="bl_946">5.7</font>
					</td>
					<td class="ball_ff"><input id="num6" name="num6" value="狗" type="checkbox">
					</td>
					<td style="white-space: nowrap;" class="ball_bg"><span style="color: rgb(0, 0, 0);" class="ball_b2">10</span><span style="color: rgb(0, 0, 0);" class="ball_g2">22</span><span style="color: rgb(0, 0, 0);" class="ball_r2">34</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">46</span>
					</td>
					<td class="ball_bg"><span class="STYLE10">猪 </span>
					</td>
					<td class="ball_ff"><font id="bl_952">5.7</font>
					</td>
					<td class="ball_ff"><input id="num12" name="num12" value="猪" type="checkbox"></td>
					<td style="white-space: nowrap;" class="ball_bg"><span style="color: rgb(0, 0, 0);" class="ball_b2">09</span><span style="color: rgb(0, 0, 0);" class="ball_g2">21</span><span style="color: rgb(0, 0, 0);" class="ball_g2">33</span><span
						style="color: rgb(0, 0, 0);" class="ball_r2">45</span>
					</td>
				</tr>
				<tr>
					<td class="tbtitle" height="35" colSpan="8" align="center"><input id="btnSubmit" class="button_a" name="btnSubmit" value="下注" type="submit"> &nbsp;&nbsp; <input class="button_a" onclick="sel=','" name="Submit3" value="重设" type="reset">
					</td>
				</tr>
				</form>
			</tbody>
		</table>
	</div>
	<script type="text/javascript">
		var url = "${ctx}/cp/server";
		var params = 'mU+H/jdLZgyyM2hCkOBh1Q==';
		var ftime = 30000;
		var sexxx = 1000000;
		var dxxe = 50000;
		var xyed = 0.00;
		var minxy = 0;
	</script>
	<script type="text/javascript">
		var cp_gameTypeCode_temp="${results.gameTypeCode}";
		var cp_typeCode_temp="HX";
		var cp_cateCode_temp="";
		var cp_xzlxCode_temp="";

	</script>
	<script src="${resourceRoot}/js/js.php?uid=5f42ce51144b7c99bb0ef&amp;langx=zh-cn" type="text/javascript"></script>
	<div id="kspanel">
		<div class="divheader">下注金额</div>
		<div>
			<ul>
				<li>100元</li>
				<li>300元</li>
				<li>500元</li>
				<li>1000元</li>
				<li>5000元</li>
			</ul>
		</div>
	</div>

</body>
</html>