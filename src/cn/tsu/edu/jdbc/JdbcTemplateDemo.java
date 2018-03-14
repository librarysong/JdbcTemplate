package cn.tsu.edu.jdbc;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class JdbcTemplateDemo {
	//查询操作
	
	
	
	
	
	@Test
	public void add()
	{
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql:///spring_day03");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		
		//创建模板
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		
		String sql="insert into user values(?,?)";
		int num=jdbcTemplate.update(sql,"tom","123");
	    System.out.println(num);
	}
	
	//修改操作
	@Test
	public void update()
	{
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql:///spring_day03");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		
	    String sql="update user  set password=? where username=?";
	    
	    jdbcTemplate.update(sql,"1314","tom");
	    
	}
	
	
	
	//删除操作
	@Test
	public void delete()
	{
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql:///spring_day03");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		
	    String sql="delete from user where username=?";
	    
	    jdbcTemplate.update(sql,"tom");
	    
	}

}
