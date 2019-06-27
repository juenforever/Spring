package kr.or.ddit.lprod.dao;

import java.util.List;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.paging.model.PageVo;

public interface ILprodDao {

	/**
	* Method : lprodList
	* 작성자 : PC01
	* 변경이력 :
	* @return
	* Method 설명 : 전체 lprod 리스트 조회
	*/
	List<LprodVo> lprodList();

	/**
	* Method : getLprod
	* 작성자 : PC01
	* 변경이력 :
	* @param lprod_id
	* @return
	* Method 설명 : lprod 가져오기
	*/
	LprodVo getLprod(String lprod_id);

	/**
	* Method : lprodPagingList
	* 작성자 : PC01
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : lprod 페이지 리스트 조회
	*/
	List<LprodVo> lprodPagingList(PageVo pageVo);

	/**
	* Method : lprodCnt
	* 작성자 : PC01
	* 변경이력 :
	* @return
	* Method 설명 : 상품 전체수 조회
	*/
	int lprodCnt();

}
