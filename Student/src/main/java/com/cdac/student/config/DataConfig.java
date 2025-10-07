package com.cdac.student.config;

import com.cdac.student.security.jwt.JwtUtils;
import jakarta.persistence.EntityManagerFactory;
import java.util.Properties;
import javax.sql.DataSource;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.*;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.*;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.cdac.student")
@EnableJpaRepositories(basePackages = "com.cdac.student.dao") 
public class DataConfig {

    @Bean
    public DataSource dataSource() {
        var ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.mariadb.jdbc.Driver");
        // IMPORTANT: add auth/TLS flags if your server is MySQL-8 style auth
        ds.setUrl("jdbc:mariadb://localhost:3307/user?allowPublicKeyRetrieval=true&useSSL=false");
        ds.setUsername("root");
        ds.setPassword("manager");
        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource ds) {
        var emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(ds);
        emf.setPackagesToScan("com.cdac.student.entity"); // your @Entity package

        var vendor = new HibernateJpaVendorAdapter();
        vendor.setDatabasePlatform("org.hibernate.dialect.MariaDBDialect");
        vendor.setShowSql(true);
        vendor.setGenerateDdl(true);
        emf.setJpaVendorAdapter(vendor);

        var props = new Properties();
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.format_sql", "true");
        emf.setJpaProperties(props);
        return emf;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
   
    @Bean
    public JwtUtils jwtUtils() { return new JwtUtils(); }
    
}
