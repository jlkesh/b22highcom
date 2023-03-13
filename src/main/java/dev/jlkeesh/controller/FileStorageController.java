package dev.jlkeesh.controller;

import dev.jlkeesh.domains.Upload;
import dev.jlkeesh.repository.UploadRepository;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.Path;

@Controller
@RequestMapping("/files")
public class FileStorageController {
    private final UploadRepository uploadRepository;
    private Path rootPath = Path.of(System.getProperty("user.home"), "/uploads");;

    public FileStorageController(UploadRepository uploadRepository) {
        this.uploadRepository = uploadRepository;
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> homePage(@PathVariable String filename) {
        Upload upload = uploadRepository.findByGeneratedName(filename);
        Resource resource = new FileSystemResource(rootPath.resolve(filename));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(upload.getContentType()))
                .contentLength(upload.getSize())
                .body(resource);
    }
}
