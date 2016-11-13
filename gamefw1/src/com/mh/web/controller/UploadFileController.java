package com.mh.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.mh.commons.constants.WebSiteManagerConstants;
import com.mh.entity.TWebFileInfo;
import com.mh.service.WebFileInfoService;

@Controller
@RequestMapping("/file")
public class UploadFileController extends BaseController {
	@Resource
	private WebFileInfoService webFileInfoService;
	
	@RequestMapping("/webUpload")
	public void httpFile(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		String webIp=getRootWebDomain_(request);
		JSONObject json = new JSONObject();
		String message = "Y";
		String rootPath="/resources-1.0";
		// 获取文件上传需要保存的路径，upload文件夹需存在。
		String realPath = WebSiteManagerConstants.UPLOAD_FILE_PATH;
		String path = request.getSession().getServletContext().getRealPath(realPath);

		File f = new File(path);
		if (f != null && !f.exists()) {
			f.mkdirs();
		}

		String dix = request.getParameter("dix");// 文件后缀名
		String fileType=request.getParameter("fileType");
		String belong=request.getParameter("belong");
//		String webIp=request.getParameter("webIp");
		try {
			DefaultMultipartHttpServletRequest multipartRequest = (DefaultMultipartHttpServletRequest) request;
			MultipartFile mf = multipartRequest.getFile("file");
			CommonsMultipartFile orginalFile = (CommonsMultipartFile) mf;

			if (null != orginalFile) {
				String fileName = orginalFile.getOriginalFilename();

				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				String subname = sdf.format(new Date());
				if (fileName.lastIndexOf(".") < 0) {
					fileName = subname;
				} else {
					fileName = subname + "." + dix;
				}
				InputStream in = orginalFile.getInputStream();
				OutputStream out = new FileOutputStream(new File(path, fileName));

				int length = 0;
				byte[] buf = new byte[1024];
				

				while ((length = in.read(buf)) != -1) {
					out.write(buf, 0, length);
				}
				out.close();
				in.close();
				json.put("fileName", fileName);
				json.put("filePath", rootPath+realPath);
				
				TWebFileInfo entity=new TWebFileInfo();
				entity.setRequestIp(webIp);
				entity.setFileType(fileType);
				entity.setFileFolder(fileType+"s");
				entity.setFileName(fileName);
				entity.setFileUrl(rootPath+realPath+"/"+fileName);
				entity.setFileBelong(belong);
				entity.setUploadTime(new Date());
				this.webFileInfoService.saveFileInfo(entity);
			}
			
			
		} catch (Exception e) {
			message = "error";
			e.printStackTrace();
		}
		json.put("message", message);
		try {
			response.getWriter().print(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
