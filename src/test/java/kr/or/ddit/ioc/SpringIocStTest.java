package kr.or.ddit.ioc;

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
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-st.xml")
public class SpringIocStTest {

	private static final Logger logger = LoggerFactory.getLogger(SpringIocStTest.class);

	@Resource(name = "bService")
	private IBoardService boardService;
	@Resource(name = "bDao")
	private IBoardDao boardDao;

	/**
	 * Method : test 작성자 : PC01 변경이력 : Method 설명 : 스프링 컨테이너 생성 테스트
	 */
	@Test
	public void springIocTest() {

		/*** Given ***/
//	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:kr/or/ddit/ioc/application-ioc-test.xml");

		/*** When ***/
//	IBoardService boardService = (IBoardService)context.getBean("boardService");
//	String msg = boardService.sayHello();

		/*** Then ***/
		assertEquals(boardDao, boardService.getBoardDao());
		logger.debug("boardDao:{}", boardDao);
		logger.debug("boardService.getBoardDao():{}", boardService.getBoardDao());
	}

}
