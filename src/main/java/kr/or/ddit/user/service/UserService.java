package kr.or.ddit.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.model.UserVo;

@Service
public class UserService implements IUserService {

	@Resource(name = "userDao")
	private IUserDao userDao;

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

}
