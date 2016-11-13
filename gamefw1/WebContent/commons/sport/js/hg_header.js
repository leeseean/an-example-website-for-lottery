function chg_type_class(num){	
//已選取：黃字 class="type_on"
//選取後離開：白字 class="type_out"
	var tn;
	var aa="ftn"+num;
	for(var i=1;i<=24;i++){	   	
	   tn ="ftn"+i;
	   if(tn != aa){
		   //document.getElementById("#"+tn).className = "type_out";
		   //$("#"+tn).css("color","#FFF");
		   $("#"+tn).attr("class","type_out");
	   }else{
		   //document.getElementById("#"+tn).className = "type_on";
		   //$("#"+tn).css("color","#F9C100");
		   $("#"+tn).attr("class","type_on");
	   }
	}

}

function chg_type_last(num){	
	//已選取：黃字 class="type_on"
	//選取後離開：白字 class="type_out"
		var tn;
		var aa="last"+num;
		for(var i=1;i<=63;i++){	   	
		   tn ="last"+i;
		   if(tn != aa){ 
			   // document.getElementById("#"+tn).className = "type_out";
			   // $("#"+tn).css("color","#FFF");
			   $("#"+tn).attr("class","type_out");
		   }else{
			  // document.getElementById("#"+tn).className = "type_on";
			   //$("#"+tn).css("color","#F9C100");
			   $("#"+tn).attr("class","type_on");
		   }
		}

	}

function chg_button_bg(gtype,btn){
//  滚球  今日赛事  早盘	
	if (btn=="today"){
	  $('#today_btn').removeClass("today");
	  $('#today_btn').addClass("today_on");
	  $('#rb_btn').removeClass("rb_on");
	  $('#rb_btn').addClass("rb");
	  $('#early_btn').removeClass("early_on");
	  $('#early_btn').addClass("early");
	}else if(btn=="early"){
	  $('#early_btn').removeClass("early");
	  $('#early_btn').addClass("early_on");	
	  $('#rb_btn').removeClass("rb_on");
	  $('#rb_btn').addClass("rb");
	  $('#today_btn').removeClass("today_on");
	  $('#today_btn').addClass("today");
	}else if(btn=="rb"){
	  $('#rb_btn').removeClass("rb");
	  $('#rb_btn').addClass("rb_on");
	  $('#today_btn').removeClass("today_on");
	  $('#today_btn').addClass("today");
	  $('#early_btn').removeClass("early_on");
	  $('#early_btn').addClass("early");
	}
}

/**
 * 二级菜单切换
 * @param a
 * @return
 */
function chg_nav(a){
//  足球  篮球   早餐足球
	if(a=='today'){
		$("#nav_today").show();
		$("#nav_early").hide();
		$("#nav_re").hide();
	}else if(a=='early'){
		$("#nav_early").show();
		$("#nav_re").hide();
		$("#nav_today").hide();
	}else if(a=='re'){
		$("#nav_re").show();
		$("#nav_early").hide();
		$("#nav_today").hide();
	}
	
}

/**
 * 三级块切换
 * @param a
 * @return
 */
function chg_navli(a){
// 独赢 波胆  总入球 半全场  等	
	if(a=='today'){
		$("#type_today").show();
		$("#type_early").hide();
		$("#type_re").hide();
	}else if(a=='early'){
		$("#type_early").show();
		$("#type_re").hide();
		$("#type_today").hide();
	}else if(a=='re'){
		$("#type_re").show();
		$("#type_early").hide();
		$("#type_today").hide();
	}
}

function chg_third(n){
	var tn;
	var aa="third"+n;
	for(var i=1;i<=24;i++){	   	
	   tn ="third"+i;
	   if(tn != aa){ 
		   $("#"+tn).hide();
	   }else{	 
		   $("#"+tn).show();
	   }
	}   
}

function chg_navli_o(t,n){

	if(n=='1'){
		$("#type_"+t+"_1").show();
		$("#type_"+t+"_2").hide();
	}else if(n=='2'){
		$("#type_"+t+"_2").show();
		$("#type_"+t+"_1").hide();
	}
	
}

function chg_url(a){
	if(a.indexOf('?')>0){
		if(a.indexOf('&')>0){
			a += "&_rand="+new Date().getTime();
		}else{
			a += "_rand="+new Date().getTime();
		}
	}else{
		a += "?_rand="+new Date().getTime();
	}
	parent.showFrame.location=a;
}
function showRB(gtypeFT){
    if(gtypeFT=='FT'){
		 parent.showFrame.location.href='/sports/ft/gq';
		 parent.header.chg_button_bg('FT','rb');
		 parent.header.chg_nav(3);
		 parent.header.chg_navli(4);
		 parent.header.chg_type_class('ftn',1);
	}else{
		 parent.showFrame.location.href='/sports/bb/gq';
		 parent.header.chg_button_bg('BK','rb');
		 parent.header.chg_nav(3);
		 parent.header.chg_navli(5);
		 parent.header.chg_type_class('ftn',8);
		
	}
	parent.header.chg_button_bg(gtypeFT,"rb");
}
function reloadCrditFunction(){ 
  $("#credit").html('加载中...');
  $.get("/cn/balance", function(result){
      $("#credit").html("人民币 "+result);
  });
}
function OnMouseOverEvent() {
	document.getElementById("informaction").style.display = "block";
}
function OnMouseOutEvent() {
	document.getElementById("informaction").style.display = "none";
}

function showMoreMsg(url){
	winOpen(url,620,550,0,0,"sport");
}

function winOpen(url,width,height,left,top,name)
{
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
	temp += ',left='
	+ Math.round((window.screen.width - parseInt(width)) / 2);
	}
	if (top) {
	temp += ',top=' + top;
	} else {
	temp += ',top='
	+ Math.round((window.screen.height - parseInt(height)) / 2);
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
		//window.setTimeout("obj.document.location=url",3000);
	}
	else{
			window.open (url,name,temp);
	}
}