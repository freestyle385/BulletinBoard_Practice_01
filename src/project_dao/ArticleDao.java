package project_dao;

import java.util.ArrayList;
import java.util.List;

import project_dto.Article;

public class ArticleDao extends Dao {

	public List<Article> articles;

	public ArticleDao() {
		articles = new ArrayList<>();
	}

	public void add(Article article) {
		articles.add(article);
		lastId = article.id;
	}
}
