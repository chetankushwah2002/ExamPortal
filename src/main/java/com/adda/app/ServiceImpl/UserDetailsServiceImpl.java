package com.adda.app.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.adda.app.Reposatotries.IUserReposatory;
import com.adda.app.entity.User;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private IUserReposatory urepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = this.urepo.findByusername(username);
		if(user == null) 
		{
			System.out.println("User Not Found");
			throw new UsernameNotFoundException("no user found");
		}
		List<GrantedAuthority> authorities = user.getUserRoles()
				.stream().map(role->new SimpleGrantedAuthority(role.getRole().getRoleName()))
				.collect(Collectors.toList());
		return new org.springframework.security.core.userdetails.User(username,user.getPassword(),authorities);
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() 
	{
		return new BCryptPasswordEncoder();
	}

}
