package ftn.upp.sc.controller;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.upp.sc.model.search.AdvancedQuery;
import ftn.upp.sc.model.search.RequiredHighlight;
import ftn.upp.sc.model.search.ResultData;
import ftn.upp.sc.model.search.SearchType;
import ftn.upp.sc.model.search.SimpleQuery;
import ftn.upp.sc.service.search.QueryBuilder;
import ftn.upp.sc.service.search.ResultRetriever;

@RestController
@RequestMapping("search")
public class SearchController {

		@Autowired
		private ResultRetriever resultRetriever;
		
		@PostMapping(consumes="application/json")
		public ResponseEntity<List<ResultData>> searchPhrase(@RequestBody SimpleQuery simpleQuery) throws Exception {
			org.elasticsearch.index.query.QueryBuilder query= QueryBuilder.buildQuery(SearchType.phrase, simpleQuery.getField(), simpleQuery.getValue());
			List<RequiredHighlight> rh = new ArrayList<RequiredHighlight>();
			rh.add(new RequiredHighlight(simpleQuery.getField(), simpleQuery.getValue()));
			List<ResultData> results = resultRetriever.getResults(query, rh);			
			return new ResponseEntity<List<ResultData>>(results, HttpStatus.OK);
		}
		
		@PostMapping(value="boolean", consumes="application/json")
		public ResponseEntity<List<ResultData>> searchBoolean(@RequestBody AdvancedQuery advancedQuery) throws Exception {
			BoolQueryBuilder builder = QueryBuilders.boolQuery();
			List<RequiredHighlight> rh = new ArrayList<RequiredHighlight>();
			System.out.println(advancedQuery.getOperation());
			for(SimpleQuery sq : advancedQuery.getQueries()){				
				org.elasticsearch.index.query.QueryBuilder query = QueryBuilder.buildQuery(SearchType.phrase, sq.getField(), sq.getValue());
				if(advancedQuery.getOperation().equalsIgnoreCase("AND")){
					builder.must(query);
				}
				else if(advancedQuery.getOperation().equalsIgnoreCase("OR")){
					builder.should(query);
				}
				rh.add(new RequiredHighlight(sq.getField(), sq.getValue()));
			}
			List<ResultData> results = resultRetriever.getResults(builder, rh);			
			return new ResponseEntity<List<ResultData>>(results, HttpStatus.OK);
		}
	
		
	
}
