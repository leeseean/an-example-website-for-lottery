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
		<div class="ag-wrap">
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
					<div class="ag-form-head cf">
						<h2 class="col-one ele-title l"></h2>
					</div>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td width="60">序号</td>
				<td width="100" >退水起始金额</td>
				<td width="100" >退水结束金额</td>
				<td width="130" >皇冠体育退佣</td>
				<td width="100" >彩票退佣</td>
				<td width="100" >AG退佣</td>
				<td width="110" >BBIN退佣</td>
				<td width="100" >MG退佣</td>
				<td width="100" >HG退佣</td>
				<td width="100" >DS退佣</td>
				<td width="100" >NT退佣</td>
				<td width="100" >PT退佣</td>
				<td width="100" >欧博退佣</td>
				<td width="100" >OG退佣</td>
				<td width="100" >OS退佣</td>
				<td width="100" >SB退佣</td>
				<td width="100" >GD退佣</td>
				<td width="100" >TTG退佣</td>
				<td width="100" >SBT退佣</td>
				<td width="100" >SA退佣</td>
			</tr>
			<c:forEach var="item" items="${list}">
			<tr >
				<td>${item_index.index+1 }</td>
				<td>${item.tyBegin }</td>
				<td>${item.tyEnd }</td>
				<td>${item.tySport}%</td>
				<td>${item.tyCaipiao}%</td>
				<td>${item.tyAg}%</td>
				<td>${item.tyBbin}%</td>
				<td>${item.tyMg}%</td>
				<td>${item.tyHg}%</td>
				<td>${item.tyDs}%</td>
				<td>${item.tyNt}%</td>
				<td>${item.tyPt}%</td>
				<td>${item.tyAb}%</td>
				<td>${item.tyOg}%</td>
				<td>${item.tyOs}%</td>
				<td>${item.tySb}%</td>
				<td>${item.tyGd}%</td>
				<td>${item.tyTtg}%</td>
				<td>${item.tySbt}%</td>
				<td>${item.tySa}%</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</body>
<%@include file="comm_foot.jsp"%>
</html>
