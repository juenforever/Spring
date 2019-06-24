package kr.or.ddit.user.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.ParseException;

import kr.or.ddit.testEnv.LogicTestEnv;
import kr.or.ddit.user.model.UserVo;

public class UserDaoTest extends LogicTestEnv {
	private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

	@Resource(name = "userDao")
	private IUserDao userDao;

	@Test
	public void userListTest1() {
		/*** Given ***/

		/*** When ***/
		List<UserVo> userList = userDao.userList();
		logger.debug(userList.get(0).getUserId());

		/*** Then ***/
		assertNotNull(userList);
		assertTrue(userList.size() >= 100);
		assertEquals(130, userList.size());
	}

	/**
	 * Method : userListTest 작성자 : PC01 변경이력 : Method 설명 : 사용자 전체 조회 테스트
	 */
	@Test
	public void userListTest() {

		/*** Given ***/

		/*** When ***/
		List<UserVo> userList = userDao.userList();
		/*** Then ***/
		// assertEquals("brown", userList.get(0).getUserId());
		// assertEquals(5, userList.size());
		logger.debug("userList : {}", userList);

	}

	/**
	 * Method : getUserTest 작성자 : PC01 변경이력 : Method 설명 : 사용자 정보 조회 테스트
	 */
	@Test
	public void getUserTest() {
		/*** Given ***/
		String userId = "brown";
		/*** When ***/
		UserVo userVo = userDao.getUser(userId);
		/*** Then ***/
		assertEquals("브라운", userVo.getName());
		logger.debug("userVo : {}", userVo);

	}

	/**
	 * Method : userPagingListTest 작성자 : PC01 변경이력 : Method 설명 : 사용자 페이징 리스트 조회 테스트
	 */
//	@Test
//	public void userPagingListTest() {
//		/*** Given ***/
//		PageVo pageVo = new PageVo(1, 10);
//
//		/*** When ***/
//		List<UserVo> userList = userDao.userPagingList(pageVo);
//
//		/*** Then ***/
//		assertNotNull(userList);
//		assertEquals(10, userList.size());
//
//	}

	/**
	 * Method : usersCntTest 작성자 : PC01 변경이력 : Method 설명 : 사용자 전체수 조회 테스트
	 */

	@Test
	public void usersCntTest() {
		/*** Given ***/

		/*** When ***/
		int usersCnt = userDao.usersCnt();
		/*** Then ***/
		assertEquals(130, usersCnt);
	}

	/**
	 * Method : insertUserTest 작성자 : PC01 변경이력 : Method 설명 : 사용자 등록 테스트
	 * 
	 * @throws java.text.ParseException
	 */
	@Test
	public void insertUserTest() throws java.text.ParseException {
		/*** Given ***/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 사용자 정보를 담고 있는 vo 객체 준비
		UserVo userVo = null;
		try {
			userVo = new UserVo("a3a33a", "userTest", "중앙로", "userTest1234", "대전광역시 중구 중앙로 76", "영민빌딩 2층 204호", "34940",
					sdf.parse("2019-05-05"), "asdf", "asdf");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*** When ***/
		// userDao.insertUser()
		int insertCnt = userDao.insertUser(userVo);
		/*** Then ***/
		assertEquals(1, insertCnt);
		// insertCnt(1)
		userDao.deleteUser(userVo.getUserId());

	}

	@Test
	public void updateUserTest() throws java.text.ParseException {
		/*** Given ***/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 사용자 정보를 담고 있는 vo 객체 준비
		UserVo userVo = null;
		try {
			userVo = new UserVo("cony", "userTest", "중앙로", "userTest1234", "대전광역시 중구 중앙로 76", "영민빌딩 2층 204호", "34940",
					sdf.parse("2019-05-05"), null, null);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*** When ***/
		// userDao.insertUser()
		logger.debug("userVo : {}", userVo);
		int updateCnt = userDao.updateUser(userVo);
		/*** Then ***/
		assertEquals(1, updateCnt);
		// insertCnt(1)

	}

}
