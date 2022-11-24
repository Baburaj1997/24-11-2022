package com.spring.core.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.spring.core.jdbc.model.Category;

public class CategoryDaoImpl implements CategoryDao {

	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void save(Category category) {
		String query = " insert category(id, name, description) values (?, ?, ?)";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		Object[] values = new Object[] {
			category.getId(),
			category.getName(),
			category.getDescription()
		};
		
		int out = jdbcTemplate.update(query, values);
		
		if(out != 0)
			System.out.println("Category saved with id = " + category.getId());
		else
			System.out.println("Category save failed with id =" + category.getId());	
	}

	@Override
	public Category getById(int id) {
		
		String query = "select id, name, description from category where id = ?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		//using RowMapper anonymous class, we can create a separate RowMapper for reuse
			Category ctgy = jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<Category>(){

			@Override
			public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
			Category ctg = new Category();
						
			ctg.setId(rs.getInt("id"));
			ctg.setName(rs.getString("name"));
			ctg.setDescription(rs.getString("description"));
			return ctg;
			}});
				
		return ctgy;
	}

	@Override
	public void update(Category category) {
		
		String query = "update category set name=?, description=? where id=?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		Object[] args = new Object[] {
				category.getName(), 
				category.getDescription(), 
				category.getId()
		};
		
		int out = jdbcTemplate.update(query, args);
		
		if(out !=0){
			System.out.println("Category updated with id="+category.getId());
		}
		else System.out.println("Category Not found with id="+category.getId());
		
	}

	@Override
	public void deleteById(int id) {
		
		String query = "delete from category where id=?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		int out = jdbcTemplate.update(query, id);
		
		if(out !=0){
			System.out.println("Category deleted with id="+id);
		}
		else 
			System.out.println("Category Not found with id="+id);
		
		
	}

	@Override
	public List<Category> getAll() {
		
		String query = "select id, name, description from category";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Category> ctgList = new ArrayList<Category>();

		List<Map<String,Object>> ctgRows = jdbcTemplate.queryForList(query);
		
		for(Map<String,Object> ctgRow : ctgRows){
				Category ctg = new Category();
			
			ctg.setId(Integer.parseInt(String.valueOf(ctgRow.get("id"))));
			
			ctg.setName(String.valueOf(ctgRow.get("name")));
			ctg.setDescription(String.valueOf(ctgRow.get("description")));
			
			ctgList.add(ctg);
		}
		
		return ctgList;
	}

	@Override
	public Category getByName(String name) {
		
		String query = "select id, name, description from category where name = ?";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		Category ctg = jdbcTemplate.queryForObject(query, new Object[]{name}, new RowMapper<Category>(){

			@Override
			public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
				Category ctg = new Category();
				
				ctg.setId(rs.getInt("id"));
				ctg.setName(rs.getString("name"));
				ctg.setDescription(rs.getString("description"));
				return ctg;
			}});
		
		return ctg;
	}

	@Override
	public List<Category> getByNames(String substring) {
		
		String query = "select id, name, description from category where name like ?%";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Category> ctgList = new ArrayList<Category>();

		List<Map<String,Object>> ctgRows = jdbcTemplate.queryForList(query);
		
		for(Map<String,Object> ctgRow : ctgRows){
			Category ctg = new Category();
			
			ctg.setId(Integer.parseInt(String.valueOf(ctgRow.get("id"))));
			ctg.setName(String.valueOf(ctgRow.get("name")));
			ctg.setDescription(String.valueOf(ctgRow.get("description")));
			
			ctgList.add(ctg);
		}
		
		return ctgList;
	}

}
