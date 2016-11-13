/**   
* 文件名称: AgentInterceptor.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-7-21 下午1:09:00<br/>
*/  
package com.mh.web.login;

import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-7-21 下午1:09:00<br/>
 */
public class AgentInterceptor implements WebRequestInterceptor {

	public void afterCompletion(WebRequest request, Exception arg1)
			throws Exception {
	
	}

	public void postHandle(WebRequest request, ModelMap arg1) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void preHandle(WebRequest request) throws Exception {
	} 
	
 

}
