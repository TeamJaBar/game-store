package model;

import java.time.LocalDate;

public class GameVO {
	private int num;
	private String title;
	private LocalDate date;
	private int price;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "GameVO [num=" + num + ", title=" + title + ", date=" + date + ", price=" + price + "]";
	}
}
