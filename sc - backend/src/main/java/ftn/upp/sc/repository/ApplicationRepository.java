package ftn.upp.sc.repository;


import org.springframework.stereotype.Repository;

import ftn.upp.sc.model.Application;
import ftn.upp.sc.repository.base.ApplicationRepositoryBase;

@Repository
public interface ApplicationRepository extends ApplicationRepositoryBase {
	Application findByFile(String file);
}
