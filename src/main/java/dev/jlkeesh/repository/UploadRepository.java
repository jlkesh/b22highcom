package dev.jlkeesh.repository;

import dev.jlkeesh.domains.Upload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UploadRepository extends JpaRepository<Upload, String> {
    @Query("select u from Upload u where u.generatedName = ?1")
    Upload findByGeneratedName(String generatedName);
}