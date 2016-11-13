<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title></title>
  <%@include file="common.jsp"%>
  <base target="_self">
</head>

<body class="ag-frame-content font-hei">
  <div class="ag-wrap ag-edit">
    <table width="100%" class="ag-form">
    <thead>
	    <tr>
	      <th colspan="19">
	        <div class="ag-form-head cf" align="left">
	        	<button onclick="goBack();" class="ui-btn ml10">返回</button>
	        </div>
	      </th>
	    </tr>
	  </thead>
   	<thead>
    <tr>
      <th colspan="19">
        <div class="ag-form-head cf" align="left">
        	<h2 class="col-one ele-title l">${betFlat }馆</h2>
        </div>
      </th>
    </tr>
	</thead>
	<tbody>
        <tr>
          <td width="300" class="ag-form-title pr10 b">会员输赢</td>
          <td class="pl10 al">
          <a href="${ctx }/agent/gotoUserRecords?qs=${detail.bet_qs}&agentName=${detail.bet_user_agent}&betFlat=${detail.bet_flat}" />
          ${detail.bet_usr_win>0?"<font color=red>":"<font color=green>"} <fmt:formatNumber value="${detail.bet_usr_win }" pattern="#0.00#"/> </td>
        </tr>
        <tr>
          <td class="ag-form-title pr10 b">退佣比例</td>
          <td class="pl10 al">${detail.ty_rate }%</td>
        </tr>
        <tr>
          <td class="ag-form-title pr10 b">有效投注</td>
          <td class="pl10 al"><fmt:formatNumber value="${detail.bet_income }" pattern="#0.00#"/></td>
        </tr>
        <tr>
          <td class="ag-form-title pr10 b">退水比例</td>
          <td class="pl10 al">${detail.ts_rate }%</td>
        </tr>
        <tr>
          <td class="ag-form-title pr10 b">会员返水</td>
          <td class="pl10 al">${detail.water_back}</td>
        </tr>
        <tr>
          <td class="ag-form-title pr10 b">行政费用</td>
          <td class="pl10 al">${detail.xz_fee}</td>
          
        </tr>
        <tr>
          <td class="ag-form-title pr10 b">佣金</td>
          <td class="pl10 al">${detail.comms_total}</td>
        </tr>
		</tbody>
    </table>
    <br>
	<div class="ag-form-head cf" align="left">
		<h2 class="col-one ele-title l">  注：</h2>
		
    	<p style="font-size: 14px; color:#444;" id="formula"></p>
		<p style="font-size: 14px;color:#444;" id="feeTotal"></p>
    </div>
    <script type="text/javascript">
    //平台
    var betFlat = "${betFlat}";
    if(betFlat)
    {
    	var flatName = betFlat;
    	 
    	if(betFlat == 'sport')
    	{
    		flatName="皇冠体育";
    	}
    	else if(betFlat == 'caipiao')
    	{
    		flatName="彩票";
    	}
    	else if(betFlat == 'douji')
    	{
    		//斗鸡
    		flatName="斗鸡";
    	}else if(betFlat == 'ab')
    	{
    		//欧博
    		flatName="欧博";
    	}else if(betFlat == 'sa')
    	{
    		//沙龙
    		flatName="沙龙";
    	}
    	$("#formula").html("公式计算:"+flatName+"输赢总和 - "+flatName+"输赢总和  x "+flatName+"行政费比例  - 会员返水 x 行政费比例 = 实际盈利,实际盈利x退佣比例+"+flatName+"有效投注x退水比例=代理佣金");
    }
    var xzfy = "${xzfy}"/100;					//行政费用比例
    var usrWin = "${detail.bet_usr_win}";					//会员输赢
    if(usrWin < 0)
   	{
    	usrWin = usrWin * -1;
   	}
    var waterBack = "${detail.water_back}";					//会员返水
    var tyRate = "${detail.ty_rate }"/100;		//退佣比例
    var income = "${detail.bet_income }";					//有效投注
    var tsRate = "${detail.ts_rate }"/100;		//退水比例
    var innerChar = "合计:";
    var sum = usrWin - (usrWin * xzfy) - waterBack;
    innerChar += usrWin + "-" + "(" + usrWin + "*" + xzfy + ")" + "-" + waterBack + "=" + sum.toFixed(2) + ",",
    innerChar += sum.toFixed(2) + "*";
    sum = sum * tyRate + income * tsRate;
    innerChar += tyRate.toFixed(2) + "+" + income + "*" + tsRate.toFixed(2) + "=" + sum.toFixed(2);
   // $("#feeTotal").html(innerChar);
    </script>
  </div>
</body>
<%@include file="comm_foot.jsp"%>
</html>
