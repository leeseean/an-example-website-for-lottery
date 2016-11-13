<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>皇冠体育</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${resourceRoot}/sport/css/mem_order_sel.css" rel="stylesheet" type="text/css">
<link href="${resourceRoot}/sport/css/mem_order_olympics.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${resourceRoot}/sport/js/jquery.min.js"></script>
<script type="text/javascript">
	function betOrder(gid, timeType, matchType, rtype, btype, dtype, selection,
			period) {
		$.ajax({
			url : "${ctx}/valid/checkUserLogin",
			type : "POST",
			data : {},
			dataType : "json",
			success : function(data) {

				if (data.rs) {
					var params = "?gid=" + gid + "&timeType=" + timeType
							+ "&matchType=" + matchType + "&rtype=" + rtype
							+ "&btype=" + btype + "&selection=" + selection
							+ "&period=" + period + "&dtype=" + dtype;
					var url = "${ctx}/sport/xiazhu" + params;
					toBetOrder(url);
				} else {
					alert('请先登录');
				}
			}
		});

	}

	function toBetOrder(url) {
		bet_order_frame.location.replace(url);
		reinitIframe();
		showOrder();//显示左边	
	}

	function reinitIframe() {
		var iframe = document.getElementById("bet_order_frame");
		try {
			iframe.height = 410;//单注
		} catch (ex) {
		}
	}

	function reinitRecIframe() {
		var iframe = document.getElementById("rec_frame");
		try {
			iframe.height = 410;//单注
		} catch (ex) {
		}
	}

	//综合过关,主要是设置显示高度	
	function betOrderP3(gid, timeType, matchType, rtype, btype, dtype,
			selection, period, gidm) {
		$.ajax({
			url : "${ctx}/valid/checkUserLogin",
			type : "POST",
			data : {},
			dataType : "json",
			success : function(data) {
				if (data.rs) {
					var params = "?gid=" + gid + "&timeType=" + timeType
							+ "&matchType=" + matchType + "&rtype=" + rtype
							+ "&btype=" + btype + "&selection=" + selection
							+ "&period=" + period + "&dtype=" + dtype
							+ "&gidm=" + gidm;
					var url = "${ctx}/sport/xiazhu" + params;
					toBetOrderP3(url, data.sportP3n);
				} else {
					alert('请先登录');
				}
			}
		});

	}

	function toBetOrderP3(url, n) {
		$("#bet_order_frame").show();
		bet_order_frame.location.replace(url);
		// reinitIframeP3(n);//高度
		showOrder();//显示左边

	}

	function reinitIframeP3(n) {
		var iframe = document.getElementById("bet_order_frame");
		try {
			var h = 0;
			for ( var i = 0; i < n + 1; i++) {
				h = h + 90;
			}
			if (n >= 3) {
				iframe.height = 90 + h;//单注
			} else if (n == 0) {
				iframe.height = 410;//单注
			} else {
				iframe.height = h;//单注
			}
		} catch (ex) {
		}
	}

	function delteamsDefaultShow() {
		$("#order_div").show();//滚球
		$("#pls_bet").show();//滚球

		$("#euro_open").show();//滚球
		$("#info_div").show();//公告
		//bet_order_frame.location.replace(url);
		//reinitIframe();//高度
		$("#bet_order_frame").hide();
		$("#bet_order_frame").attr("src", "");
		// showOrder();//显示左边	
	}

	function showOrder() {
		document.getElementById('order_button').className = "ord_on";
		document.getElementById('record_button').className = "record_btn";
		var betDiv = document.getElementById('bet_div');
		var recDiv = document.getElementById('rec_div');
		betDiv.style.display = "";
		recDiv.style.display = "none";
		//bet_order_frame.location.replace("${ctx}/sport/none");
	}

	function showRec() {
		document.getElementById('order_button').className = "ord_btn";
		document.getElementById('record_button').className = "record_on";
		var betDiv = document.getElementById('bet_div');
		var recDiv = document.getElementById('rec_div');
		betDiv.style.display = "none";
		recDiv.style.display = "";
		rec_frame.location.replace("${ctx}/sport/searchNewestTenOrders");
	}

	/**提交订单,左侧高调默认化**/
	function defaultFrameHeight() {
		var iframe = document.getElementById("bet_order_frame");
		try {
			iframe.height = 200;//单注
		} catch (ex) {
		}
		//bet_order_frame.location.replace("${ctx}/sport/none");
	}

	function reinitRecIfFrame(n) {
		var iframe = document.getElementById("rec_frame");
		try {
			iframe.height = 350+n * 80;//单注
		} catch (ex) {
		}
	}

	function jiajianRecIfFrame(h) {
		var iframe = document.getElementById("rec_frame");
		try {
			iframe.height = parseInt(iframe.height) + parseInt(h);//单注

		} catch (ex) {
		}
	}
</script>
</head>

<body id="OSEL" class="bodyset">
<div id="main" class="main" style="margin-left: 2px">
	<div id="menu">
		<div class="ord_on" id="order_button" onclick="if(checkUid())showOrder();">交易单</div>
		<div class="record_btn" id="record_button" onclick="if(checkUid())showRec();">最新十笔交易</div>
	</div>

	<div id="order_div" name="order_div" style="overflow:hidden;">
		<div id="bet_div" name="bet_div" style="display: ">
			<iframe src="${ctx}/sport/none" id="bet_order_frame" name="bet_order_frame" scrolling="NO" frameborder="NO" border="0" height="410"></iframe>
		</div>
		<div id="rec_div" name="rec_div" style="display: ">
			<iframe id="rec_frame" name="rec_frame"  scrolling="NO" frameborder="NO" border="0" height="0"></iframe>
		</div>
	</div>
</div>
<script type="text/javascript">

	function checkUid(){
		var uid = top.uid;
		 
		if(uid=="top_guest"){
			alert("您还未登录,请先登录!");
			return false;
		}
		return true;
	}

	/**
	 * 动态取滚球信息
	 * 
	 * @param
	 * @return
	 */
	function refreshRollCount() {
		$.ajax({
			url : "${ctx}/valid/refreshUserMoney",
			type : "POST",
			data : "{}",
			dataType : "json",
			success : function(result) {
				if(result.rs){
					$("#userMoneyId").html(result.datas.userMoney + " RMB");
					$("#userMessageId").html(result.datas.msgTotal);
				}
			},
			error : function() {
			}
		});
	}
</script>
</body>
</html>