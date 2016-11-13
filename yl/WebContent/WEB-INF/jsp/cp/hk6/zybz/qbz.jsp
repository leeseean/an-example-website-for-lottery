<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html style="overflow-x:hidden">
<head>
<%@include file="/commons/cp/jsp/cp_common.jsp" %>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>webcom</title>
</head>
<body >



	<div class="wrapCss_004">
		<table class="tab_001" border="0" cellSpacing="0" cellPadding="0"
			width="100%">
			<tbody>
				<tr style='background: url("${resourceRoot}/cp/images/title_bg_004.gif") repeat-x left top;'>
					<td><span class="font_sty001"><strong>七不中下注</strong></span></td>
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
							<input checked="checked" id="glx" onclick="document.location='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=ZYBZ&cpZuTypeCode=QBZ'" name="glx" value="7" type="radio" >七不中&nbsp;
							<input id="glx" onclick="document.location='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=ZYBZ&cpZuTypeCode=JBZ'" name="glx" value="9" type="radio" >九不中&nbsp;
							<input id="glx" onclick="document.location='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=ZYBZ&cpZuTypeCode=SZY'" name="glx" value="4" type="radio" > 4中一&nbsp;
							<input id="glx" onclick="document.location='${ctx}/cp/goList?gameTypeCode=HK6&cpTypeCode=ZYBZ&cpZuTypeCode=LZY'" name="glx" value="6" type="radio"> 6中一&nbsp;

						</td>
					</tr>
					<form name="order_form" id="order_form" action="${ctx}/order/goMultiGroupOrderList?code=HK6&type=ZYBZ&cate=QBZ&multilen=7"
					method="post" target="bet_order_frame"    onsubmit="return SubChk(this);">
					<tr class="hset">
						<td class="tbtitle" colSpan="15" align="left">号码勾选说明:
							5不中(5~10) 7不中(7~10) 9不中(9~12) 4中一(4~10) 6中一(6~10)</td>
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
						<td class="ball_ff">
							<input id="Num_667"onclick="return SubChkBox(this,1)" name="Num_667" value="01"type="checkbox">
						</td>
						<td id="bl_667" class="ball_ff">3</td>

						<td class="ball_g">11</td>
						<td class="ball_ff">
							<input id="Num_668"onclick="return SubChkBox(this,11)" name="Num_668" value="11"type="checkbox">
						</td>
						<td id="bl_668" class="ball_ff">3</td>

						<td class="ball_g">21</td>
						<td class="ball_ff">
							<input id="Num_669"onclick="return SubChkBox(this,21)" name="Num_669" value="21"type="checkbox">
						</td>
						<td id="bl_669" class="ball_ff">3</td>

						<td class="ball_b">31</td>
						<td class="ball_ff">
							<input id="Num_670"onclick="return SubChkBox(this,31)" name="Num_670" value="31"type="checkbox">
						</td>
						<td id="bl_670" class="ball_ff">3</td>


						<td class="ball_b">41</td>
						<td class="ball_ff">
							<input id="Num_671"onclick="return SubChkBox(this,41)" name="Num_671" value="41"type="checkbox">
						</td>
						<td id="bl_671" class="ball_ff">3</td>
					</tr>
					<tr class="tbtitle">
						<td class="ball_r">02</td>
						<td class="ball_ff">
					    	<input id="Num_672"onclick="return SubChkBox(this,2)" name="Num_672" value="02"type="checkbox">

					    </td>
						<td id="bl_672" class="ball_ff">3</td>
						<td class="ball_r">12</td>
						<td class="ball_ff">
							<input id="Num_673"onclick="return SubChkBox(this,12)" name="Num_673" value="12"type="checkbox"></td>
						<td id="bl_673" class="ball_ff">3</td>
						<td class="ball_g">22</td>
						<td class="ball_ff">
							<input id="Num_674"onclick="return SubChkBox(this,22)" name="Num_674" value="22"type="checkbox">	</td>
						<td id="bl_674" class="ball_ff">3</td>
						<td class="ball_g">32</td>
						<td class="ball_ff">
							<input id="Num_675"onclick="return SubChkBox(this,32)" name="Num_675" value="32"type="checkbox"></td>
						<td id="bl_675" class="ball_ff">3</td>
						<td class="ball_b">42</td>
						<td class="ball_ff">
					  		<input id="Num_676"onclick="return SubChkBox(this,42)" name="Num_676" value="42"	type="checkbox"></td>
						<td id="bl_676" class="ball_ff">3</td>
					</tr>
					<tr class="tbtitle">
						<td class="ball_b">03</td>
						<td class="ball_ff">
							<input id="Num_677"onclick="return SubChkBox(this,3)" name="Num_677" value="03"type="checkbox"></td>
						<td id="bl_677" class="ball_ff">3</td>
						<td class="ball_r">13</td>
						<td class="ball_ff">
							<input id="Num_678"onclick="return SubChkBox(this,13)" name="Num_678" value="13"	type="checkbox">	</td>
						<td id="bl_678" class="ball_ff">3</td>
						<td class="ball_r">23</td>
						<td class="ball_ff">
							<input id="Num_679"onclick="return SubChkBox(this,23)" name="Num_679" value="23"type="checkbox"></td>
						<td id="bl_679" class="ball_ff">3</td>
						<td class="ball_g">33</td>
						<td class="ball_ff">
							<input id="Num_680"onclick="return SubChkBox(this,33)" name="Num_680" value="33"type="checkbox"></td>
						<td id="bl_680" class="ball_ff">3</td>
						<td class="ball_g">43</td>
						<td class="ball_ff">
							<input id="Num_681"onclick="return SubChkBox(this,43)" name="Num_681" value="43"type="checkbox">	</td>
						<td id="bl_681" class="ball_ff">3</td>
					</tr>
					<tr class="tbtitle">
						<td class="ball_b">04</td>
						<td class="ball_ff">
							<input id="Num_682"onclick="return SubChkBox(this,4)" name="Num_682" value="04"type="checkbox">	</td>
						<td id="bl_682" class="ball_ff">3</td>
						<td class="ball_b">14</td>
						<td class="ball_ff">
							<input id="Num_683"onclick="return SubChkBox(this,14)" name="Num_683" value="14"	type="checkbox"></td>
						<td id="bl_683" class="ball_ff">3</td>
						<td class="ball_r">24</td>
						<td class="ball_ff">
							<input id="Num_684"onclick="return SubChkBox(this,24)" name="Num_684" value="24"	type="checkbox"></td>
						<td id="bl_684" class="ball_ff">3</td>
						<td class="ball_r">34</td>
						<td class="ball_ff">
							<input id="Num_685"onclick="return SubChkBox(this,34)" name="Num_685" value="34"type="checkbox">	</td>
						<td id="bl_685" class="ball_ff">3</td>
						<td class="ball_g">44</td>
						<td class="ball_ff">
							<input id="Num_686"onclick="return SubChkBox(this,44)" name="Num_686" value="44"	type="checkbox"></td>
						<td id="bl_686" class="ball_ff">3</td>
					</tr>
					<tr class="tbtitle">
						<td class="ball_g">05</td>
						<td class="ball_ff">
							<input id="Num_687"onclick="return SubChkBox(this,5)" name="Num_687" value="05"		type="checkbox"></td>
						<td id="bl_687" class="ball_ff">3</td>
						<td class="ball_b">15</td>
						<td class="ball_ff">
							<input id="Num_688"	onclick="return SubChkBox(this,15)" name="Num_688" value="15"	type="checkbox">	</td>
						<td id="bl_688" class="ball_ff">3</td>
						<td class="ball_b">25</td>
						<td class="ball_ff">
							<input id="Num_689"	onclick="return SubChkBox(this,25)" name="Num_689" value="25"	type="checkbox"></td>
						<td id="bl_689" class="ball_ff">3</td>
						<td class="ball_r">35</td>
						<td class="ball_ff">
							<input id="Num_690"	onclick="return SubChkBox(this,35)" name="Num_690" value="35"	type="checkbox">	</td>
						<td id="bl_690" class="ball_ff">3</td>
						<td class="ball_r">45</td>
						<td class="ball_ff">
							<input id="Num_691"	onclick="return SubChkBox(this,45)" name="Num_691" value="45"			type="checkbox"></td>
						<td id="bl_691" class="ball_ff">3</td>
					</tr>
					<tr class="tbtitle">
						<td class="ball_g">06</td>
						<td class="ball_ff">
							<input id="Num_692"	onclick="return SubChkBox(this,6)" name="Num_692" value="06"		type="checkbox">	</td>
						<td id="bl_692" class="ball_ff">3</td>

						<td class="ball_g">16</td>
						<td class="ball_ff">
							<input id="Num_693"	onclick="return SubChkBox(this,16)" name="Num_693" value="16"				type="checkbox"></td>
						<td id="bl_693" class="ball_ff">3</td>
						<td class="ball_b">26</td>
						<td class="ball_ff">
							<input id="Num_694"onclick="return SubChkBox(this,26)" name="Num_694" value="26"	type="checkbox"></td>
						<td id="bl_694" class="ball_ff">3</td>
						<td class="ball_b">36</td>
						<td class="ball_ff">
							<input id="Num_695"onclick="return SubChkBox(this,36)" name="Num_695" value="36"	type="checkbox">	</td>
						<td id="bl_695" class="ball_ff">3</td>
						<td class="ball_r">46</td>
						<td class="ball_ff">
							<input id="Num_696"	onclick="return SubChkBox(this,46)" name="Num_696" value="46"type="checkbox">	</td>
						<td id="bl_696" class="ball_ff">3</td>
					</tr>
					<tr class="tbtitle">
						<td class="ball_r">07</td>
						<td class="ball_ff">
							<input id="Num_697"onclick="return SubChkBox(this,7)" name="Num_697" value="07"	type="checkbox"></td>
						<td id="bl_697" class="ball_ff">3</td>
						<td class="ball_g">17</td>
						<td class="ball_ff">
							<input id="Num_698"	onclick="return SubChkBox(this,17)" name="Num_698" value="17"			type="checkbox"></td>
						<td id="bl_698" class="ball_ff">3</td>
						<td class="ball_g">27</td>
						<td class="ball_ff">
							<input id="Num_699"	onclick="return SubChkBox(this,27)" name="Num_699" value="27"type="checkbox"></td>
						<td id="bl_699" class="ball_ff">3</td>
						<td class="ball_b">37</td>
						<td class="ball_ff">
							<input id="Num_700"	onclick="return SubChkBox(this,37)" name="Num_700" value="37"		type="checkbox">	</td>
						<td id="bl_700" class="ball_ff">3</td>
						<td class="ball_b">47</td>
						<td class="ball_ff">
							<input id="Num_701"onclick="return SubChkBox(this,47)" name="Num_701" value="47"			type="checkbox">	</td>
						<td id="bl_701" class="ball_ff">3</td>
					</tr>
					<tr class="tbtitle">
						<td class="ball_r">08</td>
						<td class="ball_ff">
							<input id="Num_702"	onclick="return SubChkBox(this,8)" name="Num_702" value="08"	type="checkbox"></td>
						<td id="bl_702" class="ball_ff">3</td>
						<td class="ball_r">18</td>
						<td class="ball_ff">
							<input id="Num_703"onclick="return SubChkBox(this,18)" name="Num_703" value="1"type="checkbox"></td>
						<td id="bl_703" class="ball_ff">3</td>
						<td class="ball_g">28</td>
						<td class="ball_ff">
							<input id="Num_704"onclick="return SubChkBox(this,28)" name="Num_704" value="28"		type="checkbox">	</td>
						<td id="bl_704" class="ball_ff">3</td>
						<td class="ball_g">38</td>
						<td class="ball_ff">
							<input id="Num_705"	onclick="return SubChkBox(this,38)" name="Num_705" value="38"		type="checkbox"></td>
						<td id="bl_705" class="ball_ff">3</td>
						<td class="ball_b">48</td>
						<td class="ball_ff">
							<input id="Num_706"	onclick="return SubChkBox(this,48)" name="Num_706" value="48"			type="checkbox"></td>
						<td id="bl_706" class="ball_ff">3</td>
					</tr>
					<tr class="tbtitle">
						<td class="ball_b">09</td>
						<td class="ball_ff">
							<input id="Num_707"onclick="return SubChkBox(this,9)" name="Num_707" value="09"		type="checkbox">	</td>
						<td id="bl_707" class="ball_ff">3</td>
						<td class="ball_r">19</td>
						<td class="ball_ff">
							<input id="Num_708"onclick="return SubChkBox(this,19)" name="Num_708" value="19"	type="checkbox">	</td>
						<td id="bl_708" class="ball_ff">3</td>
						<td class="ball_r">29</td>
						<td class="ball_ff">
							<input id="Num_709"	onclick="return SubChkBox(this,29)" name="Num_709" value="29"	type="checkbox"></td>
						<td id="bl_709" class="ball_ff">3</td>
						<td class="ball_g">39</td>
						<td class="ball_ff">
							<input id="Num_710"onclick="return SubChkBox(this,39)" name="Num_710" value="39"				type="checkbox">	</td>
						<td id="bl_661" class="ball_ff">3</td>
						<td class="ball_g">49</td>
						<td class="ball_ff">
							<input id="Num_711"	onclick="return SubChkBox(this,49)" name="Num_711" value="49"	type="checkbox">	</td>
						<td id="bl_711" class="ball_ff">3</td>
					</tr>
					<tr class="tbtitle">
						<td class="ball_b">10</td>
						<td class="ball_ff">
							<input id="Num_712"onclick="return SubChkBox(this,10)" name="Num_712" value="10"		type="checkbox">	</td>
						<td id="bl_712" class="ball_ff">3</td>
						<td class="ball_b">20</td>
						<td class="ball_ff">
							<input id="Num_713"	onclick="return SubChkBox(this,20)" name="Num_713" value="20"	type="checkbox">	</td>
						<td id="bl_713" class="ball_ff">3</td>
						<td class="ball_r">30</td>
						<td class="ball_ff">
							<input id="Num_714"	onclick="return SubChkBox(this,30)" name="Num_714" value="30"		type="checkbox">	</td>
						<td id="bl_714" class="ball_ff">3</td>
						<td class="ball_r">40</td>
						<td class="ball_ff">
							<input id="Num_715"	onclick="return SubChkBox(this,40)" name="Num_715" value="40"		type="checkbox"></td>
						<td id="bl_715" class="ball_ff">3</td>
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
		var cp_cateCode_temp="QBZ";
		var cp_xzlxCode_temp="";

	</script>
	<%@include file="/commons/cp/jsp/cp_fast_set_footer.jsp" %>

</body>
</html>