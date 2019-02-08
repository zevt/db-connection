package com.zeroexception.sqlconnection.dataSourceConfig;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = {"com.zeroexception.sqlconnection.serviceLayer.repository"},
    entityManagerFactoryRef = "postgreEntityManagerFactory",
    transactionManagerRef = "postgreTransactionManager")
public class PostgreDataSourceConfig {

  private String username;
  private String password;
  private String url;
  private String driverClassName;
  private String dialect;
  private String hbm2ddl;

  @Autowired
  public PostgreDataSourceConfig(@Value("${postgres.datasource.username}") String username,
      @Value("${postgres.datasource.password}") String password,
      @Value("${postgres.datasource.url}") String url,
      @Value("${postgres.datasource.driver-class-name}") String driverClassName,
      @Value("${postgres.jpa.database-platform}") String dialect,
      @Value("${postgres.jpa.hibernate.ddl-auto}") String hbm2ddl) {
    this.username = username;
    this.password = password;
    this.url = url;
    this.driverClassName = driverClassName;
    this.dialect = dialect;
    this.hbm2ddl = hbm2ddl;
  }

  @Bean(name = "postgreDataSource")
//    @ConfigurationProperties(prefix = "")
  public DataSource dataSource() {

    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create()
        .driverClassName(this.driverClassName)
        .username(this.username)
        .password(this.password)
        .url(this.url);
    return dataSourceBuilder.build();

  }

  @Bean(name = "postgreEntityManagerFactory")
  public EntityManagerFactory
  postgreEntityManagerFactory(EntityManagerFactoryBuilder builder,
      @Qualifier("postgreDataSource") DataSource dataSource
  ) {

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setGenerateDdl(true);

    Properties jpaProperties = new Properties();
    jpaProperties.put("hibernate.hbm2ddl.auto", this.hbm2ddl);
    jpaProperties.put("hibernate.dialect", this.dialect);

    LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
    factoryBean.setJpaVendorAdapter(vendorAdapter);
    factoryBean.setDataSource(dataSource);
    factoryBean.setJpaProperties(jpaProperties);
    factoryBean.setPackagesToScan("com.zeroexception.sqlconnection.postgresmodel");
    factoryBean.afterPropertiesSet();

    return factoryBean.getObject();
  }

  @Bean(name = "postgreTransactionManager")
  public PlatformTransactionManager postgreTransactionManager(
      @Qualifier("postgreEntityManagerFactory")
          EntityManagerFactory postgreEntityManagerFactory) {
    return new JpaTransactionManager(postgreEntityManagerFactory);
  }

//    @Bean(name = "entityManagerFactory")
//    @Primary
//    public EntityManagerFactory entityManagerFactory(EntityManagerFactory entityManagerFactory) {
//        return entityManagerFactory;
//    }


}
