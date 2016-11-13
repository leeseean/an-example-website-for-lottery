<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="abc">
	<form name="lid_form" method="post" action="${ctx}/sport/goMatchCenter">
		<input type="hidden" name="League" value="true"> 
		<input type="hidden" name="timeType" value="${timeType}">
		<input type="hidden" name="matchType" value="${matchType}">
		<input type="hidden" name="rtype" value="${rtype }">
		<input type="hidden" id="leas" name="leas" value="${leas}"/>
		<table border="0" cellpadding="0" cellspacing="0" id="box1" class="layer_notice" style="display: none;">
			<tbody>
				<tr>
					<td class="leg_top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="league_hf">
							<tbody>
								<tr>
									<td width="30%">
										<h1><input type="checkbox" value="all" id="sall" onClick="selall();"><label class="all_sel">全选</label></h1>
									</td>
									<td class="btn_td">
										<input type="button" name="button" id="button" value="取消" class="enter_btn" onclick="leagueClose();">&nbsp; 
										<input type="submit" name="button" id="button" value="提交" class="enter_btn" onclick="chk_league();">
									</td>
									<td class="close_td"><span class="close_box" onClick="leagueClose();">关闭</span></td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<div class="leg_mem">
							<table border="0" cellspacing="1" cellpadding="0" class="leg_game" style="background-color: white">
								<tbody>
									<tr>
										<c:set var="ino" value="1"></c:set>
										<c:forEach var="item" items="${leagues}">
											<td class="league">
												<div>
													<input type="checkbox" name="lea" id="LID_${ino}" value="${item }" onclick="chk_all(this.checked);"${leaMap[item]!=null? "checked='checked'":"" }> <font>${item}</font>
												</div>
											</td>
											<c:if test="${ino%3 == 0}">
												</tr><tr>
											</c:if>
											<c:set var="ino" value="${ino+1}"></c:set>
										</c:forEach>
									</tr>
								</tbody>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td class="leg_top">
						<table width="100%" class="league_hf" border="0" cellspacing="0" cellpadding="0">
							<tbody>
								<tr>
									<td width="30%"></td>
									<td class="btn_td">
										<input type="button" name="button" id="button" value="取消" class="enter_btn" onclick="leagueClose();">&nbsp; 
										<input type="submit" name="button" id="button" value="提交" class="enter_btn" onclick="chk_league();">
									</td>
									<td class="close_td"></td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>