<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<script>
var resourceRoot= "${resourceRoot}";
var rootPath = "${ctx}";
var cpparam = "${cpparam}";
var webUser = "${webUser}";
var internalRecordLeft = null;
</script>
 
<link rel="icon" type="image/png" href="${resourceRoot}/cp/images/favicon.png">
<link rel="stylesheet" href="${resourceRoot}/cp/css/pure-min.css">
<link rel="stylesheet" href="${resourceRoot}/cp/css/ui-button.css">
<link rel="stylesheet" href="${resourceRoot}/cp/css/ui-icon.css">
<link rel="stylesheet" href="${resourceRoot}/cp/css/lotto-mod.css">
<link rel="stylesheet" href="${resourceRoot}/cp/css/lotto.css">
<link rel="stylesheet" href="${resourceRoot}/cp/css/artdialog/default.css">


<script src="${resourceRoot}/cp/js/jquery-1.11.0.min.js"></script>
<script src="${resourceRoot}/cp/js/core.min.js"></script>
<script src="${resourceRoot}/cp/js/main.min.js"></script>
<script src="${resourceRoot}/cp/js/artDialog.js"></script>
<script src="${resourceRoot}/cp/js/common-utils.js"></script>
<script src="${resourceRoot}/cp/js/service/cp_order.js"></script>
<script src="${resourceRoot}/cp/js/cpgl.js"></script>