package org.mdn.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));

		return new org.springframework.security.core.userdetails.User("admin", "admin", true, true, true, true,
				authorities);

	}

}