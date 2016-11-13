<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div data-role="page" data-close-btn="right">
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<c:set var="entity" value="${eventList[0]}"></c:set>
	
	<script type="text/javascript">
	var matchType = "${bet.matchType}";
    var timeType = "${bet.timeType}";
    var rType = "${bet.rtype}";
    var period = "${bet.period}";
    var selection = "${bet.selection}";
    var btype = "${bet.btype}";
    var dtype = "${bet.dtype}";
    var gid = "${bet.gid}";
    
    /**period**/
	$("#period").text("半场/全场");
	$("#remark").text("${entity.teamH } VS ${entity.teamC }");
    
    /**btype**/
    if(btype == "H_H"){
		$("#b_type").text("主/主");
		$("#odd").text("${entity.iorFhh}");
		$("#team").text("${entity.teamH }/${entity.teamH } @ ");
	}else if(btype == "H_N"){
		$("#b_type").text("主/和");
		$("#odd").text("${entity.iorFhn}");
		$("#team").text("${entity.teamH }/和 @ ");
	}else if(btype == "H_C"){
		$("#b_type").text("主/客");
		$("#odd").text("${entity.iorFhc}");
		$("#team").text("${entity.teamH }/${entity.teamC } @ ");
	}else if(btype == "N_H"){
		$("#b_type").text("和/主");
		$("#odd").text("${entity.iorFnh}");
		$("#team").text("和/${entity.teamH } @ ");
	}else if(btype == "N_N"){
		$("#b_type").text("和/和");
		$("#odd").text("${entity.iorFnn}");
		$("#team").text("和/和 @ ");
	}else if(btype == "N_C"){
		$("#b_type").text("和/客");
		$("#odd").text("${entity.iorFnc}");
		$("#team").text("和/${entity.teamC } @ ");
	}else if(btype == "C_H"){
		$("#b_type").text("客/主");
		$("#odd").text("${entity.iorFch}");
		$("#team").text("${entity.teamC }/${entity.teamH } @ ");
	}else if(btype == "C_N"){
		$("#b_type").text("客/和");
		$("#odd").text("${entity.iorFcn}");
		$("#team").text("${entity.teamC }/和  @ ");
	}else if(btype == "C_C"){
		$("#b_type").text("客/客");
		$("#odd").text("${entity.iorFcc}");
		$("#team").text("${entity.teamC }/${entity.teamC } @ ");
	}
	</script>
	<%@ include file="order_common.jsp" %>
</div>
<!-- /page -->
