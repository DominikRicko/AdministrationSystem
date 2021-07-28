package com.samuraiDigital.adminsystem.security;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Order(SecurityProperties.IGNORED_ORDER)
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfiguration {
	
	
	@Configuration
	@Order(2)
	public static class TestConfigurationAdapter extends WebSecurityConfigurerAdapter{

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().anyRequest().denyAll();
		}
		
	}

	@Configuration
	@Order(1)
	public static class Test2ConfigurationAdapter extends WebSecurityConfigurerAdapter{

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/login").permitAll();
		}
		
	}
	
//
//	@Configuration
//	@Order(Ordered.LOWEST_PRECEDENCE)
//	public static class GlobalConfigurationAdapter extends WebSecurityConfigurerAdapter {
//
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//
//			http.csrf().disable();
//
//			http.authorizeRequests().antMatchers("/js/**", "/images/**", "/css/**").permitAll();
//			http.authorizeRequests().anyRequest().authenticated();
//
//			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		}
//
//	}
//
//	@Configuration
//	@Order(5)
//	public static class LoginConfigurationAdapter extends WebSecurityConfigurerAdapter {
//
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//
//			http.csrf().disable();
//
//			http.authorizeRequests()
//					.antMatchers("/login", "/perform_login", "/register", "/perform_register", "/reset_password")
//					.permitAll();
//
//			http.formLogin().loginPage("/login").loginProcessingUrl("/perform_login").and().logout()
//					.logoutUrl("/perform_logout").deleteCookies("JSESSIONID");
//			
//			http.authorizeRequests().anyRequest().authenticated();
//
//			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
//		}
//
//	}
//
//	@Configuration
//	@Order(9)
//	public static class EApiConfigurationAdapter extends WebSecurityConfigurerAdapter {
//
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//
//			http.csrf().disable();
//
//			http.authorizeRequests().antMatchers("e/api/**").authenticated().and().httpBasic();
//
//			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//		}
//	}
//
//	@Configuration
//	@Order(1)
//	public static class IApiConfigurationAdapter extends WebSecurityConfigurerAdapter {
//
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			http.csrf().disable();
//
//			http.authorizeRequests().antMatchers("i/api/**").permitAll().and().httpBasic();
//
//			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		}
//
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
