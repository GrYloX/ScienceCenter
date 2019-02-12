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

import ftn.upp.sc.converter.user.UserDetailsConverter;
import ftn.upp.sc.dto.user.UserDetailsDTO;
import ftn.upp.sc.model.users.UserDetails;
import ftn.upp.sc.service.user.UserDetailsService;



@Controller
@RequestMapping(value="/userDetails")
public class UserDetailsController {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserDetailsConverter userDetailsConverter;
	
	@RequestMapping(value="getUserDetailss", method=RequestMethod.GET)
	public ResponseEntity<List<UserDetailsDTO>> getUserDetailss(){
		List<UserDetails> userDetailss = userDetailsService.findAll();
		List<UserDetailsDTO> userDetailssDTO = new ArrayList<UserDetailsDTO>();
		for(UserDetails userDetails : userDetailss){
			userDetailssDTO.add(userDetailsConverter.entityToDto(userDetails));
		}
		return new ResponseEntity<>(userDetailssDTO, HttpStatus.OK);		
	}	
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<UserDetailsDTO> addUserDetails(@RequestBody UserDetailsDTO userDetailsDTO){
			
		UserDetails newUserDetails = userDetailsService.saveUserDetails(userDetailsDTO);
		return new ResponseEntity<>(userDetailsConverter.entityToDto(newUserDetails), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDetailsDTO> getUserDetails(@PathVariable Long id) {
		UserDetails userDetails = userDetailsService.findOne(id);
		if (userDetails == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(userDetailsConverter.entityToDto(userDetails), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UserDetailsDTO> delete(@PathVariable Long id) {
		UserDetails deleted = userDetailsService.deleteUserDetails(id);
		return new ResponseEntity<>(userDetailsConverter.entityToDto(deleted), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<UserDetailsDTO> edit(@RequestBody UserDetailsDTO userDetailsDTO) {
		UserDetails edited = userDetailsService.saveUserDetails(userDetailsDTO);
		return new ResponseEntity<>(userDetailsConverter.entityToDto(edited), HttpStatus.OK);
	}
}
