package dev.jlkeesh.repository;

import dev.jlkeesh.domains.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, String> {
}