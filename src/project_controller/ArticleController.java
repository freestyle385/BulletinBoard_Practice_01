package project_controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import project_dto.Article;
import project_util.Util;

public class ArticleController extends Controller {
	private Scanner sc;
	private List<Article> articles;
	private String command;
	private String actionMethodName;

	public ArticleController(Scanner sc) {
		this.sc = sc;

		articles = new ArrayList<>();
	}

	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "list":
			showList();
			break;
		case "detail":
			showDetail();
			break;
		case "write":
			doWrite();
			break;
		case "modify":
			doModify();
			break;
		case "delete":
			doDelete();
			break;
		default:
			System.out.println("존재하지 않는 명령어입니다.");
			break;
		}
	}

	public void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다.");

		articles.add(new Article(1, Util.getNowDateStr(), 1, "제목1", "내용1", 11));
		articles.add(new Article(2, Util.getNowDateStr(), 1, "제목2", "내용2", 22));
		articles.add(new Article(3, Util.getNowDateStr(), 2, "제목3", "내용3", 33));
	}

	public ArticleController(Scanner sc, List<Article> articles) {
		this.sc = sc;
		this.articles = articles;
	}

	private void doWrite() {
		int id = articles.size() + 1;
		String regDate = Util.getNowDateStr();

		System.out.printf("제목 : ");
		String title = sc.nextLine();

		System.out.printf("내용 : ");
		String body = sc.nextLine();

		Article article = new Article(id, regDate, loginedMember.id, title, body, 0);
		articles.add(article);

		System.out.printf("%d번 글이 생성되었습니다.\n", id);
	}

	private void showList() {
		// 게시물 리스팅 시 필터링할 키워드를 searchKeyword로 추출
		// 필터링된 게시물만 forListArticles 배열에 추가
		String searchKeyword = command.substring("article list".length()).trim();

		List<Article> forListArticles = articles;

		if (searchKeyword.length() > 0) {
			// searchKeyword가 유효할 때, searchKeyword가 포함된 게시물을 forListArticles의 새로 생성된 배열에 추가
			forListArticles = new ArrayList<>();

			for (Article article : articles) {
				if (article.title.contains(searchKeyword)) {
					forListArticles.add(article);
				}
			}
			if (forListArticles.size() == 0) {
				System.out.println("검색결과가 존재하지 않습니다.");
				return;
			}
		}

		if (articles.size() == 0) {
			System.out.println("게시글이 없습니다.");
			return;
		}
		System.out.println("번호 | 조회 |   제목  | 작성자");

		for (int i = forListArticles.size() - 1; i >= 0; i--) {
			Article article = forListArticles.get(i);

			System.out.printf("%4d|%5d|%7s|%5d\n", article.id, article.hit, article.title, article.memberId);
		}
	}

	private void showDetail() {
		String[] commandBits = command.split(" ");

		int id = Integer.parseInt(commandBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}

		foundArticle.increaseHit();

		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("날짜 : %s\n", foundArticle.regDate);
		System.out.printf("작성자 : %d\n", foundArticle.memberId);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
		System.out.printf("조회수 : %d\n", foundArticle.hit);
	}

	private void doModify() {
		String[] commandBits = command.split(" ");

		int id = Integer.parseInt(commandBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}

		if (loginedMember.id != foundArticle.memberId) {
			System.out.printf("%d번 게시물을 수정할 권한이 없습니다.\n", foundArticle.id);
			return;
		}

		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		foundArticle.title = title;
		foundArticle.body = body;

		System.out.printf("%d번 글이 수정되었습니다.\n", id);
	}

	private void doDelete() {
		String[] commandBits = command.split(" ");

		int id = Integer.parseInt(commandBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}

		if (loginedMember.id != foundArticle.memberId) {
			System.out.printf("%d번 게시물을 삭제할 권한이 없습니다.\n", foundArticle.id);
			return;
		}

		articles.remove(foundArticle);
		System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
	}

	private int getArticleIndexById(int id) {
		// id를 통해 게시물의 배열 인덱스를 알아냄(0 ~ )
		// 찾는 게시물이 없을 경우 인덱스 범위 외의 -1를 반환
		int i = 0;

		for (Article article : articles) {
			if (article.id == id) {
				return i;
			}
			i++;
		}
		return -1;
	}

	private Article getArticleById(int id) {
		// getArticleIndexById의 리턴값(배열 인덱스)에 해당하는 데이터 찾기
		int index = getArticleIndexById(id);

		if (index != -1) {
			return articles.get(index);
		}
		return null;
	}

}
