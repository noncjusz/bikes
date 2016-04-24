package com.nono;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author nono
 */
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("${spring.datasource.driver}");
        driverManagerDataSource.setUrl("${spring.datasource.url}");
        driverManagerDataSource.setUsername("${spring.datasource.username}");
        driverManagerDataSource.setPassword("${spring.datasource.password}");
        return driverManagerDataSource;
    }
}