<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 注單查詢 -->
<div data-role="page" data-close-btn="right">
	<script type="text/javascript">
	function submitfrom()
	{
		var start = $("#bet-order-filter-11").val(); 
		var end = $("#bet-order-filter-12").val();
		
		if(start == "" || end == "")
			return;
		if(end < start)
		{
			alert("截止日期不能小于起始日期");
			return;			
		}
		document.getElementById("subform").submit();
	}
	</script>
  <div data-role="header">
    <h1>注单查询</h1>
  </div>
  <!-- /header -->
  <div data-role="content" class="ybm-form-content">
    <form action="${ctx}/m/slot/getslotOrderList" id="subform" method="post">
    	<input type="hidden" name="cateType" id="cateType" value="${code }">
      <div data-role="fieldcontain">
        <label for="beginTime">起始日期：</label>
        <input type="date" name="beginTime" id="beginTime" value="${ currDateStr}">
      </div>
      <!-- /item -->
      <div data-role="fieldcontain">
        <label for="endTime">截至日期：</label>
        <input type="date" name="endTime" id="endTime" value="${currDateStr }">
      </div>
      <!-- /item -->
      <div data-role="fieldcontain" style=" text-align: center;">
        <a href="javascript:void(0);" onclick="submitfrom();" data-rel="dialog" data-role="button" data-inline="true" data-corners="false" data-icon="check" data-theme="b">確定</a>
      </div>
      <!-- /item -->
    </form>
  </div>
  <!-- /content -->
</div>
<!-- /注單查詢 -->
