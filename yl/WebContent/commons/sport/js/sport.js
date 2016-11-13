function CheckKey(evt){
	var key = window.event ? evt.keyCode : evt.which;	
	if(key == 32){
		return false;
	}
	if(key == 13) {
	}
	else if((key < 48 || key > 57) && (key > 95 || key < 106)&& (key != 8)){
		alert("下注金额只能输入数字"); 
		return false;
	}
}

function changeWin(obj,minus){
	var odds = parseFloat($("#xiazhu_odds").text());
	var n = parseFloat(obj.value);
	var sum = 0;
	
	/****/
	if(isNaN(n)){
		$("#xiazhu_win").text('0');
		return ;
	}
	
	sum =n*(odds-minus);

	if( sum>=0){
		$("#xiazhu_win").text(sum.toFixed(2));
	}
}

function orderSubmit(frm){
	var min = parseInt(xiazhuForm.limitBetMin.value);
	var betMoney=parseInt(xiazhuForm.gold.value);
	if(betMoney<min){
		alert("单注最低投注额是 人民币 "+min);
		return false;
	}
	
	var max=parseInt(xiazhuForm.limitBet.value);
	if(betMoney>max){
		alert("单注最高投注额是 人民币 "+max);
		return false;
	}
	
	var r=confirm("可赢金额："+($("#xiazhu_win").text())+"\u000d"+"确定进行下注吗？");
	if (r==true){
		$("#submit").html('提交中...请稍等');
		//frm.submit();
		return true;
	}
	return false;
}

/**以下针对综合过关**/
function delteam(gid){
	 
	
	window.location.href=rootPath+"/sport/delteam?gid="+gid;
}
function delteams(gid){
	window.location.href=rootPath+"/sport/delteams";
}

function cancelBetOrder(){
	window.location.href=rootPath+"/sport/cancelBetOrder";
 
}

function CheckKey3(evt){
	var key = window.event ? evt.keyCode : evt.which;	
	if(key == 32){
		return false;
	}
	if(key == 13) {
	}
	else if((key < 48 || key > 57) && (key > 95 || key < 106)&& (key != 8)){
		alert("下注金额只能输入数字"); 
		return false;
	}
}

function changeWinP3(obj,minus){
	var odds = parseFloat($("#xiazhu3_odds").text());
	
	var n = parseFloat(obj.value);
	
	/****/
	if(isNaN(n)){
		$("#xiazhu_win").text('0');
		return ;
	}
	
	
	var sum = 0;
	sum =n*(odds-minus);
	if( sum>=0){
		$("#xiazhu_win").text(sum.toFixed(2));
	}
}


function winOpen(url,width,height,left,top,name){
	 
	var temp = "menubar=no,toolbar=no,directories=no,scrollbars=yes,resizable=no";
	if (width) {
		temp += ',width=' + width;
	} else {
		width = 1024;
	}
	if (height) {
		temp += ',height=' + height;
	} else {
		height = 600;
	}
	if (left) {
		temp += ',left=' + left;
	} else {
		temp += ',left='+ Math.round((window.screen.width - parseInt(width)) / 2);
	}
	if (top) {
		temp += ',top=' + top;
	} else {
		temp += ',top='+ Math.round((window.screen.height - parseInt(height)) / 2);
	} 
	if(typeof(name)=="undefined"){
		name="";
	}
	if(name=="game")
	{
		//alert(temp);
		var obj=window.open (url,name,temp);
		obj.moveTo(0,0);
		obj.resizeTo(window.screen.availWidth,window.screen.availHeight);	

	}else if(name=="message"){
		window.open (url,name,temp);
		return;
	}
	if(name=="game") temp+=',fullscreen=1';
	window.open(url,name,temp);
}
