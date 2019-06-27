package kr.or.ddit.lprod.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.paging.model.PageVo;

@Repository
public class LprodDao implements ILprodDao {

	private static final Logger logger = LoggerFactory.getLogger(LprodDao.class);

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	/**
	 * Method : lprodList 작성자 : PC01 변경이력 :
	 * 
	 * @return Method 설명 : 전체 lprod 리스트 조회
	 */
	@Override
	public List<LprodVo> lprodList() {
		logger.debug("lprodList");
		return sqlSession.selectList("lprod.lprodList");

	}

	/**
	 * Method : getLprod 작성자 : PC01 변경이력 :
	 * 
	 * @param lprod_id
	 * @return Method 설명 : lprod 가져오기
	 */
	@Override
	public LprodVo getLprod(String lprod_id) {

		return sqlSession.selectOne("lprod.getLprod", lprod_id);

	}

	/**
	 * Method : lprodPagingList 작성자 : PC01 변경이력 :
	 * 
	 * @param pageVo
	 * @return Method 설명 : lprod 페이지 리스트 조회
	 */
	@Override
	public List<LprodVo> lprodPagingList(PageVo pageVo) {

		return sqlSession.selectList("lprod.lprodPagingList", pageVo);

	}

	/**
	 * Method : lprodCnt 작성자 : PC01 변경이력 :
	 * 
	 * @return Method 설명 : 상품 전체수 조회
	 */
	@Override
	public int lprodCnt() {

		return sqlSession.selectOne("lprod.lprodCnt");

	}

}
