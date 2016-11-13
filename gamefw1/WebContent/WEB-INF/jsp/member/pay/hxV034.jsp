<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <head>
    <base href="<%=basePath%>">
    
    <title>账户充值</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	
<%@include file="/commons/member/jsp/member_common.jsp"%>
	<script type="text/javascript">
	var billno = '${billno}';
	</script>
  </head>
  <body>
  	<div class="switch-body-wrap">
		<div class="progress-body-item progress-body-step1">
			<div class="sheet-mod">
				<div class="sheet-content mt10">
				<form id="payForm" action="${ctx}/pay/doPayCenterData" method="post" target="_blank">
					<table class="mt20 s14">
						<tr>
							<td width="160" class="pr20 ar gray-dark">会员账号</td>
							<td>${user.userName }</td>
						</tr>
						<tr>
							<td class="pr20 ar gray-dark">总账户余额</td>
							<td><span class="red">${webuser.userMoney }</span></td>
						</tr>
						<tr>
							<td class="pr20 ar gray-dark"><label for="rechargeAmount">充值金额</label>
							</td>
							<td class="s12">
							<input type="text" size="10" class="write b red" id="money" name="money" onKeyUp="clearNoNum(this);" />
								<span class="pl10 s12 gray">单笔支付（${thirdPay.thirdMinEdu } ~ ${thirdPay.thirdMaxEdu }）元！</span>
							</td>
						</tr>
						<tr>
							<td></td>
							<td>
							
							
							<input type="button" class="button button-raised button-primary button-small"
									onclick="return SubInfoForOnlinePay();" value="充值"/>
								</td>
						</tr>
					</table>
					<input type="hidden" name="thirdMinEdu" id="thirdMinEdu" value=${thirdPay.thirdMinEdu } />
					<input type="hidden" name="thirdMaxEdu"  id="thirdMaxEdu" value=${thirdPay.thirdMaxEdu }  />
					<input type="hidden" name="domain" value="<%=basePath%>"/>
					<input type="hidden" name="billno" value="${billno }"/>
					<input type="hidden" name="payId" value="${thirdPay.id }"/>
					<input type="hidden" name="pay_url" value="/payhc/payCenter_payHandleCenter.action"/>
					</form>
				</div>
			</div>
			<!-- /sheet -->
		</div>
		<div class="progress-body-item progress-body-step2"></div>
		<!-- /item -->
	</div>
  </body>
</html>
