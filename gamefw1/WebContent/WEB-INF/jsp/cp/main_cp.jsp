<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>彩票</title>
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp">
<%@include file="/commons/cp/jsp/common_cp.jsp"%>

</head>
<body>
	<div class="ybb-lotto">
		<div class="lotto-header">
			<div class="inner">
				<div class="pure-g lotto-top cf">
					<div class="pure-u-1-2 c1 cf">
						<!--  
				        <div class="ui-head l b">系统公告</div>
				        <div class="ui-body l">
				          <ul class="cf">
				            <li><a href="javascript:void(0);">公告内容 1</a></li>
				            <li><a href="javascript:void(0);">公告内容 2</a></li>
				          </ul>
				        </div>-->
					</div>
					<div class="pure-u-1-2 c2">
						<ul class="cf">
							<li class="ui-item"><a href="javascript:void(0)" id="xzmx" class="ui-link">下注明细</a></li>
							<li class="ui-item"><a href="javascript:void(0)" class="ui-link" id="yzbb">一周报表</a></li>
							<li class="ui-item"><a href="javascript:void(0)" class="ui-link" id="lskj">历史开奖</a></li>
							<li class="ui-item"><a href="javascript:void(0)" class="ui-link" onclick="openGameRule()" >游戏规则</a></li>
						</ul>
					</div>
					<!-- /c2 -->
				</div>
				<!-- /top -->
				<div class="lotto-nav">
					<div class="nav-main" id="memu">
						<div class="nav-group">
							<ul class="cf">
								<%--一级菜单 --%>
								<c:forEach var="item" items="${cpmenu1 }">
									<li class="ui-item" id="${item.key }"><a href="javascript:void(0);" class="ui-link">${item.value }</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
					<!-- /main -->
					<%-- 二级菜单 --%>
					<div class="nav-child" id="memu_two">
						<c:forEach items="${cpmenu2}" var="item">
							<div class="nav-group" id="${item.key }_1">
								<ul class="cf">
									<c:forEach items="${item.value }" var="itemList">
										<c:if test="${itemList.isEnable eq 1 }"><%-- 启用状态 --%>
											<li class="ui-item" id="${itemList.gameTypeCode }">
												<a class="ui-link"><div class="r1" gpid="${itemList.id }">${itemList.gameTypeName }</div><div class="r2">--:--</div> </a>
											</li>
										</c:if>
									</c:forEach>
	·							</ul>
							</div>
						</c:forEach>
						<!-- /group -->
					</div>
					<!-- /child -->
				</div>
				<!-- /nav -->
			</div>
			<!-- /inner -->
		</div>
		<!-- /Header -->
		<!-- lotto-body -->
		<div class="lotto-body">
			<div class="pure-g inner">
				<div class="pure-u-1" id="memu_three">
					<div class="nav-mode" id="betmenu_data" >
						<%-- 三级菜单 --%>
						<c:forEach items="${cpmenu3 }" var="item" varStatus="status">
							<ul class="cf" id="${item.key }_1" <c:if test="${status.index != 0 }">style="display: none;"</c:if>>
								<c:forEach items="${item.value }" var="itemList" >
									<li><a href="javascript:void(0);"  p="${itemList.p }">${itemList.cpTypeName }</a></li>
								</c:forEach>
							</ul>
						</c:forEach>
					</div>
				</div>
				<div id="topHtml" style="width:100%;"></div>
				<div class="pure-u-6-24" id="leftHtml">
					<div class="sidebar">
						<div class="ui-mod ui-form user-info">
							<div class="ui-table">
								<div class="ui-table-loop">
									<div class="ui-head-son">
										<div class="pure-g">
											<div class="pure-u-2-5">
												<div class="ui-item-son">盘口</div>
											</div>
											<div class="pure-u-3-5">
												<div class="ui-item-son">余额</div>
											</div>
										</div>
									</div>
									<div class="ui-body-son">
										<div class="pure-g">
											<div class="pure-u-2-5">
												<div class="ui-item-son b red">(A盘)</div>
											</div>
											<div class="pure-u-3-5">
												<div class="ui-item-son b red" id="userBalance">0.0</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="ui-mod ui-tabs">
							<div class="ui-mod ui-tabs">
								<div class="ui-mod-head ui-tabs-head">
									<ul class="cf">
										<li class="ui-item ui-item-first ui-active"><a
											id="left_menu_0" tmpid="fastbet" href="javascript:void(0);"
											onclick="changeLeftMenu(0);" class="ui-link">快捷下注</a>
										</li>
										<li class="ui-item"><a id="left_menu_1"
											tmpid="transRecord" href="javascript:void(0);"
											onclick="changeLeftMenu(1);" class="ui-link">最近交易记录</a>
										</li>
									</ul>
								</div>
								<div class="ui-mod-body ui-tabs-body" id="menu_left"></div>
							</div>
						</div>
						<!-- /Sidebar -->
					</div>
				</div>
				<div class="pure-u-18-24" id="rightHtml">
					<div id="betplay_data" class="lotto-main">
						<div class="loading">
							<div class="uil-loading">
								<div></div>
								<div></div>
							</div>
							<p></p>
						</div>
					</div>
					<!-- /Main -->
				</div>
			</div>
			<!-- /inner -->
			
			<!--  rule -->
				<%@include file="rule.jsp" %>
			<!-- /rule -->
		</div>
		<!-- /lotto-body -->
	</div>
	<!-- /Lott -->

</body>
</html>


