<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>公司入款</title>
  <%@include file="../inc/mobile_common.jsp"%>
</head>

<body>
  <div data-role="page" id="deposit-accounts">
    <div data-role="header" data-position="fixed">
      <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal">
        <a href="#quickMenu" data-role="button" data-icon="bars" data-iconpos="notext"></a>
        <a href="${ctx}/m/wap/member" data-role="button" data-ajax="false" data-icon="back" data-iconpos="notext"></a>
      </div>
      <h1>入款账户</h1>
    </div>
    <!-- /header -->
    <div data-role="content" class="ybm-deposit">
      <div class="deposit-accounts accounts-list">
      <c:forEach var="item" items="${bankList}">
        <ul data-role="listview" data-inset="true" data-corners="false" class="item">
          <li data-role="list-divider">${item.bankType }</li>
          <li class="b tac red">${item.bankCard }</li>
          <li>
            <div class="ui-grid-a">
              <div class="ui-block-a">开户名</div>
              <div class="ui-block-b">${item.bankUser }</div>
            </div>
          </li>
          <li>
            <div class="ui-grid-a">
              <div class="ui-block-a">开户行</div>
              <div class="ui-block-b">${item.bankAddress }</div>
            </div>
          </li>
        </ul>
        </c:forEach>
      </div>
    </div>
    <!-- /content -->
    <div data-role="footer" data-position="fixed" data-id="deposit-footer">
      <div data-role="navbar">
        <ul>
          <li><a href="#deposit-accounts" data-transition="none" class="ui-btn-active ui-state-persist">入款账户</a></li>
          <li><a href="#deposit-form" data-transition="none">入款表单</a></li>
        </ul>
      </div>
    </div>
    <!-- /footer -->
    <!-- 左側快捷菜單 -->
    <div data-role="panel" data-display="overlay" id="quickMenu" class="ybm-panel">
    	<%@ include file="../inc/left_memu.jsp" %>
    </div>
    <!-- /左側快捷菜單 -->
  </div>
  <!-- /page -->
  <div data-role="page" id="deposit-form">
    <div data-role="header" data-position="fixed">
      <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal">
        <a href="#quickMenu2" data-transition="none" data-role="button" data-icon="bars" data-iconpos="notext"></a>
        <a href="${ctx}/m/wap/member" data-role="button" data-ajax="false" data-icon="back" data-iconpos="notext"></a>
      </div>
      <h1>入款表单</h1>
    </div>
    <!-- /header -->
    <div data-role="content" class="ybm-deposit">
      <div class="ybm-form deposit-form ui-mini">
        <ul data-role="listview">
          <li data-role="divider" data-theme="e">会员帐号：${webUser.userName }</li>
          <li>
            <div data-role="fieldcontain">
              <label for="hkMoney">充值金额</label>
              <input type="number" name="hkMoney" id="hkMoney" pattern="[0-9]*" placeholder="${webConfig.companyMinPay }" value="${webConfig.companyMinPay }">
            </div>
          </li>
          <!-- /item -->
          <li>
            <div data-role="fieldcontain">
              <label for="hkCompanyBank">汇入银行</label>
              <select name="hkCompanyBank" id=hkCompanyBank>
                <c:forEach var="item" items="${bankList}">
	                <option value="${item.payNo }">
						${item.bankType } - ${item.bankUser }
					</option>
                </c:forEach>
              </select>
            </div>
          </li>
          <!-- /item -->
          <li>
            <div data-role="fieldcontain">
              <label for="createTime">汇入日期</label>
              <input type="date" name="createTime" id="createTime" value="${currDateStr }">
            </div>
          </li>
          <!-- /item -->
          <li>
            <div data-role="fieldcontain">
              <label for="hkType">汇入方式</label>
              <select name="hkType" id="hkType">
                <option value="银行柜台">银行柜台</option>
				<option value="ATM现金">ATM现金</option>
				<option value="ATM卡转">ATM卡转</option>
				<option value="网银转账">网银转账</option>
				<option value="手机银行转帐">手机银行转帐</option>
				<option value="微信转账">微信转账</option>
				<option value="支付宝转账">支付宝转账</option>
              </select>
            </div>
          </li>
          <!-- /item -->
          <li>
            <div data-role="fieldcontain">
              <label for="hkUserName">汇款人姓名</label>
              <input type="text" name="hkUserName" id="hkUserName" placeholder="真实姓名" value="${webUser.userRealName }">
            </div>
          </li>
          <!-- /item -->
          <li class="tac">
            <fieldset data-role="controlgroup" data-type="horizontal" data-corners="false">
              <a href="javascript:void(0);" onclick="checkparam();" type="submit" data-transition="pop" data-role="button" data-corners="false"
					data-inline="true" data-icon="check" data-theme="b">
					确认
				</a>
            </fieldset>
          </li>
          <!-- /item -->
        </ul>
      </div>
    </div>
    <!-- /content -->
    <div data-role="footer" data-position="fixed" data-id="deposit-footer">
      <div data-role="navbar">
        <ul>
          <li><a href="#deposit-accounts" data-transition="none">入款账户</a></li>
          <li><a href="#deposit-form" data-transition="none" class="ui-btn-active ui-state-persist">入款表单</a></li>
        </ul>
      </div>
    </div>
    <!-- /footer -->
    <!-- 左側快捷菜單 -->
    <div data-role="panel" data-display="overlay" id="quickMenu2" class="ybm-panel">
    	<jsp:include page="../inc/left_memu.jsp"></jsp:include>
    </div>
    <!-- /左側快捷菜單 -->
  </div>
  <!-- /page -->
  <script type="text/javascript">
  
function checkparam()
{
	var hkMoney = $("#hkMoney").val();
	var hkCompanyBank = $("#hkCompanyBank").val();
	var createTime = $("#createTime").val();
	var hkType = $("#hkType").val();
	var hkUserName = $("#hkUserName").val();
	if(!checkParam(hkMoney,hkUserName,createTime))
		return;
	
	$.mobile.changePage("#deposit_dialog", "pop", true, true);
}
  
function deposit_submit()
{
  	var hkMoney = $("#hkMoney").val();
	var hkCompanyBank = $("#hkCompanyBank").val();
	var createTime = $("#createTime").val();
	var hkType = $("#hkType").val();
	var hkUserName = $("#hkUserName").val();
	if(!checkParam(hkMoney,hkUserName,createTime))
		return;
	
	$("#out_sub").hide();
	$("#out_msg").show();
	$.ajax( {
		url : rootPath+"/member/doBankPay",
		type : "POST",
		data :{'hkMoney':hkMoney,'hkCompanyBank':$("#hkCompanyBank").find("option:selected").text(),'createTime':createTime,'hkType':hkType,'hkUserName':hkUserName,'payNo':hkCompanyBank,'mobile':1},
		dataType : "json",
		timeout:30000,
		success : function(data) {
			$("#respMsg").text(data.msg);
			$.mobile.changePage("#resp_success", "pop", true, true);
		}
	});
	
}

function checkParam(money,name,createTime)
{
	var companyMinPay = parseInt("${webConfig.companyMinPay }");
	if (money < companyMinPay)
	{
		alert("最小汇款金额:" + companyMinPay);
		return false;
	}
	
	if(createTime == "")
	{
		alert("请选择汇款时间");
		return;
	}
	var re = /^[\da-z\u4E00-\u9FA5\uF900-\uFA2D]+$/g;
	var valid = re.test(name);
	if(!valid)
	{
		alert("请正确输入真实姓名");
		return false;
	}
	return true;
}
</script>
  
<%--确认弹框 --%>
<div data-role="dialog" data-close-btn="none" id="deposit_dialog">
  <div data-role="header">
    <h1>入款确认</h1>
  </div>
  <div data-role="content" class="ybm-order-confirm">
    <ul data-role="listview">
      <li data-role="list-divider" data-theme="e">
        确定入款吗?
      </li>
      <li style=" text-align: center;" id="out_sub">
          <fieldset data-role="controlgroup" data-type="horizontal" data-corners="false">
            <a href="javascript:void(0);" onclick="deposit_submit();" data-rel="dialog" data-transition="pop" data-role="button" data-icon="check" data-theme="b">确认</a>
            <a href="" data-role="button" data-rel="back" data-icon="back">取消</a>
          </fieldset>
      </li>
      <li style="display: none;" id="out_msg"><font style="color: red;">请求处理中...请勿关闭页面</font></li>
    </ul>
  </div>
</div>

<div data-role="dialog" data-close-btn="none" id="resp_success">
  <div data-role="header">
    <h1>操作结果</h1>
  </div>
  <div data-role="content" class="ybm-order-confirm">
    <ul data-role="listview">
      <li data-role="list-divider" data-theme="e" id="respMsg">
      </li>
      <li style=" text-align: center;">
          <fieldset data-role="controlgroup" data-type="horizontal" data-corners="false">
            <a href="${ctx }/m/member/goAccounts" data-role="button" data-ajax="false" data-icon="check" data-theme="b">确认</a>
          </fieldset>
      </li>
    </ul>
  </div>
</div>

</body>

</html>
