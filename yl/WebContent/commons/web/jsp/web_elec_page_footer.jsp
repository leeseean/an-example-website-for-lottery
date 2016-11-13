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
 
	
		form_submit($("#" + "${param.form_id}").get(0), {
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
	
	//列表查询提交
	function form_submit(submit_form, param){
		if(param.action){ submit_form.action = param.action; }
		if(param.method){ submit_form.method = param.method; }
		if(param.pageNo){ $("input[name=pageNo]", $(submit_form)).val(param.pageNo); }
		if(param.pageSize){ $("input[name=pageSize]", $(submit_form)).val(param.pageSize); }


		submit_form.submit();
	}
	
</script>
<style>
.pageCss {
	text-align: center;
	border: 1px;
	margin-left: 1px;
	height: 26px;
	width: 40px;
	background-image: url('${resourceRoot}/web/images/nav/page_bg.jpg');
	float: left;
	text-align: center;
	line-height: 26px;
	height: 26px;
}
</style>
<c:if test="${!empty page.result}">
	<div class="menu" style="width: 100%; margin: 0 auto; margin-top: 10px; position: relative; z-index:999; margin-left:222px;">
		<span style="margin: 0 auto; list-style: none;">
			<span>
				<a href="javascript:void(0);" onclick="jump_page(1);" style="color:#FDE46E">
					<div class="pageCss" style="width: 70px">首页</div>
				</a>
			</span>
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
			<span>
				<c:forEach begin="${begin}" end="${end}" var="v">
					<a href="javascript:void(0);" onclick="jump_page('${v}');" style="color: ${v eq page.pageNo?'#FF0000':'#FDE46E'};">
						<div class="pageCss" style="width: 70px">${v}</div>
					</a>
				</c:forEach>
			</span>
			<span>
				<a onclick="jump_page(${page.totalPages});" style="color: #FDE46E">
					<div class="pageCss" style="width: 70px">尾页</div>
				</a>
			</span>
		</span>
	</div>
</c:if>
 
