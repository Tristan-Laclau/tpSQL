import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import fr.fms.dao.ArticleDao;
import fr.fms.dao.BddConnection;
import fr.fms.dao.UserDao;
import fr.fms.entities.Article;
import fr.fms.entities.User;

public class testUserDao {

	public static Scanner scan = new Scanner(System.in);


	public static boolean isCertifiedUser() {

		String username;
		String password;

		ArrayList<User> userList = new ArrayList<>();

		UserDao userDao = new UserDao();

		System.out.print("Username :");

		username = scan.nextLine();	
		System.out.println();

		System.out.print("Password :");
		password = scan.nextLine();

		userList = userDao.readAll();

		for (User u : userList) {

			if (u.getLogin().equals(username) && u.getPassword().equals(password)){
				System.out.println("Vous êtes bien connecté "+ username);
				return true;
			}
		}
		return false;
	}

	public static void displayMenu() {

		int input;

		ArticleDao articleDao = new ArticleDao();

		System.out.println("Que voulez-vous faire");

		System.out.println("1 : Consulter un article");
		System.out.println("2 : Consulter la liste des articles");

		while(!scan.hasNextInt())scan.next();

		input = scan.nextInt();

		switch(input) {
		case 1:

			System.out.println("Entrez l'id de l'article que vous souhaitez consulter");
			while(!scan.hasNextInt())scan.next();

			input = scan.nextInt();

			System.out.println(articleDao.read(input));

			break;

		case 2:
			for (Article a : articleDao.readAll()) {

				System.out.println(a);
			}

			break;

		default:
			System.out.println("Merci d'avoir utilisé notre appli, au revoir");
		}


	}

	public static void main(String[] args) {


		try (Statement statement = BddConnection.getConnection().createStatement()) {


			if (isCertifiedUser()) {
				displayMenu();
			}else {
				System.out.println("Vous n'avez pas les droits d'acceder à cette ressource");
			}




		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
