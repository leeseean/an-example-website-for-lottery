<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page trimDirectiveWhitespaces="true" %>

<link rel="stylesheet" href="${resourceRoot}/cp/css/artdialog/default.css">
<script src="${resourceRoot}/cp/js/artDialog.js"></script>

<div class=" ">
	<style>
		.ybb-pg-bd {
			width: 1024px;
			margin: 0 auto;
		}
		#lotteryFrame {
			overflow-x: hidden; 
			overflow-y: scroll;
			width: 100%;
			height: 800px;
		}
	</style>
	
	<script language="javascript" type="text/javascript">
		  function resizeIframe(id) {
		  		var interval;
				function getDocHeight(doc) {
				    doc = doc || document;
				    var body = doc.body, html = doc.documentElement;
				    var height = body.offsetHeight ;
				    return height;
				}
				
				function setIframeHeight(id) {
				    var ifrm = document.getElementById(id);
				    var doc = ifrm.contentDocument? ifrm.contentDocument: 
				        ifrm.contentWindow.document;
				       
				    var height = getDocHeight( doc )  + "px";
				    var old_height = ifrm.style.height;
				    if (height == old_height) {
				    	clearInterval(interval);
				    	return ;
				    }
				    ifrm.style.height = height;
				}
				
				interval = setInterval(function () {
					setIframeHeight(id);
				}, 800);
				
		  }
	</script>
	
	<script>
		window.self.onmessage = function (e) {
			try {
				var data = JSON.parse(e.data);
				if (data.type == 'load_finish') {
					resizeIframe('lotteryFrame');
				}
				else if (data.type == 'showMessage') {
					var title = data['title'],
						content = data['content'];
						
					art.dialog({
						title:title,
						id: 'message_order',
						fixed: true,
					    lock: true,
					    background: '#000000', // 背景色
					    opacity: 0.7,	// 透明度
					    content: content,
					    ok: function (){
					        return true;
					    }
					});
				}
				else if (data.type == 'showDivHtmlMessage') {
					var postData = {
						jsons: data.jsons,
						orderFlag: data.orderFlag
					};
					var title = data['title'],
						content = data['content'];
					art.dialog({
						title: title,
						id: 'zhudanId',
						fixed: true,
					    lock: true,
					    background: '#000000', // 背景色
					    opacity: 0.7,	// 透明度
					    content: content,
					    ok: function (){
					    	postData['ok'] = 'true';
					    	document.getElementById('lotteryFrame').contentWindow.postMessage(postData, '*');
					        return true;
					    },
					    cancel: function(){
					    	postData['cancel'] = 'true';
					    	document.getElementById('lotteryFrame').contentWindow.postMessage(postData, '*');
					    }
					});
				}
				else if (data.type == 'showHK6DivHtmlMessage') {
					var postData = {
						jsons: data.jsons,
						orderFlag: data.orderFlag
					};
					var title = data['title'],
						content = data['content'];
					art.dialog({
						title:title,
						id: 'zhudanId',
						fixed: true,
					    lock: true,
					    background: '#000000',
					    opacity: 0.7,
					    content: content,
					    ok: function (){
					    	postData['ok'] = 'true';
					    	postData['type'] = 'showHK6DivHtmlMessage';
					    	document.getElementById('lotteryFrame').contentWindow.postMessage(postData, '*');
					        return true;
					    },
					    cancel: function(){
					    	postData['cancel'] = 'true';
					    	postData['type'] = 'showHK6DivHtmlMessage';
					    	document.getElementById('lotteryFrame').contentWindow.postMessage(postData, '*');
					    }
					});
				}
			}
			catch (e) {
				//
			}
	
		}
	</script>
	
	<div class="main">
		<iframe src="${ctx}/cp/main?cpparam=${cpparam}" name="lotteryFrame" id="lotteryFrame" onload="resizeIframe('lotteryFrame')" frameborder="0" ></iframe>
	</div>
</div>
