package kr.or.ddit.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 servlet
 -extends HttpServlet
 -servlet-mapping
 
 spring
 - pojo(Plain Old Java Object)
 
 */

@Controller
public class MainController {

	@RequestMapping("/main")
	public String mainView(Model model) {
		// prefix : /WEB-INF/views/
		// suffix : .jsp

		// prefix + viewName + surffix
		// /WEB-INF/views/main.jsp
		model.addAttribute("mainUserId", "brown");
		
		//viewName
		return "main";
	}
}
