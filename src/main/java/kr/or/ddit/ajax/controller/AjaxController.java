package kr.or.ddit.ajax.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@RequestMapping("/requestDataResponseBody")
	@ResponseBody	//응답 내용을 responseBody에다가 작성
	public PageVo requestDataResponseBody() {
		return new PageVo(5, 10);
	}

	@RequestMapping("/user")
	public String user(String userId, Model model) {
		
		UserVo userVo = userService.getUser(userId);
		model.addAttribute("userVo",userVo);
		
		return "jsonView";
	}
	
	@RequestMapping("/userHtml")
	public String userHtml(String userId, Model model) {
		
		UserVo userVo = userService.getUser(userId);
		model.addAttribute("userVo",userVo);
		
		return "user/userHtml";
	}
	
	@RequestMapping("/requestBody")
	@ResponseBody
	public UserVo requestBody(@RequestBody UserVo userVo) {
		userVo.setUserId(userVo.getUserId()+"_MODIFY");
		userVo.setPass(userVo.getPass()+"_MODIFY");
		return userVo;
	}
	
	@RequestMapping(path = "/requestBody", consumes = {"application/json"},
			
			)
	
}
