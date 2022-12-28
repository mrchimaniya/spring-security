package com.divergentsl.config;

import javax.sql.DataSource; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer 
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
	
	@Value("${jwt.key}")
	private String jwtKey;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
/* Step1-:
    http://localhost:8080/oauth/token?grant_type=password&username=user&password=123&scope=read
     ===param===
      grant_type=password
      username=user
      password=123  (of user)
      scope=read (optional)
      
      *add client credentials in basic auth username & password
      *you will get a token

    this step is optional it internally uses by resource server
 step 2-:
         http://localhost:8080/oauth/check_token
         ==param==
           token=generated token
           
        if you are using isAuthenticated() so need client credentials also in basic auth
        iff you are using permitAll() so not need client credentials also in basic auth
     
  

step-3 
     turn on resource server app and send request to acess resource server's resource
     send request with generated token
       http://localhost:9090/hello   //resource of resource server
       ==header=
        Authorization= Bearer ee708df2-3255-4ea5-accb-bc7c3f89cb49(token)
     */
	
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception { 
	    clients.inMemory()
	    	.withClient("passclient1")
	    	.secret("123")
	    	.scopes("read")
	    	.accessTokenValiditySeconds(8001/*in seconds*/)
	    	.authorizedGrantTypes("password","refresh_token");
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
		.accessTokenConverter(jwtAccessTokenConverter());
	}
	
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		var converter = new JwtAccessTokenConverter();
		converter.setSigningKey(jwtKey);
		return converter;
	}
}
