
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<head>
<%@include file="/commons/member/jsp/member_common.jsp"%>

</head>
<body>
	<div class="wrapper">
		<div class="panel-content font-hei">
			<div class="panel-security">
				<div class="page-title clear">
					<div class="left">
						<h2 class="s24 blue">密码安全</h2>
						<h3 class="mt5 gray">密码安全与您的资金息息相关，切勿透露给他人</h3>
					</div>
					<div class="right mt10">
						<button class="button button-primary button-raised button-pill button-tiny btn-contact"><i></i>在线客服</button>
					</div>
				</div>
				<!-- /title -->
				<div class="page-body">
					<div class="switch-group">
						<div class="switch-item">
							<div class="page-body">
								<div class="panel-progress">
									<ul class="progress-step-group clear">
										<li class="step step-one step-active">
											<div class="step-inner"><span class="step-icon">1</span><h2>输入新密码</h2></div>
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
								<div class="switch-body-wrap">
									<div class="progress-body-item progress-body-step1">
										<div class="sheet-mod">
											<div class="sheet-content pt20">
												<table class="mt20 s14">
													<tr>
														<td width="160" class="pr20 ar gray-dark">旧密码</td>
														<td class="s12">
															<input name="oldPwd2" id="oldPwd2" maxlength="4"
																onKeyUp="value=value.replace(/[^0-9]+/,'')" type="password"
																class="write b red">
														</td>
													</tr>
													<tr>
														<td class="pr20 ar gray-dark">新密码</td>
														<td class="s12"> 
															<input name="newPwd2" id="newPwd2" maxlength="4"
																onKeyUp="value=value.replace(/[^0-9]+/,'')" type="password"
																class="write b red" />
															<span class="pl10 gray">请使用英文字母、数字来更新您的密码</span>
														</td>
													</tr>
													<tr>
														<td class="pr20 ar gray-dark">确认密码</td>
														<td class="s12"> 
															<input name="renewPwd2" id="renewPwd2" maxlength="4"
																onKeyUp="value=value.replace(/[^0-9]+/,'')" type="password"
																class="write b red" />
														</td>
													</tr>
													<tr>
														<td></td>
														<td>
															<button class="button button-raised button-primary button-small"
																onclick="checkWithdrawPwdForm();return false;">确认</button>
														</td>
													</tr>
												</table>
											</div>
										</div>
										<!-- /sheet -->
									</div>
									<div class="progress-body-item progress-body-step2"></div>
									<!-- /item -->
								</div>
							</div>
						</div>
						<!-- /item -->
					</div>
					<!-- /group -->
				</div>
				<!-- /body -->
			</div>
		</div>
	</div>
	<script src="${resourceRoot}/member/js/plugins.js"></script>
</body>