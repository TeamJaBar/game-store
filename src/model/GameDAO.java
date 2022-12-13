package model;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

public class GameDAO {
	private int PK;
	ArrayList<GameVO> games;
	Time time;

	public GameDAO() {
		PK = 101;
		games = new ArrayList<GameVO>();
	}

	// 게임 추가(크롤링) C
	public boolean addGame(GameVO gvo) {
//		GameVO game = new GameVO();
//		game.setNum(PK++);
//		game.setTitle(gvo.getTitle());
//		game.setDate(gvo.getDate());
//		game.setPrice(gvo.getPrice());
//		games.add(game);

		try {
			gvo.setNum(PK++);
			games.add(gvo);
			System.out.println("\t로그 : 게임 추가 성공");
		} catch (Exception e) {
			System.out.println("\t로그 : 게임 추가 실패");
			return false;
		}
		return true;
	}

   // R : 전체 목록, 검색
   public ArrayList<GameVO> selectAll(GameVO gvo) {
      if (gvo == null) {
         return games; // 전체 목록 넘기기
      } 
      
      //검색 결과 데이터 넘기기
      ArrayList<GameVO> res = new ArrayList<GameVO>();
      for (GameVO g : games) { // games를 순회하며
         if (g.getTitle().contains(gvo.getTitle())) { // 입력한 단어가 포함된 타이틀명이 있다면
            res.add(g); // 추가
         }
      }
      return res;
   }

	// R : 출시예정인 게임
	public ArrayList<GameVO> selectUpComming(GameVO gvo) {
		// null이 아니라면 출시 예정 게임 목록 데이터 넘기기
		LocalDate today = LocalDate.now();
		ArrayList<GameVO> res = new ArrayList<GameVO>();

		for (GameVO g : games) {
			if (g.getDate().isAfter(today)) { // 발매일이 오늘 이후인 객체
				res.add(g);
			}
		}
		return res;
	}

	// D : 게임 삭제
	public boolean deleteGame(GameVO gvo) {
		for (int i = 0; i < games.size(); i++) {
			if (games.get(i).getNum() == gvo.getNum()) {
				games.remove(i);
				System.out.println("\t로그: 게임 삭제 성공");
				return true;
			}
		}
		System.out.println("\t로그: 게임 삭제 실패");
		return false;
	}

}
