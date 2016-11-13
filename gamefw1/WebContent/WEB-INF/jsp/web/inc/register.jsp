<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mh" uri="http://www.mh.com/framework/tags"%>


<div class="row">
	<div class="wrapper">
		<div class="content-top-banner"></div>
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
			<div class="container clearfix inner-content">
				<div class="pages-help pages-help-reg">
					<div class="nav nav-left w-percent-30 left">
						<div class="h69"></div>
						<div class="sidemenu-body">
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
						</ul>
						</div>
						<div class="sidemenu-footer">
						</div>
					</div>

					<div class="reg-container w-percent-70 right">
						<div class="ybb-md-ntc-signup"></div>
						<div class="module-content module-signup">
							<div class="module-notice module-notice-signup">${ctxMap['msg009']
								}</div>
							<form id="regForm" name="regForm" method="POST"
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
														<p>请输入4-10个字符,仅可输入英文字母以及数字的组合!</p>
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

											<div class="form-section-item">
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
							</form>
						</div>
						<div class="signup-notice">
							<h6>备注</h6>
							<ol>
								<li>手机与取款密码为取款金额时的凭证,请会员务必填写详细资料。</li>
								<li>若公司有其它活动会E-MAIL通知，请客户填写清楚。</li>
							</ol>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- /article -->
<style type="text/css">
.ui-datepicker {
	width: 260px;
}
</style>
<div class="signup-agm-content" title="務必詳細閱讀">${ctxMap['msg010']}</div>
<div class="ybb-dia-si"></div>