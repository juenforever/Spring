package kr.or.ddit.prod.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.prod.model.ProdVo;

public class ProdDaoTest {
	
	@Resource(name = "prodDao")
	private IProdDao prodDao;

	@Test
	public void prodListTest() {
		/***Given***/
		String prod_lgu = "P101";

		/***When***/
		List<ProdVo>prodList = prodDao.prodList(prod_lgu);

		/***Then***/
	}
	
}
