<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="mh" uri="http://www.mh.com/framework/tags" %>
<%
 
	String reFlag = (String)request.getSession().getAttribute("PARAM_S");
	if ("0".equals(reFlag)) {
		request.getSession().removeAttribute("PARAM_S");
		out.flush();
		out.println("<script>");
		out.println("alert('您尚未登录，请先登录再进行游戏!');");
		out.println("</script>");
		
	}
%>
<style>
<!--
.randPic{
vertical-align: bottom;
margin-bottom: 1px;
margin-left: 3px;
width: 46px;
height: 23px
}
-->
</style>
<script type="text/javascript">
		
	String.prototype.trim = function() {
	    return this.replace(/(^\s+)|(\s+$)/g, "");
	};
	
	var Verify=0;
	var val = "0123456789abcdefghijklmnopqrstuvwxyz_ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	var p = "";
	function sendForm(){
		var s,v;
		s=document.getElementById("loginName").value.trim();
		p=document.getElementById("password").value.trim();
		v=document.getElementById("verifycode").value.trim();
		
		if(Verify == 1 && v==""){
			alert("请输入验证码");
			document.getElementById("verifycode").focus();
			return false;
		}
		if(s==""||p==""){
			alert("请输入帐号密码");
			document.getElementById("loginName").focus();
			return false;
		}
		for(var i=0; i<s.length; i++){
			if(val.indexOf(s.charAt(i)) == -1){
				alert("帐号包含非法字符 ["+s.charAt(i)+"]");
				return false;
			}
		}
		
 		var rp = /^[a-zA-Z0-9]{6,12}$/;//密码规范
	/* 	if (!rp.test(p)) {
			alert("密码不符合规范");
			document.getElementById("password").focus();
			return false;
		} */

		
		if (v == "" || v.Leng == 0) {
			alert("验证码不能为空！");
			document.getElementById("verifycode").focus();
			return false;
		}
		var rpp = /^[0-9]{4}$/;//验证码
		if (!rpp.test(v)) {
			alert("请输入四位数字验证码");
			document.getElementById("verifycode").focus();
			return false;
		}

		//$("#password").val(hex_sha1($("#password").val()).toUpperCase());
		return true;
	}

	function login() {
		if (!sendForm())
			return false;
		var dates = $("#loginForm").serialize();
		$.ajax({
			url : "${ctx}/login",
			 type: "post",
			data : dates,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			timeout : 30000,
			dataType :"json",
			cache : false,
			error : function() {
				alert("用户登入网络连接出错~");
				window.location.reload();
			},
			success : function(obj) {
				//重启服务器，令牌失效，重新获取令牌
				if (typeof (obj) == 'undefined' || obj == null) {
					window.location.reload();
				}
				if (obj.rs) {
					window.location.href = "${ctx}/loginRule?loginFlag=ok";
				} else {
					$("#password").val(p);
					$("#verifycode").val("");
					createCode();
					alert(obj.msg);
					$("#_form_token_uniq_id").val(obj.token.token);
				}
			}
		});
	}

	function createCode() {
		$("#img_validateCode").attr("src","${ctx}/resources-code.jpg?" + new Date().getTime());
	}
	
	
	function loginOut(){
		$.ajax({
			    url: "${ctx}/loginOut",
			    type: "post",
			    data: null,
			    timeout : 30000,
			    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			   	dataType:"json",
			    error: function(){
			        alert("用户退出系统出错~");
			    },
			    success: function(obj){
			    	if(obj.rs){
			    		window.location.href="${ctx}";
			    	}else{
			    		 alert("用户退出系统出错~");
			    	}
				}
			});
	}
	
	
	
	function refreshUserMoney(){
		$.ajax({
		    url: "${ctx}/valid/refreshUserMoney",
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
		    		$("#userMoneyId").html(data.userMoney+"RMB");
		    		$("#userMessageId").html(data.msgTotal);
		    		//公告
		    		
		    		
		    	}
			}
		});
	
	}

	$(document).ready(function() {
		//防止session过期时，index页面在子页面中打开
		if (self != top) {
			//window.top.location = "${ctx}/";
		}
		createCode();
		$("#loginName").keydown(function(event) {
			if (event.keyCode == 13) {
				$("#password")[0].focus();
			}
		});
		$("#password").keydown(function(event) {
			if (event.keyCode == 13) {
				$("#verifycode")[0].focus();
			}
		});

		$("#verifycode").keydown(function(event) {
			if (event.keyCode == 13) {
				login();
			}
		});

		//$("#loginName")[0].focus();
	});
	


</script>
		
 
<div class="header-zone">
	<div class="header">
		<div class="inner">
			<div class="header-slogan left">
				<div class="url-list">
					<h6>备用网址列表：</h6>
					<div class="row row-one clear">
						<div class="one left">MGM</div>
						<div class="two domain">
							<div class="domain-body">
								<ul>
									<li>088</li>
									<li>188</li>
									<li>288</li>
									<li>388</li>
									<li>488</li>
									<li>588</li>
									<li>688</li>
									<li>788</li>
									<li>888</li>
									<li>988</li>
								</ul>
							</div>
						</div>
						<div class="three left">.cc</div>
					</div>
					<div class="row row-two clear">
						<div class="one left">MGM</div>
						<div class="two domain">
							<div class="domain-body">
								<ul>
									<li>088</li>
									<li>188</li>
									<li>288</li>
									<li>388</li>
									<li>488</li>
									<li>588</li>
									<li>688</li>
									<li>788</li>
									<li>888</li>
									<li>988</li>
								</ul>
							</div>
						</div>
						<div class="three left">.net</div>
					</div>
				</div>
			</div>
			<!-- /slogan -->
			<h1 class="logo"><a href="${ctx}/index" class="block"></a></h1>
			<div class="header-body right">
				<div class="top clear">
					<ul class="menu left clear">
						<li><a href="javascript:void(0);" id="setHome" onclick="setFirst('http://'+ window.location.hostname)">设为首页</a></li>
						<li><a href="javascript:void(0);" id="addToF" onclick="bookMarksite('http://'+ window.location.hostname,document.title);">加入收藏</a></li>
						<li><a href="">线路测试</a></li>
					</ul>
					<ul class="lang right clear">
						<li class="tw"><a href=""></a></li>
						<li class="zh"><a href=""></a></li>
						<li class="en"><a href=""></a></li>
					</ul>
				</div>
				<!-- /.top -->
				<c:choose>
					<c:when test="${!empty webUser}">
						<div class="user-info">
							<ul class="row mt15 clear">
								<li>账号：<span class="b yellow">${webUser.userName}</span></li>
								<li>余额：<span class="b yellow" id="userMoneyId">${webUser.userMoney}RMB</span></li>
								<li><a href="${ctx}/member/main?type=5" target="_blank" >未读讯息(<font id="userMessageId" color="F0E800">0</font>)</a></li>
								<li><a href="javascript:void(0);" onclick="loginOut();return false">退出</a></li>
							</ul>
							<ul class="row mt10 clear">
								<li><a href="${ctx}/member/main" target="_blank"  >会员中心</a></li>
								<li><a href="${ctx}/member/main?type=1" target="_blank" >线上存款</a></li>
								<li><a href="${ctx}/member/main?type=2" target="_blank" >线上取款</a></li>
								<li><a href="${ctx}/member/main?type=3" target="_blank" >额度转换</a></li>
								<li><a href="${ctx}/member/main?type=4" target="_blank" >往来记录</a></li>
							</ul>
							<script type="text/javascript">
								setInterval('refreshUserMoney()', 10000);
							</script>
						</div>
					</c:when>
					<c:otherwise>
						<div class="signin clear">
							<div class="form left">
								<form id="loginForm">
									<mh:token></mh:token>
									<div class="row">
										<input type="text"  name="loginName" id="loginName" value="用户名:" onfocus="if(this.value=='用户名:') this.value=''" onblur="if(this.value =='') this.value ='用户名:'" class="item un"><input type="password" name="password" id="password" onclick="this.value='';" class="item psw">
									</div>
									<div class="row">
										<input type="text" name="verifycode" id="verifycode" value="验证码" class="item vcode" 
											onkeyup="value=value.replace(/[^\d]/g,'')" size="4"  maxlength="4" 
											onfocus="javascript:if(this.value=='验证码')this.value='';"
											onblur="if(this.value=='')this.value='验证码'" 
											style="width: 45px;"/>
									 	<label id="validateCodeLabelId"
											onclick="javascript:createCode();"><img
											src="${resourceRoot}/web/ybb/assets/img/yzm.gif" class="randPic"
											id="img_validateCode">
										</label>
										<input type="button" value="登入" class="item signin-btn ml10" onclick="return login();"/>
										<a href="javascript:return false" onclick="alert('请您联系7*24小时在线客服找回!')" class="item lnk-find ml5 f14 b">忘记密码?</a>
									</div>
								</form>
							</div>
							<div class="right">
								<a href="${ctx}/register" class="btn-signup mt5 block"></a>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
				<!-- /.signin -->
			</div>
			<!-- /.header-body -->
		</div>
		<!-- /.inner -->
	</div>
	<!-- /.header -->
	<div class="nav">
		<div class="inner clear">
			<ul class="left clear">
				<li><a href="${ctx}" class="nav-link">首页</a></li>
				<li><a href="${ctx}/goPageCenter?code=sport" class="nav-link">体育赛事</a></li>
				<li class="nav-item nav-item-live">
					<a href="${ctx}/goPageCenter?code=live" class="nav-link">真人视讯</a>
					<div class="nav-lv2-wrap" style="display: none;">
						<ul class="clear">
							<li class="nav-lv2-live-hg">
								<div class="title"></div>
								<h6>提供丰富、多样的视讯直播<br>给您带来不一样的视觉体验</h6> 
								<c:choose>
									<c:when test="${!empty webUser}">
										<a href="javascript:void(0)" onclick="winOpen('${ctx}/forwardGame?gameType=hg',window.screen.width,window.screen.height,0,0,'game')"  class="btn-play mt10 f16 b">进入游戏</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:void(0)" onclick="alert('您尚未登录，请先登录再进行游戏')"  class="btn-play mt10 f16 b">进入游戏</a>
									</c:otherwise>
								</c:choose>
							</li>
							<li class="nav-lv2-live-ag">
								<div class="title"></div>
								<h6>行业内首创六张牌先发与咪牌<br>百家乐包桌服务</h6> 
								
								<c:choose>
									<c:when test="${!empty webUser}">
										<a href="javascript:void(0)" onclick="winOpen('${ctx}/forwardGame?gameType=ag&agGameType=2',window.screen.width,window.screen.height,0,0,'game')"  class="btn-play mt10 f16 b">进入游戏</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:void(0)" onclick="alert('您尚未登录，请先登录再进行游戏')"  class="btn-play mt10 f16 b">进入游戏</a>
									</c:otherwise>
								</c:choose>
							</li>
							<li class="nav-lv2-live-bb">
								<div class="title"></div>
								<h6>以创造极富现声感操作模式及<br>娱乐环境为原则</h6> 
								
								<c:choose>
									<c:when test="${!empty webUser}">
										<a href="javascript:void(0)" onclick="winOpen('${ctx}/forwardGame?gameType=bbin&pageSite=live',window.screen.width,window.screen.height,0,0,'game')"  class="btn-play mt10 f16 b">进入游戏</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:void(0)" onclick="alert('您尚未登录，请先登录再进行游戏')"  class="btn-play mt10 f16 b">进入游戏</a>
									</c:otherwise>
								</c:choose>
							</li>
							<li class="nav-lv2-live-ds">
								<div class="title"></div>
								<h6>太阳城真人现场厅<br>终于可以体验官网太阳城的视讯</h6> 
								<c:choose>
									<c:when test="${!empty webUser}">
										<a href="javascript:void(0)" onclick="winOpen('${ctx}/forwardGame?gameType=ds',window.screen.width,window.screen.height,0,0,'game')"  class="btn-play mt10 f16 b">进入游戏</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:void(0)" onclick="alert('您尚未登录，请先登录再进行游戏')"  class="btn-play mt10 f16 b">进入游戏</a>
									</c:otherwise>
								</c:choose>
							</li>
						</ul>
					</div>
				</li>
				<!-- /.nav-item -->
				<li class="nav-item nav-item-slots">
					<a href="${ctx}/electronic?code=mg" class="nav-link">电子游艺</a>
					<div class="nav-lv2-wrap" style="display: none;">
						<ul class="clear">
							<li class="nav-lv2-slots-mg">
								<div class="title"></div>
								<h6>美洲电子游艺、老虎机、电动扑克<br>纸牌电子应有尽有</h6> 
								<c:choose>
									<c:when test="${!empty webUser}">
										<a href="${ctx}/electronic?code=mg" class="btn-play mt10 f16 b">进入游戏</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:void(0)" onclick="alert('您尚未登录，请先登录再进行游戏')"  class="btn-play mt10 f16 b">进入游戏</a>
									</c:otherwise>
								</c:choose>
							</li>
							<li class="nav-lv2-slots-pt">
								<div class="title"></div>
								<h6>欧洲老牌电子游戏<br>经典电子老虎机游戏</h6> 
								<c:choose>
									<c:when test="${!empty webUser}">
										<a href="${ctx}/electronic?code=pt" class="btn-play mt10 f16 b">进入游戏</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:void(0)" onclick="alert('您尚未登录，请先登录再进行游戏')"  class="btn-play mt10 f16 b">进入游戏</a>
									</c:otherwise>
								</c:choose>
							</li>
							<li class="nav-lv2-slots-nt">
								<div class="title"></div>
								<h6>NT品牌电子游艺<br>让您体会不一样的老虎</h6> 
								<c:choose>
									<c:when test="${!empty webUser}">
										<a href="${ctx}/electronic?code=nt" class="btn-play mt10 f16 b">进入游戏</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:void(0)" onclick="alert('您尚未登录，请先登录再进行游戏')"   class="btn-play mt10 f16 b">进入游戏</a>
									</c:otherwise>
								</c:choose>
							</li>
						</ul>
					</div></li>
				<!-- /nav slots -->
				<li class="nav-item nav-item-lott">
					<c:choose>
						<c:when test="${!empty webUser}">
							<a href="javascript:void(0)" onclick="winOpen('${ctx}/lottery',window.screen.width,window.screen.height,0,0,'彩票')" class="nav-link">彩票游戏</a>
						</c:when>
						<c:otherwise>
							<a href="javascript:void(0)" onclick="alert('您尚未登录，请先登录再进行游戏')"   class="nav-link">彩票游戏</a>
						</c:otherwise>
					</c:choose>
				</li>
				<li class="nav-item nav-item-bbin"><a href="${ctx}/goPageCenter?code=bbin" class="nav-link">BBIN专区</a>
					<div class="nav-lv2-wrap" style="">
						<ul class="clear">
							<li class="nav-lv2-bbin-sport">
								<div class="title"></div>
								<h6>体育赛事赔率高<br>结算快</h6> 
							
								<c:choose>
									<c:when test="${!empty webUser}">
										<a href="javascript:void(0)" onclick="winOpen('${ctx}/forwardGame?gameType=bbin&pageSite=ball',window.screen.width,window.screen.height,0,0,'game')"  class="btn-play mt10 f16 b">进入游戏</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:void(0)" onclick="alert('您尚未登录，请先登录再进行游戏')"  class="btn-play mt10 f16 b">进入游戏</a>
									</c:otherwise>
								</c:choose>
							
							</li>
							<li class="nav-lv2-bbin-live">
								<div class="title"></div>
								<h6>丰富多彩真人视讯<br>给您带来不一样的视觉体验</h6> 
							
								<c:choose>
									<c:when test="${!empty webUser}">
										<a href="javascript:void(0)" onclick="winOpen('${ctx}/forwardGame?gameType=bbin&pageSite=live',window.screen.width,window.screen.height,0,0,'game')"  class="btn-play mt10 f16 b">进入游戏</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:void(0)" onclick="alert('您尚未登录，请先登录再进行游戏')"  class="btn-play mt10 f16 b">进入游戏</a>
									</c:otherwise>
								</c:choose>
							
							</li>
							<li class="nav-lv2-bbin-slots">
								<div class="title"></div>
								<h6>几百种电子游艺丰富多样<br>3D效果更是炫彩</h6> 
							
								<c:choose>
									<c:when test="${!empty webUser}">
										<a href="javascript:void(0)" onclick="winOpen('${ctx}/forwardGame?gameType=bbin&pageSite=game',window.screen.width,window.screen.height,0,0,'game')"  class="btn-play mt10 f16 b">进入游戏</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:void(0)" onclick="alert('您尚未登录，请先登录再进行游戏')"  class="btn-play mt10 f16 b">进入游戏</a>
									</c:otherwise>
								</c:choose>
							</li>
							<li class="nav-lv2-bbin-lott">
								<div class="title"></div>
								<h6>六合、时时彩、快乐彩等<br>多种盘口多种玩法</h6> 
								<c:choose>
									<c:when test="${!empty webUser}">
										<a href="javascript:void(0)" onclick="winOpen('${ctx}/lottery',window.screen.width,window.screen.height,0,0,'彩票')" class="btn-play mt10 f16 b">进入游戏</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:void(0)" onclick="alert('您尚未登录，请先登录再进行游戏')"   class="btn-play mt10 f16 b">进入游戏</a>
									</c:otherwise>
								</c:choose>
							</li>
						</ul>
					</div></li>
				<!-- /nav bbin -->
				<li class="nav-item nav-item-promos"><a href="${ctx}/goPageCenter?code=promos" class="nav-link">优惠活动</a></li>
				<li class="nav-item nav-item-promos"><a href="${ctx}/goPageCenter?_2&code=help&pgSn=problem" class="nav-link">代理合作</a></li>
			 
			</ul>
			<a href="http://messenger.providesupport.com/messenger/0zbzj68kpa3931cy4vhdp32s6w.html?ps_l=https%3A//admin2.providesupport.com/zh_CN/view/my-account/setup-instructions/code-test%3Bwsid%3Dijm268oguoo6zxdr69jwx7sn" target="_blank" class="serv right f16 b">在线客服</a>
		</div>
	</div>
	<!-- /.nav -->
</div>
<!-- /.header-zone -->
		