package kr.or.ddit.login.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.testEnv.ControllerTestEnv;
import kr.or.ddit.user.model.UserVo;

public class LoginControllerTest extends ControllerTestEnv {

	@Test
	public void loginViewNotLoginedTest() throws Exception {

		/*** Given ***/

		/*** When ***/
		MvcResult mvcResult = mockMvc.perform(get("/login")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		/*** Then ***/
		assertEquals("login/login", viewName);
	}

	@Test
	public void loginViewLoginedTest() throws Exception {

		/*** Given ***/

		/*** When ***/
		MvcResult mvcResult = mockMvc.perform(get("/login").sessionAttr("USER_INFO", new UserVo())).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		/*** Then ***/
		assertEquals("main", viewName);
	}
	
	/**
	* Method : loginProcessSuccessTest
	* 작성자 : PC01
	* 변경이력 :
	* @throws Exception
	* Method 설명 :로그인 요청 처리 성공 테스트
	*/
	@Test
	public void loginProcessSuccessTest() throws Exception {
		/***Given***/
		String userId = "brown";
		String password = "brown1234";

		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/login").param("userId",userId).param("password",password)).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		HttpSession session = mvcResult.getRequest().getSession();
		
		String viewName = mav.getViewName();
		UserVo userVo = (UserVo)session.getAttribute("USER_INFO");
		/***Then***/
		assertEquals("main", viewName);
		assertEquals("브라운", userVo.getName());
	}
	/**
	* Method : loginProcessFailTest
	* 작성자 : PC01
	* 변경이력 :
	* @throws Exception
	* Method 설명 :로그인 요청 실패 테스트
	*/
	@Test
	public void loginProcessFailTest() throws Exception {
		/***Given***/
		String userId = "brown";
		String password = "brown1234sdfsdf";
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/login").param("userId",userId).param("password",password)).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		HttpSession session = mvcResult.getRequest().getSession();
		String viewName = mav.getViewName();
		UserVo userVo = (UserVo)session.getAttribute("USER_INFO");
		/***Then***/
		assertEquals("login/login", viewName);
	}

}
