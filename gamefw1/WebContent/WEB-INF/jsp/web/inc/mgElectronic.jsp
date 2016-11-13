<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<link rel="Stylesheet" href="http://www.tickerassist.co.uk/ProgressiveTickers/include/css/ProgressiveTickers.css" />

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
		<div class="content-body">
			<div class="ybb-slot-bd w1000">
				<div class="hall">
					<ul class="clear">
						<c:forEach var="item" items="${slotSite }">
						<li class="${ item.flat eq  code ?  'active': '' }">
							<c:choose>
								<c:when test="${item.flat eq 'bbin' }">
									<c:choose>
										<c:when test="${!empty webUser}">
											<a
												onclick="winOpen('${ctx}/forwardGame?gameType=bbin&pageSite=game',window.screen.width,window.screen.height,0,0,'game','bbin');return false;"
												href="javascript:void(0);" target="_blank" class="block">
										</c:when>
										<c:otherwise>
											<a href="javascript:void(0);"
												onclick="alert('您尚未登录，请先登录再进行游戏')" class="block">
										</c:otherwise>
									</c:choose>BBIN电游</a>
								</c:when>
								
								<c:when test="${item.flat  eq 'ag'}">
									<c:choose>
										<c:when test="${!empty webUser}">
											<a
												onclick="winOpen('${ctx}/forwardGame?gameType=ag&agGameType=2',window.screen.width,window.screen.height,0,0,'game','ag');return false;"
												href="javascript:void(0);" class="block" target="_blank">
										</c:when>
										<c:otherwise>
											<a href="javascript:void(0);"
												onclick="alert('您尚未登录，请先登录再进行游戏')" class="block">
										</c:otherwise>
									</c:choose>AG电游</a>
								</c:when>
								
								<c:otherwise>
									<a href="${ctx}/${item.flatUrl}" class="block">${item.flatName }</a>
								</c:otherwise>
							</c:choose>
						</li>
						</c:forEach>
					</ul>
				</div>
				<div class="sort clear">
					<ul class="clear left">
						<li><a href="${ctx}/electronic?code=mg&gameType1=Table">桌面游戏</a>
						</li>
						<li><a href="${ctx}/electronic?code=mg&gameType1=Slots">老虎机</a>
						</li>
						<li><a href="${ctx}/electronic?code=mg&gameType1=NewSlots">新老虎机</a>
						</li>
						<li><a href="${ctx}/electronic?code=mg&gameType1=Video">视频扑克</a>
						</li>
						<li><a href="${ctx}/electronic?code=mg&gameType1=Qt">其他游戏</a>
						</li>
						<li><a href="${ctx}/electronic?code=mg&gameType1=Pot"
							style=" font-weight: bold; color: #ff0;">累计彩池</a>
						</li>
					</ul>
					<div class="ybb-slot-jp-total">
						<span class="left">现金彩池：</span>
						<script type="text/javascript"
							src="http://www.tickerassist.co.uk/ProgressiveTickers/include/js/ProgressiveTickersControl.js?currency=USD&fontcolor=black&fontfamily=verdana&font-size=14"></script>
						<style>
							.ybb-slot-jp-total {
								float: right;
								height: 30px;
								line-height: 30px;
								font-size: 14px;
								font-weight: bold;
								color: black;
							}
							
							#progressiveTickerjackpot {
								float: left;
							}
							
							#upperbox {
								display: none;
							}
							
							#jackpotstotal {
								width: auto;
							}
						</style>
					</div>
				</div>
				<div class="group">
					<div class="section clear">
						<c:forEach var="item" items="${result}" varStatus="status">
							<div class="item">
								<div class="title abs">
									<h6>${item.eleGameCnname}</h6>
									<div class="bg-black abs"></div>
								</div>
								<div class="overlay abs">
									<c:choose>
										<c:when test="${!empty webUser}">
											<i><a target="_blank"
												onclick="winOpen('${ctx}/forwardGame?gameType=mgElectronic&gameName=${item.eleGameId}&gameCode=${item.remark }',window.screen.width,window.screen.height,0,0,'game','mg');return false;"
												href="javascript:void(0);">开始游戏</a>
											</i>
										</c:when>
										<c:otherwise>
											<i><a href="javascript:void(0);"
												onclick="alert('您尚未登录，请先登录再进行游戏');">开始游戏</a>
											</i>
										</c:otherwise>
									</c:choose>
								</div>
								<c:if test="${gameType1 eq 'Pot' }">
									<div class="title abs" id="${item.eleGameId}"
										style=" margin-top: 120px;">
										<h6>
											<script type="text/javascript"
												src="http://www.tickerassist.co.uk/ProgressiveTickers/include/js/ProgressiveTickersControl.js?progid=${item.eleGameName}&font-color=yellow&font-family=verdana&showlogo=no&currency=CNY"></script>
										</h6>
										<div class="bg-black abs"></div>
										<script type="text/javascript">
	                function reflashPot() {
	                  var index = Math.floor(Math.random() * 1.6) + 0.9;
	                  var id = "progressive" + '${item.eleGameName}';
	                  setInterval(function() {
	                    var step = Math.floor(Math.random() * 6 + 1);
	                    var s = document.getElementById(id).value;
	                    var priceVal = parseFloat(s.replace(/[^0-9-]/g, '')); // 1234599
	                    priceVal = (priceVal + step) / 100;
	                    //alert(priceVal)
	                    /* if(s.indexOf("$")>-1){
	                      s= s.replace("$","");
	                    } */
	                    var num = formatMoney(priceVal, 2, "$");
	                    document.getElementById(id).value = num;
	                  }, 1000 * index);
	                }
	                reflashPot();
	                </script>
									</div>
								</c:if>
								<c:choose>
									<c:when test="${gameType1 eq 'Pot' }">
										<img class="lazy" width="230" height="150"
											data-original="${resourceRoot}/web/ybb/common/images/mg/${item.eleGamePic}"
											src="${resourceRoot}/web/ybb/common/images/grey.gif" />
									</c:when>
									<c:otherwise>
										<a class="gm-ic-mg lazy"
											data-original="${resourceRoot}/web/ybb/common/images/mg/${item.eleGamePic}"
											style="background-image: url(${resourceRoot}/web/ybb/common/images/grey.gif)"></a>
									</c:otherwise>
								</c:choose>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

