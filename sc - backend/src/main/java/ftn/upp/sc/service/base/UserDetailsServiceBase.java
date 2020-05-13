package ftn.upp.sc.service.base;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.upp.sc.converter.UserDetailsConverter;
import ftn.upp.sc.dto.UserDetailsDTO;
import ftn.upp.sc.model.UserDetails;
import ftn.upp.sc.repository.UserDetailsRepository;

public class UserDetailsServiceBase {
	
	@Autowired
	public UserDetailsRepository userDetailsRepository;
	
	@Autowired
	public UserDetailsConverter userDetailsConverter;
	
	public List<UserDetails> findAll() {
		return userDetailsRepository.findAll();
	}
	
	public UserDetails findOne(long id) {
		Optional<UserDetails> userDetails = userDetailsRepository.findById(id);
		return userDetails.get();
	}
	
	public UserDetails saveUserDetails(UserDetailsDTO dto) {
		UserDetails userDetails = userDetailsConverter.DtoToEntity(dto);
		return userDetailsRepository.save(userDetails);
	}

	public UserDetails deleteUserDetails(long id) {
		UserDetails userDetails = this.findOne(id);
		if(userDetails == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant");
		}
		userDetailsRepository.delete(userDetails);
		return userDetails;
	}
}
