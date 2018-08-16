package alterUber.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("alterUber.repositories")
@PropertySource("classpath:application.properties")
public class JPAConfig {

  @Autowired
  private Environment env;

  @Bean("entityManagerFactory")
  public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {

    LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
    entityManager.setJpaVendorAdapter(getJpaVendorAdapter());
    entityManager.setDataSource(getDataSource());
    entityManager.setPersistenceUnitName("myJpaPersistenceUnit");
    entityManager.setPackagesToScan("alterUber.dbEntities");
    entityManager.setJpaProperties(jpaProperties());
    return entityManager;
  }

  @Bean
  public JpaVendorAdapter getJpaVendorAdapter() {
    JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
    return adapter;
  }

  @Bean
  public DataSource getDataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName(env.getProperty("db.driverClassName"));
    dataSource.setUrl(env.getProperty("db.url"));
    dataSource.setUsername(env.getProperty("db.username"));
    dataSource.setPassword(env.getProperty("db.password"));
    return dataSource;
  }


  @Bean(name="transactionManager")
  public PlatformTransactionManager txManager(){
    JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(
            getEntityManagerFactoryBean().getObject());
    return jpaTransactionManager;
  }
  private Properties jpaProperties() {
    Properties properties = new Properties();
    properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
    properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
    properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
    properties.put("hibernate.id.new_generator_mappings", env.getProperty("hibernate.id.new_generator_mappings"));
    return properties;
  }

}