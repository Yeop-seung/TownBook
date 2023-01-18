package com.ssafy.TownBook.model.repository;

import com.ssafy.TownBook.model.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
