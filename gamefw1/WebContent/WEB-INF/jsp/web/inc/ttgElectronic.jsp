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
			<div class="ybb-slot-bd ybb-slot-ttg w1000">
			  <div class="module-notice module-notice-slots" style="margin: 15px 10px 10px 10px;color: yellow;line-height: 22px;font-size: 14px"></div>
			  <div class="hall">
			    <ul class="clear">
					<c:forEach var="item" items="${slotSite}">
					<c:choose>
						<c:when test="${item.flat eq 'bbin' or item.flat eq 'ag'}">
					<c:choose>
					 <c:when test="${!empty webUser}">
					<li><a onclick="winOpen('${ctx}/${item.flatUrl }',window.screen.width,window.screen.height,0,0,'game','${item.flat }');return false;" href="javascript:void(0);" target="_blank">${item.flatName }</a></li>
					</c:when>
					<c:otherwise>
					  <li><a href="javascript:void(0)" onclick="alert('您尚未登录，请先登录再进行游戏')">${item.flatName }</a></li>
					  </c:otherwise>
					</c:choose>
						</c:when>
						<c:otherwise>
						<li class="${item.flat eq code?'active':'' }"><a href="${ctx}/${item.flatUrl}">${item.flatName}</a></li>
					     </c:otherwise>
					</c:choose>
					</c:forEach>
			    </ul>
			  </div>
			  <div class="sort clear">
			    <ul class="left clear">
			      <li><a href="${ctx}/electronic?code=ttg&gameType1=Table games">桌面游戏</a></li>
			      <li><a href="${ctx}/electronic?code=ttg&gameType1=Slot">老虎机</a></li>
			      <li><a href="${ctx}/electronic?code=ttg&gameType1=Card games">卡牌游戏</a></li>
			      <li><a href="${ctx}/electronic?code=ttg&gameType1=Video Poker">视频扑克</a></li>
			      <li><a href="${ctx}/electronic?code=ttg&gameType1=Soft games">soft游戏</a></li>
			    </ul>
				<div class="ybb-slot-jp-total">
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
											<i><a target="_blank" onclick="winOpen('${ctx}/forwardGame?gameType=ttg&gameName=${item.eleGameEnname}&gameId=${ item.eleGameId}&eleGameType=${item.eleGameType2 }',window.screen.width,window.screen.height,0,0,'game','ttg');return false;" href="javascript:void(0);">开始游戏</a>
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
												src="http://www.tickerassist.co.uk/ProgressiveTickers/include/js/ProgressiveTickersControl.js?progid=${item.eleGameName}&font-color=white&font-family=verdana&showlogo=no&currency=CNY"></script>
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
					                <img class="lazy" width="230" height="150" data-original="${resourceRoot}/web/ybb/common/images/ttg/${item.eleGamePic}.jpg" src="${resourceRoot}/web/ybb/common/images/grey.gif" />
					              </c:when>
					              <c:otherwise>
					                <a class="gm-ic-mg lazy" data-original="${resourceRoot}/web/ybb/common/images/ttg/${item.eleGameId}.png" style="background-image: url(${resourceRoot}/web/ybb/common/images/grey.gif);background-size: 100%;background-position: 0px -6px;"></a>
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
