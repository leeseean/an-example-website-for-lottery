<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="ui-mod-body">
	<div class="ui-table">
		<div class="ui-table-loop">
			<div class="ui-head-son">
				<div class="pure-g">
					<div class="pure-u-1-24">
						<div class="ui-item-son ui-item-colspan">序号</div>
					</div>
					<div class="pure-u-3-24">
						<div class="ui-item-son ui-item-colspan">期数</div>
					</div>
					<div class="pure-u-3-24">
						<div class="ui-item-son ui-item-colspan">开奖时间</div>
					</div>
					<div class="pure-u-7-24">
						<div class="ui-item-son">开奖球号</div>
					</div>

					<div class="pure-u-1-24">
						<div class="ui-item-son ui-item-colspan">总分</div>
					</div>
					<div class="pure-u-1-24">
						<div class="ui-item-son ui-item-colspan">龙虎</div>
					</div>
					<div class="pure-u-1-24">
						<div class="ui-item-son ui-item-colspan">第一球</div>
					</div>
					<div class="pure-u-1-24">
						<div class="ui-item-son ui-item-colspan">第二球</div>
					</div>
					<div class="pure-u-1-24">
						<div class="ui-item-son ui-item-colspan">第三球</div>
					</div>
					<div class="pure-u-1-24">
						<div class="ui-item-son ui-item-colspan">第四球</div>
					</div>
					<div class="pure-u-1-24">
						<div class="ui-item-son ui-item-colspan">第五球</div>
					</div>
					<div class="pure-u-1-24">
						<div class="ui-item-son ui-item-colspan">第六球</div>
					</div>
					<div class="pure-u-1-24">
						<div class="ui-item-son ui-item-colspan">第七球</div>
					</div>
					<div class="pure-u-1-24">
						<div class="ui-item-son ui-item-colspan">第八球</div>
					</div>
				</div>
			</div>
			<div class="ui-head-son ui-head-blank">
				<div class="pure-g">
					<div class="pure-u-1-24">
						<div class="ui-item-son ui-item-blank"></div>
					</div>
					<div class="pure-u-3-24">
						<div class="ui-item-son ui-item-blank"></div>
					</div>
					<div class="pure-u-3-24">
						<div class="ui-item-son ui-item-blank"></div>
					</div>
					<div class="pure-u-7-24">
						<div class="pure-g">
							<div class="pure-u-3-24">
								<div class="ui-item-son">一</div>
							</div>
							<div class="pure-u-3-24">
								<div class="ui-item-son">二</div>
							</div>
							<div class="pure-u-3-24">
								<div class="ui-item-son">三</div>
							</div>
							<div class="pure-u-3-24">
								<div class="ui-item-son">四</div>
							</div>
							<div class="pure-u-3-24">
								<div class="ui-item-son">五</div>
							</div>
							<div class="pure-u-3-24">
								<div class="ui-item-son">六</div>
							</div>
							<div class="pure-u-3-24">
								<div class="ui-item-son">七</div>
							</div>
							<div class="pure-u-3-24">
								<div class="ui-item-son">八</div>
							</div>
						</div>
					</div>
					<div class="pure-u-1-24">
						<div class="ui-item-son ui-item-blank"></div>
					</div>
					<div class="pure-u-1-24">
						<div class="ui-item-son ui-item-blank"></div>
					</div>
					<div class="pure-u-1-24">
						<div class="ui-item-son ui-item-blank"></div>
					</div>
					<div class="pure-u-1-24">
						<div class="ui-item-son ui-item-blank"></div>
					</div>
					<div class="pure-u-1-24">
						<div class="ui-item-son ui-item-blank"></div>
					</div>
					<div class="pure-u-1-24">
						<div class="ui-item-son ui-item-blank"></div>
					</div>
					<div class="pure-u-1-24">
						<div class="ui-item-son ui-item-blank"></div>
					</div>
					<div class="pure-u-1-24">
						<div class="ui-item-son ui-item-blank"></div>
					</div>
					<div class="pure-u-1-24">
						<div class="ui-item-son ui-item-blank"></div>
					</div>
					<div class="pure-u-1-24">
						<div class="ui-item-son ui-item-blank"></div>
					</div>
				</div>
			</div>
			<div class="ui-body-son ui-body-ball">
				<div class="pure-g">
					<c:set var="blueSet" scope="request"
						value=".03.04.09.10.14.15.20.25.26.31.36.37.41.42.47.48."></c:set>
					<c:set var="redSet" scope="request"
						value=".01.02.07.08.12.13.18.19.23.24.29.30.34.35.40.45.46."></c:set>
					<c:set var="greenSet" scope="request"
						value=".05.06.11.16.17.21.22.27.28.32.33.38.39.43.44.49."></c:set>
					<c:choose>
						<c:when test="${empty page.result}">
							<div class="pure-u-1">
								<div class="ui-item-son">没有记录！</div>
							</div>
						</c:when>
						<c:otherwise>
							<!-- 循环 -->
							<c:forEach var="item" items="${page.result}"
								varStatus="itemindex">
								<div class="pure-u-1-24">
									<div class="ui-item-son">${itemindex.index+1}</div>
								</div>
								<div class="pure-u-3-24">
									<div class="ui-item-son">${item.qs}</div>
								</div>
								<div class="pure-u-3-24">
									<div class="ui-item-son">${item.gtKjsj}</div>
								</div>
								<!--开奖球号  -->
								<div class="pure-u-7-24">
									<div class="pure-g">
										<div class="pure-u-3-24">
											<div class="ui-item-son">

												<c:choose>
													<c:when test="${fn:contains(blueSet, item.kjhm1)}">
														<span class="ball ball-blue">${item.kjhm1}</span>
													</c:when>
													<c:when test="${fn:contains(redSet, item.kjhm1)}">
														<span class="ball ball-red">${item.kjhm1}</span>
													</c:when>
													<c:when test="${fn:contains(greenSet, item.kjhm1)}">
														<span class="ball ball-green">${item.kjhm1}</span>
													</c:when>
													<c:otherwise>
									${item.kjhm1}
								</c:otherwise>
												</c:choose>
											</div>
										</div>
										<div class="pure-u-3-24">
											<div class="ui-item-son">
												<c:choose>
													<c:when test="${fn:contains(blueSet, item.kjhm2)}">
														<span class="ball ball-blue">${item.kjhm2}</span>
													</c:when>
													<c:when test="${fn:contains(redSet, item.kjhm2)}">
														<span class="ball ball-red">${item.kjhm2}</span>
													</c:when>
													<c:when test="${fn:contains(greenSet, item.kjhm2)}">
														<span class="ball ball-green">${item.kjhm2}</span>
													</c:when>
													<c:otherwise>
									${item.kjhm2}
								</c:otherwise>
												</c:choose>
											</div>
										</div>
										<div class="pure-u-3-24">
											<div class="ui-item-son">
												<c:choose>
													<c:when test="${fn:contains(blueSet, item.kjhm3)}">
														<span class="ball ball-blue">${item.kjhm3}</span>
													</c:when>
													<c:when test="${fn:contains(redSet, item.kjhm3)}">
														<span class="ball ball-red">${item.kjhm3}</span>
													</c:when>
													<c:when test="${fn:contains(greenSet, item.kjhm3)}">
														<span class="ball ball-green">${item.kjhm3}</span>
													</c:when>
													<c:otherwise>
							${item.kjhm3}
						</c:otherwise>
												</c:choose>
											</div>
										</div>
										<div class="pure-u-3-24">
											<div class="ui-item-son">
												<c:choose>
													<c:when test="${fn:contains(blueSet, item.kjhm4)}">
														<span class="ball ball-blue">${item.kjhm4}</span>
													</c:when>
													<c:when test="${fn:contains(redSet, item.kjhm4)}">
														<span class="ball ball-red">${item.kjhm4}</span>
													</c:when>
													<c:when test="${fn:contains(greenSet, item.kjhm4)}">
														<span class="ball ball-green">${item.kjhm4}</span>
													</c:when>
													<c:otherwise>
									${item.kjhm4}
								</c:otherwise>
												</c:choose>
											</div>
										</div>
										<div class="pure-u-3-24">
											<div class="ui-item-son">
												<c:choose>
													<c:when test="${fn:contains(blueSet, item.kjhm5)}">
														<span class="ball ball-blue">${item.kjhm5}</span>
													</c:when>
													<c:when test="${fn:contains(redSet, item.kjhm5)}">
														<span class="ball ball-red">${item.kjhm5}</span>
													</c:when>
													<c:when test="${fn:contains(greenSet, item.kjhm5)}">
														<span class="ball ball-green">${item.kjhm5}</span>
													</c:when>
													<c:otherwise>
									${item.kjhm5}
								</c:otherwise>
												</c:choose>
											</div>
										</div>
										<div class="pure-u-3-24">
											<div class="ui-item-son">
												<c:choose>
													<c:when test="${fn:contains(blueSet, item.kjhm6)}">
														<span class="ball ball-blue">${item.kjhm6}</span>
													</c:when>
													<c:when test="${fn:contains(redSet, item.kjhm6)}">
														<span class="ball ball-red">${item.kjhm6}</span>
													</c:when>
													<c:when test="${fn:contains(greenSet, item.kjhm6)}">
														<span class="ball ball-green">${item.kjhm6}</span>
													</c:when>
													<c:otherwise>
									${item.kjhm6}
								</c:otherwise>
												</c:choose>
											</div>
										</div>
										<div class="pure-u-3-24">
											<div class="ui-item-son">
												<c:choose>
													<c:when test="${fn:contains(blueSet, item.kjhm7)}">
														<span class="ball ball-blue">${item.kjhm7}</span>
													</c:when>
													<c:when test="${fn:contains(redSet, item.kjhm7)}">
														<span class="ball ball-red">${item.kjhm7}</span>
													</c:when>
													<c:when test="${fn:contains(greenSet, item.kjhm7)}">
														<span class="ball ball-green">${item.kjhm7}</span>
													</c:when>
													<c:otherwise>
									${item.kjhm7}
								</c:otherwise>
												</c:choose>
											</div>
										</div>

										<div class="pure-u-3-24">
											<div class="ui-item-son">
												<c:choose>
													<c:when test="${fn:contains(blueSet, item.kjhm8)}">
														<span class="ball ball-blue">${item.kjhm8}</span>
													</c:when>
													<c:when test="${fn:contains(redSet, item.kjhm8)}">
														<span class="ball ball-red">${item.kjhm8}</span>
													</c:when>
													<c:when test="${fn:contains(greenSet, item.kjhm8)}">
														<span class="ball ball-green">${item.kjhm8}</span>
													</c:when>
													<c:otherwise>
									${item.kjhm8}
								</c:otherwise>
												</c:choose>
											</div>
										</div>


									</div>
								</div>
								<!-- 总分 -->
								<div class="pure-u-1-24">
									<div class="ui-item-son">${item.zf}</div>
								</div>

								<!-- 龙虎 -->
								<div class="pure-u-1-24">
									<div class="ui-item-son">
										<c:choose>
											<c:when test="${item.lh=='虎'}">
												<font color="blue">${item.lh}</font>
											</c:when>
											<c:when test="${item.lh=='龙'}">
												<font color="red">${item.lh}</font>
											</c:when>
											<c:otherwise>
								${item.lh}
							</c:otherwise>
										</c:choose>
									</div>
								</div>






								<!-- 1qiu-->
								<div class="pure-u-1-24">
									<div class="ui-item-son">
										<div class="pure-g">
											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d1qDx=='小'}">
														<font color="blue">${item.d1qDx}</font>
													</c:when>
													<c:when test="${item.d1qDx=='大'}">
														<font color="red">${item.d1qDx}</font>
													</c:when>
													<c:otherwise>
												${item.d1qDx}
											</c:otherwise>
												</c:choose>
											</div>
											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d1qDs=='双'}">
														<font color="blue">${item.d1qDs}</font>
													</c:when>
													<c:when test="${item.d1qDs=='单'}">
														<font color="red">${item.d1qDs}</font>
													</c:when>
													<c:otherwise>
												${item.d1qDs}
											</c:otherwise>
												</c:choose>
											</div>
										</div>
										<div class="pure-g">
											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d1qDxnb=='东'}">
														<font color="red">${item.d1qDxnb}</font>
													</c:when>
													<c:when test="${item.d1qDxnb=='南'}">
														<font color="blue">${item.d1qDxnb}</font>
													</c:when>
													<c:when test="${item.d1qDxnb=='西'}">
														<font color="green">${item.d1qDxnb}</font>
													</c:when>
													<c:when test="${item.d1qDxnb=='北'}">
														<font color="purple">${item.d1qDxnb}</font>
													</c:when>
													<c:otherwise>
												${item.d1qDxnb}
											</c:otherwise>
												</c:choose>
											</div>
											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d1qZfb=='中'}">
														<font color="red">${item.d1qZfb}</font>
													</c:when>
													<c:when test="${item.d1qZfb=='发'}">
														<font color="blue">${item.d1qZfb}</font>
													</c:when>
													<c:when test="${item.d1qZfb=='白'}">
														<font color="green">${item.d1qZfb}</font>
													</c:when>
													<c:otherwise>
												${item.d1qZfb}
											</c:otherwise>
												</c:choose>
											</div>
										</div>
									</div>
								</div>



								<!-- 2qiu-->
								<div class="pure-u-1-24">
									<div class="ui-item-son">
										<div class="pure-g">
											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d2qDx=='小'}">
														<font color="blue">${item.d2qDx}</font>
													</c:when>
													<c:when test="${item.d2qDx=='大'}">
														<font color="red">${item.d2qDx}</font>
													</c:when>
													<c:otherwise>
										${item.d2qDx}
									</c:otherwise>
												</c:choose>
											</div>
											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d2qDs=='双'}">
														<font color="blue">${item.d2qDs}</font>
													</c:when>
													<c:when test="${item.d2qDs=='单'}">
														<font color="red">${item.d2qDs}</font>
													</c:when>
													<c:otherwise>
										${item.d2qDs}
									</c:otherwise>
												</c:choose>
											</div>

											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d2qDs=='东'}">
														<font color="red">${item.d2qDs}</font>
													</c:when>
													<c:when test="${item.d2qDs=='南'}">
														<font color="blue">${item.d2qDs}</font>
													</c:when>
													<c:when test="${item.d2qDs=='西'}">
														<font color="green">${item.d2qDs}</font>
													</c:when>
													<c:when test="${item.d2qDs=='北'}">
														<font color="purple">${item.d2qDs}</font>
													</c:when>
													<c:otherwise>
												${item.d2qDs}
											</c:otherwise>
												</c:choose>
											</div>

											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d2qZfb=='中'}">
														<font color="red">${item.d2qZfb}</font>
													</c:when>
													<c:when test="${item.d2qZfb=='发'}">
														<font color="blue">${item.d2qZfb}</font>
													</c:when>
													<c:when test="${item.d2qZfb=='白'}">
														<font color="green">${item.d2qZfb}</font>
													</c:when>
													<c:otherwise>
												${item.d2qZfb}
											</c:otherwise>
												</c:choose>
											</div>
										</div>
									</div>
								</div>





								<!-- 3qiu-->
								<div class="pure-u-1-24">
									<div class="ui-item-son">
										<div class="pure-g">
											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d3qDx=='小'}">
														<font color="blue">${item.d3qDx}</font>
													</c:when>
													<c:when test="${item.d3qDx=='大'}">
														<font color="red">${item.d3qDx}</font>
													</c:when>
													<c:otherwise>
										${item.d3qDx}
									</c:otherwise>
												</c:choose>
											</div>

											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d3qDx=='双'}">
														<font color="blue">${item.d3qDx}</font>
													</c:when>
													<c:when test="${item.d3qDx=='单'}">
														<font color="red">${item.d3qDx}</font>
													</c:when>
													<c:otherwise>
										${item.d3qDx}
									</c:otherwise>
												</c:choose>
											</div>
											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d3qDx=='东'}">
														<font color="red">${item.d3qDx}</font>
													</c:when>
													<c:when test="${item.d3qDx=='南'}">
														<font color="blue">${item.d3qDx}</font>
													</c:when>
													<c:when test="${item.d3qDx=='西'}">
														<font color="green">${item.d3qDx}</font>
													</c:when>
													<c:when test="${item.d3qDx=='北'}">
														<font color="purple">${item.d3qDx}</font>
													</c:when>
													<c:otherwise>
										${item.d3qDx}
									</c:otherwise>
												</c:choose>
											</div>
											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d3qDx=='中'}">
														<font color="red">${item.d3qDx}</font>
													</c:when>
													<c:when test="${item.d3qDx=='发'}">
														<font color="blue">${item.d3qDx}</font>
													</c:when>
													<c:when test="${item.d3qDx=='白'}">
														<font color="green">${item.d3qDx}</font>
													</c:when>
													<c:otherwise>
										${item.d3qDx}
									</c:otherwise>
												</c:choose>
											</div>
											<%-- <div class="pure-u-1-2">
										<c:choose>
											<c:when test="${item.d3qDx=='小'}">
												<font color="blue">${item.d3qDx}</font>
											</c:when>
											<c:when test="${item.d3qDx=='大'}">
												<font color="red">${item.d3qDx}</font>
											</c:when>
											<c:otherwise>
											${item.d3qDx}
										</c:otherwise>
										</c:choose>
									</div> --%>
										</div>
									</div>
								</div>







								<div class="pure-u-1-24">
									<div class="ui-item-son">
										<div class="pure-g">
											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d4qDs=='双'}">
														<font color="blue">${item.d4qDs}</font>
													</c:when>
													<c:when test="${item.d4qDs=='单'}">
														<font color="red">${item.d4qDs}</font>
													</c:when>
													<c:otherwise>
										${item.d4qDs}
									</c:otherwise>
												</c:choose>
											</div>
											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d4qDx=='大'}">
														<font color="blue">${item.d4qDx}</font>
													</c:when>
													<c:when test="${item.d4qDx=='小'}">
														<font color="red">${item.d4qDx}</font>
													</c:when>
													<c:otherwise>
										${item.d4qDx}
									</c:otherwise>
												</c:choose>
											</div>
											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d4qDxnb=='南'}">
														<font color="blue">${item.d4qDxnb}</font>
													</c:when>
													<c:when test="${item.d4qDxnb=='西'}">
														<font color="green">${item.d4qDxnb}</font>
													</c:when>
													<c:when test="${item.d4qDxnb=='北'}">
														<font color="purple">${item.d4qDxnb}</font>
													</c:when>
													<c:otherwise>
										${item.d4qDxnb}
									</c:otherwise>
												</c:choose>
											</div>
											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d4qZfb=='中'}">
														<font color="red">${item.d4qZfb}</font>
													</c:when>
													<c:when test="${item.d4qZfb=='发'}">
														<font color="blue">${item.d4qZfb}</font>
													</c:when>
													<c:when test="${item.d4qZfb=='白'}">
														<font color="green">${item.d4qZfb}</font>
													</c:when>
													<c:otherwise>
										${item.d4qZfb}
									</c:otherwise>
												</c:choose>
											</div>

										</div>
									</div>
								</div>






								<div class="pure-u-1-24">
									<div class="ui-item-son">
										<div class="pure-g">
											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d5qDx=='小'}">
														<font color="blue">${item.d5qDx}</font>
													</c:when>
													<c:when test="${item.d5qDx=='大'}">
														<font color="red">${item.d5qDx}</font>
													</c:when>
													<c:otherwise>
										${item.d5qDx}
									</c:otherwise>
												</c:choose>
											</div>
											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d5qDs=='双'}">
														<font color="blue">${item.d5qDs}</font>
													</c:when>
													<c:when test="${item.d5qDs=='单'}">
														<font color="red">${item.d5qDs}</font>
													</c:when>
													<c:otherwise>
										${item.d5qDs}
									</c:otherwise>
												</c:choose>
											</div>
											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d5qDxnb=='东'}">
														<font color="red">${item.d5qDxnb}</font>
													</c:when>
													<c:when test="${item.d5qDxnb=='南'}">
														<font color="blue">${item.d5qDxnb}</font>
													</c:when>
													<c:when test="${item.d5qDxnb=='西'}">
														<font color="green">${item.d5qDxnb}</font>
													</c:when>
													<c:when test="${item.d5qDxnb=='北'}">
														<font color="purple">${item.d5qDxnb}</font>
													</c:when>
													<c:otherwise>
										${item.d5qDxnb}
									</c:otherwise>
												</c:choose>
											</div>
											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d5qZfb=='中'}">
														<font color="red">${item.d5qZfb}</font>
													</c:when>
													<c:when test="${item.d5qZfb=='发'}">
														<font color="blue">${item.d5qZfb}</font>
													</c:when>
													<c:when test="${item.d5qZfb=='白'}">
														<font color="green">${item.d5qZfb}</font>
													</c:when>
													<c:otherwise>
										${item.d5qZfb}
									</c:otherwise>
												</c:choose>
											</div>

										</div>
									</div>
								</div>








								<div class="pure-u-1-24">
									<div class="ui-item-son">
										<div class="pure-g">
											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d6qDx=='小'}">
														<font color="blue">${item.d6qDx}</font>
													</c:when>
													<c:when test="${item.d6qDx=='大'}">
														<font color="red">${item.d6qDx}</font>
													</c:when>
													<c:otherwise>
										${item.d6qDx}
									</c:otherwise>
												</c:choose>
											</div>
											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d6qDx=='双'}">
														<font color="blue">${item.d6qDx}</font>
													</c:when>
													<c:when test="${item.d6qDx=='单'}">
														<font color="red">${item.d6qDx}</font>
													</c:when>
													<c:otherwise>
										${item.d6qDx}
									</c:otherwise>
												</c:choose>
											</div>
											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d6qDxnb=='东'}">
														<font color="red">${item.d6qDxnb}</font>
													</c:when>
													<c:when test="${item.d6qDxnb=='南'}">
														<font color="blue">${item.d6qDxnb}</font>
													</c:when>
													<c:when test="${item.d6qDxnb=='西'}">
														<font color="green">${item.d6qDxnb}</font>
													</c:when>
													<c:when test="${item.d6qDxnb=='北'}">
														<font color="purple">${item.d6qDxnb}</font>
													</c:when>
													<c:otherwise>
										${item.d6qDxnb}
									</c:otherwise>
												</c:choose>
											</div>
											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d6qZfb=='中'}">
														<font color="red">${item.d6qZfb}</font>
													</c:when>
													<c:when test="${item.d6qZfb=='发'}">
														<font color="blue">${item.d6qZfb}</font>
													</c:when>
													<c:when test="${item.d6qZfb=='白'}">
														<font color="green">${item.d6qZfb}</font>
													</c:when>
													<c:otherwise>
										${item.d6qZfb}
									</c:otherwise>
												</c:choose>
											</div>

										</div>
									</div>
								</div>







								<div class="pure-u-1-24">
									<div class="ui-item-son">
										<div class="pure-g">
											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d7qDx=='小'}">
														<font color="blue">${item.d7qDx}</font>
													</c:when>
													<c:when test="${item.d7qDx=='大'}">
														<font color="red">${item.d7qDx}</font>
													</c:when>
													<c:otherwise>
										${item.d7qDx}
									</c:otherwise>
												</c:choose>
											</div>
											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d7qDs=='双'}">
														<font color="blue">${item.d7qDs}</font>
													</c:when>
													<c:when test="${item.d7qDs=='单'}">
														<font color="red">${item.d7qDs}</font>
													</c:when>
													<c:otherwise>
										${item.d7qDs}
									</c:otherwise>
												</c:choose>
											</div>
											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d7qDxnb=='东'}">
														<font color="red">${item.d7qDxnb}</font>
													</c:when>
													<c:when test="${item.d7qDxnb=='南'}">
														<font color="blue">${item.d7qDxnb}</font>
													</c:when>
													<c:when test="${item.d7qDxnb=='西'}">
														<font color="green">${item.d7qDxnb}</font>
													</c:when>
													<c:when test="${item.d7qDxnb=='北'}">
														<font color="purple">${item.d7qDxnb}</font>
													</c:when>
													<c:otherwise>
										${item.d7qDxnb}
									</c:otherwise>
												</c:choose>
											</div>
											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d7qZfb=='中'}">
														<font color="red">${item.d7qZfb}</font>
													</c:when>
													<c:when test="${item.d7qZfb=='发'}">
														<font color="blue">${item.d7qZfb}</font>
													</c:when>
													<c:when test="${item.d7qZfb=='白'}">
														<font color="green">${item.d7qZfb}</font>
													</c:when>
													<c:otherwise>
										${item.d7qZfb}
									</c:otherwise>
												</c:choose>
											</div>

										</div>
									</div>
								</div>









								<div class="pure-u-1-24">
									<div class="ui-item-son">
										<div class="pure-g">
											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d8qDx=='小'}">
														<font color="blue">${item.d8qDx}</font>
													</c:when>
													<c:when test="${item.d8qDx=='大'}">
														<font color="red">${item.d8qDx}</font>
													</c:when>
													<c:otherwise>
										${item.d8qDx}
									</c:otherwise>
												</c:choose>
											</div>
											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d8qDs=='双'}">
														<font color="blue">${item.d8qDs}</font>
													</c:when>
													<c:when test="${item.d8qDs=='单'}">
														<font color="red">${item.d8qDs}</font>
													</c:when>
													<c:otherwise>
										${item.d8qDs}
									</c:otherwise>
												</c:choose>
											</div>
											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d8qDxnb=='东'}">
														<font color="red">${item.d8qDxnb}</font>
													</c:when>
													<c:when test="${item.d8qDxnb=='南'}">
														<font color="blue">${item.d8qDxnb}</font>
													</c:when>
													<c:when test="${item.d8qDxnb=='西'}">
														<font color="green">${item.d8qDxnb}</font>
													</c:when>
													<c:when test="${item.d8qDxnb=='北'}">
														<font color="purple">${item.d8qDxnb}</font>
													</c:when>
													<c:otherwise>
										${item.d8qDxnb}
									</c:otherwise>
												</c:choose>
											</div>
											<div class="pure-u-1-2">
												<c:choose>
													<c:when test="${item.d8qZfb=='中'}">
														<font color="red">${item.d8qZfb}</font>
													</c:when>
													<c:when test="${item.d8qZfb=='发'}">
														<font color="blue">${item.d8qZfb}</font>
													</c:when>
													<c:when test="${item.d8qZfb=='白'}">
														<font color="green">${item.d8qZfb}</font>
													</c:when>
													<c:otherwise>
										${item.d8qZfb}
									</c:otherwise>
												</c:choose>
											</div>
										</div>
									</div>
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