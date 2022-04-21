package fr.fms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Article;

public class ArticleDao implements Dao<Article>{

	@Override
	public void create(Article obj) {
		try(Statement statement = connection.createStatement()){
			String str = "INSERT INTO T_Articles (Description, Brand, UnitaryPrice) "
					+ "VALUES ('" +obj.getDescription()+ "' ,'" + obj.getBrand() + "' ," + obj.getPrice() + ");";
			int row = statement.executeUpdate(str);
			if (row == 1) System.out.println("Insertion ok");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Article read(int id) {
		try(Statement statement = connection.createStatement()){
			String str = "SELECT * FROM T_Articles WHERE IdArticle =" + id + ";";
			statement.execute(str);
			try (ResultSet rs = statement.getResultSet()){
				
				rs.next();
				int rsIdArticle = rs.getInt(1);
				String rsDescription = rs.getString(2);
				String rsBrand = rs.getString(3);
				double rsPrice = rs.getDouble(4);
				
				Article article = new Article(rsIdArticle, rsDescription, rsBrand, rsPrice);
				
				return article;
				
			}catch (Exception e) {
				System.out.println("L'ID indiqué ne correspond à aucun article");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(Article obj) {
		
		String str = "UPDATE T_Articles "
				+ "SET Description = '"+ obj.getDescription()+"' , Brand = '"+obj.getBrand() +"' , UnitaryPrice = "+ obj.getPrice() +" "
				+ "WHERE IdArticle = "+ obj.getId() + " ;";
		
		try(Statement statement = connection.createStatement()){
			return statement.execute(str);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean delete(Article obj) {
		String str = "DELETE "
				+ "FROM T_Articles "
				+ "WHERE IdArticle = "+ obj.getId() + " ;";
		
		try(Statement statement = connection.createStatement()){
			return statement.execute(str);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public ArrayList<Article> readAll() {
		
		ArrayList<Article> allArticles = new ArrayList<Article>();
		
		try(Statement statement = connection.createStatement()){
			String str = "SELECT * FROM T_Articles;";
			statement.execute(str);
			try (ResultSet rs = statement.getResultSet()){
				
				while(rs.next()) {
					allArticles.add(read(rs.getInt(1)));
				}
				
				return allArticles;
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
