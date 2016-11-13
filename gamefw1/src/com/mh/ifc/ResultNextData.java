/**   
* 文件名称: NextData.java<br/>
* 版本号: V1.0<br/>   
* 创建人: alex<br/>  
* 创建时间 : 2015-6-2 下午8:19:21<br/>
*/  
package com.mh.ifc;

import java.util.List;

/** 
 * 类描述: TODO<br/>下一期接口返回
 * 创建人: TODO alex<br/>
 * 创建时间: 2015-6-2 下午8:19:21<br/>
 */
public class ResultNextData {
	
	private String rows;//行数
	
	private String code;
	
	private String remain;
	
	private List<DataInfo> data;
	
	public class DataInfo {
		
		private String expect;
		
		private String time;
		
		private String next;

		public String getExpect() {
			return expect;
		}

		public void setExpect(String expect) {
			this.expect = expect;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getNext() {
			return next;
		}

		public void setNext(String next) {
			this.next = next;
		}
		
		
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRemain() {
		return remain;
	}

	public void setRemain(String remain) {
		this.remain = remain;
	}

	public List<DataInfo> getData() {
		return data;
	}

	public void setData(List<DataInfo> data) {
		this.data = data;
	}
	
	
	

}
