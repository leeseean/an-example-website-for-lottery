<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<td id="page_no" align="left">
				<c:if test="${searchLeague == 0}">
					<span class="pg_top"> ${curPage} / ${control.curpages}
						页&nbsp;&nbsp; <select style="width: 36px" name="pages"
						onchange="selectPage(this.value)">
							<c:forEach var="s" begin="1" end="${control.curpages}">
								<option value=${s } ${s eq curPage?'selected="selected"':'' }>${s}</option>
							</c:forEach>
					</select></span>
				</c:if>
			</td>
			<td id="tool_td">
				<table align="right" border="0" cellspacing="0" cellpadding="0" class="tool_box">
					<tbody>
						<tr>
						 
							<td class="refresh_btn" id="refresh_btn"
								onclick="this.className='refresh_on';">
								<!--秒数更新-->
								<div onclick="javascript:refreshReload()">
									<font id="refreshTime">
										<c:choose>
											<c:when test="${timeType=='roll'}">60</c:when>
											<c:otherwise>90</c:otherwise>
										</c:choose>
									</font>
								</div>
							</td>
							<td class="leg_btn">
								<div onclick="javascript:openXieYi();" id="sel_league">
									选择联赛 (<span id="str_num">全部</span>)
								</div>
							</td>
							<td id="SortGame" class="SortGame" >
								<select id="SortSel" >
									 <option value="C" selected="selected">按联盟排序</option>
									 <option value="T">按时间排序</option>
								</select>
							</td>
							<td class="OrderType" id="Ordertype">
								<select id="myoddType" >
									<option value="H" selected="selected">香港盘</option>
								</select>
							</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
	</tbody>
</table>