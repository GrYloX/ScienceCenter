package ftn.upp.sc.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.upp.sc.dto.UserDTO;
import ftn.upp.sc.model.User;
import ftn.upp.sc.service.UserService;


@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

	@Autowired
	private UserService userService;

	@PreAuthorize("isAuthenticated()")
	@GetMapping
	public ResponseEntity<UserDTO> getCurrentUser(Principal principal) {
		User user = userService.findOne(principal.getName());
		if(user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		UserDTO userDTO = userService.getUserDTO(user);
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}

}
