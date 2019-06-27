package kr.or.ddit.lprod.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.paging.model.PageVo;

@RequestMapping("/lprod")
@Controller
public class LprodController {
	private static final Logger logger = LoggerFactory.getLogger(LprodController.class);

	@Resource(name = "lprodService")
	private ILprodService lprodService;

	@RequestMapping("/pagingList")
	public String pagingList(PageVo pageVo, Model model) {

		Map<String, Object> resultMap = lprodService.lprodPagingList(pageVo);
		logger.debug("resultMap:{}",resultMap);
		List<LprodVo> lprodList = (List<LprodVo>) resultMap.get("lprodList");
		logger.debug("lprodList:{}",lprodList);
		int paginationSize = (Integer) resultMap.get("paginationSize");

		model.addAttribute("lprodList", lprodList);
		model.addAttribute("paginationSize", paginationSize);
		model.addAttribute("pageVo", pageVo);

		return "lprod/lprodPagingList";
	}

}
