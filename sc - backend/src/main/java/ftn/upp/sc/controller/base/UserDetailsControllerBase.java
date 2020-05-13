package ftn.upp.sc.controller.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ftn.upp.sc.converter.UserDetailsConverter;
import ftn.upp.sc.dto.UserDetailsDTO;
import ftn.upp.sc.model.UserDetails;
import ftn.upp.sc.service.UserDetailsService;

@RequestMapping(value="/userDetails")
public class UserDetailsControllerBase {

	@Autowired
	public UserDetailsService userDetailsService;
	
	@Autowired
	public UserDetailsConverter userDetailsConverter;
	
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
	public ResponseEntity<UserDetailsDTO> getUserDetails(@PathVariable long id) {
		UserDetails userDetails = userDetailsService.findOne(id);
		if (userDetails == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(userDetailsConverter.entityToDto(userDetails), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UserDetailsDTO> delete(@PathVariable long id) {
		UserDetails deleted = userDetailsService.deleteUserDetails(id);
		return new ResponseEntity<>(userDetailsConverter.entityToDto(deleted), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<UserDetailsDTO> edit(@RequestBody UserDetailsDTO userDetailsDTO) {
		UserDetails edited = userDetailsService.saveUserDetails(userDetailsDTO);
		return new ResponseEntity<>(userDetailsConverter.entityToDto(edited), HttpStatus.OK);
	}
}
