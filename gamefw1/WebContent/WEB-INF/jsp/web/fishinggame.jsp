<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
  <%@include file="/inc.jsp"%>
    <!DOCTYPE html>
    <html lang="zh-CN">

    <head>
      <meta charset="UTF-8">
      <meta http-equiv="x-ua-compatible" content="ie=edge">
      <title></title>
      <%@include file="inc/common.jsp"%>
        <script src="${resourceRoot}/web/ybb/common/js/jquery.min.js"></script>
        <script>
        var root = "${resourceRoot}";
        var rootPath = "${ctx}";
        </script>
    </head>

    <body>
		<div class="body body-mgelectronic body-${page_name }">
			<div class="header">
				<%@include file="inc/head.jsp" %>
			</div>
			<div class="main-content">
				<div class="wrapper">
					<div class="row">
						<div class="wrapper">
					
						</div>
					</div>

					<div class="row">
						<div class="wrapper">
							<div class="header-msg">
								<%@include file="inc/msg.jsp" %>
							</div>
						</div>
					</div>

					<div class="row">
						<style>

							/* .fishing-con a {
								display: block;
							}

							.fishing-con img  {
								display: block;
								width: 100%;
							} */

              .fishing-con  #vsFish{
                background: url("${resourceRoot }/web/ybb/assets/img/vsgame/fish1.png");
                width: 410px;height: 412px;float: left;
              }
              .fishing-con  #vsFish:hover{
                background: url("${resourceRoot }/web/ybb/assets/img/vsgame/fish2.png");
                width: 410px;height: 412px;float: left;
              }
              .fishing-con  #landlord{
                background: url("${resourceRoot }/web/ybb/assets/img/vsgame/landlord1.png");
                width: 410px;height: 412px;float: right;
              }
              .fishing-con  #landlord:hover{
                background: url("${resourceRoot }/web/ybb/assets/img/vsgame/landlord2.png");
                width: 410px;height: 412px;float: right;
              }
						</style>

            <div class="content-body">
              <div class="fishing-con w1000">
                <div class="wrapper">
                  <div class="row">
						<div class="wrapper">
							<div class="content-top-banner dz"></div>
						</div>	
				  </div>

                  <a href="javascript:void(0)" onclick="winOpen('${ctx}/forwardGame?gameType=ag&agGameType=6',window.screen.width,window.screen.height,0,0,'game','ag')" id="vsFish"></a>
                  <a href="javascript:void(0)" onclick="winOpen('${ctx}/forwardGame?gameType=vg',window.screen.width,window.screen.height,0,0,'game','ag')" id="landlord"></a>
                </div>
                <div style="clear: both;"></div>
              </div>
            </div>
            </div>
				</div>
			</div>
			<div class="footer">
				<%@include file="inc/foot.jsp" %>
			</div>
		</div>

		<%@include file="inc/foot_in.jsp" %>
	</body>
