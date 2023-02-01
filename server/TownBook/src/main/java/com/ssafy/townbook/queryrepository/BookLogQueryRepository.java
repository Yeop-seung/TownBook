package com.ssafy.townbook.queryrepository;

import static com.ssafy.townbook.model.entity.QBookLog.bookLog;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.townbook.model.entity.Book;
import java.util.List;
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
     * @return List
     */
    public List<Book> findBookByLockerNo(Long lockerNo) {
        List<Book> result = jpaQueryFactory
                .select(bookLog.book)
                .from(bookLog)
                .where(bookLog.bookLogState.eq(true).and(bookLog.locker.lockerNo.eq(lockerNo)))
                .fetch();
        return result;
    }
    
    /**
     * 단일 도서의 모든 리뷰 조회
     *
     * @param bookIsbn
     * @return List
     */
    public List<String> findBookLogReviewByBookIsbn(String bookIsbn) {
        List<String> result = jpaQueryFactory
                .select(bookLog.bookLogReview)
                .from(bookLog)
                .where(bookLog.book.bookIsbn.eq(bookIsbn))
                .fetch();
        return result;
    }
}
