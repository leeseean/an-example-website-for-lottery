<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="banner slide-common">
	<c:forEach var="item" items="${carousels}" varStatus="status">
		<div>
			<div class="r1">
       	 		<a class="block full" href="${item.crsUrl}"><img src="${ctx}${item.crsPic}"></a>
     	 </div>
		</div>
	</c:forEach>
</div>