package fr.fms.entities;

public class Article {
	
	private int id;
	private String description;
	private String brand;
	private double price;
	private int quantite = 1;
	
	

	public Article(String description, String brand, double price) {
		this.description = description;
		this.brand = brand;
		this.price = price;
	}
	public Article(int id, String description, String brand, double price) {
		this.id = id;
		this.description = description;
		this.brand = brand;
		this.price = price;
	}
	public Article(Article article) {
		this.id = article.getId();
		this.description = article.getDescription();
		this.brand = article.getBrand();
		this.price = article.getPrice();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public String toString() {
		return getDescription() +" | "+ getBrand() +" | "+ getPrice();
		
	}
	
}
