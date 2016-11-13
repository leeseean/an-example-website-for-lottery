<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html style="overflow-x:hidden">
<head>
<title>皇冠體育</title>

<link rel="stylesheet" href="${resourceRoot}/sport/css/mem_body_ft.css" type="text/css">
<link rel="stylesheet" href="${resourceRoot}/sport/css/mem_body_his.css" type="text/css">

<script src="${resourceRoot}/sport/js/jquery.min.js" type="text/javascript"></script>
<script src="${resourceRoot}/sport/js/layer/layer.min.js" type="text/javascript"></script>

</head>
<body id="MWAG" class="bodyset">
<table border="0" cellpadding="0" cellspacing="0" id="box">
  <tbody><tr>
    <td class="top">
  	  <h1><em>交易状况</em></h1>
	</td>
  </tr>
  <tr>
    <td class="mem">
       <h2>
            <table width="100%" border="0" cellpadding="0" cellspacing="0" id="fav_bar">
              <tbody><tr>
				<td style="line-height:34px;" align="right">
<!-- 			  <input type="button" value="观看取消交易单" class="inputSub2" onclick="wagers_sta();">
 -->
			</td></tr>
            </tbody></table>
        </h2>

            <table border="0" cellspacing="0" cellpadding="0" class="game" style="width: 717px;">
              <tbody>
              <tr>
		        <!--th width="5%">编号</th-->
		        <th width="10%">注单号/投注日期</th>
		        <th width="15%">投注类型</th>
		        <th width="45%">选项</th>
		        <th width="10%">投注额</th>
		        <th width="10%">派彩结果</th>
		        <!--th width="10%">注单状态</th>
		        <th width="12%">可赢</th>
		        <th width="12%">IP</th-->
		      </tr>
			<c:forEach var="bet" items="${bets}">
				<c:if test="${bet.matchRtype ne 'p3'}">
					<c:forEach var="detail" items="${bet.details}">
					 <tr class="his_even center">
				        <!--td>1</td-->
				        <td class="left">
				        <fmt:formatDate value="${bet.orderTime}" pattern="MM-dd" /><br>
				        <fmt:formatDate value="${bet.orderTime}" pattern="HH:mm:ss" />
				        </td>
				        <td>
						${detail.betOddsDes }<br><font color="green">香港盘</font><br>
				        <font color="#0000CC">${bet.betWagersId}</font>
				        <br>
						</td>
				        <td> ${detail.league }<br>[30610]vs[30609]<br>${detail.teamH }   <font color="#0000BB"><b>.VS.</b></font> ${detail.teamC }   <br><font color="#CC0000">${detail.betOddsName } </font> @ <font color="#CC0000"><b>${detail.betOdds }</b></font></td>
				        <td>${bet.betIn}</td>
				        <td>${bet.betUsrWin}</td>
				        <!--<td>未结算</td><td>未结算</td><td></td>-->
				      </tr>
				      </c:forEach>
				</c:if>
				<c:if test="${bet.matchRtype eq 'p3'}">

					<tr class="his_even center">
			        <!--td>1</td-->
			        <td class="left"><fmt:formatDate value="${bet.orderTime}" pattern="MM-dd" /><br>
				        <fmt:formatDate value="${bet.orderTime}" pattern="HH:mm:ss" />
				    </td>
			        <td>
					${detail.betOddsDes }<br><font color="green">3串一</font><br>
			        <font color="#0000CC">${bet.betWagersId}</font>
			        <br>
					</td>
					<td>
					<c:forEach var="detail" items="${bet.details}">
					 	${detail.league }<br>[30610]vs[30609]<br>${detail.teamH }   <font color="#0000BB"><b>.VS.</b></font> ${detail.teamC }   <br><font color="#CC0000">${detail.betOddsName } </font> @ <font color="#CC0000"><b>${detail.betOdds }</b></font>
				        <hr style="margin:5px 0;height:1px;border:none;border-top:1px solid #ddd;width: 95%">
					</c:forEach>
					</td>
			       <td>${bet.betIn}</td>
				   <td>${bet.betUsrWin}</td>
				        <!--<td>未结算</td><td>未结算</td><td></td>-->
			    	</tr>
				</c:if>
			</c:forEach>

		      <tr class="sum_bar center">
		        <td colspan="2" class="right bold">统计:</td>
		        <td>${total.count }</td>
		        <td>${total.sumIn }</td>
		        <td>${total.sumWin }</td>
		        <!--<td>245.7</td><td>&nbsp;</td>-->
		      </tr>

              <!-- <tr>
                <td id="page_no"></td>
                <td class="right"><span onclick="wagers_sta('Y');" class="wag_btn" style="display:none;">您有(<span class="wag_none">0</span>)张取消注单</span>
                	<span class="wag_btn2">您有(<span class="wag_none">0</span>)张取消注单</span>
                </td>
              </tr> -->
            </tbody></table>
            <c:if test="${empty bets}">
				<table border="0" cellspacing="1" cellpadding="0" class="game">
					<tbody><tr>
			        <td height="70" class="b_cen">您没有未结算的注单。如果本日已有交易，请查看帐户历史。</td>
			      </tr>
			    </tbody>
			    </table>
			</c:if>

  <tr><td id="foot"><b>&nbsp;</b></td></tr>
</tbody></table>
</body></html>