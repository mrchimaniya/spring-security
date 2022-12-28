package com.divergentsl.oauthsecuritythirdparty;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	 * @Bean public ClientRegistrationRepository clientRepository() { var c =
	 * clientRegistration(); return new InMemoryClientRegistrationRepository(c); }
	 * 
	 * private ClientRegistration clientRegistration() {
	 * 
	 * 
	 * ClientRegistration cr =
	 * ClientRegistration.withRegistrationId("github").clientId(
	 * "1900cf5b3204d28918da")
	 * .clientSecret("ce892760d0616598dfcfff8f788b46fb13b9fe9e").scope(new String[]
	 * { "read:user" })
	 * .authorizationUri("https://github.com/login/oauth/authorize")
	 * .tokenUri("https://github.com/login/oauth/access_token").userInfoUri(
	 * "https://api.github.com/user")
	 * .userNameAttributeName("id").clientName("GitHub")
	 * .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
	 * .redirectUriTemplate("{baseUrl}/{action}/oauth2/code/{registrationId}").build
	 * (); retrun cr;
	 * 
	 * 
	 * return CommonOAuth2Provider.GITHUB.getBuilder("github").clientId(
	 * "1900cf5b3204d28918da")
	 * .clientSecret("ce892760d0616598dfcfff8f788b46fb13b9fe9e").build(); }
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// setting the auth method
		http.oauth2Login();
		/*
		 * http.oauth2Login(c -> { c.clientRegistrationRepository(clientRepository());
		 * });
		 */

		// a user needs to be authenticated to make a request
		http.authorizeRequests().anyRequest().authenticated();
	}

}
