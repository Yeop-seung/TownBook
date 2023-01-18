package com.ssafy.TownBook.model.repository;

import com.ssafy.TownBook.model.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
