package io.sample.provider;

import io.sample.bean.ExtendUser;
import io.sample.bean.model.UserModel;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class DefaultAuthenticationProvider implements AuthenticationProvider {

	final Logger logger = LoggerFactory.getLogger(DefaultAuthenticationProvider.class);
	@Autowired
	private Md5PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication)
		throws AuthenticationException {
		Object userName = authentication.getPrincipal();
		Object userPwd = authentication.getCredentials();

		UsernamePasswordAuthenticationToken upat = null;
		ExtendUser user = null;
		UserModel userModel = null;

		try {
			logger.info("UserDetailsService >> userName >1> " + userName);

			/////////////// You can make another authentication/////////////

			userModel = new UserModel();
			userModel.setUserId("testID");
			userModel.setUserName("admin");
			userModel.setUserPwd("test");
			if(userName.equals("admin")) {
				userModel.setUserStatus("1");				
			} else {
				userModel.setUserStatus("2");				
			}

	        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	        // For java1.6
	        switch(Integer.valueOf(userModel.getUserStatus())) {
	            case 1:
	                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
	                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
	            break;
	            default:
	                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
	            break;
	        }

	        boolean enabled = true;
	        boolean accountNonExpired = true;
	        boolean credentialsNonExpired = true;
	        boolean accountNonLocked = true;

	        user = new ExtendUser(userModel.getUserId(),  passwordEncoder.encodePassword("test", null), enabled, 
	        		accountNonExpired, credentialsNonExpired, accountNonLocked, authorities, userModel);

	        upat = new UsernamePasswordAuthenticationToken(user, passwordEncoder.encodePassword("test", null), authorities);
	        upat.setDetails(authentication.getDetails());

		} catch (Exception e) {
			logger.error("Select error, userId={}, userStatus={}", userModel.getUserId(), userModel.getUserStatus());
			logger.error("Exception >> ", e);
		}

			 return upat;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}