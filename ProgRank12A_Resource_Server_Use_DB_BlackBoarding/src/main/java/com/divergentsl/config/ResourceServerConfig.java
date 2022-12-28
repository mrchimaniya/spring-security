package com.divergentsl.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore());
	}
	
	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}
	/* JdbcTokenStore assume you have already tables
	
			create table oauth_access_token (
			  token_id VARCHAR(256),
			  token BLOB,
			  authentication_id VARCHAR(256) PRIMARY KEY,
			  user_name VARCHAR(256),
			  client_id VARCHAR(256),
			  authentication BLOB,
			  refresh_token VARCHAR(256)
			);
			
			create table oauth_refresh_token (
			  token_id VARCHAR(256) PRIMARY KEY,
			  token BLOB,
			  authentication BLOB
			);

	 */
}
