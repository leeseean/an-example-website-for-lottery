/**   
 * 文件名称: WebMessageController.java<br/>
 * 版本号: V1.0<br/>   
 * 创建人: Channel<br/>  
 * 创建时间 : 2015-7-4 下午1:21:00<br/>
 */
package com.mh.web.controller;

/** 
 * 类描述: TODO<br/>
 * 创建人: TODO Channel<br/>
 * 创建时间: 2015-7-4 下午1:21:00<br/>
 */

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mh.commons.conf.CommonConstant;
import com.mh.commons.constants.WebSiteManagerConstants;
import com.mh.commons.orm.Page;
import com.mh.entity.WebGongGao;
import com.mh.entity.WebMessage;
import com.mh.service.WebMessageService;
import com.mh.web.login.UserContext;
@SuppressWarnings("all")
@Controller
@RequestMapping("/message")
public class WebMessageController extends BaseController {
	@Autowired
	private WebMessageService webMessageService;

	/**
	 * 跳转到信息中心的站內消息 方法描述: TODO</br>
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/listForUser")
	public ModelAndView getMessageNum(HttpServletRequest request, HttpServletResponse response) {
		UserContext uc = this.getUserContext(request);
		WebMessage webMessage = new WebMessage();
		webMessage.setUserName(uc.getUserName());
		Page page = this.newPage(request);
		this.webMessageService.getMessageUserName(page, webMessage);
		page.setColspanNum(6);
		return new ModelAndView("member/message/listForUser").addObject("webMessage", webMessage).addObject(CommonConstant.PAGE_KEY, page);

	}

	/**
	 * 查询系统消息: 方法描述:TODO</br>
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/listForSys")
	public ModelAndView adminMessage(HttpServletRequest request, HttpServletResponse response) {
		UserContext uc = this.getUserContext(request);
		WebMessage webMessage = new WebMessage();
		webMessage.setUserName(uc.getUserName());
		webMessage.setMsgType(1);
		Page page = this.newPage(request);
		this.webMessageService.getMessageByAdmin(page, webMessage);
		page.setColspanNum(6);
		return new ModelAndView("member/message/listForSys").addObject("webMessage", webMessage).addObject(CommonConstant.PAGE_KEY, page);
	}

	/**
	 * 查詢消息內容 方法描述: TODO</br>
	 * 
	 * @param request
	 * @param response
	 * @param webMessage
	 * @return ModelAndView
	 */
	@RequestMapping("/showMessage")
	public ModelAndView showMessage(HttpServletRequest request, HttpServletResponse response) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		WebMessage webMessage = this.webMessageService.findMessageContent(id);
		this.webMessageService.updateUserMsgStatus(id);
		return new ModelAndView("member/message/showMessage").addObject("webMessage", webMessage);

	}

	/**
	 * 删除信息 方法描述：TODO</br>
	 * 
	 * @param webMessage
	 * @return ModelAndView
	 * 
	 * */
	@RequestMapping("/deleteMessage")
	public ModelAndView deleteMessage(HttpServletRequest request, HttpServletResponse response) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		UserContext uc = this.getUserContext(request);
		this.webMessageService.deleteUserMsg(id);
		WebMessage webMessage = new WebMessage();
		webMessage.setUserName(uc.getUserName());
		Page page = this.newPage(request);
		this.webMessageService.getMessageUserName(page, webMessage);
		page.setColspanNum(6);
		return new ModelAndView("member/message/listForUser").addObject("webMessage", webMessage).addObject(CommonConstant.PAGE_KEY, page);
	}

	/**
	 * 公告消息列表 方法描述: TODO</br>
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/goGongGaoList")
	public ModelAndView goGongGaoList(HttpServletRequest request, HttpServletResponse response) {
		List<WebGongGao> dataList = WebSiteManagerConstants.WEBGONGGAO_LIST;
		return new ModelAndView("web/message").addObject("dataList", dataList);

	}

}
