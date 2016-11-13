<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
<title>皇冠體育</title>
 
<link rel="stylesheet" href="${resourceRoot}/sport/css/mem_body_ft.css" type="text/css">
<link rel="stylesheet" href="${resourceRoot}/sport/css/mem_body_his.css" type="text/css">

<script src="${resourceRoot}/sport/js/jquery.min.js" type="text/javascript"></script>
<script src="${resourceRoot}/sport/js/layer/layer.min.js" type="text/javascript"></script>
</head>
<body id="Mall" class="bodyset HIS" >

	<script type="text/javascript">
		function changeGtpye(){
			if(on_Submit())sel_gtype.submit();
		}
		function changeUrl(a){
		 self.location=a;
		}
		function on_Submit(){
			if (document.getElementById("startTime").value > document.getElementById("endTime").value){
				alert("日期区间错误!");
				return false;
			}
			return true;
		}
		
		function changeGtpye(){
			if(on_Submit())sel_gtype.submit();
		}
		function overbars(obj,color){
		  //alert(obj.cells["d_date"].className);
		  var className=obj.cells["d_date"].className;
		  if (className=="his_list_none") return;  
			obj.cells["d_date"].className=color;
		
		}
		function outbars(obj,color){
		var className=obj.cells["d_date"].className;
		  if (className=="his_list_none") return;
			obj.cells["d_date"].className=color;
			//alert("out--"+obj.cells["d_date"].className);
		}
	</script>
	<body id="Mall" class="bodyset HIS" onload="onLoad()">

	<table border="0" cellpadding="0" cellspacing="0" id="box">
	  <tbody><tr>
	    <td class="top">
	  	  <h1><em>帐户历史摘要</em></h1>
		</td>
	  </tr>
	  <tr>
	    <td class="mem">
	    <h2>
            <table width="100%" border="0" cellpadding="0" cellspacing="0" id="fav_bar">
              <form action="${ctx}/sport/accountHis" method="post" id="sel_gtype" onsubmit="return on_Submit()" style="display:inline">
	              <tbody><tr>
                    <td>
                   	按体育查看记录:
	                  <select name="betSportType" id="betSportType">
	                  	<c:forEach var="map" items="${types }" >
	                  		<option value="${map.key }" <c:if test="${map.key eq record.betSportType}">selected="selected"</c:if>>${map.value }</option>
	                  	</c:forEach>
	                   </select>
	                  </td>
	                  <td>日期:</td>
	                  <td>
	                  	<select id="startTime" name="startTime" class="za_select">
	                  	<c:forEach var="item" items="${timeList }">
	                  		<option value="${item }" <c:if test="${item eq record.startTime}">selected="selected"</c:if> >${item } </option>
	                  	</c:forEach>
						</select>
					  </td>
	                  <td>到</td>
	                  <td>
	                  	<select id="endTime" name="endTime" class="za_select">
	                  	<c:forEach var="item" items="${timeList }">
	                  		<option value="${item }" <c:if test="${item eq record.endTime}">selected="selected"</c:if> >${item } </option>
	                  	</c:forEach>
						</select>
	                  </td>
	                  <td><input type="submit" value="搜寻"></td>
	              </tr>
              
            </tbody>
            </form>
            </table>
    </h2>
    
    <table border="0" cellspacing="0" cellpadding="0" class="game">
      <tbody><tr> 
        <th class="his_time">日期</th>
        <th class="his_wag">投注额</th>
        <th class="his_wag">有效金额</th>
        <th class="his_wag">派彩结果</th>
        <!--th width="25%">有效金额</th-->
      </tr>
      <c:forEach var="item" items="${accounts }" varStatus="status">
      <tr class="color_bg${status.index%2==0?1:2}"  onmouseover="overbars(this,'his_over');" onmouseout="outbars(this,'his_list')">
        <td class="his_list_none" id="d_date"><span>
        <c:choose>
        <c:when test="${item.sumIn>0 }">
        	<a href="${ctx}/sport/accountDetail?searchTime=${item.time}&betSportType=${record.betSportType}">${fn:substring(item.time,5,10)} ${item.week }</a>
        </c:when>
        <c:otherwise>${fn:substring(item.time,5,10)} ${item.week }</c:otherwise>
        </c:choose>
        
        </span></td>
        <td class="his_td"><span class="fin_gold">${item.sumIn }</span></td>
        <td class="his_td">${item.sumInCome }</td>
        <td class="his_td">${item.sumWin }</td>
        <!--td>-</td-->
      </tr>
      </c:forEach>

      <tr class="sum_bar right">
        <td class="center his_total">总计</td>
        <td class="his_total">${total.sumIn }</td>
        <td class="his_total">${total.sumInCome }</td>
        <td class="his_total">${total.sumWin }</td>
        <!--td>-</td-->
      </tr>
    </tbody></table> 
	</td>
  </tr>
  <tr><td id="foot"><b>&nbsp;</b></td></tr>
</tbody></table>
</body></html>