package kr.or.ddit.user.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.UserVo;

@Repository
public class UserDao implements IUserDao {

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	/**
	 * Method : userList 
	 * 작성자 :
	 *  PC01 변경이력 :
	 * 
	 * @return 
	 * Method 설명 : 전체 사용자 조회
	 */
	@Override
	public List<UserVo> userList() {
		return sqlSession.selectList("user.userList");
	}

	/**
	* Method : getUser
	* 작성자 : PC01
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 정보 조회
	*/
	@Override
	public UserVo getUser(String userId) {
		return sqlSession.selectOne("user.getUser", userId);

	}

	@Override
	public int usersCnt() {
		return sqlSession.selectOne("user.usersCnt");
	}

	@Override
	public int insertUser(UserVo userVo) {
		return sqlSession.insert("user.insertUser", userVo);
	}

	@Override
	public int updateUser(UserVo userVo) {
		// TODO Auto-generated method stub
		return sqlSession.update("user.updateUser", userVo);
	}

	@Override
	public int deleteUser(String userId) {
		// TODO Auto-generated method stub
		return sqlSession.update("user.deleteUser", userId);
	}

}
