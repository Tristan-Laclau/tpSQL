
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import fr.fms.dao.BddConnection;
import fr.fms.entities.Article;


public class test {

	
	
	public static void main(String[] args) throws Exception {
		
/*		Properties prop = new Properties();
		FileInputStream in = new FileInputStream("config.properties");
		prop.load(in);
		in.close();
		// Extraction des proprietes
		String url = prop.getProperty("db.url");
		String user = prop.getProperty("db.user");
		String password = prop.getProperty("db.password");
		
		
		
		try {
			Class.forName(prop.getProperty("db.driver.class"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	String url = "jdbc:mariadb://localhost:3306/shop";
	String login = "root";
	String password = "fms2022";
*/
		
		
		ArrayList<Article> articles = new ArrayList<Article>();
		
			String strSql = "SELECT * FROM T_Articles";
			String strSql2 = "UPDATE T_Articles\r\n"
					+ "SET UnitaryPrice = 70\r\n"
					+ "WHERE IdArticle = 13";
			String strSql3 = "DELETE\r\n"
					+ "FROM T_Articles\r\n"
					+ "WHERE IdArticle = 12;";
			String strSql4 = "SELECT *\r\n"
					+ "FROM T_Articles\r\n"
					+ "WHERE IdArticle = 7;";
			try (Statement statement = BddConnection.getConnection().createStatement()) {
				
				statement.executeQuery(strSql2);
				statement.executeQuery(strSql3);
				
				try (ResultSet resultSet = statement.executeQuery(strSql4)) {
					while (resultSet.next()) {
						int rsIdUser = resultSet.getInt(1);
						String rsDescription = resultSet.getNString(2);
						String rsBrand = resultSet.getNString(3);
						double rsPrice = resultSet.getDouble(4);
						articles.add((new Article(rsIdUser, rsDescription, rsBrand, rsPrice)));
					}
					resultSet.close();
				}
			
			for (Article a : articles)
				System.out.println(a.getId() + " - " + a.getDescription() + " - " + a.getBrand() + " - " + a.getPrice());
			
			articles.clear();
			
			System.out.println("------------------");
				
				try (ResultSet resultSet = statement.executeQuery(strSql)) {
					while (resultSet.next()) {
						int rsIdUser = resultSet.getInt(1);
						String rsDescription = resultSet.getNString(2);
						String rsBrand = resultSet.getNString(3);
						double rsPrice = resultSet.getDouble(4);
						articles.add((new Article(rsIdUser, rsDescription, rsBrand, rsPrice)));
					}
			for (Article a : articles)
				System.out.println(a.getId() + " - " + a.getDescription() + " - " + a.getBrand() + " - " + a.getPrice());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	}
}