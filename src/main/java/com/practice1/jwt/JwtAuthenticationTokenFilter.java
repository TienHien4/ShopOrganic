package com.practice1.jwt;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import com.practice1.conf.Customer.UserDetailsServiceIplm;
import com.practice1.service.iplm.JwtTokenProvider;

@Component
public class JwtAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter{
	private final static String TOKEN_HEADER = "Authorization";
	private final JwtTokenProvider jwt = new JwtTokenProvider();
	@Autowired
	private UserDetailsServiceIplm userService;
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httRequest =(HttpServletRequest) request;
		String authToken = httRequest.getHeader(TOKEN_HEADER);
		if(jwt.validateTokenLogin(authToken)) {
			String username = jwt.getUserNameFormToken(authToken);
			UserDetails user = userService.loadUserByUsername(username);
			if(user!=null) {
				boolean enabled = true;
				boolean accountNonExpired = true;
				boolean credentialsNonExpired = true;
				boolean accountNonLocked = true;
				UserDetails userDetails = new User(username, user.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, user.getAuthorities());
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httRequest));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		
		super.doFilter(request, response, chain);
	}

}
