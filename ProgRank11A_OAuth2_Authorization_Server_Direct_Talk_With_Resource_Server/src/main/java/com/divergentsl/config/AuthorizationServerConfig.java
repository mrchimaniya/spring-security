package com.divergentsl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer 
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
	
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

    this step is otional it internally uses by resource server
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
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	   //we can use bcrypt of using autowired
	   //but here if client wants different encoder so you can configure there it will encode password automatically
		security.passwordEncoder(NoOpPasswordEncoder.getInstance());
		
//		/oauth/check_token by default deny all request so we have to define soem methods here		
		security.checkTokenAccess("isAuthenticated()"); // this needs client credentials in request in basic authentication
//		security.checkTokenAccess("permitAll()");    //this will allow all request without credentials
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception { 
	    clients.inMemory()
	    	.withClient("passclient1")
	    	.secret("123")
	    	.scopes("read")
	    	.accessTokenValiditySeconds(8001/*in seconds*/)
	    	.authorizedGrantTypes("password","refresh_token")
	 //Add a set of credentials for the resource server to use when calling the /oauth/check_token endpoint
	    	.and().withClient("resourceserver").secret("resourceserversecret");
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager);
	}
}
