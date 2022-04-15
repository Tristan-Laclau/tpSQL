import java.sql.Statement;

import fr.fms.dao.ArticleDao;
import fr.fms.dao.BddConnection;
import fr.fms.entities.Article;

public class testArticleDao {

	public static void main(String[] args) throws Exception{
		
		
		try (Statement statement = BddConnection.getConnection().createStatement()) {
			
			Article article1 = new Article("Casque audio","New Bee",50);
			
			ArticleDao articleDao = new ArticleDao();
			System.out.println(articleDao.read(1));
			articleDao.create(article1);
			
			System.out.println(articleDao.readAll());
			
			article1 = articleDao.readAll().get(articleDao.readAll().size()-1);
			article1.setPrice(60);
			
			System.out.println(articleDao.update(article1));
			
			System.out.println(articleDao.read(articleDao.readAll().get(articleDao.readAll().size()-1).getId()));
			
			System.out.println(articleDao.delete(articleDao.readAll().get(articleDao.readAll().size()-1)));
			System.out.println(articleDao.readAll());
			

			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
