<%@ page language="java" import="java.util.*,com.mh.commons.constants.*" pageEncoding="utf-8"%>
<%
	Map<String,String> ctxMap = WebSiteManagerConstants.ctxMap;
	request.setAttribute("ctxMap", ctxMap);
	
	String p = request.getParameter("p");
	if(!"".equals(p) && !"null".equals(p) && p!=null){
		request.getSession().setAttribute("agentNo", p);
	}
%>
<!-- 页面底部 begin -->
<div data-role="footer" data-position="fixed">
	<h2>Copyright &copy; ${ctxMap['siteName'] } Reserved</h2>
</div>

<!-- 页面底部 end -->