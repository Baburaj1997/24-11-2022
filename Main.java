package com.spring.core.jdbc.main;

import java.util.List;
import java.util.Random;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.core.jdbc.dao.CategoryDao;
import com.spring.core.jdbc.dao.ProductDao;
import com.spring.core.jdbc.model.Category;
import com.spring.core.jdbc.model.Product;

public class Main {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = null;
		try {
			 context =new ClassPathXmlApplicationContext("spring.xml");
			 
			 System.out.println("WELCOME TO SPRING-CORE-JDBC PROGRAM");
			 
		//Category class
			 System.out.println("/t CATEGORY SECTION /t");
			 System.out.println();
			 
			 CategoryDao categoryDao = context.getBean(CategoryDao.class);
				
			 Category ctg = new Category();
				
			 int rand = new Random().nextInt(1000);
				
			 ctg.setId(rand);
			 ctg.setName("Electronic");
			 ctg.setDescription("TWG Ear buds");
				
			//Save values to the table	
			 categoryDao.save(ctg);
				
			 System.out.println("Category is saved successfully");
				
			 System.out.println();
			 System.out.println("**************************************************");
			 System.out.println();
			 
			 //Update values in the table
			 ctg.setName("Gadgets");
			 categoryDao.update(ctg);
				
			 List<Category> ctgList = categoryDao.getAll();
			 System.out.println(ctgList);
				
			 System.out.println();
			 System.out.println("**************************************************");
			 System.out.println();
			
			 //Delete the value by Id
			 categoryDao.deleteById(rand);
			 
			 //Display all the records
			 ctgList = categoryDao.getAll();
			 System.out.println(ctgList);
				
			 System.out.println();
			 System.out.println("**************************************************");
			 System.out.println();
			
			 //Display all the records of categories table having name Perfume
			 Category ctg2 = categoryDao.getByName("Perfume");
			 System.out.println("Category by name :" + ctg2);
				
			 System.out.println();
			 System.out.println("**************************************************");
			 System.out.println();
			
			//Display all the records of categories table having substring Perfume
			 List<Category> ctgList2 = categoryDao.getByNames("P");
			 System.out.println(ctgList2);
				
			 System.out.println();
			 System.out.println("**************************************************");
			 System.out.println();
			
			 //Product class
			 System.out.println("/t Product Class /t");
			 System.out.println();
				
			 ProductDao productDAO = context.getBean(ProductDao.class);
				
			 Product p1 = new Product();
				
			 int randp = new Random().nextInt(1000);
				
			 p1.setId(randp);
			 p1.setName("Boat twg near buds");
			 p1.setPrice(1500.00);
			 p1.setUnit(10);
			 p1.setDiscontinued(true);
			
			 //Save the records in table Product
			 productDAO.save(p1);
				
			 System.out.println("Product is saved successfully");
				
			 System.out.println();
			 System.out.println("**************************************************");
			 System.out.println();
			
			 //Display the records by id
			 Product pro = productDAO.getById(randp);
			 System.out.println("Product by id :" + pro);
				
			 System.out.println();
			 System.out.println("**************************************************");
			 System.out.println();
			
			 //Update the records of table by provided id
			 p1.setName("sony sound basw");
			 p1.setDiscontinued(false);
			 p1.setPrice(2000);
			 p1.setUnit(5);
			 productDAO.update(p1);
			
			 //Display all the records of table products
			 List<Product> pList = productDAO.getAll();
			 System.out.println(pList);
				
			 System.out.println();
			 System.out.println("**************************************************");
			 System.out.println();
			
			 //Delete the records of table Product by id
			 productDAO.deleteById(rand);
				
			 pList = productDAO.getAll();
			 System.out.println(pList);
				
			 System.out.println();
			 System.out.println("**************************************************");
			 System.out.println();
			
			 //Display the records of table by name
			 Product pro2 = productDAO.getByName("lenovo double");
			 System.out.println("Product by name :" + pro2);
				
			 System.out.println();
			 System.out.println("**************************************************");
			 System.out.println();
				
			 //Display the records of table by substring
			 List<Product> pList2 = productDAO.getByNames("P");
			 System.out.println(pList2);
				
			 System.out.println();
			 System.out.println("**************************************************");
			 System.out.println();
			
			 //Display all the records having price in between
			 List<Product> pro3 = productDAO.getByBetweenPrice(200, 5000);
			 System.out.println(pro3);
			
			 System.out.println();
			 System.out.println("**************************************************");
			 System.out.println();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(context != null)
				context.close();
		}

	}

}
