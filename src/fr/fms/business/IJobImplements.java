package fr.fms.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

import fr.fms.dao.ArticleDao;
import fr.fms.entities.Article;

public class IJobImplements implements IJob {

	private static HashMap<Integer, Article> cart = new HashMap<Integer , Article>();
	private ArticleDao articleDao = new ArticleDao();
	private static Scanner scan = new Scanner(System.in);

	@Override
	public ArrayList<Article> getAllArticles() {
		return articleDao.readAll();
	}

	@Override
	public void addArticleToCart(Article article) {

		if (!cart.containsKey(article.getId())) {
			cart.put(article.getId(),article);
		}
		else {
			article = cart.get(article.getId());
			article.setQuantite(article.getQuantite()+1);
			cart.put(article.getId(), article);
		}
		System.out.println("Article ajouté au panier");
	}

	@Override
	public void removeArticleFromCart(int id) {

		if (!cart.containsKey(id)) {
			System.out.println("Cet article n'est pas présent dans votre panier");
		}
		else if (cart.get(id).getQuantite() > 1){
			Article article = cart.get(id);
			article.setQuantite(article.getQuantite()-1);
			cart.put(id, article);
			System.out.println("Article retiré du panier");
		} else if (cart.get(id).getQuantite() == 1) {
			cart.remove(id);
			System.out.println("Article retiré du panier");
		}

	}

	@Override
	public void displayCart() {
		if(cart.isEmpty()) System.out.println("Votre panier est vide");

		for (Article a : cart.values()) {
			System.out.println(a + " x " + a.getQuantite());
		}

	}

	@Override
	public void clearCart() {
		cart.clear();
		System.out.println("Panier vidé");
	}

	@Override
	public void checkout() {

		if(!cartIsEmpty()) {

			double price = 0;
			int input = 0;
			System.out.println("Votre panier : ");
			displayCart();

			System.out.print("Prix : ");
			for (Article a : cart.values()) {
				price += (a.getPrice()*a.getQuantite());
			}

			System.out.println(price);

			System.out.println("Tapez 1 pour confirmer votre achat, 2 pour annuler");

			while(!scan.hasNextInt())scan.next();
			input = scan.nextInt();

			switch(input) {
			case 1:
				System.out.println("Merci de votre achat");
				clearCart();	
				break;
			case 2:
				System.out.println("Retour au menu principal");
				break;
			default:
				System.out.println("Mauvaise saisie, veuillez réessayer");
				checkout();
				break;
			}
		}else {
			System.out.println("Votre panier est vide, retour au menu principal");
		}

	}

	@Override
	public Article selectArticle(Collection<Article> c){

		int input;
		Article article;

		System.out.println("Entrez l'ID  de l'article désiré");

		for (Article a : c) {
			System.out.println( a.getId() + " : " + a);
		}

		while(!scan.hasNextInt())scan.next();
		input = scan.nextInt();

		try {
			article = articleDao.read(input);
			article.getId(); //Si article est null, renvoie une exception ce qui nous fait passer dans le catch
		}
		catch (Exception e) {
			System.out.println("Veuillez entrer un ID existant");
			article = selectArticle(c);
		}

		return article;
	}

	@Override
	public Article selectArticle() {

		return selectArticle(cart.values());

	}

	@Override
	public boolean cartIsEmpty() {
		return cart.isEmpty();
	}

}
