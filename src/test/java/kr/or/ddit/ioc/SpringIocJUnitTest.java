package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.service.IBoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-test.xml")
public class SpringIocJUnitTest {

	// bean이 다르고 scope가 singleton 이면 같은객체 생성됨ㅁ
	// bean이 같지만 scope가 prototype이면 다른 객체다

	// 같은클래스 다른객체
	@Resource(name = "boardDao")
	private IBoardDao boardDao;

	@Resource(name = "boardDaoPrototype") // scope = prototype
	private IBoardDao boardDaoPrototype;

	@Resource(name = "boardDaoPrototype") // scope = prototype
	private IBoardDao boardDaoPrototype2;

	// 같은클래스 다른객체
	@Resource(name = "boardService")
	private IBoardService boardService;

	// 같은클래스 다른객체
	@Resource(name = "boardService")
	private IBoardService boardService2;

	// 같은클래스 springSingleton
	@Resource(name = "boardServiceConstruction")
	private IBoardService boardServiceConstruction;

	/**
	 * Method : test 작성자 : PC01 변경이력 : Method 설명 : 스프링 컨테이너 생성 테스트
	 */
	@Test
	public void springIocTest() {

		/*** Given ***/
//	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:kr/or/ddit/ioc/application-ioc-test.xml");

		/*** When ***/
//	IBoardService boardService = (IBoardService)context.getBean("boardService");
		String msg = boardService.sayHello();

		/*** Then ***/
		assertNotNull(boardService);
		assertEquals("boardDao sayHello", msg);

	}

	// 같은 객체
	@Test
	public void springSingletonScopeTest() {
		/*** Given ***/
		// boardService, boardService2 : 같아야함
		// boardService, boardServiceConstrucetor 다른 객체
		// boardService2, boardServiceConstrucetor 다른 객체

		/*** When ***/
		assertNotNull(boardService);
		assertNotNull(boardService2);
		assertNotNull(boardServiceConstruction);
		assertEquals(boardService, boardService2);
		assertNotEquals(boardService, boardServiceConstruction);
		assertNotEquals(boardService2, boardServiceConstruction);
		/*** Then ***/

	}

	@Test
	public void springPrototypeScopeTest() {

		/*** Given ***/

		/*** When ***/
		assertNotNull(boardDao);
		assertNotNull(boardDaoPrototype);
		assertNotNull(boardDaoPrototype2);
		assertNotEquals(boardDao, boardDaoPrototype);
		assertNotEquals(boardDao, boardDaoPrototype2);
		assertNotEquals(boardDaoPrototype, boardDaoPrototype2);
		/*** Then ***/

	}

}
