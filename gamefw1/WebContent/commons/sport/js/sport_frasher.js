var interval = null;
loadLeg();
function loadLeg() {
	var templeague = "";
	for(var i =0;i<datasArry.length;i++){
		var data = datasArry[i];
		if(data.league == templeague){
			myLeg[data.league].push(data.id);
		}else{
			if(isNull(myLeg[data.league])){
				myLeg[data.league] = new Array();
			}
			myLeg[data.league].push(data.id);
			templeague = data.league;
		}
	}
}

function isNull(exp){ 
	return (typeof(exp)=="undefined" || exp == "" || exp == undefined || exp == null) ? true : false; 
}

function showLeg(leg){
	for (var i=0;i<myLeg[leg].length;i++){
		if(document.getElementById("TR_"+myLeg[leg][i]).style.display!="none"){
			showLegIcon(leg,"LegClose",myLeg[leg][i],"none");
		}else{
			showLegIcon(leg,"LegOpen",myLeg[leg][i],"");
		}
	}
}

function showLegIcon(leg,state,gnumH,display){
	var ary= document.getElementsByName(leg);
	for (var j=0;j<ary.length;j++){
		ary[j].innerHTML="<span id='"+state+"'></span>";
	}

	try{
		 document.getElementById("TR_"+gnumH).style.display=display;
	}catch(E){}
	try{
		 document.getElementById("TR1_"+gnumH).style.display=display;
	}catch(E){}
	try{
		 document.getElementById("TR2_"+gnumH).style.display=display;
	}catch(E){}
	try{
		 document.getElementById("TR3_"+gnumH).style.display=display;
	}catch(E){}
	try{
		document.getElementById("TR4_"+gnumH).style.display=display;
	}catch(E){}
	try{
		document.getElementById("TR5_"+gnumH).style.display=display;
	}catch(E){}
	
}


setRefreshPos();
function setRefreshPos(){
	var refresh_right= document.getElementById('refresh_right');
	if(refresh_right){
		refresh_right.style.left= document.getElementById('myTable').clientWidth*1+20;
	}
	//refresh_right.style.top= 39;
}	
window.onscroll = scroll;

function scroll() {
	var refresh_right = document.getElementById('refresh_right');
	refresh_right.style.top = document.body.scrollTop + 39;
}

function Load() {
		clearTimeout(interval);    //清除延时程序  
		interval = setInterval(function() {
			if(secs<0){
				clearTimeout(interval);    //清除延时程序  
				reload();
				return;
			}
			document.getElementById('refreshTime').innerHTML = '' + secs;
			secs--;
		}, 1000);
	}

 
	function chk_league() {
		lid_form.submit();
	}

	function selall() {
		var len = lid_form.elements.length;
		var does = true;
		does = lid_form.sall.checked;
		for ( var i = 1; i < len; i++) {
			var e = lid_form.elements[i];
			if (e.id.substr(0, 3) == "LID")
				e.checked = does;
		}
	}

	function chk_all(e) {
		if (!e)
			lid_form.sall.checked = e;
	}

	function leagueClose() {
		document.getElementById("abc").style.display = "none";
	}
	
	function refreshReload() {
		if(document.getElementById("refresh_right")){
			document.getElementById("refresh_right").className = 'refresh_M_on';
		}
		if(document.getElementById("refresh_btn")){
			document.getElementById("refresh_btn").className = 'refresh_on';
		}
		if(document.getElementById("refresh_down")){
			document.getElementById("refresh_down").className = 'refresh_M_on';
		}
		reload();
	}

	function reload() {
		var leas = document.getElementById("leas").value;
		var random = (Math.random()*1000).toFixed(0);
		
		var iframeObj = parent.document.getElementById("frasherData");
		parent.document.getElementById("frasherData").src = URL+"&reFlag=1&leas="+encodeURI(leas)+"&_random="+random;
 
		clearInterval(interval);
		secs = 90;
		if(typeof isRoll === 'undefined'){
			secs = 90;
		}else{
			if(isRoll){
				secs=60;
			}
		}
	 
		Load();
	 
		console.log(iframeObj.src);
		setTimeout(function() {
			document.getElementById("refresh_right").className = 'refresh_M_btn';
			document.getElementById("refresh_btn").className = 'refresh_btn';
			document.getElementById("refresh_down").className = 'refresh_M_btn';
			if(iframeObj.attachEvent){ 
				iframeObj.attachEvent("onload", function(){ 
					var doc= iframeObj.contentDocument;
					if(doc){
						var content = doc.body.innerHTML;
						//alert(content);
						//console.log("*********************************>");
						//console.log(content);
						if(content!=null && content!=''){
							$("#showtable").html(content);
						}
					}
				}); 
			} else { 
				iframeObj.onload = function(){ 
					var doc= iframeObj.contentDocument;
					if(doc){
						var content = doc.body.innerHTML;
						//console.log("========================================>");
						//console.log(content);
						if(content!=null && content!=''){
							$("#showtable").html(content);
						}
					}
				}; 
			} 
		}, 2000);  //5秒后将会调用执行remind()函数  

	}

	function selectPage(selPage) {
		window.location.href = pageURL + "&matchPage=" + selPage;
	}

	function openXieYi() {
		document.getElementById("abc").style.display = "block";
		$.layer({
			type : 1,
			shade : [ 0 ],
			area : [ '650px', 'auto' ],
			title : false,
			border : [ 10, 1, '#493721' ],
			closeBtn : [ 0, false ], //去掉默认关闭按钮
			offset : [ '90px', '10px' ],
			page : {
				dom : '.layer_notice'
			}
		});
	}
 