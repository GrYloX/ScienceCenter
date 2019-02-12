package ftn.upp.sc.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ftn.upp.sc.converter.user.UserConverter;
import ftn.upp.sc.dto.user.UserDTO;
import ftn.upp.sc.model.users.User;
import ftn.upp.sc.service.user.UserService;


@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserConverter userConverter;
	
	@RequestMapping(value="getUsers", method=RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getUsers(){
		List<User> users = userService.findAll();
		List<UserDTO> usersDTO = new ArrayList<UserDTO>();
		for(User user : users){
			usersDTO.add(userConverter.entityToDto(user));
		}
		return new ResponseEntity<>(usersDTO, HttpStatus.OK);		
	}	
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO){
			
		User newUser = userService.saveUser(userDTO);
		return new ResponseEntity<>(userConverter.entityToDto(newUser), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> getUser(@PathVariable String id) {
		User user = userService.findOne(id);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(userConverter.entityToDto(user), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UserDTO> delete(@PathVariable String id) {
		User deleted = userService.deleteUser(id);
		return new ResponseEntity<>(userConverter.entityToDto(deleted), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<UserDTO> edit(@RequestBody UserDTO userDTO) {
		User edited = userService.saveUser(userDTO);
		return new ResponseEntity<>(userConverter.entityToDto(edited), HttpStatus.OK);
	}
}
