/**   
* 文件名称: WebWeihuController.java<br/>
* 版本号: V1.0<br/>   
* 创建人: zoro<br/>  
* 创建时间 : 2016-1-13 下午6:57:05<br/>
*/  
package com.mh.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSONObject;
import com.mh.commons.conf.CommonConstant;
import com.mh.commons.constants.WebSiteManagerConstants;
import com.mh.commons.utils.IPSeeker;
import com.mh.commons.utils.ServletUtils;
import com.mh.entity.SysParameter;
import com.mh.entity.WebUserFlat;
import com.mh.service.SysParameterService;
import com.mh.service.WebClientInfoService;
import com.mh.service.WebSiteManagerService;
import com.mh.service.WebUserFlatService;
import com.mh.web.job.CpCacheHelper;
import com.mh.web.job.WebFlatSiteDateHelper;
import com.mh.web.job.WebResourceDataHelper;
import com.mh.web.util.FastUtil;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO zoro<br/>
 * 创建时间: 2016-1-13 下午6:57:05<br/>
 */
@Controller
@RequestMapping("/clientInterface")
public class WebClientInterfaceController {
	
	private static final Logger logger = LoggerFactory.getLogger(WebClientInterfaceController.class);
	
	@Autowired
	private WebClientInfoService infoService;
	@Resource
	private WebSiteManagerService webSiteManagerService;
	
	@Autowired
	private SysParameterService sysParameterService;
	@Autowired
	private WebUserFlatService webUserFlatService;
	/**
	 * 会员核对：查询会员
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/memberInfo")
	public void memberInfo(HttpServletRequest request, HttpServletResponse response){
		WebUserFlat webUserList=null;
		try {
			String userName = request.getParameter("data");
			if(StringUtils.isBlank(userName)){
				ServletUtils.outPrintFail(request, response, "参数错误");
				return;
			}
			
			boolean reFlag = this.getAuthOnlineIp(request);
			if(!reFlag){
				logger.info("无权限访问！");
				ServletUtils.outPrintFail(request, response, "IP无权限访问");
				return;
			}
			webUserList = webUserFlatService.getWebUserFlat(userName);
			ServletUtils.outPrintSuccess(request, response, webUserList);
		} catch (Exception e) {
			logger.info("会员核对：查询会员名字出错！");
			ServletUtils.outPrintFail(request, response, "查询出错");
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 方法描述: 超端维护接口</br>
	 * 参数报文格式{"flat":{"mg":1,"pt":0},"msg":{"mg":"1","pt":"0"}}
	 * 创建人: zoro<br/> 
	 * @param request
	 * @param response  
	 * void
	 */
	@RequestMapping("/weihu")
	public void weihu(HttpServletRequest request, HttpServletResponse response){
		try{
			
			boolean reFlag = this.getAuthOnlineIp(request);
			if(!reFlag){
				logger.info("无权限访问！");
				ServletUtils.outPrintFail(request, response, "IP无权限访问");
				return;
			}
			logger.info("权限通过");
			
			String _betInfo = request.getParameter("flat");
			String _msg = request.getParameter("msg");
			if(StringUtils.isBlank(_betInfo) || StringUtils.isBlank(_msg)){
				ServletUtils.outPrintFail(request, response, "参数错误");
				return;
			}
			logger.info("_betInfo"+_betInfo);
			logger.info("_msg"+_msg);
			JSONObject flatInfo = JSONObject.parseObject(_betInfo);
			JSONObject msgInfo = JSONObject.parseObject(_msg);
			
			List<Object[]> batchArgs_num = new ArrayList<Object[]>();
			for(String key :flatInfo.keySet()){
				logger.info(key);
				logger.info(flatInfo.getString(key));
				Object[] args ={msgInfo.getString(key), flatInfo.getString(key), key};
				batchArgs_num.add(args);
			}
			infoService.updateBatchWeihu(batchArgs_num);
			logger.info("更新成功！");
			/**刷新缓存*/
			WebSiteManagerConstants.WEBWEIHU_LIST = webSiteManagerService.webweihu_list();
			WebSiteManagerConstants.initCtxMap();// 常用数据
			logger.info("缓存刷新完成");
			ServletUtils.outPrintSuccess(request, response, "更新成功！");
		}catch (Exception e) {
			logger.info("更新出现异常！");
			ServletUtils.outPrintFail(request, response, "更新出错"+e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/slot")
	public void slot(HttpServletRequest request, HttpServletResponse response){
		try{
			
			boolean reFlag = this.getAuthOnlineIp(request);
			if(!reFlag){
				logger.info("无权限访问！");
				ServletUtils.outPrintFail(request, response, "IP无权限访问");
				return;
			}
			logger.info("权限通过");
			
			WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
			HibernateTemplate hibernateTemplate_shared = (HibernateTemplate) wac.getBean("hibernateTemplate_shared");
			JdbcTemplate jdbcTemplate_shared = (JdbcTemplate) wac.getBean("jdbcTemplate_shared");
			WebResourceDataHelper helper = new WebResourceDataHelper(jdbcTemplate_shared, hibernateTemplate_shared);
			helper.initData();
			
			logger.info("缓存刷新完成");
			ServletUtils.outPrintSuccess(request, response, "更新成功！");
		}catch (Exception e) {
			logger.info("更新出现异常！");
			ServletUtils.outPrintFail(request, response, "更新出错"+e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	
	
	
	/**
	 * POST 
	 * 方法描述: 超端维护接口</br>
	 * 参数报文格式</br>
	 * </br>
		"flat"=</br>
		{</br>
			"live":{"ag":1,"bbin":0},</br>
			"slot":{"mg":1,"pt":0},</br>
			"sport":{'sb':1}</br>
			},</br>
		"index"=</br>
		{</br>
			"live":{"ag":1,"bbin":2},</br>
			"slot":{"mg":1,"pt":2},</br>
			"sport":{'sb':1}</br>
			}</br>
		</br>
	 * 创建人: zoro<br/> 
	 * @param request
	 * @param response  
	 * void
	 */
	@RequestMapping(value="/webFlatSite", method=RequestMethod.POST)
	public void webFlatSite(HttpServletRequest request, HttpServletResponse response){
		try{
			
			boolean reFlag = this.getAuthOnlineIp(request);
			if(!reFlag){
				logger.info("无权限访问！");
				ServletUtils.outPrintFail(request, response, "IP无权限访问");
				return;
			}
			logger.info("权限通过");
			
			//String _betInfo = request.getParameter("flat");
			String _index = request.getParameter("index");
			if(StringUtils.isBlank(_index)){
				ServletUtils.outPrintFail(request, response, "参数错误");
				return;
			}
			//logger.info("_betInfo"+_betInfo);
			logger.info("__index:"+_index);
			//JSONObject flatInfo = JSONObject.parseObject(_betInfo);
			JSONObject indexInfo = JSONObject.parseObject(_index);
			
			List<Object[]> batchArgs_num = new ArrayList<Object[]>();
			
			JSONObject liveJson = indexInfo.getJSONObject("live");
			JSONObject sportJson = indexInfo.getJSONObject("sport");
			JSONObject slotJson = indexInfo.getJSONObject("slot");
			JSONObject caipiao = indexInfo.getJSONObject("caipiao");
			for(String key :liveJson.keySet()){
				logger.info(key);
				logger.info(liveJson.getString(key));
				Object[] args ={1, liveJson.getString(key), key, "live"};
				batchArgs_num.add(args);
			}
			for(String key :sportJson.keySet()){
				logger.info(key);
				logger.info(sportJson.getString(key));
				Object[] args ={1, sportJson.getString(key), key, "sport"};
				batchArgs_num.add(args);
			}
			for(String key :slotJson.keySet()){
				logger.info(key);
				logger.info(slotJson.getString(key));
				Object[] args ={1, slotJson.getString(key), key, "slot"};
				batchArgs_num.add(args);
			}
			for(String key :caipiao.keySet()){
				logger.info(key);
				logger.info(caipiao.getString(key));
				Object[] args ={1, caipiao.getString(key), key, "caipiao"};
				batchArgs_num.add(args);
			}
			infoService.updateBatchFlatInfo(batchArgs_num);
			logger.info("更新成功！");
			/**刷新缓存*/
			WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
			HibernateTemplate hibernateTemplate = (HibernateTemplate) wac.getBean("hibernateTemplate");
			JdbcTemplate jdbcTemplate = (JdbcTemplate) wac.getBean("jdbcTemplate");
			WebFlatSiteDateHelper helper = new WebFlatSiteDateHelper(jdbcTemplate, hibernateTemplate);
			helper.initData();
			logger.info("缓存刷新完成");
			ServletUtils.outPrintSuccess(request, response, "更新成功！");
		}catch (Exception e) {
			logger.info("更新出现异常！");
			ServletUtils.outPrintFail(request, response, "更新出错"+e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value="/cpcache")
	public void cpcache(HttpServletRequest request, HttpServletResponse response){
		try{
			
			boolean reFlag = this.getAuthOnlineIp(request);
			if(!reFlag){
				logger.info("无权限访问！");
				ServletUtils.outPrintFail(request, response, "IP无权限访问");
				return;
			}
			logger.info("权限通过");
		
			
			/**刷新缓存*/
			CpCacheHelper helper = new CpCacheHelper();
			helper.getBaseData();
			logger.info("缓存刷新完成");
			ServletUtils.outPrintSuccess(request, response, "更新成功！");
		}catch (Exception e) {
			logger.info("更新出现异常！");
			ServletUtils.outPrintFail(request, response, "更新出错"+e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 获取鉴权ip地址
	 * 方法描述: TODO</br> 
	 * @return  
	 * List<String>
	 */
	public boolean getAuthOnlineIp(HttpServletRequest request){
		
		SysParameter sysParameter = this.sysParameterService.getSysParameterByPramName(CommonConstant.WEB_USER_ONLINE_AUTH_IP);
		String userIp = "";
		if(sysParameter!=null){
			userIp = sysParameter.getParamValue();
		}
		String authIp = IPSeeker.getIpAddress(request);
		logger.info("访问ip:" + authIp);
		logger.info("配置Ip:" + userIp);
		String[] ipArr = userIp.split(",");
		for(int i=0;i<ipArr.length;i++){
			if(authIp.indexOf(ipArr[i])>0 || authIp.equals(ipArr[i])){
				return true;
			}
		}
		return false;
	}
	
	
	
	//发送数据json
	@RequestMapping(value="/paramsEdit")
	public String paramsEdit(HttpServletRequest request , HttpServletResponse response){
		try{
			boolean reFlag = this.getAuthOnlineIp(request);
			if(!reFlag){
				logger.info("无权限访问！");
				ServletUtils.outPrintFail(request, response, "IP无权限访问");
			}else{
				String paramNameString = request.getParameter("data");
				if(paramNameString != null){
					List<String> paramName = (List<String>) FastUtil.parseArray(paramNameString, String.class);
					String[] arr = paramName.toArray(new String[paramName.size()]);
					List<SysParameter> list = sysParameterService.getSysParameterList(arr);
					ServletUtils.outPrintSuccess(request, response, list);
				}
			}
		}catch(Exception e){
			logger.info("sysParameter查询数据异常");
			ServletUtils.outPrintFail(request, response, "查询出错"+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	
	//调用修改后的数据
	@RequestMapping(value="/updateParamsEdit")
	public String updateParamsEdit(HttpServletRequest request , HttpServletResponse response){
		String data = request.getParameter("data");
		if(data != null){
			List<SysParameter> list = (List<SysParameter>) FastUtil.parseArray(data, SysParameter.class);
			sysParameterService.saveUpdateAll(list);
			ServletUtils.outPrintSuccess(request, response, "更新成功！");
		}else{
			logger.info("sysParameter接收数据失败");
		}
		return null;
	}
	
}
