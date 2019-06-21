package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.typeConvert.model.FormattingVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-type-formatting.xml")
public class ApplicationIocTypeFormattingTest {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationIocTypeFormattingTest.class);

	@Resource(name = "formattingVo")
	private FormattingVo formattingVo;

	@Test
	public void FormattingVoTest() {

		/*** Given ***/

		/*** When ***/
		Date red_dt = formattingVo.getMod_dt();
		Date mod_dt = formattingVo.getMod_dt();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String reg_dtS = sdf.format(red_dt);
		String mod_dtS = sdf.format(mod_dt);
		/*** Then ***/
		logger.debug("formattingVo");
		assertEquals("2019-06-21", reg_dtS);
		assertEquals("2019-06-21", mod_dtS);
		assertEquals(6000000, formattingVo.getNumber());
	}

}
