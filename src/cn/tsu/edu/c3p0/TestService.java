package cn.tsu.edu.c3p0;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestService {
	
	@Test
	public void TestDemo()
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("application3.xml");
		
		UserService user=(UserService) context.getBean("userService");
		user.add();
		
	}

}
