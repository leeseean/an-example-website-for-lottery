<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>个人中心</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/commons/member/jsp/member_common.jsp"%>
</head>
<body>
<body>
	<div class="ui-moneyBox">
		<ul>
			<table width="100%" border="0" cellspacing="0" cellpadding="5" id="thetable">
				<tr>
					<td colspan="6" align="center" bgcolor="#333333" style="font-size: 14px; color: #FF0">请选择下列公司账号进行转账汇款</td>
				</tr>
				<s:if test="listBanks==null or listBanks.size==0">
					<tr>
						<td colspan="6" align="center"><font size="+1" color="blue">为了本公司帐号安全,暂时不公开银行帐号,请您联系客服获取,或点击:</font>
							<a href='javascript:void(0)' onclick="Go('/member/index!${thirdPay.thirdType}.do')" style='font-size: 16px; font-weight: bold; color: #F00'>在线支付</a>
						</td>
					</tr>
				</s:if>

				<c:forEach var="item" items="${listBanks}">
					<tr>
						<td width="200" height="24" align="right">${item.bankType }：</td>
						<td width="200" style="font-size: 14px; color: #F00;" align="center">${item.bankCard }</td>
						<td width="60" align="right">开户名：</td>
						<td width="100" align="center" style="font-size: 14px; color: #F00;">${item.bankUser }</td>
						<td width="60" align="right">开户行：</td>
						<td style="font-size: 14px; color: #F00;" align="center">${item.bankAddress }</td>
					</tr>
				</c:forEach>

				<tr>
					<td height="24" colspan="6">
						<p><strong>温馨提示：</strong> <br /></p>
						<p>一、请在金额转出之后务必填写该页下方的汇款信息表格，以便财务系统能够及时的为您确认添加金额到您的会员帐户中。 <br /></p>
						<p>二、 <font color="red">本公司最低存款金额为${webConfig.companyMinPay}元RMB</font>，公司财务系统将对银行存款的会员以存款金额大小，实行返利派送。</p>
						<p>三、跨行转帐请您使用跨行快汇。</p>
					</td>
				</tr>
			</table>
		</ul>
		<ul style="margin-top: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="5">
				<form id="form1" action="/member/index!bank.do" method="post">
					<tr>
						<td colspan="2" align="center" bgcolor="#333333"
							style="font-size: 14px; color: #FF0">请认真填写下列表单</td>
					</tr>
					<tr>
						<td width="150" align="right">用户名：</td>
						<td><font color="#0000FF">${webUser.userName }</font></td>
					</tr>
					<tr>
						<td align="right">存款金额：</td>
						<td>
							<input type="text" id="bankHk.hkMoney"
								name="bankHk.hkMoney" class="Input_100"
								onkeyup="clearNoNum(this);" /><font color="red">*最低入款${webConfig.companyMinPay }元</font>
						</td>
					</tr>
					<tr>
						<td align="right">汇入银行：</td>
						<td>
							<select id="bankHk.hkCompanyBank" name="bankHk.hkCompanyBank" style="height: 26px; border: 1px solid #d7d7d7;">
								<option value="" selected="selected">--请选择转入银行--</option>
								<c:forEach var="item" items="${listBanks}">
									<option value="${item.bankType } - ${item.bankUser }">
										${item.bankType } - ${item.bankUser }
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">汇入时间：</td>
						<td><input name="time" id="time" value="${time}"
							style="height: 17px; width: 90px" class="Wdate" type="text"
							onClick="WdatePicker();"> 时间： <select name="timeHour"
							id="timeHour" style="height: 26px; border: 1px solid #d7d7d7;">
								<option value="00">00</option>
								<option value="01">01</option>
								<option value="02">02</option>
								<option value="03">03</option>
								<option value="04">04</option>
								<option value="05">05</option>
								<option value="06">06</option>
								<option value="07">07</option>
								<option value="08">08</option>
								<option value="09">09</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
								<option value="13">13</option>
								<option value="14">14</option>
								<option value="15">15</option>
								<option value="16">16</option>
								<option value="17">17</option>
								<option value="18">18</option>
								<option value="19">19</option>
								<option value="20">20</option>
								<option value="21">21</option>
								<option value="22">22</option>
								<option value="23">23</option>
						</select> 时 <select name="timeMinute" id="timeMinute"
							style="height: 26px; border: 1px solid #d7d7d7;">
								<option value="00">00</option>
								<option value="01">01</option>
								<option value="02">02</option>
								<option value="03">03</option>
								<option value="04">04</option>
								<option value="05">05</option>
								<option value="06">06</option>
								<option value="07">07</option>
								<option value="08">08</option>
								<option value="09">09</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
								<option value="13">13</option>
								<option value="14">14</option>
								<option value="15">15</option>
								<option value="16">16</option>
								<option value="17">17</option>
								<option value="18">18</option>
								<option value="19">19</option>
								<option value="20">20</option>
								<option value="21">21</option>
								<option value="22">22</option>
								<option value="23">23</option>
								<option value="24">24</option>
								<option value="25">25</option>
								<option value="26">26</option>
								<option value="27">27</option>
								<option value="28">28</option>
								<option value="29">29</option>
								<option value="30">30</option>
								<option value="31">31</option>
								<option value="32">32</option>
								<option value="33">33</option>
								<option value="34">34</option>
								<option value="35">35</option>
								<option value="36">36</option>
								<option value="37">37</option>
								<option value="38">38</option>
								<option value="39">39</option>
								<option value="40">40</option>
								<option value="41">41</option>
								<option value="42">42</option>
								<option value="43">43</option>
								<option value="44">44</option>
								<option value="45">45</option>
								<option value="46">46</option>
								<option value="47">47</option>
								<option value="48">48</option>
								<option value="49">49</option>
								<option value="50">50</option>
								<option value="51">51</option>
								<option value="52">52</option>
								<option value="53">53</option>
								<option value="54">54</option>
								<option value="55">55</option>
								<option value="56">56</option>
								<option value="57">57</option>
								<option value="58">58</option>
								<option value="59">59</option>
						</select> 分</td>
					</tr>
					<tr>
						<td align="right">汇入方式：</td>
						<td>
							<select id="bankHk.hkType" name="bankHk.hkType" style="height: 26px; border: 1px solid #d7d7d7;">
									<option value="">请选择汇款方式</option>
									<option value="银行柜台">银行柜台</option>
									<option value="ATM现金">ATM现金</option>
									<option value="ATM卡转">ATM卡转</option>
									<option value="网银转账">网银转账</option>
									<option value="手机银行转帐">手机银行转帐</option>
	
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">汇款人姓名：</td>
						<td>
							<input name="bankHk.hkUserName" type="text" class="Input_100" id="bankHk.hkUserName" onfocus="javascript:this.select();" size="34" />
						</td>
					</tr>
					<tr>
						<td colspan="2" align="left">
							<p><strong>汇款信息提交说明：</strong> <br /></p>
							<p>一、请您按表格提示填写准确的汇款转帐信息，确认提交后财务系统将会及时的为您核查入款情况！ <br /></p>
							<p>二、请您在转帐时金额后面多加个尾数（例如：存款金额 1000.66 或 1000.88）等。 <br /></p>
							<p>三、如有任何疑问,您可以联系在线客服,我们为您提供365天×24小时不间断的友善和专业客户咨询服务！</p></td>
					</tr>
					<tr>
						<td colspan="2" align="left" style="padding-left: 160px;">
							<input type="hidden" name="key" id="key" value="${param.key }" /> 
							<input type="hidden" name="minPay" id="minPay" value="${webConfig.companyMinPay }" /> 
							<input type="button" name="button" id="button" value="确认提交" class="m_btn" onclick="SubInfo();" />
						</td>
					</tr>
				</form>
			</table>
		</ul>
	</div>
</body>
</html>