package kr.or.ddit.lprod.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.paging.model.PageVo;

public interface ILprodService {

	List<LprodVo> lprodList();

	LprodVo getLprod(String lprod_id);

	Map<String, Object> lprodPagingList(PageVo pageVo);

	int lprodCnt();

}
