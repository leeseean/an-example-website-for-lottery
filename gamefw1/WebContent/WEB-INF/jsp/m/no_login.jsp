<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div data-role="page" data-close-btn="none" id="no_login">
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <div data-role="header">
    <h1>请登录</h1>
  </div>
  <!-- /header -->
  <div data-role="content" class="ybm-form ybm-order ybm-order-success">
    <ul data-role="listview">
      <li>
        请登陆后再进行投注!
      </li>
      <li>
        <div data-role="fieldcontain" style=" padding: 0; text-align: center;">
          <a href="#" data-role="button" data-rel="back" data-ajax="false" data-corners="false" data-inline="true" data-icon="back">返回</a>
          <a href="${ctx }/m/wap/member" data-role="button" data-ajax="false" data-corners="false" data-inline="true" data-icon="star">登录</a>
          <!-- /返回下注起始頁面 -->
        </div>
      </li>
    </ul>
  </div>
  <!-- /content -->
</div>