package kr.or.ddit.board.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.IBoardDao;

@Service
public class BoardService implements IBoardService {

	@Resource(name = "boardDao")
	private IBoardDao boardDao;

	public BoardService() {

	}

	public BoardService(IBoardDao boardDao) {
		this.boardDao = boardDao;
	}

	public void setBoardDao(IBoardDao boardDao) {
		this.boardDao = boardDao;
	}

	@Override
	public String sayHello() {
		return boardDao.sayHello();
	}

	@Override
	public IBoardDao getBoardDao() {
		// TODO Auto-generated method stub
		return boardDao;
	}

}
