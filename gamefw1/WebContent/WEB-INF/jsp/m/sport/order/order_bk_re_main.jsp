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
    if(btype == "dy"){
    	$("#b_type").text("独赢");
    }else if(btype == "rf"){
    	$("#b_type").text("让分");
    }else if(btype == "dx"){
    	$("#b_type").text("大小");
    }else if(btype == "jf"){
    	$("#b_type").text("得分");
    }
    
    /**period**/
    $("#period").text(period == "f" ? "全场" : "半场");
    
    /**selection**/
    $("#team").text(selection == "H" ? "${entity.teamH } @ " : "${entity.teamC } @ ");
    
    if(btype == "dy"){
    	$("#odd").text(selection == "H" ? "${entity.iorMh}" : "${entity.iorMc}");
    	$("#remark").text("${entity.teamH } VS ${entity.teamC }");
    }else if(btype == "rf"){
    	$("#odd").text(selection == "H" ? "${entity.iorRh}" : "${entity.iorRc}");
    	if("${entity.strong}" == "H"){
	    	$("#remark").text("${entity.teamH } ${entity.ratio } ${entity.teamC }");
    	}else{
    		$("#remark").text("${entity.teamC } ${entity.ratio } ${entity.teamH }");
    	}
    }else if(btype == "dx"){
    	$("#remark").text("${entity.teamH } VS ${entity.teamC }");
    	$("#odd").text(selection == "H" ? "${entity.iorOuh}" : "${entity.iorOuc}");
    	$("#team").text(selection == "H" ? "大${entity.ratioU } @ " : "小${entity.ratioO } @ ");
    }else if(btype == "jf"){
    	if(dtype){
    		//得分大小
        	if(selection == "H"){
        		$("#period").text("${entity.teamH } - 大/小");
        		$("#team").text(dtype == "dx_big" ? "大${entity.ratioOuho } @ " : "小${entity.ratioOuhu } @ ");
        		$("#odd").text(dtype == "dx_big" ? "${entity.iorOuho}" : "${entity.iorOuhu}");
        	}else if(selection == "C"){
        		$("#period").text("${entity.teamC } - 大/小");
        		$("#team").text(dtype == "dx_big" ? "大${entity.ratioOuco } @ " : "小${entity.ratioOucu } @ ");
            	$("#odd").text(dtype == "dx_big" ? "${entity.iorOuco}" : "${entity.iorOucu}");
            }
    	}
    	$("#remark").text("${entity.teamH } VS ${entity.teamC }");
    }
	</script>
	<%@ include file="order_common.jsp" %>
</div>
<!-- /page -->
