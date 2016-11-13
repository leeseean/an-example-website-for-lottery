<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html lang="en">
<head><meta charset="utf-8">
<title>皇冠体育</title>
</head>

<frameset rows="118,*" cols="*" frameborder="NO" border="0" framespacing="0">
    <frame src="${ctx}/sport/headNav" name="header" id="header" scrolling="NO" noresize="" />
    <frameset cols="240,1*" frameborder="NO" border="0" framespacing="0">
       <frame src="${ctx}/sport/orderSide.do" name="orderFrame" id="orderFrame" noresize="" scrolling="auto" />
       <frame src="${ctx}/sport/goMatchCenter?timeType=today&matchType=ft&rtype=r" name="showFrame" id="showFrame" scrolling="auto" />
    </frameset>
    <noframes><body></body></noframes>
</frameset>
</html>