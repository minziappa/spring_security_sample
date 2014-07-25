package io.sample.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class DefaultAuthenticationProvider implements AuthenticationProvider {

	final Logger logger = LoggerFactory.getLogger(DefaultAuthenticationProvider.class);

	@Override
	public Authentication authenticate(Authentication authentication)
		throws AuthenticationException {
		 //String name = authentication.getName();
	     //String password = authentication.getCredentials().toString();

		 Object userId = authentication.getPrincipal();
		 Object userPwd = authentication.getCredentials();

		 logger.info("This is the first check");
		 boolean blnCheck = false;
		 // check

//		 // Check Authentication, Authorization
//		 if(!blnCheck) {
//			 throw new BadCredentialsException("Username does not match for " + principal);
//		 }

		 //Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		 //roles.add(new SimpleGrantedAuthority("ROLE_USER"));

		 // return new UsernamePasswordAuthenticationToken(principal, password, null);
		 return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}