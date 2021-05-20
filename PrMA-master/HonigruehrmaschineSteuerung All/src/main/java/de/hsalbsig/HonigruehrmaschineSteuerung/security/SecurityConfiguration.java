package de.hsalbsig.HonigruehrmaschineSteuerung.security;

import de.hsalbsig.HonigruehrmaschineSteuerung.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

/**
 * @author
 */

@EnableWebSecurity
public class SecurityConfiguration {


	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests(authorizeRequests ->
				authorizeRequests
						.mvcMatchers("/assets/**").permitAll()
						.mvcMatchers( "/error").permitAll()
						.mvcMatchers( "/join").permitAll()
						.anyRequest().authenticated()
		).formLogin().loginPage("/login").permitAll()
				.and().cors()
				.and().csrf()
				.and().logout().logoutUrl("/logout");
		return http.build();
	}

	@Bean
	UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	PasswordEncoder passwordEncoder (){
		return new BCryptPasswordEncoder();
	}


}
