package com.spring.core.jdbc.model;

public class Product {
	private int id;
	private String name;
	private double price;
	private int unit;
	private boolean discontinued;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(int id, String name, double price, int unit, boolean discontinued) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.unit = unit;
		this.discontinued = discontinued;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public boolean isDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(boolean discontinued) {
		this.discontinued = discontinued;
	}

	@Override
	public String toString() {
		return "Products [id=" + id + ", name=" + name + ", price=" + price + ", unit=" + unit + ", discontinued="
				+ discontinued + "]";
	}
}
