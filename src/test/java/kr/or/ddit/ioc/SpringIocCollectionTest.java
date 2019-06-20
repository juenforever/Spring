package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.collection.IocCollection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-collection.xml")
public class SpringIocCollectionTest {
	private static final Logger logger = LoggerFactory.getLogger(SpringIocCollectionTest.class);

	@Resource(name = "collectionBean")
	private IocCollection iocCollection;

	@Test
	public void IocCollectionTest() {
		/*** Given ***/

		/*** When ***/
		assertNotNull(iocCollection);
		assertEquals("brown", iocCollection.getList().get(0));
		assertEquals("2019-08-08", iocCollection.getMap().get("birth"));
		assertTrue(iocCollection.getSet().contains("cony"));
		assertEquals("브라운", iocCollection.getProperties().get("name"));

		/*** Then ***/
	}

}
