package com.ssafy.townbook.model.repository;

import com.ssafy.townbook.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, String> { // Long -> String
    

    /**
     * ISBN 으로 책 조회
     *
     * @param bookIsbn
     * @return Optional<Book>
     */
    Optional<Book> findBookByBookIsbn(String bookIsbn);
}
