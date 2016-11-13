<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 注單查詢 -->
<div data-role="page" data-close-btn="right">
  <div data-role="header">
    <h1>注单查询</h1>
  </div>
  <!-- /header -->
  <div data-role="content" class="ybm-form-content">
    <form action="">
      <div data-role="fieldcontain">
        <label for="beginTime">起始日期：</label>
        <input type="date" name="beginTime" id="beginTime" value="${initDate }"/>
      </div>
      <!-- /item -->
      <div data-role="fieldcontain">
        <label for="endTime">截至日期：</label>
        <input type="date" name="endTime" id="endTime" value="${initDate }"/>
      </div>
      <!-- /item -->
      <div data-role="fieldcontain">
        <fieldset data-role="controlgroup" data-type="horizontal" data-corners="false">
          <legend>类别：</legend>
          <select name="gameTypeCode" id="gameTypeCode">
            <option value='' selected='selected'>全部</option>
            <option value='HK6' >六合彩</option>
   			<option value='FC3D'>福彩3D</option>
   			<option value='PL3'>排列3</option>
   			<option value='CQSSC'>重庆时时彩</option>
   			<option value='TJSSC'>天津时时彩</option>
   			<option value='XJSSC'>新疆时时彩</option>
   			<option value='GDKLSF'>广东快乐十分</option>
   			<option value='TJKLSF'>天津快乐十分</option>
   			<option value='BJPK10'>北京PK拾</option>
   			<option value='BJKL8'>幸运28</option>
   			<option value='CAKENO'>加拿大28</option>
          </select>
          
        </fieldset>
        <fieldset data-role="controlgroup" data-type="horizontal" data-corners="false">
          <legend>状态：</legend>
          <select name="order_status" id="order_status" >
           	<option value='' selected='selected'>全部</option>
            <option value='1' >未结算</option>
            <option value='2' >已结算</option>
          </select>
        </fieldset>
      </div>
      <!-- /item -->
      <div data-role="fieldcontain" style=" text-align: center;">
        <a href="" onclick="doSubmit(this)" data-ajax="false"  data-role="button" data-inline="true" data-corners="false" data-icon="check" data-theme="b" >確定</a>
        
        <!-- <a href="lottery-order-list.html" data-rel="dialog" data-role="button" data-inline="true" data-corners="false" data-icon="check" data-theme="b">確定</a> -->
      </div>
      <!-- /item -->
    </form>
  </div>
  <!-- /content -->
  <script type="text/javascript">
   		function doSubmit(obj){
   			var beginTime=$("#beginTime").val();
   			var endTime=$("#endTime").val();
   			var order_status=$("#order_status").val();
   			var gameTypeCode=$("#gameTypeCode").val();
   			var url=rootPath;
   			console.log(obj);
   			if($("#endTime").val() < $("#beginTime").val())
   			{
   				alert("截止日期不能小于开始日期");
   				return;
   			}
   				//form.action=rootPath+"/m/mobileCpAccount/getOrderList";
   				//form.submit();
   			url+="/m/mobileCpAccount/getOrderList?";
   			url+="beginTime="+beginTime+"&endTime="+endTime+"&gameTypeCode="+gameTypeCode+"&order_status="+order_status;
   			obj.href=url;
   			//${ctx}/m/mobileCpAccount/getOrderList
   		}
   
   
   		
		</script>
</div>
<!-- /注單查詢 -->