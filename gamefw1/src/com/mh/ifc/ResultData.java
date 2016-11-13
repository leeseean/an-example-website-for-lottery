package com.mh.ifc;

import java.util.List;

/**
 * 
 * 类描述: TODO<br/> 
 * 修改人: alex<br/>
 * 修改时间: 2015-5-25 下午2:31:29<br/>
 */
public class ResultData {
	
	private String rows;//行数
	
	private String code;
	
	private String remain;
	
	private List<DataInfo> data;
	
	
	
	
	public class DataInfo {

		private String expect;
		
		private String opencode;
		
		private String opentime;
		
		private String opentimestamp;

		public String getExpect() {
			return expect;
		}

		public void setExpect(String expect) {
			this.expect = expect;
		}

		public String getOpencode() {
			return opencode;
		}

		public void setOpencode(String opencode) {
			this.opencode = opencode;
		}

		public String getOpentime() {
			return opentime;
		}

		public void setOpentime(String opentime) {
			this.opentime = opentime;
		}

		public String getOpentimestamp() {
			return opentimestamp;
		}

		public void setOpentimestamp(String opentimestamp) {
			this.opentimestamp = opentimestamp;
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
