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
							<c:if test="${webAgent.status eq 1}">
							<li class="item" onclick="goParent('${ctx}/member/agentEnterInfor')">代理入口</li>
							</c:if>
 						</ul>
					</div>
					<div class="switch-group">
						<div class="switch-item">
							<div class="switch-body-wrap">
								<div class="sheet-mod">
									<div class="sheet-content pt20">
										<form id="form1" action="${ctx}/member/agentDetail"method="POST">
											<table class="s14">
												<tr class="sheet-title s14 b" align="center">
													<td colspan="2" >
														${webAgent.status!=1?"您的代理申请还在审核中，您可以主动联系我们客服加快审核过程。":"您的代理线正常。"}
													</td>
												</tr>

												<tr>
													<td  class="pr20 ar gray" style="width: 160px;">推广地址：</td>
													<td><font color="#0000FF">${webAgent.status==1?webAgent.agentUrl:""}</font></td>
												</tr>

												<tr>
													<td class="pr20 ar gray">代理账号：</td>
													<td>${webAgent.userName }</td>
												</tr>
												<tr>
													<td class="pr20 ar gray">真实姓名：</td>
													<td>${webAgent.userRealName }</td>
												</tr>
												<tr>
													<td class="pr20 ar gray">银行账号：</td>
													<td style="font-size: 14px; color: #F00; font-weight: bold">
														${webUser.userBankCard }</td>
												</tr>
												<tr>
													<td class="pr20 ar gray">开户行地址：</td>
													<td>${webUser.userBankAddress }</td>
												</tr>
												<tr>
													<td class="pr20 ar gray">电话号码：</td>
													<td>${webUser.userMobile}</td>
												</tr>
												<tr>
													<td class="pr20 ar gray">邮箱地址：</td>
													<td>${webAgent.agentMail }</td>
												</tr>
												<tr>
													<td class="pr20 ar gray">申请理由：</td>
													<td>${webAgent.content }</td>
												</tr>
												<tr>
													<td class="pr20 ar gray">会员数：</td>
													<td>${webAgent.count }</td>
												</tr>
												<tr>
													<td class="pr20 ar gray">代理类型：</td>
													<td>${1 eq webAgent.agentType ? '退佣类型' :"退水类型" }</td>
												</tr>
												<tr>
													<td class="pr20 ar gray">最后登录IP：</td>
													<td>${webUser.userLastLoginIp }</td>
												</tr>
											</table>
											</from>
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
	<script src="${resourceRoot}/member/js/plugins.js"></script>
</body>