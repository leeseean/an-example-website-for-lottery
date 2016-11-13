<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title></title>
  <%@include file="common.jsp"%>
  <script type="text/javascript">
	
	function goto_query() {
	 	$.form_submit($("#page_form").get(0), {
			"action" : "${ctx}/agent/memberList",
			"pageNo" : "1"
		});
	}
 
</script>
</head>
<body class="ag-frame-content font-hei">
	<form action="${ctx}/agent/memberList" method="post" id="page_form">
		<input type="hidden" id="pageNo" name="pageNo" value="${page.pageNo }" />
		<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages }" />
	
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
              <h2 class="col-one ele-title l">会员管理</h2>
              <div class="ele-filter l">
                <ul class="cf">
                  <li>
                  	 账号：
                  	 <input type="hidden" name="agentName" id="agentName" value="${param.agentName }">
                  	<input type="text" value="${param.username }" id="username" name="username" size="6" class="ui-write">
                  </li>
                  <li>
					<input type="button" onclick="goto_query();" value="查询" class="ui-btn"></li>
                </ul>
              </div>
            </div>
          </th>
        </tr>
      </thead>
     
      <tbody>
        <tr class="ag-form-title b">
          <td width="60">序号</td>
          <td >账户</td>
          <td >昵称</td>
          <td >余额</td>
          <td >入款</td>
          <td >出款</td>
		  <td >登录信息</td>
          <td >开户日期</td>
        </tr>
        <c:forEach var="item" items="${page.result}" varStatus="s">
        	<tr class="ag-form-content">
       		<td>${s.index+1 }</td>
        	<td>${item.user_name }</td>
        	<td>${item.user_real_name }</td>
        	<td>${item.user_money}</td>
        	<td>${item.inMoney}</td>
        	<td>${item.outMoney}</td>
        	<td><fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${item.user_last_login_time}" /> | ${item.user_last_login_ip }</td>
          	<td ><fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${item.create_time}" /></td>
        	</tr>
        </c:forEach>
      </tbody>
      <jsp:include page="/commons/agent/jsp/member_page_footer.jsp">
		<jsp:param name="action" value="${ctx}/agent/memberList" />
		<jsp:param name="form_id" value="page_form" />
		</jsp:include>
    </table>
  </div>
</form>
</body>
<%@include file="comm_foot.jsp"%>
</html>
