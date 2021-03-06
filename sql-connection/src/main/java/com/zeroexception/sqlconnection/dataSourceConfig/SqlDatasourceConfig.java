package com.zeroexception.sqlconnection.dataSourceConfig;


import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
    basePackages = {"com.zeroexception.sqlconnection.serviceLayer.animalRepo"}
)

public class SqlDatasourceConfig {


  private String username;
  private String password;
  private String url;
  private String driverClassName;
  private String dialect;
  private String hbm2ddl;

  @Autowired
  public SqlDatasourceConfig(@Value("${spring.datasource.username}") String username,
      @Value("${spring.datasource.password}") String password,
      @Value("${spring.datasource.url}") String url,
      @Value("${spring.datasource.driver-class-name}") String driverClassName,
      @Value("${spring.jpa.database-platform}") String dialect,
      @Value("${spring.jpa.hibernate.ddl-auto}") String hbm2ddl) {
    this.username = username;
    this.password = password;
    this.url = url;
    this.driverClassName = driverClassName;
    this.dialect = dialect;
    this.hbm2ddl = hbm2ddl;
  }

  @Bean(name = "dataSource")
//    @ConfigurationProperties(prefix = "")
  @Primary
  public DataSource dataSource() {

    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create()
        .driverClassName(this.driverClassName)
        .username(this.username)
        .password(this.password)
        .url(this.url);
    return dataSourceBuilder.build();

  }

  @Bean(name = "entityManagerFactory")
  @Primary
  public EntityManagerFactory
  postgreEntityManagerFactory(@Qualifier("dataSource") DataSource dataSource
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
//        Set the package of data Model which uses this dataSource to persist data
    factoryBean.setPackagesToScan("com.zeroexception.sqlconnection.mysqlmodel");

    factoryBean.afterPropertiesSet();

    return factoryBean.getObject();
  }


  @Bean(name = "transactionManager")
  public PlatformTransactionManager postgreTransactionManager(
      @Qualifier("entityManagerFactory")
          EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }

  @Bean
  public JdbcTemplate jdbcTemplate(@Qualifier("dataSource") DataSource dataSource) {
    return new JdbcTemplate(dataSource, true);
  }

}
