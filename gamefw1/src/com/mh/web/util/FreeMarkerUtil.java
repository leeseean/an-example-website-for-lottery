package com.mh.web.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.mh.web.utilBean.FreeMarkerBean;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
/**
 * freemarker工具类
 * ClassName: FreeMarkerUtil 
 * @Description: TODO
 * @author andy
 * @date 2015-7-20
 */
public class FreeMarkerUtil {
	//生成html文件
	 public void resolution(FreeMarkerBean bean) throws Exception{  
	        Writer out = null;  
	        String charset="UTF-8";
	        
	        Configuration cfg = new Configuration();  
	        cfg.setDirectoryForTemplateLoading(new File(bean.getFtlPath()));  
	        cfg.setDefaultEncoding(charset);  
	        
	        try {       
	            Template template = cfg.getTemplate(bean.getFtlName(),charset);  
	            String path = bean.getSavePath(); 
	            
	            File file = new File(path +bean.getFileName());  
	            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),charset));  
	            template.process(bean.getMap(), out);  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } catch (TemplateException e) {  
	            e.printStackTrace();  
	        }finally{  
	            try {  
	                out.flush();  
	                out.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	    }  
}
