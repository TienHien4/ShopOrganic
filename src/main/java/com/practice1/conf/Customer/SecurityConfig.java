package com.practice1.conf.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.BeanIds;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.practice1.jwt.JwtAuthenticationTokenFilter;
import com.practice1.service.iplm.JwtTokenProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	 private JwtTokenProvider tokenProvider;
	@Autowired
	    private UserDetailsServiceIplm customUserDetailsService;

	  @Bean
	    public UserDetailsService userDetailsService(){
	        return new UserDetailsServiceIplm();
	    }
	  
	  @Bean
	    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception{
	        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
	        jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
			return jwtAuthenticationTokenFilter;
	    }

	    @Bean
	    public BCryptPasswordEncoder passwordEncoder(){
	        return new BCryptPasswordEncoder();
	    }
	    
	    @Bean
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        // Get AuthenticationManager bean
	        return super.authenticationManagerBean();
	    }

	    @Bean
	    public DaoAuthenticationProvider provider(){
	        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	        provider.setPasswordEncoder(passwordEncoder());
	        provider.setUserDetailsService(userDetailsService());
	        return provider;
	    }

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(provider());
	    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/admin/*")
		.hasRole("ADMIN")
		.antMatchers("/*")
		.permitAll()
		.antMatchers("/signin").permitAll()
		.and().formLogin().loginPage("/signin").loginProcessingUrl("/login")
				.defaultSuccessUrl("/index").and().csrf().disable();
		
		
		http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

		
		
		
		

	}

}
