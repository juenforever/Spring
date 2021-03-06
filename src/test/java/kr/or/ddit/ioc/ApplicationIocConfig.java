package kr.or.ddit.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.service.BoardService;

@Configuration
public class ApplicationIocConfig {

	@Bean
	public IBoardDao boardDao() {
		return new BoardDao();
	}

	@Bean
	public BoardService boardService() {

		BoardService boardService = new BoardService();
		boardService.setBoardDao(boardDao());
		return boardService;
	}

}
