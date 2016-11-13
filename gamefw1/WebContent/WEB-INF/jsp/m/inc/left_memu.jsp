<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 左側快捷菜單 -->
	<div class="ybm-panel-header" style=" text-align: right;">
		<div data-role="controlgroup" data-type="horizontal" data-mini="true" data-theme="b">
			<a href="${ctx }/m/wap/member" data-ajax="false" data-role="button" data-icon="home" data-iconpos="notext"></a>
			<a href="" data-rel="close" data-role="button" data-icon="delete" data-iconpos="notext"></a>
		</div>
	</div>
	<ul data-role="listview" data-inset="true" data-corners="false" data-icon="false" class="ui-mini">
		<li data-role="list-divider">会员中心</li>
		<li>
			<a href="${ctx }/m/wap/member#/messages" data-ajax="false">个人资料</a>
		</li>
		<li>
			<a href="${ctx }/m/wap/member#/user/update/password" data-ajax="false">密码安全</a>
		</li>
		<li>
			<a href="javascript:void(0);" onclick="mobile_loginOut();">注销</a>
		</li>

		<li data-role="list-divider">会员消息</li>
		<li>
			<a href="${ctx }/m/wap/member#/messages" data-ajax="false">短信息</a>
		</li>
		<li data-role="list-divider">财务中心</li>
		<li>
			<a href="${ctx }/m/wap/member#/deposit/account" data-ajax="false" >公司入款</a>
		</li>
		<li>
			<a href="${ctx }/m/wap/member#/thidpay/wechat" data-ajax="false">微信支付</a>
		</li>
		<li>
			<a href="${ctx }/m/wap/member#/thidpay/alipay" data-ajax="false">支付宝支付</a>
		</li>
		<li>
			<a href="${ctx }/m/wap/member#/withdrawal/account" data-ajax="false">在线提款</a>
		</li>
		<li><a href="${ctx }/m/wap/member#/conversion" data-ajax="false">额度转换</a></li>
	</ul>
<!-- /左側快捷菜單 -->