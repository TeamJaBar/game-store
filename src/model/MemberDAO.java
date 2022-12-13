package model;

import java.util.ArrayList;

public class MemberDAO {
	private ArrayList<MemberVO> members;

	public MemberDAO() {
		// 샘플 데이터
		members.add(new MemberVO("timo", "1234", "티모"));
		members.add(new MemberVO("admin", "1234", "관리자"));
	}

	// 회원가입 C
	public boolean signUp(MemberVO mvo) {
		try {
			mvo.setCart(new ArrayList<GameVO>());
			mvo.setLibrary(new ArrayList<GameVO>());
			members.add(mvo);
		} catch (Exception e) {
			System.out.println("\t로그: 회원가입 실패");
			return false;
		}
		System.out.println("\t로그: 회원가입 성공");
		return true;
	}

	// ID 중복 검사
	public boolean checkId(MemberVO mvo) {
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getId().equals(mvo.getId())) {
				System.out.println("\t로그: 아이디 중복");
				return false;
			}
		}
		System.out.println("\t로그: 아이디 중복 아님");
		return true;
	}

	// 로그인 R
	public MemberVO signIn(MemberVO mvo) {
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getId().equals(mvo.getId())) {
				System.out.println("\t로그: 아이디 있음");
				if (members.get(i).getPw().equals(mvo.getPw())) {
					System.out.println("\t로그: 로그인 성공");
					return members.get(i);
				}
				System.out.println("\t로그: 비밀번호 틀림");
			}
		}
		System.out.println("\t로그: 로그인 실패");
		return null;
	}

	// 장바구니 보기 R
	public ArrayList<GameVO> selectCart(MemberVO mvo) {
		return mvo.getCart();
	}

	// 장바구니 추가 C (오버로딩)
	public boolean addCart(MemberVO mvo, GameVO gvo) {
		try {
			mvo.getCart().add(gvo);
			System.out.println("\t로그: 장바구니 추가 성공");
		} catch (Exception e) {
			System.out.println("\t로그: 장바구니 추가 실패");
			return false;
		}
		return true;
	}

	// 장바구니에 있는 게임들의 총 가격
	public int getTotalPrice(MemberVO mvo) {
		if (mvo.getCart().size() == 0) {
			System.out.println("\t로그: 장바구니 비어있음");
			return 0;
		}

		int totalPrice = 0;
		for (int i = 0; i < mvo.getCart().size(); i++) {
			totalPrice += mvo.getCart().get(i).getPrice();
		}
		System.out.println("\t로그: 총 가격 계산 성공");
		return totalPrice;
	}

	// 게임 구매 가능 여부
	public boolean isMoneyEnough(MemberVO mvo) {
		int totalPrice = getTotalPrice(mvo);
		if (mvo.getMoney() < totalPrice) {
			System.out.println("\t로그: 보유금 부족");
			return false;
		}
		System.out.println("\t로그: 보유금 - 총 가격");
		return true;
	}

	// 구매하기 U
	public boolean buyGame(MemberVO mvo) {
		int totalPrice = getTotalPrice(mvo);
		if (mvo.getMoney() >= totalPrice) {
			mvo.setMoney(mvo.getMoney() - totalPrice); // 보유금 - 총 가격
			for (int i = 0; i < mvo.getCart().size(); i++) {
				mvo.getLibrary().add(mvo.getCart().get(i)); // 라이브러리에 구매한 게임 추가
			}
			mvo.getCart().clear(); // 장바구니 비우기
			System.out.println("\t로그: 보유금 - 총 가격");
			return true;
		}
		System.out.println("\t로그: 보유금 부족");
		return false;
	}

	// 장바구니 비우기 D
	public boolean clearCart(MemberVO mvo) {
		mvo.getCart().clear();
		return true;
	}

	// 장바구니 게임 1개 삭제 D
//	public boolean removeCart(MemberVO mvo, GameVO gvo) {
//		members.get(i).getCart().remove(gvo);
//		return true;
//	}

	// 머니 충전 U
	public boolean chargeMoney(MemberVO mvo) {
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getId().equals(mvo.getId())) {
				members.get(i).setMoney(members.get(i).getMoney() + mvo.getMoney());
				System.out.println("\t로그: 머니 충전 성공");
				return true;
			}
		}
		System.out.println("\t로그: 머니 충전 실패");
		return false;
	}

	// 라이브러리 조회 R
	public ArrayList<GameVO> selectLibrary(MemberVO mvo) {
		return mvo.getLibrary();
	}

}
