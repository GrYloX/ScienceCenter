package ftn.upp.sc.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.upp.sc.model.common.Application;
import ftn.upp.sc.model.common.Magazine;
import ftn.upp.sc.model.common.ScienceField;
import ftn.upp.sc.model.search.IndexUnit;
import ftn.upp.sc.model.users.User;
import ftn.upp.sc.model.users.UserDetails;
import ftn.upp.sc.repository.PaperElasticSearchRepository;
import ftn.upp.sc.repository.common.ApplicationRepository;
import ftn.upp.sc.service.handlers.DocumentHandler;
import ftn.upp.sc.service.handlers.PDFHandler;
import ftn.upp.sc.service.handlers.TextDocHandler;
import ftn.upp.sc.service.handlers.Word2007Handler;
import ftn.upp.sc.service.handlers.WordHandler;

@Service
public class Indexer {
	

	@Autowired
	private PaperElasticSearchRepository repository;
	
	@Autowired
	private ApplicationRepository applicationRepository;
	
	public Indexer() {
	}
	
	
	public boolean delete(String filename){
		if(repository.equals(filename)){
			repository.deleteById(filename);
			return true;
		} else
			return false;
		
	}
	
	public boolean update(IndexUnit unit){
		unit = repository.save(unit);
		if(unit!=null)
			return true;
		else
			return false;
	}
	
	public boolean add(IndexUnit unit){
		System.out.println("jos malo pa zamalo");
		unit = repository.index(unit);
		if(unit!=null)
			return true;
		else
			return false;
	}
	
	/**
	 * 
	 * @param file Direktorijum u kojem se nalaze dokumenti koje treba indeksirati
	 */
	public int index(File file){		
		System.out.println("tu sam");
		DocumentHandler handler = null;
		String fileName = null;
		int retVal = 0;
		try {
			File[] files;
			if(file.isDirectory()){
				files = file.listFiles();
			}else{
				files = new File[1];
				files[0] = file;
			}
			for(File newFile : files){
				if(newFile.isFile()){
					fileName = newFile.getName();
					Application a = applicationRepository.findByFile(fileName);				
					handler = getHandler(fileName);
					if(handler == null){
						System.out.println("Nije moguce indeksirati dokument sa nazivom: " + fileName);
						continue;
					}	
					System.out.println("i ovde sam");
					String text = handler.getText(newFile);
					System.out.println("ali ovde nisam");
					ScienceField sf = a.getScienceField();
					Magazine m = a.getMagazine();
					User author = a.getAuthor();
					UserDetails authorDetails = author.getUserDetails();
					IndexUnit iu = new IndexUnit(m.getName(),text, a.getTitle(),authorDetails.getFirstName()+ " "+authorDetails.getLastName(),a.getKeyTerms(),a.getPaperAbstract(),sf.getName(),a.getFile());
					if(add(iu))
						retVal++;
				} else if (newFile.isDirectory()){
					retVal += index(newFile);
				}
			}
			System.out.println("indexing done");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("indexing NOT done");
		}
		return retVal;
	}
	

	public DocumentHandler getHandler(String fileName){
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