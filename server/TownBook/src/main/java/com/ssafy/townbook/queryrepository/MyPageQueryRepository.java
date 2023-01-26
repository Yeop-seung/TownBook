package com.ssafy.townbook.queryrepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.townbook.model.entity.Account;
import com.ssafy.townbook.model.entity.Book;
import com.ssafy.townbook.model.entity.BookLog;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.ssafy.townbook.model.entity.QAccount.account;
import static com.ssafy.townbook.model.entity.QBook.book;
import static com.ssafy.townbook.model.entity.QBookLog.bookLog;

@RequiredArgsConstructor
@Repository
public class MyPageQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<Account> findByName(Long accountNo) throws Exception {
        return queryFactory.selectFrom(account).where(account.accountNo.eq(accountNo)).fetch();
    }

    public Optional<List<BookLog>> getBookLog(Long accountNo) throws Exception {
        return Optional.ofNullable(queryFactory.selectFrom(bookLog).where(bookLog.account.accountNo.eq(accountNo)).fetch());
    }

    public Optional<List<Book>> getBook(String isbn) throws Exception {
        return Optional.ofNullable(queryFactory.selectFrom(book).where(book.bookIsbn.eq(isbn)).fetch());
    }
}
