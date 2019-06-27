package kr.or.ddit.lprod.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.testEnv.LogicTestEnv;

public class LprodServiceTest extends LogicTestEnv {
	private static final Logger logger = LoggerFactory.getLogger(LprodServiceTest.class);

	@Resource(name = "lprodService")
	private ILprodService lprodService;

	@Test
	public void lprodListServiceTest() {
		/*** Given ***/
		/*** When ***/
		List<LprodVo> lprodList = lprodService.lprodList();
		/*** Then ***/
		assertNotNull(lprodList);
		assertEquals(18, lprodList.size());

	}

	@Test
	public void getLprodServiceTest() {
		/*** Given ***/
		String lprod_id = "1";

		/*** When ***/
		LprodVo lprodVo = lprodService.getLprod(lprod_id);

		/*** Then ***/
		assertEquals("P101", lprodVo.getLprod_gu());

	}

	@Test
	public void lprodPagingListServiceTest() {
		/*** Given ***/
		PageVo pageVo = new PageVo(1, 10);

		/*** When ***/
		Map<String, Object> lprodList = lprodService.lprodPagingList(pageVo);

		/*** Then ***/
		assertNotNull(lprodList);
		assertEquals(2, lprodList.size());
	}

	@Test
	public void lprodCntServiceTest() {
		/*** Given ***/

		/*** When ***/
		int lprodCnt = lprodService.lprodCnt();
		/*** Then ***/
		assertEquals(18, lprodCnt);
	}

}
