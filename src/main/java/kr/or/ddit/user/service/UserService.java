package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.model.UserVo;

@Service
public class UserService implements IUserService {

	@Resource(name = "userDao")
	private IUserDao userDao;

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	/**
	 * Method : userList 작성자 : PC01 변경이력 :
	 * 
	 * @return Method 설명 : 사용자 전체 리스트 조회
	 */

	@Override
	public List<UserVo> userList() {

		return userDao.userList();
	}

	@Override
	public UserVo getUser(String userId) {
		return userDao.getUser(userId);
	}

	@Override
	public int usersCnt() {
		return userDao.usersCnt();
	}

	@Override
	public int insertUser(UserVo userVo) {
		return userDao.insertUser(userVo);
	}

	@Override
	public int deleteUser(String userId) {
		return userDao.deleteUser(userId);
	}

	@Override
	public int updateUser(UserVo userVo) {
		return userDao.updateUser(userVo);
	}

	@Override
	public Map<String, Object> userPagingList(PageVo pageVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("userList", userDao.userPagingList(pageVo));
		int usersCnt = userDao.usersCnt();
		
		int paginationSize = (int)Math.ceil((double)usersCnt/pageVo.getPageSize());
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
	}

	@Override
	public int encryptPassAllUser() {
		// 사용하지 마세요 이미 암호화 적용되었습니다.
		if (1 == 1)
			return 0;
		// 0.sql 실행에 필요한 sqlSession 객체를 생성

		// 1.모든 사용자 조회 (단, 기존 암호화 적용 사용자 조회)
		List<UserVo> userList = userDao.userListForPassEncrypt(sqlSession);

		// 2. 조회된 사용자의 비밀번호를 암호화 적용후 사용자 업데이트
		int updateCntSum = 0;
		for (UserVo userVo : userList) {
			String encryptPass = KISA_SHA256.encrypt(userVo.getPass());
			userVo.setPass(encryptPass);

			int updateCnt = userDao.updateUserEncryptPass(sqlSession, userVo);
			updateCntSum += updateCnt;

			// 비정상
			if (updateCnt != 1) {
				sqlSession.rollback();
				break;
			}
		}
		// 3. sqlSession객체를 commit, close
		sqlSession.commit();
		sqlSession.close();

		return updateCntSum;
	}

}
