package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.placeholder.DbInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-placeholder.xml")
public class SpringLifeCycleTest {

	@Resource(name = "dbInfo")
	private DbInfo dbInfo;

	@Test
	public void lifeCycleTestTest() {
		/*** Given ***/

		/*** When ***/
		assertNotNull(dbInfo);
		assertEquals("pc01", dbInfo.getUsername());
		assertEquals("java", dbInfo.getPassword());

		/*** Then ***/
	}

}
