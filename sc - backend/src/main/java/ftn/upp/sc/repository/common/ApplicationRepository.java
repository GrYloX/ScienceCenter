package ftn.upp.sc.repository.common;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.upp.sc.model.common.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
	Application findByFile(String file);
}
