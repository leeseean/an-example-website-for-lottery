<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div data-role="page" id="pageNum_0" data-close-btn="right">
  <div data-role="header">
  	 <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal">
        <a href="#quickMenu" data-role="button" data-icon="bars" data-iconpos="notext"></a>
        <!-- <a href="${ctx }/m/wap/member" data-role="button" data-icon="back" data-iconpos="notext"></a> -->
      </div>
    <h1>彩票投注历史</h1>
  </div>
  <!-- /header -->
 
  <div data-role="content">
   	此时间内没有任何注单数据!
      
  </div>
  <!-- /content -->
	<%@include file="../inc/mobile_foot.jsp" %>
 
 <div data-role="panel" data-display="overlay" id="quickMenu${st.count}" class="ybm-panel">
   <%@ include file="../inc/left_memu.jsp" %>
 </div>
 
 </div>