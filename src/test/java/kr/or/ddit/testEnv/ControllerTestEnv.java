package kr.or.ddit.testEnv;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import jdk.nashorn.internal.ir.annotations.Ignore;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:kr/or/ddit/config/spring/root-context.xml",
		"classpath:kr/or/ddit/config/spring/application-context.xml",
		"classpath:kr/or/ddit/config/spring/application-datasource_dev.xml",
		"classpath:kr/or/ddit/config/spring/application-transaction.xml" })

//웹 환경
@WebAppConfiguration
public class ControllerTestEnv {

	@Autowired
	protected WebApplicationContext ctx;
	protected MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	@Test 
	@org.junit.Ignore
	public void dummy() {};

}
