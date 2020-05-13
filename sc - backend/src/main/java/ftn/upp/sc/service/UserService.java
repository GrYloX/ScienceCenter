package ftn.upp.sc.service;

import java.util.List;
import java.util.Optional;

import org.camunda.bpm.engine.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ftn.upp.sc.converter.UserConverter;
import ftn.upp.sc.dto.UserDTO;
import ftn.upp.sc.model.TransactionState;
import ftn.upp.sc.model.Type;
import ftn.upp.sc.model.User;
import ftn.upp.sc.model.payment.MerchantOrder;
import ftn.upp.sc.repository.MerchantOrderRepository;
import ftn.upp.sc.repository.UserDetailsRepository;
import ftn.upp.sc.repository.UserRepository;
import ftn.upp.sc.service.base.UserServiceBase;

@Service
public class UserService extends UserServiceBase {
	
	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	MerchantOrderRepository merchantOrderRepository;
	
	@Autowired
	IdentityService identityService;
	
	@Override
	public User saveUser(UserDTO dto) {
		User user = userConverter.DtoToEntity(dto);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		org.camunda.bpm.engine.identity.User camundaUser = identityService.newUser(user.getUsername());
		camundaUser.setPassword(user.getPassword());		
		identityService.saveUser(camundaUser);		
		return userRepository.save(user);
	}
	
	public void failedLogin(User user) {
		user.incrementFailedLoginAttempts();
		userRepository.save(user);
	}

	public void successfulLogin(User user) {
		user.setFailedLoginAttempts(0);
		userRepository.save(user);
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
