package ftn.upp.sc.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ftn.upp.sc.service.user.UserService;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserService userService;
//	private PrivilegeService privilegeService;

	public UserDetailsServiceImpl(UserService userService
			/*, PrivilegeService privilegeService */) {
		this.userService = userService;
//		this.privilegeService = privilegeService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ftn.upp.sc.model.users.User user = userService.findOne(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("Reader"));
//		List<Privilege> privileges = privilegeService.findAllForRole(applicationUser.getUserType());
//		for(Privilege privilege : privileges) {
//			authorities.add(new SimpleGrantedAuthority(privilege.getName()));
//		}

		return new User(user.getUsername(),
				user.getPassword(),
				user.getFailedLoginAttempts() < SecurityConstants.MAX_FAILED_ATTEMPTS,
				true,
				true,
				true,
				authorities);
	}

}