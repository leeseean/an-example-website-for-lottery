<div class="ybb-md-tt">
  <ul class="tab-title clear">
<#if (wpList?size>1) >
<#list wpList as wp>
    <li>
<#if (wp.pgCtype ==0 ) >
      <a href="javascript:void(0);">${wp.pgTitle}</a>
<#else>
      <a href="javascript:void(0);">${wp.pgTitle}</a>
</#if>
    </li>
</#list>
<#else>
    <li>${wm.pgTitle}</li>
</#if>
  </ul>
</div>
<div class="ybb-md-ct tab-content">
<#if (wpList?size>1)>
<#list wpList as wp>
  <div>${wp.pgContent}</div>
</#list>
<#else>
  <p>${wm.pgContent}</p>
</#if>
</div>
<script>
// Tab切換
$(function(){
  $('.tab-title > li').first().addClass('active').siblings().removeClass('active');
  $('.tab-content > div').first().fadeIn().siblings().hide();
  $('.tab-title li').click(function(){
    var index = $('.tab-title li').index(this);
    $(this).addClass('active').siblings().removeClass('active');
    $('.tab-content > div').eq(index).fadeIn().siblings().hide();
  });
});
</script>