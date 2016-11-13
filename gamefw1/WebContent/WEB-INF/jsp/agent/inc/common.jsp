<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mh" uri="http://www.mh.com/framework/tags" %>

<link rel="stylesheet" href="${resourceRoot}/agent/css/normalize.css">
<link rel="stylesheet" href="${resourceRoot}/agent/css/base.css">
<link rel="stylesheet" href="${resourceRoot}/agent/css/jquery-ui.css">
<link rel="stylesheet" href="${resourceRoot}/agent/css/layout.css">

<script type="text/javascript" src="${resourceRoot}/agent/js/jquery.min.js"></script>
<script type="text/javascript" src="${resourceRoot}/agent/js/iss.core.js"></script>

<script>
var root= "${resourceRoot}";
var rootPath = "${ctx}";
</script>
<script type="text/javascript">
	function changeTime( type){
			if( type =='yes'){
				$('#beginTimeStr').val('${searchDateTime[2]}');
				$('#endTimeStr').val( '${searchDateTime[3]}');
			}
			if( type =='week'){
				$('#beginTimeStr').val('${searchDateTime[4]}');
				$('#endTimeStr').val( '${searchDateTime[5]}');
			}
			if( type =='month'){
				$('#beginTimeStr').val('${searchDateTime[6]}');
				$('#endTimeStr').val('${searchDateTime[7]}');
			}
			if( type =='premonth'){
				$('#beginTimeStr').val('${searchDateTime[8]}');
				$('#endTimeStr').val('${searchDateTime[9]}');
			}
			goto_query();
	} 
	</script>
