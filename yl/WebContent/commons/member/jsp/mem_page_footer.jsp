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
<tr>
	<td colSpan="${page.colspanNum }">
	<div class="listFooter" align="center">
		<div class="footerPagination">
		总计<font color="red">${page.totalCount}</font>笔记录&nbsp;&nbsp;共${page.totalPages}页&nbsp;&nbsp;当前第<font color="#0000ff">${page.pageNo }</font>页 &nbsp;&nbsp;
		<a href="javascript:jump_page(1);">首页</a>
		<a href="javascript:jump_page(${page.pageNo-1});">上一页</a>
		<a href="javascript:jump_page(${page.pageNo+1});">下一页</a>
		<a href="javascript:jump_page(${page.totalPages});">尾页</a>
		</div>
	</div>
	</td>
	
	<td height="26" width="60">
		<div align="center">
			<select onchange="jump_page(this.value)" name="page">
				<c:forEach begin="1" end="${page.totalPages}" var="v">
					<option <c:if test="${v==page.pageNo}">selected="selected"</c:if> value="${v}">第${v}页</option>
				</c:forEach>
			</select>
		</div>
	</td>
</tr>