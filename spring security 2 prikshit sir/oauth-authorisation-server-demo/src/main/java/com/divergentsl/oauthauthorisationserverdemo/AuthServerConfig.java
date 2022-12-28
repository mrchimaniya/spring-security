package com.divergentsl.oauthauthorisationserverdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
	@Autowired

	private AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.authenticationManager(authenticationManager);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		clients.inMemory()
				// Password grant type
				.withClient("client").secret("secret").authorizedGrantTypes("password").scopes("read")
				// authorization code grant type
				.and().withClient("client1").secret("secret1").authorizedGrantTypes("authorization_code").scopes("read")
				.redirectUris("http://localhost:8080/authorization")
				// client_credentials grant type
				.and().withClient("client2").secret("secret2").authorizedGrantTypes("client_credentials").scopes("info")
				// with refresh_token grant type
				.and().withClient("client3").secret("secret3").authorizedGrantTypes("password", "refresh_token")
				.scopes("read")
				//Adds a set of credentials for the resource server to use when calling the /oauth/check_token endpoint
				.and().withClient("resourceserver").secret("resourceserversecret");

	}
	
	

	public void configure(AuthorizationServerSecurityConfigurer security) {
		// Specifies the condition for which we can call the "check_token" endpoint
		// this will be called form the resoruce server to validate the token
		security.checkTokenAccess("isAuthenticated()");
	}

}
