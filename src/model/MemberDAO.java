package model;

import java.util.ArrayList;

public class MemberDAO {
	private ArrayList<MemberVO> members;

	public MemberDAO() {
		members = new ArrayList<MemberVO>();
		// 샘플 데이터 생성
		members.add(new MemberVO("timo", "1234", "티모"));
		members.add(new MemberVO("admin", "1234", "관리자"));
	}

	// C : 회원가입
	public boolean signUp(MemberVO mvo) {
		try {
			mvo.setCart(new ArrayList<GameVO>());
			mvo.setLibrary(new ArrayList<GameVO>());
			members.add(mvo); // 멤버 어레이리스트에 추가
		} catch (Exception e) {
			// System.out.println("\t로그: 회원가입 실패");
			return false;
		}
		// System.out.println("\t로그: 회원가입 성공");
		return true;
	}

	// ID 중복 검사
	public boolean checkId(MemberVO mvo) {
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getId().equals(mvo.getId())) { // 이미 있는 아이디인 경우
				// System.out.println("\t로그: 아이디 중복");
				return false;
			}
		}
		// System.out.println("\t로그: 아이디 중복 아님");
		return true;
	}

	// R : 로그인
	public MemberVO signIn(MemberVO mvo) {
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getId().equals(mvo.getId())) { // 아이디 체크
				// System.out.println("\t로그: 아이디 있음");
				if (members.get(i).getPw().equals(mvo.getPw())) { // 비밀번호 체크
					// System.out.println("\t로그: 로그인 성공");
					return members.get(i); // 해당 멤버 객체주소 리턴
				}
				// System.out.println("\t로그: 비밀번호 틀림");
			}
		}
		// System.out.println("\t로그: 로그인 실패");
		return null;
	}

	// C : 장바구니 추가
	public boolean addCart(MemberVO mvo, GameVO gvo) {
		try {
			mvo.getCart().add(gvo);
			// System.out.println("\t로그: 장바구니 추가 성공");
		} catch (Exception e) {
			// System.out.println("\t로그: 장바구니 추가 실패");
			return false;
		}
		return true;
	}

	// 장바구니에 있는 게임들의 총 가격
	public int getTotalPrice(MemberVO mvo) {
		if (mvo.getCart().size() == 0) { // 장바구니가 비어있다면
			// System.out.println("\t로그: 장바구니 비어있음");
			return 0;
		}

		int totalPrice = 0;
		for (int i = 0; i < mvo.getCart().size(); i++) {
			totalPrice += mvo.getCart().get(i).getPrice(); // 카트에 담긴 게임 가격 더하기
		}
		// System.out.println("\t로그: 총 가격 계산 성공");
		return totalPrice;
	}

	// U : 게임구매하기
	public boolean buyGame(MemberVO mvo) {
		int totalPrice = getTotalPrice(mvo); // 장바구니에 담긴 게임들의 가격 합
		if (mvo.getMoney() >= totalPrice) { // 구매 가능한지 체크
			mvo.setMoney(mvo.getMoney() - totalPrice);// 보유머니 수정
			for (int i = 0; i < mvo.getCart().size(); i++) {
				mvo.getLibrary().add(mvo.getCart().get(i)); // 라이브러리에 구매한 게임 추가
			}
			mvo.getCart().clear(); // 장바구니 비우기
			// System.out.println("\t로그: 보유금 - 총 가격");
			return true;
		}
		// System.out.println("\t로그: 보유금 부족"); //보유머니 부족할시 구매 실패
		return false;
	}

	// 장바구니 비우기
	public boolean clearCart(MemberVO mvo) {
		mvo.getCart().clear();
		return true;
	}

	// 머니 충전 U
	public boolean chargeMoney(MemberVO mvo) {
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getId().equals(mvo.getId())) {
				members.get(i).setMoney(members.get(i).getMoney() + mvo.getMoney()); // 원래 보유하고있던 머니에 새로 입력받은 머니 추가하기
				// System.out.println("\t로그: 머니 충전 성공");
				return true;
			}
		}
		// System.out.println("\t로그: 머니 충전 실패");
		return false;
	}

	// 라이브러리 조회 R
	public ArrayList<GameVO> selectLibrary(MemberVO mvo) {
		return mvo.getLibrary();
	}

	/*
	 * 컨트롤에서 gvo.setNum(view.getDeleteNum().getNum()) gvo =
	 * gameModel.selectOne(gvo); memberModel.deleteGameInCart(deleteGvo);
	 * gameModel.deleteGame(gvo);
	 */
	// 삭제된 게임이 장바구니에 담겨있을 경우 빼주기
	public boolean deleteGameInCart(GameVO gvo) {
		for (int i = 0; i < members.size(); i++) {
			ArrayList<GameVO> cart = members.get(i).getCart();
			if (cart.contains(gvo)) { // 만약 삭제한 게임이 장바구니에 있다면
				cart.remove(gvo); // 장바구니에서 제거
			}
		}
		return true;
	}
}
