var GameRtype = new Array();
var SP_wtypes = new Array();
var gid ='';

		try{
		parent.mem_order.document.getElementById('today_btn').className="today_btn";
		parent.mem_order.document.getElementById('early_btn').className="early_btn";
		}catch(E){}

function Check(uid,gtype,gid,lang,i){
	document.getElementById("show_table").style.top=document.getElementById("moreid_"+i).offsetTop+10;
	//document.getElementById("show_table").style.top=document.body.scrollTop+event.clientY+10;
	SP_Data.location ="./result_sp.php?uid="+uid+"&gtype="+gtype+"&gid="+gid+"&langx="+lang;
}

function show_key_result_sp(){
	var rary =SP_wtypes;
	var rary1=new Array("F","L");
	var rarysub=new Array("H","C");
	var tmpDate= new Array();
	var tmp_table = document.getElementById("show_table_sp").innerHTML;
	for(var j=0; j < rary.length; j++) {
		for(var k=0; k < rary1.length; k++) {
			if(GameRtype[gid+rary[j]+rary1[k]][1]=="無開放" || GameRtype[gid+rary[j]+rary1[k]][1]=="无开放" || GameRtype[gid+rary[j]+rary1[k]][1]=="N/A"){
			tmp_table  = tmp_table.replace('*'+rary[j]+rary1[k]+'*',GameRtype[gid+rary[j]+rary1[k]][1]).replace('*'+rary[j]+rary1[k]+'A'+'*',"mor_2").replace('*'+rary[j]+rary1[k]+'B'+'*',"morth_2");
		}else{
			tmp_table  = tmp_table.replace('*'+rary[j]+rary1[k]+'*',GameRtype[gid+rary[j]+rary1[k]][1]).replace('*'+rary[j]+rary1[k]+'A'+'*',"mor_1").replace('*'+rary[j]+rary1[k]+'B'+'*',"morth_1");
			}
		}
	}
	show_table.innerHTML =tmp_table ;
}

function Closedv(){
	show_table.innerHTML="";
}


function chg_gtype(tmpValue,tmpURL){
	var strUrl ="";
	strUrl =rootPath + "/sport/getftRes";
	strUrl +="?selgtype="+tmpValue;
	window.location.href=strUrl+tmpURL;
}

/*
function onchangeDate(url,tmpgtype,lang){
        var todayTmp= document.getElementById('today_gmt');
        var chk=chk_changeDate(todayTmp.value);
        if(chk==false){
        	alert("Date error!!");
        	return;
        } 
       location.href=url+"&game_type="+tmpgtype+"&today="+todayTmp.value+"&langx="+lang;

}

function chk_changeDate(today_Tmp){
	if(today_Tmp==""){
		return true;}
	
	var dateArr = today_Tmp.split("-");
	if(dateArr.length!=3){
		return false;
	}else if(dateArr[0]*1< 2000 || dateArr[0]*1 > 2999){
		return false;
	}else if(dateArr[1]*1< 1 || dateArr[1]*1 > 12){
		return false;
	}else if(dateArr[2]*1< 1 || dateArr[2]*1 > 31){
		return false;
	}else{
		return true;
	}	
	
}  
*/ 
function refreshReload() {
    reload_var("");
}
function reload_var(Level){
	location.reload();
}
//window.onscroll = scroll;

function scroll()
{
	var refresh_right= document.getElementById('refresh_right');
	
	//refresh_right.style.top=document.body.scrollTop+21+34+25+10;
	refresh_right.style.top=document.body.scrollTop+(document.body.clientHeight-118)/2;
	                      // 捲軸位置              +( frame高度                -header高度)/2
	
 //alert("scroll event detected! "+document.body.scrollTop);
// 
	//conscroll.style.display="block";
//conscroll.style.top=document.body.scrollTop;
 // note: you can use window.innerWidth and window.innerHeight to access the width and height of the viewing area
}
//----------------------
