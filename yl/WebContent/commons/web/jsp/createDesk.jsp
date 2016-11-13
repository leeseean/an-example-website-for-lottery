<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.io.*"%>
<%@page import="javax.swing.filechooser.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html style="overflow-x:hidden">
<head>
<title>快捷方式生成</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body>
	<%
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		String templateContent = "[InternetShortcut]" + "\n" + "URL= " + "http://www.615.cc"; //生成快捷方式的网址
		//String realfilename = request.getServerName() + ".url"; //快捷方式命名
		String realfilename = "永利娱乐城www.615.cc.url"; //快捷方式命名//
		FileSystemView fsv = FileSystemView.getFileSystemView();
		String upurl = fsv.getHomeDirectory().toString(); //桌面路径
		String filename = upurl + "/" + realfilename; //保存快捷方式地址
		File myfile = new File(filename);
		if (!myfile.exists())
		{
			FileOutputStream fileoutputstream = new FileOutputStream(filename);//建立文件输出流
			byte tag_bytes[] = templateContent.getBytes();
			fileoutputstream.write(tag_bytes);
			fileoutputstream.close();
		}
		try
		{
			File file = new File(upurl, realfilename);
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[111000];
			realfilename = java.net.URLEncoder.encode(realfilename, "UTF-8");
			response.reset();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-download");//不同类型的文件对应不同的MIME类型
			response.setHeader("Content-Disposition","attachment; filename=" + realfilename);
			OutputStream os = response.getOutputStream();
			while (bis.read(buffer) > 0)
			{
				os.write(buffer);
			}
			bis.close();
			os.close();
			out.clear();
			out = pageContext.pushBody();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	%>
</body>
</html>
