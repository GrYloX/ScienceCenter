package ftn.upp.sc.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.upp.sc.converter.user.UserDetailsConverter;
import ftn.upp.sc.dto.user.UserDetailsDTO;
import ftn.upp.sc.model.users.UserDetails;
import ftn.upp.sc.repository.user.UserDetailsRepository;

@Service
public class UserDetailsService {
	
	@Autowired	
	UserDetailsRepository userDetailsRepository;
	
	@Autowired
	UserDetailsConverter userDetailsConverter;
	
	public List<UserDetails> findAll() {
		return userDetailsRepository.findAll();
	}
	
	public UserDetails findOne(Long id) {
		Optional<UserDetails> inv = userDetailsRepository.findById(id);
		return inv.get();
	}
	
	public UserDetails saveUserDetails(UserDetailsDTO dto) {
		UserDetails inv = userDetailsConverter.DtoToEntity(dto);
		return userDetailsRepository.save(inv);
	}

	public UserDetails deleteUserDetails(Long id) {
		UserDetails i = this.findOne(id);
		if(i == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant");
		}
		userDetailsRepository.delete(i);
		return i;
	}
}
