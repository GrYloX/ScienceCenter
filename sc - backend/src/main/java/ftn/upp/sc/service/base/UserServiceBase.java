package ftn.upp.sc.service.base;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.upp.sc.converter.UserConverter;
import ftn.upp.sc.dto.UserDTO;
import ftn.upp.sc.model.User;
import ftn.upp.sc.repository.UserRepository;

public class UserServiceBase {
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public UserConverter userConverter;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findOne(String id) {
		Optional<User> user = userRepository.findById(id);
		return user.get();
	}
	
	public User saveUser(UserDTO dto) {
		User user = userConverter.DtoToEntity(dto);
		return userRepository.save(user);
	}

	public User deleteUser(String id) {
		User user = this.findOne(id);
		if(user == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant");
		}
		userRepository.delete(user);
		return user;
	}
}
