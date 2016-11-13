<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">

<html>
	<%@include file="/commons/member/jsp/member_common.jsp"%>
	<body>
		<div class="ui-moneyBox">
			<ul>
				<table width="100%" border="0" cellspacing="0" cellpadding="5">
					<form id="form1" action="/member/index!withdraw.do" method="POST">
						
						<tr>
							<td colspan="2" align="center">
								<font color="red"><b>请先完善您的取款银行卡信息，再进行下一步操作。</b></font>
							</td>
						</tr>
						<tr>
							<td align="right">
								会员账号：
							</td>
							<td>
								${user.userName }
							</td>
						</tr>
						<tr>
							<td width="150" align="right">
								收款人姓名：
							</td>
							<td>
								<font style="font-size: 14px; color: #F00; font-weight: bold">${user.userRealName }</font>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="left" bgcolor="#384967"
								style="font-size: 14px; color: #FF0">
								以下为您的收款信息，一旦录入将无法修改，请认真填写！
							</td>
						</tr>
						<tr>
							<td align="right">
								收款银行：
							</td>
							<td>
								<select id="userBankName" name="userBankName"
									style="height: 26px; border: 1px solid #d7d7d7; margin-top: 2px;">
									<option value="中国银行">
										中国银行
									</option>
									<option value="工商银行">
										工商银行
									</option>
									<option value="农业银行">
										农业银行
									</option>
									<option value="建设银行">
										建设银行
									</option>
									<option value="邮政银行">
										邮政银行
									</option>
									<option value="招商银行">
										招商银行
									</option>
									<option value="民生银行">
										民生银行
									</option>
									<option value="兴业银行">
										兴业银行
									</option>
									<option value="中信银行">
										中信银行
									</option>
									<option value="交通银行">
										交通银行
									</option>
									<option value="华厦银行">
										华厦银行
									</option>
									<option value="广发银行">
										广发银行
									</option>
									<option value="光大银行">
										光大银行
									</option>
									<option value="平安银行">
										平安银行
									</option>
									<option value="浦发银行">
										浦发银行
									</option>
									<option value=" 农村信用社">
										 农村信用社
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">
								银行账号：
							</td>
							<td>
								<input type="text" name="userBank" id="userBank" size="30"
						onKeyUp="value=value.replace(/[^0-9]+/,'')">
							</td>
						</tr>
						<tr>
							<td align="right">
								开户行地址：
							</td>
							<td>
								<input type="text" name="userBankProvince" id="userBankProvince" size="30">省份   
								<input type="text" name="userBankCity" id="userBankCity" size="30">市/县
							</td>
						</tr>
						<tr>
							<td align="right">
								请输入您的资金密码：
							</td>
							<td>
								<input type="password" maxlength="4" id="userWithdrawPassword" name="userWithdrawPassword"
									class="Input_100" />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="left" style="padding-left: 160px;">
								<input type="button" onclick="bankSaveCheck()" name="button" id="button" value="确认录入"
									class="m_btn" />
							</td>
						</tr>
					</form>
				</table>
			</ul>
		</div>
	</body>
</html>