package io.sample.service.impl;

import io.sample.bean.ExtendUser;
import io.sample.bean.model.UserModel;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private Md5PasswordEncoder passwordEncoder;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

		logger.info("username >> " + username);

		// Get a user information form DB.
				
		// Or Get the true/false from Active Directory

		// For test
		UserModel userModel = new UserModel();
		userModel.setUserName("admin");
		userModel.setUserPwd("password");
		userModel.setUserStatus("2");

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

        // Add a user's the game Id.
        ExtendUser user = new ExtendUser(userModel.getUserId(), userModel.getUserPwd(), enabled, 
        		accountNonExpired, credentialsNonExpired, accountNonLocked, authorities, userModel);

		return user;
	}

}
