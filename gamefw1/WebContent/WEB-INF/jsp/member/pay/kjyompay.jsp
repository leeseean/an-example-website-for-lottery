<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<head>
  <%@include file="/commons/member/jsp/member_common.jsp"%>
  <script type="text/javascript">var billno = '${billno}';</script>
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
                <td style="text-align: left">${user.userName }</td>
              </tr>
              <tr>
                <td class="pr20 ar gray-dark">总账户余额</td>
                <td style="text-align: left">
                  <span class="red">${webuser.userMoney }</span>
                </td>
              </tr>
              <tr>
                <td class="pr20 ar gray-dark">
                  <label for="rechargeAmount">充值金额</label>
                </td>
                <td class="s12" style="text-align: left;width: 40px">
                  <input type="text" size="10" class="write b red" id="money" name="money" onKeyUp="clearNoNum(this);" />
                  <span class="pl10 s12 gray">单笔支付（${paykj.payMinEdu } ~ ${paykj.payMaxEdu }）元！</span>
                </td>
         
              </tr>
              
              <tr>
                <td></td>
                <td style="text-align: left">
                  <input type="button" class="button button-raised button-primary button-small" onclick="return SubInfoForOnlinePay(this.form);" value="充值" />
                </td>
              </tr>
            </table>
            <input type="hidden" name="thirdMinEdu" id="thirdMinEdu" value=${paykj.payMinEdu } />
            <input type="hidden" name="thirdMaxEdu" id="thirdMaxEdu" value=${paykj.payMaxEdu } } />
            <input type="hidden" name="domain" value="<%=basePath%> " />
            <input type="hidden" name="billno" value="${billno }" />
            <input type="hidden" name="payId" value="${thirdPay.id }" />
           	<input type="hidden" name="bank_code" value="${choosePayType}"/>
            <input type="hidden" name="pay_url" value="/payhc/payCenter_payHandleCenter.action" />
          </form>
        </div>
      </div>
      <!-- /sheet --> </div>
    <div class="progress-body-item progress-body-step2"></div>
    <!-- /item --> </div>
</body>

</html>