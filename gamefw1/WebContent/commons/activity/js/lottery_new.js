//加载初始化		
	$(document).ready(function(){
		lotterylist();
		var pin = new $.Zebra_Pin($('#pin2'), {
			contain: true
		});

			$(window).scroll(function() {      
				if($(window).scrollTop() >= 1000){
					$('.actGotop').fadeIn(300); 
				}else{    
					$('.actGotop').fadeOut(300);    
				}  
			});
			$('.actGotop').click(function(){
			$('html,body').animate({scrollTop: '0px'}, 800);}); 
			
		$(".productshow").Xslider({
			unitdisplayed:3,
			numtoMove:1,
			loop:"cycle"
		});
	});

//初始化Flash转盘    
  var resultPid = 0;
  var resultMsg = "";
  var bCode = "";
  var bMobile = "";
  var bUser = "";
  var bPhone = "";
  var bEmail = "";
  var bQQ = "";
  var bAddress = "";

   var angle = 0;
   var direction = true;
   
   var startPower = null;
  
//是否可抽奖
var isLottery = true;

$(document).ready(function(){

  //angle:奖项ID, text:中奖提示语句，
  var rotateFunc = function(pid,text){   
	
    //读取力量值
    var progress = $("#progressbar_value").html();
    //根据力量值计算转盘转动速度
    var _duration = getDuration(angle);
	
	var _angle = getAngle(angle);

    angleIndex = parseInt(pid) - 1;

    var prizeAngle = [0,36,72,108,144,180,216,252,288,324,360];

    if(prizeAngle[angleIndex]==undefined){
        msgBox('温馨提示','配置错误,请联系管理员');
        return;
    } 

    $("#dzp_img").rotate({
      angle:0, 
      duration: _duration, 
      animateTo: prizeAngle[angleIndex]+_angle, //2160是要让指针旋转6圈 360
      callback:function(){
        $("#jquery_jplayer_1").jPlayer("stop");	
		$("#lottery_img").hide();
		$("#lotteryLine").rotate(0);
		$("#lotteryBtn").attr("src","images/pointer_load_static.png");
        isLottery = true;
        msgBox('温馨提示',text);
        lotterylist();
      }
    }); 
	
  };

$("#lottery_img").click(function(){		  
          if(!isLottery){
              return;
          } 
		  clearInterval(startPower);
          isLottery = false;       
           $.ajax({
                url: 'bonus.php',
                dataType: 'json',
                cache: false,
                type: 'POST',
                data: {bonuscode: bCode},
                success: function (obj) {
                    switch (obj.stat) {
                        case '-1':
                          isLottery = true;
                          logBox();
                          break;  
						case '-9':
						   isLottery = true;            
                           msgBox('温馨提示',stopmsg);
						   $("#lotteryBtn").attr("src","images/pointer.png");
						   $("#lottery_img").hide();
							break;
                        case '-2':
                           isLottery = true;            
                           msgBox('温馨提示','您的会员帐号不符合抽奖资格!');
						   $("#lottery_img").hide();
						   $("#lotteryBtn").attr("src","images/pointer.png");
                           bCode = "";
                            break;
                        case '-3':
                          isLottery = true;            
                            msgBox('温馨提示','您的会员帐号已经没有机会了!');
                            bCode = "";
							$("#lottery_img").hide();
							$("#lotteryBtn").attr("src","images/pointer.png");
                            break;
                        case '5': 
                            isLottery = true;          
                            msgBox('温馨提示',obj.desc);
							$("#lottery_img").hide();
							$("#lotteryBtn").attr("src","images/pointer.png");
                            break;  
                        case '0':                             
							$("#jquery_jplayer_0").jPlayer("stop");
							$("#jquery_jplayer_1").jPlayer("play");							 
                            resultPid = obj.pId;
                            resultMsg = obj.msg;
                            rotateFunc(resultPid,resultMsg);
                            break;
                        default:
                            isLottery = true;
                            msgBox('温馨提示',obj.msg);
							$("#lottery_img").hide();
							$("#lotteryBtn").attr("src","images/pointer.png");
                            break;
                        }
                    },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    isLottery = true;
                    msgBox('温馨提示','网络错误,请稍后再抽奖1'+"<br>错误类型textStatus: "+textStatus+"<br>异常对象errorThrown: "+errorThrown);
                  }
                })

  });

   $("#lotteryBtn").click(function(){
		 if(bCode==""){
          logBox();
          return;
        }   
		angle = 0;
		$("#lottery_img").show();
		$(this).attr("src","images/pointer_load_on.png");
		startPower = setInterval('Power()',50);
		$("#jquery_jplayer_0").jPlayer("play");	
        //executeAsync21(20).start();
		
    })
	$("#jquery_jplayer_0").jPlayer({
			ready: function () {
				$(this).jPlayer("setMedia", {
					mp3:"new_sound/luck_music.mp3"
				});
			},
			swfPath: "js",
			supplied: "mp3",
			wmode: "window"
	});
	$("#jquery_jplayer_1").jPlayer({
		ready: function () {
			$(this).jPlayer("setMedia", {
				mp3:"new_sound/luck_musicl.mp3"
			});
		},
		swfPath: "js",
		supplied: "mp3",
		wmode: "window"
	});
	

});

  //基础弹出框
  function msgBox(_title,_content){   
    art.dialog({
      id: 'xymf_box',
      title: _title,
      width:300,
      height:120,
      content: _content,
      ok: function () {
                this.close();
            },
      close:function(){
      },
            okVal: '确定',
            lock: true
    });
  }

//输入抽奖码
  function logBox(){
	  if(isStop==1)
	  {
		  msgBox('温馨提示',stopmsg);
          return false;
	  }else{
    var sHtml = '<div style="text-align:right;"><br/>会员帐号：<input id="login-na" type="text" value="" />'
        + '<br /></div>';
    art.dialog({
      id: 'xymf_box_code',
      title: '请先输入会员帐号',
      content: sHtml,
      lock: true,
      ok: function () {
            
        var _bonuscode = $("#login-na").val();
		//var _bonusmobile = $("#login-mobile").val();
        if(_bonuscode==""){
          alert("会员帐号不能为空!");
          return false;
        }  
		/*
		if(_bonusmobile==""){
          alert("手机号码不能为空!");
          return false;
        }     
		var isMobile=/^(?:13\d|15\d)\d{5}(\d{3}|\*{3})$/;   
		var isPhone=/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
		 if(!isMobile.test(_bonusmobile)){
            alert("请正确填写手机号码，例如:13812341234");
            return false;
        }
		*/
        bCode = _bonuscode;
		//bMobile = _bonusmobile;
        var _obj = this;
        $.ajax({
          url: 'ajax.php?action=check',
          dataType: 'json',
          cache: false,
          type: 'POST',
          data: {bonuscode: bCode},
          success: function (obj) {   
            switch (obj.stat) {
              case '-1':            
                alert('您输入的会员帐号不能为空!');
                break;
              case '-2':            
                alert('您的会员帐号不符合抽奖资格!');
                break;
              case '-3':            
                alert('您的会员帐号已经没有机会了!');
                break;
			case '-4':            
                alert('您输入的手机号码不能为空!');
                break;
              case '0':                    
                //alert("验证成功,请点击按钮进行抽奖");
				$("#lotteryBtn").attr("src","images/pointer_load_static.png");
                _obj.close();
                break;
              default:
                alert('网络错误,请稍后再抽奖2');
                break;
            }
          },
          error: function(XMLHttpRequest, textStatus, errorThrown) {
              var x = 1;
          }
        });
        return false;
        
      },
      okValue: '确定',
      cancel: function () {}
    });
	  }
  }


 
function Power(){
	if(!isLottery){
        return;
    }
	if(direction==true){
		angle+=3;
		if(angle>225){
			direction = false;
		}
	}else{
		angle-=3;
		if(angle<0){
			direction = true;
		}
	}
	$("#lotteryLine").rotate(angle);
}

function getDuration(angle){
	if(angle<45){
		return 3000;
	}
	if(angle<90){
		return 6000;
	}
	if(angle<145){
		return 9000;
	}
	if(angle<180){
		return 12000;
	}
	if(angle<225){
		return 15000;
	}
	return 15000;
}

function getAngle(angle){
	if(angle<45){
		return 1080;
	}
	if(angle<90){
		return 2160;
	}
	if(angle<145){
		return 3240;
	}
	if(angle<180){
		return 4320;
	}
	if(angle<225){
		return 5400;
	}
	return 5400;
}

	//中奖列表
	function lotterylist(){
    	$.ajax({
            url: 'ajax.php?action=lotterylist',
            dataType: 'json',
            cache: false,
            type: 'POST',
            success: function (obj) {     
                    var sAwardEle = "";
                	$.each(obj, function(i, award){
         				sAwardEle += '<tr><td height="25" style="width:95px;">恭喜 '+award.user+'</td><td height="25" style="width:130px;">获得'+award.prize+'</td><td height="25" align="right">'+award.date+'</td></tr>';
						});
					clearInterval(MyMar);
					$("#dem2").html("");
					$("#lottery_winners_data").html("");
					$("#lottery_winners_data").html(sAwardEle);
					show_tzj();

			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
                    var x = 1;
            }
        }) 
    }
	
	//中奖查询
	function querylist(){
    	var sHtml = '<div >会员帐号：<input id="querycode" type="text" value="" />'
        + '<br/><br/><div style="height:340px;"><table class="myprizelist"><tr class="list_head"><td style="width:170px;">奖品名称</td><td style="width:140px;">中奖时间</td></tr><tbody id="query_content"></tbody></table></div><div class="quotes"></div></div>';
		art.dialog({
			id: 'xymf_box_reg',
			title: '输入会员帐号 查询',
			width: 370,
			top:60,
			content: sHtml,
			lock: true,
			ok: function () {
				var _bonuscode = $("#querycode").val();
				if(_bonuscode == ""){
					alert("输入会员帐号不能为空!");
					return false;
				}
				queryPage(1);
				return false;
			},
			okValue: '查询',
			cancel: function () {}
		});
    }
var pagesize = 8;

function queryPage(page){
				$.ajax({
					url: 'ajax.php?action=querylist&p='+page+'&size='+pagesize+'&querycode='+$("#querycode").val(),
					dataType: 'json',
					cache: false,
					//data : {querycode:$("#querycode").val()},
					type: 'GET',
					success: function (obj) {
						if(obj.count != 0){
							var sHtml1 = "";
							$.each(obj.data, function(i, award){
							    sHtml1 +="<tr><td>"+award.prize_name+"</td><td>"+award.won_time+"</td></tr>";
							})
							var sPage = Paging(page,pagesize,obj.count,2,"queryPage",'','','上一页','下一页');
							$(".quotes").html(sPage);							
							$("#query_content").html(sHtml1);
						}else{
							$("#query_content").html("<tr><td colspan='2'>未找到相关信息</td></tr>");
						}
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) {
						var x = 1;
					}
				})
} 
	
function Paging(pageNum,pageSize,totalCount,skipCount,fuctionName,currentStyleName,currentUseLink,preText,nextText,firstText,lastText){
	    var returnValue = "";
	    var begin = 1;
	    var end = 1;
	    var totalpage = Math.floor(totalCount / pageSize);
	    if(totalCount % pageSize >0){
	        totalpage ++;
	    }	   
	    if(preText == null){
	        firstText = "prev";
	    }
	    if(nextText == null){
	        nextText = "next";
	    }
	    
	    begin = pageNum - skipCount;
	    end = pageNum + skipCount;
	    
	    if(begin <= 0){
	        end = end - begin +1;
	        begin = 1;
	    }
	    
	    if(end > totalpage){
	        end = totalpage;
	    }
	    for(count = begin;count <= end;count ++){
	        if(currentUseLink){ 
	            if(count == pageNum){
	                returnValue += "<a class=\""+currentStyleName+"\" href=\"javascript:void(0);\" onclick=\""+fuctionName+"("+count.toString()+");\">"+count.toString()+"</a> ";
	            }
	            else{
	                returnValue += "<a href=\"javascript:void(0);\" onclick=\"" + fuctionName + "(" + count.toString() + ");\">" + count.toString() + "</a> ";
	            }
	        }
	        else {
	            if (count == pageNum) {
	                returnValue += "<span class=\""+currentStyleName+"\">"+count.toString()+"</span> ";
	            }
	            else{           
	                returnValue += "<a href=\"javascript:void(0);\" onclick=\""+fuctionName+"("+count.toString()+");\">"+count.toString()+"</a> ";}
	            }
	        }
	        if(pageNum - skipCount >1){
	            returnValue = " ... "+returnValue;
	        }
	        if(pageNum + skipCount < totalpage){
	            returnValue = returnValue + " ... ";
	        }
	        
	        if(pageNum > 1){
	            returnValue = "<a href=\"javascript:void(0);\" onclick=\""+fuctionName+"("+(pageNum - 1).toString()+");\"> " + preText + "</a> " + returnValue;
	        }
	        if(pageNum < totalpage){
	            returnValue = returnValue + " <a href=\"javascript:void(0);\" onclick=\""+fuctionName+"("+(pageNum+1).toString()+");\">" + nextText + "</a>";
	        }
	        
	        if(firstText!= null){
	            if(pageNum >1){
	                returnValue = "<a href=\"javascript:void(0);\" onclick=\""+fuctionName+"(1);\">" + firstText + "</a> " + returnValue;}
	        }
	        if(lastText !=null){
	            if(pageNum < totalpage){
	                returnValue = returnValue + " " + " <a href=\"javascript:void(0);\" onclick=\""+fuctionName+"("+totalpage.toString()+");\">" + lastText + "</a>";}
	        }
	        return returnValue;
        
	}