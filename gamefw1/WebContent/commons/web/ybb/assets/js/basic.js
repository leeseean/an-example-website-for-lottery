


$(function(){
	$('#login input').bind('focus',function(){
		$(this).next().html('?');
	});	
	if($('#login .nowtime')[0] != undefined){
		var timeZone = Math.abs(new Date().getTimezoneOffset()/60);
		setInterval(function(){
			var myDate = new Date();
			var times = timeZone+'　'+myDate.getFullYear()+'-'+(myDate.getMonth()+1)+'-'+myDate.getDate()+'-'+myDate.toLocaleTimeString();
			$('#login .nowtime').html(times);
		},1000);
	}
	if($('#content .loading1')[0] != undefined){
		$(function(){
			var divL1 = $('#content .loading1')[0];
			var divL2 = $('#content .loading2')[0];
			var span1 = $('#content span')[0];
			var span2 = $('#content span')[1];
			var loading =0;
			timer=setInterval(function(){
				loading++;
				divL1.style.width =loading+'px';
				divL2.style.width =Math.round(loading*2.2)+'px';
				span1.innerHTML = Math.round(loading/3+3);
				span2.innerHTML = Math.round(loading/60*2) + '\'' + Math.abs((loading-Math.round(loading/60)*60)+36);
				if(loading==60){
		              clearInterval(timer);	
		        }
			},10);
		});
	}












});

	function ScrollImgLeft(){ 
		var speed=50; 
		var scroll_begin = document.getElementById("scroll_begin"); 
		var scroll_end = document.getElementById("scroll_end"); 
		var scroll_div = document.getElementById("scroll_div"); 
		scroll_end.innerHTML=scroll_begin.innerHTML; 
		function Marquee(){ 
		if(scroll_end.offsetWidth-scroll_div.scrollLeft<=0) 
		scroll_div.scrollLeft-=scroll_begin.offsetWidth; 
		else 
		scroll_div.scrollLeft++; 
		} 
		var MyMar=setInterval(Marquee,speed); 
		scroll_div.onmouseover=function() {clearInterval(MyMar);} 
		scroll_div.onmouseout=function() {MyMar=setInterval(Marquee,speed);} 
	} 








