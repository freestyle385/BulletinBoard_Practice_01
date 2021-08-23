package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import project_controller.ArticleController;
import project_controller.MemberController;
import project_dto.Article;
import project_dto.Member;
import project_util.Util;

public class App {

	private static List<Article> articles;
	private static List<Member> members;

	public App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}

	public void start() {
		System.out.println("=== 프로그램 시작 ===");

		makeTestData();

		Scanner sc = new Scanner(System.in);
		
		MemberController memberController = new MemberController(sc, members);
		ArticleController articleController = new ArticleController(sc, articles);

		while (true) {
			System.out.printf("입력하실 명령어 )) ");
			String command = sc.nextLine();
			command = command.trim();

			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;

			} else if (command.equals("system exit")) {
				break;

			} else if (command.equals("member join")) {
				memberController.doJoin();

			} else if (command.equals("article write")) {
				articleController.doWrite();

			} else if (command.startsWith("article list ")) {
				articleController.showList(command);

			} else if (command.startsWith("article detail ")) {
				articleController.showDetail(command);

			} else if (command.startsWith("article modify ")) {
				articleController.doModify(command);

			} else if (command.startsWith("article delete ")) {
				articleController.doDelete(command);

			} else {
				System.out.printf("%s(은)는 존재하지 않는 명령어 입니다.\n", command);
				continue;
			}
		}
		sc.close();
		System.out.println("=== 프로그램 종료 ===");

	}

	private static void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다.");

		articles.add(new Article(1, Util.getNowDateStr(), "제목1", "내용1", 11));
		articles.add(new Article(2, Util.getNowDateStr(), "제목2", "내용2", 22));
		articles.add(new Article(3, Util.getNowDateStr(), "제목3", "내용3", 33));
	}

	
}
