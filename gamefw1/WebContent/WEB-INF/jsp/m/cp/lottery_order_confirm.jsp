<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="mh" uri="http://www.mh.com/framework/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div data-role="page" data-close-btn="none">
  <div data-role="header">
    	<script src="${resourceRoot}/m/js/comm.js"></script>
  
    <h1>投注确认</h1>
  </div>
  <div data-role="content" class="ybm-order-confirm">
  <form id="order_detail_form" action="${ctx }/" method="post" >
	 <mh:token></mh:token>
	        
	<input type="hidden" id="gameTypeCode" name="gameTypeCode" value="${gameTypeCode }" />
	<input type="hidden" id="cpTypeCode" name="cpTypeCode" value="${cpTypeCode }" />
	<input type="hidden" id="minMoney" name="minMoney" value="${cpType.gminSingle }" />
	<input type="hidden" id="maxMoney" name="maxMoney" value="${cpType.gmaxSingle }" />
	<input type="hidden" id="maxMoney" name="maxMoney" value="${cpType.gmaxSingle }" />
	<input type="hidden" id="bigtypeCode" name="bigtypeCode" value="${cpType.bigtypeCode }" />
	<input type="hidden" id="maxSumMoney" name="maxSumMoney" value="${cpType.singleCredit }" />
	<input type="hidden" id="tmsb" name="tmsb" value="${tmsb }" />
    <ul data-role="listview">
      <li>
        <div class="ui-grid-a">
          <div class="ui-block-a">用户名：${userName }</div>
          <div class="ui-block-b">余　额：¥<fmt:formatNumber value="${userMoney }" type="currency" pattern="#,#00.0#"/></div>
        </div>
      </li>
 	<c:forEach var="order" items="${orderList }" varStatus="st">
      <li>
      <input type="hidden" name="number[]" id="${order.cfgId }" value="${order.xzje }" />
     	${order.content }
      </li>
      <li data-role="list-divider" data-theme="e">
        下注：<strong class="red">${order.xzje }</strong>　可赢金额：<strong class="red">${order.kyje }</strong>
      </li>
   </c:forEach>
      <li>
        总投注：<strong class="red">${total }</strong>
      </li>
      <li class="tac">
          <fieldset data-role="controlgroup" data-type="horizontal" data-corners="false">
            <a href="javascript:" onclick="saveOrder('order_detail_form',this);" data-rel="dialog" data-transition="pop" data-role="button" data-icon="check" data-theme="b" data-ajax="false">确认</a>
            <a href="" data-role="button" data-rel="back" data-icon="back">取消</a>
          </fieldset>
        
      </li>
      <li>
        <p>最低限额：<strong>${cpType.gminSingle }</strong></p>
        <p>单注限额：<strong>${cpType.gmaxSingle }</strong></p>
        <p>总注限额：<strong>${cpType.singleCredit }</strong></p>
        
      </li>
    </ul>
    </form>
  </div>
</div>
