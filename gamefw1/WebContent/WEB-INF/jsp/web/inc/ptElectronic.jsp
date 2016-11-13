<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
		    <div class="sort cf">
		      <ul class="l cf">
		        <li><a href="${ctx}/electronic?code=pt&gameType1=Video Poker">电子扑克</a></li>
		        <li><a href="${ctx}/electronic?code=pt&gameType1=TableCard">桌面游戏</a></li>
		        <li><a href="${ctx}/electronic?code=pt&gameType1=Scratch Cards">刮刮卡</a></li>
		        <li><a href="${ctx}/electronic?code=pt&gameType1=Live Games">真人游戏</a></li>
		        <li><a href="${ctx}/electronic?code=pt&gameType1=Slot Machines">老虎机</a></li>
		        <li><a href="${ctx}/electronic?code=pt&gameType1=Arcade">街机游戏</a></li>
		        <li><a href="${ctx}/electronic?code=pt&gameType1=Mini Games">迷你游戏</a></li>
		        <li><a href="${ctx}/electronic?code=pt&gameType1=support" style=" font-weight: bold; color: #ff0;">累计奖池</a></li>
		      </ul>
		      <style>
		      .pt-jackpot-total {
		        float: right;
		        padding-right: 10px;
		        width: 230px;
		        height: 30px;
		      }
		
		      .pt-jackpot-total .c1 {
		        float: left;
		        line-height: 30px;
		        color: #000;
		      }
		      </style>
		      <div class="pt-jackpot-total">
		        <div class="c1">超级彩池：</div>
		        <div class="c2">
		          <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,0,0" width=160 height=30>
		            <param name=movie value="http://tickers.playtech.com/jackpots/new_jackpot.swf?info=4&font_color=ffff00&casino=winforfun88&local=0&font_face=arial&font_size=26&bold=true&currency=cny">
		            <param name=quality value=high>
		            <param name=bgcolor value=#000000>
		            <embed src="http://tickers.playtech.com/jackpots/new_jackpot.swf?info=4&font_color=ffff00&casino=winforfun88&local=0&font_face=arial&font_size=26&bold=true&currency=cny" quality=high bgcolor=#000000 width=160 height=30 type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash">
		            </embed>
		          </object>
		        </div>
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
		                  onclick="winOpen('${ctx}/forwardGame?gameType=ptElectronic&game=${item.eleGameCode}',window.screen.width,window.screen.height,0,0,'game','pt');return false;"
		                  href="javascript:void(0);">开始游戏</a> | <a target="_blank" onclick="comeToOpen('${ctx}/test/forwardGame?gameType=ptElectronic&gameCode=${item.eleGameCode}',window.screen.width,window.screen.height,0,0,'game','pt');return false;" href="javascript:void(0);">免费试玩</a>
		                </i>
		                </c:when>
		                <c:otherwise>
		                  <i><a href="javascript:void(0)"
		                  onclick="alert('您尚未登录，请先登录再进行游戏')">开始游戏</a> | <a target="_blank" onclick="comeToOpen('${ctx}/test/forwardGame?gameType=ptElectronic&gameCode=${item.eleGameCode}',window.screen.width,window.screen.height,0,0,'game','pt');return false;" href="javascript:void(0);">免费试玩</a>
		                </i>
		                </c:otherwise>
		              </c:choose>
		            </div>
		            <c:if test="${item.isSupport eq 1 && gameType1 eq 'support'}">
		              <div class="abs" id="progressive_${item.id}" style=" top: 118px; z-index: 8; height: 30px;">
		                <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,0,0" width=230 height=30>
		                  <param name=movie value="http://tickers.playtech.com/jackpots/new_jackpot.swf?info=1&casino=winforfun88&game=${item.progressiveGameCode}&font_color=ffff00&font_face=arial&bold=true&currency=CNY">
		                  <param name=quality value=high>
		                  <embed src="http://tickers.playtech.com/jackpots/new_jackpot.swf?info=1&casino=winforfun88&game=${item.progressiveGameCode}&font_color=ffff00&font_face=arial&bold=true&currency=CNY" quality=high bgcolor=#000000 width=230 height=30 type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash">
		                  </embed>
		                </object>
		              </div>
		            </c:if>
	                <c:choose>
					<c:when test="${empty item.eleGamePic}">
				 		<img class="lazy" width="220" height="130"  data-original="${resourceRoot}/web/ybb/common/images/pt/slot_thumb_null.png" src="${resourceRoot}/web/ybb/common/images/grey.gif" />
					</c:when>
					<c:otherwise>
						<img class="lazy" width="220" height="130"  data-original="${resourceRoot}/web/ybb/common/images/pt/${item.eleGamePic}" src="${resourceRoot}/web/ybb/common/images/grey.gif" />
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

