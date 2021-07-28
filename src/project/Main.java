package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("=== ���α׷� ���� ===");
		
		int lastArticleId = 0;
		int id;
		String title;
		String body;
		
		List<Article> articles = new ArrayList<>();
		
		

		while (true) {
			System.out.print("�Է��Ͻ� ��ɾ� )) ");
			String command = sc.nextLine().trim();

			System.out.printf("�Էµ� ��ɾ� )) %s\n", command);

			if (command.length() == 0) {
				System.out.println("��ɾ �Է����ּ���.");
				continue;

			} else if (command.equals("program exit")) {
				break;

			} else if (command.equals("article write")) {
				id = lastArticleId + 1;
				System.out.print("���� : ");
				title = sc.nextLine();
				
				System.out.print("���� : ");
				body = sc.nextLine();
				
				Article article = new Article(id, title, body);
				articles.add(article);
				lastArticleId++;
				
				System.out.printf("%d�� ���� �����Ǿ����ϴ�.\n", id);
				
			} else if (command.equals("article list")) {
				System.out.println("article list");

			} else if (command.equals("article detail")) {
				System.out.println("article detail");

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