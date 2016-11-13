<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
change(10);

function change(obj) {
  var limitBet = parseFloat("${bet.limitBet }");
  var money = parseFloat(obj.value);
  if (isNaN(money) || money < 10) {
    money = 10;
    obj.value = money;
  } else if (money > limitBet) {
    money = limitBet;
    obj.value = money;
  }

  var odd = $("#odd").text();
  if (odd > 1)
    odd = odd - 1;
  var total = money * odd;
  $("#win_money").text(total.toFixed(3));
}

function doSubmit() {
  if(!checkSatus()){
	  return;
  }
  var orderMoney = parseFloat($("#money").val());
  var userMoney = parseFloat("${user.userMoney }");
  if (orderMoney > userMoney) {
    alert("您的余额不足！！");
    return;
  }
  var datas = {
    matchType: matchType,
    timeType: timeType,
    rType: rType,
    period: period,
    selection: selection,
    btype: btype,
    dtype: dtype,
    gid: gid,
    money: orderMoney
  };
  $("#doSubmit").html("");
  $.ajax({
    url: "${ctx}/m/sportOrders/doSubmit",
    type: "POST",
    data: datas,
    dataType: "json",
    timeout: 30000,
    success: function(data) {
      $.mobile.changePage("${ctx}/m/dialog/sport", {
        role: "dialog"
      });
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
</script>

<div data-role="header">
	<h1>投注单</h1>
</div>

<!-- 投注單 -->
<div data-role="content" class="ybm-form ybm-order">
	<div class="ui-grid-a user-detail">
		<div class="ui-block-a">用户名：${user.userName }</div>
		<div class="ui-block-b">
			余 额：<span style=" color: red;">${user.userMoney }</span>
		</div>
	</div>
	<ul data-role="listview" data-inset="true" class="order-item">
		<li data-role="list-divider" data-mini="true">${entity.league}</li>
		<li class="ui-mini"><strong id="b_type"></strong>&nbsp;-&nbsp;<strong style=" color: red;" id="period"></strong></li>
		<li id="remark"></li>
	</ul>
	<ul data-role="listview" style=" margin-top: 0;">
		<li data-role="list-divider" data-theme="e">
			<div class="ui-grid-a">
				<div class="ui-block-a"><span id="team"></span><span id="odd"></span></div>
				<div class="ui-block-b" style="text-align: right; display: none;">
					<label for="">自动接受最新赔率</label>
				</div>
			</div>
		</li>
		<li>
			<div data-role="fieldcontain" style=" padding: 0;">
				<label for="bet-order-11">交易金额：</label>
				<input type="number" pattern="[0-9]*" name="bet-order-11" id="money" value="10" onchange="change(this);">
			</div>
		</li>
		<li class="order-tips">
			<div class="order-tips-inner">
				<div class="ui-grid-c">
					<div class="ui-block-a">
						<strong >可赢金额：</strong>
					</div>
					<div class="ui-block-b">
						<span style=" color: red;" id="win_money"></span>
					</div>
					<div class="ui-block-a">单注最低：</div>
					<div class="ui-block-b">${bet.limitBetMin }</div>
					<div class="ui-block-c">单注最高：</div>
					<div class="ui-block-d">${bet.limitBet }</div>
				</div>
			</div>
		</li>
		<li>
			<div data-role="fieldcontain" style=" padding: 0; text-align: center;">
				<a href="javascript:void(0);" onclick="doSubmit();" id="doSubmit" data-role="button" data-corners="false"
					data-inline="true" data-icon="check" data-theme="b">
					确认
				</a>
			</div>
		</li>
	</ul>
</div>
<!-- /投注單 -->
