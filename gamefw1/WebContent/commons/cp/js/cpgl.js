$(function() {
	var iWidth = 1200; // 弹出窗口的宽度;
	var iHeight = 640; // 弹出窗口的高度;
	var iTop = (window.screen.availHeight - 10 - iHeight) / 2; // 获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; // 获得窗口的水平位置;
	$("#xzmx").click(function() {
		if(webUser){
			winOpenMessage(rootPath + '/cpHistory/betDetail', 'message',"height=" + iHeight + ", width=" + iWidth + ", top="+ iTop + ", left=" + iLeft)
		}else{
			alert("请先登录");
		}
	});
	$("#yzbb").click(function() {
		if(webUser){
			winOpenMessage(rootPath + '/cpHistory/week', 'message',"height=" + iHeight + ", width=" + iWidth + ", top="+ iTop + ", left=" + iLeft)
		}else{
			alert("请先登录");
		}
	});
	$("#lskj").click(function() {
		winOpenMessage(rootPath + '/cpResult/goList', 'message',"height=" + iHeight + ", width=" + iWidth + ", top="+ iTop + ", left=" + iLeft)
	});
	function winOpenMessage(url, B, C) {
		window.open(url, B, C);
	}
});