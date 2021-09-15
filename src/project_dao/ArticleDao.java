package project_dao;

import java.util.ArrayList;
import java.util.List;

import project_dto.Article;

public class ArticleDao {

	public List<Article> articles;
	
	public ArticleDao() {
		articles = new ArrayList<>();
	}
}
