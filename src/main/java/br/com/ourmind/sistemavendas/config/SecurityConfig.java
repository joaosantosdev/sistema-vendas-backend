package br.com.ourmind.sistemavendas.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.ourmind.sistemavendas.security.JWTAuthenticationFilter;
import br.com.ourmind.sistemavendas.security.JWTAuthorizationFilter;
import br.com.ourmind.sistemavendas.security.JWTUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private Environment env;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	private static final String[] PUBLIC_MATCHERS = {
			"/h2-console/**",
	};
	
	private static final String[] PUBLIC_MATCHERS_POST = {
			"/clients/**",
			"/auth/forgot/**",
	};
	
	private static final String[] PUBLIC_MATCHERS_GET = {
			"/products/**",
			"/categories/**",
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		if(Arrays.asList(this.env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		
		http.cors().and().csrf().disable();
		http
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, SecurityConfig.PUBLIC_MATCHERS_POST).permitAll()
		.antMatchers(HttpMethod.GET, SecurityConfig.PUBLIC_MATCHERS_GET).permitAll()
		.antMatchers(SecurityConfig.PUBLIC_MATCHERS).permitAll()
		.anyRequest().authenticated();
		
		http.addFilter(new JWTAuthenticationFilter(this.authenticationManager(), this.jwtUtil));
		
		http.addFilter(new JWTAuthorizationFilter(this.authenticationManager(), this.jwtUtil, this.userDetailsService));
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	public void configurer(AuthenticationManagerBuilder auth) throws Exception{
		auth
		.userDetailsService(this.userDetailsService)
		.passwordEncoder(this.bCryptPasswordEncoder());
	}
	
	
	

}
