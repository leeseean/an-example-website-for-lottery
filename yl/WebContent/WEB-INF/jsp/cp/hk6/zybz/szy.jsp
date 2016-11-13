<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html style="overflow-x:hidden">
<head>
<%@include file="/commons/cp/jsp/cp_common.jsp"%>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>webcom</title>
</head>
<body>


	<div class="wrapCss_004">
		<table class="tab_001" border="0" cellSpacing="0" cellPadding="0" width="100%">
			<tbody>
				<tr style='background: url("${resourceRoot}/cp/images/title_bg_004.gif") repeat-x left top;'>
					<td><span class="font_sty001"><strong>4中一下注</strong> </span></td>
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
					<tr class="hsetEm">
						<td class="tbtitle" colSpan="15"></td>
					</tr>

					<tr class="hset">
						<td class="tbtitle4" colSpan="15" align="left">
							类型： <input id="glx" onclick="document.location='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=ZYBZ&cpZuTypeCode=WBZ'" name="glx" value="5"  type="radio">五不中&nbsp;
							<input  id="glx" onclick="document.location='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=ZYBZ&cpZuTypeCode=QBZ'" name="glx" value="7" type="radio" >七不中&nbsp;
							<input id="glx" onclick="document.location='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=ZYBZ&cpZuTypeCode=JBZ'" name="glx" value="9" type="radio" >九不中&nbsp;
							<input checked="checked" id="glx" onclick="document.location='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=ZYBZ&cpZuTypeCode=SZY'" name="glx" value="4" type="radio" > 4中一&nbsp;
							<input id="glx" onclick="document.location='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=ZYBZ&cpZuTypeCode=LZY'" name="glx" value="6" type="radio"> 6中一&nbsp;

						</td>
					</tr>
				<form name="order_form" id="order_form" action="${ctx}/order/goMultiGroupOrderList?code=HK6&type=ZYBZ&cate=SZY&multilen=4"
					method="post" target="bet_order_frame"    onsubmit="return SubChk(this);">
					<tr class="hset">
						<td class="tbtitle" colSpan="15" align="left">号码勾选说明: 5不中(5~10) 7不中(7~10) 9不中(9~12) 4中一(4~10) 6中一(6~10)</td>
					</tr>
					<tr class="tbtitle2">
						<td width="30">号码</td>
						<td>勾选</td>
						<td>赔率</td>
						<td width="30">号码</td>
						<td>勾选</td>
						<td>赔率</td>
						<td width="30">号码</td>
						<td>勾选</td>
						<td>赔率</td>
						<td width="30">号码</td>
						<td>勾选</td>
						<td>赔率</td>
						<td width="30">号码</td>
						<td>勾选</td>
						<td>赔率</td>
					</tr>
					<tr class="tbtitle">
						<td class="ball_r">01</td>
						<td class="ball_ff"><input id="Num_765" onclick="return SubChkBox(this,1)" name="Num_765" value="01" type="checkbox"></td>
						<td id="bl_765" class="ball_ff">2.2</td>
						<td class="ball_g">11</td>
						<td class="ball_ff"><input id="Num_766" onclick="return SubChkBox(this,11)" name="Num_766" value="11" type="checkbox">
						</td>
						<td id="bl_766" class="ball_ff">2.2</td>
						<td class="ball_g">21</td>
						<td class="ball_ff"><input id="Num_767" onclick="return SubChkBox(this,21)" name="Num_767" value="21" type="checkbox">
						</td>
						<td id="bl_767" class="ball_ff">2.2</td>
						<td class="ball_b">31</td>
						<td class="ball_ff"><input id="Num_768" onclick="return SubChkBox(this,31)" name="Num_768" value="31" type="checkbox">
						</td>
						<td id="bl_768" class="ball_ff">2.2</td>
						<td class="ball_b">41</td>
						<td class="ball_ff"><input id="Num_769" onclick="return SubChkBox(this,41)" name="Num_769" value="41" type="checkbox">
						</td>
						<td id="bl_769" class="ball_ff">2.2</td>
					</tr>
					<tr class="tbtitle">
						<td class="ball_r">02</td>
						<td class="ball_ff"><input id="Num_770" onclick="return SubChkBox(this,2)" name="Num_770" value="02" type="checkbox">
						</td>
						<td id="bl_770" class="ball_ff">2.2</td>
						<td class="ball_r">12</td>
						<td class="ball_ff"><input id="Num_771" onclick="return SubChkBox(this,12)" name="Num_771" value="12" type="checkbox">
						</td>
						<td id="bl_771" class="ball_ff">2.2</td>
						<td class="ball_g">22</td>
						<td class="ball_ff"><input id="Num_772" onclick="return SubChkBox(this,22)" name="Num_772" value="22" type="checkbox"></td>
						<td id="bl_772" class="ball_ff">2.2</td>
						<td class="ball_g">32</td>
						<td class="ball_ff"><input id="Num_773" onclick="return SubChkBox(this,32)" name="Num_773" value="32" type="checkbox">
						</td>
						<td id="bl_773" class="ball_ff">2.2</td>
						<td class="ball_b">42</td>
						<td class="ball_ff"><input id="Num_774" onclick="return SubChkBox(this,42)" name="Num_774" value="42" type="checkbox">
						</td>
						<td id="bl_774" class="ball_ff">2.2</td>
					</tr>
					<tr class="tbtitle">
						<td class="ball_b">03</td>
						<td class="ball_ff"><input id="Num_775" onclick="return SubChkBox(this,3)" name="Num_775" value="03" type="checkbox">
						</td>
						<td id="bl_775" class="ball_ff">2.2</td>
						<td class="ball_r">13</td>
						<td class="ball_ff"><input id="Num_776" onclick="return SubChkBox(this,13)" name="Num_776" value="13" type="checkbox"></td>
						<td id="bl_776" class="ball_ff">2.2</td>
						<td class="ball_r">23</td>
						<td class="ball_ff"><input id="Num_777" onclick="return SubChkBox(this,23)" name="Num_777" value="23" type="checkbox">
						</td>
						<td id="bl_777" class="ball_ff">2.2</td>
						<td class="ball_g">33</td>
						<td class="ball_ff"><input id="Num_778" onclick="return SubChkBox(this,33)" name="Num_778" value="33" type="checkbox">
						</td>
						<td id="bl_778" class="ball_ff">2.2</td>
						<td class="ball_g">43</td>
						<td class="ball_ff"><input id="Num_779" onclick="return SubChkBox(this,43)" name="Num_779" value="43" type="checkbox"></td>
						<td id="bl_779" class="ball_ff">2.2</td>
					</tr>
					<tr class="tbtitle">
						<td class="ball_b">04</td>
						<td class="ball_ff"><input id="Num_780" onclick="return SubChkBox(this,4)" name="Num_780" value="04" type="checkbox"></td>
						<td id="bl_780" class="ball_ff">2.2</td>
						<td class="ball_b">14</td>
						<td class="ball_ff"><input id="Num_781" onclick="return SubChkBox(this,14)" name="Num_781" value="14" type="checkbox">
						</td>
						<td id="bl_781" class="ball_ff">2.2</td>
						<td class="ball_r">24</td>
						<td class="ball_ff"><input id="Num_782" onclick="return SubChkBox(this,24)" name="Num_782" value="24" type="checkbox">
						</td>
						<td id="bl_782" class="ball_ff">2.2</td>
						<td class="ball_r">34</td>
						<td class="ball_ff"><input id="Num_783" onclick="return SubChkBox(this,34)" name="Num_783" value="34" type="checkbox"></td>
						<td id="bl_783" class="ball_ff">2.2</td>
						<td class="ball_g">44</td>
						<td class="ball_ff"><input id="Num_784" onclick="return SubChkBox(this,44)" name="Num_784" value="44" type="checkbox">
						</td>
						<td id="bl_784" class="ball_ff">2.2</td>
					</tr>
					<tr class="tbtitle">
						<td class="ball_g">05</td>
						<td class="ball_ff"><input id="Num_785" onclick="return SubChkBox(this,5)" name="Num_785" value="05" type="checkbox">
						</td>
						<td id="bl_785" class="ball_ff">2.2</td>
						<td class="ball_b">15</td>
						<td class="ball_ff"><input id="Num_786" onclick="return SubChkBox(this,15)" name="Num_786" value="15" type="checkbox"></td>
						<td id="bl_786" class="ball_ff">2.2</td>
						<td class="ball_b">25</td>
						<td class="ball_ff"><input id="Num_787" onclick="return SubChkBox(this,25)" name="Num_787" value="25" type="checkbox">
						</td>
						<td id="bl_787" class="ball_ff">2.2</td>
						<td class="ball_r">35</td>
						<td class="ball_ff"><input id="Num_788" onclick="return SubChkBox(this,35)" name="Num_788" value="35" type="checkbox"></td>
						<td id="bl_788" class="ball_ff">2.2</td>
						<td class="ball_r">45</td>
						<td class="ball_ff"><input id="Num_789" onclick="return SubChkBox(this,45)" name="Num_789" value="45" type="checkbox">
						</td>
						<td id="bl_789" class="ball_ff">2.2</td>
					</tr>
					<tr class="tbtitle">
						<td class="ball_g">06</td>
						<td class="ball_ff"><input id="Num_790" onclick="return SubChkBox(this,6)" name="Num_790" value="06" type="checkbox"></td>
						<td id="bl_790" class="ball_ff">2.2</td>
						<td class="ball_g">16</td>
						<td class="ball_ff"><input id="Num_791" onclick="return SubChkBox(this,16)" name="Num_791" value="16" type="checkbox">
						</td>
						<td id="bl_791" class="ball_ff">2.2</td>
						<td class="ball_b">26</td>
						<td class="ball_ff"><input id="Num_792" onclick="return SubChkBox(this,26)" name="Num_792" value="26" type="checkbox">
						</td>
						<td id="bl_792" class="ball_ff">2.2</td>
						<td class="ball_b">36</td>
						<td class="ball_ff"><input id="Num_793" onclick="return SubChkBox(this,36)" name="Num_793" value="36" type="checkbox"></td>
						<td id="bl_793" class="ball_ff">2.2</td>
						<td class="ball_r">46</td>
						<td class="ball_ff"><input id="Num_794" onclick="return SubChkBox(this,46)" name="Num_794" value="46" type="checkbox"></td>
						<td id="bl_794" class="ball_ff">2.2</td>
					</tr>
					<tr class="tbtitle">
						<td class="ball_r">07</td>
						<td class="ball_ff"><input id="Num_795" onclick="return SubChkBox(this,7)" name="Num_795" value="07" type="checkbox">
						</td>
						<td id="bl_795" class="ball_ff">2.2</td>
						<td class="ball_g">17</td>
						<td class="ball_ff"><input id="Num_796" onclick="return SubChkBox(this,17)" name="Num_796" value="17" type="checkbox">
						</td>
						<td id="bl_796" class="ball_ff">2.2</td>
						<td class="ball_g">27</td>
						<td class="ball_ff"><input id="Num_797" onclick="return SubChkBox(this,27)" name="Num_797" value="27" type="checkbox">
						</td>
						<td id="bl_797" class="ball_ff">2.2</td>
						<td class="ball_b">37</td>
						<td class="ball_ff"><input id="Num_798" onclick="return SubChkBox(this,37)" name="Num_798" value="37" type="checkbox"></td>
						<td id="bl_798" class="ball_ff">2.2</td>
						<td class="ball_b">47</td>
						<td class="ball_ff"><input id="Num_799" onclick="return SubChkBox(this,47)" name="Num_799" value="47" type="checkbox"></td>
						<td id="bl_799" class="ball_ff">2.2</td>
					</tr>
					<tr class="tbtitle">
						<td class="ball_r">08</td>
						<td class="ball_ff"><input id="Num_800" onclick="return SubChkBox(this,8)" name="Num_800" value="08" type="checkbox">
						</td>
						<td id="bl_800" class="ball_ff">2.2</td>
						<td class="ball_r">18</td>
						<td class="ball_ff"><input id="Num_801" onclick="return SubChkBox(this,18)" name="Num_801" value="1" type="checkbox">
						</td>
						<td id="bl_801" class="ball_ff">2.2</td>
						<td class="ball_g">28</td>
						<td class="ball_ff"><input id="Num_802" onclick="return SubChkBox(this,28)" name="Num_802" value="28" type="checkbox"></td>
						<td id="bl_802" class="ball_ff">2.2</td>
						<td class="ball_g">38</td>
						<td class="ball_ff"><input id="Num_803" onclick="return SubChkBox(this,38)" name="Num_803" value="38" type="checkbox">
						</td>
						<td id="bl_803" class="ball_ff">2.2</td>
						<td class="ball_b">48</td>
						<td class="ball_ff"><input id="Num_804" onclick="return SubChkBox(this,48)" name="Num_804" value="48" type="checkbox">
						</td>
						<td id="bl_804" class="ball_ff">2.2</td>
					</tr>
					<tr class="tbtitle">
						<td class="ball_b">09</td>
						<td class="ball_ff"><input id="Num_805" onclick="return SubChkBox(this,9)" name="Num_805" value="09" type="checkbox"></td>
						<td id="bl_805" class="ball_ff">2.2</td>
						<td class="ball_r">19</td>
						<td class="ball_ff"><input id="Num_806" onclick="return SubChkBox(this,19)" name="Num_806" value="19" type="checkbox"></td>
						<td id="bl_806" class="ball_ff">2.2</td>
						<td class="ball_r">29</td>
						<td class="ball_ff"><input id="Num_807" onclick="return SubChkBox(this,29)" name="Num_807" value="29" type="checkbox">
						</td>
						<td id="bl_807" class="ball_ff">2.2</td>
						<td class="ball_g">39</td>
						<td class="ball_ff"><input id="Num_808" onclick="return SubChkBox(this,39)" name="Num_808" value="39" type="checkbox"></td>
						<td id="bl_808" class="ball_ff">2.2</td>
						<td class="ball_g">49</td>
						<td class="ball_ff"><input id="Num_809" onclick="return SubChkBox(this,49)" name="Num_809" value="49" type="checkbox"></td>
						<td id="bl_809" class="ball_ff">2.2</td>
					</tr>
					<tr class="tbtitle">
						<td class="ball_b">10</td>
						<td class="ball_ff"><input id="Num_810" onclick="return SubChkBox(this,10)" name="Num_810" value="10" type="checkbox"></td>
						<td id="bl_810" class="ball_ff">2.2</td>
						<td class="ball_b">20</td>
						<td class="ball_ff"><input id="Num_811" onclick="return SubChkBox(this,20)" name="Num_811" value="20" type="checkbox"></td>
						<td id="bl_811" class="ball_ff">2.2</td>
						<td class="ball_r">30</td>
						<td class="ball_ff"><input id="Num_812" onclick="return SubChkBox(this,30)" name="Num_812" value="30" type="checkbox"></td>
						<td id="bl_812" class="ball_ff">2.2</td>
						<td class="ball_r">40</td>
						<td class="ball_ff"><input id="Num_813" onclick="return SubChkBox(this,40)" name="Num_813" value="40" type="checkbox">
						</td>
						<td id="bl_813" class="ball_ff">2.2</td>
						<td colSpan="3"></td>
					</tr>
					<tr class="hset2">
						<td class="tbtitle" colSpan="15">
							<input id="btnSubmit" class="button_a" name="btnSubmit"   value="下注" type="submit">&nbsp;&nbsp;
							<input name="Submit3" value="重设" type="button" onclick="restDate();" class="button_a"   >
						</td>
					</tr>
				</form>
				</tbody>

			</table>

	</div>
	<script type="text/javascript">
			var sel = ",";
		function restDate(){
			 sel = ",";
			document.order_form.reset();
		}

		function SubChk(obj) {
			var a = sel.split(",");
			var len = a.length - 2;
			var len2 = $("input:radio[name=glx]:checked").val();

			switch (len2) {
			case "3":

				if (len<3 || len>10) {
					alert("请选择3~10个号码！");
					return false;
				}
				break;
			case "4":
				if (len<4 || len>10) {
					alert("请选择4~10个号码！");
					return false;
				}
				break;
			case "5":
				if (len == 0) {
					alert("请先选择最少5个号码！");
					return false;
				}
				if (len<5 || len>10) {
					alert("请选择5~10个号码！");
					return false;
				}
				break;
			case "6":
				if (len == 0) {
					alert("请先选择最少6个号码！");
					return false;
				}
				if (len<6 || len>10) {
					alert("请选择6~10个号码！");
					return false;
				}
				break;
			case "7":
				if (len == 0) {
					alert("请先选择最少7个号码！");
					return false;
				}
				if (len<7 || len>10) {
					alert("请选择7~10个号码！");
					return false;
				}
				break;
			case "8":
				if (len == 0) {
					alert("请先选择最少8个号码！");
					return false;
				}
				if (len<8 || len>11) {
					alert("请选择8~11个号码！");
					return false;
				}
				break;
			case "9":
				if (len == 0) {
					alert("请先选择最少6个号码！");
					return false;
				}
				if (len<9 || len>12) {
					alert("请选择9~12个号码！");
					return false;
				}
				break;
			case "10":
				if (len<10 || len>13) {
					alert("请选择10~13个号码！");
					return false;
				}
				break;
			case "11":
				if (len<11 || len>13) {
					alert("请选择11~13个号码！");
					return false;
				}
				break;
			case "12":
				if (len<12 || len>14) {
					alert("请选择12~14个号码！");
					return false;
				}
				break;
			}
			parent.k_meml.showOrder();
			return true;
		}

		function SubChkBox(obj, bmbm) {
			if (obj.checked) {
				var a = sel.split(",");
				var len = a.length - 1;
				var len2 = $("input:radio[name=glx]:checked").val();
				switch (len2) {
				case "3":
					if (len > 10) {
						alert("请选择3~10个号码！");
						return false;
					}
					break;
				case "4":
					if (len > 10) {
						alert("请选择4~10个号码！");
						return false;
					}
					break;
				case "5":
					if (len > 10) {
						alert("请选择5~10个号码！");
						return false;
					}
					break;
				case "6":
					if (len > 10) {
						alert("请选择6~10个号码！");
						return false;
					}
					break;
				case "7":
					if (len > 10) {
						alert("请选择7~10个号码！");
						return false;
					}
					break;
				case "8":
					if (len > 11) {
						alert("请选择8~11个号码！");
						return false;
					}
					break;
				case "9":
					if (len > 12) {
						alert("请选择9~12个号码！");
						return false;
					}
					break;
				case "10":
					if (len > 13) {
						alert("请选择10~13个号码！");
						return false;
					}
					break;
				case "11":
					if (len > 13) {
						alert("请选择11~13个号码！");
						return false;
					}
					break;
				case "12":
					if (len > 14) {
						alert("请选择12~14个号码！");
						return false;
					}
					break;
				}
				sel += obj.value + ",";
			} else
				sel = sel.replace("," + obj.value + ",", ",");
		}
	</script>
	<script type="text/javascript">
		var cp_gameTypeCode_temp="${results.gameTypeCode}";
		var cp_typeCode_temp="ZYBZ";
		var cp_cateCode_temp="SZY";
		var cp_xzlxCode_temp="";

	</script>
	<%@include file="/commons/cp/jsp/cp_fast_set_footer.jsp" %>

</body>
</html>