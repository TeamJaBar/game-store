package model;

import java.util.ArrayList;

public class MemberVO {
	private String id; // PK
	private String pw;
	private String name;
	private int money;
	private ArrayList<GameVO> cart; // 장바구니
	private ArrayList<GameVO> library; // 라이브러리

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public ArrayList<GameVO> getCart() {
		return cart;
	}

	public void setCart(ArrayList<GameVO> cart) {
		this.cart = cart;
	}

	public ArrayList<GameVO> getLibrary() {
		return library;
	}

	public void setLibrary(ArrayList<GameVO> library) {
		this.library = library;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pw=" + pw + ", name=" + name + ", money=" + money + "]";
	}

	public MemberVO() {

	}

	public MemberVO(String id, String pw, String name) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.money = 0;
		this.cart = new ArrayList<GameVO>();
		this.library = new ArrayList<GameVO>();
	}

}
