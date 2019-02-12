package ftn.upp.sc.service.user;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ftn.upp.sc.converter.user.UserConverter;
import ftn.upp.sc.dto.user.UserDTO;
import ftn.upp.sc.model.TransactionState;
import ftn.upp.sc.model.Type;
import ftn.upp.sc.model.payment.MerchantOrder;
import ftn.upp.sc.model.users.User;
import ftn.upp.sc.repository.MerchantOrderRepository;
import ftn.upp.sc.repository.user.UserRepository;

@Service
public class UserService {
	
	@Autowired	
	UserRepository userRepository;
	
	@Autowired
	UserConverter userConverter;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	MerchantOrderRepository merchantOrderRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findOne(String id) {
		Optional<User> user = userRepository.findById(id);
		return user.get();
	}
	
	public User saveUser(UserDTO dto) {
		User user = userConverter.DtoToEntity(dto);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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
	
	public void failedLogin(User user) {
		user.incrementFailedLoginAttempts();
		userRepository.save(user);
	}

	public void successfulLogin(User user) {
		user.setFailedLoginAttempts(0);
		userRepository.save(user);
	}

	public User add(@Valid User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public UserDTO getUserDTO(User user) {
		UserDTO userDTO = userConverter.entityToDto(user);
		List<MerchantOrder> userOrders = merchantOrderRepository.findByBuyer_username(user.getUsername());
		for(MerchantOrder order : userOrders){
			if(order.getTransactionState()==TransactionState.USPEH){
				if(order.getType()==Type.MAGAZINE_EDITION){
					userDTO.getEditions().add(Long.parseLong(order.getPurchaseTypeId()));
				}
				if(order.getType()==Type.PAPER){
					userDTO.getPapers().add(order.getPurchaseTypeId());
				}
			}			
		}
		return userDTO;
	}
}
