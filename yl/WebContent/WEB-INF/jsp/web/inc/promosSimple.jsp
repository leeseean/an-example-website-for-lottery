<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<style>
  .ui-accordion .ui-accordion-icons {
    background: none;
    border: 0;
    margin-bottom: -5px;
  }
  .ui-accordion .ui-accordion-content {
    padding: 0em 2.2em;
    border: 0;
    background: none;
    overflow: hidden;
  }
  .ui-accordion .ui-accordion-header {
    padding: 0.5em .5em 0em 2.2em;
  }

</style>
<style>
  .content-top-banner {
    margin-top: 56px;
    height: 441px;
  }
</style>
<div class="row">
	<div class="wrapper">
		<div class="content-top-banner"></div>
		<%@ include file="msg.jsp" %>
		<div class="module-promos promos-easy">
			<div class="promos-group">
				<%@include file="/commons/web/ybb/html/promos/promosEasy.html" %>
			</div>
		</div>
	</div>
</div>
