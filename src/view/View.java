package view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import model.GameVO;
import model.MemberVO;

public class View {
	private int aAction;// 관리자 모드
	private int mAction;// 서비스 목록(member Menu)
	private int pAction;// 구매하기
	private int myAction;// 마이페이지
	private int sAction;// 회원가입,로그인,종료하기
	public int action;
	private Scanner sc;
	private LocalDate today;

	public View() {// 숫자는 쓰면서 수정
		sAction = 3;
		aAction = 2;
		mAction = 6;
		pAction = 4;
		myAction = 2;
		sc = new Scanner(System.in);
	}

	public void printStart() {// 첫 출력 화면
		while (true) {
			System.out.println("🎮 Welcome to Nintendo Store 🎮");
			System.out.println("1. 회원가입");// 아이디가 admin일때 관리자 모드
			System.out.println("2. 로그인");
			System.out.println("3. 종료");
			System.out.print("입력) ");
			try {
				action = sc.nextInt();
				Thread.sleep(3000);
			} catch (Exception e) {
				sc.nextLine();
				System.out.println("숫자를 입력해 주세요!");
				continue;
			}
			if (1 <= action && action <= sAction) {
				break;
			}
			System.out.println("1~3사이의 숫자를 입력해 주세요!");
		}
	}

	public MemberVO login() {// 로그인
		System.out.println("🎮 LOGIN 🎮");
		System.out.print("ID) ");
		String id = sc.next();
		System.out.print("PW) ");
		String pw = sc.next();
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPw(pw);
		return vo;
	}

	public void loginTrue(MemberVO mvo) {// 로그인 성공시
		System.out.println(mvo.getName() + ",님 환영합니다 :)");
	}

	public void loginFalse() {// 로그인 실패시
		System.out.println("   로그인 실패!");
		System.out.println("  ID or PW 다시 확인해 주세요!");
	}

	public void loginInfo() {// 아이디 중복
		System.out.println("이 아이디는 쓸 수 없습니다!");
		System.out.println("다른 아이디를 입력해 주세요!!");
	}

	public void logOut() {// 로그아웃 했을때
		System.out.println(" 🎮 LOGOUT 🎮 ");
	}

	public String getId() {// 회원가입 id
		System.out.print("ID) ");
		String id = sc.next();
		return id;
	}

	public String getPw() {// 회원가입 pw
		System.out.print("PW) ");
		String pw = sc.next();
		return pw;
	}

	public String getName() {// 회원가입 이름
		System.out.print("NAME) ");
		String name = sc.next();
		return name;
	}

	public void checkTure() {// 성공했을때
		System.out.println("Succed:D");
	}

	public void checkFalse() {// 실패했을때
		System.out.println("Fail");
	}

	public void powrOff() {// 프로그램 종료
		for (int i = 0; i <= 1; i++) {
			System.out.println("......");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("프로그램이 종료되었습니다!");
	}

	public void memberMenu() {// 서비스 메뉴
		while (true) {
			System.out.println("1. 출시예정 게임 목록");
			System.out.println("2. 전체 게임 목록");
			System.out.println("3. 구매하기");// ->purchMenu로 넘어가기
			System.out.println("4. 검색하기");
			System.out.println("5. 마이페이지");
			System.out.println("6. 로그아웃");
			try {
				action = sc.nextInt();
				Thread.sleep(1000);
			} catch (Exception e) {
				sc.nextLine();
				System.out.println("숫자를 입력해 주세요!");
				continue;
			}
			if (1 <= action && action <= mAction) {
				break;
			}
			System.out.println("1~6사이의 숫자를 입력해 주세요!");
		}
	}

	public void upcomingGame(ArrayList<GameVO> games) {// 출시예정 게임 목록
		for (int i = 0; i < games.size(); i++) {
			int num = games.get(i).getNum();// 번호
			String title = games.get(i).getTitle();// 타이틀
			LocalDate date = games.get(i).getDate();// 날짜
			String price = String.format("%,d", games.get(i).getPrice());// 가격
			System.out.println(num + ". " + title + " | 출시: " + date + " | 가격: " + price + "원");
		}
	}

	public void printGame(ArrayList<GameVO> games, MemberVO mvo) {// 전체게임목록(출시예정게임 포함)
		for (int i = 0; i < games.size(); i++) {
			int num = games.get(i).getNum();// 번호
			String title = games.get(i).getTitle();// 타이틀
			LocalDate date = games.get(i).getDate();// 날짜
			String price = String.format("%,d", games.get(i).getPrice());// 가격
			// 전체 목록(구매하기)
			// "num" 대신 "[출시 예정]"
			if (date.isAfter(today)) { // 출시일이 내일이후라면
				System.out.println("[출시 예정] " + title + " | 출시: " + date + " | 가격: " + price + "원");
			} else { // 출시일이 오늘 전이라면
				System.out.println(num + ". " + title + " | 출시: " + date + " | 가격: " + price + "원");
			}
			// 보유중인 게임이라면
			if (mvo.getCart().contains(games.get(i))) {
				System.out.println("[보유중] " + title + " | 출시: " + date + " | ");
			}
		}
	}

	public void purchaseMenu() {// 구매하기
		while (true) {
			System.out.println("======구매하기======");
			System.out.println("1. 장바구니 보기");
			System.out.println("2. 장바구니 추가");
			System.out.println("3. 전체삭제하기");
			System.out.println("4. 결제하기");
			System.out.print("입력) ");
			try {
				action = sc.nextInt();
				Thread.sleep(3000);
			} catch (Exception e) {
				sc.nextLine();
				System.out.println("숫자를 입력해주세요!");
				continue;
			}
			if (1 <= action && action <= pAction) {
				break;
			}
			System.out.println("1~4사이의 숫자를 입력해주세요!");
		}
	}

	public void cart(MemberVO mvo) {// 장바구니 보기
		ArrayList<GameVO> cart = mvo.getCart();
		if (cart.size() == 0) {
			System.out.println("장바구니가 비어있습니다!");
			return;
		}
		System.out.println("-----------------------------------");
		System.out.println("🛒 장바구니 🛒");
		for (int i = 0; i < cart.size(); i++) {
			System.out.println("[" + cart.get(i).getNum() + "] 상품 이름: " + cart.get(i).getTitle() + ", 상품 가격: "
					+ cart.get(i).getPrice() + "원");
		}
	}

	public GameVO addCart() {// 장바구니 추가
		System.out.print("추가할 게임번호 입력) ");
		int num = sc.nextInt();// PK로 입력받기
		GameVO vo = new GameVO();
		vo.setNum(num);
		return vo;
	}

	public boolean deleteGame(ArrayList<GameVO> cart) {// 장바구니 전체 삭제하기
		if (cart.size() == 0) {
			System.out.println("장바구니가 비어있습니다!!");
			return false;
		}
		System.out.println("정말로 삭제하겠습니까?");
		System.out.println("삭제를 원하시면 Y를 입력해 주세요.");
		String ans = sc.next();
		if (ans.equals("Y")) {
			System.out.println("삭제가 완료되었습니다.");
			return true;
		}
		System.out.println("삭제가 되지 않았습니다.제대로 입력해 주세요");
		return false;
	}

	public void printReceipt(ArrayList<GameVO> cart) {// 장바구니에 담긴 내역
		if (cart.size() == 0) {
			System.out.println("결제할 게임이 없습니다.");
			return;
		}
		System.out.println("🧾 영수증 🧾");
		for (GameVO v : cart) {
			System.out.println("[" + v.getNum() + "] 상품 이름: " + v.getTitle() + "," + v.getPrice() + "원");
		}
	}

	public void buyTrue() {// 결제 성공
		System.out.println("결제가 완료되었습니다.");
	}

	public void buyFalse() {// 결제 실패
		System.out.println("결제 실패! 다시 시도해 주세요!");
	}

	public String getTitle() {// 검색하기
		String title;
		System.out.print("검색할 게임 이름을 입력해주세요!: ");
		title = sc.next();
		return title;
	}

	public void myPageMenu() {// 마이페이지
		while (true) {
			try {
				System.out.println();
				System.out.println(" - 서비스 - ");
				System.out.println("1. 머니 충전");
				System.out.println("2. 라이브러리");
				System.out.print(" ");
				action = sc.nextInt();
			} catch (Exception e) {
				sc.nextLine();
				System.out.println("다시 입력해주세요!");
				continue;
			}
			if (1 <= action && action <= myAction) {
				break;
			}
			System.out.println("범위를 확인하고 다시 입력해주세요!");
		}
	}

	// 머니 충전
	public MemberVO chargeMoney() {
		int money;
		while (true) {
			System.out.print("얼마를 충전하실래요? ");
			try {
				money = sc.nextInt();
				if (money <= 0) {
					System.out.println("양수를 입력해주세요!");
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.println("숫자를 입력해 주세요!");
				sc.nextLine();
				continue;
			}
		}
		MemberVO vo = new MemberVO();
		vo.setMoney(money);
		return vo;
	}

	public void library(ArrayList<GameVO> library) {// 라이브러리
		System.out.println("보유 중인 게임을 보여드릴게요!");
		for (GameVO v : library) {
			System.out.println("[" + v.getNum() + "] " + v.getTitle());
		}
	}

	public MemberVO adminLogin() {// 관리자모드
		System.out.println();
		System.out.println(" - 관리자 로그인 - ");
		System.out.print("아이디: ");
		String id = sc.next();
		System.out.print("비밀번호: ");
		String pw = sc.next();
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPw(pw);
		return vo;
	}

	public void printAdminMenu() {
		while (true) {
			try {
				System.out.println();
				System.out.println(" - 관리자 모드 - ");
				System.out.println("1. 게임 삭제");
				System.out.println("2. 로그아웃");
				System.out.print(" ");
				action = sc.nextInt();
			} catch (Exception e) {
				sc.nextLine();
				System.out.println("정수로 다시 입력해주세요!");
				continue;
			}
			if (1 <= action && action <= aAction) {
				break;
			}
			System.out.println("범위를 확인하고 다시 입력해주세요!");
		}
	}

}