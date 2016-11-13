/**进入会员中心后 首次获取消息总条数**/
var initMsgCount = 0;

document.oncontextmenu=new Function("event.returnValue=false;");

/*******************************************************************************
 * 只能输入大于0的数,小数点也不行
 *
 * @param $this
 * @return
 */
function digitOnly ($this) {
	var n = $($this);
	var r = /^\+?[1-9][0-9]*$/;
	if (!r.test(n.val())) {
		n.val("");
	}
}
// 数字验证 过滤非法字符
function clearNoNum(obj){
	// 先把非数字的都替换掉，除了数字和.
	obj.value = obj.value.replace(/[^\d.]/g,"");
	// 必须保证第一个为数字而不是.
	obj.value = obj.value.replace(/^\./g,"");
	// 保证只有出现一个.而没有多个.
	obj.value = obj.value.replace(/\.{2,}/g,".");
	// 保证.只出现一次，而不能出现两次以上
	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	if(obj.value != ''){
	var re=/^\d+\.{0,1}\d{0,2}$/;
		  if(!re.test(obj.value))
		  {
			  obj.value = obj.value.substring(0,obj.value.length-1);
			  return false;
		  }
	}
}
function isChinese(str){
	return /[\u4E00-\u9FA0]/.test(str);
}
function Go(url){
	window.location.href=url;
}
function OnlineService(){
	window.open('http://www.baidu.com/',"newFrames","width=700,height=550,status=no,scrollbars=no");
}
// 表格各行换色
function bianse(o,a,b,c,d){
var t=document.getElementById(o).getElementsByTagName("tr");
for(var i=1;i<t.length-1;i++){
t[i].style.backgroundColor=(t[i].sectionRowIndex%2==0)?a:b;
t[i].onclick=function(){
if(this.x!="1"){
this.x="1";// 本来打算直接用背景色判断，FF获取到的背景是RGB值，不好判断
this.style.backgroundColor=d;
}else{
this.x="0";
this.style.backgroundColor=(this.sectionRowIndex%2==0)?a:b;
}
}
t[i].onmouseover=function(){
if(this.x!="1")this.style.backgroundColor=c;
}
t[i].onmouseout=function(){
if(this.x!="1")this.style.backgroundColor=(this.sectionRowIndex%2==0)?a:b;
}
}
}
// 二级联动
var strArea = new Array();
strArea[0] = ['总账户','0','A','ALL'];
strArea[1] = ['AG旗舰厅','1','A','AG'];
strArea[2] = ['BB娱乐场','2','A','BB'];
strArea[3] = ['VIP国际厅','3','A','VIP'];
strArea[4] = ['新天地旗舰厅','4','A','XTD'];
strArea[5] = ['AG旗舰厅','5','0','AG'];
strArea[6] = ['BB娱乐场','6','0','BB'];
strArea[7] = ['VIP国际厅','7','0','VIP'];
strArea[8] = ['新天地旗舰厅','8','0','XTD'];
strArea[9] = ['总账户','9','1','ALL'];
strArea[10] = ['总账户','10','2','ALL'];
strArea[11] = ['总账户','11','3','ALL'];
strArea[12] = ['总账户','12','4','ALL'];
function ddl_Clear(ddl_name){
    var obj = document.getElementById(ddl_name);
    for(var i = obj.options.length - 1;i >= 0;i--){
        obj.options[i] = null;
    }
}
function ddl_selected(ddl_name,match_val,isValue){
    var obj = document.getElementById(ddl_name);
    for( var i = 0; i < obj.options.length; i++ ){
        var matchobj = obj.options[i].value;
        if(!isValue){
            matchobj = obj.options[i].Text;
        }
        if(match_val == matchobj){
            obj.options[i].selected = "selected";
        }
    }
}
function delspace(findstr){
    var myfind = findstr;
    for(var i = 0; i < findstr.length; i++){
        var myfind = myfind.replace(" ","");
    }
    return myfind;
}
function ddl_Bind(ddl_name,bindData,keyword){
    var obj = document.getElementById(ddl_name);
    for( var i = 0;i < bindData.length; i++ ){
        if(bindData[i][2] == keyword){
              if(i == 0){
                  if(bindData[i][0] != ""){
                      obj.add(new Option(bindData[i][0],bindData[i][1]));
                  }
              }
              else{
                  obj.add(new Option(bindData[i][0],bindData[i][1]));
              }
        }
    }
    var num = 0;
    var subValue = obj.options[num].value;
    ddl_selected(ddl_name, num, true)
    ddl_changed('To', subValue, strArea);
}
function ddl_changed(ddl_name,keywords,ddl_data){
    var obj = document.getElementById(ddl_name);
    var m = 0;

    ddl_Clear(ddl_name);
    for( var i = 0; i < ddl_data.length; i++ ){
        if( ddl_data[i][2] == keywords ){
            obj.options[m] = ( new Option( ddl_data[i][0],ddl_data[i][3] ) );
            m = m + 1;
        }
    }
    obj.fireEvent("onchange");
}
function GoToMoney(){
	var Money = $("#GoToMoney").val();
	var Go	= $("#Go").val();
	var To	= $("#To").val();
	var Key	= $("#Key").val();
	var GoToType = $("#GoToType").val();
	if(Money==''){
		alert("请输入要转换的金额！");
		return false;
	}
	$("#loading").show();
	$("#button").attr("disabled","true");
	$.post("GoTo_Money.php", {T:Math.random() ,Go:Go ,To:To , Money:Money ,Key:Key ,GoToType:GoToType}, function(data)
	{
		alert(data.txt);
		$("#loading").hide();
		location.reload();
	}, "json");
}
function TakeMoney(){
	var Money = $("#Money").val();
	var MoneyPass	= $("#MoneyPass").val();
	var Key	= $("#Key").val();
	var Num	= $("#Num").val();
	if(Money==''){
		alert("请输入提款金额！");
		return false;
	}
	$("#loading").show();
	if(Num>5){
		if(confirm('您今日的提款已经超过【5次】！\n\n每笔提款我们将收取50元手续费作为行政费用！\n\n您确定还要继续提款吗？')){
			$("#button").attr("disabled","true");
			$.post("Take_Money.php", {T:Math.random() ,Money:Money ,MoneyPass:MoneyPass ,Key:Key , Num:Num}, function(data)
			{
				if(data.ok>0){
					$("#loading").hide();
					alert('提款申请成功！\n\n财务部门将在审核过后，将您的提款金额存入您的提款账号中！\n\n您也可以到会员中心【提现记录】里查询您的提款状态！');
					Go('TakeList.php');
					return false;
				}
				if(data.ok==0){
					$("#loading").hide();
					alert(data.txt);
					location.reload();
					return false;
				}
			}, "json");
		}else{
			return false;
		}
	}else{
		$("#button").attr("disabled","true");
		$.post("Take_Money.php", {T:Math.random() ,Money:Money ,MoneyPass:MoneyPass ,Key:Key , Num:Num}, function(data)
		{
			if(data.ok>0){
				$("#loading").hide();
				alert('提款申请成功！\n\n财务部门将在审核过后，将您的提款金额存入您的提款账号中！\n\n您也可以到会员中心【提现记录】里查询您的提款状态！');
				Go('TakeList.php');
				return false;
			}
			if(data.ok==0){
				$("#loading").hide();
				alert(data.txt);
				location.reload();
				return false;
			}
		}, "json");
	}

}

//*******************************************************************************


//加载平台金额
function loadAllMoney(){
	$("#allBnt").attr("disabled","disabled");
	$("#ag_img").attr("src",root+"/member/images/load.gif");
	$("#mg_img").attr("src",root+"/member/images/load.gif");
	$("#bbin_img").attr("src",root+"/member/images/load.gif");
	$("#ds_img").attr("src",root+"/member/images/load.gif");
	$("#hg_img").attr("src",root+"/member/images/load.gif");
	//$("#nt_img").attr("src",root+"/member/images/load.gif");
	$("#pt_img").attr("src",root+"/member/images/load.gif");
	/*$("#douji_img").attr("src",root+"/member/images/load.gif");*/
	$("#ab_img").attr("src",root+"/member/images/load.gif");
	$("#og_img").attr("src",root+"/member/images/load.gif");
	$("#os_img").attr("src",root+"/member/images/load.gif");
	$("#sb_img").attr("src",root+"/member/images/load.gif");
	$("#gd_img").attr("src",root+"/member/images/load.gif");
	$("#ttg_img").attr("src",root+"/member/images/load.gif");
	$("#sbt_img").attr("src",root+"/member/images/load.gif");
	$("#newnt_img").attr("src",root+"/member/images/load.gif");
	$("#sa_img").attr("src",root+"/member/images/load.gif");
	$("#vg_img").attr("src",root+"/member/images/load.gif");
	$.post(rootPath+"/member/getBlanceMoney?flatName=ag", {type:0 ,t:Math.random()}, function(data)
	{
		if(data.rs){
			var htmlStr = "<font style=\"font-size: 24px;height: 24px;line-height: 35px;color: red;\">"+data.datas.flatMoney+"</font>";
			$("#ag_money").html(htmlStr);
		}

	}, "json");

	$.post(rootPath+"/member/getBlanceMoney?flatName=mg", {type:0 ,t:Math.random()}, function(data)
	{
		if(data.rs){
			var htmlStr = "<font style=\"font-size: 24px;height: 24px;line-height: 35px;color: red;\">"+data.datas.flatMoney+"</font>";
			$("#mg_money").html(htmlStr);
		}

	}, "json");


	$.post(rootPath+"/member/getBlanceMoney?flatName=bbin", {type:0 ,t:Math.random()}, function(data)
	{
		if(data.rs){
			var htmlStr = "<font style=\"font-size: 24px;height: 24px;line-height: 35px;color: red;\">"+data.datas.flatMoney+"</font>";
			$("#bbin_money").html(htmlStr);
		}

	}, "json");

	$.post(rootPath+"/member/getBlanceMoney?flatName=ds", {type:0 ,t:Math.random()}, function(data)
	{
		if(data.rs){
			var htmlStr = "<font style=\"font-size: 24px;height: 24px;line-height: 35px;color: red;\">"+data.datas.flatMoney+"</font>";
			$("#ds_money").html(htmlStr);
		}
	}, "json");

	$.post(rootPath+"/member/getBlanceMoney?flatName=hg", {type:0 ,t:Math.random()}, function(data)
	{
		if(data.rs){
			var htmlStr = "<font style=\"font-size: 24px;height: 24px;line-height: 35px;color: red;\">"+data.datas.flatMoney+"</font>";
			$("#hg_money").html(htmlStr);
		}
	}, "json");
	/*$.post(rootPath+"/member/getBlanceMoney?flatName=nt", {type:0 ,t:Math.random()}, function(data)
	{
		if(data.rs){
			var htmlStr = "<font style=\"font-size: 24px;height: 24px;line-height: 35px;color: red;\">"+data.datas.flatMoney+"</font>";
			$("#nt_money").html(htmlStr);
		}
	}, "json");*/
	$.post(rootPath+"/member/getBlanceMoney?flatName=pt", {type:0 ,t:Math.random()}, function(data)
	{
		if(data.rs){
			var htmlStr = "<font style=\"font-size: 24px;height: 24px;line-height: 35px;color: red;\">"+data.datas.flatMoney+"</font>";
			$("#pt_money").html(htmlStr);
		}
	}, "json");
	/*$.post(rootPath+"/member/getBlanceMoney?flatName=douji", {type:0 ,t:Math.random()}, function(data)
			{
				if(data.rs){
					var htmlStr = "<font style=\"font-size: 24px;height: 24px;line-height: 35px;color: red;\">"+data.datas.flatMoney+"</font>";
					$("#douji_money").html(htmlStr);
				}
	}, "json");*/

	$.post(rootPath+"/member/getBlanceMoney?flatName=ab", {type:0 ,t:Math.random()}, function(data)
			{
				if(data.rs){
					var htmlStr = "<font style=\"font-size: 24px;height: 24px;line-height: 35px;color: red;\">"+data.datas.flatMoney+"</font>";
					$("#ab_money").html(htmlStr);
				}
	}, "json");
	$.post(rootPath+"/member/getBlanceMoney?flatName=og", {type:0 ,t:Math.random()}, function(data)
			{
				if(data.rs){
					var htmlStr = "<font style=\"font-size: 24px;height: 24px;line-height: 35px;color: red;\">"+data.datas.flatMoney+"</font>";
					$("#og_money").html(htmlStr);
				}
	}, "json");
	$.post(rootPath+"/member/getBlanceMoney?flatName=os", {type:0 ,t:Math.random()}, function(data)
			{
				if(data.rs){
					var htmlStr = "<font style=\"font-size: 24px;height: 24px;line-height: 35px;color: red;\">"+data.datas.flatMoney+"</font>";
					$("#os_money").html(htmlStr);
				}
	}, "json");
	$.post(rootPath+"/member/getBlanceMoney?flatName=sb", {type:0 ,t:Math.random()}, function(data)
			{
				if(data.rs){
					var htmlStr = "<font style=\"font-size: 24px;height: 24px;line-height: 35px;color: red;\">"+data.datas.flatMoney+"</font>";
					$("#sb_money").html(htmlStr);
				}
	}, "json");
	$.post(rootPath+"/member/getBlanceMoney?flatName=gd", {type:0 ,t:Math.random()}, function(data)
			{
				if(data.rs){
					var htmlStr = "<font style=\"font-size: 24px;height: 24px;line-height: 35px;color: red;\">"+data.datas.flatMoney+"</font>";
					$("#gd_money").html(htmlStr);
				}
	}, "json");
	$.post(rootPath+"/member/getBlanceMoney?flatName=ttg", {type:0 ,t:Math.random()}, function(data)
			{
				if(data.rs){
					var htmlStr = "<font style=\"font-size: 24px;height: 24px;line-height: 35px;color: red;\">"+data.datas.flatMoney+"</font>";
					$("#ttg_money").html(htmlStr);
				}
	}, "json");
	$.post(rootPath+"/member/getBlanceMoney?flatName=sbt", {type:0 ,t:Math.random()}, function(data)
			{
				if(data.rs){
					var htmlStr = "<font style=\"font-size: 24px;height: 24px;line-height: 35px;color: red;\">"+data.datas.flatMoney+"</font>";
					$("#sbt_money").html(htmlStr);
				}
	}, "json");

	$.post(rootPath+"/member/getBlanceMoney?flatName=newnt", {type:0 ,t:Math.random()}, function(data)
			{
				if(data.rs){
					var htmlStr = "<font style=\"font-size: 24px;height: 24px;line-height: 35px;color: red;\">"+data.datas.flatMoney+"</font>";
					$("#newnt_money").html(htmlStr);
				}
	}, "json");

	$.post(rootPath+"/member/getBlanceMoney?flatName=sa", {type:0 ,t:Math.random()}, function(data)
			{
				if(data.rs){
					var htmlStr = "<font style=\"font-size: 24px;height: 24px;line-height: 35px;color: red;\">"+data.datas.flatMoney+"</font>";
					$("#newnt_money").html(htmlStr);
				}
	}, "json");

	$.post(rootPath+"/member/getBlanceMoney?flatName=vg", {type:0 ,t:Math.random()}, function(data)
			{
				if(data.rs){
					var htmlStr = "<font style=\"font-size: 24px;height: 24px;line-height: 35px;color: red;\">"+data.datas.flatMoney+"</font>";
					$("#newnt_money").html(htmlStr);
				}
	}, "json");

	setTimeout(function test(){
		$("#allBnt").removeAttr("disabled");
	},5000);

}

//加载账户金额
function loadMoney(flatName){
	$("#"+flatName+"_img").attr("src",root+"/member/images/load.gif");
	$.post(rootPath+"/member/getBlanceMoney?flatName="+flatName, {type:0 ,t:Math.random()}, function(data)
	{
		if(data.rs){
			var htmlStr = "<font style=\"font-size: 24px;height: 24px;line-height: 35px;color: red;\">"+data.datas.flatMoney+"</font>";

//			$("#"+flatName+"_money").html("￥"+data.datas.flatMoney);
			$("#"+flatName+"_money").html(htmlStr);
		}

	}, "json");
}

/*******************************************************************************
 * 会员修改密码
 *
 * @return
 */
function checkEditPwdForm()
{
	/** 1.数据校验* */
	var oldPwd = $("#oldPwd");
    var newPwd = $("#newPwd");
    var renewPwd = $("#renewPwd");
    if (!CheckUserPassword(oldPwd.val())) {
		alert("请输入规范的原登录密码");
		return false;
	}

	if (!CheckUserPassword(newPwd.val())) {
		alert("请输入规范的新登录密码");
		return false;
	}

	if (!CheckUserPassword(renewPwd.val())) {
		alert("请输入规范的确认密码");
		return false;
	}

	if (newPwd.val()!=renewPwd.val()) {
		alert("两次输入的新密码不一样");
		return false;
	}

	var reFlag = false;
    if(reFlag){
   	 return;
    }
    layer.confirm('OK?', {icon: 3, title: '确认提交的信息'}, function(index){
	    layer.close(index);
		$.ajax( {
			url : rootPath+"/member/doLoginPwd",
			type : "POST",
			data : {oldPwd:oldPwd.val(),newPwd:newPwd.val(),renewPwd:renewPwd.val()},
			dataType : "json",
			timeout:30000,
			error: function(){
				reFlag = true;
			    alert("修改登录密码出错~");
			},
			beforeSend: function(){
				 $('.progress-body-step1').hide();
			     $('.progress-body-step2').load(root+'/member/pages/info-step2.html');
			     $('.progress-body-step2').show();
			     $('.step-one').removeClass('step-active');
			     $('.step-two').addClass('step-active');
			},
			success : function(data) {
				reFlag = true;
				if(data.rs){
					$("#setp3Ts").html("修改成功");
					alert(data.msg);
				}else{
					$("#setp3Ts").html("修改失败");
					alert(data.msg);
				}
			},complete: function () {
				reFlag = true;
				 $('.progress-body-step2').hide();
				 $('.progress-body-step3').show();
				 $('.step-two').removeClass('step-active');
				 $('.step-three').addClass('step-active');
			}
		});
		oldPwd.val("");
		newPwd.val("");
		renewPwd.val("");
	 });

}

/*******************************************************************************
 * ajax请求前，弹出层
 *
 * @return
 */
function ajaxReqLoading(){
	var loadingDiv = $("#loading");
	if (loadingDiv.length == 0) {
		var mask="<div id='m'></div>";
		var loading="<div id='lo'><div><img src='"+root+"/member/images/load.gif'/></div><div style='margin-top:4px'></div></div></center>";
		$("body").prepend(loading).prepend(mask);
		$("#lo").css("position","absolute").css("width","32px").css("height","32px").css("text-align","center").css("top","150px").css("left","474px").css("z-index","9999");
		$("#m").width($(document).width()).height($(document).height()).css("background-color","#F5F4F4").css("filter","alpha(opacity=60)").css("-moz-opacity","0.4").css("position","absolute").css("z-index","0");
	}
}

/*******************************************************************************
 * ajax请求
 *
 * @param url
 * @param reqData
 * @param frm
 * @return
 */
function ajaxReqAction(url,reqData,fun){
	$.ajax( {
		url : url,
		type : "POST",
		data : reqData,
		dataType : "json",
		timeout:30000,
		  contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		    error: function(){
		        alert("用户退出系统出错~");
		    },
		    success: function(obj){
		    	alert(obj.msg);
		    }
	});
}

/*******************************************************************************
 * 清除表单内容
 *
 * @param frm
 * @return
 */
function clearForm(frm){
	$(':input','#'+frm)
	.not(':button, :submit, :reset, :hidden')
	.val('')
	.removeAttr('checked')
	.removeAttr('selected');
}

function CheckUserPassword(pwd) {
	var re = /^[a-z0-9]{6,12}$/i;
	var user_password = re.test(pwd);
	if (!user_password) {
		return false;
	}
	return true;
}

function CheckUserMoneyPassword(pwd) {
	var re = /^[0-9]{4}$/i;
	var user_password = re.test(pwd);
	if (!user_password) {
		return false;
	}
	return true;
}


/*******************************************************************************
 * 会员修改取款密码
 *
 * @return
 */
function checkWithdrawPwdForm()
{
	/** 1.数据校验* */
	var oldPwd = $("#oldPwd2");
    var newPwd = $("#newPwd2");
    var renewPwd = $("#renewPwd2");
	    if (!CheckUserMoneyPassword(oldPwd.val())) {
			alert("请输入规范的原资金密码");
			return false;
		}

		if (!CheckUserMoneyPassword(newPwd.val())) {
			alert("请输入规范的新资金密码");
			return false;
		}

		if (!CheckUserMoneyPassword(renewPwd.val())) {
			alert("请输入规范的确认密码");
			return false;
		}

		if (newPwd.val()!=renewPwd.val()) {
			alert("两次输入的新密码不一样");
			return false;
		}

		var reFlag = false;
	     if(reFlag){
	    	 return;
	     }
	     layer.confirm('OK?', {icon: 3, title: '确认提交的信息'}, function(index){
		    layer.close(index);
			$.ajax( {
				url : rootPath+"/member/doWithdrawPwd",
				type : "POST",
				data : {oldPwd:oldPwd.val(),newPwd:newPwd.val(),renewPwd:renewPwd.val()},
				dataType : "json",
				timeout:30000,
				error: function(){
					reFlag = true;
				    alert("修改资金密码出错~");
				},
				beforeSend: function(){
					 $('.progress-body-step1').hide();
				     $('.progress-body-step2').load(root+'/member/pages/info-step2.html');
				     $('.progress-body-step2').show();
				     $('.step-one').removeClass('step-active');
				     $('.step-two').addClass('step-active');
				},
				success : function(data) {
					reFlag = true;
					if(data.rs){
						$("#setp3Ts").html("修改成功");
					}else{
						$("#setp3Ts").html("修改失败");
					}
					alert(data.msg);
				},
				complete: function () {
					reFlag = true;
					 $('.progress-body-step2').hide();
					 $('.progress-body-step3').show();
					 $('.step-two').removeClass('step-active');
					 $('.step-three').addClass('step-active');
				}
			});
			oldPwd.val("");
			newPwd.val("");
			renewPwd.val("");
		 });

}

/*******************************************************************************
 * 银行支付校验
 *
 * @return
 */
function SubInfo(){


	// var hkMoney = $("#form1 input[id='hkMoney']");
	 var hkMoney = $(document.getElementById("hkMoney"));
	 var hkCompanyBank = $(document.getElementById('hkCompanyBank'));
	 var time = $(document.getElementById("time"));
	 var hkType = $(document.getElementById("hkType"));
	 var hkUserName = $(document.getElementById("hkUserName"));
	 var timeMinute = $(document.getElementById("timeMinute"));
	 var timeHour = $(document.getElementById("timeHour"));
	 var key = $(document.getElementById("key"));

	 var minPay = $("#minPay");

	 if(hkMoney.val()=="")
     {
         alert("存款金额不能为空！");
         hkMoney.focus();
         return false;
     }

     if(parseFloat(hkMoney.val())<parseFloat(minPay.val())){
    	 alert('公司入款最低'+minPay.val()+'元！');
    	 hkMoney.focus();
    	 return false;
     }


     if(hkCompanyBank.val()=="")
     {
         alert("请选择汇入银行！");
         hkCompanyBank.focus();
         return false;
     }


     if(time.val()==""){
     	 alert("入款时间不能为空！");
         time.focus();
         return false;
     }

     if(hkType.val()=="")
     {
         alert("请选择汇款方式！");
         hkType.focus();
         return false;
     }

     if(hkUserName.val()=="" || $.trim(hkUserName.val())==""){
     	alert("存款人姓名不能为空！");
     	hkUserName.focus();
         return false;
     }

     var reFlag = false;
     if(reFlag){
    	 return;
     }
     layer.confirm('OK?', {icon: 3, title: '确认提交的信息'}, function(index){
	    layer.close(index);
	    $('.progress-body-step1').hide();
		$.ajax( {
			url : rootPath+"/member/doBankPay",
			type : "POST",
			data : $("#form1").serialize(),
			dataType : "json",
			timeout:30000,
			error: function(){
				reFlag = true;
			    alert("入款出错~");
			},
			beforeSend: function(){
				 $('.progress-body-step1').hide();
			     $('.progress-body-step2').load(root+'/member/pages/info-step2.html');
			     $('.progress-body-step2').show();
			     $('.step-one').removeClass('step-active');
			     $('.step-two').addClass('step-active');
			},
			success : function(data) {
				reFlag = true;
				if(data.rs){
					$("#setp3Ts").html("申请成功");
					alert(data.msg);
				}else{
					$("#setp3Ts").html("申请失败");
					alert(data.msg);
				}

			},complete: function () {
				reFlag = true;
				 $('.progress-body-step2').hide();
				 $('.progress-body-step3').show();
				 $('.step-two').removeClass('step-active');
				 $('.step-three').addClass('step-active');
			}
		});
    	 hkMoney.val('');
    	 hkUserName.val('');
	 });

}

/**
 * 快捷支付
 */
function SubKjInfo()
{
	 var hkMoney = $(document.getElementById("hkMoney"));
	 var hkUserName = $(document.getElementById("hkUserName"));
	 var minPay = $("#minPay");

	 if(hkMoney.val()=="")
	 {
        alert("存款金额不能为空！");
        hkMoney.focus();
        return false;
	 }
	 if(parseFloat(hkMoney.val())<parseFloat(minPay.val()))
	 {
		 alert('公司入款最低'+minPay.val()+'元！');
		 hkMoney.focus();
		 return false;
	 }
	 if(hkUserName.val()=="")
	 {
		 alert("存款人姓名不能为空！");
		 hkUserName.focus();
		 return false;
	 }
	 var reFlag = false;
	 if(reFlag)
	 {
		 return;
	 }
	 layer.confirm('OK?', {icon: 3, title: '确认提交的信息'}, function(index)
	 {
	    layer.close(index);
	    $('.progress-body-step1').hide();
		$.ajax( {
			url : rootPath+"/member/doKjPay",
			type : "POST",
			data : $("#form1").serialize(),
			dataType : "json",
			timeout:30000,
			error: function()
			{
				reFlag = true;
			    alert("入款出错~");
			},
			beforeSend: function()
			{
				 $('.progress-body-step1').hide();
			     $('.progress-body-step2').load(root+'/member/pages/info-step2.html');
			     $('.progress-body-step2').show();
			     $('.step-one').removeClass('step-active');
			     $('.step-two').addClass('step-active');
			},
			success : function(data)
			{
				reFlag = true;
				if(data.rs)
				{
					$("#setp3Ts").html("申请成功");
					alert(data.msg);
				}
				else
				{
					$("#setp3Ts").html("申请失败");
					alert(data.msg);
				}
			},
			complete: function ()
			{
				reFlag = true;
				 $('.progress-body-step2').hide();
				 $('.progress-body-step3').show();
				 $('.step-two').removeClass('step-active');
				 $('.step-three').addClass('step-active');
			}
		});
   	 	hkMoney.val('');
   	 	hkUserName.val('');
	 });
}


/*******************************************************************************
 * 在线支付校验
 *
 * @return
 */

function SubInfoForOnlinePay(){
	$('.step-one',parent.document.body).removeClass('step-active');
	$('.step-two',parent.document.body).addClass('step-active');
	SubInfoForOnline();
}


function SubInfoForOnline(){
	var Money = $('#money').val();
	var Billno = $('#billno').val();

	var min = $('#thirdMinEdu').val();
	var max = $('#thirdMaxEdu').val();
	var bank_code = $('#bank_code').val();
	if(Money==''||bank_code==''){
		alert('请填写完整表单！');
		return false;
	}
	if(parseInt(Money)<parseInt(min)||parseInt(Money)>parseInt(max)){
		alert('单笔支付金额范围（ '+min+" ~ "+max+'） 元！');
		return false;
	}

	payForm.submit();

	layer.close(1);
	$('.progress-body-step1').hide();
	$('.progress-body-step2').load(root+'/member/pages/recharge-bank-step2.html');
	$('.progress-body-step2').show();
	$('.step-one').removeClass('step-active');
	$('.step-two').addClass('step-active');


	   //return true;


}

function ajaxReqForOnlineAction(url,reqData){
	$.ajax( {
		url : url,
		type : "POST",
		data : reqData,
		dataType : "json",
		success : function(data) {
		document.getElementById("button").disabled = true;
			$('#form1').submit();
			/*
			 * if(fun!='undefined'){ fun(data.optResult);// 回调函数 }
			 */
		}
	});
}
/*******************************************************************************
 * 在线付款弹出层
 *
 * @return
 */
function ajaxFormLoading(){
	var timenow = new Date().getTime();
	var loadingDiv = $("#loading");
//	if (loadingDiv.length == 0) {
//		var mask="<div id='m'></div>";
//		var loading="<div id='lo' style='border-style: none;'><div><a href='javascript:void(0)' onclick=gotoPay3('"+rootPath+"/pay/checkPayResult?billno="+billno+"')>点击这里查看支付结果</a></div><div style='margin-top:4px'></div></div></center>";
//		$("body").prepend(loading).prepend(mask);
//		$("#lo").css("position","absolute").css("width","200px").css("height","35px").css("text-align","center").css("top","150px").css("left","290px").css("z-index","9999");
//		$("#m").width($(document).width()).height($(document).height()-50).css("background-color","#F5F4F4").css("filter","alpha(opacity=60)").css("-moz-opacity","0.4").css("position","absolute").css("z-index","0");
//	}


}

/***第二步验证**/
function validPay(){
	var url = rootPath+"/pay/checkPayResult?billno="+billno;

	$("#payifram",parent.document.body).attr("src",url);
	try{
		$('.step-one',parent.document.body).removeClass('step-active');
		$('.step-two',parent.document.body).removeClass('step-active');
		$('.step-three',parent.document.body).addClass('step-active');
	}catch(e){}
	console.log(url);
}
/*******************************************************************************
 * 绑定银行卡
 *
 * @return
 */
function bankSaveCheck(){

	 var userBankName = $("#userBankName");
	 var userBank = $("#userBank");
	 var userBankProvince = $("#userBankProvince");
	 var userBankCity = $("#userBankCity");
	 var userWithdrawPassword = $("#userWithdrawPassword");

	 if(userBankName.val()==""||userBank.val()==""||userBankProvince.val()==""||userBankCity.val()==""||userBankCity.val()==""||userWithdrawPassword.val()=="")
     {
         alert("请填写完整的表单信息！");
         return false;
     }


     if(!confirm("是否确定写入？")){
    	 return false;
     }


 	/** 3.请求* */
 	$.ajax({
	    url: rootPath+"/member/saveBackInfo",
	    type: "post",
	    data: $("#form1").serialize(),
	    timeout : 30000,
	    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	   	dataType:"json",
	    error: function(){
	        alert("绑定银行卡出错~");
	    },
	    success: function(obj){
	    	if(obj.rs){
	    		alert("绑定银行卡成功！");
	    		parent.document.getElementById('memberFrame').src =rootPath+"/member/accountInfo";
	    	}else{
	    		alert(obj.msg);
	    	}


		}
	});
}

/*******************************************************************************
 * 提款校验
 *
 * @return
 */
function withdrawMoneyCheck(){
	 var money = $("#form1 input[id='money']");
	 var userWithdrawPassword = $("#form1 input[id='userWithdrawPassword']");

	 if(money.val()=="")
    {
        alert("请输入提款金额！");
        return false;
    }

	 if(money.val()<100){
		 alert("最低提款为：100元!");
		 return false;
	 }

	 if(userWithdrawPassword.val()==""){
        alert("请输入资金密码！");
        return false;
    }


    var reFlag = false;
    if(reFlag){
   	 	return;
    }
    layer.confirm('是否确定写入?', {icon: 3, title: '确认提交的信息'}, function(index){
	    layer.close(index);
	    $('.progress-body-step1').hide();
		$.ajax( {
			url : rootPath+"/member/saveWithdraw",
			type : "POST",
			data: {'money':money.val(),'userWithdrawPassword':userWithdrawPassword.val()},
			dataType : "json",
			timeout:30000,
			error: function(){
				reFlag = true;
			    alert("申请提款出错~");
			},
			beforeSend: function(){
				 $('.progress-body-step1').hide();
			     $('.progress-body-step2').load(root+'/member/pages/info-step2.html');
			     $('.progress-body-step2').show();
			     $('.step-one').removeClass('step-active');
			     $('.step-two').addClass('step-active');
			},
			success : function(data) {
				reFlag = true;
				if(data.rs){
					$("#setp3Ts").html("申请提款成功");
					alert(data.msg);
				}else{
					$("#setp3Ts").html("申请提款失败");
					alert(data.msg);
				}

			},complete: function () {
				reFlag = true;
				 $('.progress-body-step2').hide();
				 $('.progress-body-step3').show();
				 $('.step-two').removeClass('step-active');
				 $('.step-three').addClass('step-active');
			}
		});
		money.val('');
		userWithdrawPassword.val('');
	 });

}

/*******************************************************************************
 * 提款回调函数
 *
 * @param optResult
 * @return
 */
function withdrawCallback(optResult){
	/** 清除表单数据* */
	$("#form1 input[id='money']").val('');
	$("#form1 input[id='userWithdrawPassword']").val('');
	/** 提款限制* */
	/**重新刷新页面**/
	window.location.href=rootPath+"/member/goWithdraw";

}


/*******************************************************************************
 * 额度转换,选择变动
 *
 * @return
 */
function selectFromFlat(){
	var selectFromFlat = $("#fromFlatName");
  	var selectToFlat = $("#toFlatName");
   	selectToFlat.empty();
	if(selectFromFlat.val()=="account"){
		selectToFlat.append('<option value="ag">AG平台</option>');
		selectToFlat.append('<option value="ds">DS平台</option>');
		selectToFlat.append('<option value="hg">HG平台</option>');
		selectToFlat.append('<option value="bbin">BBIN平台</option>');
		selectToFlat.append('<option value="mg">MG平台</option>');
		selectToFlat.append('<option value="pt">PT平台</option>');
		/*selectToFlat.append('<option value="nt">NT平台</option>');*/
		selectToFlat.append('<option value="newnt">NT平台</option>');
		selectToFlat.append('<option value="douji">斗鸡平台</option>');
		selectToFlat.append('<option value="ab">欧博平台</option>');
		selectToFlat.append('<option value="og">OG平台</option>');
		selectToFlat.append('<option value="os">OS平台</option>');
		selectToFlat.append('<option value="sb">沙巴平台</option>');
		selectToFlat.append('<option value="gd">GD平台</option>');
		selectToFlat.append('<option value="ttg">TTG平台</option>');
		selectToFlat.append('<option value="sbt">SBT平台</option>');
		selectToFlat.append('<option value="sa">SA平台</option>');
		selectToFlat.append('<option value="vg">VG平台</option>');
	}else{
		selectToFlat.append('<option value="account">总帐号金额</option>');
	}/*else if(selectFromFlat.val()=="ag"){
		selectToFlat.append('<option value="account">总帐号金额</option>');
	}else if(selectFromFlat.val()=="mg"){
		selectToFlat.append('<option value="account">总帐号金额</option>');
	}else if(selectFromFlat.val()=="jbb"){
		selectToFlat.append('<option value="account">总帐号金额</option>');
	}else if(selectFromFlat.val()=="bbin"){
		selectToFlat.append('<option value="account">总帐号金额</option>');
	}else if(selectFromFlat.val()=="ds"){
		selectToFlat.append('<option value="account">总帐号金额</option>');
	}else if(selectFromFlat.val()=="hg"){
		selectToFlat.append('<option value="account">总帐号金额</option>');
	}else if(selectFromFlat.val()=="newnt"){
		selectToFlat.append('<option value="account">总帐号金额</option>');
	}else if(selectFromFlat.val()=="pt"){
		selectToFlat.append('<option value="account">总帐号金额</option>');
	}else if(selectFromFlat.val()=="douji"){
		selectToFlat.append('<option value="account">总帐号金额</option>');
	}else if(selectFromFlat.val()=="ab"){
		selectToFlat.append('<option value="account">总帐号金额</option>');
	}else if(selectFromFlat.val()=="og"){
		selectToFlat.append('<option value="account">总帐号金额</option>');
	}else if(selectFromFlat.val()=="os"){
		selectToFlat.append('<option value="account">总帐号金额</option>');
	}else if(selectFromFlat.val()=="sb"){
		selectToFlat.append('<option value="account">总帐号金额</option>');
	}else if(selectFromFlat.val()=="gd"){
		selectToFlat.append('<option value="account">总帐号金额</option>');
	}else if(selectFromFlat.val()=="ttg"){
		selectToFlat.append('<option value="account">总帐号金额</option>');
	}else if(selectFromFlat.val()=="sbt"){
		selectToFlat.append('<option value="account">总帐号金额</option>');
	}else if(selectFromFlat.val()=="sa"){
		selectToFlat.append('<option value="account">总帐号金额</option>');
	}*/
	selectToFlat.selectmenu('refresh', true);
}

/*******************************************************************************
 * 额度转换
 *
 * @return
 */
function checkUserEduAccess()
{
	 var wPoints = $("#form1 input[id='wPoints']");	//转出额度
    var fromFlatName = $("#form1 select[id='fromFlatName']");//转出平台
    var toFlatName = $("#form1 select[id='toFlatName']");//转入平台

    if(wPoints.val()=="")
    {
        alert("请输入要转换的金额！");
        wPoints.focus();
        return false;
    }
    if(wPoints.val()<10)
    {
        alert("最低转账额度为:10元");
        wPoints.focus();
        return false;
    }
	var eduFlag = false;
	$.ajax({
		url : rootPath+"/member/checkEdu",
		type: "post",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		timeout : 30000,
		dataType :"json",
		data :{'wPoints':wPoints.val(),'fromFlatName':fromFlatName.val(),'toFlatName':toFlatName.val()},
		cache : false,
		async:false,
		error:function(){
			alert("网络连接有问题!");
		},
		success : function(obj) {
			if (!obj.rs) {
				eduFlag = true;
				alert(obj.msg);
			}
		}
	});
	if(eduFlag){
		return;
	}

	 var reFlag = false;
     if(reFlag){
    	 return;
     }
     layer.confirm('是否确定写入?', {icon: 3, title: '确认提交的信息'}, function(index){
	    layer.close(index);

		$.ajax( {
			url : rootPath+"/member/doEdu",
			type : "POST",
			data :{'wPoints':wPoints.val(),'fromFlatName':fromFlatName.val(),'toFlatName':toFlatName.val()},
			dataType : "json",
			timeout:30000,
			error: function(){
				reFlag = true;
			    alert("额度转换出错~");
			},
			beforeSend: function(){
				 $('.progress-body-step1').hide();
			     $('.progress-body-step2').load(root+'/member/pages/info-step2.html');
			     $('.progress-body-step2').show();
			     $('.step-one').removeClass('step-active');
			     $('.step-two').addClass('step-active');
			},
			success : function(data) {
				reFlag = true;
				if(data.rs){
					$("#setp3Ts").html("转换成功");
					alert(data.msg);
				}else{
					$("#setp3Ts").html("转换失败");
					alert(data.msg);
				}

			},complete: function () {
				reFlag = true;
				 $('.progress-body-step2').hide();
				 $('.progress-body-step3').show();
				 $('.step-two').removeClass('step-active');
				 $('.step-three').addClass('step-active');
			}
		});
		wPoints.val('');
	 });

}

function checkUserEduForm(){

	var fromFlatName = $("#form1 select[id='fromFlatName']");
    var toFlatName = $("#form1 select[id='toFlatName']");
    var code = fromFlatName.val();
    if("account" == code){
    	code=toFlatName.val();
    }else{
    	code = fromFlatName.val();
    }
	$.ajax({
		url : rootPath+"/valid/checkflatsatus",
		type: "post",
		data : {"code":code},
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		timeout : 30000,
		dataType :"json",
		cache : false,
		success : function(obj) {
			if (obj.datas.status) {
				alert(obj.datas.msg);
				return;
			}else{
				checkUserEduAccess();
			}
		}
	});
}


/*******************************************************************************
 * 额度转换回调
 *
 * @param optResult
 * @return
 */
function eduChangeCallback(optResult){
	 $("#form1 input[id='wPoints']").val('');
	 LoadMoney();
}

/*******************************************************************************
 * 删除会员消息
 *
 * @param id
 * @return
 */
function userDeleteMessage(id){
	    if(!confirm("是否确定删除？")){
	   	 return false;
	    }

	    /** 2.加载层* */
		ajaxReqLoading();

		/** 3.请求* */
		var reqData={

					};
		ajaxReqAction("/member/message!deleteUserMessage.do?id="+id,reqData,deleteUserMessageCallback);
}
function deleteUserMessageCallback(optResult){
	 Go('/member/message!listForUser.do');
}

/**
 * MG记录
 * @return
 */
function mgBetRecord(){
   /** 2.加载层* */
	ajaxReqLoading();
	/** 3.请求* */
	var reqData={};
	ajaxReqNoAlertAction("/member/ajaxct!getMgBetRecordUrl.do",reqData,mgBetRecordCallBack);
}

function ajaxReqNoAlertAction(url,reqData,fun){
	$.ajax( {
		url : url,
		type : "POST",
		data : reqData,
		dataType : "json",
		success : function(data) {
			$("#m").remove();
			$("#lo").remove();

			if(typeof fun =='function'){// 若这个参数有值，且是函数时回调
				fun(data.url);
			}
		}
	});
}

function mgBetRecordCallBack(url){
	Go(url);
}

/*******************************************************************************
 * 代理申请
 *
 * @return
 */
function agentApplayCheck(){
	 var mail =  $("#agentMail");
	 var content = $("#content");
	 var agentType = $("#agentType");

	 if(mail.val()=="")
    {
        alert("请输入邮箱地址！");
        return false;
    }
	var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
	if(!pattern.test(mail.val())){
		 alert("邮箱地址格式不正确!");
		 return false;
	}


	if(content.val()==""){
		 alert("请填写您的申请理由!");
		 return false;
	}

    if(!confirm("是否确定写入？")){
   	 return false;
    }

    var reFlag = false;
    if(reFlag){
   	 return;
    }

	$.ajax( {
		url : rootPath+"/member/insertApply",
		type : "POST",
		data : {'agentMail':mail.val(),'content':content.val(), 'agentType':agentType.val()},
		dataType : "json",
		timeout:30000,
		error: function(){
			reFlag = true;
		    alert("申请代理出错。。。");
		},
		success : function(data) {
			reFlag = true;
			if(data.rs){
				 alert("代理申请成功!");
				 Go(rootPath+"/member/agentDetail");
			}else{
				 alert("代理申请失败，请联系客服!");
			}
		}
	});




}

/*******************************************************************************
 * 代理申请回调函数
 *
 * @param optResult
 * @return
 */
function applyAgentCallback(optResult){
	/** 清除表单数据* */
	$("#agentMail").val('');
	$("#content").val('');
	$("#agentType").val();
	window.location.href=rootPath+"/member/agentDetail";
}

function gotoPay3(value){
	var url = value;
	$("#payifram",parent.document.body).attr("src",url);
	try{
		$('.step-one',parent.document.body).removeClass('step-active');
		$('.step-two',parent.document.body).removeClass('step-active');
		$('.step-three',parent.document.body).addClass('step-active');
	}catch(e){}

}

function refreshUserMoney(){
	$.ajax({
	    url: rootPath+"/valid/refreshUserMoney",
	    type: "post",
	    data: null,
	    timeout : 30000,
	    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	   	dataType:"json",
	    error: function(){
	        //alert("刷新金额出错~");
	    },
	    success: function(obj){
	    	if(obj.rs){
	    		var data= obj.datas;
	    		$("#userMoneyId").html("￥"+data.userMoney);
	    		$("#userMessageId").html(data.msgTotal);
	    		//公告
	    		$("#msgClass").hide();
	    		if(data.msgTotal > 0 )
	    		{
	    			$("#msgClass").show();
	    		}
	    		if(data.msgTotal == 0)
    			{
	    			initMsgCount = 0;
    			}
	    		//如果成立,说明有新消息
	    		if(data.msgTotal > initMsgCount)
	    		{
	    			initMsgCount++;
	    			var player = document.getElementById("player");
					player.play();
	    		}
	    		else
	    		{
	    			initMsgCount = data.msgTotal;
	    		}
	    	}
		}
	});

}

