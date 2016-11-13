/**   
* 文件名称: NewPtResResult.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-12-5 上午1:09:52<br/>
*/  
package com.mh.ifc.http;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-12-5 上午1:09:52<br/>
 */
public class NewPtResResult {


	private String code;//0成功 其他失败
	
	private String message;
 
	private String data;
	
	 
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

 
	
}
