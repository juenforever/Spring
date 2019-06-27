package kr.or.ddit.lprod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.lprod.dao.ILprodDao;
import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.paging.model.PageVo;

@Service
public class LprodService implements ILprodService {

	@Resource(name = "lprodDao")
	private ILprodDao lprodDao;

	@Override
	public List<LprodVo> lprodList() {
		return lprodDao.lprodList();
	}

	@Override
	public LprodVo getLprod(String lprod_id) {
		return lprodDao.getLprod(lprod_id);
	}

	@Override
	public Map<String, Object> lprodPagingList(PageVo pageVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("lprodList", lprodDao.lprodPagingList(pageVo));
		int usersCnt = lprodDao.lprodCnt();

		int paginationSize = (int) Math.ceil((double) usersCnt / pageVo.getPageSize());
		resultMap.put("paginationSize", paginationSize);

		return resultMap;
	}

	@Override
	public int lprodCnt() {
		return lprodDao.lprodCnt();
	}

}
