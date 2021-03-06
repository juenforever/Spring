package kr.or.ddit.aop;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.service.IBoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/aop/application-aop.xml")
public class AopTest {
	private static final Logger logger = LoggerFactory.getLogger(AopTest.class);

	@Resource(name = "boardService")
	private IBoardService boardService;
	@Resource(name = "boardDao")
	private IBoardDao boardDao;

	@Test
	public void aopBeforeTest() {
		/*** Given ***/

		/*** When ***/
		String msg = boardService.sayHello();
		/*** Then ***/
		assertEquals("boardDao sayHello", msg);
	}

	@Test
	public void timeTest() {
		/*** Given ***/

		/*** When ***/
		String msg = boardService.sayHello();

		/*** Then ***/
	}

}
