package ftn.upp.sc.controller;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ftn.upp.sc.service.storage.StorageFileNotFoundException;
import ftn.upp.sc.service.storage.StorageService;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    private final /*FileSystem*/ StorageService storageService;

    @Autowired
    public FileUploadController(/*FileSystem*/StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/files")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws IOException {

        Resource file = storageService.loadAsResource(filename);        
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/files")
    public ResponseEntity<Path> handleFileUpload(@RequestParam("file") MultipartFile file,
                                                 RedirectAttributes redirectAttributes) throws URISyntaxException {

        Path path = storageService.store(file);
        URI uri = path.toUri();

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return ResponseEntity.created(uri)
                .body(path);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/downloadEdition/{editionId}")
    @ResponseBody
    public ResponseEntity<Resource> serveEditionZip(@PathVariable Long editionId) throws IOException{
    	 String filename=storageService.createEditionZip(editionId);
    	 Resource file = storageService.loadAsResource(filename);
         return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                 "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
