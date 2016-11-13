<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/inc.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:hidden" lang="zh-CN">
	<head>
	  <meta charset="UTF-8">
	  <meta http-equiv="x-ua-compatible" content="ie=edge">
	  <title></title>
	<%@include file="web/inc/common.jsp"%>
	  <link rel="stylesheet" href="${resourceRoot}/web/ybb/common/plugins/jui/smoothness/jquery-ui.min.css"/>
	  <script src="${resourceRoot}/web/ybb/common/js/jquery.min.js"></script>
	  <script>
	    var root = "${resourceRoot}";
	    var rootPath = "${ctx}";
	  </script>
	</head>
	<body style="background: #1a1a1a">
		<div class="body body-${page_name }">
			<div class="header">
				<%@include file="web/inc/head1.jsp" %>
			</div>
			<div class="main-content">
				<div class="wrapper">
					<%@include file="web/inc/main.jsp" %>
				</div>
			</div>
			<div class="footer">
				<%@include file="web/inc/foot.jsp" %>
			</div>
		</div>

		<%@include file="web/inc/foot_in.jsp" %>
  <style>
		/* .ui-dialog .ui-dialog-content {
    background: rebeccapurple;
    } */
    /* .ui-state-default .ui-icon.ui-icon-closethick {
    background: transparent url("${resourceRoot }/web/ybb/assets/img/main/icon_close.png") no-repeat center center;
    width: 24px;
    height: 24px;
    margin: 0;
    left: 0;
    top: -3px;
    outline: none;
		} */
    /* .ui-draggable .ui-dialog-titlebar {
    font-size: 16px!important;
    background: transparent none!important;
    border-radius: 0!important;
    border: medium none!important;
    border-bottom: solid 1px #494949!important;
    margin: 0 -3px!important;
    color: #FC3!important;
		}
    .ui-widget-content {
    z-index: 1111;
    }
    .ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ui-draggable.ui-resizable {
    background: #1c1c1c none;
    border: solid 3px #333333;
    border-radius: 8px;
    overflow: hidden;
		} */
    .ui-widget-overlay {
      z-index: 100000;
    }
    .ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ui-draggable.ui-resizable {
      background: #1c1c1c none;
      border: solid 3px #333333;
      border-radius: 8px;
      overflow: hidden;
    }
    .ui-draggable .ui-dialog-titlebar {
      font-size: 16px;
      background: transparent none;
      border-radius: 0;
      border: medium none;
      border-bottom: solid 1px #494949;
      margin: 0 -3px;
      color: #FC3;
    }
    .ui-draggable.newsAnomount .ui-dialog-titlebar .ui-dialog-title {
      display: inline-block;
      padding-right: 78px;
      width: auto;
    }
    .ui-dialog .ui-dialog-titlebar-close {
      border: medium none;
      background: transparent none;
      right: 10px;
      width: 24px;
      height: 24px;
      outline: none;
    }
    .ui-state-default .ui-icon {
      background: transparent none;
    }
    .ui-state-default .ui-icon.ui-icon-closethick {
      background: transparent url("${resourceRoot }/web/ybb/common/css/images/icon_close.png") no-repeat center center;
      width: 24px;
      height: 24px;
      margin: 0;
      left: 0;
      top: -3px;
      outline: none;
    }
    .ui-dialog .ui-dialog-content {
      padding: 5px;
    }
    .ui-dialog .ui-dialog-content * {
      line-height: 28px;
      font-size: 14px;
      font-family: 'Microsoft YaHei', '\5FAE\8F6F\96C5\9ED1';
      /*color: #896966;*/
    }
    .ui-dialog .ui-dialog-content p {
      margin-bottom: 10px;
    }

  </style>
		<div class="ybb-dia-hp" title="澳门永利赌场欢迎莅临，官网www.7331.cc" style="display:none">
		    <p class="center">${ctxMap['msg004'] }</p>
		</div>
		<script type="text/javascript">
			$(function(){
				// if("${!empty ctxMap['msg004']}"=="true"){
					$('.ybb-dia-hp').dialog({
					  modal: true,
					  width: 600,
					  show: {
				        effect: 'fade', duration: 800
					  }
				  });
				// }
			});
		</script>
	</body>
</html>