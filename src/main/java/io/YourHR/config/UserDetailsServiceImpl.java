package io.YourHR.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.YourHR.Entity.UserEntity;
import io.YourHR.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	public UserDetailsServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userdtls = userRepository.findByEmail(username);
//		System.out.println(userdtls.getEmail());
//		System.out.println(userdtls.getPassword());
		if(userdtls == null) {
			throw new UsernameNotFoundException("User not found!");
		}
		else {
			CustomUserDtls customUser = new CustomUserDtls(userdtls);
			return customUser;
		}
	}
	
}
