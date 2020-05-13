package ftn.upp.sc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import ftn.upp.sc.service.UserService;

@Component
public class AuthenticationEventListener {

	@Autowired
	private UserService userService;

	@EventListener
	public void authenticationFailed(AuthenticationFailureBadCredentialsEvent event) {
		String username = (String) event.getAuthentication().getPrincipal();
		ftn.upp.sc.model.User user = userService.findOne(username);
		userService.failedLogin(user);
	}

	@EventListener
	public void authenticationSucceeded(AuthenticationSuccessEvent event) {
		User principal = (User) event.getAuthentication().getPrincipal();
		String username = principal.getUsername();
		ftn.upp.sc.model.User user = userService.findOne(username);
		userService.successfulLogin(user);
	}

}
