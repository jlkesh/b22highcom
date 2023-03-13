package dev.jlkeesh.repository;

import dev.jlkeesh.domains.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, String> {
}