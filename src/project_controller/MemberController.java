package project_controller;

import java.util.Scanner;

import project.Container;
import project_dto.Member;
import project_service.MemberService;
import project_util.Util;

public class MemberController extends Controller {
	private Scanner sc;
	private String command;
	private String actionMethodName;
	private MemberService memberService;

	public MemberController(Scanner sc) {
		this.sc = sc;

		memberService = Container.memberService;

	}

	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		case "modify":
			doModify();
			break;
		case "withdraw":
			doWithdraw();
			break;
		default:
			System.out.println("존재하지 않는 명령어입니다.");
			break;
		}
	}

	private void doLogin() {
		String loginId;
		String loginPw;
		Member member;

		int loginCount = 0;
		int loginMaxCount = 3; // 로그인 시도가 3번 실패하면(3번 초과하면) 처음으로 돌아가기

		while (true) {
			loginCount++;

			if (loginCount > loginMaxCount) {
				System.out.println("로그인을 3회 실패하셨습니다. 처음부터 다시 실행해주세요.");
				return;
			}

			System.out.print("아이디 : ");
			loginId = sc.nextLine().trim();
			System.out.print("비밀번호 : ");
			loginPw = sc.nextLine().trim();

			member = memberService.getMemberByLoginId(loginId);

			if (member == null) {
				System.out.printf("%s 계정은 존재하지 않습니다.\n", loginId);
				continue;
			}

			if (member.loginPw.equals(loginPw) == false) {
				System.out.println("비밀번호가 일치하지 않습니다");
				continue;
			}

			loginedMember = member;
			break;
		}

		System.out.printf("%s님 환영합니다.\n", loginedMember.name);

	}

	private void doLogout() {

		System.out.printf("%s님이 로그아웃되었습니다.\n", loginedMember.name);
		loginedMember = null;

	}

	private void doJoin() {
		int id = Container.memberDao.getNewId();
		String regDate = Util.getNowDateStr();
		String loginId = null;

		while (true) {
			System.out.print("가입하실 아이디 : ");
			loginId = sc.nextLine();

			if (memberService.isJoinableLoginId(loginId) == false) {
				System.out.printf("%s는(은) 이미 사용중인 아이디입니다.\n", loginId);
				continue;
			}
			break;
		}

		String loginPw = null;
		String loginPwConfirm = null;

		while (true) {
			System.out.print("로그인 비밀번호 : ");
			loginPw = sc.nextLine();

			System.out.print("로그인 비밀번호 확인 : ");
			loginPwConfirm = sc.nextLine();

			if (loginPw.equals(loginPwConfirm) == false) {
				System.out.println("비밀번호를 다시 입력해주세요.");
				continue;
			}
			break;
		}
		System.out.print("이름 : ");
		String name = sc.nextLine();

		Member member = new Member(id, regDate, loginId, loginPw, name);
		memberService.join(member);

		System.out.printf("%d번 회원이 생성되었습니다.\n", id);
	}

	private void doModify() {
		String loginPw = null;

		authByPw(loginPw);

		System.out.print("새 이름 : ");
		String name = sc.nextLine();

		loginedMember.name = name;
		System.out.printf("%s님의 정보가 수정되었습니다.\n", loginedMember.name);
	}

	private void doWithdraw() {
		String loginPw = null;

		authByPw(loginPw);

		Member member = loginedMember;
		memberService.withdraw(member);

		System.out.printf("%s님의 계정 탈퇴가 완료되었습니다.\n", loginedMember.name);
		loginedMember = null;
	}

	private void authByPw(String loginPw) {
		int loginCount = 0;
		int loginMaxCount = 3; // 로그인 시도가 3번 실패하면(3번 초과하면) 처음으로 돌아가기

		System.out.println("본인확인을 위해 비밀번호를 입력해주세요.");

		while (true) {
			loginCount++;

			if (loginCount > loginMaxCount) {
				System.out.println("본인확인을 3회 실패하셨습니다. 처음부터 다시 실행해주세요.");
				return;
			}

			System.out.print("비밀번호 : ");
			loginPw = sc.nextLine().trim();

			if (loginedMember.loginPw.equals(loginPw) == false) {
				System.out.println("비밀번호가 일치하지 않습니다");
				continue;
			}

			System.out.println("본인확인이 완료되었습니다.");
			break;
		}
	}

	public void makeTestData() {
		System.out.println("테스트를 위한 회원 데이터를 생성합니다.");

		Container.memberDao
				.add(new Member(Container.memberDao.getNewId(), Util.getNowDateStr(), "admin", "admin", "관리자"));
		Container.memberDao
				.add(new Member(Container.memberDao.getNewId(), Util.getNowDateStr(), "test1", "test1", "홍길동"));
		Container.memberDao
				.add(new Member(Container.memberDao.getNewId(), Util.getNowDateStr(), "test2", "test2", "임꺽정"));
	}
}
