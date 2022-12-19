package view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import model.GameVO;
import model.MemberVO;

//장바구니랑 라이브러리 중복값 메서드만들기!
public class View {
	private int aAction;// 관리자 모드
	private int mAction;// 서비스 목록(member Menu)
	private int pAction;// 구매하기
	private int myAction;// 마이페이지
	private int sAction;// 회원가입,로그인,종료하기
	public int action;
	private Scanner sc;
	private LocalDate today;// 오늘 날짜

	// ver.1214-public boolean deleteGame(ArrayList<GameVO>cart)
	// ->public boolean deleteCart(ArrayList<GameVO>cart)로 바뀜
	// memberMenu 입력받는 print 추가
	// myPageMenu 입력받는 print 추가
	// public boolean checkGame(ArrayList<GameVO> games) 추가
	// public GameVO getDeleteNum() 추가
	// public MemberVO chargeMoney()->public int chargeMoney(MemberVO mvo)로 수정 및 총
	// 보유금액 출력
	// public void checkTure()->public void checkTrue() true 오타 수정
	// public GameVO getDeleteNum()->public GameVO
	// getDeleteNum(ArrayList<GameVO>games)
	// public int addCart(MemberVO mvo)->public int addCart(MemberVO
	// mvo,ArrayList<GameVO> games)로 변경
	// public void printGame(ArrayList<GameVO>)추가
	// public int chargeMoney(MemberVO mvo)->public MemberVO chargeMoney()로 변경
	// public void printChargeResult(MemberVO mvo) 추가
	// public void buyTrue()->public void buyTrue(MemberVO mvo, int totalPrice)변경
	// ver.1216 - public void signUp() 추가
	// - 회원가입시 비밀번호 이중체크 추가

	public View() {
		sAction = 3;
		aAction = 2;
		mAction = 6;
		pAction = 5;
		myAction = 3;
		sc = new Scanner(System.in);
	}

	public void printStart() {// 첫 출력 화면
		while (true) {
			System.out.println("\n🎮 Welcome to Nintendo Store 🎮\n");
			System.out.println("1. 회원가입");// 아이디가 admin일때 관리자 모드
			System.out.println("2. 로그인");
			System.out.println("3. 종료\n");
			System.out.print("입력: ");
			try {// 정수가 아닌 값을 입력했을 때: try-catch문
				action = sc.nextInt();
				Thread.sleep(250);
			} catch (Exception e) {
				sc.nextLine();
				System.out.println("숫자를 입력해 주세요!");
				continue;
			}
			if (1 <= action && action <= sAction) {
				break;
			}
			System.out.printf("  1~%d사이의 숫자를 입력해 주세요!\n", sAction);
		}
	}

	public MemberVO login() {// 로그인
		System.out.println("\n  🎮 LOGIN 🎮");
		System.out.print("ID: ");
		String id = sc.next();
		System.out.print("PW: ");
		String pw = sc.next();
		MemberVO vo = new MemberVO();
		vo.setId(id);// 입력한 id, pw보내주기
		vo.setPw(pw);
		return vo;
	}

	public void loginTrue(MemberVO mvo) {// 로그인 성공시
		System.out.println("\n  " + mvo.getName() + ",님 환영합니다! :)");
	}

	public void loginFalse() {// 로그인 실패시
		System.out.println("  로그인에 실패했어요..");
		System.out.println("  아이디나 비밀번호를 다시 확인해 주세요!");
	}

	public void logOut() {// 로그아웃 했을때
		System.out.print("\n  🎮 LOGOUT 🎮 \n  ");
		try {
			for (int i = 0; i < 3; i++) {
				System.out.print(" . ");
				Thread.sleep(100);
			}
			System.out.println("\n\n 로그아웃 되었습니다.\n");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void signUp() { // 회원가입 타이틀
		System.out.println("\n 🎮 SIGN UP 🎮 ");
	}

	public String getId() {// 회원가입 id
		System.out.print("\nID: ");
		String id = sc.next();
		return id;
	}

	public String getPw() {// 회원가입 pw
		String pw;
		while (true) {
			System.out.print("PW: ");
			pw = sc.next();
			System.out.print("PW 확인: ");
			String check = sc.next();
			if (check.equals(pw)) {
				break;
			}
			System.out.println("  일치하지 않습니다.. 다시 입력해주세요!\n");
		}
		return pw;
	}

	public String getName() {// 회원가입 이름
		System.out.print("NAME: ");
		String name = sc.next();
		return name;
	}

	public void idInfo() {// 아이디 중복
		System.out.println("  이 아이디는 이미 다른 분이 사용 중이에요!");
		System.out.println("  다른 아이디를 입력해 주세요!");
	}

	public void checkTrue() {// 성공했을때
		System.out.println("  성공입니다! :)");
	}

	public void checkFalse() {// 실패했을때
		System.out.println("  실패했어요...");
	}

	public void powrOff() {// 프로그램 종료
		for (int i = 0; i <= 1; i++) {
			System.out.println("\t...");
			try {// 유효성 검사
				Thread.sleep(500);// 0.5초 뒤에 출력
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("\n프로그램이 종료되었습니다. 다음에 또 만나요!");
	}

	public void memberMenu() {// 서비스 메뉴
		while (true) {
			System.out.println("\n  .... MENU ....");
			System.out.println("1. 출시예정 게임 목록");
			System.out.println("2. 전체 게임 목록");
			System.out.println("3. 구매하기");// ->purchMenu로 넘어가기
			System.out.println("4. 검색하기");
			System.out.println("5. 마이페이지");
			System.out.println("6. 로그아웃");
			try {// 유효성 검사
				System.out.print("\n입력: ");
				action = sc.nextInt();
			} catch (Exception e) {
				sc.nextLine();
				System.out.println("  숫자를 입력해 주세요!");
				continue;
			}
			if (1 <= action && action <= mAction) {// mAction=6일때 action이 1보다 크거나 같고 6보다 작거나 같을 때(유지보수)
				break;
			}
			System.out.printf("  1~%d사이의 숫자를 입력해 주세요!\n", mAction);
		}
	}

	public void upcomingGame(ArrayList<GameVO> games) {// 출시예정 게임 목록
		if (games.isEmpty()) {
			System.out.println("\n데이터가 없습니다...");
			return;
		}
		System.out.printf("\n%-4s  %-15s %-9s %-60s\n", "No.", "출시일", "가격 (￦)", "TITLE");
		System.out.println("--------------------------------------------------------------------------");
		for (int i = 0; i < games.size(); i++) {
			int num = games.get(i).getNum();// 번호
			String title = games.get(i).getTitle();// 타이틀
			LocalDate date = games.get(i).getDate();// 날짜
			String price = String.format("%,d", games.get(i).getPrice());// 가격
			System.out.printf("%-5s %-16s %-10s %-60s\n", num, date, price, title);
		}
	}

	public void gameisEmpty() {
		System.out.println("\n  게임이 없습니다...");
	}

	public void printGame(ArrayList<GameVO> games, MemberVO mvo) {// 전체게임목록(출시예정게임 포함)
		if (games.isEmpty()) {
			System.out.println("\n데이터가 없습니다...");
			return;
		}
		System.out.printf("\n%-8s  %-15s %-9s %-60s\n", "No.", "출시일", "가격 (￦)", "TITLE");
		System.out.println("--------------------------------------------------------------------------");
		for (int i = 0; i < games.size(); i++) {
			int num = games.get(i).getNum();// 번호
			String title = games.get(i).getTitle();// 타이틀
			LocalDate date = games.get(i).getDate();// 날짜
			String price = String.format("%,d", games.get(i).getPrice());// 가격
			// 전체 목록(구매하기)
			// "num" 대신 "[출시 예정]"
			today = LocalDate.now();
			if (date.isAfter(today)) { // 출시일이 내일이후라면
				System.out.printf("%-7s %-16s %-10s %-60s\n", "[출시예정] ", date, price, title);
				// System.out.println("[출시 예정] " + title + " | 출시: " + date + " | 가격: " + price
				// + "원");
			}
			// 보유중인 게임이라면
			else if (mvo.getLibrary().contains(games.get(i))) {
				System.out.printf("%-7s %-16s %-10s %-60s\n", "[보유중]  ", date, price, title);
				// System.out.println("[보유중] " + title + " | 출시: " + date + " | ");
			}
			// 장바구니에 이미 추가한 게임이라면
			else if (mvo.getCart().contains(games.get(i))) {
				System.out.printf("%-7s %-16s %-10s %-60s\n", "[장바구니]", date, price, title);
				// System.out.println("[장바구니] " + title + " | 출시: " + date + " | ");
			} else { // 출시일이 오늘 전이라면
				System.out.printf("%-8s %-16s %-10s %-60s\n", num + "     ", date, price, title);
				// System.out.println(num + ". " + title + " | 출시: " + date + " | 가격: " +
				// price+"￦￦" + "원");
			}
		}
	}

	// 전체게임목록오버로딩
	public void printGame(ArrayList<GameVO> games) {
		if (games.isEmpty()) {
			System.out.println("\n데이터가 없습니다...");
			return;
		}
		System.out.printf("\n%-4s  %-15s %-9s %-60s\n", "No.", "출시일", "가격 (￦)", "TITLE");
		System.out.println("--------------------------------------------------------------------------");
		for (int i = 0; i < games.size(); i++) {
			int num = games.get(i).getNum();// 번호
			String title = games.get(i).getTitle();// 타이틀
			LocalDate date = games.get(i).getDate();// 날짜
			String price = String.format("%,d", games.get(i).getPrice());// 가격

			System.out.printf("%-5s %-16s %-10s %-60s\n", num, date, price, title);
		}
	}

	public void purchaseMenu() {// 구매하기,(수정)
		while (true) {
			System.out.println("\n .... 구매하기 ....");
			System.out.println("1. 장바구니 보기");
			System.out.println("2. 장바구니 추가");
			System.out.println("3. 전체삭제하기");
			System.out.println("4. 결제하기");
			System.out.println("5. 돌아가기");
			System.out.print("\n입력: ");
			try {// 유효성 검사
				action = sc.nextInt();
			} catch (Exception e) {
				sc.nextLine();
				System.out.println("  숫자를 입력해주세요!");
				continue;
			}
			if (1 <= action && action <= pAction) {// pAction:5 일때 action은 1보다 크거나 같고 4보다 작거나 같을 떄 유효성검사)
				break;
			}
			System.out.printf("  1~%d사이의 숫자를 입력해주세요!\n", pAction);
		}
	}

	public void cart(MemberVO mvo) {// 장바구니 보기
		ArrayList<GameVO> cart = mvo.getCart();
		if (cart.size() == 0) {// cart에 담긴게 없을때
			System.out.println("  장바구니가 비어있습니다!");
			return;
		}
		System.out.println("\n.....................🛒 장바구니 🛒.....................\n");
		for (int i = 0; i < cart.size(); i++) {// 장바구니에 담겨져 있는 물품 출력하기
			System.out.printf(" [%d] %s : %,d원\n", cart.get(i).getNum(), cart.get(i).getTitle(),
					cart.get(i).getPrice());
		}
		System.out.println("\n.................................................... ");
	}

	public int addCart(MemberVO mvo) {// 장바구니 추가,(수정)
		int num;
		while (true) {
			try {
				System.out.println("게임을 추가하려면 게임 번호를");
				System.out.println("돌아가려면 0을 입력하세요..");
				System.out.print("\n입력: ");
				num = sc.nextInt();// PK로 입력받기
				break;
			} catch (Exception e) {
				System.out.println("  숫자를 입력해 주세요!");
				sc.nextLine();
				continue;
			}
		}
		return num;
	}

	public boolean deleteCart(ArrayList<GameVO> cart) {// 장바구니 전체 삭제하기(이름 변경)
		if (cart.size() == 0) {// 장바구니에 값이 없을 때
			System.out.println("  장바구니가 비어있습니다!");
			return false;
		}
		System.out.println("정말로 삭제하겠습니까? ");
		System.out.println("삭제를 원하시면 Y를 입력해 주세요.");
		String ans = sc.next();
		if (ans.equals("Y")) {// 삭제 더블 체크
			System.out.println("  Y를 입력하셨습니다..");
			return true;
		}
		System.out.println(" 삭제가 취소되었습니다.. 메뉴로 돌아갑니다.");
		return false;
	}

	public void printReceipt(ArrayList<GameVO> cart) {// 장바구니에 담긴 내역(수정)
		if (cart.size() == 0) {
			System.out.println("  결제할 게임이 없습니다..");
			return;
		}
		System.out.println("\n.....................🧾 주문서 🧾.....................\n");
		for (GameVO v : cart) {// 장바구니에 있는 상품 출력
			System.out.printf(" [%d] %s : %,d원\n", v.getNum(), v.getTitle(), v.getPrice());
		}
		System.out.println("\n....................................................\n ");
	}

	public void buyTrue(MemberVO mvo, int totalPrice) {// 결제 성공(남은금액,총 결제한 금액)
		System.out.println("결제가 완료되었습니다.");
		System.out.printf("총 결제액: %,d원\n", totalPrice);
		System.out.printf("결제 후 보유머니:  %,d원\n", mvo.getMoney());
	}

	public void buyFalse() {// 결제 실패
		System.out.println("  결제를 실패했습니다.. 메뉴로 돌아갑니다");
	}

	public String getTitle() {// 검색하기
		String title;
		System.out.print("검색할 게임 이름을 입력해주세요!: ");
		title = sc.next();
		return title;
	}

	public void myPageMenu() {// 마이페이지,돌아가기 추가
		while (true) {
			try {
				System.out.println();
				System.out.println("\n  🎮 MY PAGE 🎮  ");
				System.out.println("1. 머니 충전");
				System.out.println("2. 라이브러리");
				System.out.println("3. 돌아가기");
				System.out.print("\n입력: ");
				action = sc.nextInt();
			} catch (Exception e) {// 유효성 검사
				sc.nextLine();
				System.out.println("  숫자를 입력해 주세요!");
				continue;
			}
			if (1 <= action && action <= myAction) {// myAction=3일때 Action은 1보다 크거나 같고 2보다 작거나 같은 떄(유지보수)
				break;
			}
			System.out.printf("  1~%d사이의 숫자를 입력해주세요!\n", myAction);
		}
	}

	public MemberVO chargeMoney() {// 머니 충전 ,return 값, output 변경
		int money;
		while (true) {
			System.out.print("얼마를 충전하실래요? ");
			try {// 유효성 검사
				money = sc.nextInt();
				if (money <= 500) {
					System.out.println("  500원 이상부터 충전이 가능해요!");
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.println("  숫자를 입력해 주세요!");
				sc.nextLine();
				continue;
			}
		}
		MemberVO mvo = new MemberVO();
		mvo.setMoney(money);
		return mvo;
	}

	public void printChargeResult(MemberVO mvo) {// 보유하고 있는 총금액 출력(추가)
		System.out.println("\n충전이 완료되었습니다.");
		System.out.printf("현제 보유 금액: %,d원\n", mvo.getMoney());
	}

	public void library(ArrayList<GameVO> library) {// 라이브러리
		if (library.size() == 0) {
			System.out.println("  현재 보유 중인 게임이 없습니다..");
			return;
		}
		System.out.println("보유 중인 게임을 보여드릴게요!");
		System.out.println("\n................🎮 보유 게임 🎮................\n");
		for (GameVO v : library) {// 결제 완료한 사용자가 보유중인 게임 출력
			System.out.println(" [" + v.getNum() + "] " + v.getTitle());
		}
		System.out.println("\n...........................................\n");
	}

	// 관리자모드 메뉴
	public void printAdminMenu() {
		while (true) {
			try {// 유효성 검사
				System.out.println();
				System.out.println(" .... ADMIN MODE .... ");
				System.out.println("1. 게임 삭제");
				System.out.println("2. 로그아웃");
				System.out.print("\n입력: ");
				action = sc.nextInt();
			} catch (Exception e) {
				sc.nextLine();
				System.out.println("  정수로 다시 입력해주세요!");
				continue;
			}
			if (1 <= action && action <= aAction) {// aAction=2일 때 action이 1보다 크거나 같고 2보다 작거나 같을 때(유지보수)
				break;
			}
			System.out.printf("  1~%d사이의 숫자를 입력해 주세요!\n", aAction);
		}
	}

	public GameVO getDeleteNum() { // 관리자 모드 게임 삭제하기
		int num;
		while (true) {
			try {// 유효성 검사
				System.out.print("\n삭제할 게임의 번호를 입력해주세요!: ");
				num = sc.nextInt();
			} catch (Exception e) {
				sc.nextLine();
				System.out.println("  유효하지않은 입력입니다.");
				continue;
			}
			break;
		}
		GameVO gvo = new GameVO();
		gvo.setNum(num);
		return gvo;
	}

	public boolean checkGame(ArrayList<GameVO> games) {
		System.out.println("정말로 삭제하시겠습니까?");
		System.out.println("삭제를 원하시면 Y를 입력해 주세요.");
		String ans = sc.next();
		if (ans.equals("Y")) {
			System.out.println("  Y를 입력하셨습니다..");
			return true;
		}
		return false;
	}

}