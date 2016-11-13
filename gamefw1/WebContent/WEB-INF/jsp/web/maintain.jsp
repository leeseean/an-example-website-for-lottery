<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>系統维护中 System-Updating</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="${resourceRoot}/web/modules/maintain/css/normalize.css">
  <style>
    *{ margin: 0; padding: 0;}
    body{ font-family: Arial; background: url(${resourceRoot}/web/modules/maintain/img/system-update-bg.png) center 0 no-repeat;}
    ul{ list-style: none;}
    img{ border: 0;}
    a{ text-decoration: none; color: #555;}
    a:hover{ color: #ccc; transition: color .5s;}
    .system-update-icon{ position: absolute; top: 0; left: 0; z-index: 1; display: block; overflow: hidden; width: 553px; height: 475px; background: url(${resourceRoot}/web/modules/maintain/img/system-update-icon.png) no-repeat;}
    .system-update-content{ position: absolute; top: 50%; left: 50%; z-index: 9; margin: -150px 0 0 -480px; width: 960px; height: 300px; background: url(${resourceRoot}/web/modules/maintain/img/system-update-text.png) 450px 30px no-repeat;}
    .system-update-casino{ float: left; margin: 90px 0 0 30px; display: table; overflow: hidden; width: 388px; height: 118px; text-align: center; border: 1px solid #222;}
    .system-update-casino span{ display: table-cell; vertical-align: middle;}
    .system-update-logo{ margin: auto;}
    .system-update-info{ float: left; padding: 140px 0 0 30px; overflow: hidden; width: 510px; font-size: 12px; line-height: 130%; color: #666;}
    .system-update-time{ font-size: 14px;}
    .system-update-time span{ color: #fff;}
    .system-update-info p{ padding-top: 5px;}
    .system-update-contact ul{ height: 34px;}
    .system-update-contact li{ position: relative; float: left; margin: 10px 15px 0 0; height: 34px;}
    .system-update-contact i{ position: absolute; top: 5px; left: 5px; width: 34px; height: 24px; background-image: url(${resourceRoot}/web/modules/maintain/img/system-update-contact.png); background-repeat: no-repeat;}
    .system-update-contact a{ padding: 0 15px 0 35px; display: block; height: 34px; line-height: 34px;}
    .system-update-contact a:hover{ border-color: #555}
    .system-update-contact .tel i{ background-position: 0 -24px;}
    .system-update-contact .qq i{ background-position: 0 -48px;}
  </style>
</head>
<body>
  <div class="wrapper" id="system-update">
    <span class="system-update-icon"></span>
    <div class="system-update-content">
      <div class="system-update-casino">
        <span><img src="${resourceRoot}/web/modules/maintain/img/logo-header.png" title="美高梅娱乐城" alt="美高梅娱乐城" width="307" height="105" class="system-update-logo"></span>
      </div>
      <div class="system-update-info">
        <h2 class="system-update-time">
          系统维护时间：<span>2015/7/12 01:50 至 2015/7/12 05:30</span>
        </h2>
        <p>由于系统固定维护或临时维护等原因暂停提供服务，我们将尽快完成维护并恢复正常服务。</p>
        <p>Since system upgrades or temporary upgrade fixed services were suspended and other reasons, we will complete the upgrade and restore normal service as quickly as possible.</p>
        <div class="system-update-contact">
          <ul>
            <li class="ol"><i></i><a href="${ctxMap['msg007'] }" target="_blank">在线客服</a></li>
            <li class="tel"><i></i><a href="javascript:void(0);">+63 9779999999</a></li>
            <li class="qq"><i></i><a href="javascript:void(0);">${ctxMap['siteQq'] }</a></li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</body>
</html>