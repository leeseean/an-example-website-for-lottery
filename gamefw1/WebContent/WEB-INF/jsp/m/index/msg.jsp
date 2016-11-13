<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="news cf">
	<div class="c1 b">最新消息</div>
	<div class="c2 slide-news">
		<c:forEach var="item" items="${ggList}" varStatus="status">
			<div><a data-ajax="false" href="${ctx }/m/main?code=new_list">${item.ggName }</a></div>
		</c:forEach>
	</div>
</div>