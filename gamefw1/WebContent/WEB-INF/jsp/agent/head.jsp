<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="${resourceRoot}/agent/css/normalize.css">
<link rel="stylesheet" href="${resourceRoot}/agent/css/base.css">
<link rel="stylesheet" href="${resourceRoot}/agent/css/layout.css">
<script src="${resourceRoot}/agent/js/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">

function loginOut(){
	$.ajax({
		    url: "${ctx}/agent/loginOut",
		    type: "post",
		    data: null,
		    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		   	dataType: "json",
		    error: function(){
		        window.location.href="${ctx}/agent/index";
		    },
		    success: function(obj){
		    	 window.location.href="${ctx}/agent/index";
			}
		});
}


</script>
<base target="frameContent">
</head>

<body class="ag-frame-head font-hei">
  <div class="header">
    <div class="inner cf">
      <h1 class="logo l"><a href="javascript:void(0);" class="block"></a></h1>
      <div class="header__nav l">
        <ul class="cf">
          <li class="nav__item">
            <a href="javascript:void(0);" class="nav__link block">账户管理</a>
            <ul class="nav__sub cf">
              <li><a onclick="javascript:void(0);" href="${ctx}/agent/agentlist" target="frameContent" >代理商</a></li>
            </ul>
          </li>
          <c:if test="${isAdmin ne true }">
          <li class="nav__item">
            <a href="javascript:void(0);" class="nav__link block">个人管理</a>
            <ul class="nav__sub cf">
              <li><a onclick="javascript:void(0);" href="${ctx}/agent/goAgentLogList" target="frameContent">操作日志</a></li>
              <li><a onclick="javascript:void(0);" href="${ctx}/agent/goAccountUpdate" target="frameContent" >账户信息 </a></li>
            </ul>
          </li>
          </c:if>
          <li class="nav__item">
            <a href="javascript:void(0);" class="nav__link block">报表管理</a>
            <ul class="nav__sub cf">
              <%-- <li><a onclick="javascript:void(0);" href="${ctx}/agent/goAgentReport" target="frameContent" >交收报表</a></li> --%>
              <li><a onclick="javascript:void(0);" href="${ctx}/agent/agentReport?code=1" target="frameContent" >本月代理报表</a></li>
              <li><a onclick="javascript:void(0);" href="${ctx}/agent/agentReport?code=2" target="frameContent" >历史代理报表</a></li>
              <%-- <li><a onclick="javascript:void(0);" href="${ctx}/agent/goUserReport" target="frameContent">会员报表</a></li> --%>
            </ul>
          </li>
          
          <li class="nav__item">
            <a href="javascript:void(0);" class="nav__link block">代理信息</a>
            <ul class="nav__sub cf">
               <c:if test="${isAdmin ne true }">
               <li><a onclick="javascript:void(0);" href="${ctx}/agent/agentType" target="frameContent">代理类型</a></li>
               </c:if>
              <li><a onclick="javascript:void(0);" href="${ctx}/agent/goCenter?code=agentEquation" target="frameContent" >佣金算法 </a></li>
            </ul>
          </li>
        </ul>
      </div>
      <div class="ctrl r">
        <ul>
          <li class="group cf">
            <span class="item item-un block">账号：<strong>${user.userName}</strong></span>
            <span class="item block">总代理</span>
            <span class="bg"></span>
          </li>
          <li class="mt10 cf"><a href="javascript:void(0);" onclick="loginOut()" class="btn-exit r block ac">退出</a></li>
        </ul>
      </div>
    </div>
  </div>
</body>
<script type="text/javascript" src="${resourceRoot}/agent/js/jquery.min.js"></script>
<script type="text/javascript" src="${resourceRoot}/agent/js/custom.js"></script>

</html>
