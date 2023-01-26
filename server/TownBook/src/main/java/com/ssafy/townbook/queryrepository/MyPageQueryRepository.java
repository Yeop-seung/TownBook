package com.ssafy.townbook.queryrepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.townbook.model.entity.Account;
import com.ssafy.townbook.model.entity.Book;
import com.ssafy.townbook.model.entity.BookLog;
import com.ssafy.townbook.model.entity.WishList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.ssafy.townbook.model.entity.QAccount.account;
import static com.ssafy.townbook.model.entity.QBook.book;
import static com.ssafy.townbook.model.entity.QBookLog.bookLog;
import static com.ssafy.townbook.model.entity.QWishList.wishList;

@RequiredArgsConstructor
@Repository
public class MyPageQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<Account> findByName(Long accountNo) throws Exception {
        return queryFactory.selectFrom(account).where(account.accountNo.eq(accountNo)).fetch();
    }

    public Optional<List<BookLog>> getBookLogByAccountNo(Long accountNo) throws Exception {
        return Optional.ofNullable(queryFactory.selectFrom(bookLog).where(bookLog.account.accountNo.eq(accountNo)).fetch());
    }

    public Optional<BookLog> getBookLogByBookLogNo(Long bookLogNo) throws Exception {
        return Optional.ofNullable(queryFactory.selectFrom(bookLog).where(bookLog.account.accountNo.eq(bookLogNo)).fetch().get(0));
    }

    public Optional<List<BookLog>> getReceiveBookLog(Long receiverNo) throws Exception {
        return Optional.ofNullable(queryFactory.selectFrom(bookLog).where(bookLog.bookLogReceiverNo.eq(receiverNo)).fetch());
    }

    public Optional<Book> getBook(String isbn) throws Exception {
        return Optional.ofNullable(queryFactory.selectFrom(book).where(book.bookIsbn.eq(isbn)).fetch().get(0));
    }

    public Optional<List<WishList>> getWishList(Long accountNo) throws Exception {
        return Optional.ofNullable(queryFactory.selectFrom(wishList).where(wishList.account.accountNo.eq(accountNo)).fetch());
    }
}
