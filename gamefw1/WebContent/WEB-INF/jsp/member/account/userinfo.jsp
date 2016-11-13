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
            <div class="panel-security">
              <div class="page-title clear">
                <div class="left">
                  <h2 class="s24 blue">个人资料</h2>
                  <h3 class="mt5 gray">查看并确认您的个人资料信息</h3>
                </div>
                <div class="right mt10">
                  <button class="button button-primary button-raised button-pill button-tiny btn-contact">
                    <i></i>在线客服
                  </button>
                </div>
              </div>
              <!-- /title -->
              <div class="page-body">
                <div class="switch-group">
                  <div class="switch-item">
                    <div class="page-body">
                      <ul class="panel-player-info-list">
                        <li>
                          <label>用户名:</label>${ webUser.userName}
                        </li>
                        <li>
                          <label>姓名:</label>${webUser.userRealName }
                        </li>
                        <li>
                          <label>会员等级:</label>${userType.typeLevel }
                        </li>
                        <li>
                          <label>账户余额:</label>${webUser.userMoney }
                        </li>
                        <li>
                          <label>电子邮箱:</label>${ webUser.userMail}
                        </li>
                        <li>
                          <label>QQ号码:</label>${ webUser.userQq}
                        </li>
                        <li>
                          <label>手机号码:</label>${webUser.userMobile}
                        </li>
                        <li>
                          <!-- 用户名 -->
                        </li>
                      </ul>
                      <p class="center">友情提示：<span style="color:red">若需修改个人资料，请联系在线客服</span></p>
                    </div>
                  </div>
                  <!-- /item -->
                </div>
                <!-- /group -->
              </div>
              <!-- /body -->
            </div>
          </div>
        </div>
        <script src="${resourceRoot}/member/js/plugins.js"></script>
      </body>
