package view;

import java.time.LocalDate;
import java.util.ArrayList;

import model.GameVO;

public class View {
	LocalDate today;

	public View() {
		today = LocalDate.now();
	}

	// games 출력
	public void printGames(ArrayList<GameVO> games) {
		for (int i = 0; i < games.size(); i++) {
			// 번호
			int num = games.get(i).getNum();

			// 타이틀
			String title = games.get(i).getTitle();

			// 날짜
			int year = Integer.parseInt("20" + games.get(i).getDate().split("\\.")[0]);
			int month = Integer.parseInt(games.get(i).getDate().split("\\.")[1]);
			int day = Integer.parseInt(games.get(i).getDate().split("\\.")[2]);
			LocalDate gameDate = LocalDate.of(year, month, day);

			// 가격
			String price = String.format("%,d", games.get(i).getPrice());

			// 이미 보유 중
//			for (int j = 0; j < mvo.getLibrary().size(); j++) {
//				if (games.get(i).getNum() == mvo.getLibrary().get(j).getNum()) {
//					System.out.println("[보유] " + title + " | 출시: " + gameDate + " | 가격: " + price + "원");
//				}
//			}

			// 전체 목록(구매하기)
			// "num" 대신 "[출시 예정]"
			if (gameDate.isAfter(today)) { // 출시일이 내일이후라면
				System.out.println("[출시 예정] " + title + " | 출시: " + gameDate + " | 가격: " + price + "원");
			} else { // 출시일이 오늘 전이라면
				System.out.println(num + ". " + title + " | 출시: " + gameDate + " | 가격: " + price + "원");
			}
		}
	}

	public static void main(String[] args) {

	}

}
