package ftn.upp.sc.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.upp.sc.converter.user.EditorConverter;
import ftn.upp.sc.dto.user.EditorDTO;
import ftn.upp.sc.model.users.Editor;
import ftn.upp.sc.repository.user.EditorRepository;


@Service
public class EditorService {
	
	@Autowired
	EditorRepository editorRepository;
	
	@Autowired
	EditorConverter editorConverter;
	
	public List<Editor> findAll() {
		return editorRepository.findAll();
	}
	
	public Editor findOne(Integer id) {
		Optional<Editor> editor = editorRepository.findById(id);
		return editor.get();
	}
	
	public Editor saveEditor(EditorDTO dto) {
		Editor editor = editorConverter.DtoToEntity(dto);
		return editorRepository.save(editor);
	}

	public Editor deleteEditor(Integer id) {
		Editor editor = this.findOne(id);
		if(editor == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant");
		}
		editorRepository.delete(editor);
		return editor;
		
		
	}

}
