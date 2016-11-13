package com.mh.web.util;

public class MobileUtil {
	/**
	 * 获取订单跳转页面的名称
	 * @Description: TODO
	 * @param    
	 * @return String
	 * @author Andy
	 * @date 2015-9-28
	 */
	public static String getForwardOrderPageName(String gameCode,String cpTypeCode){
//		String templ_1="cp_order_1";
		
		return "lottery_order";
	}
	
	/**
	 * 获取跳转页面的名称
	 * @Description: TODO
	 * @param    
	 * @return String
	 * @author Andy
	 * @date 2015-9-28
	 */
	public static String getForwardPageName(String gameCode){
		if("HK6".equals(gameCode)){
			return "hk6";	
		}else if("FC3D".equals(gameCode) || "PL3".equals(gameCode)){
			return "fc3dpl3";
		}else if(gameCode.indexOf("SSC")!=-1){
			return "ssc";
		}else if(gameCode.indexOf("KLSF")!=-1){
			return "klsf";
		}else{
			return gameCode;
		}
		
	}
}
