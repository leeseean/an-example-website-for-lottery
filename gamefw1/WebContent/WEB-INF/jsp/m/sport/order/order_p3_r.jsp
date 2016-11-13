<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div data-role="page" data-close-btn="right">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<script type="text/javascript">
	/**订单参数**/
	var matchType = "${param['matchType']}";
    var timeType = "${param['timeType']}";
    var rType = "${param['rType']}";
    var period = "${param['period']}";
    var selection = "${param['selection']}";
    var btype = "${param['btype']}";
    var gid = "${param['gid']}";
	
	change(10);
    function change(obj) 
    {
    	var limitBet = parseFloat("${bet.limitBet }");
        var money = parseFloat(obj.value);
        if (isNaN(money) || money < 10) 
        {
            money = 10;
            obj.value = money;
        } 
        else if (money > limitBet) 
        {
            money = limitBet;
            obj.value = money;
        }
        
        var odd = $("#odd").text();
        if(odd > 1)
        	odd = odd - 1;
        var total = money * odd;
        $("#win_money").text(total.toFixed(3));
    }
    
    
    function deleteEvent(gid)
    {
    	var dates = {gid:gid};
    	$.ajax({
    		url : "${ctx }/m/sport/deleteEvent",
    		type: "post",
    		data : dates,
    		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
    		timeout : 30000,
    		dataType :"json",
    		cache : false,
    		success : function(obj) 
    		{
    			if(obj.rs == "0")
    			{
    				$("#odd").text(obj.odds.toFixed(2));
    				var money = $("#money").val();
    				var total = money * obj.odds;
    		        $("#win_money").text(total.toFixed(3));
    				if(obj.size < 3)
    					$("#listview").html("");
    			}
    		}
    	});
    }
    
    function checkSatus(){
    	var flag = false;
    	$.ajax({
    		url : rootPath+"/valid/checkflatsatus",
    		type: "post",
    		data : {"code":"huangguan"},
    		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
    		timeout : 30000,
    		dataType :"json",
    		cache : false,
    		async:false,
    		error:function(){
    			alert("网络连接有问题!");
    		},
    		success : function(obj) {
    			if (obj.datas.status) {
    				alert(obj.datas.msg);
    			}else{
    				flag = true;
    			}
    		}
    	}); 
    	return flag;
    }
    
    function doSubmit()
    {
    	if(!checkSatus()){
    		return;
    	}
    	
    	var orderMoney = $("#money").val();
    	var userMoney = "${user.userMoney }";
    	if(orderMoney > userMoney)
    	{
    		alert("您的余额不足！！");
    		return;
    	}
    	var datas = 
    	{
   		    matchType : matchType,
   		    timeType : timeType,
   		    rType : rType,
   		    period : period,
   		    selection : selection,
   		    btype : btype,
   		    gid : gid,
   		    money : orderMoney
    	};
    	$("#doSubmit").html("");
    	$.ajax( {
    		url : "${ctx}/m/sportOrders/doSubmit",
    		type : "POST",
    		data : datas,
    		dataType : "json",
    		timeout:30000,
    		success : function(data) 
    		{
    			$.mobile.changePage("${ctx}/m/dialog/sportp3", {role: "dialog"});
    		}
    	});
    }
	</script>
  <div data-role="header">
    <h1>投注单</h1>
  </div>
  <!-- 串關投注單 -->
  <div data-role="content" class="ybm-form ybm-order ybm-order-cg">
    <div class="ui-grid-a user-detail">
      <div class="ui-block-a">用户名：${user.userName }</div>
      <div class="ui-block-b">余　额：<span style=" color: red;">${user.userMoney }</span></div>
    </div>
    <c:forEach var="item" items="${orderParam }">
	    <ul data-role="listview" data-inset="true" class="order-item">
	      <li data-role="list-divider" data-mini="true">
	        ${item.value.league }
	        <a href="javascript:void();" onclick="deleteEvent('${item.value.gid}');$(this).parents('.order-item').hide();">×</a>
	      </li>
	      <li class="ui-mini">${item.value.btypeName } - <strong style=" color: red;">${item.value.periodName }</strong></li>
	      <li>${item.value.remark }</li>
	      <li data-theme="e">${item.value.teamName } @ ${item.value.odds }</li>
	    </ul>
    </c:forEach>
    <!-- /組 -->
    <c:if test="${size >= 3 }">
	    <ul data-role="listview" style=" margin-top: 0;" id="listview">
	      <li>
		      <div data-role="fieldcontain" style=" padding: 0;">
		      	<label for="bet-order-11">交易金额：</label>
		      	<input type="number" pattern="[0-9]*" name="bet-order-11" id="money" value="10" onchange="change(this);">
		      </div>
	      </li>
	      <li data-theme="e" style=" padding-left: 30px; font-size: 12px;">串关信息：@ <span id="odd">${odds}</span></li>
	      <li class="order-tips">
		      <div class="order-tips-inner">
			      <div class="ui-grid-c">
			      <div class="ui-block-a"><strong>可赢金额：</strong></div>
			      <div class="ui-block-b"><span style=" color: red;" id="win_money"></span></div>
			      <div class="ui-block-a">单注最低：</div>
			      <div class="ui-block-b">${bet.limitBetMin }</div>
			      <div class="ui-block-c">单注最高：</div>
			      <div class="ui-block-d">${bet.limitBet }</div>
			      </div>
		      </div>
	      </li>
	      <li>
	      <div data-role="fieldcontain" style=" padding: 0; text-align: center;">
	      	<a href="javascript:void(0);" data-rel="dialog" id="doSubmit" onclick="doSubmit();" data-transition="pop" data-role="button" data-corners="false" data-inline="true" data-icon="check" data-theme="b">确认</a>
	      </div>
	      </li>
	    </ul>
    </c:if>
  </div>
  <!-- /串關投注單 -->
</div>
<!-- /page -->