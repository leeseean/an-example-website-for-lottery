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
        	<h2 class="col-one ele-title l">代理类型</h2>
        </div>
      </th>
    </tr>
	</thead>
	<tbody>
        <tr>
          <td width="300" class="ag-form-title pr10 b">名称</td>
          <td class="pl10 al">${agentType.type_name}</td>
        </tr>
        <tr>
	      	<c:set var="key1"> 
	        	<c:out value="${agentType.ty_tuiyong}" /> 
			</c:set>
			<c:set var="key2"> 
	        	<c:out value="${agentType.ty_tuishui}" /> 
			</c:set>
          <td class="ag-form-title pr10 b">退佣</td>
          <td class="pl10 al">
		  <a href="${ctx }/agent/agentOdds?id=${agentType.ty_tuiyong}&type=tuiyong">
          ${tuiyongMap[key1]}</a></td>
        </tr>
        <tr>
          <td class="ag-form-title pr10 b">退水</td>
          <td class="pl10 al">
          <a href="${ctx }/agent/agentOdds?id=${agentType.ty_tuishui}&type=tuishui">
          ${tuiShuiMap[key2]}</a></td>
        </tr>
        <tr>
          <td class="ag-form-title pr10 b">备注</td>
          <td class="pl10 al">${agentType.remark}</td>
        </tr>
		</tbody>
    </table>
  </div>
</body>
<%@include file="comm_foot.jsp"%>
</html>
