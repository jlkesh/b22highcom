package dev.jlkeesh.service;

import dev.jlkeesh.config.security.SessionUser;
import dev.jlkeesh.domains.Article;
import dev.jlkeesh.domains.Upload;
import dev.jlkeesh.dto.dto.ArticleCreateDTO;
import dev.jlkeesh.dto.dto.ArticleDTO;
import dev.jlkeesh.dto.dto.ArticleUpdateDTO;
import dev.jlkeesh.dto.upload.UploadCreateDTO;
import dev.jlkeesh.dto.upload.UploadDTO;
import dev.jlkeesh.dto.upload.UploadUpdateDTO;
import dev.jlkeesh.repository.ArticleRepository;
import dev.jlkeesh.repository.UploadRepository;
import dev.jlkeesh.utils.BaseUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
public class UploadService implements GenericService<UploadDTO, UploadCreateDTO, UploadUpdateDTO, String> {
    private Path rootPath;
    private final UploadRepository uploadRepository;

    public UploadService(UploadRepository uploadRepository) {
        this.uploadRepository = uploadRepository;
    }

    @PostConstruct
    public void init() throws IOException {
        rootPath = Path.of(System.getProperty("user.home"), "/uploads");
        if (!Files.exists(rootPath)) {
            Files.createDirectories(rootPath);
        }
    }

    @Override
    public String create(UploadCreateDTO dto) {
        return createAndGet(dto).getId();
    }

    @Override
    public boolean update(UploadUpdateDTO dto) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public UploadDTO get(String s) {
        return null;
    }

    @Override
    public List<UploadDTO> getAll() {
        return null;
    }

    public Upload createAndGet(UploadCreateDTO dto) {
        MultipartFile file = dto.getFile();
        Upload upload = Upload.builder()
                .contentType(file.getContentType())
                .size(file.getSize())
                .originalName(file.getOriginalFilename())
                .generatedName(BaseUtils.generateUniqueName(Objects.requireNonNull(file.getOriginalFilename())))
                .build();
        uploadRepository.save(upload);
        CompletableFuture.runAsync(() -> {
            Path path = rootPath.resolve(upload.getGeneratedName());
            try {
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return upload;
    }
}
