<%@ page language="java" pageEncoding="utf-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
      <!DOCTYPE html>

      <head>
        <%@include file="/commons/member/jsp/member_common.jsp"%>
      </head>

      <body>
        <div class="wrapper">
          <div class="panel-content font-hei">
            <div class="panel-message">
              <div class="page-title clear">
                <div class="left">
                  <h2 class="s24 blue">站内消息</h2>
                  <p class="mt5 gray">方便会员得知我们的最新动态而设的服务功能</p>
                </div>
                <div class="right mt10">
                  <button class="button button-primary button-raised button-pill button-tiny btn-contact">
                    <i></i>在线客服
                  </button>
                </div>
              </div>
              <div class="page-body switch-mod">
                <div class="switch-menu s16">
                  <ul class="center clear">
                    <li class="item" onclick="goParent('${ctx}/message/listForUser?id=${item.id}')">个人信息</li>
                    <li class="item on">系统信息</li>
                  </ul>
                </div>
                <div class="switch-group">
                  <div class="switch-item panel-message">
                    <div class="page-body">
                      <div class="sheet-mod">
                        <div class="title">
                          <form id="page_form" target="memberFrame" action="${ctx}/message/listForSys?id=${item.id}" method="Post">
                            <input type="hidden" id="pageNo" name="pageNo" value="${page.pageNo }" />
                            <input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }" />
                            <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages }" />
                            <input type="hidden" name="code" value="${records.code}" />
                          </form>
                        </div>
                        <!-- /title -->
                        <table class="center">
                          <tr class="sheet-title s14 b">
                            <td class="al pl15">内容</td>
                            <td width="118">时间</td>
                          </tr>
                          <c:if test="${empty page.result}">
                            <tr height="125px;">
                              <td colspan="2">
                                <span class="gray-dark">查询结果 - 暂无纪录</span>
                              </td>
                            </tr>
                          </c:if>
                          <c:forEach var="item" items="${page.result}" varStatus="s">
                            <tr class="sheet-body s12 gray-dark">
                              <td class="al pl15" style=" line-height: 1.3">${item.msg_content}</td>
                              <td align="center">
                                <fmt:formatDate value="${item.create_time}" pattern="yyyy-MM-dd HH:mm" />
                              </td>
                            </tr>
                          </c:forEach>
                          <c:if test="${!empty page.result}">
                            <tr class="sheet-title">
                              <td colspan="7" class="pr20 ar"><span class="right">总计${page.totalCount}笔记录&nbsp;&nbsp;共${page.totalPages}页&nbsp;&nbsp;当前第${page.pageNo
                              }页 &nbsp;&nbsp;</span>
                              </td>
                            </tr>
                          </c:if>
                        </table>
                      </div>
                    </div>
                    <!-- form_id: 查询表单ID;action: 表单查义URL   -->
                    <jsp:include page="/commons/member/jsp/member_page_footer.jsp">
                      <jsp:param name="action" value="${ctx}/message/listForSys" />
                      <jsp:param name="form_id" value="page_form" />
                    </jsp:include>
                  </div>
                </div>
                <!-- /body -->
              </div>
              <!-- /record -->
            </div>
          </div>
          <script src="${resourceRoot}/member/js/plugins.js"></script>
        </div>
        </div>
