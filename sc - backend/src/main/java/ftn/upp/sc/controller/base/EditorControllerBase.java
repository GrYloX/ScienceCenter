package ftn.upp.sc.controller.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ftn.upp.sc.converter.EditorConverter;
import ftn.upp.sc.dto.EditorDTO;
import ftn.upp.sc.model.Editor;
import ftn.upp.sc.service.EditorService;

@RequestMapping(value="/editor")
public class EditorControllerBase {

	@Autowired
	public EditorService editorService;
	
	@Autowired
	public EditorConverter editorConverter;
	
	@RequestMapping(value="getEditors", method=RequestMethod.GET)
	public ResponseEntity<List<EditorDTO>> getEditors(){
		List<Editor> editors = editorService.findAll();
		List<EditorDTO> editorsDTO = new ArrayList<EditorDTO>();
		for(Editor editor : editors){
			editorsDTO.add(editorConverter.entityToDto(editor));
		}
		return new ResponseEntity<>(editorsDTO, HttpStatus.OK);		
	}	
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<EditorDTO> addEditor(@RequestBody EditorDTO editorDTO){
			
		Editor newEditor = editorService.saveEditor(editorDTO);
		return new ResponseEntity<>(editorConverter.entityToDto(newEditor), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<EditorDTO> getEditor(@PathVariable Integer id) {
		Editor editor = editorService.findOne(id);
		if (editor == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(editorConverter.entityToDto(editor), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<EditorDTO> delete(@PathVariable Integer id) {
		Editor deleted = editorService.deleteEditor(id);
		return new ResponseEntity<>(editorConverter.entityToDto(deleted), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<EditorDTO> edit(@RequestBody EditorDTO editorDTO) {
		Editor edited = editorService.saveEditor(editorDTO);
		return new ResponseEntity<>(editorConverter.entityToDto(edited), HttpStatus.OK);
	}
}
