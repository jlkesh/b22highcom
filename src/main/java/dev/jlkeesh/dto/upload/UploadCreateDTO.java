package dev.jlkeesh.dto.upload;

import dev.jlkeesh.dto.Dto;
import org.springframework.web.multipart.MultipartFile;

public class UploadCreateDTO implements Dto {
    private MultipartFile file;

    public UploadCreateDTO(MultipartFile file) {
        this.file = file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }
}

