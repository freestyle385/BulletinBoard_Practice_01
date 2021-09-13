package project_controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import project_dto.Member;
import project_util.Util;

public class MemberController extends Controller {
	private Scanner sc;
	private List<Member> members;
	private String command;
	private String actionMethodName;
	private Member loginedMember;

	public MemberController(Scanner sc) {
		this.sc = sc;

		members = new ArrayList<>();

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
		
		if (loginedMember != null) {
			System.out.printf("현재 %s님이 로그인 중입니다.\n", loginedMember.name);
			return;
		}
		
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

			member = getMemberByLoginId(loginId);

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
		
		if (loginedMember == null) {
			System.out.println("현재 로그인 상태가 아닙니다.");
			return;
		}
		
		System.out.printf("%s님이 로그아웃되었습니다.\n", loginedMember.name);
		loginedMember = null;
		
	}

	private void doJoin() {
		int id = members.size() + 1;
		String regDate = Util.getNowDateStr();
		String loginId = null;

		while (true) {
			System.out.print("가입하실 아이디 : ");
			loginId = sc.nextLine();

			if (isJoinableLoginId(loginId) == false) {
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
		members.add(member);

		System.out.printf("%d번 회원이 생성되었습니다.\n", id);
	}

	private boolean isJoinableLoginId(String loginId) {
		// getMemberIndexByLoginId의 리턴값(배열 인덱스)을 통해 가입할 아이디의 중복여부를 나타냄
		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return true;
		}
		return false;
	}

	private int getMemberIndexByLoginId(String loginId) {
		// loginId를 통해 회원의 배열 인덱스를 알아냄(0 ~ )
		// 중복된 아이디가 없을 경우 인덱스 범위 외의 -1를 반환
		int i = 0;
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return i;
			}
			i++;
		}
		return -1;
	}

	private Member getMemberByLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return null;
		}

		return members.get(index);

	}

	public void makeTestData() {
		System.out.println("테스트를 위한 회원 데이터를 생성합니다.");

		members.add(new Member(1, Util.getNowDateStr(), "admin", "admin", "관리자"));
		members.add(new Member(2, Util.getNowDateStr(), "test1", "test1", "홍길동"));
		members.add(new Member(3, Util.getNowDateStr(), "test2", "test2", "임꺽정"));
	}
}
