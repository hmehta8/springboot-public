package com.multiplayerwordle.app.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class WordleConfig {

	@Value("${database.url}")
	String url;
	
	@Value("${database.password}")
	String password;
	
	@Value("${database.driver}")
	String driver;
	
	@Value("${database.username}")
	String username;
	
	@Value("${database.minimumIdle}")
	Integer minIdleSize;
	
	@Value("${database.maximumPoolSize}")
	Integer maxPoolSize;
	
	@Value("${database.maxLifeTime}")
	Long maxLifeTime;
	
	private DataSource ds = null;
	
	@Bean
	public DataSource dataSource() {
		
		if(ds == null) {
			HikariConfig config = new HikariConfig();
			config.setDriverClassName(driver);
			config.setJdbcUrl(url);
			config.setUsername(username);
			config.setPassword(password);
			config.setMinimumIdle(minIdleSize);
			config.setMaximumPoolSize(maxPoolSize);
			config.setMaxLifetime(maxLifeTime);
			config.addDataSourceProperty("cachePrepStmts", "true");
			config.addDataSourceProperty("prepStmtCacheSize", "250");
			config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
			
			ds = new HikariDataSource(config);
			
		} 
		
		return ds;
		
	}
	
	
}
