package kr.or.ddit.user.dao;

import java.util.List;

import kr.or.ddit.user.model.UserVo;

public interface IUserDao {

	/**
	 * Method : userList 작성자 : PC01 변경이력 :
	 * 
	 * @return Method 설명 : 전체 사용자 조회
	 */
	List<UserVo> userList();

	/**
	* Method : getUser
	* 작성자 : PC01
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 정보 조회
	*/
	UserVo getUser(String userId);

	int usersCnt();

	int insertUser(UserVo userVo);

	int updateUser(UserVo userVo);

	int deleteUser(String userId);

}
