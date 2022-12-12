package model;

public class GameVO {
	private int num;
	private String title;
	private String date;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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

	public GameVO() {

	}

	public GameVO(int num, String title, String date, int price) {
		this.num = num;
		this.title = title;
		this.date = date;
		this.price = price;
	}
}
