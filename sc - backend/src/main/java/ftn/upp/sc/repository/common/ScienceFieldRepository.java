package ftn.upp.sc.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.upp.sc.model.common.ScienceField;

@Repository
public interface ScienceFieldRepository extends JpaRepository<ScienceField, Long> {

}
