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
    }else if(btype == "rq"){
    	$("#b_type").text("让球");
    }else if(btype == "dx"){
    	$("#b_type").text("大小");
    }else if(btype == "ds"){
    	$("#b_type").text("单双");
    }
    
    /**period**/
    if(period == "f"){
		$("#period").text("全场");
    }else if(period == "h"){
    	$("#period").text("上半场");
    }
    
    /**selection**/
    if(selection == "H"){
    	if(btype == "ds"){
    		$("#team").text("单 @ ");
    	}else if(btype == "dx"){
    		if(period == "f"){
	    		$("#team").text("大 ${entity.ratioU } @ ");
    		}else if(period == "h"){
    			$("#team").text("大 ${entity.hratioU } @ ");
    		}
    	}
    	else{
	    	$("#team").text("${entity.teamH } @ ");
    	}
    }else if(selection == "C"){
    	if(btype == "ds"){
    		$("#team").text("双 @ ");
    	}else if(btype == "dx"){
    		if(period == "f"){
	    		$("#team").text("小 ${entity.ratioU } @ ");
    		}else if(period == "h"){
    			$("#team").text("小 ${entity.hratioO } @ ");
    		}
    	}else{
    		$("#team").text("${entity.teamC } @ ");
    	}
    }else{
    	$("#team").text("和局   @ ");
    }
    
    var remark = "";
    /**odd**/
    if(btype == "dy"){
    	if(period == "f"){
    		if(selection == "H"){
            	$("#odd").text("${entity.iorMh}");
    		}else if(selection == "C"){
            	$("#odd").text("${entity.iorMc}");
            }else{
            	$("#odd").text("${entity.iorMn}");
            }
    	}else{
    		//半场
    		if(selection == "H"){
            	$("#odd").text("${entity.iorHmh}");
    		}else if(selection == "C"){
            	$("#odd").text("${entity.iorHmc}");
            }else{
            	$("#odd").text("${entity.iorHmn}");
            }
    	}
    	remark = "${entity.teamH } VS ${entity.teamC }";
    }else if(btype == "rq"){
        if(period == "f"){
        	if(selection == "H"){
            	$("#odd").text("${entity.iorRh}");
        	}else if(selection == "C"){
            	$("#odd").text("${entity.iorRc}");
            }
        	if("${entity.strong}" == "H"){
        		remark = "${entity.teamH } ${entity.ratio } ${entity.teamC }";
        	}else if("${entity.strong}" == "C"){
        		remark = "${entity.teamC } ${entity.ratio } ${entity.teamH }";
        	}
    	}else{
        	if(selection == "H"){
            	$("#odd").text("${entity.iorHrh}");
        	}else if(selection == "C"){
            	$("#odd").text("${entity.iorHrc}");
            }
        	if("${entity.hstrong}" == "H"){
        		remark = "${entity.teamH } ${entity.hratio } ${entity.teamC }";
        	}else if("${entity.hstrong}" == "C"){
        		remark = "${entity.teamC } ${entity.hratio } ${entity.teamH }";
        	}
        }
    }else if(btype == "dx"){
        if(period == "f"){
        	if(selection == "H"){
        		$("#odd").text("${entity.iorOuc}");
        	}
            else if(selection == "C"){
            	$("#odd").text("${entity.iorOuh}");
            }
    	}else{
        	if(selection == "H"){
        		$("#odd").text("${entity.iorHouc}");
        	}else if(selection == "C"){
            	$("#odd").text("${entity.iorHouh}");
            }
        }
        remark = "${entity.teamH } VS ${entity.teamC }";
    }else if(btype == "ds"){
        if(selection == "H"){
        	$("#odd").text("${entity.iorEoo}");
        }else if(selection == "C"){
        	$("#odd").text("${entity.iorEoe}");
        }
        remark = "${entity.teamH } VS ${entity.teamC }";
    }
    $("#remark").text(remark);
	</script>
	<%@ include file="order_common.jsp" %>
</div>
<!-- /page -->
