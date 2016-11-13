<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="ui-mod-body">
	<div class="ui-table">
      <div class="ui-table-loop">
        <div class="ui-head-son">
          <div class="pure-g">
            <div class="pure-u-2-24">
						<div class="ui-item-son ui-item-colspan">序号</div>
					</div>
					<div class="pure-u-2-24">
						<div class="ui-item-son ui-item-colspan">期数</div>
					</div>
					<div class="pure-u-4-24">
						<div class="ui-item-son ui-item-colspan">开奖时间</div>
					</div>
					<div class="pure-u-8-24">
						<div class="ui-item-son">开奖球号</div>
					</div>
					<div class="pure-u-4-24">
						<div class="ui-item-son ui-item-colspan">大小</div>
					</div>
					<div class="pure-u-4-24">
						<div class="ui-item-son ui-item-colspan">单双</div>
					</div>
				</div>
			</div>
			<div class="ui-head-son ui-head-blank">
				<div class="pure-g">
					<div class="pure-u-2-24">
						<div class="ui-item-son ui-item-blank"></div>
					</div>
					<div class="pure-u-2-24">
						<div class="ui-item-son ui-item-blank"></div>
					</div>
					<div class="pure-u-4-24">
						<div class="ui-item-son ui-item-blank"></div>
					</div>
					<div class="pure-u-8-24">
						<div class="pure-g">
							<div class="pure-u-6-24">
								<div class="ui-item-son">第一球</div>
							</div>
							<div class="pure-u-6-24">
								<div class="ui-item-son">第二球</div>
							</div>
							<div class="pure-u-6-24">
								<div class="ui-item-son">第三球</div>
							</div>
							<div class="pure-u-6-24">
								<div class="ui-item-son">特码</div>
							</div>
						</div>
					</div>
					<div class="pure-u-4-24">
						<div class="ui-item-son ui-item-blank"></div>
					</div>
					<div class="pure-u-4-24">
						<div class="ui-item-son ui-item-blank"></div>
					</div>
					<%--<div class="pure-u-1-24">
						<div class="ui-item-son ui-item-blank"></div>
					</div>
					<div class="pure-u-2-24">
						<div class="ui-item-son ui-item-blank"></div>
					</div>
				--%></div>
			</div>
			<div class="ui-body-son ui-body-ball">
				<div class="pure-g">

					<c:set var="blueSet" scope="request" value="2.5.8.11.17.20.23.26"></c:set>
					<c:set var="graySet" scope="request" value="0.13.14.27"></c:set>
					<c:set var="greenSet" scope="request" value="1.4.7.10.16.19.22.25"></c:set>
					<c:set var="redSet" scope="request" value="3.6.9.12.15.18.21.24"></c:set>
					
			<c:choose>
					<c:when test="${empty page.result}">
							<div class="pure-u-1">
								<div class="ui-item-son">没有记录！</div>
							</div>
					</c:when>
			<c:otherwise>
					<!-- 循环 -->
					<c:forEach var="item" items="${page.result}" varStatus="item_index">
						<div class="pure-u-2-24">
							<div class="ui-item-son">${item_index.index+1}</div>
						</div>
						<div class="pure-u-2-24">
							<div class="ui-item-son">${item.formatQs}</div>
						</div>
						<div class="pure-u-4-24">
							<div class="ui-item-son">${item.gtKjsj}</div>
						</div>

						<div class="pure-u-8-24">
							<div class="pure-g">
							
								<div class="pure-u-6-24">
									<div class="ui-item-son" >
											<span >${item.d1q}</span>
									</div>
								</div>


								<div class="pure-u-6-24">
									<div class="ui-item-son">
												<span>${item.d2q}</span>
									</div>
								</div>


								<div class="pure-u-6-24">
									<div class="ui-item-son" >
												<span> ${item.d3q} </span>
									</div>
								</div>

								<div class="pure-u-6-24">
									<div class="ui-item-son" style="margin: 0px;padding:0px;">
										
										<c:set var="kjjg" value=".${item.xy28Kjjg}"/> 
										<c:choose>
											<c:when test="${fn:contains(blueSet, kjjg)}">
												<span class="ball ball-blue">${item.xy28Kjjg}</span>
											</c:when>
											<c:when test="${fn:contains(graySet, kjjg)}">
												<span class="ball ball-gray">${item.xy28Kjjg}</span>
											</c:when>
											<c:when test="${fn:contains(greenSet, kjjg)}">
												<span class="ball ball-green">${item.xy28Kjjg}</span>
											</c:when>
											<c:when test="${fn:contains(redSet, kjjg)}">
												<span class="ball ball-red">${item.xy28Kjjg}</span>
											</c:when>
										</c:choose>
									</div>
								</div>
							</div>
						</div>
						
						<div class="pure-u-4-24">
							<div class="ui-item-son blue">${item.dx}</div>
						</div>

						<div class="pure-u-4-24">
							<div class="ui-item-son blue">${item.ds}</div>
						</div>
						
						
						<!-- / 循环 -->
					</c:forEach>
				</c:otherwise>
        </c:choose>
				</div>
			</div>
			<div class="ui-head-son">
				<div class="pure-g"></div>
			</div>
		</div>
	</div>
</div>