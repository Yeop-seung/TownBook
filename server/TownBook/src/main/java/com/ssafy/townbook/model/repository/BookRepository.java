package com.ssafy.townbook.model.repository;

import com.ssafy.townbook.model.entity.Book;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> { // Long -> String
    
    
    /**
     * 도서의 ISBN으로 단일 도서 조회
     *
     * @param bookIsbn
     * @return Optional<Book>
     */
    Optional<Book> findBookByBookIsbn(String bookIsbn);
}
