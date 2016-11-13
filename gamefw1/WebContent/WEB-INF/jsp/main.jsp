<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/inc.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
	  <meta charset="UTF-8">
	  <meta http-equiv="x-ua-compatible" content="ie=edge">
	  <meta name="renderer" content="ie-comp|webkit|ie-stand" />
	  <title></title>
	<%@include file="web/inc/common.jsp"%>
	  <link rel="stylesheet" href="${resourceRoot}/web/ybb/common/plugins/jui/smoothness/jquery-ui.min.css"/>
	  <script src="${resourceRoot}/web/ybb/common/js/jquery.min.js"></script>
	  <script>
	    var root = "${resourceRoot}";
	    var rootPath = "${ctx}"; 
	  </script> 
	</head>
	<body>
		<div class="body-bg">
			<div class="body body-${page_name }">
				<div class="header">
					<%@include file="web/inc/head.jsp" %>
				</div>
				<!-- 内容部分 -->
			<div class="wrapper">
			<script>
				var imgWidth = 580,
					imgHeight = 1920;
				$('.slider-home').css('height', imgHeight * ($(window).width() / imgWidth));
			</script>
			 <div class="slider slider-home" plugin="superslide" plugin-data-pager=".links-pager ul">
				<ul class="bd clearfix">
					<li><img src="${resourceRoot }/web/ybb/assets/img/slider/ban1.jpg" alt="" /></li>
					<li><img src="${resourceRoot }/web/ybb/assets/img/slider/ban2.jpg" alt="" /></li>
					<li><img src="${resourceRoot }/web/ybb/assets/img/slider/ban3.jpg" alt="" /></li>
					<li><img src="${resourceRoot }/web/ybb/assets/img/slider/ban4.jpg" alt="" /></li>
				</ul>
				<div class="links-pager">
					<ul></ul>
				</div>
			</div>
           </div>
				  <div class="main-content">
					<div class="wrapper">
						<%@include file="web/inc/main.jsp" %>
					</div>
				</div>  
				<!--底部  -->
				<div class="footer">
					<%@include file="web/inc/foot.jsp" %>
				</div>
		    </div>
		</div>

		  <%@include file="web/inc/foot_in.jsp" %>
		
		<div class="ybb-dia-hp" title="xxx赌场欢迎莅临" style="display:none">
		    <p class="center">${ctxMap['msg004'] }</p>
		</div> 
		<script type="text/javascript">
			$(function(){
				if("${!empty ctxMap['msg004']}"=="true"){
					$('.ybb-dia-hp').dialog({
					  modal: true,
					  width: 600,
					  show: {
				        effect: 'fade', duration: 800
					  }
				  });
				}
			});
		</script>
	</body>