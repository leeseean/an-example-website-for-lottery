<%@ page language="java"   pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<div class="ui-mod-body" >
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
            <div class="pure-u-4-24">
              <div class="ui-item-son ui-item-colspan">开奖时间</div>
            </div>
            <div class="pure-u-4-24">
              <div class="ui-item-son">开奖球号</div>
            </div>
            <div class="pure-u-2-24">
              <div class="ui-item-son ui-item-colspan">总和</div>
            </div>
            <div class="pure-u-2-24">
              <div class="ui-item-son">万</div>
            </div>
            <div class="pure-u-2-24">
              <div class="ui-item-son">千</div>
            </div>
            <div class="pure-u-2-24">
              <div class="ui-item-son">百</div>
            </div>
            <div class="pure-u-2-24">
              <div class="ui-item-son">拾</div>
            </div>
            <div class="pure-u-2-24">
              <div class="ui-item-son">个</div>
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
            <div class="pure-u-4-24">
              <div class="ui-item-son ui-item-blank"></div>
            </div>
            <div class="pure-u-4-24">
              <div class="pure-g">
                <div class="pure-u-1-5">
                  <div class="ui-item-son">万</div>
                </div>
                <div class="pure-u-1-5">
                  <div class="ui-item-son">千</div>
                </div>
                <div class="pure-u-1-5">
                  <div class="ui-item-son">百</div>
                </div>
                <div class="pure-u-1-5">
                  <div class="ui-item-son">拾</div>
                </div>
                <div class="pure-u-1-5">
                  <div class="ui-item-son">个</div>
                </div>
              </div>
            </div>
            <div class="pure-u-2-24">
              <div class="ui-item-son ui-item-blank"></div>
            </div>
            <div class="pure-u-2-24">
              <div class="pure-g">
                <div class="pure-u-1-3">
                  <div class="ui-item-son">单双</div>
                </div>
                <div class="pure-u-1-3">
                  <div class="ui-item-son">大小</div>
                </div>
                <div class="pure-u-1-3">
                  <div class="ui-item-son">质合</div>
                </div>
              </div>
            </div>
            <div class="pure-u-2-24">
              <div class="pure-g">
                <div class="pure-u-1-3">
                  <div class="ui-item-son">单双</div>
                </div>
                <div class="pure-u-1-3">
                  <div class="ui-item-son">大小</div>
                </div>
                <div class="pure-u-1-3">
                  <div class="ui-item-son">质合</div>
                </div>
              </div>
            </div>
            <div class="pure-u-2-24">
              <div class="pure-g">
                <div class="pure-u-1-3">
                  <div class="ui-item-son">单双</div>
                </div>
                <div class="pure-u-1-3">
                  <div class="ui-item-son">大小</div>
                </div>
                <div class="pure-u-1-3">
                  <div class="ui-item-son">质合</div>
                </div>
              </div>
            </div>
            <div class="pure-u-2-24">
              <div class="pure-g">
                <div class="pure-u-1-3">
                  <div class="ui-item-son">单双</div>
                </div>
                <div class="pure-u-1-3">
                  <div class="ui-item-son">大小</div>
                </div>
                <div class="pure-u-1-3">
                  <div class="ui-item-son">质合</div>
                </div>
              </div>
            </div>
            <div class="pure-u-2-24">
              <div class="pure-g">
                <div class="pure-u-1-3">
                  <div class="ui-item-son">单双</div>
                </div>
                <div class="pure-u-1-3">
                  <div class="ui-item-son">大小</div>
                </div>
                <div class="pure-u-1-3">
                  <div class="ui-item-son">质合</div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="ui-body-son ui-body-ball">
          <div class="pure-g">
            <c:choose>
					<c:when test="${empty page.result}">
							<div class="pure-u-1">
								<div class="ui-item-son">没有记录！</div>
							</div>
					</c:when>
			<c:otherwise>
            <!-- 循环 -->
		<c:forEach var="item" items="${page.result}" varStatus="itemindex">
            <div class="pure-u-1-24">
              <div class="ui-item-son">${itemindex.index+1}</div>
            </div>
            <div class="pure-u-3-24">
              <div class="ui-item-son">${item.qs}</div>
            </div>
            <div class="pure-u-4-24">
              <div class="ui-item-son">${item.gtKjsj}</div>
            </div>
            <div class="pure-u-4-24 b">
              <div class="pure-g">
                <div class="pure-u-1-5">
                  <div class="ui-item-son">${item.kjhmW}</div>
                </div>
                <div class="pure-u-1-5">
                  <div class="ui-item-son">${item.kjhmQ}</div>
                </div>
                <div class="pure-u-1-5">
                  <div class="ui-item-son">${item.kjhmB}</div>
                </div>
                <div class="pure-u-1-5">
                  <div class="ui-item-son">${item.kjhmS}</div>
                </div>
                <div class="pure-u-1-5">
                  <div class="ui-item-son">${item.kjhmG}</div>
                </div>
              </div>
            </div>
            <div class="pure-u-2-24">
              <div class="pure-g">
                <div class="pure-u-1-2">
                  
                  <c:choose>
						<c:when test="${item.zhDx=='小'}">
						<div class="ui-item-son blue">${item.zhDx}</div>
					</c:when>
					<c:when test="${item.zhDx=='大'}">
						<div class="ui-item-son red">${item.zhDx}</div>
					</c:when>
					<c:otherwise>
						<div class="ui-item-son red">${item.zhDx}</div>
					</c:otherwise>
				</c:choose>
                </div>
                <div class="pure-u-1-2">
                  <c:choose>
						<c:when test="${item.zhDs=='双'}">
						<div class="ui-item-son blue">${item.zhDs}</div>
					</c:when>
					<c:when test="${item.zhDs=='单'}">
						<div class="ui-item-son red">${item.zhDs}</div>
					</c:when>
					<c:otherwise>
						<div class="ui-item-son red">${item.zhDs}</div>
					</c:otherwise>
				</c:choose>
                </div>
              </div>
            </div>
            <div class="pure-u-2-24">
              <div class="pure-g">
                <div class="pure-u-1-3">
                  <c:choose>
						<c:when test="${item.wDs=='双'}">
						<div class="ui-item-son blue">${item.wDs}</div>
					</c:when>
					<c:when test="${item.wDs=='单'}">
						<div class="ui-item-son red">${item.wDs}</div>
					</c:when>
					<c:otherwise>
						<div class="ui-item-son red">${item.wDs}</div>
					</c:otherwise>
				</c:choose>
                </div>
                <div class="pure-u-1-3">
                  <c:choose>
						<c:when test="${item.wDx=='小'}">
						<div class="ui-item-son blue">${item.wDx}</div>
					</c:when>
					<c:when test="${item.wDx=='大'}">
						<div class="ui-item-son red">${item.wDx}</div>
					</c:when>
					<c:otherwise>
						<div class="ui-item-son red">${item.wDx}</div>
					</c:otherwise>
				</c:choose>
                </div>
                <div class="pure-u-1-3">
                  <c:choose>
								<c:when test="${item.wZh=='合'}">
									<div class="ui-item-son blue">${item.wZh}</div>
								</c:when>
								<c:when test="${item.wZh=='质'}">
									<div class="ui-item-son red">${item.wZh}</div>
								</c:when>
								<c:otherwise>
									<div class="ui-item-son red">${item.wZh}</div>
								</c:otherwise>
							</c:choose>
                </div>
              </div>
            </div>
            <div class="pure-u-2-24">
              <div class="pure-g">
                <div class="pure-u-1-3">
                  <c:choose>
						<c:when test="${item.qDs=='双'}">
						<div class="ui-item-son blue">${item.qDs}</div>
					</c:when>
					<c:when test="${item.qDs=='单'}">
						<div class="ui-item-son red">${item.qDs}</div>
					</c:when>
					<c:otherwise>
						<div class="ui-item-son red">${item.qDs}</div>
					</c:otherwise>
				</c:choose>
                </div>
                <div class="pure-u-1-3">
                  <c:choose>
						<c:when test="${item.qDx=='小'}">
						<div class="ui-item-son blue">${item.qDx}</div>
					</c:when>
					<c:when test="${item.qDx=='大'}">
						<div class="ui-item-son red">${item.qDx}</div>
					</c:when>
					<c:otherwise>
						<div class="ui-item-son red">${item.qDx}</div>
					</c:otherwise>
				</c:choose>
                </div>
                <div class="pure-u-1-3">
                  <c:choose>
					<c:when test="${item.qZh=='合'}">
						<div class="ui-item-son blue">${item.qZh}</div>
					</c:when>
					<c:when test="${item.qZh=='质'}">
						<div class="ui-item-son red">${item.qZh}</div>
					</c:when>
					<c:otherwise>
						<div class="ui-item-son red">${item.qZh}</div>
					</c:otherwise>
				</c:choose>
                </div>
              </div>
            </div>
            <div class="pure-u-2-24">
              <div class="pure-g">
                <div class="pure-u-1-3">
                  <c:choose>
						<c:when test="${item.bDs=='双'}">
						<div class="ui-item-son blue">${item.bDs}</div>
					</c:when>
					<c:when test="${item.bDs=='单'}">
						<div class="ui-item-son red">${item.bDs}</div>
					</c:when>
					<c:otherwise>
						<div class="ui-item-son red">${item.bDs}</div>
					</c:otherwise>
				</c:choose>
                </div>
                <div class="pure-u-1-3">
                  <c:choose>
						<c:when test="${item.bDx=='小'}">
						<div class="ui-item-son blue">${item.bDx}</div>
					</c:when>
					<c:when test="${item.bDx=='大'}">
						<div class="ui-item-son red">${item.bDx}</div>
					</c:when>
					<c:otherwise>
						<div class="ui-item-son red">${item.bDx}</div>
					</c:otherwise>
				</c:choose>
                </div>
                <div class="pure-u-1-3">
                   <c:choose>
					<c:when test="${item.bZh=='合'}">
						<div class="ui-item-son blue">${item.bZh}</div>
					</c:when>
					<c:when test="${item.bZh=='质'}">
						<div class="ui-item-son red">${item.bZh}</div>
					</c:when>
					<c:otherwise>
						<div class="ui-item-son red">${item.bZh}</div>
					</c:otherwise>
				</c:choose>
                </div>
              </div>
            </div>
            <div class="pure-u-2-24">
              <div class="pure-g">
                <div class="pure-u-1-3">
                  <c:choose>
						<c:when test="${item.sDs=='双'}">
						<div class="ui-item-son blue">${item.sDs}</div>
					</c:when>
					<c:when test="${item.sDs=='单'}">
						<div class="ui-item-son red">${item.sDs}</div>
					</c:when>
					<c:otherwise>
						<div class="ui-item-son red">${item.sDs}</div>
					</c:otherwise>
				</c:choose>
                </div>
                <div class="pure-u-1-3">
                  <c:choose>
						<c:when test="${item.sDx=='小'}">
						<div class="ui-item-son blue">${item.sDx}</div>
					</c:when>
					<c:when test="${item.sDx=='大'}">
						<div class="ui-item-son red">${item.sDx}</div>
					</c:when>
					<c:otherwise>
						<div class="ui-item-son red">${item.sDx}</div>
					</c:otherwise>
				</c:choose>
                </div>
                <div class="pure-u-1-3">
                  <c:choose>
					<c:when test="${item.sZh=='合'}">
						<div class="ui-item-son blue">${item.sZh}</div>
					</c:when>
					<c:when test="${item.sZh=='质'}">
						<div class="ui-item-son red">${item.sZh}</div>
					</c:when>
					<c:otherwise>
						<div class="ui-item-son red">${item.sZh}</div>
					</c:otherwise>
				</c:choose>
                </div>
              </div>
            </div>
            <div class="pure-u-2-24">
              <div class="pure-g">
                <div class="pure-u-1-3">
                  <c:choose>
						<c:when test="${item.gDs=='双'}">
						<div class="ui-item-son blue">${item.gDs}</div>
					</c:when>
					<c:when test="${item.gDs=='单'}">
						<div class="ui-item-son red">${item.gDs}</div>
					</c:when>
					<c:otherwise>
						<div class="ui-item-son red">${item.gDs}</div>
					</c:otherwise>
				</c:choose>
                </div>
                <div class="pure-u-1-3">
                  <c:choose>
						<c:when test="${item.gDx=='小'}">
						<div class="ui-item-son blue">${item.gDx}</div>
					</c:when>
					<c:when test="${item.gDx=='大'}">
						<div class="ui-item-son red">${item.gDx}</div>
					</c:when>
					<c:otherwise>
						<div class="ui-item-son red">${item.gDx}</div>
					</c:otherwise>
				</c:choose>
                </div>
                <div class="pure-u-1-3">
                  <c:choose>
					<c:when test="${item.gZh=='合'}">
						<div class="ui-item-son blue">${item.gZh}</div>
					</c:when>
					<c:when test="${item.gZh=='质'}">
						<div class="ui-item-son red">${item.gZh}</div>
					</c:when>
					<c:otherwise>
						<div class="ui-item-son red">${item.gZh}</div>
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