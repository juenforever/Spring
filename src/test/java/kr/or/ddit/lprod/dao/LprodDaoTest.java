package kr.or.ddit.lprod.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.testEnv.LogicTestEnv;

public class LprodDaoTest extends LogicTestEnv {
	private static final Logger logger = LoggerFactory.getLogger(LprodDaoTest.class);

	@Resource(name = "lprodDao")
	private ILprodDao lprodDao;

	@Test
	public void lprodListDaoTest() {
		/*** Given ***/
		logger.debug("lprodDao:{}", lprodDao);
		/*** When ***/
		List<LprodVo> lprodList = lprodDao.lprodList();
		/*** Then ***/
		assertNotNull(lprodList);
		assertEquals(18, lprodList.size());

	}

	@Test
	public void getLprodDaoTest() {
		/*** Given ***/
		String lprod_id = "1";

		/*** When ***/
		LprodVo lprodVo = lprodDao.getLprod(lprod_id);

		/*** Then ***/
		assertEquals("P101", lprodVo.getLprod_gu());

	}

	@Test
	public void lprodPagingListTest() {
		/*** Given ***/
		PageVo pageVo = new PageVo(1, 10);

		/*** When ***/
		List<LprodVo> lprodList = lprodDao.lprodPagingList(pageVo);

		/*** Then ***/
		assertNotNull(lprodList);
		assertEquals(10, lprodList.size());
	}

	@Test
	public void lprodCnt() {
		/*** Given ***/

		/*** When ***/
		int lprodCnt = lprodDao.lprodCnt();
		/*** Then ***/
		assertEquals(18, lprodCnt);
	}

}
