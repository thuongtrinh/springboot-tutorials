//package com.txt.keycloak.authserver.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.PersistenceContext;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.Database;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", basePackages = {
//        "com.txt.keycloak.authserver"})
//@EntityScan(basePackages = {"com.txt.keycloak.authserver"}, basePackageClasses = {
//        Jsr310JpaConverters.class})
//public class PostgresDBConfig {
//
//    @Autowired
//    private Environment env;
//
//
//    @Bean(name = "dataSource")
//    @Primary
//    public DataSource customDataSource() {
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
//        dataSource.setJdbcUrl(env.getProperty("spring.datasource.url"));
//        dataSource.setUsername(env.getProperty("spring.datasource.username"));
//        dataSource.setPassword(env.getProperty("spring.datasource.password"));
//
//        dataSource.setMinimumIdle(env.getProperty("spring.datasource.hikari.minimum-idle", Integer.class));
//        dataSource.setMaximumPoolSize(env.getProperty("spring.datasource.hikari.maximum-pool-size", Integer.class));
//        dataSource.setAutoCommit(env.getProperty("spring.datasource.hikari.auto-commit", Boolean.class));
//        dataSource.setConnectionTimeout(env.getProperty("spring.datasource.hikari.connection-timeout", long.class));
//        dataSource.setIdleTimeout(env.getProperty("spring.datasource.hikari.idle-timeout", long.class));
//        dataSource.setMaxLifetime(env.getProperty("spring.datasource.hikari.max-lifetime", long.class));
//        dataSource.setPoolName(env.getProperty("spring.datasource.hikari.pool-name"));
//        dataSource.setConnectionTestQuery(env.getProperty("spring.datasource.hikari.connection-test-query"));
//
//        return dataSource;
//    }
//
//
//    private JpaVendorAdapter jpaVendorAdapter() {
//        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
//        hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
//        hibernateJpaVendorAdapter.setShowSql(true);
//        hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
//        return hibernateJpaVendorAdapter;
//
//    }
//
//    @Primary
//    @Bean(name = "transactionManager")
//    @Qualifier(value = "entityManager")
//    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }
//
//    @Primary
//    @Bean(name = "jdbcTemplate")
//    public JdbcTemplate jdbcTemplatePruonline(@Qualifier("dataSource") DataSource dataSource) {
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//        return jdbcTemplate;
//    }
//
//    @Primary
//    @PersistenceContext(unitName = "ecustomerUnit")
//    @Bean(name = "entityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
//                                                                       @Qualifier("dataSource") DataSource dataSource) {
//
//        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = builder.dataSource(dataSource)
//                .persistenceUnit("ecustomerUnit").build();
//        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
//        localContainerEntityManagerFactoryBean.setPackagesToScan("com.txt.keycloak.authserver");
//        return localContainerEntityManagerFactoryBean;
//    }
//
//}
