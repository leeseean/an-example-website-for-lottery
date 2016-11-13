<%@ page language="java"   pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<div class="ui-mod ui-tabs" >
    <div class="ui-mod-head ui-tabs-head nav-mode-play">
      <ul class="cf">
        <li class="ui-item ui-item-first ui-active">
          <a href="javascript:void(0);" data-url="#resultBjPK101" class="ui-link">号码</a>
        </li>
        <li class="ui-item">
          <a href="javascript:void(0);" data-url="#resultBjPK102" class="ui-link">大小</a>
        </li>
        <li class="ui-item">
          <a href="javascript:void(0);" data-url="#resultBjPK103" class="ui-link">单双</a>
        </li>
      </ul>
    </div>
    <div class="ui-mod-body ui-tabs-body">
      <!-- 北京PK10 - 号码 -->
      <div class="ui-mod-item ui-tabs-item" id="resultBjPK101">
        <div class="ui-table">
          <div class="ui-table-loop">
            <div class="ui-head-son">
              <div class="pure-g">
                <div class="pure-u-2-24">
                  <div class="ui-item-son ui-item-colspan">期数</div>
                </div>
                <div class="pure-u-4-24">
                  <div class="ui-item-son ui-item-colspan">开奖时间</div>
                </div>
                <div class="pure-u-15-24">
                  <div class="ui-item-son">彩球号码</div>
                </div>
                <div class="pure-u-3-24">
                  <div class="ui-item-son">冠亚军和</div>
                </div>
              </div>
            </div>
            <div class="ui-head-son ui-head-blank">
              <div class="pure-g">
                <div class="pure-u-2-24">
                  <div class="ui-item-son ui-item-blank"></div>
                </div>
                <div class="pure-u-4-24">
                  <div class="ui-item-son ui-item-blank"></div>
                </div>
                <div class="pure-u-15-24">
                  <div class="pure-g">
                    <div class="pure-u-4-24">
                      <div class="ui-item-son">冠军</div>
                    </div>
                    <div class="pure-u-3-24">
                      <div class="ui-item-son">亚军</div>
                    </div>
                    <div class="pure-u-3-24">
                      <div class="ui-item-son">季军</div>
                    </div>
                    <div class="pure-u-2-24">
                      <div class="ui-item-son">第四名</div>
                    </div>
                    <div class="pure-u-2-24">
                      <div class="ui-item-son">第五名</div>
                    </div>
                    <div class="pure-u-2-24">
                      <div class="ui-item-son">第六名</div>
                    </div>
                    <div class="pure-u-2-24">
                      <div class="ui-item-son">第七名</div>
                    </div>
                    <div class="pure-u-2-24">
                      <div class="ui-item-son">第八名</div>
                    </div>
                    <div class="pure-u-2-24">
                      <div class="ui-item-son">第九名</div>
                    </div>
                    <div class="pure-u-2-24">
                      <div class="ui-item-son">第十名</div>
                    </div>
                  </div>
                </div>
                <div class="pure-u-3-24">
                  <div class="pure-g">
                    <div class="pure-u-1-3">
                      <div class="ui-item-son">总和</div>
                    </div>
                    <div class="pure-u-1-3">
                      <div class="ui-item-son">大小</div>
                    </div>
                     <div class="pure-u-1-3">
                      <div class="ui-item-son">单双</div>
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
              <c:forEach var="item" items="${page.result}" varStatus="itemindex">
                <!-- 循环 -->
                <div class="pure-u-2-24">
                  <div class="ui-item-son">${item.formatQs}</div>
                </div>
                <div class="pure-u-4-24">
                  <div class="ui-item-son">${item.gtKjsj}</div>
                </div>
                <div class="pure-u-15-24 b">
                  <div class="pure-g">
                    <div class="pure-u-4-24">
                      <div class="ui-item-son">${item.gj}</div>
                    </div>
                    <div class="pure-u-3-24">
                      <div class="ui-item-son">${item.yj}</div>
                    </div>
                    <div class="pure-u-3-24">
                      <div class="ui-item-son">${item.jj}</div>
                    </div>
                    <div class="pure-u-2-24">
                      <div class="ui-item-son">${item.d4m}</div>
                    </div>
                    <div class="pure-u-2-24">
                      <div class="ui-item-son">${item.d5m}</div>
                    </div>
                    <div class="pure-u-2-24">
                      <div class="ui-item-son">${item.d6m}</div>
                    </div>
                    <div class="pure-u-2-24">
                      <div class="ui-item-son">${item.d7m}</div>
                    </div>
                    <div class="pure-u-2-24">
                      <div class="ui-item-son">${item.d8m}</div>
                    </div>
                    <div class="pure-u-2-24">
                      <div class="ui-item-son">${item.d9m}</div>
                    </div>
                    <div class="pure-u-2-24">
                      <div class="ui-item-son">${item.d10m}</div>
                    </div>
                  </div>
                </div>
                <div class="pure-u-3-24">
                  <div class="pure-g">
                    <div class="pure-u-1-3">
                      <div class="ui-item-son">${item.gyjhZh}</div>
                    </div>
                    <div class="pure-u-1-3">
                      
                      <c:choose>
									<c:when test="${item.gyjhDx=='小'}">
										<div class="ui-item-son blue">${item.gyjhDx}</div>
									</c:when>
									<c:when test="${item.gyjhDx=='大'}">
										<div class="ui-item-son red">${item.gyjhDx}</div>
									</c:when>
									<c:otherwise>
									<div class="ui-item-son red">${item.gyjhDx}</div>
								</c:otherwise>
								</c:choose>
                    </div>
                    <div class="pure-u-1-3">
                      
                      <c:choose>
						<c:when test="${item.gyjhDs=='双'}">
							<div class="ui-item-son blue">${item.gyjhDs}</div>
						</c:when>
						<c:when test="${item.gyjhDs=='单'}">
							<div class="ui-item-son red">${item.gyjhDs}</div>
						</c:when>
						<c:otherwise>
							<div class="ui-item-son red">${item.gyjhDs}</div>
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
      <!-- / 北京PK10 - 号码 -->
      <!-- 北京PK10 - 大小 -->
      <div class="ui-mod-item ui-tabs-item" id="resultBjPK102" style=" display: none;">
        <div class="ui-table">
          <div class="ui-table-loop">
            <div class="ui-head-son">
              <div class="pure-g">
                <div class="pure-u-2-24">
                  <div class="ui-item-son ui-item-colspan">期数</div>
                </div>
                <div class="pure-u-4-24">
                  <div class="ui-item-son ui-item-colspan">开奖时间</div>
                </div>
                <div class="pure-u-18-24">
                  <div class="ui-item-son">彩球号码</div>
                </div>
              </div>
            </div>
            <div class="ui-head-son ui-head-blank">
              <div class="pure-g">
                <div class="pure-u-2-24">
                  <div class="ui-item-son ui-item-blank"></div>
                </div>
                <div class="pure-u-4-24">
                  <div class="ui-item-son ui-item-blank"></div>
                </div>
                <div class="pure-u-18-24">
                  <div class="pure-g">
                    <div class="pure-u-4-24">
                      <div class="ui-item-son">冠军</div>
                    </div>
                    <div class="pure-u-3-24">
                      <div class="ui-item-son">亚军</div>
                    </div>
                    <div class="pure-u-3-24">
                      <div class="ui-item-son">季军</div>
                    </div>
                    <div class="pure-u-2-24">
                      <div class="ui-item-son">第四名</div>
                    </div>
                    <div class="pure-u-2-24">
                      <div class="ui-item-son">第五名</div>
                    </div>
                    <div class="pure-u-2-24">
                      <div class="ui-item-son">第六名</div>
                    </div>
                    <div class="pure-u-2-24">
                      <div class="ui-item-son">第七名</div>
                    </div>
                    <div class="pure-u-2-24">
                      <div class="ui-item-son">第八名</div>
                    </div>
                    <div class="pure-u-2-24">
                      <div class="ui-item-son">第九名</div>
                    </div>
                    <div class="pure-u-2-24">
                      <div class="ui-item-son">第十名</div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="ui-body-son ui-body-ball">
              <div class="pure-g">
                <!-- 循环 -->
              <c:forEach var="item" items="${page.result}" varStatus="itemindex">
                
                <div class="pure-u-2-24">
                  <div class="ui-item-son">${item.formatQs}</div>
                </div>
                <div class="pure-u-4-24">
                  <div class="ui-item-son">${item.kjsj}</div>
                </div>
                <div class="pure-u-18-24 b">
                  <div class="pure-g">
                    <div class="pure-u-4-24">
                      
                      <c:choose>
							<c:when test="${item.gjDx=='小'}">
								<div class="ui-item-son blue">${item.gjDx}</div>
							</c:when>
							<c:when test="${item.gjDx=='大'}">
								<div class="ui-item-son red">${item.gjDx}</div>
							</c:when>
							<c:otherwise>
							<div class="ui-item-son red">${item.gjDx}</div>
						</c:otherwise>
					</c:choose>
                    </div>
                    <div class="pure-u-3-24">
                      <c:choose>
							<c:when test="${item.yjDx=='小'}">
								<div class="ui-item-son blue">${item.yjDx}</div>
							</c:when>
							<c:when test="${item.yjDx=='大'}">
								<div class="ui-item-son red">${item.yjDx}</div>
							</c:when>
							<c:otherwise>
							<div class="ui-item-son red">${item.yjDx}</div>
						</c:otherwise>
					</c:choose>
                    </div>
                    <div class="pure-u-3-24">
                      <c:choose>
							<c:when test="${item.jjDx=='小'}">
								<div class="ui-item-son blue">${item.jjDx}</div>
							</c:when>
							<c:when test="${item.jjDx=='大'}">
								<div class="ui-item-son red">${item.jjDx}</div>
							</c:when>
							<c:otherwise>
							<div class="ui-item-son red">${item.jjDx}</div>
						</c:otherwise>
					</c:choose>
                    </div>
                    <div class="pure-u-2-24">
                      <c:choose>
							<c:when test="${item.d4mDx=='小'}">
								<div class="ui-item-son blue">${item.d4mDx}</div>
							</c:when>
							<c:when test="${item.d4mDx=='大'}">
								<div class="ui-item-son red">${item.d4mDx}</div>
							</c:when>
							<c:otherwise>
							<div class="ui-item-son red">${item.d4mDx}</div>
						</c:otherwise>
					</c:choose>
                    </div>
                    <div class="pure-u-2-24">
                      <c:choose>
							<c:when test="${item.d5mDx=='小'}">
								<div class="ui-item-son blue">${item.d5mDx}</div>
							</c:when>
							<c:when test="${item.d5mDx=='大'}">
								<div class="ui-item-son red">${item.d5mDx}</div>
							</c:when>
							<c:otherwise>
							<div class="ui-item-son red">${item.d5mDx}</div>
						</c:otherwise>
					</c:choose>
                    </div>
                    <div class="pure-u-2-24">
                      <c:choose>
							<c:when test="${item.d6mDx=='小'}">
								<div class="ui-item-son blue">${item.d6mDx}</div>
							</c:when>
							<c:when test="${item.d6mDx=='大'}">
								<div class="ui-item-son red">${item.d6mDx}</div>
							</c:when>
							<c:otherwise>
							<div class="ui-item-son red">${item.d6mDx}</div>
						</c:otherwise>
					</c:choose>
                    </div>
                    <div class="pure-u-2-24">
                      <c:choose>
							<c:when test="${item.d7mDx=='小'}">
								<div class="ui-item-son blue">${item.d7mDx}</div>
							</c:when>
							<c:when test="${item.d7mDx=='大'}">
								<div class="ui-item-son red">${item.d7mDx}</div>
							</c:when>
							<c:otherwise>
							<div class="ui-item-son red">${item.d7mDx}</div>
						</c:otherwise>
					</c:choose>
                    </div>
                    <div class="pure-u-2-24">
                      <c:choose>
							<c:when test="${item.d8mDx=='小'}">
								<div class="ui-item-son blue">${item.d8mDx}</div>
							</c:when>
							<c:when test="${item.d8mDx=='大'}">
								<div class="ui-item-son red">${item.d8mDx}</div>
							</c:when>
							<c:otherwise>
							<div class="ui-item-son red">${item.d8mDx}</div>
						</c:otherwise>
					</c:choose>
                    </div>
                    <div class="pure-u-2-24">
                      <c:choose>
							<c:when test="${item.d9mDx=='小'}">
								<div class="ui-item-son blue">${item.d9mDx}</div>
							</c:when>
							<c:when test="${item.d9mDx=='大'}">
								<div class="ui-item-son red">${item.d9mDx}</div>
							</c:when>
							<c:otherwise>
							<div class="ui-item-son red">${item.d9mDx}</div>
						</c:otherwise>
					</c:choose>
                    </div>
                    <div class="pure-u-2-24">
                      <c:choose>
							<c:when test="${item.d10mDx=='小'}">
								<div class="ui-item-son blue">${item.d10mDx}</div>
							</c:when>
							<c:when test="${item.d10mDx=='大'}">
								<div class="ui-item-son red">${item.d10mDx}</div>
							</c:when>
							<c:otherwise>
							<div class="ui-item-son red">${item.d10mDx}</div>
						</c:otherwise>
					</c:choose>
                    </div>
                  </div>
                </div>
                <!-- / 循环 -->
                </c:forEach>
              </div>
            </div>
            <div class="ui-head-son">
              <div class="pure-g">
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- / 北京PK10 - 大小 -->
      <!-- 北京PK10 - 单双 -->
      <div class="ui-mod-item ui-tabs-item" id="resultBjPK103" style=" display: none;">
        <div class="ui-table">
          <div class="ui-table-loop">
            <div class="ui-head-son">
              <div class="pure-g">
                <div class="pure-u-2-24">
                  <div class="ui-item-son ui-item-colspan">期数</div>
                </div>
                <div class="pure-u-4-24">
                  <div class="ui-item-son ui-item-colspan">开奖时间</div>
                </div>
                <div class="pure-u-18-24">
                  <div class="ui-item-son">彩球号码</div>
                </div>
              </div>
            </div>
            <div class="ui-head-son ui-head-blank">
              <div class="pure-g">
                <div class="pure-u-2-24">
                  <div class="ui-item-son ui-item-blank"></div>
                </div>
                <div class="pure-u-4-24">
                  <div class="ui-item-son ui-item-blank"></div>
                </div>
                <div class="pure-u-18-24">
                  <div class="pure-g">
                    <div class="pure-u-4-24">
                      <div class="ui-item-son">冠军</div>
                    </div>
                    <div class="pure-u-3-24">
                      <div class="ui-item-son">亚军</div>
                    </div>
                    <div class="pure-u-3-24">
                      <div class="ui-item-son">季军</div>
                    </div>
                    <div class="pure-u-2-24">
                      <div class="ui-item-son">第四名</div>
                    </div>
                    <div class="pure-u-2-24">
                      <div class="ui-item-son">第五名</div>
                    </div>
                    <div class="pure-u-2-24">
                      <div class="ui-item-son">第六名</div>
                    </div>
                    <div class="pure-u-2-24">
                      <div class="ui-item-son">第七名</div>
                    </div>
                    <div class="pure-u-2-24">
                      <div class="ui-item-son">第八名</div>
                    </div>
                    <div class="pure-u-2-24">
                      <div class="ui-item-son">第九名</div>
                    </div>
                    <div class="pure-u-2-24">
                      <div class="ui-item-son">第十名</div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="ui-body-son ui-body-ball">
              <div class="pure-g">
              	<c:forEach var="item" items="${page.result}" varStatus="itemindex">
                <!-- 循环 -->
                <div class="pure-u-2-24">
                  <div class="ui-item-son">${item.formatQs}</div>
                </div>
                <div class="pure-u-4-24">
                  <div class="ui-item-son">${item.kjsj}</div>
                </div>
                <div class="pure-u-18-24 b">
                  <div class="pure-g">
                    <div class="pure-u-4-24">
                      
                      <c:choose>
						<c:when test="${item.gjDs=='单'}">
							<div class="ui-item-son blue">${item.gjDs}</div>
						</c:when>
						<c:when test="${item.gjDs=='双'}">
							<div class="ui-item-son red">${item.gjDs}</div>
						</c:when>
						<c:otherwise>
						<div class="ui-item-son red">${item.gjDs}</div>
						</c:otherwise>
					</c:choose>
                    </div>
                    <div class="pure-u-3-24">
                      <c:choose>
						<c:when test="${item.yjDs=='单'}">
							<div class="ui-item-son blue">${item.yjDs}</div>
						</c:when>
						<c:when test="${item.yjDs=='双'}">
							<div class="ui-item-son red">${item.yjDs}</div>
						</c:when>
						<c:otherwise>
						<div class="ui-item-son red">${item.yjDs}</div>
						</c:otherwise>
					</c:choose>
                    </div>
                    <div class="pure-u-3-24">
                      <c:choose>
						<c:when test="${item.jjDs=='单'}">
							<div class="ui-item-son blue">${item.jjDs}</div>
						</c:when>
						<c:when test="${item.jjDs=='双'}">
							<div class="ui-item-son red">${item.jjDs}</div>
						</c:when>
						<c:otherwise>
						<div class="ui-item-son red">${item.jjDs}</div>
						</c:otherwise>
					</c:choose>
                    </div>
                    <div class="pure-u-2-24">
                      <c:choose>
						<c:when test="${item.d4mDs=='单'}">
							<div class="ui-item-son blue">${item.d4mDs}</div>
						</c:when>
						<c:when test="${item.d4mDs=='双'}">
							<div class="ui-item-son red">${item.d4mDs}</div>
						</c:when>
						<c:otherwise>
						<div class="ui-item-son red">${item.d4mDs}</div>
						</c:otherwise>
					</c:choose>
                    </div>
                    <div class="pure-u-2-24">
                       <c:choose>
						<c:when test="${item.d5mDs=='单'}">
							<div class="ui-item-son blue">${item.d5mDs}</div>
						</c:when>
						<c:when test="${item.d5mDs=='双'}">
							<div class="ui-item-son red">${item.d5mDs}</div>
						</c:when>
						<c:otherwise>
						<div class="ui-item-son red">${item.d5mDs}</div>
						</c:otherwise>
					</c:choose>
                    </div>
                    <div class="pure-u-2-24">
                       <c:choose>
						<c:when test="${item.d6mDs=='单'}">
							<div class="ui-item-son blue">${item.d6mDs}</div>
						</c:when>
						<c:when test="${item.d6mDs=='双'}">
							<div class="ui-item-son red">${item.d6mDs}</div>
						</c:when>
						<c:otherwise>
						<div class="ui-item-son red">${item.d6mDs}</div>
						</c:otherwise>
					</c:choose>
                    </div>
                    <div class="pure-u-2-24">
                       <c:choose>
						<c:when test="${item.d7mDs=='单'}">
							<div class="ui-item-son blue">${item.d7mDs}</div>
						</c:when>
						<c:when test="${item.d7mDs=='双'}">
							<div class="ui-item-son red">${item.d7mDs}</div>
						</c:when>
						<c:otherwise>
						<div class="ui-item-son red">${item.d7mDs}</div>
						</c:otherwise>
					</c:choose>
                    </div>
                    <div class="pure-u-2-24">
                       <c:choose>
						<c:when test="${item.d8mDs=='单'}">
							<div class="ui-item-son blue">${item.d8mDs}</div>
						</c:when>
						<c:when test="${item.d8mDs=='双'}">
							<div class="ui-item-son red">${item.d8mDs}</div>
						</c:when>
						<c:otherwise>
						<div class="ui-item-son red">${item.d8mDs}</div>
						</c:otherwise>
					</c:choose>
                    </div>
                    <div class="pure-u-2-24">
                       <c:choose>
						<c:when test="${item.d9mDs=='单'}">
							<div class="ui-item-son blue">${item.d9mDs}</div>
						</c:when>
						<c:when test="${item.d9mDs=='双'}">
							<div class="ui-item-son red">${item.d9mDs}</div>
						</c:when>
						<c:otherwise>
						<div class="ui-item-son red">${item.d9mDs}</div>
						</c:otherwise>
					</c:choose>
                    </div>
                    <div class="pure-u-2-24">
                       <c:choose>
						<c:when test="${item.d10mDs=='单'}">
							<div class="ui-item-son blue">${item.d10mDs}</div>
						</c:when>
						<c:when test="${item.d10mDs=='双'}">
							<div class="ui-item-son red">${item.d10mDs}</div>
						</c:when>
						<c:otherwise>
						<div class="ui-item-son red">${item.d10mDs}</div>
						</c:otherwise>
					</c:choose>
                    </div>
                  </div>
                </div>
                <!-- / 循环 -->
                </c:forEach>
              </div>
            </div>
            <div class="ui-head-son">
              <div class="pure-g">
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- / 北京PK10 - 单双 -->
    </div>
  </div>
  <script>
  $('.ui-tabs .ui-tabs-head .ui-link').on('click', function(event) {
	  var currentAttrValue = $(this).attr('data-url');
	  $('.ui-tabs-item' + currentAttrValue).fadeIn(400).siblings().hide();
	  $(this).parent('li').addClass('ui-active').siblings().removeClass('ui-active');
	  event.preventDefault();
	});
  </script>