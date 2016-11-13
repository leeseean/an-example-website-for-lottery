 
 <div class="assist-body" >
            <div class="assist-title">
              <ul class="ybb-tab-tt clear">
              	<#if (wpList?size>1) >
	              <#list wpList as wp>
	                <li>
	                <#if (wp.pgCtype ==0 ) >
	                <a href="javascript:void(0);" >${wp.pgTitle}</a>
	                <#else>
	                <a href="javascript:void(0);" >${wp.pgTitle}</a>
	                </#if>
	                
	                </li>
	              </#list>
	              <#else>
	                <li>${wm.pgTitle}</li>
	            </#if>
              </ul>
            </div>
            <div class="assist-content ybb-tab-ct">
            <#if (wpList?size>1)>
	              <#list wpList as wp>
              		<div >${wp.pgContent}</div>
             	 </#list>
	              <#else>
              		<p>${wm.pgContent}</p>
	            </#if>
              
            </div>
  </div>
  
 <script>
$(function(){
  $('.ybb-tab-tt > li').first().addClass('active').siblings().removeClass('active');
  $('.ybb-tab-ct > div').first().fadeIn().siblings().hide();
  $('.ybb-tab-tt li').click(function(){
    var index = $('.ybb-tab-tt li').index(this);
    $(this).addClass('active').siblings().removeClass('active');
    $('.ybb-tab-ct > div').eq(index).fadeIn().siblings().hide();
  });
});
</script>