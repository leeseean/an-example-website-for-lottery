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
    $("#b_type").text("波胆");
    if(btype == "other"){
    	$("#b_type").text("其他");
    }
    	
    
    /**period**/
    if(period == "f"){
		$("#period").text("全场");
    } else if(period == "h"){
    	$("#period").text("上半场");
    }
    
    $("#remark").text("${entity.teamH } VS ${entity.teamC }");
    if(btype == "other"){
    	$("#team").text("其他比分 @ ");
    }else{
    	$("#team").text(btype + " @ ");
    }
    
    /**odd**/
    if(btype == "1_0"){
   		$("#odd").text("${entity.iorH1c0}");
   	}else if(btype == "2_0"){
   		$("#odd").text("${entity.iorH2c0}");
   	}else if(btype == "2_1"){
   		$("#odd").text("${entity.iorH2c1}");
   	}else if(btype == "3_0"){
   		$("#odd").text("${entity.iorH3c0}");
   	}else if(btype == "3_1"){
   		$("#odd").text("${entity.iorH3c1}");
   	}else if(btype == "3_2"){
   		$("#odd").text("${entity.iorH3c2}");
   	}else if(btype == "4_0"){
   		$("#odd").text("${entity.iorH4c0}");
   	}else if(btype == "4_1"){
   		$("#odd").text("${entity.iorH4c1}");
   	}else if(btype == "4_2"){
   		$("#odd").text("${entity.iorH4c2}");
   	}else if(btype == "4_3"){
   		$("#odd").text("${entity.iorH4c3}");
   	}else if(btype == "0_1"){
   		$("#odd").text("${entity.iorH0c1}");
   	}else if(btype == "0_2"){
   		$("#odd").text("${entity.iorH0c2}");
   	}else if(btype == "1_2"){
   		$("#odd").text("${entity.iorH1c2}");
   	}else if(btype == "0_3"){
   		$("#odd").text("${entity.iorH0c3}");
   	}else if(btype == "1_3"){
   		$("#odd").text("${entity.iorH1c3}");
   	}else if(btype == "2_3"){
   		$("#odd").text("${entity.iorH2c3}");
   	}else if(btype == "0_4"){
   		$("#odd").text("${entity.iorH0c4}");
   	}else if(btype == "1_4"){
   		$("#odd").text("${entity.iorH1c4}");
   	}else if(btype == "2_4"){
   		$("#odd").text("${entity.iorH2c4}");
   	}else if(btype == "3_4"){
   		$("#odd").text("${entity.iorH3c4}");
   	}else if(btype == "0_0"){
   		$("#odd").text("${entity.iorH0c0}");
   	}else if(btype == "1_1"){
   		$("#odd").text("${entity.iorH1c1}");
   	}else if(btype == "2_2"){
   		$("#odd").text("${entity.iorH2c2}");
   	}else if(btype == "3_3"){
   		$("#odd").text("${entity.iorH3c3}");
   	}else if(btype == "4_4"){
   		$("#odd").text("${entity.iorH4c4}");
   	}else if(btype == "other"){
   		$("#odd").text("${entity.iorOvh}");
   	}
	</script>
	<%@ include file="order_common.jsp" %>
</div>
<!-- /page -->
