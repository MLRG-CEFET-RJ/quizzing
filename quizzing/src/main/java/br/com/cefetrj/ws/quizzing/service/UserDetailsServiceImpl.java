package br.com.cefetrj.ws.quizzing.service;

import br.com.cefetrj.ws.quizzing.model.user.ApplicationUser;
import br.com.cefetrj.ws.quizzing.repository.jpaRepository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
	private UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		ApplicationUser user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found"));
		if (user == null)
		{
			throw new UsernameNotFoundException(username);
		}
		return new User(user.getUsername(), user.getPassword(), emptyList());
	}
}
