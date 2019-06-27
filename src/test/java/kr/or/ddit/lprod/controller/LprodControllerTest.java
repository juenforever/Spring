package kr.or.ddit.lprod.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.testEnv.ControllerTestEnv;

public class LprodControllerTest extends ControllerTestEnv {
	private static final Logger logger = LoggerFactory.getLogger(LprodControllerTest.class);
	@Test
	public void lprodPagingListTest() throws Exception {
		/*** Given ***/

		/*** When ***/
		MvcResult mvcResult = mockMvc.perform(get("/lprod/pagingList").param("page", "2").param("pageSize", "10"))
				.andReturn();
		logger.debug("mvcResult:{}",mvcResult);
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		logger.debug("ModelAndView:{}",mav);
		List<LprodVo> lprodList = (List<LprodVo>) mav.getModelMap().get("lprodList");

		int paginationSize = (Integer) mav.getModelMap().get("paginationSize");
		PageVo pageVo = (PageVo) mav.getModelMap().get("pageVo");
		/*** Then ***/
		assertEquals("lprod/lprodPagingList", viewName);
		assertEquals(8, lprodList.size());
		assertEquals(2, paginationSize);
		assertEquals(2, pageVo.getPage());
		assertEquals(10, pageVo.getPageSize());
		// PageVo equals, hashCode 메소드를 구현해서 객체간 값 비교
		assertEquals(new PageVo(2, 10), pageVo);

	}

}
