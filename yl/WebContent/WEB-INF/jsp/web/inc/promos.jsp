<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<div class="wrapper font-hei">

<div class="page-container page-discount">
  <div class="page-body">
    <div class="page-head page-head-discount"></div>
    <!-- hot -->
    <div class="widget-carousel discount-hot">
      <div class="carousel-body discount-hot-body">
        <ul class="clear">
          <!-- 轮播图 -->
          <c:forEach var="t" items="${lbPromoList}" varStatus="s">
            <li class="discount-hot-item">
	            <div class="discount-hot-pic">
	              <img src="${t.pmsThemePic}" width="280" height="144" alt="">
	            </div>
	            <h2 class="discount-hot-head">
	              ${s.count }<span>${t.pmsTitle}</span>
	            </h2>
	            <div class="discount-hot-content anchor-jump">
	              <a  onclick="addLiClass(${t.id});" rel="#discount-${t.id}">${t.pmsSubTitle}</a>
	           	</div>
       		</li>
          </c:forEach>
        <!--  file="/commons/template/html/promos/lunbo.html"%> -->
        </ul>
      </div>
      <div class="carousel-head">
        <a href="javascript:void(0)" class="ctrl prev">&lt;</a>
        <a href="javascript:void(0)" class="ctrl next">&gt;</a>
        <div class="carousel-paging">
          <ul class="clear"></ul>
        </div>
      </div>
    </div>
    
    
    <!-- main -->
    <div class="page-content clear">
        <div class="module-sort module-sort-discount">
          <ul class="clear" id="tabs">
            <!-- 读取缓存 -->
            <li class="current"><a id="qwer" href="${resourceRoot}/web/ybb/html/promos/promosAll.html">全部优惠</a></li>
             <c:forEach var="pt" items="${ptList}" varStatus="s">
                 <li <c:if test="${pt.id eq param.pid}">class="current"</c:if>><a href="${resourceRoot}/web/ybb/html/promos/${pt.id }.html">${pt.pmsTypeTitle }</a></li>
              </c:forEach>
          </ul>
        </div>
        <div class="discount-page-group" id="tabs-container">
        请稍等...
        </div>
    </div>
    
  </div>
</div>
</div>
<script>
var promosId="";
function addLiClass(id){
	
	$('#tabs > li').first().addClass('current').siblings().removeClass('current');
	loadTab($("#qwer"),id);
	promosId=id;
}

function loadTab(tabObj,id){
	var containerId = '#tabs-container';
	var tabsId = '#tabs';
	
    if(!tabObj || !tabObj.length){ return; }
    $(containerId).addClass('loading');
    $(containerId).fadeOut('fast');
    
    $(containerId).load(tabObj.attr('href'), function(){
        $(containerId).removeClass('loading');
        $(containerId).fadeIn('fast');
    });
    return false;
}

function tabObjPoint(rel){
		  var pos = $(rel).offset().top;
		  $('html, body').animate({scrollTop:pos}, 500);
		$('.discount-group').accordion({
		  active: false,
		  collapsible: true,
		  animate: 500,
		  heightStyle: 'content',
		  header: '.discount-section-head'
		});
}
</script>
<script type="text/javascript">
ajaxLoadHtml();
</script>
	
