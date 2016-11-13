<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

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
			document.getElementsByName('num1')[0].checked = false;
			document.getElementsByName('num2')[0].checked = false;
			document.getElementsByName('num3')[0].checked = false;
			document.getElementsByName('num4')[0].checked = false;
			document.getElementsByName('num5')[0].checked = false;
			document.getElementsByName('num6')[0].checked = false;
			document.getElementsByName('num7')[0].checked = false;
			document.getElementsByName('num8')[0].checked = false;
			document.getElementsByName('num9')[0].checked = false;
			document.getElementsByName('num10')[0].checked = false;
			document.getElementsByName('num11')[0].checked = false;
			document.getElementsByName('num12')[0].checked = false;
		}
	</script>

	<div class="wrapCss_004">
		<table class="tab_001" border="0" cellSpacing="0" cellPadding="0"
			width="100%">
			<tbody>
				<tr style='background: url("${resourceRoot}/images/title_bg_004.gif") repeat-x left top;'>
					<td><span class="font_sty001"><strong>二肖连[中]下注</strong></span></td>
					<%@include file="/commons/cp/jsp/hk6_results.jsp" %>
				</tr>
			</tbody>
		</table>
		<table class="game_table" border="0" cellSpacing="1" cellPadding="0">
			<tbody>
				<tr class="tbtitle">
					<td id="time_td" colSpan="8">距离封盘时间：<font color="#000000"><span
							id="stime"><font color="#7aff00"><b>2</b>
							</font> 天 <font color="#7aff00"><b>8</b>
							</font> 小时 <font color="#7aff00"><b>11</b>
							</font> 分 <font color="#7aff00"><b>27</b>
							</font> 秒</span>
					</font>&nbsp;&nbsp; <span style="display: none;" id="allgold">0</span> <script
							language="javascript">
						gametimeRef("2015-06-02 09:30:00", 'stime');
					</script></td>
				</tr>
				<tr class="hsetEm">
					<td class="tbtitle" colSpan="8"></td>
				</tr>
				<form id="order_form" onsubmit="return SubChk(this);" method="post"
					name="order_form"
					action="main.php?action=n66&amp;uid=b519d9e54d9a9e6e60717&amp;langx=zh-cn&amp;lx=0"
					target="bet_order_frame">



					<tr class="hset">
						<td class="tbtitle4" colSpan="8">类型：<input id="glx"
							onclick="document.location='main.php?action=k_sxl&amp;ids=二肖连[中]&amp;uid=b519d9e54d9a9e6e60717&amp;langx=zh-cn'"
							name="glx" value="1" CHECKED="checked" type="radio">
							二肖连[中]&nbsp; <input id="glx"
							onclick="document.location='main.php?action=k_sxl&amp;ids=三肖连[中]&amp;uid=b519d9e54d9a9e6e60717&amp;langx=zh-cn'"
							name="glx" value="3" type="radio"> 三肖连[中]&nbsp; <input
							id="glx"
							onclick="document.location='main.php?action=k_sxl&amp;ids=四肖连[中]&amp;uid=b519d9e54d9a9e6e60717&amp;langx=zh-cn'"
							name="glx" value="5" type="radio"> 四肖连[中]&nbsp; <input
							id="glx"
							onclick="document.location='main.php?action=k_sxl&amp;ids=五肖连[中]&amp;uid=b519d9e54d9a9e6e60717&amp;langx=zh-cn'"
							name="glx" value="7" type="radio"> 五肖连[中]&nbsp;</td>
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
						<td class="ball_ff"><font id="bl_3269">4.2</font>
						</td>
						<td class="ball_ff"><input id="sxsx61" name="sxsx61"
							value="0" type="hidden"> <input id="num1"
							onclick="return SubChkBox(this,1)" name="num1" value="鼠"
							type="checkbox"> <input id="sxbl1" name="sxbl1" value="0"
							type="hidden">
						</td>
						<td style="white-space: nowrap;" class="ball_bg"><span
							style="color: rgb(0, 0, 0);" class="ball_r2">08</span><span
							style="color: rgb(0, 0, 0);" class="ball_b2">20</span><span
							style="color: rgb(0, 0, 0);" class="ball_g2">32</span><span
							style="color: rgb(0, 0, 0);" class="ball_g2">44</span>
						</td>
						<td class="ball_bg"><span class="STYLE10">牛 </span>
						</td>
						<td class="ball_ff"><font id="bl_3275">4.2</font>
						</td>
						<td class="ball_ff"><input id="sxsx67" name="sxsx67"
							value="0" type="hidden"> <input id="num7"
							onclick="return SubChkBox(this,7)" name="num7" value="牛"
							type="checkbox"> <input id="sxbl7" name="sxbl7" value="0"
							type="hidden"></td>
						<td style="white-space: nowrap;" class="ball_bg"><span
							style="color: rgb(0, 0, 0);" class="ball_r2">07</span><span
							style="color: rgb(0, 0, 0);" class="ball_r2">19</span><span
							style="color: rgb(0, 0, 0);" class="ball_b2">31</span><span
							style="color: rgb(0, 0, 0);" class="ball_g2">43</span>
						</td>
					</tr>
					<tr>
						<td class="ball_bg"><span class="STYLE10">虎</span>
						</td>
						<td class="ball_ff"><font id="bl_3270">4.2</font>
						</td>
						<td class="ball_ff"><input id="sxsx62" name="sxsx62"
							value="0" type="hidden"> <input id="num2"
							onclick="return SubChkBox(this,2)" name="num2" value="虎"
							type="checkbox"> <input id="sxbl2" name="sxbl2" value="0"
							type="hidden">
						</td>
						<td style="white-space: nowrap;" class="ball_bg"><span
							style="color: rgb(0, 0, 0);" class="ball_g2">06</span><span
							style="color: rgb(0, 0, 0);" class="ball_r2">18</span><span
							style="color: rgb(0, 0, 0);" class="ball_r2">30</span><span
							style="color: rgb(0, 0, 0);" class="ball_b2">42</span>
						</td>
						<td class="ball_bg"><span class="STYLE10">兔 </span>
						</td>
						<td class="ball_ff"><font id="bl_3276">4.2</font>
						</td>
						<td class="ball_ff"><input id="sxsx68" name="sxsx68"
							value="0" type="hidden"> <input id="num8"
							onclick="return SubChkBox(this,8)" name="num8" value="兔"
							type="checkbox"> <input id="sxbl8" name="sxbl8" value="0"
							type="hidden"></td>
						<td style="white-space: nowrap;" class="ball_bg"><span
							style="color: rgb(0, 0, 0);" class="ball_g2">05</span><span
							style="color: rgb(0, 0, 0);" class="ball_g2">17</span><span
							style="color: rgb(0, 0, 0);" class="ball_r2">29</span><span
							style="color: rgb(0, 0, 0);" class="ball_b2">41</span>
						</td>
					</tr>
					<tr>
						<td class="ball_bg"><span class="STYLE10">龙</span>
						</td>
						<td class="ball_ff"><font id="bl_3271">4.2</font>
						</td>
						<td class="ball_ff"><input id="sxsx63" name="sxsx63"
							value="0" type="hidden"> <input id="num3"
							onclick="return SubChkBox(this,3)" name="num3" value="龙"
							type="checkbox"> <input id="sxbl3" name="sxbl3" value="0"
							type="hidden">
						</td>
						<td style="white-space: nowrap;" class="ball_bg"><span
							style="color: rgb(0, 0, 0);" class="ball_b2">04</span><span
							style="color: rgb(0, 0, 0);" class="ball_g2">16</span><span
							style="color: rgb(0, 0, 0);" class="ball_g2">28</span><span
							style="color: rgb(0, 0, 0);" class="ball_r2">40</span>
						</td>
						<td class="ball_bg"><span class="STYLE10">蛇 </span>
						</td>
						<td class="ball_ff"><font id="bl_3277">4.2</font>
						</td>
						<td class="ball_ff"><input id="sxsx69" name="sxsx69"
							value="0" type="hidden"> <input id="num9"
							onclick="return SubChkBox(this,9)" name="num9" value="蛇"
							type="checkbox"> <input id="sxbl9" name="sxbl9" value="0"
							type="hidden"></td>
						<td style="white-space: nowrap;" class="ball_bg"><span
							style="color: rgb(0, 0, 0);" class="ball_b2">03</span><span
							style="color: rgb(0, 0, 0);" class="ball_b2">15</span><span
							style="color: rgb(0, 0, 0);" class="ball_g2">27</span><span
							style="color: rgb(0, 0, 0);" class="ball_g2">39</span>
						</td>
					</tr>
					<tr>
						<td class="ball_bg"><span class="STYLE10">马</span>
						</td>
						<td class="ball_ff"><font id="bl_3272">4.2</font>
						</td>
						<td class="ball_ff"><input id="sxsx64" name="sxsx64"
							value="0" type="hidden"> <input id="num4"
							onclick="return SubChkBox(this,4)" name="num4" value="马"
							type="checkbox"> <input id="sxbl4" name="sxbl4" value="0"
							type="hidden">
						</td>
						<td style="white-space: nowrap;" class="ball_bg"><span
							style="color: rgb(0, 0, 0);" class="ball_r2">02</span><span
							style="color: rgb(0, 0, 0);" class="ball_b2">14</span><span
							style="color: rgb(0, 0, 0);" class="ball_b2">26</span><span
							style="color: rgb(0, 0, 0);" class="ball_g2">38</span>
						</td>
						<td class="ball_bg"><span class="STYLE10">羊 </span>
						</td>
						<td class="ball_ff"><font id="bl_3278">3.5</font>
						</td>
						<td class="ball_ff"><input id="sxsx610" name="sxsx610"
							value="0" type="hidden"> <input id="num10"
							onclick="return SubChkBox(this,10)" name="num10" value="羊"
							type="checkbox"> <input id="sxbl10" name="sxbl10"
							value="0" type="hidden"></td>
						<td style="white-space: nowrap;" class="ball_bg"><span
							style="color: rgb(0, 0, 0);" class="ball_r2">01</span><span
							style="color: rgb(0, 0, 0);" class="ball_r2">13</span><span
							style="color: rgb(0, 0, 0);" class="ball_b2">25</span><span
							style="color: rgb(0, 0, 0);" class="ball_b2">37</span><span
							style="color: rgb(0, 0, 0);" class="ball_g2">49</span>
						</td>
					</tr>
					<tr>
						<td class="ball_bg"><span class="STYLE10">猴</span>
						</td>
						<td class="ball_ff"><font id="bl_3273">4.2</font>
						</td>
						<td class="ball_ff"><input id="sxsx65" name="sxsx65"
							value="0" type="hidden"> <input id="num5"
							onclick="return SubChkBox(this,5)" name="num5" value="猴"
							type="checkbox"> <input id="sxbl5" name="sxbl5" value="0"
							type="hidden">
						</td>
						<td style="white-space: nowrap;" class="ball_bg"><span
							style="color: rgb(0, 0, 0);" class="ball_r2">12</span><span
							style="color: rgb(0, 0, 0);" class="ball_r2">24</span><span
							style="color: rgb(0, 0, 0);" class="ball_b2">36</span><span
							style="color: rgb(0, 0, 0);" class="ball_b2">48</span>
						</td>
						<td class="ball_bg"><span class="STYLE10">鸡 </span>
						</td>
						<td class="ball_ff"><font id="bl_3279">4.2</font>
						</td>
						<td class="ball_ff"><input id="sxsx611" name="sxsx611"
							value="0" type="hidden"> <input id="num11"
							onclick="return SubChkBox(this,11)" name="num11" value="鸡"
							type="checkbox"> <input id="sxbl11" name="sxbl11"
							value="0" type="hidden"></td>
						<td style="white-space: nowrap;" class="ball_bg"><span
							style="color: rgb(0, 0, 0);" class="ball_g2">11</span><span
							style="color: rgb(0, 0, 0);" class="ball_r2">23</span><span
							style="color: rgb(0, 0, 0);" class="ball_r2">35</span><span
							style="color: rgb(0, 0, 0);" class="ball_b2">47</span>
						</td>
					</tr>
					<tr>
						<td class="ball_bg"><span class="STYLE10">狗</span>
						</td>
						<td class="ball_ff"><font id="bl_3274">4.2</font>
						</td>
						<td class="ball_ff"><input id="sxsx66" name="sxsx66"
							value="0" type="hidden"> <input id="num6"
							onclick="return SubChkBox(this,6)" name="num6" value="狗"
							type="checkbox"> <input id="sxbl6" name="sxbl6" value="0"
							type="hidden">
						</td>
						<td style="white-space: nowrap;" class="ball_bg"><span
							style="color: rgb(0, 0, 0);" class="ball_b2">10</span><span
							style="color: rgb(0, 0, 0);" class="ball_g2">22</span><span
							style="color: rgb(0, 0, 0);" class="ball_r2">34</span><span
							style="color: rgb(0, 0, 0);" class="ball_r2">46</span>
						</td>
						<td class="ball_bg"><span class="STYLE10">猪 </span>
						</td>
						<td class="ball_ff"><font id="bl_3280">4.2</font>
						</td>
						<td class="ball_ff"><input id="sxsx612" name="sxsx612"
							value="0" type="hidden"> <input id="num12"
							onclick="return SubChkBox(this,12)" name="num12" value="猪"
							type="checkbox"> <input id="sxbl12" name="sxbl12"
							value="0" type="hidden"></td>
						<td style="white-space: nowrap;" class="ball_bg"><span
							style="color: rgb(0, 0, 0);" class="ball_b2">09</span><span
							style="color: rgb(0, 0, 0);" class="ball_g2">21</span><span
							style="color: rgb(0, 0, 0);" class="ball_g2">33</span><span
							style="color: rgb(0, 0, 0);" class="ball_r2">45</span>
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
		var url = "server.php?uid=b519d9e54d9a9e6e60717&p=MFg5Iz7OF/vepJCc/P4bWpAGZu2jICiR0LU+srEypFSYg1lRQlmgdgzY/46hDyHDL2Y6zIZhWNxtBnW3rSfckESbzAuFLSEjl4WJQuDFBnc8HGzfeG0xollaEWJHDVjK";
		var params = 'MFg5Iz7OF/vepJCc/P4bWpAGZu2jICiR0LU+srEypFSYg1lRQlmgdgzY/46hDyHDL2Y6zIZhWNxtBnW3rSfckESbzAuFLSEjl4WJQuDFBnc8HGzfeG0xollaEWJHDVjK';
		var ftime = 15000;
		var pcflag = '33';
		var pc = new Array();
		pc['pc33'] = 0;
		var sexxx = 300000;
		var dxxe = 20000;
		var xyed = 0.00;
		var minxy = 0;
	</script>
	<script type="text/javascript">
		var cp_gameTypeCode_temp="${results.gameTypeCode}";
		var cp_typeCode_temp="LX";
		var cp_cateCode_temp="";
		var cp_xzlxCode_temp="";

	</script>
	<script type="text/javascript"
		src="js.php?uid=b519d9e54d9a9e6e60717&amp;langx=zh-cn"></script>
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