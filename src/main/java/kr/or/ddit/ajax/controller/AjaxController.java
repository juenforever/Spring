package kr.or.ddit.ajax.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;

@RequestMapping("/ajax")
@Controller
public class AjaxController {
	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
	
	@Resource(name="userService")
	private IUserService userService;

	@RequestMapping("/view")
	public String view() {
		return "tiles.ajaxView";
	}

	@RequestMapping("/requestData")
	public String requestData(Model model) {

		model.addAttribute("pageVo", new PageVo(5, 10));
		model.addAttribute("pageVo", new PageVo(2, 10));
		List<String> rangers = new ArrayList<String>();
		rangers.add("brown");
		rangers.add("sally");
		rangers.add("cony");

		model.addAttribute("rangers", rangers);

		return "jsonView";
	}

	@RequestMapping("/user")
	public String user(String userId, Model model) {
		
		logger.debug("userId:{}",userId);
		UserVo userVo = userService.getUser(userId);
		logger.debug("userVo:{}",userVo);
		model.addAttribute("userVo",userVo);
		
		//{userVo : {userId : 'brown', name : '브라운', alias : '곰'............}}
		
		return "jsonView";
	}
	
	@RequestMapping("/userHtml")
	public String userHtml(String userId, Model model) {
		
		logger.debug("userId:{}",userId);
		UserVo userVo = userService.getUser(userId);
		logger.debug("userVo:{}",userVo);
		model.addAttribute("userVo",userVo);
		
		//{userVo : {userId : 'brown', name : '브라운', alias : '곰'............}}
		
		return "user/userHtml";
	}
	
}
