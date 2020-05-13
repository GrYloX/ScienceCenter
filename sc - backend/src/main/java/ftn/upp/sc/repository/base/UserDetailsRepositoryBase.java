package ftn.upp.sc.repository.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.upp.sc.model.UserDetails;

public interface UserDetailsRepositoryBase extends JpaRepository<UserDetails, Long> {

}		
