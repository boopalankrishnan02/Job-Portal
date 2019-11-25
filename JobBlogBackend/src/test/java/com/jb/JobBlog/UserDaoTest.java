//package com.jb.JobBlog;
//
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//import com.jb.backend.dao.UserDao;
//import com.jb.backend.model.User;
//
//public class UserDaoTest {
//
//	private static UserDao userDao;
//	
//	@BeforeClass
//	public static void executeFirst() {
//		@SuppressWarnings("resource")
//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//		context.scan("com.jb.backend");
//		context.refresh();
//		userDao=(UserDao) context.getBean("userDao");
//		
//	}
//	
//	@Test
//	public void addUserTest() {
//			User user= new User();
//			user.setU_name("User1");
//			user.setU_phoneNumber("95663913332");
//			user.setU_email("user1@gmail.com");
//			user.setU_password("user1");
//			userDao.addUser(user);
//	}
//}
