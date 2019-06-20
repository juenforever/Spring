package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.service.IBoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationIocBeanScanConfig.class })
public class ApplicationIocBeanScanConfigTest {

	@Resource(name = "boardDao")
	private IBoardDao boardDao;

	@Resource(name = "boardService")
	private IBoardService boardService;

	@Test
	public void springBeanScanTest() {
		/*** Given ***/

		/*** When ***/
		String msg = boardDao.sayHello();
		/*** Then ***/
		assertNotNull(boardDao);
		assertEquals("boardDao sayHello", msg);
		assertEquals(boardDao, boardService.getBoardDao());

	}

}
