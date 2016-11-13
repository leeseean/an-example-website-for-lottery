<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 
<link rel="stylesheet" href="${resourceRoot}/member/css/normalize.css">
<link rel="stylesheet" href="${resourceRoot}/member/css/jquery-ui.min.css">
<link rel="stylesheet" href="${resourceRoot}/member/css/font.css">
<link rel="stylesheet" href="${resourceRoot}/member/css/button.css">
<link rel="stylesheet" href="${resourceRoot}/member/css/icons/flaticon.css">
<link rel="stylesheet" href="${resourceRoot}/member/css/panel.css">
<!--[if IE]>
   <script src="${resourceRoot}/member/js/html5shiv.min.js"></script>
<![endif]-->
<script>
var root= "${resourceRoot}";
var rootPath = "${ctx}";
</script>
<script src="${resourceRoot}/member/js/jquery.min.js"></script>
<script src="${resourceRoot}/member/js/jquery-ui.min.js"></script>
<script src="${resourceRoot}/member/js/datepicker-zh.js"></script>

<script src="${resourceRoot}/member/js/superslide.min.js"></script>
<script src="${resourceRoot}/member/js/layer.js"></script>
<script src="${resourceRoot}/member/js/button.js"></script>
<script src="${resourceRoot}/member/js/switch-load.js"></script>
<script src="${resourceRoot}/member/js/iss.core.js" type="text/javascript"></script>
<script src="${resourceRoot}/member/js/member.js"></script>
<script src="${resourceRoot}/member/js/plugins.js"></script>
 <script type="text/javascript">
	function goParent(url){
		parent.document.getElementById('memberFrame').src =url;
	}
	function goback(url){
		$("#memberFrame").attr("src",url);
	}
	

</script>
