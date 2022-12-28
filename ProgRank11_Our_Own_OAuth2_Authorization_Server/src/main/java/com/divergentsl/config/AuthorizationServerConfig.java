package com.divergentsl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer //it will become authorization server
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	//authorization server has to be told to genrate token for particular request for eg abc.com
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception { 
		super.configure(clients);
	
		/* ======note for all grant types always send client username & password in basic authentication area in post man========*/
		
 /* Step=1  http://localhost:8080/oauth/token
            ===param===
              grant_type=password
              username=user
              password=123
              scope=read  (Optional)
          = Choose BASIC AUTHENTICATION and send client username & password (passcliet1/passcliet2)
          = Here you can get token with username & password of client and user both and app through
			  (user->client->authorization Server)
			  */
					
		//storing client details in authorization server memory
		clients.inMemory()
		
		//client 1
		.withClient("passclient1")
		.secret("123")
		.scopes("read").authorizedGrantTypes("password","refresh_token")
		//grant type password drawback is that the user credentials will we available with 2 client but it is almost deprecated 
		
		//client 2
		.and()
		.withClient("passclient2")
		.secret("123")
		.scopes("read").authorizedGrantTypes("password")
		
		
    /*  = Assume this time already our facebook is logged in, so here user will not give facebook
		 it's credential to client  he will give directly to the authorization server.
		 and authroization server send token directly to the client
		 
		= Means request by bypass to the authorization server direclty see diagram in copy
		= But here we have to create a form to get credentials to user for sending Authorization Server 
		*/
		
/*  Step=2  here we have to use oauth/authorize because we are authorizing directly from server
		 
		  *****this step must be in browser not postman*****
          http://localhost:8080/oauth/authorize
        	  ==param==
            response_type=code (response_type=code means grant types "authroization_code" internally)
            client_id=dell
            scope=read (Optional)
            
		 = You will get a form and fill USER Details NOT CLIENT then,we are directly go to 
		   the authorization server after approve you will get a CODE=xxx in address bar
		   because it will redirect to other app because we have given and url for redirecting
		
		   *****this step must be in Postman*****
          http://localhost:8080/oauth/token
        	 ==param==
        	 grant_type=authorization_code
        	 code=xxx
        	 scope=read (optional)
          = Choose BASIC AUTHENTICATION and send client username & password (authcodeclient) 
	      = Here you can get token with the code without sending username password to the client directly send to the auhentication server
		  = Entering same code but you can'nt send request again it will get error
		*/
		
//		client seprated with 1 & 2 either use this or above
		.and()
		.withClient("authcodeclient")
		.secret("123")
		.scopes("read").authorizedGrantTypes("authorization_code")
		.redirectUris("http://localhost:8083")
		
/*   	we are going to use client credential grant type here client will send
		only client credentials to the authorization server and it will generate token
		
Step=3  http://localhost:8080/oauth/token
            ===param===
              grant_type=client_credentials
              scope=read  (Optional)
          = Choose BASIC AUTHENTICATION and send client username & password (clientcredclient)
          = Here you can get token with username & password of client only here no role of user credentials
			  */
		.and()
		.withClient("clientcredclient")
		.secret("123")
		.scopes("info").authorizedGrantTypes("client_credentials");
	
/* Step=4 is refresh_token you have 
 *  this token is used with one more token
 *  like   ....()...().authorizedGrantTypes("password","refresh_token");
 *  
 *  you will get two token acess token,refresh token
 *   
 *     so the point is that acess token has some expiry and after expiry if you want to regenrate it
 *     so you have to complete process from begining but with the help of refresh_token you can re genrate
 *     acess token easily  */
	}
	
	
	//we are connecting user with authorization server means there will a user on authorization server for verification
	// and this class it authorization server because we use @EnableAuthorizationServer
	
	//authorization server requires user info but as well as client info also
	// to verify user becasue client can be anyone for that it requies client info also
	
	  //send POST req with basic authentication and send client details not user like aayush and 123 password and the link below and we will get a token
	    //  /oauth/token comes in console  like [Ant [pattern='/oauth/token'], Ant [pattern='/oauth/token_key']
	 
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager);
	}
}
