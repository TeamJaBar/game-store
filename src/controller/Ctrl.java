package controller;

import java.util.ArrayList;

import model.Crawling;
import model.GameDAO;
import model.GameVO;
import model.MemberDAO;
import model.MemberVO;
import view.View;

// Ver 1.1 1213_초기코드 작성완료
// Ver 1.2 1214_주석추가, 장바구니 추가 내용 변경, 전체삭제 수정, 출시예정게임목록 수정
// Ver 1.3 1214_결재하기 수정
// Ver 1.4 1215_전체삭제, 장바구니 추가, 게임삭제 수정, checkId 추가, 게임삭제 로직 변경, 회원가입 코드수정
// Ver 1.5 1216_구매하기, 마이페이지 돌아가기 로직 추가, view.printChargeResult 추가, printGame(ArrayList<GameVO> games) 추가
// view.getDeleteNum(ArrayList<GameVO>games)  

public class Ctrl {
	MemberDAO memberModel;
	GameDAO gameModel;
	View view;

	public Ctrl() {
		memberModel = new MemberDAO();
		gameModel = new GameDAO();
		view = new View();
	}

	// 3. 관리자UI 출력(게임삭제, 로그아웃)▼--------------------------------
	public void adminAction() {
		while (true) {
			view.printAdminMenu();
			// 3.1 게임삭제
			if (view.action == 1) {
				ArrayList<GameVO> games = gameModel.selectAll(null);
				if (games.isEmpty()) { // 게임 데이터가 없다면
					view.gameisEmpty();
					continue;
				}

				view.printGame(games);
				GameVO gvo = view.getDeleteNum();
				gvo = gameModel.selectOne(gvo);
				if (gvo == null) {
					view.checkFalse();
					continue;
				}
				if (view.checkGame(null)) {
					if (gameModel.deleteGame(gvo)) {
						memberModel.deleteGameInCart(gvo);
						view.checkTrue();
						continue;
					}
				}
				view.checkFalse();
				// 3.2 로그아웃
			} else if (view.action == 2) {
				view.logOut();
				break;
			}
		}
	}

	// 2. 유저UI 출력(출시예정게임목록, 전체게임목록, 구매하기, 검색하기, 마이페이지, 로그아웃)▼--------
	public void userAction(MemberVO mvo) {
		while (true) {
			view.memberMenu();
			// 2.1 출시예정게임목록
			if (view.action == 1) {
				view.upcomingGame(gameModel.selectUpComming(null));
				// 2.2 전체게임목록
			} else if (view.action == 2) {
				view.printGame(gameModel.selectAll(null));
				// 2.3 구매하기
			} else if (view.action == 3) {
				while (true) {
					view.purchaseMenu();
					// 2.3.1 장바구니 보기
					if (view.action == 1) {
						view.cart(mvo);
						// 2.3.2 장바구니 추가
					} else if (view.action == 2) {
						ArrayList<GameVO> games = gameModel.selectAll(null);
						if (games.isEmpty()) { // 게임 데이터가 없다면
							view.gameisEmpty();
							continue;
						}
						// 게임데이터가 있다면
						view.printGame(games, mvo);
						GameVO vo = new GameVO();
						vo.setNum(view.addCart(mvo));
						if (gameModel.selectOne(vo) != null) {
							memberModel.addCart(mvo, gameModel.selectOne(vo));
							view.checkTrue();
							continue;
						}
						view.checkFalse();
						// 2.3.3 전체삭제
					} else if (view.action == 3) {
						if (view.deleteCart(mvo.getCart())) {
							if (memberModel.clearCart(mvo)) {
								view.checkTrue();
								continue;
							}
							view.checkFalse();
						}
						// 2.3.4 결제하기
					} else if (view.action == 4) {
						view.printReceipt(mvo.getCart());
						int totalPrice = memberModel.getTotalPrice(mvo);
						if (totalPrice != 0) {
							if (memberModel.buyGame(mvo)) {
								view.buyTrue(mvo, totalPrice);
								continue;
							}
						}
						view.buyFalse();
					} else if (view.action == 5) {
						break;
					}
				}
				// 2.4 검색하기
			} else if (view.action == 4) {
				GameVO gvo = new GameVO();
				gvo.setTitle(view.getTitle());
				view.printGame(gameModel.selectAll(gvo), mvo);
				// 2.5 마이페이지
			} else if (view.action == 5) {
				while (true) {
					view.myPageMenu();
					// 2.5.1 머니충전
					if (view.action == 1) {
						MemberVO vo = view.chargeMoney();
						vo.setId(mvo.getId());
						if (memberModel.chargeMoney(vo)) {
							view.printChargeResult(mvo);
							continue;
						}
						view.checkFalse();
						// 2.5.2 라이브러리
					} else if (view.action == 2) {
						view.library(memberModel.selectLibrary(mvo));
					} else if (view.action == 3) {
						break;
					}
				}
				// 2.6 로그아웃
			} else if (view.action == 6) {
				view.logOut();
				break;
			}
		}

	}

	// 1. 시작UI 출력(로그인, 회원가입,종료하기)▼------------------------------------------
	public void startApp() {
		// 크롤링 데이터 gmaeModel에 추가
		Crawling.sample(gameModel);
		while (true) {
			view.printStart();
			// 1.1 회원가입
			if (view.action == 1) {
				MemberVO mvo = new MemberVO();
				view.signUp();
				while (true) {
					mvo.setId(view.getId());
					if (!memberModel.checkId(mvo)) {
						view.idInfo();
						continue;
					}
					break;
				}
				mvo.setPw(view.getPw());
				mvo.setName(view.getName());
				if (memberModel.signUp(mvo)) {
					view.checkTrue();
					continue;
				}
				view.checkFalse();
				// 1.2 로그인
			} else if (view.action == 2) {
				MemberVO mvo = view.login();
				mvo = memberModel.signIn(mvo);
				if (mvo == null) {
					view.loginFalse();
					continue;
				}
				view.loginTrue(mvo);
				if (mvo.getId().equals("admin")) {
					// 1.2.1 관리자 메뉴 실행
					adminAction();
				} else {
					// 1.2.2 유저 메뉴 실행
					userAction(mvo);
				}
				// 1.3 종료하기
			} else if (view.action == 3) {
				view.powrOff();
				break;
			}
		}
	}
}