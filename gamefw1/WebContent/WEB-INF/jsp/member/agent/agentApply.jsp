<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">

<html>
<%@include file="/commons/member/jsp/member_common.jsp"%>
<script type="text/javascript">
	function goParent(url) {
		parent.document.getElementById('memberFrame').src = url;
	}
</script>
<body>
<form id="form1" action="${ctx}/member/insertApply"method="POST">
	<div class="wrapper">
		<div class="panel-content">
			<div class="panel-record">
				<div class="page-title clear">
					<div class="left">
						<h2 class="s24 blue">代理申请</h2>
					</div>
					<div class="right mt10">
						<button
							class="button button-primary button-raised button-pill button-tiny btn-contact">
							<i></i>在线客服
						</button>
					</div>
				</div>
				<div class="page-body switch-mod">
					<!-- 是代理資格 -->
					<div class="switch-menu s16">
						<ul class="center clear">
							<li class="item on" onclick="goParent('${ctx}/member/agentDetail')">代理信息</li>
						</ul>
						<div class="switch-group">
							<div class="switch-item">
								<div class="switch-body-wrap">
									<div class="sheet-mod">
										<div class="sheet-content pt20">
											
												<table class="s14">
													<tr class="sheet-title s14 b" align="center">
														<td colspan="5">
														您还不是代理会员！申请：认真填写下列表单，否则我们将无法审核您的代理资格！</td>
													</tr>
													<tr>
														<td class="pr20 ar gray">代理账号：</td>
														<td><font color="#0000FF">${webUser.userName }</font></td>
													</tr>
													<tr>
													<tr>
														<td class="pr20 ar gray"style="width:100px">真实姓名：</td>
														<td><font color="#0000FF">${webUser.userRealName} </font></td>
													</tr>
													<tr>
														<td class="pr20 ar gray">银行账号：</td>
														<td style="font-size: 14px; color: #F00; font-weight: bold">${webUser.userBankCard }</td>
													</tr>
													<tr>
														<td class="pr20 ar gray">开户行地址：</td>
														<td><font color="#0000FF">${webUser.userBankAddress}</font></td>
													</tr>
													<tr>
														<td class="pr20 ar gray">电话号码：</td>
														<td>${webUser.userMobile}</td>
													</tr>
													<tr>
													<td class="pr20 ar gray">代理类型：</td>
													<td>
														<select name="agentType" id="agentType" >
															<option value="1">退佣类型</option>
															<option value="2">退水类型</option>
														</select>
													</td>
												</tr>
													<tr>
														<td class="pr20 ar gray">邮箱地址：</td>
														<td colspan="2" >
															<input name="agentMail" style="background-color:white;height: 25px;width: 180px;" type="text" id="agentMail" value="" maxlength="30" />
															<font color="red">*</font> 和您联系的重要渠道,请用正确的邮箱
														</td>
													</tr>
													<tr>
														<td class="pr20 ar gray">申请理由：</td>
														<td colspan="2" >
															<textarea name="content" rows="11" cols="22" id="content"style="height: 100px"></textarea>
															<p><font color="red">*</font>简单说明您成为代理后将如何推广！这将是我们是否通过您的代理资格的重要信息！</p>
														</td>
													</tr>
													
													<tr>
														<td colspan="2" align="left" style="padding-left: 160px;">
															<button class="button button-raised button-primary button-small" onclick="agentApplayCheck();return false;">确认录入</button>
														</td>
													</tr>
													
											</table>
											
									</div>
								</div>
							</div>
							<!-- /sheet -->

							<!-- /item -->
						</div>
						<!-- /group -->
					</div>
					<!-- /body -->
				</div>
			</div>
		</div>
	</div>
	</div>
</form>
	<script src="${resourceRoot}/member/js/plugins.js"></script>
</body>