package fr.fms.business;
import java.util.ArrayList;
import java.util.Collection;

import fr.fms.entities.Article;

public interface IJob {

	public abstract ArrayList<Article> getAllArticles();
	public abstract void addArticleToCart(Article article);
	public abstract void removeArticleFromCart(int id);
	public abstract void displayCart();
	public abstract void clearCart();
	public abstract void checkout();
	public abstract Article selectArticle(Collection<Article> c);
	public abstract Article selectArticle();
	public abstract boolean cartIsEmpty();
	
}
