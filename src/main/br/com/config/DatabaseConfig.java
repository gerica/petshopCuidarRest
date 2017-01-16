package br.com.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

/**
 * Created by sazzad on 9/7/15
 */
@Configuration
@PropertySource({ "application.properties" })
@EnableJpaRepositories(basePackages = "br.com.*")
@EnableTransactionManagement
public class DatabaseConfig {

	private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
	private static final String PROPERTY_NAME_DATABASE_HOST = "serverName";
	private static final String PROPERTY_NAME_DATABASE_PORT = "portNumber";
	private static final String PROPERTY_NAME_DATABASE_NAME = "databaseName";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "password";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "userbd";
	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String PROPERTY_NAME_HIBERNATE_AUTO = "hibernate.hbm2ddl.auto";

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Autowired
	private ApplicationContext appContext;

	@Autowired
	private Environment env;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan(new String[] { "br.com.compartilhado", //
				"br.com.compartilhado.entidade", //
				"br.com.modulo.cliente.entidade", //
				"br.com.modulo.pet.entidade", //
				"br.com.modulo.produto.entidade", //
				"br.com.modulo.venda.entidade", //
				"br.com.modulo.relacionamento.entidade", //
				"br.com.modulo.financeiro.entidade"//
		});
		factory.setDataSource(getDataSource());
		factory.setJpaProperties(hibProperties());

		factory.afterPropertiesSet();

		return factory;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	public HikariDataSource getDataSource() {

		HikariDataSource dataSource = new HikariDataSource();

		dataSource.setDataSourceClassName(this.env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
		dataSource.addDataSourceProperty(PROPERTY_NAME_DATABASE_NAME,
				this.env.getRequiredProperty(PROPERTY_NAME_DATABASE_NAME));
		dataSource.addDataSourceProperty(PROPERTY_NAME_DATABASE_HOST,
				this.env.getRequiredProperty(PROPERTY_NAME_DATABASE_HOST));
		dataSource.addDataSourceProperty(PROPERTY_NAME_DATABASE_PORT,
				this.env.getRequiredProperty(PROPERTY_NAME_DATABASE_PORT));
		dataSource.addDataSourceProperty("user", this.env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
		dataSource.addDataSourceProperty(PROPERTY_NAME_DATABASE_PASSWORD,
				this.env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));

		return dataSource;

	}

	// @Bean
	// // (name = "hibernateTransactionManager")
	// public HibernateTransactionManager transactionManager() {
	// HibernateTransactionManager manager = new HibernateTransactionManager();
	// manager.setSessionFactory(hibernate5SessionFactoryBean().getObject());
	// return manager;
	// }

	@Bean(name = "sessionFactory")
	public LocalSessionFactoryBean hibernate5SessionFactoryBean() {
		LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
		localSessionFactoryBean.setDataSource(appContext.getBean(HikariDataSource.class));
		localSessionFactoryBean.setPackagesToScan(new String[] { "br.com.compartilhado", //
				"br.com.compartilhado.entidade", //
				"br.com.modulo.cliente.entidade" });

		localSessionFactoryBean.setHibernateProperties(hibProperties());
		return localSessionFactoryBean;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

	private Properties hibProperties() {
		Properties properties = new Properties();
		properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, this.env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL,
				this.env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		properties.put(PROPERTY_NAME_HIBERNATE_AUTO, this.env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_AUTO));
		return properties;
	}

}
