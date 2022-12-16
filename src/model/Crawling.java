package model;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawling {
	public void sample(GameDAO gdao) { // gdao: App에서 사용하는 model
		// 타겟 사이트
		final String URL = "https://store.nintendo.co.kr/games?game_category=43%2C15%2C19%2C23&publisher=14";
		Document doc = null;
		try {
			doc = Jsoup.connect(URL).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 타이틀
		Elements eles = doc.select("a.category-product-item-title-link");
		Iterator<Element> itr = eles.iterator();

		// 출시일
		Elements eles2 = doc.select("div.category-product-item-released");
		Iterator<Element> itr2 = eles2.iterator();

		// 가격
		Elements eles3 = doc.select("span.price");
		Iterator<Element> itr3 = eles3.iterator();

		System.out.println("\t로그: 크롤링 시작");
		int i = 0;
		while (itr.hasNext()) {
			if (i > 2)	break;
			GameVO gvo = new GameVO();

			// 타이틀 저장
			String title = itr.next().text();
			gvo.setTitle(title);

			// ex) "발매 22.11.22"
			// "발매", " " > ""
			// 발매와 공백 ""로 대체 후 발매일 저장
			String date = itr2.next().text().replace("발매", "").trim(); // "22.11.22"
			// 4자리의 연도로 초기화하기 위해 "20" 연결
			int year = Integer.parseInt("20" + date.split("\\.")[0]); // 연도: 2022
			int month = Integer.parseInt(date.split("\\.")[1]); // 월: 11
			int day = Integer.parseInt(date.split("\\.")[2]); // 일: 22
			LocalDate gameDate = LocalDate.of(year, month, day); // LocalDate.of(2022, 11, 22)
			gvo.setDate(gameDate);

			// ex) "₩100,000"
			// "₩", "," > ""
			// 원 기호와 쉼표 ""로 대체 후 가격 저장
			String price = itr3.next().text().replace("₩", "").replace(",", ""); // "100000"
			gvo.setPrice(Integer.parseInt(price)); // 문자열 형변환
			
			// games에 추가
			gdao.addGame(gvo);
			i++;
		}

		System.out.println("\t로그: 크롤링 끝");
	}

}
