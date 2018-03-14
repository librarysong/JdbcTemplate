package cn.tsu.edu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class JdbcTemplateDemo2 {

	
	
	
	
	//查询操作
	@Test
	public void testCount()
	{
		
//		ComboPooledDataSource dataSource=new ComboPooledDataSource();
//		dataSource.setDriverClass("com.mysql.jdbc.Driver");
//		dataSource.setJdbcUrl("jdbc:mysql:///spring_03");
//		dataSource.setUser("root");
//		dataSource.setPassword("123456");
		
		
		
		
		
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql:///spring_day03");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		
	    String sql="select count(*) from user";
	   
	   int count=jdbcTemplate.queryForObject(sql, Integer.class);
	   System.out.println(count); 
	}
	
	//jdbc底层实现
	
	@Test
	public void testJdbc() throws SQLException
	{
		
		Connection connection=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//创建连接
			connection=DriverManager.getConnection("jdbc:mysql:///spring_day03","root","123456");
			
			//编写sql语句
			String sql="select * from user where username=?";
			
			//预编译sql
			psmt=connection.prepareStatement(sql);
			psmt.setString(1, "tom");
			rs=psmt.executeQuery();
			
			//遍历
			while(rs.next())
			{
				String username=rs.getString("username");
				String password=rs.getString("password");
				
				//放到user
				User user=new User();
				user.setUsername(username);
				user.setPassword(password);
				System.out.println(user);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			rs.close();
			psmt.close();
			connection.close();
		}
	}
	
	//返回对象
	@Test
	public void TestObject()
	{
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql:///spring_day03");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		
		String sql="select * from user where username=?";
		
		User user=jdbcTemplate.queryForObject(sql, new MyRowMapper(),"marry");
		System.out.println(user);
	}




//返回对象
	@Test
	public void TestList()
	{
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql:///spring_day03");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		
		String sql="select * from user";
		
		List<User> list=jdbcTemplate.query(sql, new MyRowMapper());
		System.out.println(list);
	}

}



class MyRowMapper implements RowMapper<User>
{

	@Override
	public User mapRow(ResultSet rs, int num) throws SQLException {
		// TODO Auto-generated method stub
		String username=rs.getString("username");
		String password=rs.getString("password");
		
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		return user;
	}
	
}
