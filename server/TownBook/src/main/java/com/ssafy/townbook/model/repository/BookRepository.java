package com.ssafy.townbook.model.repository;

import com.ssafy.townbook.model.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> { // Long -> String
    
    // 전체 조회
    List<Book> findAll();
    
    // ISBN 으로 책 조회
    Book findBookByBookIsbn(String bookIsbn);
}
