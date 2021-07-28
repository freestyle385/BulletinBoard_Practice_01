package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("=== 프로그램 시작 ===");
		
		int lastArticleId = 0;
		int id;
		String title;
		String body;
		
		List<Article> articles = new ArrayList<>();
		
		

		while (true) {
			System.out.print("입력하실 명령어 )) ");
			String command = sc.nextLine().trim();

			System.out.printf("입력된 명령어 )) %s\n", command);

			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;

			} else if (command.equals("program exit")) {
				break;

			} else if (command.equals("article write")) {
				id = lastArticleId + 1;
				System.out.print("제목 : ");
				title = sc.nextLine();
				
				System.out.print("내용 : ");
				body = sc.nextLine();
				
				Article article = new Article(id, title, body);
				articles.add(article);
				lastArticleId++;
				
				System.out.printf("%d번 글이 생성되었습니다.\n", id);
				
			} else if (command.equals("article list")) {
				System.out.println("article list");

			} else if (command.equals("article detail")) {
				System.out.println("article detail");

			} else if (command.equals("article modify")) {
				System.out.println("article modify");

			} else if (command.equals("article delete")) {
				System.out.println("article delete");

			} else {
				System.out.printf("%s(은)는 존재하지 않는 명령어 입니다.\n", command);
				continue;
			}

		}
		sc.close();
		System.out.println("=== 프로그램 종료 ===");

	}

}
class Article {
	int id;
	String title;
	String body;
	
	public Article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}
}