package ftn.upp.sc.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.upp.sc.model.common.Magazine;

@Repository
public interface MagazineRepository extends JpaRepository<Magazine, String> {

}
