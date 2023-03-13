package dev.jlkeesh.service;

import dev.jlkeesh.config.security.SessionUser;
import dev.jlkeesh.domains.Article;
import dev.jlkeesh.domains.Upload;
import dev.jlkeesh.dto.dto.ArticleCreateDTO;
import dev.jlkeesh.dto.dto.ArticleDTO;
import dev.jlkeesh.dto.dto.ArticleUpdateDTO;
import dev.jlkeesh.dto.upload.UploadCreateDTO;
import dev.jlkeesh.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService implements GenericService<Article, ArticleCreateDTO, ArticleUpdateDTO, String> {
    private final ArticleRepository articleRepository;
    private final SessionUser sessionUser;
    private final UploadService uploadService;

    public ArticleService(ArticleRepository articleRepository, SessionUser sessionUser, UploadService uploadService) {
        this.articleRepository = articleRepository;
        this.sessionUser = sessionUser;
        this.uploadService = uploadService;
    }

    @Override
    public String create(ArticleCreateDTO dto) {
        Upload upload = uploadService.createAndGet(new UploadCreateDTO(dto.getPic()));
        Article article = Article.builder()
                .title(dto.getTitle())
                .overview(dto.getOverview())
                .content(dto.getContent())
                .createdBy(sessionUser.getId())
                .pic(upload)
                .build();
        articleRepository.save(article);
        return article.getId();
    }

    @Override
    public boolean update(ArticleUpdateDTO dto) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public Article get(String s) {
        return null;
    }

    @Override
    public List<Article> getAll() {
        return articleRepository.findAll();
    }
}
