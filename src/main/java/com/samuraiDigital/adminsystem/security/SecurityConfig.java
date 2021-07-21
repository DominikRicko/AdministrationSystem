package com.samuraiDigital.adminsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests().antMatchers("/login", "/perform_login", "/register", "/reset_password", "/js/*", "/images/*", "/css/*").permitAll()
		.anyRequest().authenticated().and()
		.formLogin().loginPage("/login")
		.loginProcessingUrl("/perform_login").and()
		.logout().logoutUrl("/perform_logout").deleteCookies("JSESSIONID");

	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}
