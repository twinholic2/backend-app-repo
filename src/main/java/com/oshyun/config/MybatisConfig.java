package com.oshyun.config;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.oshyun.bbs.repository.EmployeeRepository;
import com.oshyun.bbs.repository.MybatisJdbcEmployeeRepository;

@Configuration
@MapperScan(basePackages="com.oshyun.bbs.mapper.**", sqlSessionFactoryRef="SqlSessionFactory")
public class MybatisConfig implements WebMvcConfigurer {
	
	//application.properties 에 넣고 값을 불러와서 하는방식 , 또는 직접 하드코딩
	@Value("${mybatis.mapper-locations}")
    String mPath;
	
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	
	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	@Bean(name = "dataSource")
	DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		return dataSource;
	}
	
    //@Bean(name = "dataSource")
    //@ConfigurationProperties(prefix = "spring.datasource")
   // DataSource DataSource() {
   //     return DataSourceBuilder.create().build();
   // }	
    
    @Bean(name = "SqlSessionFactory")
    SqlSessionFactory SqlSessionFactory(@Qualifier("dataSource") DataSource DataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(DataSource);
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources(mPath));
        return sqlSessionFactoryBean.getObject();
    }
    
    @Bean(name = "SessionTemplate")
    SqlSessionTemplate SqlSessionTemplate(@Qualifier("SqlSessionFactory") SqlSessionFactory firstSqlSessionFactory) {
        return new SqlSessionTemplate(firstSqlSessionFactory);
    }
    
    @Bean(name = "mybatisRepository")
    public EmployeeRepository employeeRepository() {
        //return new JdbcMemberRepository(dataSource);
        return new MybatisJdbcEmployeeRepository();
        //return new JdbcMemberRepository();
    }
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
        	.allowedOrigins("http://eks.oshyun.com");
            //.allowedOrigins("http://localhost:3000");
    }
}
