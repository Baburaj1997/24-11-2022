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
			 
			 CategoryDao categoryDAO = context.getBean(CategoryDao.class);
				
				Category c1 = new Category();
				
				
				//set first category
				c1.setId(1);
				c1.setName("Electronic");
				c1.setDescription("electronic gadget");
				
				
				// save()
				categoryDAO.save(c1);

				
				System.out.println("inputed successfully");			
				
				
				
				 //GetById()
				
				Category cat=categoryDAO.getById(1);
				
				System.out.println("Getting category By Id"+cat);
				
				
				//update()
				
				c1.setName("Electro");
				categoryDAO.update(c1);
			
				List<Category> cList=categoryDAO.getAll();
				
				System.out.println(cList);
				
				//deleteById()
				
				categoryDAO.deleteById(1);
				cList=categoryDAO.getAll();
				
				System.out.println(cList);
				
				//getByName()
				Category cat2 = categoryDAO.getByName("Electronic");
				System.out.println("Category by name :" + cat2); 
				
				 System.out.println("/t PRODUCT SECTION /t");
				 System.out.println();			
				
				
				//saving products
				 context =new ClassPathXmlApplicationContext("spring.xml");
					
					ProductDao productDAO = context.getBean(ProductDao.class);
					
					Product p1 = new Product();
					
					// set first product
					p1.setId(1);
					p1.setName("EARPHONE");
					p1.setPrice(150.00);
					p1.setUnit(19);
					p1.setDiscontinued(false);
					
					productDAO.save(p1);
					
					System.out.println("INPUT successful");	
			
				//getById
								
				 Product pro = productDAO.getById(1);
				 System.out.println("Product by id :" + pro);
				 
				 // update()
				
				 p1.setName("Kitkat");
				 productDAO.update(p1);  
				
				 List<Product> pList = productDAO.getAll();
				 System.out.println(pList);
				 
				 //deletById
				 
				 productDAO.deleteById(1);
				 
				 //getAll()
				 
				 pList = productDAO.getAll();
				 System.out.println(pList);
				 
				 
				 //products between price method
				 List<Product> pro3 = productDAO.getByBetweenPrice(5, 1000);
					System.out.println(pro3);
					System.out.println();
				
				
			}
			catch(Exception e) {
				e.printStackTrace();
				
			}
			finally {
				if(context !=null)
					context.close();
			}

		}

}
