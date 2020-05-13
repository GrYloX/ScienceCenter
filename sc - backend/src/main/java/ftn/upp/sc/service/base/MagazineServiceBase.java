package ftn.upp.sc.service.base;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.upp.sc.converter.MagazineConverter;
import ftn.upp.sc.dto.MagazineDTO;
import ftn.upp.sc.model.Magazine;
import ftn.upp.sc.repository.MagazineRepository;

public class MagazineServiceBase {
	
	@Autowired
	public MagazineRepository magazineRepository;
	
	@Autowired
	public MagazineConverter magazineConverter;
	
	public List<Magazine> findAll() {
		return magazineRepository.findAll();
	}
	
	public Magazine findOne(String id) {
		Optional<Magazine> magazine = magazineRepository.findById(id);
		return magazine.get();
	}
	
	public Magazine saveMagazine(MagazineDTO dto) {
		Magazine magazine = magazineConverter.DtoToEntity(dto);
		return magazineRepository.save(magazine);
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
