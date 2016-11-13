<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!--banner-img-->
<%-- <div class="banner-img">
	<a href="">
		<img src="${resourceRoot}/web/ybb/assets/img/index/banner-img.png"/>
	</a>
</div> --%>

<!--middle-block -->
<div class="middle-block">
	<a href="" class="shortcut-online"></a>
</div>


<!--轮播-->
 <div class="sliders slider-game-navs">
			<a href="javascript:void(0)" class="btns-pre"></a>
			<a href="javascript:void(0)" class="btns-next"></a>
			
		
		<div class="slider-box"  plugin-infiniteCircleSlider=".slider-game-navs .btns-next, .slider-game-navs .btns-pre ">
			<ul class="clearfix bd">
				<li class="item slider-item-1">
					<a href="javascript:void(0)" onclick="Go('${ctx}/goPageCenter?code=live', 'live')"></a>
				</li>
				<li class="item slider-item-2">
					<a href="javascript:void(0)" onclick="Go('${ctx}/goPageCenter?code=sport', 'sport')"></a>
				</li>
				
		        <li class="item slider-item-3">
					<a href="javascript:void(0)" onclick="Go('${ctx}/electronic?code=mg', 'electronic')"></a>
				</li>
				
				<li class="item slider-item-4">
					<a href="javascript:void(0)" onclick="Go('${ctx}/goPageCenter?code=lottery_index')"></a>
				</li>
				
		  </ul>
		</div>
	</div>



