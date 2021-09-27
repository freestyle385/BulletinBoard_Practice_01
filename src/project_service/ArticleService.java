package project_service;

import java.util.List;

import project.Container;
import project_dao.ArticleDao;
import project_dto.Article;

public class ArticleService {

	private ArticleDao articleDao;

	public ArticleService() {
		this.articleDao = Container.articleDao;
	}

	public List<Article> getForPrintArticles(String searchKeyword) {

		return articleDao.getArticles(searchKeyword);
	}
	
	public List<Article> getForPrintArticles() {
		return articleDao.getArticles(null);
	}

	public int getArticleIndexById(int id) {
		return articleDao.getArticleIndexById(id);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public void remove(Article foundArticle) {
		articleDao.remove(foundArticle);

	}

}
