//加载初始化		
$(document).ready(function(){
 
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


function getSwf(movieName){
    if(window.document[movieName]){
        return window.document[movieName];
    }else if(navigator.appName.indexOf("Microsoft") == -1){
        if(document.embeds && document.embeds[movieName])
            return document.embeds[movieName];
    }else{
        return document.getElementById(movieName);
    }
}

award = '';//中奖名称
var _bonuscode='';
function start_lottery(){
	 
    var res = getSwf('lottery').start_lottery();
    if(res != 1) return;//-1为加载资源失败；0为已在抽奖中
    $.ajax({
        url: rootPath+'/activity/doLottery',
        type: "post",
        data:null,
        dataType: "json",
        timeout: 20000,
        cache: false,
        beforeSend: function(){// 提交之前
        },
        error: function(){//出错
            getSwf('lottery').reset_lottery();//取消“正中抽奖中”标志，则可重新抽奖
            alert('服务端出错~');
            _bonuscode='';
        },
        success: function(obj){//成功
        	if(obj.rs){
        		award = obj.datas.tsMsg;//得到奖品名称
        		 getSwf('lottery').show_lottery();//展现转动效果
                 setTimeout(function(){//得到抽奖结果，等5秒钟转动效果才显示结果
                     getSwf('lottery').stop_lottery(obj.datas.prizeIndex+1);
                 },5000);
        	}else{
        		 getSwf('lottery').reset_lottery();//取消“正中抽奖中”标志，则可重新抽奖
                 msgBox('温馨提示',obj.msg);
        	}
        	//_bonuscode='';
        }
    });
}
//结束后调用的函数
function lottery_result(){
    msgBox('温馨提示',award);
}

function getWinningListData(){
	 $.ajax({
	        url: rootPath+'/activity/getWinningListData',
	        type: "post",
	        data:null,
	        dataType: "json",
	        timeout: 20000,
	        cache: false,
	        beforeSend: function(){// 提交之前
	        },
	        error: function(){//出错
	            alert('获取领奖名单出错~');
	        },
	        success: function(obj){//成功
	        	var sAwardEle = "";
	        	if(obj.rs){
	        		$.each(obj.datas, function(i, award){
	        			sAwardEle += '<tr><td height="25" style="width:95px;">恭喜 '+award.user_name+'</td><td height="25" style="width:130px;">获得'+award.prize_name+'</td><td height="25" align="right">'+award.lott_time+'</td></tr>';
	        		});
	        		clearInterval(MyMar);
	        		$("#dem2").html("");
	        		$("#lottery_winners_data").html("");
	        		$("#lottery_winners_data").html(sAwardEle);
	        		show_tzj();
	        	}
	        	
	        }
	    });
}

//中奖查询
function querylist(){
	var sHtml = '<div >'
    + '<div style="height:340px;"><table class="myprizelist"><tr class="list_head"><td style="width:170px;">奖品名称</td><td style="width:140px;">中奖时间</td></tr><tbody id="query_content"></tbody></table></div><div class="quotes"></div></div>';
	
	art.dialog({
		id: 'xymf_box_reg',
		title: '中奖查询',
		width: 370,
		top:60,
		content: sHtml,
		lock: true,
		cancel: function () {}
	});
 
	queryPage(1);
}



var pagesize = 8;
function queryPage(page){
	$.ajax({
		url: rootPath+'/activity/getWinningListPage?pageNo='+page+'&pageSize='+pagesize,
		dataType: 'json',
		cache: false,
		timeout: 20000,
		//data : {querycode:$("#querycode").val()},
		type: 'GET',
		success: function (obj) {
			if(obj.rs){
				 
				var datas = obj.datas;
				if(datas != null && datas.result.length>0){
					var sHtml1 = "";
					$.each(datas.result, function(i, award){
					    sHtml1 +="<tr><td>"+award.prize_name+"</td><td>"+award.lott_time+"</td></tr>";
					});
					var sPage = Paging(page,pagesize,datas.totalCount,2,"queryPage",'','','上一页','下一页');
					$(".quotes").html(sPage);							
					$("#query_content").html(sHtml1);
				}else{
					$("#query_content").html("<tr><td colspan='2'>未找到相关信息</td></tr>");
				}
			}else{
			 
				$("#query_content").html("<tr><td colspan='2'>"+obj.msg+"</td></tr>");
			}
			
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			var x = 1;
		}
	});
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


	//基础弹出框
	function msgBox(_title,_content){   
	  art.dialog({
	    id: 'xymf_box',
	    title: _title,
	    width:300,
	    height:80,
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
	  function start_lottery3(){
		  
		  if(_bonuscode!=''){
			  start_lottery2();
		  }else{
			  var sHtml = '<div style="text-align:right;"><br/>会员帐号：<input id="login-na" type="text" value="" />'
				  + '<br /></div>';
			  art.dialog({
				  id: 'xymf_box_code',
				  title: '请先输入会员帐号',
				  content: sHtml,
				  lock: true,
				  ok: function () {
					  _bonuscode = $("#login-na").val();
					  //var _bonusmobile = $("#login-mobile").val();
					  if(_bonuscode==""){
						  alert("会员帐号不能为空!");
						  return false;
					  }  
					  start_lottery2();
				  },
				  okValue: '确定',
				  cancel: function () {}
			  });
		  }
	 
	  }
	 