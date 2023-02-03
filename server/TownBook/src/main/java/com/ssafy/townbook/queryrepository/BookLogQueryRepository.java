package com.ssafy.townbook.queryrepository;

import static com.ssafy.townbook.model.entity.QBookLog.bookLog;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.townbook.model.entity.Book;
import com.ssafy.townbook.model.entity.BookLog;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookLogQueryRepository {
    
    private JPAQueryFactory jpaQueryFactory;
    
    @Autowired
    public BookLogQueryRepository(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }
    
    /**
     * 단일 보관함에 보관중인 도서 전부 조회
     *
     * @param lockerNo
     * @return Optional<List < Book>>
     */
    public Optional<List<BookLog>> findBookLogByLockerNo(Long lockerNo) {
        List<BookLog> result = jpaQueryFactory
                .select(bookLog)
                .from(bookLog)
                .where(bookLog.bookLogState.eq(true).and(bookLog.locker.lockerNo.eq(lockerNo)))
                .fetch();
        return Optional.ofNullable(result);
    }
    
    /**
     * 단일 도서의 모든 리뷰 조회
     *
     * @param bookIsbn
     * @return Optional<List < BookLog.bookLogReview>>
     */
    public Optional<List<String>> findBookLogReviewByBookIsbn(String bookIsbn) {
        return Optional.ofNullable(jpaQueryFactory
                .select(bookLog.bookLogReview)
                .from(bookLog)
                .where(bookLog.book.bookIsbn.eq(bookIsbn))
                .fetch());
    }
    
    /**
     * 단일 회원의 모든 북로그 조회
     *
     * @param accountNo
     * @return Optional<List<BookLog>>
     */
    public Optional<List<BookLog>> findBookLogByAccountNo(Long accountNo) {
        return Optional.ofNullable(jpaQueryFactory
                .select(bookLog)
                .from(bookLog)
                .where(bookLog.account.accountNo.eq(accountNo))
                .fetch());
    }
}
