package fr.fms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Article;
import fr.fms.entities.User;

public class UserDao implements Dao<User> {

	@Override
	public void create(User obj) {
		try(Statement statement = connection.createStatement()){
			String str = "INSERT INTO T_Users (Login , Password) "
					+ "VALUES ('" +obj.getLogin()+ "' ,'" + obj.getPassword() +");";
			int row = statement.executeUpdate(str);
			if (row == 1) System.out.println("Insertion ok");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public User read(int id) {
		try(Statement statement = connection.createStatement()){
			String str = "SELECT * FROM T_Users WHERE IdUser =" + id + ";";
			statement.execute(str);
			try (ResultSet rs = statement.getResultSet()){
				
				rs.next();
				int rsIdUser = rs.getInt(1);
				String rsLogin = rs.getString(2);
				String rsPassword = rs.getString(3);
				
				User user = new User(rsIdUser, rsLogin, rsPassword);
				
				return user;
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(User obj) {
		
		String str = "UPDATE T_Users "
				+ "SET Login = '"+ obj.getLogin()+"' , Password = '"+obj.getPassword() +" "
				+ "WHERE IdUser = "+ obj.getId() + " ;";
		
		try(Statement statement = connection.createStatement()){
			return statement.execute(str);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean delete(User obj) {
		String str = "DELETE "
				+ "FROM T_Users "
				+ "WHERE IdUser = "+ obj.getId() + " ;";
		
		try(Statement statement = connection.createStatement()){
			return statement.execute(str);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public ArrayList<User> readAll() {
		
		ArrayList<User> allUsers = new ArrayList<User>();
		
		try(Statement statement = connection.createStatement()){
			String str = "SELECT * FROM T_Users;";
			statement.execute(str);
			try (ResultSet rs = statement.getResultSet()){
				
				while(rs.next()) {
					allUsers.add(read(rs.getInt(1)));
				}
				
				return allUsers;
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
