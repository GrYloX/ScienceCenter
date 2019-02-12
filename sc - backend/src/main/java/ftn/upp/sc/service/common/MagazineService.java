package ftn.upp.sc.service.common;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ftn.upp.sc.config.DatabaseUri;
import ftn.upp.sc.converter.common.MagazineConverter;
import ftn.upp.sc.dto.common.MagazineDTO;
import ftn.upp.sc.dto.payment.PaymentParametersDTO;
import ftn.upp.sc.model.common.Magazine;
import ftn.upp.sc.repository.common.MagazineRepository;

@Service
public class MagazineService {
	
	@Autowired
	MagazineRepository magazineRepository;
	
	@Autowired
	MagazineConverter magazineConverter;	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private DatabaseUri databaseUri;
	
	public List<Magazine> findAll() {
		return magazineRepository.findAll();
	}
	
	public Magazine findOne(String id) {
		Optional<Magazine> magazine = magazineRepository.findById(id);
		return magazine.get();
	}
	
	public Magazine saveMagazine(MagazineDTO dto) {
		Magazine magazine = magazineConverter.DtoToEntity(dto);
		/*HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<MagazineDTO> entity = new HttpEntity<MagazineDTO>(dto,headers);
		restTemplate.exchange(databaseUri.getKpUri()+"/merchanti", HttpMethod.POST,entity,MagazineDTO.class);*/
		return magazineRepository.save(magazine);
	}
	
	public Magazine saveMagazine(Magazine entity) {
		return magazineRepository.save(entity);
	}

	public Magazine deleteMagazine(String id) {
		Magazine magazine = this.findOne(id);
		if(magazine == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant");
		}
		magazineRepository.delete(magazine);
		return magazine;
		
		
	}

}
