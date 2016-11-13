<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="ybb-slot-main">
      <div class="game-group">
        <div class="ui-grid-a">
        <c:choose>
		<c:when test="${empty webUser}">
          <div class="ui-block-a item">
          	<a href="javascript:alert('请先登录');void(0);" data-ajax="false">
            <div class="r1" style=" background-image: url(${resourceRoot}/m/img/temp/slot/ag/starburst.png);"></div>
            <p class="r2 ac">六合彩</p>
            </a>
          </div>
          <!-- /item -->
          <div class="ui-block-b item">
          	<a href="javascript:alert('请先登录');void(0);" data-ajax="false">
            <div class="r1" style=" background-image: url(${resourceRoot}/m/img/temp/slot/ag/Mobile_MiniBaccarat.jpg);"></div>
            <p class="r2 ac">福彩3D</p>
            </a>
          </div>
          <!-- /item -->
          <div class="ui-block-a item">
          	<a href="javascript:alert('请先登录');void(0);" data-ajax="false">
            <div class="r1" style=" background-image: url(${resourceRoot}/m/img/temp/slot/ag/Mob_Fireworks.jpg);"></div>
            <p class="r2 ac">排列三</p>
            </a>
          </div>
          <!-- /item -->
          <div class="ui-block-b item">
          	<a href="javascript:alert('请先登录');void(0);" data-ajax="false">
            <div class="r1" style=" background-image: url(${resourceRoot}/m/img/temp/slot/ag/Mobile_LadyOfFortune.jpg);"></div>
            <p class="r2 ac">重庆时时彩</p>
            </a>
          </div>
          <!-- /item -->
          <div class="ui-block-a item">
          	<a href="javascript:alert('请先登录');void(0);" data-ajax="false">
            <div class="r1" style=" background-image: url(${resourceRoot}/m/img/temp/slot/ag/Mob_Fireworks.jpg);"></div>
            <p class="r2 ac">江西时时彩</p>
            </a>
          </div>
          <!-- /item -->
          <div class="ui-block-b item">
          	<a href="javascript:alert('请先登录');void(0);" data-ajax="false">
            <div class="r1" style=" background-image: url(${resourceRoot}/m/img/temp/slot/ag/Mobile_LadyOfFortune.jpg);"></div>
            <p class="r2 ac">天津时时彩</p>
            </a>
          </div>
          <!-- /item -->
          <div class="ui-block-a item">
          	<a href="javascript:alert('请先登录');void(0);" data-ajax="false">
            <div class="r1" style=" background-image: url(${resourceRoot}/m/img/temp/slot/ag/Mob_Fireworks.jpg);"></div>
            <p class="r2 ac">新疆时时彩</p>
            </a>
          </div>
          <!-- /item -->
          <div class="ui-block-b item">
          	<a href="javascript:alert('请先登录');void(0);" data-ajax="false">
            <div class="r1" style=" background-image: url(${resourceRoot}/m/img/temp/slot/ag/Mobile_LadyOfFortune.jpg);"></div>
            <p class="r2 ac">广东快乐十分</p>
            </a>
          </div>
          <!-- /item -->
          <div class="ui-block-a item">
          	<a href="javascript:alert('请先登录');void(0);" data-ajax="false">
            <div class="r1" style=" background-image: url(${resourceRoot}/m/img/temp/slot/ag/Mob_Fireworks.jpg);"></div>
            <p class="r2 ac">天津快乐十分</p>
            </a>
          </div>
          <!-- /item -->
          <div class="ui-block-b item">
          	<a href="javascript:alert('请先登录');void(0);" data-ajax="false">
            <div class="r1" style=" background-image: url(${resourceRoot}/m/img/temp/slot/ag/Mobile_LadyOfFortune.jpg);"></div>
            <p class="r2 ac">北京PK10</p>
            </a>
          </div>
          <!-- /item -->
          </c:when>
          <c:otherwise>
          	<div class="ui-block-a item">
          	<a href="${ctx }/m/mobileCpMain/menu" data-ajax="false">
            <div class="r1" style=" background-image: url(${resourceRoot}/m/img/temp/slot/ag/starburst.png);"></div>
            <p class="r2 ac">六合彩</p>
            </a>
          </div>
          <!-- /item -->
          <div class="ui-block-b item">
          	<a href="${ctx }/m/mobileCpMain/menu" data-ajax="false">
            <div class="r1" style=" background-image: url(${resourceRoot}/m/img/temp/slot/ag/Mobile_MiniBaccarat.jpg);"></div>
            <p class="r2 ac">福彩3D</p>
            </a>
          </div>
          <!-- /item -->
          <div class="ui-block-a item">
          	<a href="${ctx }/m/mobileCpMain/menu" data-ajax="false">
            <div class="r1" style=" background-image: url(${resourceRoot}/m/img/temp/slot/ag/Mob_Fireworks.jpg);"></div>
            <p class="r2 ac">排列三</p>
            </a>
          </div>
          <!-- /item -->
          <div class="ui-block-b item">
          	<a href="${ctx }/m/mobileCpMain/menu" data-ajax="false">
            <div class="r1" style=" background-image: url(${resourceRoot}/m/img/temp/slot/ag/Mobile_LadyOfFortune.jpg);"></div>
            <p class="r2 ac">重庆时时彩</p>
            </a>
          </div>
          <!-- /item -->
          <div class="ui-block-a item">
          	<a href="${ctx }/m/mobileCpMain/menu" data-ajax="false">
            <div class="r1" style=" background-image: url(${resourceRoot}/m/img/temp/slot/ag/Mob_Fireworks.jpg);"></div>
            <p class="r2 ac">江西时时彩</p>
            </a>
          </div>
          <!-- /item -->
          <div class="ui-block-b item">
          	<a href="${ctx }/m/mobileCpMain/menu" data-ajax="false">
            <div class="r1" style=" background-image: url(${resourceRoot}/m/img/temp/slot/ag/Mobile_LadyOfFortune.jpg);"></div>
            <p class="r2 ac">天津时时彩</p>
            </a>
          </div>
          <!-- /item -->
          <div class="ui-block-a item">
          	<a href="${ctx }/m/mobileCpMain/menu" data-ajax="false">
            <div class="r1" style=" background-image: url(${resourceRoot}/m/img/temp/slot/ag/Mob_Fireworks.jpg);"></div>
            <p class="r2 ac">新疆时时彩</p>
            </a>
          </div>
          <!-- /item -->
          <div class="ui-block-b item">
          	<a href="${ctx }/m/mobileCpMain/menu" data-ajax="false">
            <div class="r1" style=" background-image: url(${resourceRoot}/m/img/temp/slot/ag/Mobile_LadyOfFortune.jpg);"></div>
            <p class="r2 ac">广东快乐十分</p>
            </a>
          </div>
          <!-- /item -->
          <div class="ui-block-a item">
          	<a href="${ctx }/m/mobileCpMain/menu" data-ajax="false">
            <div class="r1" style=" background-image: url(${resourceRoot}/m/img/temp/slot/ag/Mob_Fireworks.jpg);"></div>
            <p class="r2 ac">天津快乐十分</p>
            </a>
          </div>
          <!-- /item -->
          <div class="ui-block-b item">
          	<a href="${ctx }/m/mobileCpMain/menu" data-ajax="false">
            <div class="r1" style=" background-image: url(${resourceRoot}/m/img/temp/slot/ag/Mobile_LadyOfFortune.jpg);"></div>
            <p class="r2 ac">北京PK10</p>
            </a>
          </div>
          </c:otherwise>
          </c:choose>
        </div>
      </div>
      <!-- /group -->
    </div>