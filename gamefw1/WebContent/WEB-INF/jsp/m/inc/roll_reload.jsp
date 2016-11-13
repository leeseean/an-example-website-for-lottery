<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
$(function(){
	doSecond();	
});
var count = 90;
function doSecond()
{
	--count;
	if(count == 0)
		window.location.reload();
	
	$("#second").text(count);
	setTimeout("doSecond()",1000);
}
</script>
<div data-position="fixed" class="ac" style=" padding-bottom: 10px;">
	<a data-role="button" data-ajax="false" href="${ctx }/m/sport/list" data-role="button" data-inline="true" data-mini="true" data-theme="a"  data-icon="back">返回</a>
	<a href="javascript:window.location.reload();" data-role="button" data-inline="true" data-mini="true" data-theme="a" data-icon="refresh">刷新&nbsp;&nbsp;<span id="second">90</span></a>
</div>