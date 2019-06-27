package kr.or.ddit.user.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.model.UserVoValidator;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.PartUtil;

@RequestMapping("/user")
@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource(name = "userService")
	private IUserService userService;

	
	/**
	* Method : userList
	* 작성자 : PC01
	* 변경이력 :
	* @param model
	* @return
	* Method 설명 : 사용자 전체 리스트
	*/
	@RequestMapping("/list")
	public String userList(Model model) {
		model.addAttribute("userList", userService.userList());

		return "user/userList";
	}
	
	@RequestMapping("/userListExcel")
	public String userListExcel(Model model, String filename) {
		List<String> header = new ArrayList<String>();
		header.add("userId");
		header.add("name");
		header.add("alias");
		header.add("addr1");
		header.add("addr2");
		header.add("zipcd");
		header.add("birth");
		logger.debug("filename:{}",filename);
		model.addAttribute("header",header);
		model.addAttribute("data",userService.userList());
		model.addAttribute("filename",filename);
		
		return "userExcelView";
	}

	/**
	* Method : userPagingList
	* 작성자 : PC01
	* 변경이력 :
	* @param pageVo
	* @param model
	* @return
	* Method 설명 : 
	*/
	@RequestMapping("/pagingList")
//	public String userPagingList(@RequestParam(name = "page", defaultValue = "1" ) int page, 
//								 @RequestParam(name="pageSize", defaultValue = "10") int pageSize, 
//								 Model model) {
//		PageVo pageVo = new PageVo(page,pageSize);
	public String userPagingList(PageVo pageVo, Model model) {

		logger.debug("pageVo:{}", pageVo);

		Map<String, Object> resultMap = userService.userPagingList(pageVo);
		List<UserVo> userList = (List<UserVo>) resultMap.get("userList");
		int paginationSize = (Integer) resultMap.get("paginationSize");

		model.addAttribute("userList", userList);
		model.addAttribute("paginationSize", paginationSize);
		model.addAttribute("pageVo", pageVo);

		return "user/userPagingList";
	}

	/**
	* Method : user
	* 작성자 : PC01
	* 변경이력 :
	* @param userId
	* @param model
	* @return
	* Method 설명 : 사용자 상세조회
	*/
	@RequestMapping("/user")
	public String user(String userId, Model model) {

		model.addAttribute("userVo", userService.getUser(userId));
		return "user/user";
		
	}
	/**
	* Method : userAjax
	* 작성자 : PC01
	* 변경이력 :
	* @param userId
	* @param model
	* @return
	* Method 설명 : 사용자 정보 ajax 요청
	*/
	@RequestMapping("/userJson")
	public String userJson(String userId, Model model) {
		
		model.addAttribute("userVo", userService.getUser(userId));
		
		return "jsonView";
	}
	
	/**
	* Method : userForm
	* 작성자 : PC01
	* 변경이력 :
	* @return
	* Method 설명 :사용자 등록화면
	*/
	@RequestMapping(path = "/form", method = RequestMethod.GET)
	public String userForm() {
		return "user/userForm";
	}
	
	/**
	* Method : userForm
	* 작성자 : PC01
	* 변경이력 :
	* @param userVo
	* @param userId
	* @param profile
	* @param model
	* @return
	* Method 설명 : 사용자 등록
	*/
//	@RequestMapping(path = "/form", method = RequestMethod.POST)
	public String userForm(UserVo userVo, BindingResult result, String userId, MultipartFile profile, Model model) {
		new UserVoValidator().validate(userVo, result);
		
		if(result.hasErrors())
			return "user/userForm";
		
		String viewName = "";
		
		UserVo dbUser = userService.getUser(userId);
		
		if(dbUser == null) {
			if(profile.getSize()>0) {
				String fileName = profile.getOriginalFilename();
				String ext = PartUtil.getExt(fileName);
				
				String uploadPath = PartUtil.getUploadPath();
				String filePath = uploadPath+File.pathSeparator+UUID.randomUUID().toString()+ext;
				userVo.setPath(filePath);
				userVo.setFileName(fileName);
				
				try {
					profile.transferTo(new File(filePath));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				
			}
			int insertCnt = userService.insertUser(userVo);
			if(insertCnt == 1)
				viewName = "redirect:/user/pagingList";
			
		}else {
		model.addAttribute("msg","이미 존재하는 사용자 입니다");
		viewName = userForm();
		}
		return viewName;
	}
	
	/**
	* Method : userForm
	* 작성자 : PC01
	* 변경이력 :
	* @param userVo
	* @param userId
	* @param profile
	* @param model
	* @return
	* Method 설명 : 사용자 등록
	*/
	@RequestMapping(path = "/form", method = RequestMethod.POST)
	public String userFormJsr(@Valid UserVo userVo, BindingResult result, String userId, MultipartFile profile, Model model) {
		
		if(result.hasErrors())
			return "user/userForm";
		
		String viewName = "";
		
		UserVo dbUser = userService.getUser(userId);
		
		if(dbUser == null) {
			if(profile.getSize()>0) {
				String fileName = profile.getOriginalFilename();
				String ext = PartUtil.getExt(fileName);
				
				String uploadPath = PartUtil.getUploadPath();
				String filePath = uploadPath+File.pathSeparator+UUID.randomUUID().toString()+ext;
				userVo.setPath(filePath);
				userVo.setFileName(fileName);
				
				try {
					profile.transferTo(new File(filePath));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				
			}
			int insertCnt = userService.insertUser(userVo);
			if(insertCnt == 1)
				viewName = "redirect:/user/pagingList";
			
		}else {
		model.addAttribute("msg","이미 존재하는 사용자 입니다");
		viewName = userForm();
		}
		return viewName;
	}
	
	/**
	* Method : profile
	* 작성자 : PC01
	* 변경이력 :
	* @param userId
	* @param request
	* @param response
	 * @return 
	* @throws IOException
	* Method 설명 : 사용자 사진 응답 생성
	*/
	
	@RequestMapping("/profile")
	public String profile(String userId,Model model) throws IOException {
		
		// 사용자 정보(path)를 조회
		UserVo userVo = userService.getUser(userId);
		model.addAttribute("userVo",userVo);
		
		return "profileView";	
	}
	
	/**
	* Method : userModify
	* 작성자 : PC01
	* 변경이력 :
	* @param userId
	* @param model
	* @return
	* Method 설명 : 사용자 수정 화면 요청
	*/
	@RequestMapping(path = "/modify", method = RequestMethod.GET)
	public String userModify(String userId, Model model) {
		logger.debug("get modify");
		model.addAttribute("userVo", userService.getUser(userId));
		logger.debug("userVo:{}",userService.getUser(userId));
		return "user/userModify";
	}
	
	@RequestMapping(path = "/modify", method=RequestMethod.POST)
	public String userModify(UserVo userVo, 
							MultipartFile profile, 
							HttpSession session, 
							Model model,
							RedirectAttributes redirectAttributes) throws IllegalStateException, IOException {
		
		logger.debug("modify");
		logger.debug("userVo:{}",userVo);
		if(profile.getSize()>0) {
			String fileName = profile.getOriginalFilename();
			String ext = PartUtil.getExt(fileName);
			
			String uploadPath = PartUtil.getUploadPath();
			
			String filePath = uploadPath+File.separator+UUID.randomUUID().toString()+ext;
			
			userVo.setPath(filePath);
			userVo.setFileName(fileName);
			
			profile.transferTo(new File(filePath));
		}		
		
		logger.debug("userVo:{}",userVo);
		userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
		int updateCnt = userService.updateUser(userVo);
		logger.debug("updateCnt:{}",updateCnt);
		
		if(updateCnt ==1) {
			session.setAttribute("msg", "등록되었습니다");
			redirectAttributes.addFlashAttribute("msg","등록되었습니다");
			redirectAttributes.addAttribute("userId",userVo.getUserId());
			return "redirect:/user/user";
		} else {
			return userModify(userVo.getUserId(), model);
		}		
	}
}
