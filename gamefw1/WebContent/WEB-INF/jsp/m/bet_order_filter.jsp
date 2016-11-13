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
          <select name="gameType" id="gameType" onchange="gameTypeChange(this.value)">
           		<option value="cp">彩票游戏</option>
				<option value="sport">体育赛事</option>
          </select>
          <select name="cateType" id="cateType">
            <option value='' selected='selected'>全部</option>
          </select>
        </fieldset>
      </div>
      <!-- /item -->
      <div data-role="fieldcontain" style=" text-align: center;">
        <a href="" onclick="doSubmit(this)" data-rel="dialog" data-role="button" data-inline="true" data-corners="false" data-icon="check" data-theme="b" data-ajax="false">確定</a>
        
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
   			var gameType=$("#gameType").val();
   			var cateType=$("#cateType").val();
   			var url=rootPath;
   			console.log(obj);
   			if($("#endTime").val() < $("#beginTime").val())
   			{
   				alert("截止日期不能小于开始日期");
   				return;
   			}
   			if($("#gameType").val()=="sport"){//体育
   				//form.action=rootPath+"/m/sport/getOrderList?status=1";
   				//form.submit();
   				url+="/m/sport/getOrderList?status=2";
   				url+="&beginTime="+beginTime+"&endTime="+endTime+"&cateType="+cateType;
   				obj.href=url;
   			}else{
   				//form.action=rootPath+"/m/mobileCpAccount/getOrderList";
   				//form.submit();
   				url+="/m/mobileCpAccount/getOrderList?";
   				url+="beginTime="+beginTime+"&endTime="+endTime+"&gameType="+gameType+"&cateType="+cateType;
   				obj.href=url;
   			}
   			//${ctx}/m/mobileCpAccount/getOrderList
   		}
   
   
   		function getCpTypeInfo(){
   			var info="<option value='' selected='selected'>全部</option>";
   			info+="<option value='HK6' >六合彩</option>";
   			info+="<option value='FC3D'>福彩3D</option>";
   			info+="<option value='PL3'>排列3</option>";
   			info+="<option value='CQSSC'>重庆时时彩</option>";
   			info+="<option value='TJSSC'>天津时时彩</option>";
   			info+="<option value='JXSSC'>江西时时彩</option>";
   			info+="<option value='XJSSC'>新疆时时彩</option>";
   			info+="<option value='GDKLSF'>广东快乐十分</option>";
   			info+="<option value='TJKLSF'>天津快乐十分</option>";
   			info+="<option value='BJPK10'>北京PK拾</option>";
   			return info;
   		}
   	
		function gameTypeChange(value) {
			$("#cateType").empty();
			var info="";
			if (value=="sport") {
				info="<option value='' selected='selected'>全部</option>";
				info+="<option value='FT'>足球</option>";
				info+="<option value='BK'>篮球</option>";
				
			}else{
				info=getCpTypeInfo();
				
			}
			$("#cateType").append(info);
			$("#cateType").get(0).options[0].selected = true;
			//$("#cateType").find("option[value='']").attr("selected",true);
		}
		
		$(function () {
			
			gameTypeChange("cp");
		});
		</script>
</div>
<!-- /注單查詢 -->