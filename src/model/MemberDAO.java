package model;

import java.util.ArrayList;

public class MemberDAO {
	private ArrayList<MemberVO> members;

	public MemberDAO() {
		// 샘플 데이터
		members.add(new MemberVO("timo", "1234", "티모"));
		members.add(new MemberVO("admin", "1234", "관리자"));
	}

	// 로그인 R
	public MemberVO selectOne(MemberVO mvo) {
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getId().equals(mvo.getId())) {
				System.out.println("\t로그: 아이디 있음");
				if (members.get(i).getPw().equals(mvo.getPw())) {
					System.out.println("\t로그: 비밀번호 일치");
					System.out.println("\t로그: 로그인 성공");
					return members.get(i);
				}
				System.out.println("\t로그: 비밀번호 틀림");
			}
			System.out.println("\t로그: 아이디 없음");
		}
		System.out.println("\t로그: 로그인 실패");
		return null;
	}

	// ID 중복 검사
	public boolean checkId(MemberVO mvo) {
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getId().equals(mvo.getId())) {
				System.out.println("\t로그: 아이디 중복");
				return false;
			}
		}
		System.out.println("\t로그: 아이디 중복아님");
		return true;
	}

	// 회원가입 C
	public boolean insert(MemberVO mvo) {
		try {
			members.add(mvo);
		} catch (Exception e) {
			System.out.println("\t로그: 회원가입 실패");
			return false;
		}
		System.out.println("\t로그: 회원가입 성공");
		return true;
	}

	// 장바구니 추가 C? U?
	public boolean insert(MemberVO mvo, GameVO gvo) {
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getId().equals(mvo.getId())) {
				members.get(i).getCart().add(gvo);
				System.out.println("\t로그: 장바구니 추가 성공");
				return true;
			}
		}
		System.out.println("\t로그: 장바구니 추가 실패");
		return false;
	}

	// 장바구니 추가할 때
	// 장바구니 remove할 때
	// 파라미터로 받은 Member의 Cart에 일치하는 Game Number가 있는지 검색
//	public GameVO isNumExisting(MemberVO mvo, GameVO gvo) {
//		
//		for (int i = 0; i < mvo.getCart().size(); i++) {
//			if (mvo.getCart().get(i).getNum() == gvo.getNum()) {
//				System.out.println("\t로그: 장바구니에 있음");
//				return mvo.getCart().get(i);
//			}
//		}
//		System.out.println("\t로그: 장바구니에 없음");
//		return null;
//	}

	// 장바구니에 있는 게임들의 총 가격
	// 이거 view인지...?
	public int getTotalPrice(MemberVO mvo) {
		int total = 0;

		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getId().equals(mvo.getId())) {
				for (int j = 0; j < members.get(i).getCart().size(); j++) {
					total += mvo.getCart().get(j).getPrice();
				}
				System.out.println("\t로그: 총 가격 계산 성공");
				return total;
			}
		}
		System.out.println("\t로그: 장바구니 비어 있음");
		return 0;
	}

	// 게임 구매 가능 여부
	// 얘 model 맞는지?
	public boolean isMoneyEnough(MemberVO mvo, int totalPrice) {

		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getId().equals(mvo.getId())) {
				if (members.get(i).getMoney() < totalPrice) {
					System.out.println("\t로그: 보유금 부족");
					return false;
				}
			}
		}
		System.out.println("\t로그: 보유금 - 총 가격");
		return true;
	}

	// 구매하기 U? C? D?
	public boolean buyGame(MemberVO mvo, int totalPrice) {

		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getId().equals(mvo.getId())) {
				if (members.get(i).getMoney() >= totalPrice) {
					members.get(i).setMoney(members.get(i).getMoney() - totalPrice); // 보유금 - 총 가격
					for (int j = 0; j < members.get(i).getCart().size(); j++) {
						members.get(i).getLibrary().add(members.get(i).getCart().get(j)); // 라이브러리에 구매한 게임 추가
					}
					members.get(i).getCart().clear(); // 장바구니 비우기
					System.out.println("\t로그: 보유금 - 총 가격");
					return true;
				}
			}
		}
		System.out.println("\t로그: 보유금 부족");
		return false;
	}

	// 장바구니 비우기 D
	public boolean clearCart(MemberVO mvo) {
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getId().equals(mvo.getId())) {
				members.get(i).getCart().clear();
				return false;
			}
		}
		return true;
	}

	// 장바구니 게임 1개 삭제 D
	public boolean removeCart(MemberVO mvo, GameVO gvo) {
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getId().equals(mvo.getId())) {
				// remove(Object o) 이렇게 쓰는 게 맞는지?
				members.get(i).getCart().remove(gvo);
				return false;
			}
		}
		return true;
	}

	// 충전
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

	// 회원 정보 변경(비밀번호, 이름)

	// 회원 탈퇴

	// 장바구니 조회
	public ArrayList<GameVO> selectCart(MemberVO mvo) {
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getId().equals(mvo.getId())) {
				return mvo.getCart();
			}
		}
		return null;
	}

	// 라이브러리 조회
	public ArrayList<GameVO> selectLibrary(MemberVO mvo) {
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getId().equals(mvo.getId())) {
				return mvo.getLibrary();
			}
		}
		return null;
	}

}
