<%@ page language="java"   pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<div class="ui-mod-body" >
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
            <div class="pure-u-7-24">
              <div class="ui-item-son">开奖球号</div>
            </div>
            <div class="pure-u-3-24">
              <div class="ui-item-son ui-item-colspan">特码</div>
            </div>
            <div class="pure-u-3-24">
              <div class="ui-item-son ui-item-colspan">总分</div>
            </div>
            <div class="pure-u-1-24">
              <div class="ui-item-son ui-item-colspan">生肖</div>
            </div>
            <div class="pure-u-2-24">
              <div class="ui-item-son ui-item-colspan">合数</div>
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
            <div class="pure-u-7-24">
              <div class="pure-g">
                <div class="pure-u-4-24">
                  <div class="ui-item-son">平1</div>
                </div>
                <div class="pure-u-3-24">
                  <div class="ui-item-son">平2</div>
                </div>
                <div class="pure-u-3-24">
                  <div class="ui-item-son">平3</div>
                </div>
                <div class="pure-u-4-24">
                  <div class="ui-item-son">平4</div>
                </div>
                <div class="pure-u-3-24">
                  <div class="ui-item-son">平5</div>
                </div>
                <div class="pure-u-3-24">
                  <div class="ui-item-son">平6</div>
                </div>
                <div class="pure-u-4-24">
                  <div class="ui-item-son">特码</div>
                </div>
              </div>
            </div>
            <div class="pure-u-3-24">
              <div class="ui-item-son ui-item-blank"></div>
            </div>
            <div class="pure-u-3-24">
              <div class="ui-item-son ui-item-blank"></div>
            </div>
            <div class="pure-u-1-24">
              <div class="ui-item-son ui-item-blank"></div>
            </div>
            <div class="pure-u-2-24">
              <div class="ui-item-son ui-item-blank"></div>
            </div>
          </div>
        </div>
        <div class="ui-body-son ui-body-ball">
          <div class="pure-g">
          	<c:set var="blueSet" scope="request" value=".03.04.09.10.14.15.20.25.26.31.36.37.41.42.47.48."></c:set>
			<c:set var="redSet" scope="request" value=".01.02.07.08.12.13.18.19.23.24.29.30.34.35.40.45.46."></c:set> 
			<c:set var="greenSet" scope="request" value=".05.06.11.16.17.21.22.27.28.32.33.38.39.43.44.49."></c:set> 
			<c:choose>
			
				<c:when test="${empty page.result}">
						<div class="pure-u-1">
							<div class="ui-item-son">没有记录！</div>
						</div>
				</c:when>
				
					
				
			<c:otherwise>
            <!-- 循环 -->
            <c:forEach var="item" items="${page.result}"  varStatus="itemindex">

            
           
            <div class="pure-u-2-24"><div class="ui-item-son">${itemindex.index+1}</div></div>
            <div class="pure-u-2-24"><div class="ui-item-son">${item.formatQs}</div></div>
            <div class="pure-u-4-24"><div class="ui-item-son">${item.gtKjsj}</div></div>
            
            <div class="pure-u-7-24">
              <div class="pure-g">
                <div class="pure-u-4-24">
                  <div class="ui-item-son" style="margin: 0px;padding:0px;">
                    
                    	<c:choose>
								<c:when test="${fn:contains(blueSet, item.p1)}">
									<span class="ball ball-blue">${item.p1}</span>
								</c:when>
								<c:when test="${fn:contains(redSet, item.p1)}">
									<span class="ball ball-red">${item.p1}</span>
								</c:when>
								<c:when test="${fn:contains(greenSet, item.p1)}">
									<span class="ball ball-green">${item.p1}</span>
								</c:when>
								<c:otherwise>
									<div class="ui-item-son red">${item.p1}</div>
								</c:otherwise>
							</c:choose>
                  </div>
                </div>
                <div class="pure-u-3-24">
                  <div class="ui-item-son" style="margin: 0px;padding:0px;">
                    <c:choose>
								<c:when test="${fn:contains(blueSet, item.p2)}">
									<span class="ball ball-blue">${item.p2}</span>
								</c:when>
								<c:when test="${fn:contains(redSet, item.p2)}">
									<span class="ball ball-red">${item.p2}</span>
								</c:when>
								<c:when test="${fn:contains(greenSet, item.p2)}">
									<span class="ball ball-green">${item.p2}</span>
								</c:when>
								<c:otherwise>
									<div class="ui-item-son red">${item.p2}</div>
								</c:otherwise>
							</c:choose>
                  </div>
                </div>
                <div class="pure-u-3-24">
                  <div class="ui-item-son" style="margin: 0px;padding:0px;">
    	             <c:choose>
						<c:when test="${fn:contains(blueSet, item.p3)}">
							<span class="ball ball-blue">${item.p3}</span>
						</c:when>
						<c:when test="${fn:contains(redSet, item.p3)}">
							<span class="ball ball-red">${item.p3}</span>
						</c:when>
						<c:when test="${fn:contains(greenSet, item.p3)}">
							<span class="ball ball-green">${item.p3}</span>
						</c:when>
						<c:otherwise>
							<div class="ui-item-son red">${item.p3}</div>
						</c:otherwise>
					</c:choose>
                  </div>
                </div>
                <div class="pure-u-4-24">
                  <div class="ui-item-son" style="margin: 0px;padding:0px;">
							<c:choose>
								<c:when test="${fn:contains(blueSet, item.p4)}">
									<span class="ball ball-blue">${item.p4}</span>
								</c:when>
								<c:when test="${fn:contains(redSet, item.p4)}">
									<span class="ball ball-red">${item.p4}</span>
								</c:when>
								<c:when test="${fn:contains(greenSet, item.p4)}">
									<span class="ball ball-green">${item.p4}</span>
								</c:when>
								<c:otherwise>
									<div class="ui-item-son red">${item.p4}</div>
								</c:otherwise>
							</c:choose> 
					</div>
                </div>
                <div class="pure-u-3-24">
                  <div class="ui-item-son" style="margin: 0px;padding:0px;">
							<c:choose>
								<c:when test="${fn:contains(blueSet, item.p5)}">
									<span class="ball ball-blue">${item.p5}</span>
								</c:when>
								<c:when test="${fn:contains(redSet, item.p5)}">
									<span class="ball ball-red">${item.p5}</span>
								</c:when>
								<c:when test="${fn:contains(greenSet, item.p5)}">
									<span class="ball ball-green">${item.p5}</span>
								</c:when>
								<c:otherwise>
									<div class="ui-item-son red">${item.p5}</div>
								</c:otherwise>
							</c:choose>                  </div>
                </div>
                <div class="pure-u-3-24">
                  <div class="ui-item-son" style="margin: 0px;padding:0px;">
							<c:choose>
								<c:when test="${fn:contains(blueSet, item.p6)}">
									<span class="ball ball-blue">${item.p6}</span>
								</c:when>
								<c:when test="${fn:contains(redSet, item.p6)}">
									<span class="ball ball-red">${item.p6}</span>
								</c:when>
								<c:when test="${fn:contains(greenSet, item.p6)}">
									<span class="ball ball-green">${item.p6}</span>
								</c:when>
								<c:otherwise>
									<div class="ui-item-son gree">${item.p6}</div>
								</c:otherwise>
							</c:choose>                  </div>
                </div>
                <div class="pure-u-4-24">
                  <div class="ui-item-son" style="margin: 0px;padding:0px;">
							<c:choose>
								<c:when test="${fn:contains(blueSet, item.tm)}">
									<span class="ball ball-blue">${item.tm}</span>
								</c:when>
								<c:when test="${fn:contains(redSet, item.tm)}">
									<span class="ball ball-red">${item.tm}</span>
								</c:when>
								<c:when test="${fn:contains(greenSet, item.tm)}">
									<span class="ball ball-green">${item.tm}</span>
								</c:when>
								<c:otherwise>
									<div class="ui-item-son gree">${item.tm}</div>
								</c:otherwise>
							</c:choose>  
						 </div>
                </div>
                
                
              </div>
            </div>
            <div class="pure-u-3-24">
              <div class="pure-g">
                <div class="pure-u-1-3">
                  
                     <c:choose>
						<c:when test="${item.tmDs=='双'}">
							<div class="ui-item-son blue">双</div>
						</c:when>
						<c:when test="${item.tmDs=='单'}">
							<div class="ui-item-son blue">单</div>
						</c:when>
						<c:otherwise>
							<div class="ui-item-son gree">${item.tmDs}</div>
						</c:otherwise>
				    </c:choose>
                </div>
                <div class="pure-u-1-3">
                  <c:choose>
						<c:when test="${item.tmDx=='小'}">
							<div class="ui-item-son blue">小</div>
						</c:when>
						<c:when test="${item.tmDx=='大'}">
							<div class="ui-item-son red">大</div>
						</c:when>
						<c:otherwise>
							<div class="ui-item-son gree">${item.tmDx}</div>
						</c:otherwise>
				    </c:choose>
                </div>
                <div class="pure-u-1-3">
                  <c:choose>
								<c:when test="${item.tmSb=='蓝波'}">
                  					<div class="ui-item-son blue">蓝波</div>
								</c:when>
								<c:when test="${item.tmSb=='红波'}">
                  					<div class="ui-item-son red">红波</div>
								</c:when>
								<c:when test="${item.tmSb=='绿波'}">
                  					<div class="ui-item-son green">绿波</div>
								</c:when>
								<c:otherwise>
									<div class="ui-item-son gree">${item.tmSb}</div>
								</c:otherwise>
							</c:choose>
                </div>
              </div>
            </div>
            <div class="pure-u-3-24">
              <div class="pure-g">
                <div class="pure-u-1-3">
                  <div class="ui-item-son">${item.zfS}</div>
                </div>
                <div class="pure-u-1-3">
                  <c:choose>
								<c:when test="${item.zfDs=='双'}">
                  					<div class="ui-item-son blue">双</div>
								</c:when>
								<c:when test="${item.zfDs=='单'}">
                  					<div class="ui-item-son red">单</div>
								</c:when>
								<c:otherwise>
									<div class="ui-item-son red">${item.zfDs}</div>
								</c:otherwise>
							</c:choose>
                </div>
                <div class="pure-u-1-3">
                  	<c:choose>
								<c:when test="${item.zfDx=='小'}">
                  					<div class="ui-item-son blue">小</div>
								</c:when>
								<c:when test="${item.zfDx=='大'}">
                  					<div class="ui-item-son red">大</div>
								</c:when>
								<c:otherwise>
									<div class="ui-item-son red">${item.zfDx}</div>
								</c:otherwise>
							</c:choose>
                </div>
              </div>
            </div>
            <div class="pure-u-1-24">
              <div class="ui-item-son">${item.sx}</div>
            </div>
            <div class="pure-u-2-24">
              <div class="pure-g">
                <div class="pure-u-1-2">
                  <c:choose>
								<c:when test="${item.hsDx=='小'}">
                  					<div class="ui-item-son blue">小</div>
								</c:when>
								<c:when test="${item.hsDx=='大'}">
                  					<div class="ui-item-son red">大</div>
								</c:when>
								<c:otherwise>
									<div class="ui-item-son red">${item.hsDx}</div>
								</c:otherwise>
							</c:choose>
                </div>
                <div class="pure-u-1-2">
                  <c:choose>
					<c:when test="${item.hsDx=='双'}">
                  		<div class="ui-item-son blue">双</div>
					</c:when>
					<c:when test="${item.hsDx=='单'}">
                 		 <div class="ui-item-son red">单</div>
					</c:when>
					<c:otherwise>
							<div class="ui-item-son red">${item.hsDx}</div>
					</c:otherwise>
				</c:choose>
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
          <div class="pure-g">
          </div>
        </div>
      </div>
    </div>
  </div>