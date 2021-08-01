package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("=== ���α׷� ���� ===");
		Scanner sc = new Scanner(System.in);
		
		int lastArticleId = 0;
		
		List<Article> articles = new ArrayList<>();
		

		while (true) {
			System.out.print("�Է��Ͻ� ��ɾ� )) ");
			String command = sc.nextLine().trim();

			if (command.length() == 0) {
				System.out.println("��ɾ �Է����ּ���.");
				continue;

			} else if (command.equals("system exit")) {
				break;

			} else if (command.equals("article write")) {
				int id = lastArticleId + 1;
				lastArticleId = id;
				System.out.print("���� : ");
				String title = sc.nextLine();
				
				System.out.print("���� : ");
				String body = sc.nextLine();
				
				Article article = new Article(id, title, body);
				articles.add(article);
				
				System.out.printf("%d�� ���� �����Ǿ����ϴ�.\n", id);
				
			} else if (command.equals("article list")) {
				
				if (articles.size() == 0) {
					System.out.println("�Խñ��� �����ϴ�.");
				}
				System.out.println("��ȣ | ����");
				
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					
					System.out.printf(" %d  |  %s\n", article.id, article.title);
				}
				
				

			} else if (command.startsWith("article detail ")) {
				String[] commandBits = command.split(" ");
				
				int id = Integer.parseInt(commandBits[2]);
				
				Article foundArticle = null;
				
				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					
					if (article.id == id) {
						foundArticle = article;
						break;
					}
					
				}
				if (foundArticle == null) {
					System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
					continue;
				}
				else {
					System.out.printf("��ȣ : %d\n", foundArticle.id);
					System.out.printf("��¥ : 2021-12-12 12:12:12\n");
					System.out.printf("���� : %s\n", foundArticle.title);
					System.out.printf("���� : %s\n", foundArticle.body);
					continue;
				}

			} else if (command.equals("article modify")) {
				System.out.println("article modify");

			} else if (command.equals("article delete")) {
				System.out.println("article delete");

			} else {
				System.out.printf("%s(��)�� �������� �ʴ� ��ɾ� �Դϴ�.\n", command);
				continue;
			}

		}
		sc.close();
		System.out.println("=== ���α׷� ���� ===");

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