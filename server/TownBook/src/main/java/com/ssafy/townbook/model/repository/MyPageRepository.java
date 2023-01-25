package com.ssafy.townbook.model.repository;

import com.ssafy.townbook.model.entity.BookLog;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MyPageRepository extends JpaRepository<BookLog ,Long> {

    @EntityGraph(attributePaths = {"book"})
    Optional<List<BookLog>> findByAccount_AccountNo(Long accountNo) throws Exception;

//    @EntityGraph(attributePaths = {"book"})
//    Optional<List<BookLog>> findWithBooksByAccountOOrderByBookState(Long accountNo) throws Exception;
//
//    @EntityGraph(attributePaths = {"book"})
//    Optional<List<BookLog>> getReceiveLog(Long accountNo) throws Exception;

}
