package com.brettchild.springmvc.hibernate;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.dialect.MySQLDialect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class HibernateConfiguration {

	@Value("#{dataSource}")
	private DataSource dataSource;

	@Bean
	public AnnotationSessionFactoryBean sessionFactoryBean() {
		Properties props = new Properties();
		props.put("hibernate.dialect", MySQLDialect.class.getName());
		props.put("hibernate.format_sql", "true");

		AnnotationSessionFactoryBean bean = new AnnotationSessionFactoryBean();
		bean.setPackagesToScan(new String[]{"com.brettchild.springmvc.domain"});
		bean.setHibernateProperties(props);
		bean.setDataSource(this.dataSource);
		bean.setSchemaUpdate(true);
		return bean;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		return new HibernateTransactionManager( sessionFactoryBean().getObject() );
	}
	
	@Bean 
	public TransactionTemplate transactionTemplate() {
		return new TransactionTemplate( transactionManager() );
	}

}
