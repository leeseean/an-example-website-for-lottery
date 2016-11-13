<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>

<div class="row">
	<div class="wrapper">
		<div class="content-top-banner"></div>
	</div>
</div>

<div class="row">
	<div class="wrapper">
		<div class="header-msg">
			<%@include file="msg.jsp" %>
		</div>
	</div>
</div>

<div class="row">
	<div class="wrapper">
		
		<!-- 彩票主体 -->
		<div class="content-body">
			<div class="main w1000 align-h">
				<div class="lotto-items">
				  <ul class="clearfix">
						<li>
							<div class="loteo-item clearfix">
								<img src="${resourceRoot}//web/ybb/assets/img/lotteryxh/01.png" alt="" />
								<div class="rinfo">
									<c:choose>
										<c:when test="${!empty webUser }">
											<a href="javascript:void(0)" onclick="winOpen('/lottery',window.screen.width,window.screen.height,0,0,'彩票','caipiao')">
										</c:when>
										<c:otherwise>
											<a href="javascript:void(0)" plugin-alert="您尚未登录，请先登录再进行游戏">
										</c:otherwise>
									</c:choose>
										<h3>六合彩</h3>
										<span>LIUHECAI</span>
										<b class="play-btn">立即游戏>></b>
									</a>
								</div>
							</div>
						</li>
						<li>
							<div class="loteo-item clearfix">
								<img src="${resourceRoot}//web/ybb/assets/img/lotteryxh/02.png" alt="" />
								<div class="rinfo">
									<c:choose>
										<c:when test="${!empty webUser }">
											<a href="javascript:void(0)" onclick="winOpen('/lottery',window.screen.width,window.screen.height,0,0,'彩票','caipiao')">
										</c:when>
										<c:otherwise>
											<a href="javascript:void(0)" plugin-alert="您尚未登录，请先登录再进行游戏">
										</c:otherwise>
									</c:choose>
										<h3>福彩3D</h3>
										<span>FUCAI3D</span>
										<b class="play-btn">立即游戏>></b>
									</a>
								</div>
							</div>
						</li>
						<li>
							<div class="loteo-item clearfix">
								<img src="${resourceRoot}//web/ybb/assets/img/lotteryxh/03.png" alt="" />
								<div class="rinfo">
									<c:choose>
										<c:when test="${!empty webUser }">
											<a href="javascript:void(0)" onclick="winOpen('/lottery',window.screen.width,window.screen.height,0,0,'彩票','caipiao')">
										</c:when>
										<c:otherwise>
											<a href="javascript:void(0)" plugin-alert="您尚未登录，请先登录再进行游戏">
										</c:otherwise>
									</c:choose>
										<h3>排列三</h3>
										<span>PAILIE3</span>
										<b class="play-btn">立即游戏>></b>
									</a>
								</div>
							</div>
						</li>
						<li>
							<div class="loteo-item clearfix">
								<img src="${resourceRoot}//web/ybb/assets/img/lotteryxh/04.png" alt="" />
								<div class="rinfo">
									<c:choose>
										<c:when test="${!empty webUser }">
											<a href="javascript:void(0)" onclick="winOpen('/lottery',window.screen.width,window.screen.height,0,0,'彩票','caipiao')">
										</c:when>
										<c:otherwise>
											<a href="javascript:void(0)" plugin-alert="您尚未登录，请先登录再进行游戏">
										</c:otherwise>
									</c:choose>
										<h3>重庆时时彩</h3>
										<span>CHONGQINGSHISHICAI</span>
										<b class="play-btn">立即游戏>></b>
									</a>
								</div>
							</div>
						</li>
						<li>
							<div class="loteo-item clearfix">
								<img src="${resourceRoot}//web/ybb/assets/img/lotteryxh/05.png" alt="" />
								<div class="rinfo">
									<c:choose>
										<c:when test="${!empty webUser }">
											<a href="javascript:void(0)" onclick="winOpen('/lottery',window.screen.width,window.screen.height,0,0,'彩票','caipiao')">
										</c:when>
										<c:otherwise>
											<a href="javascript:void(0)" plugin-alert="您尚未登录，请先登录再进行游戏">
										</c:otherwise>
									</c:choose>
										<h3>江西时时彩</h3>
										<span>JIANGXISHISHICAI</span>
										<b class="play-btn">立即游戏>></b>
									</a>
								</div>
							</div>
						</li>
						<li>
							<div class="loteo-item clearfix">
								<img src="${resourceRoot}//web/ybb/assets/img/lotteryxh/06.png" alt="" />
								<div class="rinfo">
									<c:choose>
										<c:when test="${!empty webUser }">
											<a href="javascript:void(0)" onclick="winOpen('/lottery',window.screen.width,window.screen.height,0,0,'彩票','caipiao')">
										</c:when>
										<c:otherwise>
											<a href="javascript:void(0)" plugin-alert="您尚未登录，请先登录再进行游戏">
										</c:otherwise>
									</c:choose>
										<h3>新疆时时彩</h3>
										<span>XJSHISHICAI</span>
										<b class="play-btn">立即游戏>></b>
									</a>
								</div>
							</div>
						</li>
						<li>
							<div class="loteo-item clearfix">
								<img src="${resourceRoot}//web/ybb/assets/img/lotteryxh/07.png" alt="" />
								<div class="rinfo">
									<c:choose>
										<c:when test="${!empty webUser }">
											<a href="javascript:void(0)" onclick="winOpen('/lottery',window.screen.width,window.screen.height,0,0,'彩票','caipiao')">
										</c:when>
										<c:otherwise>
											<a href="javascript:void(0)" plugin-alert="您尚未登录，请先登录再进行游戏">
										</c:otherwise>
									</c:choose>
										<h3>天津时时彩</h3>
										<span>TJSHISHICAI</span>
										<b class="play-btn">立即游戏>></b>
									</a>
								</div>
							</div>
						</li>
						<li>
							<div class="loteo-item clearfix">
								<img src="${resourceRoot}//web/ybb/assets/img/lotteryxh/08.png" alt="" />
								<div class="rinfo">
									<c:choose>
										<c:when test="${!empty webUser }">
											<a href="javascript:void(0)" onclick="winOpen('/lottery',window.screen.width,window.screen.height,0,0,'彩票','caipiao')">
										</c:when>
										<c:otherwise>
											<a href="javascript:void(0)" plugin-alert="您尚未登录，请先登录再进行游戏">
										</c:otherwise>
									</c:choose>
										<h3>广东快乐十分</h3>
										<span>GDKUAILESHIFEN</span>
										<b class="play-btn">立即游戏>></b>
									</a>
								</div>
							</div>
						</li>
						<li>
							<div class="loteo-item clearfix">
								<img src="${resourceRoot}//web/ybb/assets/img/lotteryxh/09.png" alt="" />
								<div class="rinfo">
									<c:choose>
										<c:when test="${!empty webUser }">
											<a href="javascript:void(0)" onclick="winOpen('/lottery',window.screen.width,window.screen.height,0,0,'彩票','caipiao')">
										</c:when>
										<c:otherwise>
											<a href="javascript:void(0)" plugin-alert="您尚未登录，请先登录再进行游戏">
										</c:otherwise>
									</c:choose>
										<h3>天津快乐十分</h3>
										<span>TJKUAILESHIFEN</span>
										<b class="play-btn">立即游戏>></b>
									</a>
								</div>
							</div>
						</li>
						<li>
							<div class="loteo-item clearfix">
								<img src="${resourceRoot}//web/ybb/assets/img/lotteryxh/10.png" alt="" />
								<div class="rinfo">
									<c:choose>
										<c:when test="${!empty webUser }">
											<a href="javascript:void(0)" onclick="winOpen('/lottery',window.screen.width,window.screen.height,0,0,'彩票','caipiao')">
										</c:when>
										<c:otherwise>
											<a href="javascript:void(0)" plugin-alert="您尚未登录，请先登录再进行游戏">
										</c:otherwise>
									</c:choose>
										<h3>北京PK10</h3>
										<span>BEIJINGPK10</span>
										<b class="play-btn">立即游戏>></b>
									</a>
								</div>
							</div>
						</li>
				  </ul>
				</div>
			</div>
		</div>
		<!-- / 彩票主体 -->

	</div>
</div>


