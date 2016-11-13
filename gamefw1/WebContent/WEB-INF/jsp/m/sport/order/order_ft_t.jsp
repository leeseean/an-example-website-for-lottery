<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div data-role="page" data-close-btn="right">
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<c:set var="entity" value="${eventList[0]}"></c:set>
	<script type="text/javascript">
	/**订单参数**/
	var matchType = "${bet.matchType}";
    var timeType = "${bet.timeType}";
    var rType = "${bet.rtype}";
    var period = "${bet.period}";
    var selection = "${bet.selection}";
    var btype = "${bet.btype}";
    var dtype = "${bet.dtype}";
    var gid = "${bet.gid}";
    
    /**btype**/
    $("#b_type").text("总入球");
    if(btype == "7up")
    	$("#b_type").text("7或以上");
    
    /**period**/
    if(period == "f")
		$("#period").text("全场");
    else if(period == "h")
    	$("#period").text("半场");
    
    $("#remark").text("${entity.teamH } VS ${entity.teamC }");
    if(btype == "7up"){
    	$("#team").text("7或以上  @ ");
    }else{
    	$("#team").text(btype + " @ ");
    }
    
    /**odd**/
    if(btype == "0_1")
   		$("#odd").text("${entity.iorT01}");
   	else if(btype == "2_3")
   		$("#odd").text("${entity.iorT23}");
   	else if(btype == "4_6")
   		$("#odd").text("${entity.iorT46}");
   	else
   		$("#odd").text("${entity.iorOver}");
    
	</script>
	
	<%@ include file="order_common.jsp" %>
</div>
<!-- /page -->
