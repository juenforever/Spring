package kr.or.ddit.main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.main.model.MainVo;
import kr.or.ddit.user.model.UserVo;

/*
 servlet
 -extends HttpServlet
 -servlet-mapping
 
 spring
 - pojo(Plain Old Java Object)
 
 */

@Controller
@SessionAttributes("rangers")
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	
	@ModelAttribute("rangers")
	public List<String> rangers(){
		logger.debug("{}","rangers()");
		List<String> rangers = new ArrayList<String>();
		rangers.add("brown");
		rangers.add("cony");
		rangers.add("sally");
		rangers.add("james");
		rangers.add("moon");
				
		return rangers;
	}
	
	/**
	* Method : mainView
	* 작성자 : PC01
	* 변경이력 :
	* @param model
	* @return
	* Method 설명 : main 페이지 요청 (viewName)
	*/
	@RequestMapping("/main")
	public String mainView(Model model, @ModelAttribute("userVo")UserVo userVo) {
		
		model.asMap().get("rangers");
		logger.debug("mainView");
		logger.debug("model.asMap().get(\"rangers\"):{}",model.asMap().get("rangers"));
		logger.debug("userVo : {}", userVo);
		
		userVo.setName("브라운");
		// prefix : /WEB-INF/views/
		// suffix : .jsp

		// prefix + viewName + surffix
		// /WEB-INF/views/main.jsp
		model.addAttribute("mainUserId", "brown");
		
		//viewName
		return "main";
	}
	
	/**
	* Method : mainViewMav
	* 작성자 : PC01
	* 변경이력 :
	* @return
	* Method 설명 : main 페이지 요청 (ModelAndView 사용)
	*/
	@RequestMapping("/mainMav")
	public ModelAndView mainViewMav(@ModelAttribute("rangers")List<String> rangers) {
		logger.debug("mainViewMav:{}",rangers);
		//viewName을 기반으로 ModelAndView 객체를 생성
		ModelAndView mav = new ModelAndView("main");
		mav.addObject("mainUserId","brown");
		//model.addAttribute("mainUserId", "brown");
		return mav;
	}
	
	/**
	* Method : pathVariable
	* 작성자 : PC01
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : pathVariable로 부터 사용자 아이디 가져오기 (ex: 도서관 사업소)
	*/
	@RequestMapping("/main/pathVariable/{userId}")
	public String pathVariable(@PathVariable("userId") String userId) {
		logger.debug("userId:{}",userId);
		return"main";
	}
	
	/**
	* Method : header
	* 작성자 : PC01
	* 변경이력 :
	* @param accept
	* @return
	* Method 설명 : Accept 헤더 정보 가져오기
	*/
	@RequestMapping("/main/header")
	public String header(@RequestHeader(name = "Accept"/*, required = false*/) String accept) {
		logger.debug("Accept:{}",accept);
		return "main";
	}
	
	@RequestMapping("/main/view")
	public String view() {
		
		return "view";
	}
	
	@RequestMapping("/main/process")
	public String process(HttpServletRequest request, 
							String[] userId, 
							@RequestParam("userId") List<String> userIdList,
							@RequestParam("name") List<String> name,
							MainVo mainVo) {
		
		String[] userIdArr = request.getParameterValues("userId");
		String userIdParameter = request.getParameter("userId");
		logger.debug("userIdParameter:{}",userIdParameter);
		
		logger.debug("request.getParameterValues(\"userId\")");
		for(String u : userIdArr)
			logger.debug("userId:{}", u);
		
		logger.debug("String[] userId");
		for(String u : userId)
			logger.debug("userId:{}",u);
		
		logger.debug("userIdList");
		for(String u : userIdList)
			logger.debug("userId:{}", u);
		
		logger.debug("mainVo:{}", mainVo);
				
		return "main";
	}
}
