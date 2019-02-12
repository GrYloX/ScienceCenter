package ftn.upp.sc.service.search;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.upp.sc.model.common.Application;
import ftn.upp.sc.model.common.Magazine;
import ftn.upp.sc.model.common.Paper;
import ftn.upp.sc.model.enums.MagazinePaymentType;
import ftn.upp.sc.model.search.IndexUnit;
import ftn.upp.sc.model.search.RequiredHighlight;
import ftn.upp.sc.model.search.ResultData;
import ftn.upp.sc.model.users.User;
import ftn.upp.sc.model.users.UserDetails;
import ftn.upp.sc.repository.PaperElasticSearchRepository;
import ftn.upp.sc.repository.common.ApplicationRepository;
import ftn.upp.sc.repository.common.PaperRepository;
import ftn.upp.sc.service.handlers.DocumentHandler;
import ftn.upp.sc.service.handlers.PDFHandler;
import ftn.upp.sc.service.handlers.TextDocHandler;
import ftn.upp.sc.service.handlers.Word2007Handler;
import ftn.upp.sc.service.handlers.WordHandler;

@Service
public class ResultRetriever {
	
	@Autowired
	private PaperElasticSearchRepository repository;
	
	@Autowired
	private ApplicationRepository applicationRepository;
	
	@Autowired
	private PaperRepository paperRepository;
	
	
	public ResultRetriever(){
	}

	public List<ResultData> getResults(org.elasticsearch.index.query.QueryBuilder query,
			List<RequiredHighlight> requiredHighlights) {
		if (query == null) {
			return null;
		}
			
		List<ResultData> results = new ArrayList<ResultData>();
        for (IndexUnit indexUnit : repository.search(query)) {
        	Application a = applicationRepository.findByFile(indexUnit.getFilename());
        	Paper p = paperRepository.findByPaperDetails_id(a.getId());
        	Magazine m = a.getMagazine();
			User author = a.getAuthor();
			UserDetails authorDetails = author.getUserDetails();
			Boolean free = false;
			if(m.getPaymentType()==MagazinePaymentType.open_access){
				free = true;
			}
        	results.add(new ResultData(indexUnit.getTitle(),p.getDoi(),authorDetails.getFirstName()+ " "+authorDetails.getLastName(),"",p.getPrice(),indexUnit.getFilename(), m.getName(),free));
		}
        
		
		return results;
	}
	
	protected DocumentHandler getHandler(String fileName){
		if(fileName.endsWith(".txt")){
			return new TextDocHandler();
		}else if(fileName.endsWith(".pdf")){
			return new PDFHandler();
		}else if(fileName.endsWith(".doc")){
			return new WordHandler();
		}else if(fileName.endsWith(".docx")){
			return new Word2007Handler();
		}else{
			return null;
		}
	}
}
