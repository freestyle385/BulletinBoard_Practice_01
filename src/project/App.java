package project;

import java.util.Scanner;

import project_controller.ArticleController;
import project_controller.Controller;
import project_controller.MemberController;

public class App {

	public App() {
	}

	public void start() {
		System.out.println("=== 프로그램 시작 ===");
		Scanner sc = new Scanner(System.in);
		
		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);
		
		articleController.makeTestData();
		memberController.makeTestData();
		
		while (true) {
			System.out.printf("입력하실 명령어 )) ");
			String command = sc.nextLine();
			command = command.trim();

			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;

			} else if (command.equals("system exit")) {
				break;

			} 
			
			String[] commandBits = command.split(" "); // article detail
			
			if (commandBits.length == 1) {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}
			
			String controllerName = commandBits[0]; // article
			String actionMethodName = commandBits[1]; // detail
			
			Controller controller = null;
			
			if (controllerName.equals("article")) {
				controller = articleController;
			} else if (controllerName.equals("member")) {
				controller = memberController;
			} else {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}
			
			String actionName = controllerName + "/" + actionMethodName;
			
			switch (actionName) {
			case "article/write":
			case "article/delete":
			case "article/modify":
			case "member/logout":
			case "member/withdraw":
			case "member/modify":
				if (Controller.loginedMember == null) {
					System.out.println("로그인 후 이용해주세요.");
					continue;
				}
				break;
			}
			
			switch (actionName) {
			case "member/login":
			case "member/join":
				if (Controller.loginedMember != null) {
					System.out.println("로그아웃 후 이용해주세요.");
					continue;
				}
				break;
			}
			
			controller.doAction(command, actionMethodName);
			
		}
		sc.close();
		System.out.println("=== 프로그램 종료 ===");

	}

	

	
}
