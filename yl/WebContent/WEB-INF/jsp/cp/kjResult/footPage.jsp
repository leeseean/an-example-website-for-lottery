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

		//$.form_submit($("#" + "${param.form_id }").get(0), {
		//	"action" : "${param.action }",
		//	"pageNo" : pageNo,
		//	"pageSize" : $("#page_size").val()
		//});
		$("#pageNo").val(pageNo);
		$("#page_form").submit();
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

<div class="ui-pager">
  <div class="ui-mod-head cf">
    <div class="l b">
      <input type="button" onclick="jump_page(1);" value="刷新" class="button button-small button-raised button-primary">
    </div>
    <div class="l pager ac">
      总计<strong class="red">${page.totalCount}</strong>笔记录　共<strong class="red">${page.totalPages}</strong>页  当前第<strong class="red">${page.pageNo }</strong>页
      <a href="javascript:jump_page(1);">首页</a>
      <a href="javascript:jump_page(${page.pageNo-1});">&lt;</a>
      <a href="javascript:jump_page(${page.pageNo+1});">&gt;</a>
      <a href="javascript:jump_page(${page.totalPages});">尾页</a>
    </div>
    <div class="r">
      <select onchange="jump_page(this.value)" name="page">
		<c:forEach begin="1" end="${page.totalPages}" var="v">
			<option <c:if test="${v==page.pageNo}">selected="selected"</c:if> value="${v}">第${page.pageNo}页</option>
		</c:forEach>
	 </select>
    </div>
  </div>
</div>
