package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("=== 프로그램 시작 ===");
		Scanner sc = new Scanner(System.in);
		
		int lastArticleId = 0;
		int id;
		String title;
		String body;
		
		List<Article> articles = new ArrayList<>();
		

		while (true) {
			System.out.print("입력하실 명령어 )) ");
			String command = sc.nextLine().trim();

			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;

			} else if (command.equals("system exit")) {
				break;

			} else if (command.equals("article write")) {
				id = lastArticleId + 1;
				lastArticleId = id;
				System.out.print("제목 : ");
				title = sc.nextLine();
				
				System.out.print("내용 : ");
				body = sc.nextLine();
				
				Article article = new Article(id, title, body);
				articles.add(article);
				
				System.out.printf("%d번 글이 생성되었습니다.\n", id);
				
			} else if (command.equals("article list")) {
				
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
				}
				System.out.println("번호 | 제목");
				
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					
					System.out.printf(" %d  |  %s\n", article.id, article.title);
				}
				
				

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