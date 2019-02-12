package ftn.upp.sc.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import ftn.upp.sc.model.search.IndexUnit;

public interface PaperElasticSearchRepository extends ElasticsearchRepository<IndexUnit, String> {

}
