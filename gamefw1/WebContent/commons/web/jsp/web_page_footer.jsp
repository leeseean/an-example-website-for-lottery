<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script language="javascript">
	//需要再分页前执行自己的函数，请自己重写方法beforePage
	function jump_page(pageNo){
		if(typeof(window.beforePage) == "function"){
			beforePage();
		}
		if(typeof(pageNo) == "undefined" || Number(pageNo) == 'NaN'|| pageNo < 1){
			pageNo = 1;
		}else{
			var totalPage = parseInt('${page.totalPages }');
			if(pageNo > totalPage){
				pageNo = totalPage;
			}
		}

		//设置当前请页数
		//$("input[type=hidden][name=pageNo]", $form).val(pageNo);

		//设置每页数
		//var $pageSize = $("input[type=hidden][name=pageSize]", $form).val($();

		$.form_submit($("#" + "${param.form_id }").get(0), {
			"action" : "${param.action }",
			"pageNo" : pageNo,
			"pageSize" : $("#page_size").val()
		});
	}
	
	$(function(){
		$("#jump_page_input").keydown(function(event){
			if(event.keyCode == 13){
				jump_page($('#jump_page_input').val());
			}
		});
		
		$("#page_size").keydown(function(event){
			if(event.keyCode == 13){
				jump_page(1);
			}
		});
		
		$("#page_form input[type='text']").keydown(function(event){
			if(event.keyCode == 13){
				jump_page(1);
			}
		});
	});
</script>
<c:if test="${!empty page.result}">
	<div class="paging mt20">
		<ul class="center clear">
			<li><span onclick="jump_page(${page.pageNo-1});">&lt;</span></li>
			
 			<c:set var="begin" value="1" />
 			<c:set var="end" value="${page.totalPages}" />
			<c:choose>
				<c:when test="${page.totalPages<=5}">
					<c:set var="end" value="${page.totalPages}" />
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${page.pageNo==page.totalPages}">
							<c:set var="begin" value="${page.pageNo-4}" />
							<c:set var="end" value="${page.totalPages}" />
						</c:when>
						<c:when test="${page.pageNo>=5&&page.pageNo<page.totalPages-5}">
							<c:set var="begin" value="${page.pageNo-2}" />
							<c:set var="end" value="${page.pageNo+2}" />
						</c:when>
						<c:when test="${page.pageNo>=5 && page.pageNo ==page.totalPages-5}">
							<c:set var="begin" value="${page.pageNo}" />
							<c:set var="end" value="${page.totalPages-1}" />
						</c:when>
						<c:when test="${page.pageNo>=5&& page.pageNo >page.totalPages-5}">
							<c:set var="begin" value="${page.totalPages-4}" />
							<c:set var="end" value="${page.totalPages}" />
						</c:when>
						<c:otherwise>
							<c:set var="end" value="5" />
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
			<c:forEach begin="${begin}" end="${end}" var="v">
				<li  onclick="jump_page('${v}')" <c:if test="${v==page.pageNo}">class="active"</c:if>><span>${v}</span></li>
			</c:forEach>
			<li><span onclick="jump_page(${page.pageNo+1});">&gt;</span></li>
		</ul>
	</div>
</c:if>
 
