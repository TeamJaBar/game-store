package model;

import java.sql.Time;
import java.util.ArrayList;

public class GameDAO {
	int PK;
	ArrayList<GameVO> games;
	Time time;

	public GameDAO() {
		PK = 101;
		games = new ArrayList<GameVO>();
	}

	// 게임 추가(크롤링)
	public boolean insert(GameVO gvo) {
//		gvo.setNum(PK++);
//		games.add(gvo);

		GameVO game = new GameVO();
		game.setNum(PK++);
		game.setTitle(gvo.getTitle());
		game.setDate(gvo.getDate());
		game.setPrice(gvo.getPrice());
		games.add(game);
		return true;
	}

	// 게임 가격 변경(관리자)
	public boolean updateGame(GameVO gvo) {
		for (int i = 0; i < games.size(); i++) {
			if (games.get(i).getNum() == gvo.getNum()) {
				games.get(i).setPrice(gvo.getPrice());
				System.out.println("\t로그: 게임 정보 변경 성공");
				return true;
			}
		}
		System.out.println("\t로그: 게임 정보 변경 실패");
		return false;
	}

	// 전체 게임 목록
	public ArrayList<GameVO> selectAll(GameVO gvo) {
		return games;
	}

	// 신규 게임 목록(출시 예정)
	public ArrayList<GameVO> selectNew(GameVO gvo) {
		return games;
	}

}
