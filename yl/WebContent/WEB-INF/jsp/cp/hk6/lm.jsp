<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.util.*,com.mh.commons.utils.*,com.mh.web.util.HK6Util"%>
<%
	Map<String,List<String>> animalMap= AnimalUtil.getCurrAnimalCodeList();
	Map<String,String> map = new HashMap<String,String>();
	for(String key:animalMap.keySet()){
		List<String> list = animalMap.get(key);
		StringBuffer buffer = new StringBuffer("");
		for(int i=0;i<list.size();i++){
			if(i>0){
				buffer.append(",");
			}
			buffer.append(list.get(i));
		}
		map.put(key, buffer.toString());
	}

 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html style="overflow-x:hidden">
<head>

<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>webcom</title>
<%@include file="/commons/cp/jsp/cp_common.jsp"%>
</head>
<body>
	<div class="wrapCss_004">
		<table class="tab_001" border="0" cellSpacing="0" cellPadding="0"
			width="100%">
			<tbody>
				<tr style='background: url("${resourceRoot}/cp/images/title_bg_004.gif") repeat-x left top;'>
					<td><span class="font_sty001"><strong>连码下注</strong> </span></td>
					<%@include file="/commons/cp/jsp/hk6_results.jsp" %>
				</tr>
			</tbody>
		</table>
		<form name="order_form" id="order_form"
			onsubmit="return SubChk(this);"  action="${ctx}/order/goLmOrder?code=HK6&type=LM"
			method="post" target="bet_order_frame" >
			<table border="0" cellpadding="0" cellspacing="1" class="game_table">
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
						<td class="tbtitle" colspan="10"></td>
					</tr>
					<tr class="hset">
						<td class="tbtitle4" colSpan="10">类别：
						 <input id="rrtype"name="rrtype" value="1" type="hidden">
						 <input onclick="select_types(2);" name="rtype" id="rtype_0" value="0" checked="checked" type="radio"> 三全中
						 <input onclick="select_types(1);"name="rtype" id="rtype_1" value="1" type="radio"> 三中二
						 <input onclick="select_types(3);" name="rtype" id="rtype_2" value="2" type="radio">二全中
						 <input onclick="select_types(4);" name="rtype" id="rtype_3" value="3"type="radio"> 二中特
						 <input onclick="select_types(5);"name="rtype" id="rtype_4" value="4" type="radio"> 特串</td>
					</tr>
					<tr class="hset">
						<td class="tbtitle" colSpan="10">赔率：
							三全中 <font id="bl_496" class="ball_ff1">630</font>&nbsp;&nbsp;
							中二 <font id="bl_497" class="ball_ff1">20</font>&nbsp;&nbsp;
							中三 <font id="bl_498" class="ball_ff1">85</font>&nbsp;&nbsp;
							二全中 <font id="bl_499" class="ball_ff1">61</font>&nbsp;&nbsp;
							中特 <font id="bl_500" class="ball_ff1">30</font>&nbsp;&nbsp;
							中二 <font id="bl_501" class="ball_ff1">50</font>&nbsp;&nbsp;
							特串 <font id="bl_502" class="ball_ff1">150</font>
						</td>
					</tr>
					<tr class="tbtitle2">
						<td width="30">号码</td>
						<td width="70">勾选</td>
						<td width="30">号码</td>
						<td width="70">勾选</td>
						<td width="30">号码</td>
						<td width="70">勾选</td>
						<td width="30">号码</td>
						<td width="70">勾选</td>
						<td width="30">号码</td>
						<td width="70">勾选</td>
					</tr>
					<tr class="tbtitle">
						<td height="35" class="ball_r">01</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,1)"
							type="checkbox" value="01" name="num1" id="num1">
						</td>
						<td class="ball_g">11</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,11)"
							type="checkbox" value="11" name="num11">
						</td>
						<td class="ball_g">21</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,21)"
							type="checkbox" value="21" name="num21">
						</td>
						<td class="ball_b">31</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,31)"
							type="checkbox" value="31" name="num31">
						</td>
						<td class="ball_b">41</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,41)"
							type="checkbox" value="41" name="num41">
						</td>
					</tr>
					<tr class="tbtitle">
						<td height="35" class="ball_r">02</td>
						<td class="ball_ff"><input name="num2" type="checkbox"
							onclick="SubChkBox(this,2)" value="02">
						</td>
						<td class="ball_r">12</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,12)"
							type="checkbox" value="12" name="num12">
						</td>
						<td class="ball_g">22</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,22)"
							type="checkbox" value="22" name="num22">
						</td>
						<td class="ball_g">32</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,32)"
							type="checkbox" value="32" name="num32">
						</td>
						<td class="ball_b">42</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,42)"
							type="checkbox" value="42" name="num42">
						</td>
					</tr>
					<tr class="tbtitle">
						<td height="35" class="ball_b">03</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,3)"
							type="checkbox" value="03" name="num3">
						</td>
						<td class="ball_r">13</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,13)"
							type="checkbox" value="13" name="num13">
						</td>
						<td class="ball_r">23</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,23)"
							type="checkbox" value="23" name="num23">
						</td>
						<td class="ball_g">33</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,33)"
							type="checkbox" value="33" name="num33">
						</td>
						<td class="ball_g">43</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,43)"
							type="checkbox" value="43" name="num43">
						</td>
					</tr>
					<tr class="tbtitle">
						<td height="35" class="ball_b">04</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,4)"
							type="checkbox" value="04" name="num4">
						</td>
						<td class="ball_b">14</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,14)"
							type="checkbox" value="14" name="num14">
						</td>
						<td class="ball_r">24</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,24)"
							type="checkbox" value="24" name="num24">
						</td>
						<td class="ball_r">34</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,34)"
							type="checkbox" value="34" name="num34">
						</td>
						<td class="ball_g">44</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,44)"
							type="checkbox" value="44" name="num44">
						</td>
					</tr>
					<tr class="tbtitle">
						<td height="35" class="ball_g">05</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,5)"
							type="checkbox" value="05" name="num5">
						</td>
						<td class="ball_b">15</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,15)"
							type="checkbox" value="15" name="num15">
						</td>
						<td class="ball_b">25</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,25)"
							type="checkbox" value="25" name="num25">
						</td>
						<td class="ball_r">35</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,35)"
							type="checkbox" value="35" name="num35">
						</td>
						<td class="ball_r">45</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,45)"
							type="checkbox" value="45" name="num45">
						</td>
					</tr>
					<tr class="tbtitle">
						<td height="35" class="ball_g">06</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,6)"
							type="checkbox" value="06" name="num6">
						</td>
						<td class="ball_g">16</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,16)"
							type="checkbox" value="16" name="num16" >
						</td>
						<td class="ball_b">26</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,26)"
							type="checkbox" value="26" name="num26">
						</td>
						<td class="ball_b">36</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,36)"
							type="checkbox" value="36" name="num36">
						</td>
						<td class="ball_r">46</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,46)"
							type="checkbox" value="46" name="num46">
						</td>
					</tr>
					<tr class="tbtitle">
						<td height="35" class="ball_r">07</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,7)"
							type="checkbox" value="07" name="num7">
						</td>
						<td class="ball_g">17</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,17)"
							type="checkbox" value="17" name="num17">
						</td>
						<td class="ball_g">27</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,27)"
							type="checkbox" value="27" name="num27">
						</td>
						<td class="ball_b">37</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,37)"
							type="checkbox" value="37" name="num37">
						</td>
						<td class="ball_b">47</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,47)"
							type="checkbox" value="47" name="num47">
						</td>
					</tr>
					<tr class="tbtitle">
						<td height="35" class="ball_r">08</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,8)"
							type="checkbox" value="08" name="num8">
						</td>
						<td class="ball_r">18</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,18)"
							type="checkbox" value="18" name="num18">
						</td>
						<td class="ball_g">28</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,28)"
							type="checkbox" value="28" name="num28">
						</td>
						<td class="ball_g">38</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,38)"
							type="checkbox" value="38" name="num38">
						</td>
						<td class="ball_b">48</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,48)"
							type="checkbox" value="48" name="num48">
						</td>
					</tr>
					<tr class="tbtitle">
						<td height="35" class="ball_b">09</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,9)"
							type="checkbox" value="09" name="num9">
						</td>
						<td class="ball_r">19</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,19)"
							type="checkbox" value="19" name="num19">
						</td>
						<td class="ball_r">29</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,29)"
							type="checkbox" value="29" name="num29">
						</td>
						<td class="ball_g">39</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,39)"
							type="checkbox" value="39" name="num39">
						</td>
						<td class="ball_g">49</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,49)"
							type="checkbox" value="49" name="num49">
						</td>
					</tr>
					<tr class="tbtitle">
						<td height="35" class="ball_b">10</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,10)"
							type="checkbox" value="10" name="num10">
						</td>
						<td class="ball_b">20</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,20)"
							type="checkbox" value="20" name="num20">
						</td>
						<td class="ball_r">30</td>
						<td class="ball_ff"><input onclick="SubChkBox(this,30)"
							type="checkbox" value="30" name="num30">
						</td>
						<td class="ball_r">40</td>
						<td align="middle" class="ball_bg"><input
							onclick="SubChkBox(this,40)" type="checkbox" value="40"
							name="num40">
						</td>
						<td colspan="2" align="middle" class="tbtitle"><input
							type="submit" value="确定" class="button_a" name="btnSubmit">
							<input type="button"
							onclick="document.order_form.reset();select_types(1)"
							class="button_a" name="Submit3" value="重设">
						</td>
					</tr>
					<tr class="tbtitle">
						<td width="76%" class="ball_bg2" colspan="8">
							<input  checked="checked" name="pabc1" id="pabc1" type="radio" onclick="select_types1(1);ra_select('pabc','1')" value="1">
							复式 <input  name="pabc2" id="pabc2" type="radio" onclick="select_types1(2);ra_select('pabc','2')" value="2">
							胆拖
							<!--
							 <input name="pabc3" id="pabc3" disabled="disabled" type="radio" onclick="select_types1(3);ra_select('pabc','3')" value="3">
							生肖对碰 <input name="pabc4" id="pabc4" disabled="disabled" type="radio" onclick="select_types1(4);ra_select('pabc','4')" value="4">
							尾数对碰
							 -->
							<input name="pabc" type="hidden" id="pabc" value="1">
						</td>
						<td width="9%" nowrap="" class="ball_bg2" id="hd1" colspan="2">
							胆1<input name="dm1" type="text" readonly="" class="input1" id="dm1" size="4">
							胆2 <input name="dm2" type="text" readonly="" class="input1" id="dm2" size="4">
						</td>
					</tr>
				</tbody>
			</table>
			<div id="a3" style="display: none;">
				<input name="pan1" id="pan1" type="hidden" value="" disabled="disabled">
				<input name="pan2" id="pan2" type="hidden" value="" disabled="disabled">
				<table border="0" cellpadding="2" cellspacing="1">
					<tbody>
						<tr class="tbtitle">
							<td bgColor="#ffffff">

								鼠<input onclick="r_pan1(1);ra_select('pan1','1')" name="pan11"value="<%=map.get("鼠")%>" type="radio">
							</td>
							<td bgColor="#ffffff">虎
								 <input onclick="r_pan1(2);ra_select('pan1','2')" name="pan12"value="<%=map.get("虎")%>" type="radio">
							</td>
							<td bgColor="#ffffff">龙
								 <input onclick="r_pan1(3);ra_select('pan1','3')" name="pan13"value="<%=map.get("龙")%>" type="radio">
							</td>
							<td bgColor="#ffffff">马
								 <input onclick="r_pan1(4);ra_select('pan1','4')" name="pan14"value="<%=map.get("马")%>" type="radio">
							</td>
							<td bgColor="#ffffff">猴
								 <input onclick="r_pan1(5);ra_select('pan1','5')" name="pan15"value="<%=map.get("猴")%>" type="radio">
							</td>
							<td bgColor="#ffffff">狗
								<input  onclick="r_pan1(6);ra_select('pan1','6')" name="pan16"value="<%=map.get("狗")%>" type="radio">
							</td>
							<td bgColor="#ffffff">牛
								<input onclick="r_pan1(7);ra_select('pan1','7')" name="pan17"value="<%=map.get("牛")%>" type="radio">
							</td>
							<td bgColor="#ffffff">兔
								<input onclick="r_pan1(8);ra_select('pan1','8')" name="pan18"value="<%=map.get("兔")%>" type="radio">
							</td>
							<td bgColor="#ffffff">蛇
								<input onclick="r_pan1(9);ra_select('pan1','9')" name="pan19"value="<%=map.get("蛇")%>" type="radio">
							</td>
							<td bgColor="#ffffff">羊
								<input onclick="r_pan1(10);ra_select('pan1','10')" name="pan110"value="<%=map.get("羊")%>" type="radio">
							</td>
							<td bgColor="#ffffff">鸡
								 <input onclick="r_pan1(11);ra_select('pan1','11')" name="pan111"value="<%=map.get("鸡")%>" type="radio">
							</td>
							<td bgColor="#ffffff">猪
								 <input onclick="r_pan1(12);ra_select('pan1','12')" name="pan112"value="<%=map.get("猪")%>" type="radio">
							</td>

						</tr>
						<tr class="tbtitle">
							<td bgColor="#ffff99">鼠
								<input	onclick="r_pan2(1);ra_select('pan2','1')" name="pan21"value="<%=map.get("鼠")%>" type="radio">
							</td>
							<td bgColor="#ffff99">虎
								<input	onclick="r_pan2(2);ra_select('pan2','2')" name="pan22"value="<%=map.get("虎")%>" type="radio">
							</td>
							<td bgColor="#ffff99">龙
								 <input onclick="r_pan2(3);ra_select('pan2','3')" name="pan23"value="<%=map.get("龙")%>" type="radio">
							</td>
							<td bgColor="#ffff99">马
								<input	onclick="r_pan2(4);ra_select('pan2','4')" name="pan24" value="<%=map.get("马")%>" type="radio">
							</td>
							<td bgColor="#ffff99">猴
								<input onclick="r_pan2(5);ra_select('pan2','5')" name="pan25"	value="<%=map.get("猴")%>" type="radio">
							</td>
							<td bgColor="#ffff99">狗
								<input onclick="r_pan2(6);ra_select('pan2','6')" name="pan26"value="<%=map.get("狗")%>" type="radio">
							</td>
							<td bgColor="#ffff99">牛
								 <input	onclick="r_pan2(7);ra_select('pan2','7')" name="pan27"value="<%=map.get("牛")%>" type="radio">
							</td>
							<td bgColor="#ffff99">兔
								<input	onclick="r_pan2(8);ra_select('pan2','8')" name="pan28"	value="<%=map.get("兔")%>" type="radio">
							</td>
							<td bgColor="#ffff99">蛇
								<input	onclick="r_pan2(9);ra_select('pan2','9')" name="pan29"value="<%=map.get("蛇")%>" type="radio">
							</td>
							<td bgColor="#ffff99">羊
								 <input onclick="r_pan2(10);ra_select('pan2','10')" name="pan210"value="<%=map.get("羊")%>" type="radio">
							</td>
							<td bgColor="#ffff99">鸡
								 <input onclick="r_pan2(11);ra_select('pan2','11')" name="pan211"	value="<%=map.get("鸡")%>" type="radio">
							</td>
							<td bgColor="#ffff99">猪
								 <input	onclick="r_pan2(12);ra_select('pan2','12')" name="pan212"value="<%=map.get("猪")%>" type="radio">
							</td>

						</tr>
					</tbody>
				</table>
			</div>
			<div id="a4" style="display: none;">
				<table border="0" cellpadding="2" cellspacing="1">
					<tbody>
						<tr class="tbtitle">
							<td bgcolor="#ffffff">
								<input name="pan3" id="pan3" type="hidden" value="" >
								0尾 <input name="pan30" type="radio"
									onclick="r_pan3(0);ra_select('pan3','0')" value="10,20,30,40"
									disabled="disabled">
							</td>
							<td bgcolor="#ffffff">1尾 <input name="pan31" type="radio"
								onclick="r_pan3(1);ra_select('pan3','1')" value="01,11,21,31,41"
								disabled="disabled">
							</td>
							<td bgcolor="#ffffff">2尾 <input name="pan32" type="radio"
								onclick="r_pan3(2);ra_select('pan3','2')" value="02,12,22,32,42"
								disabled="disabled">
							</td>
							<td bgcolor="#ffffff">3尾 <input name="pan33" type="radio"
								onclick="r_pan3(3);ra_select('pan3','3')" value="03,13,23,33,43"
								disabled="disabled">
							</td>
							<td bgcolor="#ffffff">4尾 <input name="pan34" type="radio"
								onclick="r_pan3(4);ra_select('pan3','4')" value="04,14,24,34,44"
								disabled="disabled">
							</td>
							<td bgcolor="#ffffff">5尾 <input name="pan35" type="radio"
								onclick="r_pan3(5);ra_select('pan3','5')" value="05,15,25,35,45"
								disabled="disabled">
							</td>
							<td bgcolor="#ffffff">6尾 <input name="pan36" type="radio"
								onclick="r_pan3(6);ra_select('pan3','6')" value="06,16,26,36,46"
								disabled="disabled">
							</td>
							<td bgcolor="#ffffff">7尾 <input name="pan37" type="radio"
								onclick="r_pan3(7);ra_select('pan3','7')" value="07,17,27,37,47"
								disabled="disabled">
							</td>
							<td bgcolor="#ffffff">8尾 <input name="pan38" type="radio"
								onclick="r_pan3(8);ra_select('pan3','8')" value="08,18,28,38,48"
								disabled="disabled">
							</td>
							<td bgcolor="#ffffff">9尾 <input name="pan39" type="radio"
								onclick="r_pan3(9);ra_select('pan3','9')" value="09,19,29,39,49"
								disabled="disabled">
							</td>
						</tr>
						<tr align="middle">
							<td bgcolor="#FFFF99"><input name="pan4" id="pan4"
								type="hidden" value="" disabled="disabled"> 0尾<input
								name="pan40" type="radio"
								onclick="r_pan4(0);ra_select('pan4','0')" value="10,20,30,40"
								disabled="disabled">
							</td>
							<td bgcolor="#FFFF99">1尾 <input name="pan41" type="radio"
								onclick="r_pan4(1);ra_select('pan4','1')" value="01,11,21,31,41"
								disabled="disabled">
							</td>
							<td bgcolor="#FFFF99">2尾 <input name="pan42" type="radio"
								onclick="r_pan4(2);ra_select('pan4','2')" value="02,12,22,32,42"
								disabled="disabled">
							</td>
							<td bgcolor="#FFFF99">3尾 <input name="pan43" type="radio"
								onclick="r_pan4(3);ra_select('pan4','3')" value="03,13,23,33,43"
								disabled="disabled">
							</td>
							<td bgcolor="#FFFF99">4尾 <input name="pan44" type="radio"
								onclick="r_pan4(4);ra_select('pan4','4')" value="04,14,24,34,44"
								disabled="disabled">
							</td>
							<td bgcolor="#FFFF99">5尾 <input name="pan45" type="radio"
								onclick="r_pan4(5);ra_select('pan4','5')" value="05,15,25,35,45"
								disabled="disabled">
							</td>
							<td bgcolor="#FFFF99">6尾 <input name="pan46" type="radio"
								onclick="r_pan4(6);ra_select('pan4','6')" value="06,16,26,36,46"
								disabled="disabled">
							</td>
							<td bgcolor="#FFFF99">7尾 <input name="pan47" type="radio"
								onclick="r_pan4(7);ra_select('pan4','7')" value="07,17,27,37,47"
								disabled="disabled">
							</td>
							<td bgcolor="#FFFF99">8尾 <input name="pan48" type="radio"
								onclick="r_pan4(8);ra_select('pan4','8')" value="08,18,28,38,48"
								disabled="disabled">
							</td>
							<td bgcolor="#FFFF99">9尾 <input name="pan49" type="radio"
								onclick="r_pan4(9);ra_select('pan4','9')" value="09,19,29,39,49"
								disabled="disabled">
							</td>
						</tr>
					</tbody>
				</table>



			</div>
		</form>
	</div>

	<script type="text/javascript">
var type_nums = 10;
var type_min = 3;
var cb_num = 1;

$(function(){
   $("#rrtype").val(1);
   type_nums = 10;
   type_min = 3;
   cb_num = 1;
   $("#dm1").val("");
   $("#dm2").val("");

});


function select_types(type,i) {
	cb_num=1
	$("#rrtype").val(1);
	$("#dm1").val("");
	$("#dm2").val("");

	if (type == 1 || type == 2) {
		type_nums = 10;
		type_min = 3;
		$("input[name^='pan1']").attr("disabled","disabled").attr("checked",false);
		$("input[name^='pan2']").attr("disabled","disabled").attr("checked",false);
		$("input[name^='pan3']").attr("disabled","disabled").attr("checked",false);
		$("input[name^='pan4']").attr("disabled","disabled").attr("checked",false);
		$("input[name='pabc3']").attr("disabled","disabled");
		$("input[name='pabc4']").attr("disabled","disabled");
		$("input[name^='num']").attr("disabled",false).attr("checked",false);

		a2.style.display = "";
		a3.style.display = "none";
		a4.style.display = "none";

	 	for(i=1; i<5; i++) {
			if (i==1) {
				$("#pabc").val(1);
				$("input[name='pabc"+i+"']").attr("checked",true);
			}else{
				$("input[name='pabc"+i+"']").attr("checked",false);
			}
		}
	}
	else{
		cb_num=1
		$("#rrtype").val(2);
		$("#dm1").val("");
		$("#dm2").val("");
		type_nums = 10;
		type_min = 2;

		//a2.style.display = "";
		//a3.style.display = "";
		//a4.style.display = "";

		$("input[name='pabc3']").attr("disabled",false);
		$("input[name='pabc4']").attr("disabled",false);
		$("input[name^='num']").attr("disabled",false).attr("checked",false);

		$("input[name^='pan1']").attr("disabled","disabled").attr("checked",false);
		$("input[name^='pan2']").attr("disabled","disabled").attr("checked",false);
		$("input[name^='pan3']").attr("disabled","disabled").attr("checked",false);
		$("input[name^='pan4']").attr("disabled","disabled").attr("checked",false);

		for(i=1; i<5; i++) {
			if (i==1) {
				$("#pabc").val(1);
				$("input[name='pabc"+i+"']").attr("checked",true);
			}else{
				$("input[name='pabc"+i+"']").attr("checked",false);
			}
		}
	}
}
function select_types1(type) {
	cb_num=1;
	$("#dm1").val("");
	$("#dm2").val("");
	if(type<=2)
	{
		$("input[name^='num']").attr("disabled",false).attr("checked",false);
		$("input[name^='pan1']").attr("disabled",true).attr("checked",false);
		$("input[name^='pan2']").attr("disabled",true).attr("checked",false);
		$("input[name^='pan3']").attr("disabled",true).attr("checked",false);
		$("input[name^='pan4']").attr("disabled",true).attr("checked",false);
		a3.style.display = "none";
		a4.style.display = "none";
	}
	else
		$("input[name^='num']").attr("disabled","disabled").attr("checked",false);



	for(i=1; i<5; i++) {
		if (i!=type) {
			$("input[name='pabc"+i+"']").attr("checked",false);
		}
	}
	if (type ==3) {
		$("input[name^='pan1']").attr("disabled",false).attr("checked",false);
		$("input[name^='pan2']").attr("disabled",false).attr("checked",false);
		a3.style.display = "";
		a4.style.display = "none";
	}
	else if (type ==4) {
		a3.style.display = "none";
		a4.style.display = "";
		$("input[name^='pan3']").attr("disabled",false).attr("checked",false);
		$("input[name^='pan4']").attr("disabled",false).attr("checked",false);
	}
	checkCount = 0;
}



function r_pan1(zizi,zzz){
	for(i=0; i<10; i++) {
		if (i!=zizi) {
			$("input[name='pan1"+i+"']").attr("checked",false);
		}
	}
	if($("input[name='pan2']").val()==zizi)
	{
		$("input[name='pan1"+zizi+"']")	.attr("checked",false);
		$("input[name='pan1']").val("");
		alert("对不起!请重新选择两个不一样的！");
		return false;
	}
}


function r_pan2(zizi,zzz){
	for(i=0; i<10; i++) {
		if (i!=zizi) {
			$("input[name='pan2"+i+"']").attr("checked",false);
		}
	}
	if($("input[name='pan1']").val()==zizi)
	{
		$("input[name='pan1"+zizi+"']")	.attr("checked",false);
		$("input[name='pan1']").val("");
		alert("对不起!请重新选择两个不一样的！");
		return false;
	}
}

function r_pan3(zizi,zzz){
	for(i=0; i<10; i++) {
		if (i!=zizi) {
			$("input[name='pan3"+i+"']").attr("checked",false);
		}
	}
	if($("input[name='pan4']").val()!="" && $("input[name='pan4']").val()==zizi)
	{
		$("input[name='pan3"+zizi+"']")	.attr("checked",false);
		$("input[name='pan3']").val("");
		alert("对不起!请重新选择两个不一样的！");
		return false;
	}
}

function r_pan4(zizi,zzz){
	for(i=0; i<10; i++) {
		if (i!=zizi) {
			$("input[name='pan4"+i+"']").attr("checked",false);
		}
	}
	if($("input[name='pan3']").val()!="" &&  $("input[name='pan3']").val()==zizi)
	{
		$("input[name='pan4"+zizi+"']")	.attr("checked",false);
		$("input[name='pan4']").val("");
		alert("对不起!请重新选择两个不一样的！");
		return false;
	}
}

function ra_select(str1,zz){
	$("input[name='"+str1+"']").val(zz);
}


function SubChkBox(obj,bmbm) {

	if(obj.checked == false)
	{
		cb_num--;
	}
	if(obj.checked == true)
	{
		if ( cb_num > type_nums )
		{
			alert("最多只能选择"+type_nums+"个号码");
			cb_num--;
			obj.checked = false;
		}
		cb_num++;
	}
	if($("input[name='pabc']").val()=="2")
	{

		if($("input[name='rrtype']").val()=="1")
		{
			if($("input[name='dm1']").val()=="")
			{
				$("input[name='num"+bmbm+"']").attr("disabled",true);
				$("input[name='dm1']").val(bmbm);
				//$("input[name='dm1']").attr("disabled",false);
			}
			else
			{
				if($("input[name='dm2']").val()=="")
				{
					$("input[name='num"+bmbm+"']").attr("disabled",true);
					$("input[name='dm2']").val(bmbm);
					//$("input[name='dm2']").attr("disabled",false);
				}
			}
		}
		else
		{
			if($("input[name='dm1']").val()=="")
			{
				$("input[name='num"+bmbm+"']").attr("disabled",true);
				$("input[name='dm1']").val(bmbm);
				//$("input[name='dm1']").attr("disabled",false);
			}
		}
	}
}
function clear_select(){
	cb_num=1;
}
function SubChk(obj) {


	if ($("#pabc").val() == 3) {
		if ($("#pan1").val() =="" || $("#pan2").val() ==""){
			alert('请选择生肖');
			return false;
		}
	}
	else if ($("#pabc").val() == 4) {
		if ($("#pan3").val() =="" || $("#pan4").val() ==""){
			alert('请选择尾数');
			return false;
		}

	}

	if ($("#rrtype").val() == 1) {
		if ($("#pabc").val() == 2) {
			if ($("#dm1").val() =="" || $("#dm2").val() ==""){
				  alert('请选择胆');
				  return false;
			}

		}
	}
	else if ($("#rrtype").val() == 2) {
		if ($("#pabc").val() == 2) {
			if ($("#dm1").val() =="" ){
				  alert('请选择胆');
				  return false;
			}
		}
	}

	var checkCount = 0;
	var checknum = obj.elements.length;
	var rtypechk = 0;

	var rrtype = $("#rrtype").val();
	for(i=0; i<obj.rtype.length; i++) {
		if (obj.rtype[i].checked) {
			rtypechk ++;
		}
	}

	if (rtypechk == 0) {
	   alert('请选择类别');
	   return false;
	}


	for(i=0; i<checknum; i++) {
		if (obj.elements[i].checked) {
			if(obj.elements[i].name.indexOf('num') >= 0){ checkCount ++;}
		}
	}

 	for(i=0; i<obj.rtype.length; i++) {
		var va = obj.rtype[i].value;
		if (obj.rtype[i].checked) {
			$("#rrtype").val(va);
			$("#rtype_"+va).attr("checked","checked");
		}else{
			$("#rtype_"+va).removeAttr("checked");
		}
	}

	if (checkCount > (type_nums)) {
		alert("最多选择"+type_nums+"个号码");
		return false;
	}
	if(checkCount < (type_min)){
		alert("最少需要选择"+type_min+"个号码");
		return false;
	}else{
		parent.k_meml.showOrder();
		var pabc = $("#pabc").val();
		if(pabc=='1'){
			$("#pabc1").attr("checked","checked");
			$("#pabc2").removeAttr("checked");
		}else{
			$("#pabc1").removeAttr("checked");
			$("#pabc2").attr("checked","checked");
		}


		return true;
	}

}
</script>
	<script type="text/javascript">
		var cp_gameTypeCode_temp="${results.gameTypeCode}";
		var cp_typeCode_temp="LM";
		var cp_cateCode_temp="";
		var cp_xzlxCode_temp="";

	</script>
	<%@include file="/commons/cp/jsp/cp_fast_set_footer.jsp" %>

</body>
</html>