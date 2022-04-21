import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import fr.fms.business.IJobImplements;
import fr.fms.dao.BddConnection;
import fr.fms.dao.UserDao;
import fr.fms.entities.Article;
import fr.fms.entities.User;

public class AppliShopping {

	public static Scanner scan = new Scanner(System.in);
	public static IJobImplements job = new IJobImplements();


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

		System.out.println("Que voulez-vous faire");

		System.out.println("1 : Consulter la liste des articles");
		System.out.println("2 : Ajouter un article au panier");
		System.out.println("3 : Retirer un article du panier");
		System.out.println("4 : Afficher le panier");
		System.out.println("5 : Passer commande");
		System.out.println("6 : Quitter l'application");

		while(!scan.hasNextInt())scan.next();

		input = scan.nextInt();

		switch(input) {
		case 1:
			
			for (Article a : job.getAllArticles()) {

				System.out.println(a);	
			}
			displayMenu();
			break;

		case 2:
			
			job.addArticleToCart(job.selectArticle(job.getAllArticles()));
			displayMenu();
			break;
			
		case 3:
			
			if (job.cartIsEmpty())  System.out.println("Votre panier est vide");
			else job.removeArticleFromCart(job.selectArticle().getId());
			
			displayMenu();
			break;
			
		case 4:
			
			job.displayCart();
			displayMenu();
			break;
			
		case 5:
			
			job.checkout();
			displayMenu();
			break;
			
		case 6:
			
			System.out.println("Merci d'avoir utilisé notre application, au revoir");
			
			break;

		default:
			System.out.println("Mauvaise saisie");
			displayMenu();
			break;
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
