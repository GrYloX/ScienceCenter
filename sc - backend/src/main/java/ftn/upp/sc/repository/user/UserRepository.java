package ftn.upp.sc.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.upp.sc.model.users.User; 

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
}