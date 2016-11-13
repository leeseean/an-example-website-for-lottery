<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<head>
<%@ include file="/commons/member/jsp/member_common.jsp"%>
<body>
	<div class="wrapper">
		<div class="panel-content font-hei">
			<div class="panel-message">
				<div class="page-title clear">
					<div class="left">
						<h2 class="s24 blue">站内消息</h2>
						<p class="mt5 gray">方便会员得知我们的最新动态而设的服务功能</p>
					</div>
					<div class="right mt10">
						<button
							class="button button-primary button-raised button-pill button-tiny btn-contact">
							<i></i>在线客服
						</button>
					</div>
				</div>
				<div class="switch-group">
					<div class="switch-item">
						<div class="switch-body-wrap">
							<div class="sheet-mod">
								<div class="sheet-content pt20">
								<form id="form1" action="${ctx}/message/showMessage"method="POST">
									<table class="center">
										
											<tr class="sheet-title s14 b">
												<td width="80">标题</td>
												<td width="100">时间</td>
												<td width="100">内容</td>
											</tr>
											<tr>
												<td><b>${webMessage.msgTitle}</b>
												</td>
												<td><fmt:formatDate value="${webMessage.createTime}"
														pattern="yyyy-MM-dd HH:mm" /></td>
												<td>${webMessage.msgContent }</td>
											</tr>

											<tr>
												<td colspan="3" align="center"><input type="button"
													onclick="Go('${ctx}/message/listForUser');return false"
													name="button" id="button" value="返回" class="m_btn" /></td>
											</tr>
										
									</table>
									</form>
								</div>
								<!-- /record -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${resourceRoot}/member/js/plugins.js"></script>
</body>