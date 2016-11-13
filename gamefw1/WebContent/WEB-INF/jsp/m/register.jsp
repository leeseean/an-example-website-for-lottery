<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title></title>
  <%@include file="inc/mobile_common.jsp"%>
</head>

<body>
<div data-role="page" class="ybm-account ybm-reg">
   <div data-role="header" data-position="fixed">
   	 <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal">
        <a href="#" data-rel="back" data-role="button" data-ajax="false" data-icon="back" data-iconpos="notext"></a>
      </div>
     <h1>免费开户</h1>
   </div>
  <div data-role="content" class="ybm-form ui-mini">
  	<form id="regForm" name="regForm" method="POST" action="">
	    <div class="account-form f12">
	      <div data-role="fieldcontain">
	        <label for="userName"><font style="color: red;">*</font>账号：</label>
	        <input type="text" onkeyup="charSize(this,8);" onblur="validRegisterUserName(this);" name="userName" id="userName" placeholder="请输入4-8个字符,仅可输入英文字母以及数字的组合!">
	      </div>
	      <div data-role="fieldcontain">
	        <label for="userPassword"><font style="color: red;">*</font>密碼：</label>
	        <input type="password" onkeyup="charSize(this,12);" name="userPassword" id="userPassword" placeholder="须为6~12码英文或数字且符合0~9或a~z字元">
	      </div>
	      <div data-role="fieldcontain">
	        <label for="ruserPassword"><font style="color: red;">*</font>確認密碼：</label>
	        <input type="password" onkeyup="charSize(this,12);" name="ruserPassword" id="ruserPassword" placeholder="须为6~12码英文或数字且符合0~9或a~z字元">
	      </div>
	      <div data-role="fieldcontain">
	        <label for="userAgent"><font style="color: red;">*</font>代理账号：</label>
	        <input type="text" name="userAgent" id="userAgent" placeholder="推荐代理账号,默认账号(888)" value="888" onblur="validUserAgent(this)">
	      </div>
	      <div data-role="fieldcontain">
	        <label for="userRealName"><font style="color: red;">*</font>真实姓名：</label>
	        <input type="text" onkeyup="charSize(this,8);" placeholder="真实姓名要和银行开户姓名一致" id="userRealName" name="userRealName">
	      </div>
	      <c:if test="${ctxMap['swBirthday'] == '1'}">
		      <div data-role="fieldcontain">
		        <label for="birthday">
		        	<c:if test="${ctxMap['swBirthday_1'] == '1'}">
						<font color="#ff0000">*</font>
						<input type="hidden" name="swBirthday_1" id="swBirthday_1" value="${ctxMap['swBirthday_1'] }">
					</c:if>生日：
				</label>
		        <input type="date" name="birthday" id="birthday" value="${birthday }">
		      </div>
	      </c:if>
	      <c:if test="${ctxMap['swmobile'] == '1'}">
		      <div data-role="fieldcontain">
		        <label for="userMobile">
		        	<c:if test="${ctxMap['swmobile_1'] == '1'}">
						<font color="#ff0000">*</font>
						<input type="hidden" name="swmobile_1" id="swmobile_1" value="${ctxMap['swmobile_1'] }">
					</c:if>手机号码：
				</label>
		        <input type="number" name="userMobile" id="userMobile" onkeyup="charSize(this,11);" maxlength="11" placeholder="以手机形式确认提款，必需填写真实号码">
		      </div>
	      </c:if>
	      <c:if test="${ctxMap['swMail'] == '1'}">
		      <div data-role="fieldcontain">
		        <label for="userMail">
		        	<c:if test="${ctxMap['swMail_1'] == '1'}">
						<font color="#ff0000">*</font>
						<input type="hidden" name="swMail_1" id="swMail_1" value="${ctxMap['swMail_1'] }">
					</c:if>邮箱：
				</label>
		        <input type="text" name="userMail" id="userMail" onkeyup="charSize(this,20);">
		      </div>
	      </c:if>
	      <c:if test="${ctxMap['swQq'] == '1'}">
		      <div data-role="fieldcontain">
		        <label for="userQq">
		        	<c:if test="${ctxMap['swQq_1'] == '1'}">
						<font color="#ff0000">*</font>
						<input type="hidden" name="swQq_1" id="swQq_1" value="${ctxMap['swQq_1'] }">
					</c:if>QQ号码：
				</label>
		        <input type="number" name="userQq" id="userQq" onkeyup="charSize(this,11);" maxlength="11">
		      </div>
	      </c:if>
	      <div data-role="fieldcontain">
	        <label for="userWithdrawPassword"><font style="color: red;">*</font>提款密码：</label>
	        <input type="number" placeholder="4位数字密码" onkeyup="charSize(this,4);" name="userWithdrawPassword" id="userWithdrawPassword">
	      </div>
      <div class="ui-grid-a row-action">
        <div class="ui-block-a"><input type="submit" value="确认" data-theme="b" name="addnews" onclick="registerForm();return false;"></div>
        <div class="ui-block-b"><input type="reset" value="重设"></div>
      </div>
	      <div id="submitmsg" style="display: none;color: red;">数据提交中...请稍等</div>
	    </div>
    </form>
  </div>
</div>
<!-- /開戶 -->
</body>
</html>
