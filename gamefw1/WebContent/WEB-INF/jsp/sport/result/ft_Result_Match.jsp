<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<%@ include file="/commons/sport/jsp/sport_common.jsp"%>
<title>皇冠體育</title>
<link rel="stylesheet" href="${resourceRoot}/sport/css/common.css" type="text/css">
<link rel="stylesheet"href="${resourceRoot}/sport/css/mem_body_result.css" type="text/css">

<script type="text/javascript">
		function onLoad(){
			var select_object = document.getElementById("selgtype");
			if('FT'=='BK')
			{
				select_object.options[1].selected = true;
			}
		}

 		var datasArry = eval('${datas}');
		var myLeg = new Array();
		var sportAction="${ctx}/sport/getftRes";
		var secs = 60; //倒计时的秒数 
		var URL = sportAction + "?matchPage=${curPage}&timeType=${timeType}";
		var League = "${searchLeague}";
		function Load() {
			for ( var i = secs; i >= 0; i--) {
				window.setTimeout('doUpdate(' + i + ')', (secs - i) * 1000);
			}
		}

		function doUpdate(num) {
			document.getElementById('refreshTime').innerHTML = '' + num;
			if (num == 0) {
				if (League == "0") {
					window.location = URL;
				} else {
					chk_league();
				}

			}
		}

		function chk_league() {
			lid_form.submit();
		}

		function selall() {
			var len = lid_form.elements.length;
			var does = true;
			does = lid_form.sall.checked;
			for ( var i = 1; i < len; i++) {
				var e = lid_form.elements[i];
				if (e.id.substr(0, 3) == "LID")
					e.checked = does;
			}
		}

		function chk_all(e) {
			if (!e)
				lid_form.sall.checked = e;
		}

		function leagueClose() {
			document.getElementById("abc").style.display = "none";
		}

		function reload() {
			if (League == "0") {
				window.location = URL;
			} else {
				chk_league();
			}
		}

		function selectPage(selPage) {
			window.location.href = sportAction + "?matchPage=" + selPage + "&timeType=${timeType}";
		}

		function openXieYi() {
			document.getElementById("abc").style.display = "block";
			$.layer({
				type : 1,
				shade : [ 0 ],
				area : [ '650px', 'auto' ],
				title : false,
				border : [ 10, 1, '#493721' ],
				closeBtn : [ 0, false ], //去掉默认关闭按钮
				offset : [ '90px', '10px' ],
				page : {
					dom : '.layer_notice'
				}
			});
		}
</script>
<script src="${resourceRoot}/sport/js/sport_frasher.js"></script>
<script language="javascript" src="${resourceRoot}/sport/js/result.js"></script>
<script language="javascript" src="${resourceRoot}/sport/js/zh-tw.js"></script>
</head>
<body id="MRSU" onload="onLoad()">
	<table border="0" cellpadding="0" cellspacing="0" id="box">
		<tbody>
			<tr>
				<td class="top"><h1>
						<em>足球 : 赛果 </em>
					</h1>
				</td>
			</tr>
			<tr>
				<td class="mem">
					<h2>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id="fav_bar">
							<tbody>
								<tr>
									<td id="page_no"><select name="selgtype" id="selgtype" style="font-size: 12px"
										onchange="chg_gtype(selgtype.value,'&searchDate=${searchDate }')">
											<option value="FT" <c:if test="${selgtype eq 'FT' }">selected</c:if>>足球 : 赛果</option>
											<option value="BK" <c:if test="${selgtype eq 'BK' }">selected</c:if>>篮球 : 赛果</option>
									</select></td>
									<td id="tool_td">

										<table border="0" cellspacing="0" cellpadding="0"
											class="tool_box">
											<tbody>
												<tr>
													<td class="searh">
														<form name="game_result" action="${ctx }/sport/getftRes?selgtype=${selgtype}"
															method="POST">
															<span class="rig" style="font-size: 12px">
															<a href="${ctx }/sport/getftRes?searchDate=${beforeDate }">昨日</a>
																&nbsp;&nbsp; 
																<c:if test="${! empty afterDate }">/&nbsp;&nbsp;<a
																		href="${ctx }/sport/getftRes?searchDate=${afterDate}">明日</a>
																</c:if> &nbsp;&nbsp;选择日期 : <input id="searchDate" type="TEXT"
																name="searchDate" value="${searchDate }" size="9"
																maxlength="10" class="txt"> <input type="submit"
																class="btn_search" value="查询" name="submit"> </span>
														</form></td>
													<td class="rsu_refresh">
														<!--秒数更新-->
														<div onclick="javascript:refreshReload()">
															<font id="refreshTime"></font>
														</div>
													</td>
													<!--td class="leg_btn"><div onClick="javascript:chg_league();" id="sel_league">选择联赛</div></td-->
													<td class="OrderType"></td>

												</tr>
											</tbody>
										</table></td>
								</tr>
							</tbody>
						</table>
					</h2> <!--     资料显示的layer     -->

					<table border="0" cellspacing="0" cellpadding="0" class="game">
						<tbody>
							<tr>
								<th class="time">时间</th>

								<th class="rsu">赛果</th>
							</tr>

						</tbody>
					</table>


					<table border="0" cellspacing="0" cellpadding="0" class="game">
						<tbody>
							<!-- 赛事数据开始 -->
							<c:set var="tmpLeague" value=""></c:set>
							<c:forEach var="item" items="${list}">
								<!--SHOW LEGUAGE BEGIN-->
								<tr style="display:${tmpLeague eq item.league ? 'none':'' } ;">
									<td colspan="4" class="b_hline">
										<table border="0" cellpadding="0" cellspacing="0">
											<tbody>
												<tr>
													<td colspan="6" class="b_hline"><span
														onclick="showLeg('${item.league}')" class="showleg">
															<span id="LegOpen"></span> </span><span
														onclick="showLeg('${item.league}')" class="leg_bar">${item.league}</span>
													</td>
												</tr>
											</tbody>
										</table></td>
								</tr>

								<c:set var="tmpLeague" value="${item.league}"></c:set>
								<!--SHOW LEGUAGE END-->
								<tr id="TR_${item.id }" class="b_cen" style="display:;">
									<td rowspan="3" class="time">${item.matchTime }</td>
									<td></td>
									<td colspan="2" class="team_out_ft">
										<table border="0" width="100%" cellpadding="0" cellspacing="0"
											class="team_main">
											<tr class="b_cen">
												<td class="team_c_ft" width="40%" height="20"
													style="color: #CC0000;font-weight: bold">${item.teamH
													}</td>
												<td align="center">vs</td>
												<td class="team_h_ft" width="40%">${item.teamC }</td>
											</tr>
										</table>
									</td>

								</tr>
								<tr id="TR1_${item.id }" class="b_cen"  style="display: ; background-color:#EAEAEA; height: 20px">
									<td class="hr_title ">半场</td>
									<td class="hr_main_ft "><span
										style="overflow:hidden; ${item.matchStatus eq 1 ? '':'color:#686868;font-weight: bold;'}">${item.matchStatus
											eq 1 ? item.hrScoreH : item.hrScoreHCal }</span></td>
									<td class="hr_main_ft "><span
										style="overflow:hidden; ${item.matchStatus eq 1 ? '':'color:#686868;font-weight: bold;'}">${item.matchStatus
											eq 1 ? item.hrScoreC : item.hrScoreCCal }</span></td>

								</tr>
								<tr id="TR2_${item.id }" class="b_cen" 
									style="display:;background-color:#${item.matchStatus eq 1 ? 'D4E8FF':'EAEAEA'};height: 20px">
									<td class="full_title ">全场</td>
									<td class="full_main_ft "><span
										style="overflow:hidden; ${item.matchStatus eq 1 ? '':'color:#686868;font-weight: bold;'}">${item.matchStatus
											eq 1 ? item.flScoreH : item.flScoreHCal }</span></td>
									<td class="full_main_ft "><span
										style="overflow:hidden; ${item.matchStatus eq 1 ? '':'color:#686868;font-weight: bold;'}">${item.matchStatus
											eq 1 ? item.flScoreC : item.flScoreCCal }</span></td>
								</tr>



								<!--SHOW MATCH END-->
							</c:forEach>
							<!-- 赛事数据结束 -->

							<!-- 没有数据 -->
							<c:if test="${empty list }">
								<tr>
									<td colspan="20" class="no_game">您选择的项目暂时没有赛事。请修改您的选项或迟些再返回。</td>
								</tr>
							</c:if>
						</tbody>
					</table>

					</div></td>
			</tr>
			<tr>
				<td id="foot"><b>&nbsp;</b></td>
			</tr>
		</tbody>
	</table>
	</td>
	</tr>
	</tbody>
	</table>
 
	<script type="text/javascript">
		//Load();
		//parent.header.changeMatchCount("${sc.timeType}","${sc.ftRtype}","${sc.bkRtype}","${sc.ftCount}","${sc.bkCount}");
	</script>
</body>
</html>