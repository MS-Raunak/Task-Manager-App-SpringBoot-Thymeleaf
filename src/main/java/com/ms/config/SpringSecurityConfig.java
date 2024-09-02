package com.ms.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity  http) throws Exception {
		http
			.csrf(csrf->csrf.disable())
			.authorizeHttpRequests(authorize->{
			//authorize.requestMatchers(HttpMethod.POST, "/**").hasRole("ADMIN"); //Role based authentication
			//authorize.requestMatchers("/tasks/delete/**").hasRole("ADMIN"); //only admin can perform delete and post
			//authorize.requestMatchers("/tasks/edit/**").hasRole("ADMIN");
			//authorize.anyRequest().authenticated();
			authorize.anyRequest().authenticated();
			})
			.httpBasic(Customizer.withDefaults());
			
		
		return http.build();
	}
	
	
	//In-memory authentication
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.builder()
						 .username("user")
						 .password(passwordEncoder().encode("123"))
						 .roles("USER")
						 .build();
		UserDetails user2 = User.builder()
				            .username("admin")
				            .password(passwordEncoder().encode("123"))
				            .roles("ADMIN")
				            .build();
		
		return new InMemoryUserDetailsManager(user, user2);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
