package ctrl;

import java.util.ArrayList;

import model.Crawling;
import model.GameDAO;
import model.GameVO;
import view.View;

public class Ctrl {
	GameDAO model;

	public Ctrl() {
		model = new GameDAO(); // GameDAO 초기화 > PK = 101, ArrayList<GameVO> 선언
		Crawling crawling = new Crawling(); // Crawling 객체화
		crawling.sample(model); // App에서 사용하는 model을 사용!
		// sample(GameDAO) 메서드 호출
	}

	public void startApp() {
		ArrayList<GameVO> games = model.selectAll(null);

		View view = new View();

		// 전체 게임 목록 출력
	}

}
