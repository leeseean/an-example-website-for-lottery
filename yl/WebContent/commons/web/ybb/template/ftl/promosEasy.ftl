<%@ page language="java" pageEncoding="UTF-8"%>
<#list pdAllList as pd>
<div class="promos-section" id="promos-${pd.id}">
  <div class="promos-section-head">
    <div class="text clear">
      <h2>${pd.pmsTitle}</h2><h3>${pd.pmsSubTitle}</h3>
    </div>
    <div class="thumb">
      <img src="${pd.pmsSmallPic}" alt="">
    </div>
  </div>
  <div class="promos-section-content">
<#if pd.pmsShowType=="pic">
    <img src="${pd.pmsBigPic}" alt="">
<#else>
    ${pd.pmsContent}
</#if>
  </div>
</div>
</#list>