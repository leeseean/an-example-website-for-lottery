<%@ page language="java" import="java.util.*,com.mh.commons.constants.*" pageEncoding="utf-8" %>
<%
	Map<String,String> ctxMap = WebSiteManagerConstants.ctxMap;
	request.setAttribute("ctxMap", ctxMap);

	String p = request.getParameter("p");
	if(!"".equals(p) && !"null".equals(p) && p!=null){
		request.getSession().setAttribute("agentNo", p);
	}
%>
<header class="ybb-mobile-header">
  <div class="r1">
    <a href="#quickMenu" class="btn-menu"><i class="flaticon-rectangular9"></i></a>
    <span class="spr-common ybb-mobile-logo"></span>
    <div class="c-action r">
      <c:choose>
        <c:when test="${empty webUser}">
          <a href="${ctx}/m/main/index" data-ajax="false">登录</a>
          <a href="${ctx}/m/register/goRegister" data-ajax="false">注册</a>
        </c:when>
        <c:otherwise> 
          <a href="${ctx}/m/wap/member#/home" class="nav-link-item-3" data-ajax="false">财务中心</a>
          <a href="${ctx}/m/wap/member#/home" data-ajax="false">
            <i class="flaticon-maleuser"></i>
          </a>
          <c:if test="${empty code }">
            <c:if test="${!empty webUser }">
              <a href="${ctx}/m/wap/member#/user/order" data-rel="dialog">
                <i class="flaticon-note20"></i>
              </a>
            </c:if>
          </c:if>
          <a href="javascript:void(0);" onclick="mobile_loginOut();" data-ajax="false">
            <i class="flaticon-exit23"></i>
          </a>
        </c:otherwise>
      </c:choose>
    </div>
    <!-- /Action -->
  </div>
  <!-- /Row1 -->
  <div class="r2 ybb-mobile-nav">
    <nav class="nav-group">
      <ul class="cf">
        <li class="i-home"><a href="${ctx}/m/main" data-ajax="false"><i class="flaticon-house58"></i></a></li>
        <li><a href="${ctx}/m/main?code=live" data-ajax="false">真人视讯</a></li>
        <li><a href="${ctx}/m/main?code=slot_pt" data-ajax="false">电子</a></li>
        <li><a href="${ctx}/m/main?code=sport" data-ajax="false">体育</a></li>
        <li><a href="${ctx}/m/wap" data-ajax="false">彩票</a></li>
        <li><a href="${ctxMap['msg007'] }" target="_blank" data-ajax="false">客服</a></li>
        <!-- <li class="i-more">更多</li> -->
      </ul>
      <ul class="cf">
        <li class="i-home"></li>
        <li><a href="${ctx}/m/main?code=bbin" data-ajax="false">BBIN</a></li>
        <li><a href="${ctx}/m/help?code=m_promos" data-ajax="false">优惠</a></li>
        <li><a href="${ctx}/m/help?code=m_agent" data-ajax="false">合作</a></li>
        <li><a href="${ctx}/m/help?code=m_domain" data-ajax="false">网址</a></li>
        <li class="i-more">更多</li>
      </ul>
    </nav>
    <!-- /Nav -->
    <nav class="nav-more">
      <ul>
        <li><a href="${ctx}/m/help?code=m_duty" data-ajax="false">博彩责任</a></li>
        <li><a href="${ctx}/m/help?code=m_fair" data-ajax="false">公平与公正</a></li>
        <li><a href="${ctx}/m/help?code=m_rule" data-ajax="false">规则与条款</a></li>
      </ul>
    </nav>
    <!-- <nav class="nav-more">
      <ul>
        <li><a href="${ctx}/m/help?code=m_promos" data-ajax="false">优惠活动<i class="flaticon-supermarket16"></i></a></li>
        <li><a href="${ctx}/m/help?code=m_agent" data-ajax="false">代理合作<i class="flaticon-diamond36"></i></a></li>
        <li><a href="${ctx}/m/help?code=m_domain" data-ajax="false">备用网址<i class="flaticon-worldgrid"></i></a></li>
        <li><a href="${ctxMap['msg007'] }" target="_blank" data-ajax="false">在线客服<i class="flaticon-help9"></i></a></li>
      </ul>
    </nav> -->
    <script>
    // $(document).on('vclick', '.i-more', function(event) {
    //   $('.nav-more').slideToggle('fast');
    // });
    </script>
  </div>
  <!-- /Row2 -->
</header>
<!-- /Header -->
