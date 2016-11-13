<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="mh" uri="http://www.mh.com/framework/tags"%>
      <!-- <div class="test-content">
	      <div class="pagesmain">
	        <div class="banner" style="background:url(${resourceRoot}/web/ybb/assets/img/pages/bannerreg.jpg) top center no-repeat; height:256px;"></div>
				<%@ include file="msg.jsp" %>
	          <div class="aboutpages clear">
	            <div class="pagesleft">
	              <img src="${resourceRoot}/web/ybb/assets/img/pages/welcome.png">
	              <ul class="nav">
	                <li class="li01"><a href="${ctx}/goPageCenter?code=sport">体育赛事</a></li>
	                <li class="li03"><a href="${ctx}/goPageCenter?code=live">真人视讯</a></li>
	                <li class="li04"><a href="${ctx}/electronic?code=mg">电子游艺</a></li>
	                <li class="li05">
	                  <c:choose>
	                    <c:when test="${!empty webUser}">
	                      <a href="javascript:void(0)" onclick="winOpen('${ctx}/lottery',window.screen.width,window.screen.height,0,0,'彩票','caipiao')">彩票游戏</a>
	                    </c:when>
	                    <c:otherwise>
	                      <a href="javascript:void(0)" onclick="alert('您尚未登录，请先登录再进行游戏')">彩票游戏</a>
	                    </c:otherwise>
	                  </c:choose>
	                </li>
	                <li class="li06"><a href="${ctx}/register">免费开户</a></li>
	              </ul>
	              <div class="leftwhychose">
	                <img src="${resourceRoot}/web/ybb/assets/img/pages/left02.jpg" class="whychoseimg">
	              </div>
	            </div>
	            <div class="pagesright article">

	<div class="container">
	  <div class="ybb-md-ntc-signup"></div>
	  <div class="module-content module-signup">
	    <div class="module-notice module-notice-signup">${ctxMap['msg009'] }</div>
	    <form id="regForm" name="regForm" method="POST" action="${ctx}/doRegister">
	      <div class="ybb-signup-form ybb-try" style="padding-top:10px">
	        <div class="section">
	          <div class="ybb-md-tt">
	            <ul class="clear">
	              <li>试玩账户注册</li>
	              <li>已有账户登入</li>
	            </ul>
	          </div>
	          <mh:token></mh:token>
	          <div class="ybb-md-ct">
	            <ul>
	              <li>
	                <div class="form-section-item item">
	                  <label for="field-id">账号</label>
	                  <div class="form-section-content">
	                    <span>${userName}</span>
	                    <input type="hidden" id="testUserName" name="testUserName" value="${userName}" />
	                  </div>
	                </div>
	                <div class="form-section-item item">
	                  <label for="field-psw">密码</label>
	                  <div class="form-section-content">
	                    <input type="password" maxlength="12" id="password1" name="password1" value="" onblur="validTestPassword1(this)" class="ybb-ui-in">
	                    <font color="#ff0000">*</font> <span id="password1Info"></span>
	                    <p>须为6~12码英文或数字且符合0~9或a~z字元</p>
	                  </div>
	                </div>
	                <div class="form-section-item item">
	                  <label for="field-psw-verify">确认密码</label>
	                  <div class="form-section-content">
	                    <input type="password" id="password2" name="password2" onblur="validTestPassword2(this)" value="" maxlength="12" class="ybb-ui-in">
	                    <font color="#ff0000">*</font><span id="password2Info"></span>
	                    <p>须为6~12码英文或数字且符合0~9或a~z字元</p>
	                  </div>
	                </div>
	                <div class="item">
	                  <div class="item">
	                    <div class="form-section-content">
	                      <input type="checkbox" id="agree" style=" vertical-align: middle" checked="checked" style="background-color:none"> 我已届满合法博彩年龄﹐且同意各项开户条约。 <a href="javascript:void(0)" class="signup-agm">《開戶協議》</a>
	                    </div>
	                  </div>
	                  <div class="item">
	                    <div class="form-section-content">
	                      <input type="button" class="ui-button" value="进入游戏" name="addnews" onclick="registerTestForm();return false;">
	                    </div>
	                  </div>
	                  <div class="signup-notice">
	                    <h6>备注：</h6>
	                    <ol>
	                      <li>带 * 号项为必填项</li>
	                      <li>注册账户为本网站试玩账户，可在试玩登录进行登录再次试玩。</li>
	                    </ol>
	                  </div>
	                </div>
	              </li>
	              <li>
	                <div class="form-section-item item">
	                  <label for="field-id">账号</label>
	                  <div class="form-section-content">
	                    <input type="text" maxlength="12" id="testLoginUserName" name="testLoginUserName" value="" onblur="validTestLoginName(this)" class="ybb-ui-in">
	                    <font color="#ff0000">*</font> <span id="testLoginUserNameInfo"></span>
	                    <p>须为6~12码英文或数字且符合0~9或a~z字元</p>
	                  </div>
	                </div>
	                <div class="form-section-item item">
	                  <label for="field-psw">密码</label>
	                  <div class="form-section-content">
	                    <input type="password" maxlength="12" id="testLoginPwd" name="testLoginPwd" value="" onblur="validTestLoginPassword(this)" class="ybb-ui-in">
	                    <font color="#ff0000">*</font> <span id="testLoginPwdInfo"></span>
	                    <p>须为6~12码英文或数字且符合0~9或a~z字元</p>
	                  </div>
	                </div>
	                <div class="form-section-item item">
	                  <label for="field-psw">验证码</label>
	                  <div class="form-section-content">
	                    <input type="text" name="testverifycode" id="testverifycode" value="" class="ybb-ui-in" size="4" maxlength="4" style="width: 45px;" onblur="validTestVerifycode(this)" />
	                    <span id="testverifycodeLabelId" onclick="javascript:createTestCode();"><img
	                  src="${resourceRoot}/web/ybb/assets/images/yzm.gif"
	                  id="img_testverifycode" style="vertical-align: middle">
	                </span>
	                    <font color="#ff0000">*</font><span id="testverifycodeInfo"></span>
	                  </div>
	                </div>
	                <div class="item">
	                  <div class="item">
	                    <div class="form-section-content">
	                      <input type="button" class="ui-button" value="登录" onclick="loginTestForm();">
	                    </div>
	                  </div>
	                  <div class="signup-notice">
	                    <h6>备注：</h6>
	                    <ol>
	                      <li>带 * 号项为必填项</li>
	                    </ol>
	                  </div>
	                </div>
	              </li>
	            </ul>
	          </div>
	        </div>
	      </div>
	    </form>
	  </div>
	</div>

	            </div>
	          </div>
	          <!-- /article -->
	     <!--  </div>
      </div> --> -->
<style>
	.content-top-banner {
    margin-top: 56px;
    height: 441px;
  }
</style>
<div class="row" style="background: black">
	<div class="wrapper">
		<div class="content-top-banner" style="background:url(${resourceRoot}/web/ybb/assets/img/zhenren/zhenrenbg1.jpg) top center no-repeat; height:398px;"></div>
	</div>
</div>

<div class="row">
	<div class="wrapper">
		<div class="header-msg">
			<%@include file="msg.jsp"%>
		</div>
	</div>
</div>

<div class="row">
	<div class="wrapper">
		<div class="content-body">
			<div class="container clearfix">
				<div class="pages-help pages-help-reg" style="padding-bottom: 76px">
					<div class="nav nav-left w-percent-30 left">
						<div class="h69"></div>
						<ul class="clearfix">
							<li><a
								href="javascript:Go('${ctx}/goPageCenter?code=sport')"
								class="block"><em class="icon-1"></em><span>体育赛事</span></a></li>
							<li><a
								href="javascript:Go('${ctx}/goPageCenter?code=live')"
								class="block"><em class="icon-2"></em><span>视讯直播</span>  </a></li>
							<li class="current"><a
								href="javascript:Go('${ctx}/electronic?code=mg')"
								class="block"><em class="icon-3"></em><span>电子游戏</span>  </a></li>
							<li><a
								href="javascript:Go('${ctx}/goPageCenter?code=lotto')"
								class="block"><em class="icon-4"></em> <span>彩票游戏</span></a></li>
							<li><a
								href="javascript:Go('${ctx}/goPageCenter?code=promos', 'promos')"
								class="block"><em class="icon-5"></em><span>优惠活动</span> </a></li>
							<li><a
								href="javascript:Go('${ctx}/register', 'register')"
								class="block"><em class="icon-6"></em> <span>免费注册</span> </a></li>
							<div>
								<img src="${resourceRoot }/web/ybb/assets/img/guanyu/aboutleftimg.png" width="230px">
							</div>
						</ul>
					</div>

					<div class="reg-container w-percent-70 right">
						<div class="ybb-md-ntc-signup"></div>
						<div class="module-content module-signup" style="overflow: hidden">
							<div class="module-notice module-notice-signup">${ctxMap['msg009']
								}
							</div>
							<form id="regForm" name="regForm" method="POST" action="${ctx}/doRegister">
				      <div class="ybb-signup-form ybb-try" style="padding-top:10px">
				        <div class="section">
				          <div class="ybb-md-tt">
				            <ul class="clear">
				              <li style="color: white">试玩账户注册</li>
				              <li style="color: white">已有账户登入</li>
				            </ul>
				          </div>
				          <mh:token></mh:token>
				          <div class="ybb-md-ct">
				            <ul>
				              <li>
				                <div class="form-section-item item">
				                  <label for="field-id">账号</label>
				                  <div class="form-section-content">
				                    <span>${userName}</span>
				                    <input type="hidden" id="testUserName" name="testUserName" value="${userName}" />
				                  </div>
				                </div>
				                <div class="form-section-item item">
				                  <label for="field-psw">密码</label>
				                  <div class="form-section-content">
				                    <input type="password" maxlength="12" id="password1" name="password1" value="" onblur="validTestPassword1(this)" class="ybb-ui-in">
				                    <font color="#ff0000">*</font> <span id="password1Info"></span>
				                    <p>须为6~12码英文或数字且符合0~9或a~z字元</p>
				                  </div>
				                </div>
				                <div class="form-section-item item">
				                  <label for="field-psw-verify">确认密码</label>
				                  <div class="form-section-content">
				                    <input type="password" id="password2" name="password2" onblur="validTestPassword2(this)" value="" maxlength="12" class="ybb-ui-in">
				                    <font color="#ff0000">*</font><span id="password2Info"></span>
				                    <p>须为6~12码英文或数字且符合0~9或a~z字元</p>
				                  </div>
				                </div>
				                <div class="item">
				                  <div class="item">
				                    <div class="form-section-content">
				                      <input type="checkbox" id="agree" style=" vertical-align: middle" checked="checked" style="background-color:none"> 我已届满合法博彩年龄﹐且同意各项开户条约。 <a href="javascript:void(0)" class="signup-agm">《開戶協議》</a>
				                    </div>
				                  </div>
				                  <div class="item">
				                    <div class="form-section-content">
				                      <input type="button" class="ui-button ui-submit" value="进入游戏" name="addnews" onclick="registerTestForm();return false;" style="margin-left: 30px;margin-top: 10px">
				                    </div>
				                  </div>
				                  <div class="signup-notice">
				                    <h6>备注：</h6>
				                    <ol>
				                      <li>带 * 号项为必填项</li>
				                      <li>注册账户为本网站试玩账户，可在试玩登录进行登录再次试玩。</li>
				                    </ol>
				                  </div>
				                </div>
				              </li>
				              <li>
				                <div class="form-section-item item">
				                  <label for="field-id">账号</label>
				                  <div class="form-section-content">
				                    <input type="text" maxlength="12" id="testLoginUserName" name="testLoginUserName" value="" onblur="validTestLoginName(this)" class="ybb-ui-in">
				                    <font color="#ff0000">*</font> <span id="testLoginUserNameInfo"></span>
				                    <p>须为6~12码英文或数字且符合0~9或a~z字元</p>
				                  </div>
				                </div>
				                <div class="form-section-item item">
				                  <label for="field-psw">密码</label>
				                  <div class="form-section-content">
				                    <input type="password" maxlength="12" id="testLoginPwd" name="testLoginPwd" value="" onblur="validTestLoginPassword(this)" class="ybb-ui-in">
				                    <font color="#ff0000">*</font> <span id="testLoginPwdInfo"></span>
				                    <p>须为6~12码英文或数字且符合0~9或a~z字元</p>
				                  </div>
				                </div>
				                <div class="form-section-item item">
				                  <label for="field-psw">验证码</label>
				                  <div class="form-section-content">
				                    <input type="text" name="testverifycode" id="testverifycode" value="" class="ybb-ui-in" size="4" maxlength="4" style="width: 45px;" onblur="validTestVerifycode(this)" />
				                    <span id="testverifycodeLabelId" onclick="javascript:createTestCode();"><img
				                  src="${resourceRoot}/web/ybb/assets/images/yzm.gif"
				                  id="img_testverifycode" style="vertical-align: middle">
				                </span>
				                    <font color="#ff0000">*</font><span id="testverifycodeInfo"></span>
				                  </div>
				                </div>
				                <div class="item">
				                  <div class="item">
				                    <div class="form-section-content">
				                      <input type="button" class="ui-button ui-submit" style="margin-left: 30px;margin-top: 10px" value="登录" onclick="loginTestForm();">
				                    </div>
				                  </div>
				                  <div class="signup-notice">
				                    <h6>备注：</h6>
				                    <ol>
				                      <li>带 * 号项为必填项</li>
				                    </ol>
				                  </div>
				                </div>
				              </li>
				            </ul>
				          </div>
				        </div>
				      </div>
				    </form>
							<!-- <form id="regForm" name="regForm" method="POST"
								action="${ctx}/doRegister">
								<div class="signup-form">
									<div class="form-section">
										<fieldset>
											<legend>注册账号</legend>
											<mh:token></mh:token>
											<div class="section-body">
												<div class="form-section-item">
													<label for="field-id">账号</label>
													<div class="form-section-content">
														<input type="text" name="userName" id="userName1"
															maxlength="10" onblur="validRegisterUserName(1,this)"
															value="" class="ybb-ui-in"> <font color="#ff0000">*</font><span
															id="userNameInfo"></span><br>
														<p>请输入6-10个字符,仅可输入英文字母以及数字的组合!</p>
													</div>
												</div>
												<div class="form-section-item">
													<label for="field-psw">密码</label>
													<div class="form-section-content">
														<input name="userPassword" type="password" maxlength="12"
															id="userPassword1" value=""
															onblur="validRegisterPassword(1,this)" size=""
															class="ybb-ui-in"> <font color="#ff0000">*</font>
														<span id="passwordInfo"></span> <br>
														<p>须为6~12码英文或数字且符合0~9或a~z字元</p>
													</div>
												</div>
												<div class="form-section-item">
													<label for="field-psw-verify">确认密码</label>
													<div class="form-section-content">
														<input name="rePwd" type="password" id="rePwd"
															onblur="validRegisterRepwd(1,this)" value="" size=""
															class="ybb-ui-in"> <font color="#ff0000">*</font><span
															id="rePwdInfo"></span>
													</div>
												</div>
												<div class="form-section-item">
													<label for="field-psw-verify">代理账号</label>
													<div class="form-section-content">
														<input name="userAgent" type="text" id="userAgent"
															value="888" size="" onblur="validUserAgent(this)"
															class="ybb-ui-in"> <span
															id="userAgentInfo"></span>
														<br>
														<p>推荐代理账号,默认账号(888)</p>
													</div>
												</div>
											</div>
										</fieldset>
									</div>
									<div class="form-section-item">
										<fieldset>
											<legend>会员资料</legend>

											<div class="section-body">
												<div class="form-section-item">
													<label for="field-name">真实姓名</label>
													<div class="form-section-content">
														<input name="userRealName" type="text" id="userRealName"
															onblur="validRegisterUserRealName(1,this)" value=""
															size="" class="ybb-ui-in"> <font color="#ff0000">*</font>
														<span id="userRealNameInfo"></span> <br>
														<p>真实姓名要和银行开户姓名“一致”</p>
													</div>
												</div>
												<c:if test="${ctxMap['swBirthday'] == '1'}">
													<div class="form-section-item">
														<label for="field-name">生日</label>
														<div class="form-section-content">
															<input type="hidden" id="needed_birthday"
																value="${ctxMap['swBirthday_1']}" /> <input
																name="birthday" type="text" id="birthday"
																onblur="validRegisterBirthday(1,this)" value="" size=""
																class="ybb-ui-in ybb-su-birthday" />
															<c:if test="${ctxMap['swBirthday_1'] == '1'}">
																<font color="#ff0000">*</font>
															</c:if>
															<span id="birthdayInfo"></span><br>
															<p>生日号码以身份证上的号码为准</p>
														</div>
													</div>
												</c:if>
											</div>

											<c:if test="${ctxMap['swmobile'] == '1'}">
												<div class="form-section-item">
													<label for="field-tel">手机号码</label>
													<div class="form-section-content">
														<input type="hidden" id="needed_userMobile"
															value="${ctxMap['swmobile_1']}" /> <input
															name="userMobile" type="text" id="userMobile" value=""
															size="" onkeyup="value=value.replace(/[^0-9]+/,'')"
															onblur="validRegisterUserMobile(1,this)" maxlength="11"
															class="ybb-ui-in" />
														<c:if test="${ctxMap['swmobile_1'] == '1'}">
															<font color="#ff0000">*</font>
														</c:if>
														<span id="userMobileInfo"></span><br>
														<p>我们将以手机形式确认提款，必需填写真实号码</p>
													</div>
												</div>
											</c:if>
											<c:if test="${ctxMap['swMail'] == '1'}">
												<div class="form-section-item">
													<label for="field-tel">邮箱</label>
													<div class="form-section-content">
														<input type="hidden" id="needed_userEmail"
															value="${ctxMap['swMail_1']}" /> <input name="userEmail"
															type="text" id="userEmail" value="" size=""
															onblur="validRegisterUserEmail(1,this)" maxlength="30"
															class="ybb-ui-in" />
														<c:if test="${ctxMap['swMail_1'] == '1'}">
															<font color="#ff0000">*</font>
														</c:if>
														<span id="userEmailInfo"></span>
													</div>
												</div>
											</c:if>
											<c:if test="${ctxMap['swQq'] == '1'}">
												<div class="form-section-item">
													<label for="field-qq">QQ号码</label>
													<div class="form-section-content">
														<input type="hidden" id="needed_userQq"
															value="${ctxMap['swQq_1']}" /> <input name="userQq"
															type="text" id="userQq"
															onkeyup="value=value.replace(/[^0-9]+/,'')"
															onblur="validRegisterUserQq(1,this)" value=""
															maxlength="15" class="ybb-ui-in" />
														<c:if test="${ctxMap['swQq_1'] == '1'}">
															<font color="#ff0000">*</font>
														</c:if>
														<span id="userQqInfo"></span><br> <label
															class="registerFont"></label>
													</div>
												</div>
											</c:if>

											<div class="form-section-item">
												<label for="field-mail">提款密码</label>
												<div class="form-section-content">
													<label for="userWithdrawPassword"></label> <select
														name="pwd1" id="pwd1" style="width: 40px;color:#000">
														<option label="-" value="-" selected="selected">-</option>
														<c:forEach var="num" begin="0" end="9" step="1">
															<option label="${num }" value="${num }">${num }</option>
														</c:forEach>
													</select> <select name="pwd2" id="pwd2"
														style="width: 40px;color:#000">
														<option label="-" value="-" selected="selected">-</option>
														<c:forEach var="num" begin="0" end="9" step="1">
															<option label="${num }" value="${num }">${num }</option>
														</c:forEach>
													</select> <select name="pwd3" id="pwd3"
														style="width: 40px;color:#000">
														<option label="-" value="-" selected="selected">-</option>
														<c:forEach var="num" begin="0" end="9" step="1">
															<option label="${num }" value="${num }">${num }</option>
														</c:forEach>
													</select> <select name="pwd4" id="pwd4"
														style="width: 40px;color:#000">
														<option label="-" value="-" selected="selected">-</option>
														<c:forEach var="num" begin="0" end="9" step="1">
															<option label="${num }" value="${num }">${num }</option>
														</c:forEach>
													</select> <font color="#ff0000">*</font><span id="withdrawInfo"></span>
													<label class="registerFont"> </label> <input
														name="userWithdrawPassword" type="hidden"
														id="userWithdrawPassword">
													<p>
														提款认证必须且是<span style="color:#F00">4位数字</span>的密码，请务必记住！
													</p>
												</div>
											</div>

											<!-- <div class="form-section-item">
												<div class="form-section-content">
													<input type="checkbox" id="agree"
														style=" vertical-align: middle" checked="checked"
														style="background-color:none">
													我已届满合法博彩年龄﹐且同意各项开户条约。 <a href="javascript:void(0)"
														class="signup-agm">《開戶協議》</a>
												</div>
											</div>
										</fieldset>
										<div class="form-section-item">
											<div class="form-section-content">
												<input type="submit" class="ui-button ui-submit" value="确认"
													name="addnews" onclick="registerForm();return false;">
											</div>
										</div>
									</div>
								</div>
							</form> -->
						</div>
						<!-- <div class="signup-notice">
							<h6>备注</h6>
							<ol>
								<li>带 * 号项为必填项</li> -->
								<!-- <li>若公司有其它活动会E-MAIL通知，请客户填写清楚。</li>
							</ol>
						</div> -->
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<style type="text/css">
	.ui-datepicker {
	  width: 260px;
	}
</style>
<script type="text/javascript">
function createTestCode() {
  $("#img_testverifycode").attr("src",rootPath+"/test-resources-code.jpg?" + new Date().getTime());
}
createTestCode();
</script>
<style>
	/* .ui-draggable .ui-dialog-titlebar {
    font-size: 16px!important;
    background: transparent none!important;
    border-radius: 0!important;
    border: medium none!important;
    border-bottom: solid 1px #494949!important;
    margin: 0 -3px!important;
    color: #FC3!important;
  }
  .ui-widget-content {
    z-index: 1111;
    }
  .ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ui-draggable.ui-resizable {
    background: #1c1c1c none;
    border: solid 3px #333333;
    border-radius: 8px;
    overflow: hidden;
  } */
	  .ui-widget-overlay {
	  z-index: 10000;
		}
		.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ui-draggable.ui-resizable {
		  background: #1c1c1c none;
		  border: solid 3px #333333;
		  border-radius: 8px;
		  overflow: hidden;
		}
		.ui-draggable .ui-dialog-titlebar {
		  font-size: 16px;
		  background: transparent none;
		  border-radius: 0;
		  border: medium none;
		  border-bottom: solid 1px #494949;
		  margin: 0 -3px;
		  color: #FC3;
		}
		.ui-draggable.newsAnomount .ui-dialog-titlebar .ui-dialog-title {
		  display: inline-block;
		  padding-right: 78px;
		  width: auto;
		}
		.ui-dialog .ui-dialog-titlebar-close {
		  border: medium none;
		  background: transparent none;
		  right: 10px;
		  width: 24px;
		  height: 24px;
		  outline: none;
		}
		.ui-state-default .ui-icon {
		  background: transparent none;
		}
		.ui-state-default .ui-icon.ui-icon-closethick {
		  background: transparent url("${resourceRoot }/web/ybb/common/css/images/icon_close.png") no-repeat center center;
		  width: 24px;
		  height: 24px;
		  margin: 0;
		  left: 0;
		  top: -3px;
		  outline: none;
		}
		.ui-dialog .ui-dialog-content {
		  padding: 5px;
		}
		.ui-dialog .ui-dialog-content * {
		  line-height: 28px;
		  font-size: 14px;
		  font-family: 'Microsoft YaHei', '\5FAE\8F6F\96C5\9ED1';
		  /*color: #896966;*/
		}
		.ui-dialog .ui-dialog-content p {
		  margin-bottom: 10px;
		}

</style>
<div class="signup-agm-content" style="background: white" title="務必詳細閱讀">
  ${ctxMap['msg010'] }
</div>
<div class="ybb-dia-si"></div>