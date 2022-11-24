package com.spring.core.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.spring.core.jdbc.model.Product;

public class ProductDaoImpl implements ProductDao {
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Product product) {
		
		String query = " insert product(id, name, price, unit) values (?, ?, ?, ?)";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		Object[] values = new Object[] {
				product.getId(),
				product.getName(),
				product.getPrice(),
				product.getUnit()
		};
		
		int out = jdbcTemplate.update(query, values);
		
		if(out != 0)
			System.out.println("Product saved with id = " + product.getId());
		else
			System.out.println("Product save failed with id =" + product.getId());	
	}

	@Override
	public Product getById(int id) {
		
		String query = "select id, name,  price, unit, discontinued from products where id = ?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
					
		//using RowMapper anonymous class, we can create a separate RowMapper for reuse
		Product pro = jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<Product>(){

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product prdt = new Product();
				
				prdt.setId(rs.getInt("id"));
				prdt.setName(rs.getString("name"));
				prdt.setPrice(rs.getDouble("price"));
				prdt.setUnit(rs.getInt("unit"));
				prdt.setDiscontinued(rs.getBoolean("discontinued"));
				return prdt;
			}});
		
		return pro;
	}

	@Override
	public void update(Product product) {
		
		String query = "update product set name=?, price=?, unit=? where id=?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		Object[] args = new Object[] {
				product.getName(), 
				product.getPrice(),
				product.getUnit(),
				product.getId()
		};
		
		int out = jdbcTemplate.update(query, args);
		
		if(out !=0){
			System.out.println("Product updated with id="+product.getId());
		}
		else System.out.println("Product Not found with id="+product.getId());
	}

	@Override
	public void deleteById(int id) {
		
		String query = "delete from product where id=?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		int out = jdbcTemplate.update(query, id);
		
		if(out !=0){
			System.out.println("Product deleted with id="+id);
		}
		else 
			System.out.println("Product Not found with id="+id);
		
	}

	@Override
	public List<Product> getAll() {
		String query = "select id, name, price, unit from product";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Product> prdtList = new ArrayList<Product>();

		List<Map<String,Object>> prdtRows = jdbcTemplate.queryForList(query);
		
		for(Map<String,Object> prdtRow : prdtRows){
				Product prdt = new Product();
			
			prdt.setId(Integer.parseInt(String.valueOf(prdtRow.get("id"))));
			
			prdt.setName(String.valueOf(prdtRow.get("name")));
			prdt.setPrice(Double.parseDouble(String.valueOf(prdtRow.get("price"))));
			prdt.setUnit(Integer.parseInt(String.valueOf(prdtRow.get("unit"))));
			
			prdtList.add(prdt);
		}
		
		return prdtList;
	}

	@Override
	public Product getByName(String name) {
		
		String query = "select id, name, price, unit, discontinued from products where name like '?%' ";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Product> proList = new ArrayList<Product>();

		List<Map<String,Object>> proRows = jdbcTemplate.queryForList(query);
		
		for(Map<String,Object> proRow : proRows){
			Product pro = new Product();
			
			pro.setId(Integer.parseInt(String.valueOf(proRow.get("id"))));
			pro.setName(String.valueOf(proRow.get("name")));
			pro.setPrice(Double.parseDouble(String.valueOf(proRow.get("price"))));
			pro.setUnit(Integer.parseInt(String.valueOf(proRow.get("unit"))));
			pro.setDiscontinued(Boolean.valueOf(String.valueOf(proRow.get("discontinued"))));
			
			proList.add(pro);
		}
		return (Product) proList;
	}

	@Override
	public List<Product> getByNames(String substring) {
		
		String query = "select id, name, price, unit, discontinued from products where price between ? and ?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Product> proList = new ArrayList<Product>();

		List<Map<String,Object>> proRows = jdbcTemplate.queryForList(query);
		
		for(Map<String,Object> proRow : proRows){
			Product pro = new Product();
			
			pro.setId(Integer.parseInt(String.valueOf(proRow.get("id"))));
			pro.setName(String.valueOf(proRow.get("name")));
			pro.setPrice(Double.parseDouble(String.valueOf(proRow.get("price"))));
			pro.setUnit(Integer.parseInt(String.valueOf(proRow.get("unitsInStock"))));
			pro.setDiscontinued(Boolean.valueOf(String.valueOf(proRow.get("discontinued"))));
			
			proList.add(pro);
		}
		
		return proList;
	}

	@Override
	public List<Product> getByBetweenPrice(double iPrice, double oPrice) {
		
		String query = "select id, name, price, unit, discontinued from products where disconitinued = true";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Product> proList = new ArrayList<Product>();

		List<Map<String,Object>> proRows = jdbcTemplate.queryForList(query);
		
		for(Map<String,Object> proRow : proRows){
			Product pro = new Product();
			
			pro.setId(Integer.parseInt(String.valueOf(proRow.get("id"))));
			pro.setName(String.valueOf(proRow.get("name")));
			pro.setPrice(Double.parseDouble(String.valueOf(proRow.get("price"))));
			pro.setUnit(Integer.parseInt(String.valueOf(proRow.get("unit"))));
			pro.setDiscontinued(Boolean.valueOf(String.valueOf(proRow.get("discontinued"))));
			
			proList.add(pro);
		}
		
		return proList;
	}

}
