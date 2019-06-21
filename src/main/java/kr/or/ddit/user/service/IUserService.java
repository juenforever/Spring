package kr.or.ddit.user.service;

import java.util.List;

import kr.or.ddit.user.model.UserVo;

public interface IUserService {

	/**
	 * Method : userList 작성자 : PC01 변경이력 :
	 * 
	 * @return Method 설명 : 사용자 전체 리스트 조회
	 */
	List<UserVo> userList();

	UserVo getUser(String userId);

	int usersCnt();

	int insertUser(UserVo userVo);

	int deleteUser(String userId);

	int updateUser(UserVo userVo);

}
