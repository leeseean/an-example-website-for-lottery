<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="ybb-main ybb-sport-main">
      <div class="r1 mt20 spr-common"></div>
      <div class="r2">
        <p>每日支援全球超过5000种运动类型投注赛事，皇冠提供多元体育赛事投注，包含足球、棒球、篮球及橄榄球等世界主要体育专案，由专业的资讯管理团队提供最准确即时的赛事结

果与线上投注服务，更备有亚洲盘、欧洲盘、香港盘、印尼盘及马来盘等赔率盘口供玩家选择。</p>
      </div>
      <div class="r3">
      	<c:choose>
			<c:when test="${!empty webUser}">
        		<a href="javascript:void(0);" onclick="checkFlatSatus('${ctx }/m/sport/list','huangguan');" data-ajax="false" class="ui-btn ui-btn-f">皇冠体育</a>
        		<%--<a href="javascript:void(0);" onclick="checkFlatSatus('${ctx }/m/sb/goSb','sb');" data-ajax="false" class="ui-btn ui-btn-f">沙巴体育</a>--%>
        		<a href="javascript:void(0);" onclick="checkFlatSatus('${ctx}/m/game/forwardGame?gameType=sb','sb');" data-ajax="false" class="ui-btn ui-btn-f">沙巴体育</a>
        		</c:when>
       		<c:otherwise>
       			<a href="javascript:alert('请先登录!');void(0);" class="ui-btn ui-btn-f">皇冠体育</a>
       			<a href="javascript:alert('请先登录!');void(0);" class="ui-btn ui-btn-f">沙巴体育</a>
       		</c:otherwise>
		</c:choose>
      </div>
    </div>