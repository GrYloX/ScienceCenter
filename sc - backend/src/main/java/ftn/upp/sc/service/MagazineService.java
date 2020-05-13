package ftn.upp.sc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ftn.upp.sc.config.DatabaseUri;
import ftn.upp.sc.converter.MagazineConverter;
import ftn.upp.sc.dto.MagazineDTO;
import ftn.upp.sc.model.Magazine;
import ftn.upp.sc.repository.MagazineRepository;
import ftn.upp.sc.service.base.MagazineServiceBase;

@Service
public class MagazineService extends MagazineServiceBase {
	
	public Magazine saveMagazine(Magazine entity) {
		return magazineRepository.save(entity);
	}

}
