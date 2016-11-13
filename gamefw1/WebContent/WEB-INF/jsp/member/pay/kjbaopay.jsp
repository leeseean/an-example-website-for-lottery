<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<head>
  <%@include file="/commons/member/jsp/member_common.jsp"%>
    <script type="text/javascript">
    var billno = '${billno}';
    </script>
    <style>
      body{
        background-color: #F6F6F6;
      }
    </style>
</head>

<body>
  <div class="switch-body-wrap">
    <div class="progress-body-item progress-body-step1">
      <div class="sheet-mod">
        <div class="sheet-content mt10">
          <form id="payForm" action="${ctx}/pay/doPaykjCenterData" method="post" target="_blank">
            <table class="mt20 s14">
              <tr>
                <td width="160" class="pr20 ar gray-dark">会员账号</td>
                <td align="left">${user.userName }</td>
              </tr>
              <tr>
                <td class="pr20 ar gray-dark">总账户余额</td>
                <td align="left"><span class="red">${webuser.userMoney }</span></td>
              </tr>
              <tr>
                <td class="pr20 ar gray-dark">
                  <label for="rechargeAmount">充值金额</label>
                </td>
                <td class="s12" align="left">
                  <input type="text" size="10" class="write b red" id="money" name="money" onKeyUp="clearNoNum(this);" />
                  <span class="pl10 s12 gray">单笔支付（${paykj.payMinEdu } ~ ${paykj.payMaxEdu }）元！</span>
                </td>
              </tr>
              <tr>
                <td width="160" class="pr20 ar gray-dark">支付卡类型：</td>
                <td align="left">
                  <select id="bank_code" name="bank_code" class="dropmenu w150">
                    <option value="3001">招商银行</option>
                    <option value="3002">中国工商银行</option>
                    <option value="3003">中国建设银行</option>
                    <option value="3004">上海浦东发展银行</option>
                    <option value="3005">中国农业银行</option>
                    <option value="3006">中国民生银行</option>
                    <option value="3009">兴业银行</option>
                    <option value="3020">中国交通银行</option>
                    <option value="3022">中国光大银行</option>
                    <option value="3026">中国银行</option>
                    <option value="3032">北京银行</option>
                    <option value="3035">平安银行</option>
                    <option value="3036">广发银行|CGB</option>
                    <option value="3037">上海农商银行</option>
                    <option value="3038">中国邮政储蓄银行</option>
                    <option value="3039">中信银行</option>
                    <option value="3050">华夏银行</option>
                    <option value="3059">上海银行</option>
                    <option value="3060">北京农商银行</option>
                    <option value="3080001">银联无卡支付</option>
                  </select>
                </td>
              </tr>
              <tr>
                <td></td>
                <td align="left">
                  <input type="button" class="button button-raised button-primary button-small" onclick="return SubInfoForOnlinePay(this.form);" value="充值" />
                </td>
              </tr>
            </table>
            <input type="hidden" name="thirdMinEdu" id="thirdMinEdu" value=${paykj.payMinEdu } />
            <input type="hidden" name="thirdMaxEdu" id="thirdMaxEdu" value=${paykj.payMaxEdu } } />
            <input type="hidden" name="domain" value="<%=basePath%>" />
            <input type="hidden" name="billno" value="${billno }" />
            <input type="hidden" name="payId" value="${thirdPay.id }" />
            <input type="hidden" name="choosePayType" value="${choosePayType}" />
            <input type="hidden" name="pay_url" value="/payhc/payCenter_payHandleCenter.action" />
            <input type="hidden" name="payType" value="${paykj.payType }" />
          </form>
        </div>
      </div>
      <!-- /sheet -->
    </div>
    <div class="progress-body-item progress-body-step2"></div>
    <!-- /item -->
  </div>
  <script src="${resourceRoot}/member/js/plugins.js"></script>
</body>

</html>
