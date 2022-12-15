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
		final String url = "https://store.nintendo.co.kr/games?game_category=15%2C23%2C24%2C19%2C43&publisher=14";
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 타이틀
		Elements eles = doc.select("a.category-product-item-title-link");
		Iterator<Element> itr = eles.iterator();

		// 날짜
		Elements eles2 = doc.select("div.category-product-item-released");
		Iterator<Element> itr2 = eles2.iterator();

		// 가격
		Elements eles3 = doc.select("span.price");
		Iterator<Element> itr3 = eles3.iterator();

		System.out.println("\t로그: 크롤링 시작");
		while (itr.hasNext()) {
			GameVO gvo = new GameVO();
			// 타이틀 저장
			String title = itr.next().text();
			gvo.setTitle(title);

			// "발매" 떼고 공백 없애고 날짜 저장 "발매 22.11.22" 
			String date = itr2.next().text().replace("발매", "").trim(); // "22.11.22"
			int year = Integer.parseInt("20" + date.split("\\.")[0]); // 2022
			int month = Integer.parseInt(date.split("\\.")[1]); // 11
			int day = Integer.parseInt(date.split("\\.")[2]); // 22
			LocalDate gameDate = LocalDate.of(year, month, day); // LocalDate.of(2022, 11, 22)
			gvo.setDate(gameDate);

			// "₩", "," 떼고 가격 저장 "₩100,000"
			String price = itr3.next().text().replace(",", "").replace("₩", ""); // 100000
			gvo.setPrice(Integer.parseInt(price)); // 문자열 형변환

			// games에 추가
			// insert() App에서 사용하는 model의 DB에 넣으려고!
			gdao.addGame(gvo);
		}
		System.out.println("\t로그: 크롤링 끝");
	}

}
