package com.lab.aisu.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.lab.aisu.dao")
class DBConfig implements TransactionManagementConfigurer {

	@Autowired
	ApplicationContext ac;
	
//  *** 현재 DBConfig.java는 oracle과 hikariCP를 사용했을 때 기준으로 환경설정이 작성됨.

//	private String driverClassName = "oracle.jdbc.driver.OracleDriver";
	private String driverClassName = "oracle.jdbc.OracleDriver"; // oracle 9 이후
	private String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private String username = "board_ex";
	private String password = "board_ex";

	@Bean // 코배스 pdf p.83 참고 (hikariCP의 dataSource Java Config)
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(driverClassName);
		hikariConfig.setJdbcUrl(url);
		hikariConfig.setUsername(username);
		hikariConfig.setPassword(password);

		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		return dataSource;
	}
	
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return txManager();
	}

	@Bean // 코배스 pdf p.465 참고 (hikariCP 사용 시 TransactionManager Java Config)
	public DataSourceTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean // 코배스 pdf p.90 참고 (Mybatis의 Java Config)
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		// sqlSessionFactory란? -> DataSource를 이용하여 DB와 MyBatis를 연결해준다.

		sqlSessionFactory.setDataSource(dataSource());

		sqlSessionFactory.setConfigLocation(ac.getResource("classpath:/mybatis/mybatis-config.xml"));
		System.out.println("* * * * sqlSessionFactory 설정중");
		sqlSessionFactory.setMapperLocations(ac.getResources("classpath:/mapper/*.xml"));

		return (SqlSessionFactory) sqlSessionFactory.getObject();
	}

	/* ---------------------------------------------------------------------------------------- */

//	*** 부스트코스 - 트랙잭션 관련 Java Config 하기
//	(1) @EnableTransactionManagement 어노테이션: 트랜잭션과 관련된 설정을 자동으로 불러옴.
//	(2) 단, 사용자 간의 트랜잭션 처리를 위한 PlatformTransactionManager를 설정하기 위해서는
//		TransactionManagementConfigurer를 '구현'해야 함. (implements)
//	(3) annotationDrivenTransactionManager() 메소드를 오버라이딩 해야 함.
		
	
	
//  *** MySQL을 사용하고, hikariCP를 사용하지 않을 때의 환경설정	
	
//	private String driverClassName = "com.mysql.jdbc.Driver";
//	private String url = "jdbc:mysql://localhost:3306/connectdb?useUnicode=true&characterEncoding=utf8";
//	private String username = "board_ex";
//	private String password = "board_ex";
//
//	public DataSource dataSource() {
//		BasicDataSource dataSource = new BasicDataSource();
//		dataSource.setDriverClassName(driverClassName);
//		dataSource.setUrl(url);
//		dataSource.setUsername(username);
//		dataSource.setPassword(password);
//
//		return dataSource;
//	}
//
//	@Bean
//	public PlatformTransactionManager transactionManger() {
//		return new DataSourceTransactionManager(dataSource());
//	}

}