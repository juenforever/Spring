package kr.or.ddit.prod.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import kr.or.ddit.prod.model.ProdVo;

public class ProdDao implements IProdDao {

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	@Override
	public List<ProdVo> prodList(String lprod_lgu) {
		return sqlSession.selectList("prod.getProdList", lprod_lgu);
	}

}
