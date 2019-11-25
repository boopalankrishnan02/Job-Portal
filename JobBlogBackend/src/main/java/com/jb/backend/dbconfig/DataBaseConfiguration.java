package com.jb.backend.dbconfig;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jb.backend.model.BlogComment;
import com.jb.backend.model.BlogPost;

import com.jb.backend.model.Friend;
import com.jb.backend.model.Job;
import com.jb.backend.model.Notification;
import com.jb.backend.model.ProfilePicture;
import com.jb.backend.model.User;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.jb.backend")
public class DataBaseConfiguration {
	
	@Bean(name="sessionFactory")
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
		builder.addProperties(hibernateProperties());
		
		builder.addAnnotatedClass(Job.class);
		builder.addAnnotatedClass(User.class);
		
		builder.addAnnotatedClass(ProfilePicture.class);
		builder.addAnnotatedClass(Friend.class);
		builder.addAnnotatedClass(BlogPost.class);
		builder.addAnnotatedClass(BlogComment.class);
		builder.addAnnotatedClass(Notification.class);
		
		System.out.println("Session factory object created");
		return builder.buildSessionFactory();
	}
	
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource datasource = new BasicDataSource();
		
		datasource.setDriverClassName("org.h2.Driver");
		datasource.setUrl("jdbc:h2:tcp://localhost/~/demo");
		datasource.setUsername("demo");
		datasource.setPassword("demo");
		System.out.println("Datasource object created");
		return datasource;
	}
	
	@Bean(name="txManager")
	public HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
		System.out.println("Transaction manager object created");
		return new HibernateTransactionManager(sessionFactory);
	}
	
	private final Properties hibernateProperties() {
		Properties hibernateproperties = new Properties();
		hibernateproperties.setProperty("hibernate.dialect","org.hibernate.dialect.H2Dialect");
		hibernateproperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateproperties.setProperty("hibernate.show_sql","true");
		hibernateproperties.setProperty("hibernate.format_sql","true");
		hibernateproperties.setProperty("hibernate.id.new_generator_mappings","true");
		return hibernateproperties;
	}
}
