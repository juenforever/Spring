package kr.or.ddit.file.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.testEnv.ControllerTestEnv;

public class FileControllerTest extends ControllerTestEnv {
	private static final Logger logger = LoggerFactory.getLogger(FileControllerTest.class);
	/**
	* Method : uploadViewTest
	* 작성자 : PC01
	* 변경이력 :
	* Method 설명 : uploadView 요청 테스트
	 * @throws Exception 
	*/
	@Test
	public void uploadViewTest() throws Exception {
		/***Given***/
		

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/file/uploadView")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		/***Then***/
		assertEquals("upload/upload", viewName);
	}
	
	/**
	* Method : uploadTest
	* 작성자 : PC01
	* 변경이력 :
	* Method 설명 : 파일 업로드 테스트
	 * @throws Exception 
	*/
	@Test
	public void uploadTest() throws Exception {
		/***Given***/
		
		File currentFolder = new File("");
//		File file  = new File("D:\\upload\\62323532-31ef-419f-b4b9-ecd6b1595166.png");
		File file = new File(getClass().getClassLoader().getResource("kr/or/ddit/testenv/Tulips10.jpg").getFile());
//		File file = new File(currentFolder.getAbsolutePath()+File.separator+"src"+File.separator+"test"
//							+File.separator+"resources"+File.separator+"kr"+File.separator
//							+"or"+File.separator+"ddit"+File.separator+File.separator+"testenv"+File.separator+"Tulips10.jpg");
		logger.debug("file.getName();:{}",file.getName());
		FileInputStream fis = new FileInputStream(file);
		MockMultipartFile mockMultipartFile = new MockMultipartFile("img", file.getName(),"", fis);
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(fileUpload("/file/upload")
												.file(mockMultipartFile))
												.andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		String msg = (String) mav.getModelMap().get("msg");
		/***Then***/
		assertEquals("SUCCESS", msg);
		assertEquals("upload/upload", viewName);
	}

}
