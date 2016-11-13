<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div data-role="page" data-close-btn="right">
  <div data-role="header">
   <script src="${resourceRoot}/m/js/comm.js"></script>
    <h1>彩票投注</h1>
  
  </div>
 <script type="text/javascript">
	
	function getSelectNum(){
		var selectNum="";
		var num1=$("#num1").val();
		var num2=$("#num2").val();
		var num3=$("#num3").val();
		selectNum+=num1+""+num2+""+num3;
		return selectNum;
	}
	function checkIsSelectNum(number){
		var obj=$("#lab_"+number).html();
		return !obj;
	}
    function addNumberData(){
    	var num=getSelectNum();
    	var b=checkIsSelectNum(num);
    	if(b){
    		var a=$("#gameTypeCode").val();
        	var b=$("#cpTypeCode").val();
        	var c=$("#cpCateCode").val();
        	var d=$("#xzlxCode").val();
        	$.post("getConfForNumber",{gameTypeCode:a,cpTypeCode:b,cpCateCode:c,xzlxCode:d,number:num},function(data){
        		if(null!=data){
        			var info="";
        			eval("info="+data)
        			var li_info=getLi(info);
        			$("#number_ul").append(li_info);
        		}
        	});
    	}else{
    		alert("该号码已添加到号码栏!");
    		
    	}
    	
    }
    function getLi(data){
    	
    	var id=data.id;
    	var num=data.num;
    	var rate=data.rate
    	var name1=data.cateName;
    	var name2=data.xzlxName;
    	var number = data.number;
    	var li_info="<li class='ui-btn-up-d'>";
    		li_info+="<div data-role='fieldcontain' style='padding: 0;'>";
    		li_info+="<label id='lab_"+num+"' for='zxx'"+id+">";
    		li_info+=number + "|" + name1+"&"+name2+"-"+num+"@";
    		li_info+="<strong class='red'>"+rate+"</strong>";
    		li_info+="</label>";
    		li_info+="<input type='number' pattern='[0-9]*' name='number[]' ";
    		li_info+="id='"+id+"' data-mini='true' num='"+num+"' rate='"+rate+"'";
    		li_info+="cateName='"+name1+"' xzlxName='"+name2+"'";
    		li_info+="</div></li>";
	    return li_info;
    }
    
   function clearSelectAll(){
	   var r=confirm("确定要删除所有已选号码，重新选择吗？");
	   if(r){
		   $("#number_ul").empty();
	   }
	   
   }
</script>
  <!-- 投注單 -->
  <div data-role="content" class="ybm-form ybm-order ybm-lott-order">
  
    <form action="${ctx}/m/mobileCpOrder/goOrderList" id="order_form" method="post">
            <input type="hidden" id="gameTypeCode" name="gameTypeCode" value="${param.gameTypeCode }" />
            <input type="hidden" id="gameTypeName" name="gameTypeName" value="${gameTypeName }" />
            <input type="hidden" id="cpTypeCode" name="cpTypeCode" value="${param.cpTypeCode }" />
            <input type="hidden" id="cpCateCode" name="cpCateCode" value="${param.cpCateCode }" />
            <input type="hidden" id="xzlxCode" name="xzlxCode" value="${param.xzlxCode }" />
              
            <input type="hidden" id="minMoney" name="minMoney" value="${cpType.gminSingle }" />
            <input type="hidden" id="maxMoney" name="maxMoney" value="${cpType.gmaxSingle }" />
            <input type="hidden" id="maxSumMoney" name="maxSumMoney" value="${cpType.singleCredit }" />
            <input type="hidden" id="userMoney" name="userMoney" value="${userMoney }" />
    <ul data-role="listview" class="r1">
      <li data-role="list-divider" data-theme="e">期数：${nextResult.formatQs}　开奖日期：${nextResult.kjsj }</li>
     <c:if test="${sessionScope.USER_CONTEXT_KEY!=null }">
	      <li>额度： 
	          <strong class="red"><fmt:formatNumber value="${userMoney }" type="currency" pattern="#,#00.0#"/></strong>
	      </li>
	  </c:if>	
	</ul>
	<ul class="ui-listview ui-listview-inset ui-corner-all ui-shadow" data-role="listview" data-inset="true">
         	
         <li data-role="fieldcontain" class="ui-field-contain ui-body ui-br ui-li ui-li-static ui-body-c  ui-corner-top ui-btn-up-c ui-first-child">
         		<table style="width: 100%">
         			<tbody>
         				<tr style="width: 100%">
         					<td style=" width: 33%">
         					<div class="ui-select">
         						<div data-corners="true" data-shadow="true" data-iconshadow="true" data-wrapperels="span" data-icon="arrow-d" data-iconpos="right" data-theme="c" class="">
         						<select id="num1" name="num1" >
         							<c:forEach begin="0" end="9" var="i">
										<option value="${i}" >${i}</option>
									</c:forEach>
         						</select>
         						</div>
         					</div>
         					</td>
         					<td style=" width: 33%">
         					<div class="ui-select">
         						<div data-corners="true" data-shadow="true" data-iconshadow="true" data-wrapperels="span" data-icon="arrow-d" data-iconpos="right" data-theme="c" class="">
         						<select id="num2" name="num2" >
         							<c:forEach begin="0" end="9" var="i">
										<option value="${i}" >${i}</option>
									</c:forEach>
         						</select>
         						</div>
         					</div>
         					</td>
         					<td style=" width: 33%">
         					<div class="ui-select">
         						<div data-corners="true" data-shadow="true" data-iconshadow="true" data-wrapperels="span" data-icon="arrow-d" data-iconpos="right" data-theme="c" class="">
         						<select id="num3" name="num3" >
         							<c:forEach begin="0" end="9" var="i">
										<option value="${i}" >${i}</option>
									</c:forEach>
         						</select>
         						</div>
         					</div>
         					</td>
         				</tr>
         		</tbody>
         					</table>
         					</li>
   	</ul>
   	<div data-corners="true" data-shadow="true" data-iconshadow="true" data-wrapperels="span" data-theme="b" data-disabled="false" class="" aria-disabled="false">
		  <input type="button" data-theme="b" value="添加到号码栏" class="ui-btn-hidden" onclick="addNumberData();" aria-disabled="false" data-disabled="false">
	</div>
	<ul id="number_ul" data-role="listview" class="r1" style="margin-top: 20px;">
	
	</ul> 	
	<ul data-role="listview" class="r1" style="margin-top: 20px;">  	
      <li class="tac">
        <fieldset data-role="controlgroup" data-type="horizontal" data-corners="false">
          <c:if test="${sessionScope.USER_CONTEXT_KEY==null }">
          	<a href="${ctx}/m/main/noLogin" data-rel="dialog" data-transition="pop" data-role="button" data-icon="check" data-theme="b" >投注</a>
          </c:if>
          <c:if test="${sessionScope.USER_CONTEXT_KEY!=null }">
          	<a href="javascript:void(0)" onclick="doOrderSubmit('order_form');" data-rel="dialog" data-transition="pop" data-role="button" data-icon="check" data-theme="b" data-ajax="false">投注</a>
          </c:if>
          <a href="" data-role="button" data-rel="back" data-icon="back">取消</a>
        </fieldset>
      </li>
    </ul>
    
    </form>
  </div>
  <!-- /投注單 -->
</div>


<!-- /page -->