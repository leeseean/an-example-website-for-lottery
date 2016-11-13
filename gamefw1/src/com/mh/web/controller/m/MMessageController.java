package com.mh.web.controller.m;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mh.commons.utils.ServletUtils;
import com.mh.entity.WebMessage;
import com.mh.service.WebMessageService;
import com.mh.web.controller.BaseController;
import com.mh.web.login.UserContext;
@Controller
@RequestMapping("/m/message")
public class MMessageController extends BaseController 
{
	@Autowired
	private WebMessageService webMessageService;
	
	@RequestMapping("/getMessageList")
	public ModelAndView getMessageList(HttpServletRequest request,HttpServletResponse response)
	{
		String code = request.getParameter("code");
		ModelAndView model = new ModelAndView();
		model.setViewName(code.equals("2") ? "m/member/message" : "m/member/sys_message");
		UserContext uc = this.getUserContext(request);
		WebMessage msg = new WebMessage();
		if(code.equals("2"))
			msg.setUserName(uc.getUserName());
		
		msg.setMsgType(Integer.parseInt(code));
		List<WebMessage> msgList = this.webMessageService.getMessageList(msg);
		return model.addObject("msgList", msgList);
	}
	
	@RequestMapping("/showMessage")
	public ModelAndView showMessage(HttpServletRequest request,HttpServletResponse response)
	{
		String id = request.getParameter("id");
		ModelAndView model = new ModelAndView("m/member/message_detail");
		WebMessage msg = this.webMessageService.findMessageContent(Integer.parseInt(id));
		this.webMessageService.updateUserMsgStatus(Integer.parseInt(id));
		return model.addObject("msg", msg);
	}
	
	@RequestMapping("/delMessage")
	public void delMessage(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		try {
			if(StringUtils.isNotEmpty(id)){
				//int count = this.webMessageService.deleteUserMsg(Integer.parseInt(id));
				this.webMessageService.updateMsgStatus(0, Integer.parseInt(id));
				ServletUtils.outPrintSuccess(request, response, "删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ServletUtils.outPrintSuccess(request, response, "删除失败");
		}
	}
}
