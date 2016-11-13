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
    <h1>转换记录查询</h1>
  </div>
  <!-- /header -->
  <div data-role="content" class="ybm-form-content">
    <form action="${ctx}/m/edu/eduHistoryList" id="subform" method="post" data-ajax="false">
      <div data-role="fieldcontain">
        <label for="beginTime">起始日期：</label>
        <input type="date" name="beginTime" id="beginTime" value="${currDateStr }">
      </div>
      <!-- /item -->
      <div data-role="fieldcontain">
        <label for="endTime">截至日期：</label>
        <input type="date" name="endTime" id="endTime" value="${currDateStr }">
      </div>
      <!-- /item -->
      <div data-role="fieldcontain">
        <fieldset data-role="controlgroup" data-type="horizontal" data-corners="false">
          <legend>所属厅：</legend>
          <label for="flatName">所属厅：</label>
          <select name="flatName" id="flatName">
          	<option value="" >全部</option>
			<option value="ag">AG</option>
			<option value="mg">MG</option>
          </select>
        </fieldset>
        <fieldset data-role="controlgroup" data-type="horizontal" data-corners="false">
          <legend>转换类型：</legend>
          <label for="eduType">小类：</label>
          <select name="eduType" id="eduType">
          	<option value="">全部</option>
          	<option value="1">转出</option>
			<option value="2">转入</option>
          </select>
        </fieldset>
      </div>
      <!-- /item -->
      <div data-role="fieldcontain" style=" text-align: center;">
      	<!-- <input type="submit" value="确定"> -->
        <a href="javascript:void(0);" onclick="submitfrom();" data-rel="dialog" data-role="button" data-inline="true" data-corners="false" data-icon="check" data-theme="b">確定</a>
      </div>
      <!-- /item -->
    </form>
  </div>
  <!-- /content -->
</div>
<!-- /注單查詢 -->
