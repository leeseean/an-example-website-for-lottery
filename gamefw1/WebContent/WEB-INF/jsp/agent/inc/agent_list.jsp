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
			"action" : "${ctx}/agent/agentlist",
			"pageNo" : "1"
		});
		
	}
 
</script>
  <base target="_self">
</head>

<body class="ag-frame-content font-hei">
<form action="${ctx}/agent/agentlist" method="post" id="page_form">
	<input type="hidden" id="pageNo" name="pageNo" value="${page.pageNo }" />
	<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }" />
	<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages }" />

  <div class="ag-wrap">
    <table width="100%" class="ag-form">
      <thead>
        <tr>
          <th colspan="20">
            <div class="ag-form-head cf">
              <h2 class="col-one ele-title l">代理商信息</h2>
              <c:if test="${isAdmin }">
              <div class="ele-filter l">
                <ul class="cf">
                  <li>
                  	 账号：
                  	<input type="text" value="${param.agentName }" id="agentName" name="agentName" size="6" class="ui-write">
                  </li>
                  <li>
					<input type="button" onclick="goto_query();" value="查询" class="ui-btn"></li>
                </ul>
              </div>
              </c:if>
            </div>
          </th>
        </tr>
      </thead>
      <tbody>
        <tr class="ag-form-title b">
          <td >账户</td>
          <td >昵称</td>
          <td >会员数</td>
          <td >代理线地址</td>
          <td >开户日期</td>
          <td >状态</td>
          <td >操作</td>
        </tr>
        <c:forEach var="agent" items="${page.result}" varStatus="item_index">
        <tr class="ag-form-content">
          <td >${agent.user_name }</td>
          <td >${agent.user_real_name }</td>
          <td ><a href="${ctx }/agent/memberList?agentName=${agent.user_name}" class="block">${userMap[agent.user_name]==null?0:userMap[agent.user_name]}</a></td>
          <td >${agent.agent_url }</td>
          <td ><fmt:formatDate pattern="yyyy-MM-dd" value="${agent.create_time}" /> </td>
          <td ><span class="ico-state ${agent.status==1?'ico-state-active':'ico-state-unactive' }" title="${agent.status==1?"正常":"异常" }"></span></td>
          <td >
            <ul class="ele-ctrl cf">
              <li><a href="${ctx }/agent/memberList?agentName=${agent.user_name}" class="block">旗下会员</a></li>
            </ul>
          </td>
        </tr>
        </c:forEach>
      </tbody>
      <jsp:include page="/commons/agent/jsp/member_page_footer.jsp">
	   <jsp:param name="action" value="${ctx}/agent/agentlist" />
	   <jsp:param name="form_id" value="page_form" />
	   </jsp:include>
    </table>
  </div>
  </form>
</body>
<%@include file="comm_foot.jsp"%>
</html>
