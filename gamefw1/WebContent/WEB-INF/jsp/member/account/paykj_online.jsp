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
      <div class="page-title clear">
        <div class="left">
          <h2 class="s24 blue">充值到账户</h2>
          <p class="mt5 gray">尊敬的客户您好，请选择您的存款方式及银行，并填写存款金额</p>
        </div>
        <div class="right mt10">
          <button class="button button-primary button-raised button-pill button-tiny btn-contact"><i></i>在线客服</button>
        </div>
      </div>
      <div class="page-body switch-mod">
        <div class="switch-group">
          <!-- /item -->
          <div class="switch-item">
            <div class="pt20">
              <div class="panel-progress">
                <ul class="progress-step-group clear">
                  <li class="step step-one step-active">
                    <div class="step-inner">
                      <span class="step-icon">1</span>
                      <h2>查看公司账户并填表</h2>
                    </div>
                  </li>
                  <li class="step step-two">
                    <div class="step-inner">
                      <span class="step-icon">2</span>
                      <h2>正在操作，稍候查询</h2>
                    </div>
                  </li>
                  <li class="step step-three">
                    <div class="step-inner">
                      <span class="step-icon">3</span>
                      <h2>结束</h2>
                    </div>
                  </li>
                </ul>
                <div class="progress-line"></div>
              </div>
              <!-- /progress -->
            </div>
            <iframe id="payifram" name="payifram" frameborder="0" src="${ctx}/pay/paykjSelect?paytype=${paytype}&payid=${payid}" class="mt20 s14" style="width: 100%;height: 500px;"></iframe>
          </div>
          <!-- /item -->
        </div>
        <!-- /group -->
      </div>
      <!-- /body -->
    </div>
  </div>
  <script src="${resourceRoot}/member/js/plugins.js"></script>
</body>
