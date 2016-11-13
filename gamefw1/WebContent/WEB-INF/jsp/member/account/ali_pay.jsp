<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.OutputStream"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<head>
<%@include file="/commons/member/jsp/member_common.jsp"%>

</head>
<body>
<div class="wrapper" style="padding-bottom:100px;">
<div class="panel-content font-hei" >
<div class="page-title clear">
	<div class="left">
		<h2 class="s24 blue">充值到账户</h2>
		<p class="mt5 gray">尊敬的客户您好，使用支付宝扫一扫二维码进行转账</p>
	</div>
	<div class="right mt10">
		<button class="button button-primary button-raised button-pill button-tiny btn-contact"><i></i>在线客服</button>
	</div>
</div>
<div class="page-body switch-mod">
	<div class="switch-group">
		<div class="switch-item" align="center" style="padding-top: 10px;">
			<p style="font-size: 16px;">请认真核实收款方昵称/支付宝账号！</p></br>
			<p><span style="font-size: 14px; color: red;">昵称:${webKjPay.payNname}</span></p></br>
			<p><span style="font-size: 14px; color: red;">账号:${webKjPay.payRname}</span></p></br>
			<img alt="二维码" src="${ctx}/member/getPayImg?paytype=${webKjPay.payType}" width="200" height="200">
		</div>
		<!-- /item -->
	</div>
	<!-- /group -->
</div>
<div class="page-body switch-mod">
	<div class="switch-group">
		<div class="switch-item">
			<div class="pt20">
				<div class="panel-progress">
					<ul class="progress-step-group clear">
						<li class="step step-one step-active">
							<div class="step-inner"><span class="step-icon">1</span><h2>请填写支付表单</h2></div>
						</li>
						<li class="step step-two">
					      <div class="step-inner">
					        <span class="step-icon">2</span>
					        <h2 id="setp2Ts">正在操作，请稍候</h2>
					      </div>
					    </li>
					    <li class="step step-three">
					      <div class="step-inner">
					        <span class="step-icon">3</span>
					        <h2 id="setp3Ts">完成</h2>
					      </div>
					    </li>
					</ul>
					<div class="progress-line"></div>
				</div>
				<!-- /progress -->
			</div>
			<div class="switch-body-wrap">
				<div class="progress-body-item progress-body-step1">
					<div class="verify-item verify-bank mt10 s12">
						<div class="verify-title clear">
							<div class="left b">请填写信息：</div>
						</div>
					</div>
					<!-- /verify -->
					<div class="sheet-mod">
						<div class="sheet-content mt10">
							<form id="form1" action="${ctx}/member/doKjPay" method="post">
							
							<table class="mt20 s14">
								<tr>
									<td width="160" class="pr20 ar gray-dark">会员账号</td>
									<td align="left">${webUser.userName }</td>
								</tr>
								<tr>
									<td class="pr20 ar gray-dark"><label for="rechargeAmount">充值金额</label></td>
									<td class="s12" align="left"> 
										<input type="text" id="hkMoney" name="hkMoney" size="20" class="write b red" maxlength="10" onkeyup="clearNoNum(this);" />
										<span class="pl10 s12 gray">最低入款：${webConfig.companyMinPay }元</span>
									</td>
								</tr>
								<tr>
									<td class="pr20 ar gray-dark"><label for="rechargeTime">汇入日期</label></td>
									<td class="s12" align="left">
										<input type="text" size="10" name="time" value="${currDateStr}" id="time" class="kui-input-date s14 datepicker">
										时间： <select name="timeHour"
												id="timeHour" style="width: 60px" class="dropmenu">
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
												 style="width: 60px" class="dropmenu w50">
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
									<td class="pr20 ar gray-dark"><label for="rechargeName">您支付宝昵称</label></td>
									<td class="s12" align="left"  > 
										<input name="hkUserName" type="text"  id="hkUserName" onfocus="javascript:this.select();" size="30" class="write b red"  maxlength="30"/>
									</td>
								</tr>
								<tr>
									<td></td>
									<td>
										<input type="hidden" name="payNo" id="payNo" value="${webKjPay.payNo}" /> 
										<input type="hidden" name="payType" id="payType" value="${webKjPay.payType}" /> 
										<input type="hidden" name="minPay" id="minPay" value="${webConfig.companyMinPay }" /> 
										<button class="button button-raised button-primary button-small" onclick="SubKjInfo(); return false;">下一步</button>
									</td>
								</tr>
							</table>
							</form>
						</div>
					</div>
					<!-- /sheet -->
				</div>
				<div class="progress-body-item progress-body-step2"></div>
				<!-- /item -->
			</div>
		</div>
		<!-- /item -->
	</div>
	<!-- /group -->
</div>
<!-- /body -->
</div>
</div>
<script src="${resourceRoot}/member/js/plugins.js"></script>
</body>